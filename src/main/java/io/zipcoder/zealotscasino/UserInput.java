package io.zipcoder.zealotscasino;

import java.util.Scanner;

public class UserInput {

    public static String getStringInput(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = in.nextLine();
        return userInput;
    }
    public static Double getDoubleInput(String prompt) {
        try {
            return Double.parseDouble(getStringInput(prompt)); //needs to take double to two places
        } catch(Exception e){
            return getDoubleInput("Please input a valid number"); // Recursively call the method with a prompt describing the issue.
        }
    }
}
