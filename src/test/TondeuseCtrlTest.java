package test;

import controllers.TondeuseCtrl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TondeuseCtrlTest {

    TondeuseCtrl tondeuseCtrl = new TondeuseCtrl();

    /**
     * We check that the performOperations method throws the exceptions we created.
     */
    @Test
    void test1() throws Exception {
        // First exception should be thrown because we want to position a tondeuse on top of other one
        List<String> test1 = Arrays.asList("5 5", "1 2 N", "AAAAA", "1 2 N", "AAAAA");
        assertThrows(Exception.class, () -> tondeuseCtrl.performOperations(test1));
    }

    @Test
    void test2() throws Exception {
        // Second exception should be thrown because we want to perform a not valid operation
        List<String> test2 = Arrays.asList("5 5", "1 2 N", "WAAAA", "3 3 E", "AAAAA");
        assertThrows(Exception.class, () -> tondeuseCtrl.performOperations(test2));
    }

    @Test
    void test3() throws Exception {
        // Third exception should be thrown because there are some lines missing
        List<String> test3 = Arrays.asList("5 5", "1 2 N", "DAAAA", "3 3 E");
        assertThrows(Exception.class, () -> tondeuseCtrl.performOperations(test3));
    }

    @Test
    void test4() throws Exception {
        // Fourth exception should be thrown because we want to position a tondeuse out of bounds
        List<String> test4 = Arrays.asList("5 5", "1 2 N", "DAAAA", "6 3 E", "AAAAAA");
        assertThrows(Exception.class, () -> tondeuseCtrl.performOperations(test4));
    }

    @Test
    void test5() throws Exception {
        // Fifth exception should be thrown because we want to set a not valid orientation
        List<String> test5 = Arrays.asList("5 5", "1 2 Q", "DAAAA", "3 3 E", "AAAAAA");
        assertThrows(Exception.class, () -> tondeuseCtrl.performOperations(test5));
    }
}