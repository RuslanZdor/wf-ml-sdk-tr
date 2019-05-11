/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

/**
 * Assignment 3
 */
public class Assignment3IsCoveredByNerFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Type of {@link NamedEntity}.
     */
    private String type;

    /**
     * Create an instance of {@link FeatureExtractor} that detects if a token is inside the {@link NamedEntity} of the specified {@code type}.
     * @param type type of {@link NamedEntity}
     */
    public Assignment3IsCoveredByNerFE(String fieldName) {
        this.type = fieldName;
    }

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();

        if (!document.findCovering(NamedEntity.class, element).isEmpty()) {
            result.add(new Feature(type, 1));
        }

        return result;
    }

}