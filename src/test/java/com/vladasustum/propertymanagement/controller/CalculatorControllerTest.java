package com.vladasustum.propertymanagement.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {

    @InjectMocks
    private CalculatorController calculatorController;

    static Double num1;
    static Double num2;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all");
        num1 = 3.5;
        num2 = 3.5;
    }

    @BeforeEach
    void init() {
        System.out.println("Before each");

    }

    @AfterEach
    void destroy() {
        System.out.println("After each");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all");
        num1 = null;
        num2 = null;
    }


    @Test
    @DisplayName("Test Addition Success Scenario")
    void testAddFunction_Success() {
        Double result = calculatorController.add(num1, num2);

        assertEquals(7.0, result);
    }

    @Test
    @DisplayName("Test Addition Failure Scenario")
    void testAddFunction_Failure() {
        Double result = calculatorController.add(num1 - 0.5, num2);

        Assertions.assertNotEquals(7.0, result);
    }

    @Test
    @DisplayName("Test Subtraction with num1>num2 Scenario")
    void testSubFunction_num1_gt_num2() {
        Double results = calculatorController.subtract(num1 + 1, num2);
        assertEquals(1.0, results);
    }

    @Test
    @DisplayName("Test Subtraction with num1<num2 Scenario")
    void testSubFunction_num1_lt_num2() {
        Double results = calculatorController.subtract(num1, num2 + 1);
        assertEquals(1.0, results);
    }
}