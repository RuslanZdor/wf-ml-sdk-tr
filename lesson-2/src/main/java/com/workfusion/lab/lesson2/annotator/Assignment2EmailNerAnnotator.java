/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson2.annotator;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assignment 2
 */
public class Assignment2EmailNerAnnotator implements Annotator<Document> {

    /**
     * Regex to match an email.
     */
    private static final String EMAIL_REGEXP = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";

    /**
     * Type for {@link NamedEntity} to use.
     */
    private static final String NER_TYPE = "email";

    @Override
    public void process(Document document) {
        Pattern pattern = Pattern.compile(EMAIL_REGEXP);
        for (Token token : document.findAll(Token.class)) {
            if (pattern.matcher(document.getText().substring(token.getBegin(), token.getEnd())).find()) {
                NamedEntity.Descriptor elementDescriptor = NamedEntity.descriptor();
                elementDescriptor.setBegin(token.getBegin());
                elementDescriptor.setEnd(token.getEnd());
                elementDescriptor.setType(NER_TYPE);
                document.add(elementDescriptor);
            }
        }
    }

}