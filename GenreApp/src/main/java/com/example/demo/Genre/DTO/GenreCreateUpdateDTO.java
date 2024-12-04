package com.example.demo.Genre.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenreCreateUpdateDTO {
    private String name;
    private String description;
}
