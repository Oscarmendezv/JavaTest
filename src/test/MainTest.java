package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private ByteArrayInputStream testIn;

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testCase1() {
        String testString = "5 5" + "\n1 2 N" + "\nGAGAGAGAA" + "\n3 3 E" + "\nAADAADADDA";
        provideInput(testString);
    }
}