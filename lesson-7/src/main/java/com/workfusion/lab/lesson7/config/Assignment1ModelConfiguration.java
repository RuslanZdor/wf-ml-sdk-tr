/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.config;

import com.workfusion.lab.lesson7.fe.Assignment1CoveringByLineFE;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;

import java.util.ArrayList;
import java.util.List;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators.
 */
@ModelConfiguration
public class Assignment1ModelConfiguration {

    private final static String TOKEN_REGEX = "[\\w]+";

    @Named("anntotators")
    public List<Annotator> annotators(IeConfigurationContext context) {
        List<Annotator> annotators = new ArrayList<>();
        annotators.add(new MatcherTokenAnnotator("\\w+"));
        return annotators;
    }

    @Named("featureExtractors")
    public List<FeatureExtractor> featureExtractors(IeConfigurationContext context) {
        List<FeatureExtractor> annotators = new ArrayList<>();
        annotators.add(new Assignment1CoveringByLineFE());
        return annotators;
    }
}