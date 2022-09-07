package ru.timutkin.pastebin.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.timutkin.pastebin.store.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


}