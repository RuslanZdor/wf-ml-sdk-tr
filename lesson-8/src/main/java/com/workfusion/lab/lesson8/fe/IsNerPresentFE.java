/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.FeatureName;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

/**
 * Assignment 1
 */
@FeatureName(IsNerPresentFE.FEATURE_NAME)
public class IsNerPresentFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public final static String FEATURE_NAME = "nerFeature";

    /**
     * The Ner type to look for.
     */
    private final String nerType;

    /**
     * Create an instance of {@link FeatureExtractor} that detects if a token is inside the {@link NamedEntity} of the specified {@code type}.
     * @param type  type of {@link NamedEntity}
     */
    public IsNerPresentFE(String type) {
        this.nerType = type;
    }

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();

        List<NamedEntity> namedEntities = document.findCovering(NamedEntity.class, element);
        if (!namedEntities.isEmpty())  {
            result.add(new Feature(FEATURE_NAME, 1));
        }

        return result;
    }

}