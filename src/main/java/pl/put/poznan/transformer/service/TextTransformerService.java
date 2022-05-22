package pl.put.poznan.transformer.service;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.exceptions.TransformationNotFoundException;
import pl.put.poznan.transformer.model.TextWithTransformationsDTO;
import pl.put.poznan.transformer.transformation.*;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class TextTransformerService {

    private static final Map<String, Function<TextTransformation, TextTransformation>> TRANSFORMATIONS = Map.of(
            IdentityTextTransformation.NAME, IdentityTextTransformation::new,
            ReverseTextTransformation.NAME, ReverseTextTransformation::new,
            ExpandAcronymsTextTransformation.NAME, ExpandAcronymsTextTransformation::new,
            LowerTextTransformation.NAME, LowerTextTransformation::new,
            UpperTextTransformation.NAME, UpperTextTransformation::new,
            CapitalizeTextTransformation.NAME, CapitalizeTextTransformation::new,
            LatexTextTransformation.NAME, LatexTextTransformation::new,
            NumbersTextTransformation.NAME, NumbersTextTransformation::new);

    public String transform(final TextWithTransformationsDTO dto) {
        final String text = dto.getText();
        final Collection<String> transformations = dto.getTransformations();
        TextTransformation transformation = new IdentityTextTransformation();
        for (final String transformationName : transformations) {
            final var transformationSupplier =
                    TRANSFORMATIONS.get(transformationName);
            if (transformationSupplier == null) {
                throw new TransformationNotFoundException(transformationName);
            }
            transformation = transformationSupplier.apply(transformation);
        }
        return transformation.transform(text);
    }
}
