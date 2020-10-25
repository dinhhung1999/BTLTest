package com.example.casio.Utils;

public class Caculation {

    public double addition(double a, double b) {
        return  (double) a +b;
    }
    public double minusition(double a, double b) {
        return  (double) a-b;
    }
    public double multiplication(double a, double b) {
        return  (double) a*b;
    }
    public double division(double a, double b) {
        if(!isZero(b)) {
            return (double) (a/b);
        } else  return  0;
    }
    public boolean isZero(double b) {
        return b==0;
    }
}
