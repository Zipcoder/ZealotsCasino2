package io.zipcoder.zealotscasino;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by andrewwong on 5/9/17.
 */
public class TestNumberValue {
    @Test
    public void numberValue_GetNumericValue_ReturnsInt() {
        // Given
        // CardValue is a 7

        // When
        int check = 7;

        // Then
        assertEquals("Checking King's number value", check, NumberValue.SEVEN.cardVal());
    }
}
