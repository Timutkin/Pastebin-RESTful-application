package ru.timutkin.pastebin.shedule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.timutkin.pastebin.store.entity.Paste;
import ru.timutkin.pastebin.store.enumeration.PasteStatus;
import ru.timutkin.pastebin.store.repository.PasteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@EnableScheduling
@Slf4j
public class UpdateStatusPasteSchedule {

    PasteRepository pasteRepository;

    @Scheduled(fixedRate = 1000)
    public void scheduleUpdatePasteStatus(){
        List<Paste> pasteList = pasteRepository.findPasteByExpirationTimeBefore(LocalDateTime.now());
        pasteList.stream()
                .peek(paste -> paste.setStatus(PasteStatus.NOT_ACTIVE))
                .forEach(paste -> pasteRepository.save(paste));

    }



}
