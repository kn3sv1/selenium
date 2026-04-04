package main;

import test.repository.MenuRepositoryTest;

import java.io.IOException;

public class Main {
    public static void main2(String[] args) throws IOException {
        Server server = new Server(8080);
        server.start();
    }


    public static void main(String[] args) {
        MenuRepositoryTest test = new MenuRepositoryTest();
        //test.testAddMethod();
        //test.testCleanMethod();
        //test.testDeleteById();
        test.testUpdate();
    }
}
