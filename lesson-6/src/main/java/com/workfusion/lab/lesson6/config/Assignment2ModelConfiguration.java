/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson6.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.FieldType;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.AhoCorasickDictionaryNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.dictionary.CsvDictionaryKeywordProvider;

/**
 * Assignment 2
 */
@ModelConfiguration
public class Assignment2ModelConfiguration {

    @Named("featureExtractors")
    public List<FeatureExtractor> featureExtractors(IeConfigurationContext context) {
        List<FeatureExtractor> annotators = new ArrayList<>();
        if (context.getField().getType() == FieldType.FREE_TEXT) {
            annotators.add(new IsAllLettersUpperCase());
        }

        if (context.getField().getType() == FieldType.INVOICE_TYPE) {
            annotators.add(new IsOnlyNumberInTokenFE());
        }
        return annotators;
    }
    public static class IsAllLettersUpperCase<T extends Element> implements FeatureExtractor<T> {

        private static final String FEATURE_NAME = "IsUpperCaseLettersOnly";

        @Override
        public Collection<Feature> extract(Document document, T element) {
            String text = element.getText();
            if (text.matches("[A-Z]+")) {
                // if text contains only upper case letters, return a feature
                return Collections.singletonList(new Feature(FEATURE_NAME, 1));
            }
            // otherwise return empty list
            return Collections.emptyList();
        }

    }

    public static class IsOnlyNumberInTokenFE<T extends Element> implements FeatureExtractor<T> {

        private static final String FEATURE_NAME = "IsNumbersOnly";

        @Override
        public Collection<Feature> extract(Document document, T element) {
            String text = element.getText();
            if (text.matches("\\d+")) {
                // if text contains only digits, return a feature
                return Collections.singletonList(new Feature(FEATURE_NAME, 1));
            }
            // otherwise return empty list
            return Collections.emptyList();
        }
    }

}