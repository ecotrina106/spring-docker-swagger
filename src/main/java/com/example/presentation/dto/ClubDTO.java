package com.example.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDTO {
    private Long id;
    private String name;
    private String country;
}