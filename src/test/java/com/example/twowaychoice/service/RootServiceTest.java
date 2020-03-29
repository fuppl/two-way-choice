package com.example.twowaychoice.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.validation.annotation.Validated;

@SpringBootTest
@Slf4j
@Rollback(value = false)
public class RootServiceTest {
    @Autowired
    private RootService rootService;

    @Test
    public void test_reset() {
        rootService.resetPassword(1);
    }
}
