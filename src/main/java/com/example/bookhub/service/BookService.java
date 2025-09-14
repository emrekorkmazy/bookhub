package com.example.bookhub.service;

import com.example.bookhub.dto.BookDTO;
import com.example.bookhub.entity.Book;
import com.example.bookhub.entity.Category;
import com.example.bookhub.mapper.BookMapper;
import com.example.bookhub.repository.BookRepository;
import com.example.bookhub.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookDTO> getBookById(long id){
        return bookRepository.findById(id).map(bookMapper::toDTO);
    }

    public BookDTO createBook(BookDTO dto){
        Book book = bookMapper.toEntity(dto);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public BookDTO updateBook(long id, BookDTO dto){
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(dto.getTitle());
                    existingBook.setAuthor(dto.getAuthor());
                    if(dto.getCategoryId() != null){
                        Category category = categoryRepository.findById(dto.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                        existingBook.setCategory(category);
                    }
                    return bookMapper.toDTO(bookRepository.save(existingBook));
                })
                .orElseThrow(()-> new RuntimeException("Book not found"));
    }

    public void deleteBook(long id){
        bookRepository.deleteById(id);
    }

    public List<BookDTO> getBooksByCategoryId(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId)
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }


}
