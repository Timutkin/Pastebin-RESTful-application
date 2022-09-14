package ru.timutkin.pastebin.client;

import org.springframework.http.ResponseEntity;
import ru.timutkin.pastebin.exception.IncorrectTimeException;
import ru.timutkin.pastebin.exception.PasteNotActiveException;
import ru.timutkin.pastebin.exception.PasteNotFoundException;
import ru.timutkin.pastebin.store.entity.PasteEntity;

import java.util.List;

public interface PasteClient {

    ResponseEntity<PasteEntity> getPasteByHash(String hash) throws PasteNotFoundException, PasteNotActiveException;

    ResponseEntity<List<PasteEntity>> getLastPaste() throws PasteNotFoundException;

    ResponseEntity<String> savePaste(PasteEntity paste) throws IncorrectTimeException;

    ResponseEntity<List<PasteEntity>> getPage(int number);

}
