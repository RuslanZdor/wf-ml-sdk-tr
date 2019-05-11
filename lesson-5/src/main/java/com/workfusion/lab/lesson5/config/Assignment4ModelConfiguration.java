/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.AhoCorasickDictionaryNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.dictionary.CsvDictionaryKeywordProvider;
import com.workfusion.vds.sdk.nlp.component.dictionary.CsvDictionaryProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 4
 */
@ModelConfiguration
public class Assignment4ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "\\W+";

    /**
     * Type for {@link NamedEntity} to use.
     */
    private final static String NER_TYPE = "country";

    @Named("annotators")
    public List<Annotator> annotators(IeConfigurationContext context) {
        List<Annotator> annotators = new ArrayList<>();
        annotators.add(new SplitterTokenAnnotator(TOKEN_REGEX));
        annotators.add(new EntityBoundaryAnnotator());
        annotators.add(new AhoCorasickDictionaryNerAnnotator(NER_TYPE, new CsvDictionaryKeywordProvider(context.getResource("classpath:dictionary/countries.csv"))));
        return annotators;
    }
}
