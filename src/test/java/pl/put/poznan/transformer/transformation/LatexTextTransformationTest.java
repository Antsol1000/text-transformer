package pl.put.poznan.transformer.transformation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class LatexTextTransformationTest {

    @Test
    void transformSymbolsIntoLatexFormat() {
        //given
        final String text = "text $";
        final TextTransformation transformation = new LatexTextTransformation();

        //when
        final String result = transformation.transform(text);

        //then
        assertEquals("text \\$", result);
    }

    @Test
    void composeUpperTextTransformationWithOtherTransformation() {
        //given
        final String text = "text text &";

        final TextTransformation mockedTransformation = mock(TextTransformation.class);
        doReturn("text &").when(mockedTransformation).transform(eq(text));

        final TextTransformation textTransformation = new LatexTextTransformation(mockedTransformation);

        //when
        final String result = textTransformation.transform(text);

        //then
        assertEquals("text \\&", result);
    }
}