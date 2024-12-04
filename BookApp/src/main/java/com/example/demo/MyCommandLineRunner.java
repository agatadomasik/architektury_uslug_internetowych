//package com.example.demo;
//
//import com.example.demo.Book.Model.Book;
//import com.example.demo.Book.Service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Scanner;
//import java.util.UUID;
//
//@Component
//public class MyCommandLineRunner implements CommandLineRunner {
//
//    private final BookService bookService;
//
//    @Autowired
//    public MyCommandLineRunner(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @Override
//    public void run(String... args) {
//        Scanner scanner = new Scanner(System.in);
//        boolean running = true;
//
//        while (running) {
//            System.out.println("Options:\n 0: all genres\n 1: all books\n 2: add genre\n " +
//                    "3: add book\n 4: delete genre\n 5: delete book\n 6: exit");
//            String command = scanner.nextLine();
//
//            switch (command) {
//                case "0":
//                    bookService.findGenreIds().forEach(System.out::println);
//                    break;
//
//                case "1":
//                    bookService.findAll().forEach(System.out::println);
//                    break;
//
//                case "2":
//                    System.out.println("Enter genre name:");
//                    String name = scanner.nextLine();
//
//                    System.out.println("Enter genre description:");
//                    String description = scanner.nextLine();
//
//                    bookService.createGenre(name, description);
//
//                    break;
//
//                case "3":
//                    System.out.println("Enter book title:");
//                    String title = scanner.nextLine();
//
//                    System.out.println("Enter book author:");
//                    String author = scanner.nextLine();
//
//                    System.out.println("Enter book publication year:");
//                    int publicationYear = scanner.nextInt();
//
//                    System.out.println("Select genre:");
//                    List<UUID> genres = bookService.findGenreIds();
//                    for (int i = 0; i < genres.size(); i++) {
//                        System.out.println(i + ": " + genres.get(i));
//                    }
//                    int selectedIndex = scanner.nextInt();
//
//
//                    Book book = Book.builder()
//                            .title(title)
//                            .author(author)
//                            .publicationYear(publicationYear)
//                            .genreId(genres.get(selectedIndex))
//                            .build();
//
//                    bookService.save(book);
//                    break;
//
//                case "4":
//                    System.out.println("Enter genre name to delete:");
//                    //UUID id = UUID.fromString(scanner.nextLine());
//                    String deleteName = scanner.nextLine();
////                    genreService.deleteByName(deleteName);
////                    genreService.findAll().forEach(System.out::println);
//                    break;
//
//                case "5":
//                    System.out.println("Enter book id to delete:");
//                    UUID id = UUID.fromString(scanner.nextLine());
//                    //String deleteTitle = scanner.nextLine();
//                    bookService.deleteById(id);
//                    bookService.findAll().forEach(System.out::println);
//                    break;
//
//                case "6":
//                    running = false;
//                    break;
//            }
//        }
//
//        scanner.close();
//    }
//}
//
