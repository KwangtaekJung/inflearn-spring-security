package com.example.inflearnspringsecurity.form;

import com.example.inflearnspringsecurity.account.Account;
import com.example.inflearnspringsecurity.account.AccountContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {
    public void dashboard() {
        Account account = AccountContext.getAccount();
        System.out.println("================");
        System.out.println("account.getUsername() = " + account.getUsername());
        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Object credentials = authentication.getCredentials();
//        boolean authenticated = authentication.isAuthenticated();
    }
}