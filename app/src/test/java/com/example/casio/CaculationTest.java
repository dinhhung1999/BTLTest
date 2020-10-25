package com.example.casio;

import com.example.casio.Utils.Caculation;

import org.junit.Assert;
import org.junit.Test;


public class CaculationTest {
    Caculation caculation = new Caculation();
    @Test
    public void addition_C1C2() throws Exception {
        double a = 2;
        double b = 2;
        double actual = caculation.addition(a,b);
        double expected = 4;
        Assert.assertEquals(expected,actual ,0);
    }

    @Test
    public void minusion_C1C2() throws Throwable {
        double a = 6;
        double b = 2;
        double actual = caculation.minusition(a,b);
        double expected = 4;
        Assert.assertEquals(expected,actual ,0);
    }
    @Test
    public void multiplication_C1C2() throws Throwable {
        double a = 4;
        double b = 1;
        double actual = caculation.multiplication(a,b);
        double expected = 4;
        Assert.assertEquals(expected,actual ,0);
    }

    @Test
    public void division_C1C2_1() throws Throwable {
        double a = 8;
        double b = 2;
        double actual = caculation.division(a,b);
        double expected = 4;
        Assert.assertEquals(expected,actual ,0);
    }
     @Test
    public void division_C1C2_2() throws Throwable {
        double a = 8;
        double b = 0;
        double actual = caculation.division(a,b);
        double expected = 0;
        Assert.assertEquals(expected,actual ,0);
    }

    @Test
    public void isZero_C1C2() throws  Throwable {
        double expected = 0;
        Assert.assertTrue(caculation.isZero(expected));

    }
}
