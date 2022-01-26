package com.learning.springtransactionmgmtinterceptordemo.service;

import com.learning.springtransactionmgmtinterceptordemo.domain.Book;
import com.learning.springtransactionmgmtinterceptordemo.repository.BookRepository;
import com.learning.springtransactionmgmtinterceptordemo.transactionmgmt.TransactionMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private TransactionMgmt transactionMgmt;
    
    public void addBook(Book book) {
        bookRepository.save(book);    
    }

    public void updateBook(Book book) {
        //transactionMgmt.propagation_required(book.getId());
        // transactionMgmt.propagation_requiresNew(book.getId());
        // transactionMgmt.propagation_mandatory(book.getId());
        // transactionMgmt.propagation_supports(book.getId());
        // transactionMgmt.propagation_notSupported(book.getId());
         transactionMgmt.propagation_never(book.getId());
    }
    
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
