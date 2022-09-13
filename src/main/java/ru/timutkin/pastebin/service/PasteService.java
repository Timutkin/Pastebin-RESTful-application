package ru.timutkin.pastebin.service;

import ru.timutkin.pastebin.exception.IncorrectTimeException;
import ru.timutkin.pastebin.exception.PasteNotActiveException;
import ru.timutkin.pastebin.exception.PasteNotFoundException;

import ru.timutkin.pastebin.store.entity.PasteEntity;


import java.awt.print.Pageable;
import java.util.List;

public interface PasteService{

    PasteEntity getPasteByHash(String hash) throws PasteNotFoundException, PasteNotActiveException;

    List<PasteEntity> getLastPaste() throws PasteNotFoundException;

    void savePaste(PasteEntity paste) throws IncorrectTimeException;

    List<PasteEntity> getPage(int number);

    void updatePasteStatus();

}
