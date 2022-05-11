package pl.put.poznan.transformer.model;

import lombok.Value;

import java.util.Collection;

@Value
public class TextWithTransformationsDTO {
    String text;
    Collection<String> transformations;
}
