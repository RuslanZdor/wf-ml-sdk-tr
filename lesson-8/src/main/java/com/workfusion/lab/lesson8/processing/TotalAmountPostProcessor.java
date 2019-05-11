/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.processing;

import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

public class TotalAmountPostProcessor implements Processor<IeDocument> {

    private static final String REGEX_TOTAL_AMOUNT_WRONG_CHARS  = "[,($]";

    private static final String NER_TYPE_TOTAL_AMOUNT = "total_amount";

    @Override
    public void process(IeDocument document) {
        document.findFields(NER_TYPE_TOTAL_AMOUNT).stream().forEach(fieldInfo -> {
                fieldInfo.setValue(fieldInfo.getValue().replaceAll(REGEX_TOTAL_AMOUNT_WRONG_CHARS, "") + " USD");
        });
    }

}