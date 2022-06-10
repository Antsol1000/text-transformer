package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ReverseTextTransformationTest {

    @Test
    void reverseText() {
        //given
        final String text = "text";
        final TextTransformation transformation = new ReverseTextTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("txet", result);
    }

    @Test
    void composeReverseTextTransformationWithOtherTransformation() {
        //given
        final String text = "text text";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("text").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new ReverseTextTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("txet", result);
    }
}