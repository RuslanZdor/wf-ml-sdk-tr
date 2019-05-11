/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.lab.lesson8.fe.IsNerPresentFE;
import com.workfusion.lab.lesson8.fe.SimilarityKeysInPrevLineFE;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;

import javax.swing.text.html.parser.Entity;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators.
 */
@ModelConfiguration
public class Assignment2ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "([$\\$\\s:#_;'])";

    /**
     * Name of {@link Field} representing a client_address.
     */
    public final static String FIELD_STATE = "client_address_state";

    /**
     * Regex pattern to match a state.
     */
    private final static String STATE_REGEX = "[A-Z]{2}";

    /**
     * Type for {@link NamedEntity} to use for state NER.
     */
    private static final String NER_TYPE_STATE = "state";

    /**
     * Keyword that needs be found in previous line
     */
    private static final String KEYWORD_SIMILARITY = "address";

    @Named("annotators")
    public List<Annotator<Document>> getAnnotators(IeConfigurationContext context) {
        List<Annotator<Document>> annotators = new ArrayList<>();

        annotators.add(new SplitterTokenAnnotator(TOKEN_REGEX));
        annotators.add(new EntityBoundaryAnnotator());
        annotators.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(NER_TYPE_STATE, STATE_REGEX));

        return annotators;
    }

    @Named("featureExtractors")
    public List<FeatureExtractor<Element>> getFeatureExtractors(IeConfigurationContext context) {
        List<FeatureExtractor<Element>> featuresExtractors = new ArrayList<>();

        featuresExtractors.add(new SimilarityKeysInPrevLineFE<Element>(KEYWORD_SIMILARITY));

//        if (context.getField().getCode().equals(NER_TYPE_STATE)) {
            featuresExtractors.add(new IsNerPresentFE<>(NER_TYPE_STATE));
//        }

        return featuresExtractors;
    }

}