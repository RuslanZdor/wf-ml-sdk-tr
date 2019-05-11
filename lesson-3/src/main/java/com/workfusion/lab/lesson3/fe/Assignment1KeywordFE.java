/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;

/**
 * Assignment 1
 */
public class Assignment1KeywordFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "stateFeature";

    /**
     * Keywords list to use.
     */
    private static final List<String> STATES = Arrays.asList(
            "Missouri",
            "Nevada",
            "Alaska",
            "Hawaii",
            "Texas",
            "Maryland",
            "Vermont",
            "Kentucky",
            "Massachusetts",
            "Pennsylvania",
            "Virginia"
    );

    @Override
    public Collection<Feature> extract(Document document, T element) {

        if (STATES.contains(element.getText())) {
            return Collections.singletonList(new Feature(FEATURE_NAME, 1));
        }

        return Collections.emptyList();
    }

}