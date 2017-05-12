package io.zipcoder.zealotscasino;

import java.io.InputStream;
import java.util.Scanner;

public class UserInput {

    static Scanner in;
    UserInput(InputStream scannerIn){
        in = new Scanner(scannerIn);
    }
    UserInput(){
        in = new Scanner(System.in);
    }
    public static String getStringInput(String prompt) {
        System.out.println(prompt);
        String userInput = in.nextLine();
        return userInput;
    }
    public static Double getDoubleInput(String prompt) {
        try {
            return Double.parseDouble(getStringInput(prompt)); //needs to take double to two places
        } catch(IllegalArgumentException e){
            return getDoubleInput("Please input a valid number"); // Recursively call the method with a prompt describing the issue.
        }
    }
}
