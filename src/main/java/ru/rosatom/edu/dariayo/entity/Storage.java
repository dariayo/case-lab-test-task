package ru.rosatom.edu.dariayo.entity;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "storage")
@Entity(name = "storage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file")
    private byte[] file;
    @Column(name = "title")
    private String title;
    @Column(name = "creation_date")
    private Date creation_date;
    @Column(name = "description")
    private String description;
}
