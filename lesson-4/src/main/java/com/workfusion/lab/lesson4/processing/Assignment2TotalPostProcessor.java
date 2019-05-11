/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

import java.text.ParseException;

/**
 * Assignment 2
 */
public class Assignment2TotalPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a total.
     */
    public static final String FIELD_NAME = "total";

    @Override
    public void process(IeDocument document) {
        OcrAmountNormalizer ocrAmountNormalizer = new OcrAmountNormalizer();
        document.findFields(FIELD_NAME).stream().forEach(fieldInfo -> {
            boolean hasSymbol = fieldInfo.getText().contains("$");
            fieldInfo.setValue(ocrAmountNormalizer.normalize(fieldInfo.getText()));
            if (hasSymbol) {
                fieldInfo.setValue(fieldInfo.getValue() + " USD");
            }
        });
    }

}