package com.example.bookhub.mapper;

import com.example.bookhub.dto.BookDTO;
import com.example.bookhub.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    // Entity → DTO
    @Mapping(source = "category.id", target = "categoryId")
    BookDTO toDTO(Book book);

    // DTO → Entity
    @Mapping(source = "categoryId", target = "category.id")
    Book toEntity(BookDTO dto);

    List<BookDTO> toDTOList(List<Book> books);

    List<Book> toEntityList(List<BookDTO> dtos);
}
