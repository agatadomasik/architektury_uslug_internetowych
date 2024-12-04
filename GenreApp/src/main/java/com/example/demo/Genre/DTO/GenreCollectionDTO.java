package com.example.demo.Genre.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GenreCollectionDTO {
    private UUID id;
    private String name;
    private String description;
}
