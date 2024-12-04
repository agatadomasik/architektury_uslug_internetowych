package com.example.demo.Genre.DTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class GenreReadDTO {
    private UUID id;
    private String name;
    private String description;
}
