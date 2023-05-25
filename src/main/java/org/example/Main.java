package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
           File file = new File("OnlineServices-1.0-SNAPSHOT.jar");
           String absolutePath = file.getAbsolutePath();
           System.out.println(absolutePath);
    }
}