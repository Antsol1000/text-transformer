package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ReplaceWithAcronymsTransformationTest {

    @Test
    void replaceWithAcronyms() {
        //given
        final String text = "for example";
        final TextTransformation transformation = new ReplaceWithAcronymsTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("e.g.", result);
    }

    @Test
    void composeReplaceWithAcronymsTextTransformationWithOtherTransformation() {
        //given
        final String text = "for example text";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("for example").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new ReplaceWithAcronymsTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("e.g.", result);
    }
}