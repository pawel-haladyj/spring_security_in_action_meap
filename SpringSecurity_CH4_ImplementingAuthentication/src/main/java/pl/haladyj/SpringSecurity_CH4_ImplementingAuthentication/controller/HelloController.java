package pl.haladyj.SpringSecurity_CH4_ImplementingAuthentication.controller;

import org.apache.catalina.Executor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();

        return "Hello, " + a.getName()+"!";
    }

    @GetMapping("/bye")
    @Async
    public String bye(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        return "Bye, " + a.getName()+"!";
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception{
        Callable<String> task = ()->{
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = Executors.newCachedThreadPool();
        try{
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "ciao, " + e.submit(contextTask).get()+ "!";
        } finally {
            e.shutdown();
        }
    }
}
