package com.learning.springtransactionmgmtinterceptordemo.controller;

import com.learning.springtransactionmgmtinterceptordemo.domain.Book;
import com.learning.springtransactionmgmtinterceptordemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }
    
    @PutMapping("/update")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable(value = "id", required = true) Long id) {
        bookService.deleteBook(id);
    }
}
