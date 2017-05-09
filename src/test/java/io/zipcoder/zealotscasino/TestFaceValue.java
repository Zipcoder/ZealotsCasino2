package io.zipcoder.zealotscasino;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by denniskalaygian on 5/9/17.
 */
public class TestFaceValue {
    @Test
    public void cardValue_GetNumericValue_ReturnsInt() {
        // Given
        // FaceValue enum KING

        // When
        int check = 10;

        // Then
        assertEquals("Checking King's number value", check, FaceValue.KING.cardVal());
    }
}
