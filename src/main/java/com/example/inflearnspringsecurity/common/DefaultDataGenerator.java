package com.example.inflearnspringsecurity.common;

import com.example.inflearnspringsecurity.account.Account;
import com.example.inflearnspringsecurity.account.AccountService;
import com.example.inflearnspringsecurity.book.Book;
import com.example.inflearnspringsecurity.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account keesun = createUser("keesun");
        Account whiteship = createUser("whiteship");

        createBook(keesun, "spring");
        createBook(whiteship, "whiteship");
    }

    private void createBook(Account keesun, String title) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(keesun);
        bookRepository.save(book);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("123");
        account.setRole("USER");
        return accountService.createNew(account);
    }
}
