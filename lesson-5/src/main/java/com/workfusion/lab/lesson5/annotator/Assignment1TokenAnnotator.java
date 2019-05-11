/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.annotator;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assignment 1
 */
public class Assignment1TokenAnnotator implements Annotator<Document> {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private static final String TOKEN_REGEXP = "\\w+";

    @Override
    public void process(Document document) {
        Pattern pattern = Pattern.compile(TOKEN_REGEXP);
        Matcher matcher = pattern.matcher(document.getText());

        // Find all matches
        while (matcher.find()) {
            Element.ElementDescriptor elementDescriptor = Token.descriptor();
            elementDescriptor.setBegin(matcher.start());
            elementDescriptor.setEnd(matcher.end());
            document.add(elementDescriptor);
        }
//        new MatcherTokenAnnotator(TOKEN_REGEXP).process(document);
    }

}
