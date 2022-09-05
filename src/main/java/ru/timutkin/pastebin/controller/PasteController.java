package ru.timutkin.pastebin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.timutkin.pastebin.exception.PasteNotActiveException;
import ru.timutkin.pastebin.exception.PasteNotFoundException;
import ru.timutkin.pastebin.factory.PasteDTOFactory;
import ru.timutkin.pastebin.factory.PasteFactory;
import ru.timutkin.pastebin.service.PasteService;
import ru.timutkin.pastebin.store.dto.PasteDTO;
import ru.timutkin.pastebin.store.entity.PasteEntity;
import ru.timutkin.pastebin.store.enumeration.PasteAccessStatus;
import ru.timutkin.pastebin.store.enumeration.PasteStatus;
import ru.timutkin.pastebin.store.repository.PasteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/pastebin")
public class PasteController {

    PasteService pasteService;


    PasteDTOFactory pasteDTOFactory;

    PasteFactory pasteFactory;

    private static final String REF = "http://localhost:2022/api/v1/pastebin/";

    @GetMapping("/{hash}")
    public ResponseEntity<PasteDTO> getPaste(@PathVariable String hash){

        PasteEntity paste = pasteService.getPasteByHash(hash);

        return ResponseEntity.ok(pasteDTOFactory.createPasteDTO(paste));
    }

    @GetMapping("/")
    public ResponseEntity<List<PasteDTO>> getLastPaste(){
        List<PasteEntity> pastes = pasteService.getLastPaste();

        return ResponseEntity.ok(pasteDTOFactory.createListPasteDTO(pastes));
    }

    @PostMapping("/")
    public ResponseEntity<String> createPaste(@RequestParam(name = "data") String data,
                                              @RequestParam(name = "expirationTime") String expirationTime,
                                              @RequestParam(name = "access")PasteAccessStatus accessStatus){

            LocalDateTime time = LocalDateTime.parse(expirationTime);

            PasteEntity paste = pasteFactory.makeDefault(data, time, accessStatus);

            pasteService.savePaste(paste);

            return ResponseEntity.ok(REF+paste.getHash());
    }

}
