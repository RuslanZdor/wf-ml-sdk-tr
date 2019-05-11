/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson2.annotator;

import java.util.Arrays;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;

/**
 * Assignment 1
 */
public class Assignment1KeywordNerAnnotator implements Annotator<Document> {

    /**
     * Keywords list to use.
     */
    private static final List<String> STATES = Arrays.asList(
            "Missouri",
            "Nevada",
            "Alaska",
            "Hawaii",
            "Texas",
            "Maryland",
            "Vermont"
    );

    /**
     * Type for {@link NamedEntity} to use.
     */
    private final static String NER_TYPE = "state";

    @Override
    public void process(Document document) {
        for (Token token : document.findAll(Token.class)) {
            if (STATES.contains(document.getText().substring(token.getBegin(), token.getEnd()))) {
                NamedEntity.Descriptor elementDescriptor = NamedEntity.descriptor();
                elementDescriptor.setBegin(token.getBegin());
                elementDescriptor.setEnd(token.getEnd());
                elementDescriptor.setType(NER_TYPE);
                document.add(elementDescriptor);
            }
        }
        //TODO: PUT YOUR CODE HERE

    }

}