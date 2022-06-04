package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class NumbersTextTransformationTest {

    @Test
    void changeNumbersToWords() {
        //given
        final String text = "15";
        final TextTransformation transformation = new NumbersTextTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("fifteen", result);
    }

    @Test
    void composeNumbersTextTransformationWithOtherTransformation() {
        //given
        final String text = "text 99";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("99").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new NumbersTextTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("ninety nine", result);
    }
}