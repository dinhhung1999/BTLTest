package com.example.casio;

import com.example.casio.login.Valilator;

import org.junit.Assert;
import org.junit.Test;
public class ValilatorTest {
    Valilator valilator = new Valilator();

    @Test
    public void hasSpace_C1C2_1(){
        String s = "adsa  dsads";
        boolean actual = valilator.hasSpace(s);
        Assert.assertTrue(actual);
    }
    @Test
    public void hasSpace_C1C2_2(){
        String s = "adsadsads";
        boolean actual = valilator.hasSpace(s);
        Assert.assertFalse(actual);

    }
    @Test
    public void hasLength_C1C2_1(){
        String s = "123456";
        boolean actual = valilator.hasLength(s);
        Assert.assertTrue(actual);
    }
    @Test
    public void hasLength_C1C2_2(){
        String s = "123";
        boolean actual = valilator.hasLength(s);
        Assert.assertFalse(actual);

    }
    @Test
    public void hasSymbol_C1C2_1(){
    String s = "*";
    boolean actual = valilator.hasSymbol(s);
    Assert.assertTrue(actual);
    }

    @Test
    public void hasSymbol_C1C2_2(){
    String s = "s";
    boolean actual = valilator.hasSymbol(s);
    Assert.assertFalse(actual);
    }
    @Test
    public void hasUpperCase_C1C2_1(){
        String s = "Aaa";
        boolean actual = valilator.hasUpperCase(s);
        Assert.assertTrue(actual);
    }
    @Test
    public void hasUpperCase_C1C2_2(){
        String s = "aaa";
        boolean actual = valilator.hasUpperCase(s);
        Assert.assertFalse(actual);

    }
    @Test
    public void hasLowerCase_C1C2_1(){
        String s = "AAa";
        boolean actual = valilator.hasLowerCase(s);
        Assert.assertTrue(actual);
    }
    @Test
    public void hasLowerCase_C1C2_2(){
        String s = "AAA";
        boolean actual = valilator.hasLowerCase(s);
        Assert.assertFalse(actual);
    }
    @Test
    public void hasNumber_C1C2_1(){
        String s = "abc*1";
        boolean actual = valilator.hasNumber(s);
        Assert.assertTrue(actual);
    }
    @Test
    public void hasNumber_C1C2_2(){
        String s = "abc*a";
        boolean actual = valilator.hasNumber(s);
        Assert.assertFalse(actual);
    }
    @Test
    public void isValidEmail_C1C2_1(){
        String s = "hungco381@gmail.com";
        boolean actual = valilator.isValidEmail(s);
        Assert.assertTrue(actual);
    }
    @Test
    public void isValidEmail_C1C2_2(){
        String s = "hungco381@gmail";
        boolean actual = valilator.isValidEmail(s);
        Assert.assertFalse(actual);
    }
    @Test
    public void validatePhoneNumber_C1C2_1(){
        String s = "0912345678";
        boolean actual = valilator.validatePhoneNumber(s);
        Assert.assertTrue(actual);
    }
    @Test
    public void validatePhoneNumber_C1C2_2(){
        String s = "091234568799";
        boolean actual = valilator.validatePhoneNumber(s);
        Assert.assertFalse(actual);
    }
}
