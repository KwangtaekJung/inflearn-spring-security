package com.example.inflearnspringsecurity.form;

import com.example.inflearnspringsecurity.account.Account;
import com.example.inflearnspringsecurity.account.AccountContext;
import com.example.inflearnspringsecurity.account.AccountRepository;
import com.example.inflearnspringsecurity.book.BookRepository;
import com.example.inflearnspringsecurity.common.CurrentUser;
import com.example.inflearnspringsecurity.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookRepository bookRepository;

//    @GetMapping("/")
//    public String index(Model model, Principal principal) {
//        if (principal == null) {
//            model.addAttribute("message", "Hello Spring Security");
//        } else {
//            model.addAttribute("message", "Hello " + principal.getName());
//        }
//        return "index";
//    }

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account) {
        if (account == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello " + account.getUsername());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello, " + principal.getName());
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();

        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello User, " + principal.getName());
        model.addAttribute("books", bookRepository.findCurrentUserBooks());
        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                SecurityLogger.log("Callable");
                return "Async Handler";
            }
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }
}
