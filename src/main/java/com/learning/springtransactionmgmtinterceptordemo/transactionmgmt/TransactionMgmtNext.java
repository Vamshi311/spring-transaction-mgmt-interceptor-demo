package com.learning.springtransactionmgmtinterceptordemo.transactionmgmt;

import com.learning.springtransactionmgmtinterceptordemo.domain.Book;
import com.learning.springtransactionmgmtinterceptordemo.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class TransactionMgmtNext {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void failureInNext(Book book) {
        throw new RuntimeException();        
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void propagation_requiresNew(Book book) {
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void propagation_supports(Book book) {
        book.setName("in propagation supports - stage2");
        bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void propagation_notSupported(Book book) {
        book.setName("in propagation not supported - stage2");
        bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void propagation_never(Book book) {
        book.setName("in propagation not supported - stage2");
        bookRepository.save(book);
    }
}
