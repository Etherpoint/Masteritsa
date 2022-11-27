package ru.familyproject.ryabov.masteritsa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.familyproject.ryabov.masteritsa.controller.DefaultController;


@SpringBootTest
class MasteritsaApplicationTests {
    @Autowired
    DefaultController controller;

    @Test
    void contextLoads() {
    }

}
