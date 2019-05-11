/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.config;

import com.workfusion.lab.lesson7.fe.Assignment2FE;
import com.workfusion.lab.lesson7.fe.Assignment3IsCoveredByNerFE;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;

import java.util.ArrayList;
import java.util.List;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators.
 */
@ModelConfiguration
public class Assignment3ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "[\\w@.,$%’-]+";

    /**
     * Name of {@link Field} representing an invoice number.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Regex pattern to match an invoice number.
     */
    private final static String INVOICE_NUMBER_REGEX = "\\d{10}";

    /**
     * Name of {@link Field} representing an email.
     */
    public final static String FIELD_EMAIL = "email";

    /**
     * Regex pattern to match an email.
     */
    private final static String EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

    @Named("annotators")
    public List<Annotator> annotators(IeConfigurationContext context) {
        List<Annotator> annotators = new ArrayList<>();
        if (FIELD_INVOICE_NUMBER.equals(context.getField().getCode())
                || FIELD_EMAIL.equals(context.getField().getCode())) {
            annotators.add(new MatcherTokenAnnotator(TOKEN_REGEX));
            annotators.add(new EntityBoundaryAnnotator());
            if (FIELD_INVOICE_NUMBER.equals(context.getField().getCode())) {
                annotators.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(FIELD_INVOICE_NUMBER, INVOICE_NUMBER_REGEX));
            } else {
                annotators.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(FIELD_EMAIL, EMAIL_REGEX));
            }
        }
        return annotators;
    }

    @Named("featureExtractors")
    public List<FeatureExtractor> featureExtractors(IeConfigurationContext context) {
        List<FeatureExtractor> featureExtractors = new ArrayList<>();
        if (FIELD_INVOICE_NUMBER.equals(context.getField().getCode())
                || FIELD_EMAIL.equals(context.getField().getCode())) {
            featureExtractors.add(new Assignment3IsCoveredByNerFE(context.getField().getCode()));
        }
//        featureExtractors.add(new Assignment3IsCoveredByNerFE(FIELD_EMAIL));
        return featureExtractors;
    }
}