package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class LowerTextTransformationTest {

    @Test
    void transformTextToLowerCase() {
        //given
        final String text = "TexT";
        final TextTransformation transformation = new LowerTextTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("text", result);
    }

    @Test
    void composeUpperTextTransformationWithOtherTransformation() {
        //given
        final String text = "Text Text";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("Text").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new LowerTextTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("text", result);
    }
}