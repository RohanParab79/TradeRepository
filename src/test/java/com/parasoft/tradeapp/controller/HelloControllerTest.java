package com.parasoft.tradeapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloControllerTest {
    @Autowired
    private HelloController helloController;

    @Test
    void helloWorldTest() {
        assertEquals("<h2> Hello World</h2>",helloController.helloWorld());
    }
}