package pl.put.poznan.transformer.service;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.exceptions.TransformationNotFoundException;
import pl.put.poznan.transformer.model.TextWithTransformationsDTO;
import pl.put.poznan.transformer.transformation.*;
import static java.util.Map.entry;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class TextTransformerService {

    private static final Map<String, Function<TextTransformation, TextTransformation>> TRANSFORMATIONS = Map.ofEntries(
            entry(IdentityTextTransformation.NAME, IdentityTextTransformation::new),
            entry(ReverseTextTransformation.NAME, ReverseTextTransformation::new),
            entry(ExpandAcronymsTextTransformation.NAME, ExpandAcronymsTextTransformation::new),
            entry(LowerTextTransformation.NAME, LowerTextTransformation::new),
            entry(UpperTextTransformation.NAME, UpperTextTransformation::new),
            entry(CapitalizeTextTransformation.NAME, CapitalizeTextTransformation::new),
            entry(LatexTextTransformation.NAME, LatexTextTransformation::new),
            entry(NumbersTextTransformation.NAME, NumbersTextTransformation::new),
            entry(ReplaceWithAcronymsTransformation.NAME, ReplaceWithAcronymsTransformation::new),
            entry(RemoveRepetitionsTransformation.NAME, RemoveRepetitionsTransformation::new),
            entry(QuoteTextTransformation.NAME, QuoteTextTransformation::new));

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
