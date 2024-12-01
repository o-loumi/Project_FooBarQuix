package com.example.FooBarQuix.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NumberServiceImplTest {

    @InjectMocks
    private NumberServiceImpl numberService;

    @Test
    void transformNumberTest() {
        assertEquals("FOO", numberService.transformNumber(9));
        assertEquals("BAR", numberService.transformNumber(10));
        assertEquals("FOOBARBAR", numberService.transformNumber(15));
        assertEquals("FOO", numberService.transformNumber(13));
        assertEquals("FOOBAR", numberService.transformNumber(51));
        assertEquals("FOOBARQUIX", numberService.transformNumber(57));
        assertEquals("QUIX", numberService.transformNumber(17));
        assertEquals("BARFOOBAR", numberService.transformNumber(35));
        assertEquals("FOOFOOFOO", numberService.transformNumber(33));
        assertEquals("QUIX", numberService.transformNumber(7));
        assertEquals("8", numberService.transformNumber(8));
    }

}