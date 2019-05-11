/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentComplete;
import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentStart;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import org.apache.commons.collections.ArrayStack;

/**
 * Assignment 5
 */
public class Assignment5FirstInSentenceFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "firstInSentenceFeature";

    /**
     * The method is called once in the beginning of document processing.
     *
     * @param document  a document is being processed.
     * @param focusElement  a type of {@link Element} the feature extractor will accept in the {@code extract()} method.
     */

    List<Token> firstWords = new ArrayList<>();

    @OnDocumentStart
    public void documentStart(Document document, Class<T> focusElement) {
        Collection<Sentence> sentences = document.findAll(Sentence.class);
        List<Integer> sentenceBegin = sentences.stream().map(sentence -> {
            return sentence.getBegin();
        }).collect(Collectors.toList());
        firstWords = document.findAll(Token.class).stream().filter(token -> {
            return sentenceBegin.contains(token.getBegin());
        }).collect(Collectors.toList());
    }

    @Override
    public Collection<Feature> extract(Document document, T element) {
        if (firstWords.contains(element)) {
            List<Feature> features = new ArrayList<>();
            features.add(new Feature(FEATURE_NAME, 1));
            return features;
        }

        return Collections.emptyList();
    }

    /**
     * The method is called once in the end of document processing. The main purpose of the method is to release all resources allocated
     * while the feature extractor was working.
     */
    @OnDocumentComplete
    public void documentComplete() {
        firstWords.clear();
    }

}