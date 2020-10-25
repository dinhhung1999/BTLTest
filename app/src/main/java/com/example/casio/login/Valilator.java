package com.example.casio.login;

import java.util.regex.Pattern;

public class Valilator {
    public boolean hasSpace(String password){
        for(int i=0;i<password.length();i++){
            if (password.charAt(i) == ' ') {
                return true;
            }
        }
        return false;
    }
    public boolean hasLength(String password){
        if(password.length()>=6)
            return true;
        else
            return false;
    }
    public boolean hasSymbol(String password){
        if (password.matches("[A-Za-z0-9]*"))
            return false;
        else return true;
    }
    public boolean hasUpperCase(String password){
        if (password.equals(password.toLowerCase()))
            return false;
        else return true;

    }
    public boolean hasLowerCase(String password){
        if (password.equals(password.toUpperCase()))
            return false;
        else return true;

    }
    public boolean hasNumber(String password){
        String regex = "(.)*(\\d)(.)*";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(password).matches())
            return true;
        else return false;
    }

    public boolean isValidEmail(String email) {
        String emailPattern = "\\w+@\\w+[.]\\w+";
        Pattern pattern = Pattern.compile(emailPattern);
        if (pattern.matcher(email).matches())
            return true;
        else return false;
    }

    public static boolean validatePhoneNumber(String phoneNo) {
        String phoneNoPattern = "(\\+84|0)[0-9]{9,10}";
        Pattern pattern = Pattern.compile(phoneNoPattern);
        if (pattern.matcher(phoneNo).matches())
            return true;
        else return false;

    }
}
