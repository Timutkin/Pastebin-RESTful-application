package ru.timutkin.pastebin.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.timutkin.pastebin.store.entity.PasteEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PasteRepository extends JpaRepository<PasteEntity, Long> {

    Optional<PasteEntity> findPasteByHash(String hash);

    @Query(value = "select * from paste where access = 'PUBLIC' and status = 'ACTIVE' order by id desc limit 10 ",
            nativeQuery = true)
    List<PasteEntity> findLastTenPaste();

    List<PasteEntity> findPasteByExpirationTimeBefore(LocalDateTime timeBefore);

    List<PasteEntity> findPasteEntitiesByDataIsContainingIgnoreCase(String filter);


}