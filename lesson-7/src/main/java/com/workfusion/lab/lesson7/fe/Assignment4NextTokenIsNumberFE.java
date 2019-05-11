/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import com.workfusion.lab.lesson7.config.Assignment4ModelConfiguration;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.FeatureName;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;

@FeatureName(Assignment4NextTokenIsNumberFE.FEATURE_NAME)
public class Assignment4NextTokenIsNumberFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "next_token_number";

    @Override
    public Collection<Feature> extract(Document document, T element) {
        Pattern pattern = Pattern.compile("\\d+");
        List<Feature> result = new ArrayList<>();
        List<Element> nextElement = document.findPrevious(Element.class, element, 1);
        if (!nextElement.isEmpty() && pattern.matcher(nextElement.get(0).getText()).matches()) {
            result.add(new Feature(FEATURE_NAME, 1));
        }
        return result;
    }

}