/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.*;

/**
 * Assignment 4
 */
public class Assignment4FirstInSentenceFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "firstInSentenceFeature";

    @Override
    public Collection<Feature> extract(Document document, T element) {

        if (!document.findCovering(Sentence.class, element).isEmpty()) {
            Sentence sentence = document.findCovering(Sentence.class, element).get(0);
            if (sentence.getBegin() == element.getBegin()) {
                List<Feature> features = new ArrayList<>();
                features.add(new Feature(FEATURE_NAME, 1));
                return features;
            }
        }

        return Collections.emptyList();
    }

}