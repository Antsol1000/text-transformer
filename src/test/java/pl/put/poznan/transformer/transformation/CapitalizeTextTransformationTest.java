package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class CapitalizeTextTransformationTest {

    @Test
    void transformTextToCapitalized() {
        //given
        final String text = "text";
        final TextTransformation transformation = new CapitalizeTextTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("Text", result);
    }

    @Test
    void composeCapitalizeTextTransformationWithOtherTransformation() {
        //given
        final String text = "text text";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("text").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new CapitalizeTextTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("Text", result);
    }
}