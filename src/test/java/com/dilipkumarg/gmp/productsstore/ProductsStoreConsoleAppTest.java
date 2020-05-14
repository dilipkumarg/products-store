package com.dilipkumarg.gmp.productsstore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductsStoreConsoleAppTest {

    @Test
    public void execute() {
        InputStream stream = this.getClass().getResourceAsStream("/test_input.txt");
        PrintStream printStream = System.out;
        ProductsStoreConsoleApp app = new ProductsStoreConsoleApp();

        app.execute(stream, printStream);

    }
}
