package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ExpandAcronymsTextTransformationTest {

    @Test
    void expandAcronyms() {
        //given
        final String text = "prof.";
        final TextTransformation transformation = new ExpandAcronymsTextTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("professor", result);
    }

    @Test
    void composeExpandAcronymsTextTransformationWithOtherTransformation() {
        //given
        final String text = "text text";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("dr").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new ExpandAcronymsTextTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("doctor", result);
    }
}