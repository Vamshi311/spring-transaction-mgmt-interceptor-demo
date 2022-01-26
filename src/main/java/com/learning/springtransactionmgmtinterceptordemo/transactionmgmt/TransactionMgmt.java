package com.learning.springtransactionmgmtinterceptordemo.transactionmgmt;

import com.learning.springtransactionmgmtinterceptordemo.domain.Book;
import com.learning.springtransactionmgmtinterceptordemo.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
   Transaction properties:
   1. ISOLATION
   2. PROPAGARION - REQUIRED, REQUIRES_NEW, MANDATORY, SUPPORTS, NOT_SUPPORTED, NEVER
   3. timeout
   4. readOnly
   5. rollback rules - rollbackFor, noRollbackFor
   Note: MANDATORY is opposite to NEVER
*/

@Component
@Slf4j
public class TransactionMgmt {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private TransactionMgmtNext transactionMgmtNext;
    
    /*
    exception occurs in next and new book name won't get saved in db
    */
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_required(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setName("in propagation required - stage1");
        try{
            transactionMgmtNext.failureInNext(book);
        }catch (Exception ex) {
            System.out.println("exception caught");
        }
        bookRepository.save(book);
    }

    /*
    TransactionMgmt.propagation_requiresNew call runs in one transaction session and TransactionMgmtNext.propagation_requiresNew in another transaction session.
    so value gets updated in this case.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void propagation_requiresNew(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setName("in propagation required - stage1");
        try {
            transactionMgmtNext.propagation_requiresNew(book);
        }catch (Exception ex) {
            System.out.println("exception caught");
        }
        
        bookRepository.save(book);
    }

    /*
    requires active transaction to be running. as it is not in active transaction for a call from service, it should throw an exception.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void propagation_mandatory(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setName("in propagation required - stage1");
        bookRepository.save(book);
    }

    /*
    If active transaction exists then it participates else run without errors.
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void propagation_supports(Long id) {
        Book book = bookRepository.findById(id).get();
        transactionMgmtNext.propagation_supports(book);
        book.setName("in propagation required - stage1");
        bookRepository.save(book);
    }

    /*
    NOT_SUPPORTED - Do not support a current transaction; rather always execute non-transactionally. 
    So below transaction suspended until "transactionMgmtNext.propagation_notSupported(book);" executed in a non transaction way.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_notSupported(Long id) {
        Book book = bookRepository.findById(id).get();
        transactionMgmtNext.propagation_notSupported(book);
        book.setName("in propagation required - stage1");
        bookRepository.save(book);
    }

    /*
    this method should throw exception as transactionMgmtNext.propagation_never(book) does not requires transaction but parent method has ongoing transaction.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void propagation_never(Long id) {
        Book book = bookRepository.findById(id).get();
        book.setName("in propagation required - stage1");
        transactionMgmtNext.propagation_never(book);
        bookRepository.save(book);
    }
}
