package com.fimon.scc.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    void test_0() {
        IdGenerator idGenerator = new IdGenerator(1L, 1L);
        long id = idGenerator.nextId();
        System.out.println(id);
    }

}