package com.zxz.littledemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println(String.format("y1: %4.1f,\t y2: %4.1f,\t shift: %4.1f,\t ", 1.1f,2.1f,113.1f));
    }

    private long cTime() {
        return System.currentTimeMillis();
    }
}