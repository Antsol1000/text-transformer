package pl.put.poznan.transformer.service;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.exceptions.TransformationNotFoundException;
import pl.put.poznan.transformer.transformation.ExpandAcronymsTextTransformation;
import pl.put.poznan.transformer.transformation.IdentityTextTransformation;
import pl.put.poznan.transformer.transformation.ReverseTextTransformation;
import pl.put.poznan.transformer.transformation.TextTransformation;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TextTransformerService {

    private static final Map<String, TextTransformation> TRANSFORMATIONS = Map.of(
            IdentityTextTransformation.NAME, new IdentityTextTransformation(),
            ReverseTextTransformation.NAME, new ReverseTextTransformation(),
            ExpandAcronymsTextTransformation.NAME, new ExpandAcronymsTextTransformation());

    public String transform(final String text, final Collection<String> transformations) {
        final List<TextTransformation> textTransformations =
                transformations.stream().map(
                        x -> Optional.ofNullable(TRANSFORMATIONS.get(x))
                                .orElseThrow(() -> new TransformationNotFoundException(x)))
                        .collect(Collectors.toList());

        return TextTransformation.composeAll(textTransformations).apply(text);
    }
}
