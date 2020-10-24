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
    }public double division(double a, double b) {
        if(a==0 && b!=0) {
            return 0;
        } else if (b==0) {
            return 0;
        }

        return  (double) a/b;
    }
}
