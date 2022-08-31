package ru.timutkin.pastebin.store.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.timutkin.pastebin.hash.HashGenerator;
import ru.timutkin.pastebin.store.enumeration.PasteAccessStatus;
import ru.timutkin.pastebin.store.enumeration.PasteStatus;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paste", schema = "public")
@ToString
public class Paste {
    @Id
    @SequenceGenerator(name = "paste_seq",
            sequenceName = "paste_sequence",
            allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paste_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data")
    String data;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    PasteStatus status;

    @Column(name = "hash")
    String hash;

    @Column(name = "expiration_time")
    LocalDateTime expirationTime;

    @Column(name = "creation_time")
    LocalDateTime creationTime;

    @Column(name = "access")
    @Enumerated(EnumType.STRING)
    PasteAccessStatus accessStatus;



}
