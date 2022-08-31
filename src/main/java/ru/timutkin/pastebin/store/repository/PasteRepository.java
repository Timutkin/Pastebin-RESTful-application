package ru.timutkin.pastebin.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.timutkin.pastebin.store.entity.Paste;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PasteRepository extends JpaRepository<Paste, Long> {

    Optional<Paste> findPasteByHash(String hash);

    @Query(value = "select * from paste where access = 'PUBLIC' and status = 'ACTIVE' order by id desc limit 10 ",
            nativeQuery = true)
    List<Paste> findLastTenPaste();

    List<Paste> findPasteByExpirationTimeBefore(LocalDateTime timeBefore);

}