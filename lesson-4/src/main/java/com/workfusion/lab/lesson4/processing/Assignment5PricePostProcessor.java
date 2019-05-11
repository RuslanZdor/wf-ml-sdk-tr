/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

/**
 * Assignment 5
 */
public class Assignment5PricePostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a price.
     */
    public static final String FIELD_NAME = "price";

    @Override
    public void process(IeDocument document) {
        OcrAmountNormalizer ocrAmountNormalizer = new OcrAmountNormalizer();
        document.findFields(FIELD_NAME).stream().forEach(fieldInfo -> {

            String value = fieldInfo.getText();
            String correctedValue = value.replaceAll("G", "6")
                    .replaceAll("b", "6")
                    .replaceAll("B", "8")
                    .replaceAll("O", "0")
                    .replaceAll("\\|", "1")
                    .replaceAll("I", "1");
            fieldInfo.setValue(ocrAmountNormalizer.normalize(correctedValue));

            fieldInfo.setValue(fieldInfo.getValue() + " USD");
        });
    }

}