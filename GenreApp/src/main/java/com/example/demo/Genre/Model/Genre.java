package com.example.demo.Genre.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Entity
@Table(name="genres")
public class Genre implements Comparable<Genre>, Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;


    @Override
    public int compareTo(Genre o) {
        if (name.compareTo(o.name)!=0) return name.compareTo(o.name);
        return description.compareTo(o.description);
    }
}


