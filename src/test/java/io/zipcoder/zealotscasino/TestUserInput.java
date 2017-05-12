package io.zipcoder.zealotscasino;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by andrewwong on 5/12/17.
 */
public class TestUserInput {

    @Test
    public void getStringInput_UserEntersAString_StringIsReturned(){
        //Given
        String stringInput = "test";

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(stringInput.getBytes());
        UserInput userInput = new UserInput(byteArrayInputStream);

        //When
        String actualOutput = userInput.getStringInput("This is a test for strings");

        //Then
        assertEquals(stringInput, actualOutput);
    }

    @Test
    public void getDoubleInput_UserEntersAString_StringIsReturned(){
        //Given
        String doubleInput = "10";
        double expected = 10;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(doubleInput.getBytes());
        UserInput userInput = new UserInput(byteArrayInputStream);

        //When
        double actualOutput = userInput.getDoubleInput("This is a test for doubles");

        //Then
        assertEquals(expected, actualOutput, 0);
    }
}
