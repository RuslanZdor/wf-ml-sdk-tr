/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Line;
import com.workfusion.vds.sdk.api.nlp.model.Token;

/**
 * Gets similarity of focus annotation to provided keyword
 */
public class KeywordsPreviousLineFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "keywordFeature";

    private String keyword;

    public KeywordsPreviousLineFE(String keyword) {
        this.keyword = keyword.toLowerCase();

    }

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();

        List<Line> lines = document.findPrevious(Line.class, element, 1);
        if (!lines.isEmpty() && keyword.equalsIgnoreCase(lines.get(0).getText())) {
            result.add(new Feature(FEATURE_NAME, 1));
        }

        return result;
    }

}