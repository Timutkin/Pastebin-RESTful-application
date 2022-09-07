package ru.timutkin.pastebin.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.timutkin.pastebin.exception.IncorrectTimeException;
import ru.timutkin.pastebin.exception.PasteNotActiveException;
import ru.timutkin.pastebin.exception.PasteNotFoundException;
import ru.timutkin.pastebin.store.entity.PasteEntity;
import ru.timutkin.pastebin.store.enumeration.PasteAccessStatus;
import ru.timutkin.pastebin.store.enumeration.PasteStatus;
import ru.timutkin.pastebin.store.repository.PasteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@EnableScheduling
public class IPasteService implements PasteService{

    PasteRepository pasteRepository;

    @Override

    public PasteEntity getPasteByHash(String hash) {
        Optional<PasteEntity> mayBePaste = pasteRepository.findPasteByHash(hash);

        PasteEntity paste = mayBePaste.orElseThrow(
                ()-> new PasteNotFoundException(String.format("Paste with hash = {%s} not found ",hash))
        );

        if (paste.getStatus().equals(PasteStatus.NOT_ACTIVE)) {
            throw new PasteNotActiveException("The period of access to pasta is over");
        }

        return paste;
        }

        @Override
        public List<PasteEntity> getLastPaste () {
            List<PasteEntity> pastes = pasteRepository.findLastTenPaste();

            if (pastes.isEmpty()){
                throw  new PasteNotFoundException(String.format("Pastes with status = %s  and access = %s not found",
                        PasteAccessStatus.PUBLIC, PasteStatus.ACTIVE));
            }

            return pastes;
        }

        @Override
        public void savePaste (PasteEntity paste){

                if (paste.getExpirationTime().isBefore(LocalDateTime.now())) {
                    throw new IncorrectTimeException("The expiration time of the paste should be longer than the current time");
                }

                pasteRepository.save(paste);
            }

        @Override
        @Scheduled(fixedRate = 1000)
        public void updatePasteStatus () {

            List<PasteEntity> pasteList = pasteRepository.findPasteByExpirationTimeBefore(LocalDateTime.now());

            pasteList.stream()
                    .peek(paste -> paste.setStatus(PasteStatus.NOT_ACTIVE))
                    .forEach(paste -> pasteRepository.save(paste));
        }
    }
