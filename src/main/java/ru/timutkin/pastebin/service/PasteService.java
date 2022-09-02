package ru.timutkin.pastebin.service;

import ru.timutkin.pastebin.exception.IncorrectTimeException;
import ru.timutkin.pastebin.exception.PasteNotActiveException;
import ru.timutkin.pastebin.exception.PasteNotFoundException;
import ru.timutkin.pastebin.store.entity.Paste;

import java.util.List;

public interface PasteService{

    Paste getPasteByHash(String hash) throws PasteNotFoundException, PasteNotActiveException;

    List<Paste> getLastPaste() throws PasteNotFoundException;

    void savePaste(Paste paste) throws IncorrectTimeException;

    void updatePasteStatus();

}
