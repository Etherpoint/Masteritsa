package ru.familyproject.ryabov.masteritsa.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EndpointsTest {
    @ParameterizedTest
    @CsvSource(delimiter = '=', value = {
            Endpoints.MAIN_PAGE + '=' + "/",
            Endpoints.PRODUCT + '=' + "/product",
            Endpoints.FILTER + '=' + "/products",
            Endpoints.FILTER_ALL + '=' + "/all",
            Endpoints.FIND_BY_ID + '=' + "/{id}",
            Endpoints.ABOUT + '=' + "/about",
            Endpoints.SALES + '=' + "/sales",
            Endpoints.REGISTRATION + '=' + "/registration",
            Endpoints.ACCOUNT + '=' + "/account"
    })
    void checkAllEndpointsFrom_EndpointsClass(String expected, String actual) {
        assertEquals(expected, actual);
    }
}