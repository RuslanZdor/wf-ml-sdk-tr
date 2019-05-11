/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentStart;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.*;

/**
 * Determines if focus annotation is NER and the last NER in the document
 */
public class NerAbsolutePositionFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "lastnerFeature";

    private Collection<NamedEntity> lastEntity = new ArrayList<>();

    @OnDocumentStart
    public void documentStart(Document document, Class<T> focusElement) {
        Collection<NamedEntity> sentences = document.findAll(NamedEntity.class);

        lastEntity.add((NamedEntity) sentences.toArray()[sentences.size() - 1]);
    }

    /**
     * Determines if focus annotation is NER and the last NER in the document
     * @param document the Document containing the focus
     * @param element the focus being checked for it position in  the covering annotation
     * @return "lastnerFeature" if focus annotation is NER and the last NEM in the document, nothing otherwise
     */
    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();
        Collection<NamedEntity> namedEntities = document.findAll(NamedEntity.class);
        if (lastEntity.stream().filter(namedEntity -> namedEntity.getText().equals(element.getText())).findFirst().isPresent()) {
            result.add(new Feature(FEATURE_NAME, 1));
        }
        return result;
    }

}