/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import com.workfusion.vds.sdk.api.nlp.model.Cell;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.model.Line;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

import javax.print.Doc;
import java.util.List;

/**
 * Assignment 7
 */
public class Assignment7ExpandPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a product.
     */
    public static final String FIELD_NAME = "product";

    @Override
    public void process(IeDocument document) {
        document.findFields(FIELD_NAME).stream().forEach(fieldInfo -> {
            List<Line> cells = document.findCovering(Line.class, fieldInfo);
            if (!cells.isEmpty()) {
                Field.Descriptor descriptor = Field.descriptor()
                        .setName(FIELD_NAME)
                        .setValue(cells.get(0).getText())
                        .setBegin(cells.get(0).getBegin())
                        .setScore(fieldInfo.getScore())
                        .setEnd(cells.get(0).getEnd());
                document.remove(fieldInfo);
                document.add(descriptor);
            }
        });
    }

}