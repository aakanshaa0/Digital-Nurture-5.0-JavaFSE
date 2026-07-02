package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Setter Injection (Ex 1, 2, 5) :");
        BookService setterService = (BookService) context.getBean("bookService");
        setterService.addBook();

        System.out.println("\nConstructor Injection (Ex 7) :");
        BookService constructorService = (BookService) context.getBean("bookServiceConstructor");
        constructorService.addBook();
    }
}