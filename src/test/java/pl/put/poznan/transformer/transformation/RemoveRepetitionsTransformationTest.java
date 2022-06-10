package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class RemoveRepetitionsTransformationTest {

    @Test
    void removeRepetitionsFromText() {
        //given
        final String text = "text text";
        final TextTransformation transformation = new RemoveRepetitionsTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("text", result);
    }

    @Test
    void composeRemoveRepetitionsTextTransformationWithOtherTransformation() {
        //given
        final String text = "t t t----";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("t t t").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new RemoveRepetitionsTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("t", result);
    }
}