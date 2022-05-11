package pl.put.poznan.transformer.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.exceptions.TransformationNotFoundException;
import pl.put.poznan.transformer.model.TextWithTransformationsDTO;
import pl.put.poznan.transformer.service.TextTransformerService;

import java.util.Arrays;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/transform")
public class TextTransformerController {

    private final TextTransformerService service;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> get(@RequestBody final TextWithTransformationsDTO dto) {
        log.info("Got text to transform: {}", dto.getText());
        log.info("Got transformations to apply : {}", Arrays.toString(dto.getTransformations().toArray()));
        try {
            return ResponseEntity.ok(service.transform(dto.getText(), dto.getTransformations()));
        } catch (final TransformationNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}


