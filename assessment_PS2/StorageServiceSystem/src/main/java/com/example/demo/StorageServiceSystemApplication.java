package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StorageServiceSystemApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();  
        context.scan("com.example.demo");
        context.refresh();

        System.out.println("Container Loaded");

        StorageService storage = context.getBean(StorageService.class);
        storage.storeFile("cloudFile.txt");

        System.out.println("Getting LocalStorage First Time");
        StorageService local1 = (StorageService) context.getBean("localStorage");
        local1.storeFile("local1.txt");

        System.out.println("Getting LocalStorage Second Time");
        StorageService local2 = (StorageService) context.getBean("localStorage");
        local2.storeFile("local2.txt");

        context.close();
    }
}