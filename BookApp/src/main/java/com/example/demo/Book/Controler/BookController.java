package com.example.demo.Book.Controler;

import com.example.demo.Book.DTO.BookCollectionDTO;
import com.example.demo.Book.DTO.BookCreateUpdateDTO;
import com.example.demo.Book.DTO.BookReadDTO;
import com.example.demo.Book.Model.Book;
import com.example.demo.Book.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookCollectionDTO>> getAllBooks(
            @RequestParam(value = "sort", required = false) String sortType,
            @RequestParam(value = "order", required = false) String orderType,
            @RequestParam(value = "publication_year", required = false, defaultValue = "0") int pubYear,
            @RequestParam(value = "author", required = false) String author)
    {
        List<Book> books = bookService.findAll();

        boolean isAsc = !"desc".equalsIgnoreCase(orderType);

        if (sortType != null){
            switch (sortType.toLowerCase()) {
                case "title":
                    if (isAsc) {
                        books.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
                    } else {
                        books.sort((o1, o2) -> o2.getTitle().compareTo(o1.getTitle()));
                    }
                    break;

                case "author":
                    if (isAsc) {
                        books.sort((o1, o2) -> o1.getAuthor().compareTo(o2.getAuthor()));
                    } else {
                        books.sort((o1, o2) -> o2.getAuthor().compareTo(o1.getAuthor()));
                    }
                    break;

                case "publication_year":
                default:
                    if (isAsc) {
                        books.sort((o1, o2) -> Integer.compare(o1.getPublicationYear(), o2.getPublicationYear()));
                    } else {
                        books.sort((o1, o2) -> Integer.compare(o2.getPublicationYear(), o1.getPublicationYear()));
                    }
                    break;
            }
        }

        if (pubYear != 0) {
            books = books.stream()
                    .filter(o1 -> o1.getPublicationYear() == pubYear)
                    .collect(Collectors.toList());
        }
        if (author != null){
            books = books.stream()
                    .filter(o1 -> o1.getAuthor().equals(author))
                    .collect(Collectors.toList());
        }

        List<BookCollectionDTO> dtoList = books.stream()
                .map(this::mapToCollectionDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReadDTO> getBookById(@PathVariable UUID id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(mapToReadDTO(book));
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<List<Book>> getWeaponByGenre(@PathVariable UUID id) {
        return new ResponseEntity<>(bookService.findAllByGenre(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookReadDTO> createBook(@RequestBody BookCreateUpdateDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setGenreId(UUID.fromString(bookDTO.getGenreId()));

        Book savedBook = bookService.save(book);
        BookReadDTO readDTO = mapToReadDTO(savedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(readDTO);
    }

    @PostMapping("/genres")
    public void createGenres(@RequestBody List<UUID> genreIds) {
        System.out.println("Otrzymano ID gatunków: " + genreIds);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookReadDTO> updateBook(@PathVariable UUID id, @RequestBody BookCreateUpdateDTO bookDTO) {
        //Genre genre = genreService.findByName(bookDTO.getGenre());
        Book updatedBook = bookService.update(id, mapToEntity(bookDTO));
        return ResponseEntity.ok(mapToReadDTO(updatedBook));
    }



//    @GetMapping("/genre/{genreName}")
//    public ResponseEntity<List<BookCollectionDTO>> getBooksByGenre(@PathVariable String genreName) {
//        List<Book> books = bookService.findBooksByGenreName(genreName);
//        List<BookCollectionDTO> dtoList = books.stream()
//                .map(this::mapToCollectionDTO)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(dtoList);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Void> deleteBookByGenre(@PathVariable UUID id) {
        bookService.deleteByGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Book mapToEntity(BookCreateUpdateDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublicationYear(dto.getPublicationYear());
        book.setGenreId(UUID.fromString(dto.getGenreId()));
        return book;
    }

    private BookReadDTO mapToReadDTO(Book book) {
        BookReadDTO dto = new BookReadDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setGenreId(book.getGenreId());
        return dto;
    }

    private BookCollectionDTO mapToCollectionDTO(Book book) {
        BookCollectionDTO dto = new BookCollectionDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setGenreId(book.getGenreId());
        return dto;
    }
}
