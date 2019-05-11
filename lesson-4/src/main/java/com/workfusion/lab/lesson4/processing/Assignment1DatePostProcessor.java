/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Assignment 1
 */
public class Assignment1DatePostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a date.
     */
    public static final String FIELD_NAME = "date";

    /**
     * A format to which a date needs to be converted in the output.
     */
    private static final String OUTPUT_DATE_FORMAT = "MM/dd/yy";
    private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat(OUTPUT_DATE_FORMAT);

    private static final String DOCUMENT_DATE_FORMAT = "MMMM dd, yyyy";
    private static final SimpleDateFormat DOCUMENT_FORMAT = new SimpleDateFormat(DOCUMENT_DATE_FORMAT);

    @Override
    public void process(IeDocument document) {
        document.findFields(FIELD_NAME).stream().forEach(fieldInfo -> {
            try {
                fieldInfo.setValue(OUTPUT_FORMAT.format(DOCUMENT_FORMAT.parse(fieldInfo.getText())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

}