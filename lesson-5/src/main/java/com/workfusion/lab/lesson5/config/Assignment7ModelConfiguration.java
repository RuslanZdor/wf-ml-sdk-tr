/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.workfusion.vds.sdk.api.hypermodel.annotation.Import;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Token;

import static java.util.Collections.singletonList;

/**
 * Assignment 7
 */
@ModelConfiguration
@Import(
        configurations = {
                @Import.Configuration(Assignment5ModelConfiguration.class)
        }
)
public class Assignment7ModelConfiguration {

    @Named("featureNumberExtractors")
    public List<FeatureExtractor<Element>> featureExtractors() {
        return Collections.singletonList(new IsNumberIncludedFE<>());
    }
}


/**
 * The feature extractor to be used in the configuration. For each {@link Token} element in a document, it adds a {@link Feature}
 * with {@code name = FEATURE_NAME} and:
 * <ul>
 *     <li>
 *         {@code value = 1.0} if the token is a number
 *     </li>
 *     <li>
 *         {@code value = 0.0} if the token is NOT a number
 *     </li>
 * </ul>
 */
class IsNumberIncludedFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "isNumberPresent";

    /**
     * Regex pattern to match a number.
     */
    private static final String NUMBER_REGEX = ".*\\d.*";

    @Override
    public Collection<Feature> extract(Document document, T element) {
        String text = element.getText();
        double value = text.matches(NUMBER_REGEX) ? 1.0 : 0.0;
        return singletonList(new Feature(FEATURE_NAME, value));
    }

}
