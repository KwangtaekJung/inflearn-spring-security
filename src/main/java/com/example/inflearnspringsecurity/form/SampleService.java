package com.example.inflearnspringsecurity.form;

import com.example.inflearnspringsecurity.account.Account;
import com.example.inflearnspringsecurity.account.AccountContext;
import com.example.inflearnspringsecurity.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

@Service
public class SampleService {

    @Secured("ROLE_USER")
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})  //RoleHierarchy를 만들지 않고 여러 Role을 선언해놓아도 된다.
//    @RolesAllowed("ROLE_USER")
//    @PreAuthorize("hasRole('USER')")
    public void dashboard() {
//        Account account = AccountContext.getAccount();
//        System.out.println("================");
//        System.out.println("account.getUsername() = " + account.getUsername());
        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Object credentials = authentication.getCredentials();
//        boolean authenticated = authentication.isAuthenticated();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("======================");
        System.out.println(authentication);
        System.out.println(userDetails.getUsername());
    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async Service is called.");
    }
}
