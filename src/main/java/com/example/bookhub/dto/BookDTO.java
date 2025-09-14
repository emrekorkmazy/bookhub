package com.example.bookhub.dto;

import lombok.*;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Long categoryId;
}
