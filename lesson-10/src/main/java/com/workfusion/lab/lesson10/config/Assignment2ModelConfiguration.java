/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson10.config;

import com.workfusion.lab.lesson10.fe.ColumnIndexFE;
import com.workfusion.lab.lesson10.fe.RowIndexFE;
import com.workfusion.lab.lesson10.fe.TableNumberFE;
import com.workfusion.vds.nlp.hypermodel.ie.generic.config.GenericIeHypermodelConfiguration;
import com.workfusion.vds.sdk.api.hpo.ParameterSpace;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Filter;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Import;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;

import java.util.Collections;
import java.util.List;

/**
 * The model configuration class.
 */
@ModelConfiguration
@Import(
        configurations = {
                @Import.Configuration(GenericIeHypermodelConfiguration.class)
        },
        resources = {
                @Import.Resource(value="results_assignment1/training/output/model/invoice_number/config/parameters.json",
                        condition = @Filter(expression = "field.code eq 'invoice_number'"))
        }
)
public class Assignment2ModelConfiguration {

    /**
     * Name of {@link Field} representing an invoice number.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Name of {@link Field} representing a product.
     */
    public final static String FIELD_PRODUCT = "product";

    @Named("parameterSpace")
    public ParameterSpace configure(IeConfigurationContext context) {
        ParameterSpace.Builder builder = new ParameterSpace.Builder();

        context.getField().getCode().equals(FIELD_PRODUCT);

        return builder.build();
    }

    @Named("ColumnIndexFE")
    public List<FeatureExtractor> columnIndexFE() {
        return Collections.singletonList(new ColumnIndexFE());
    }

    @Named("RowIndexFE")
    public List<FeatureExtractor> rowIndexFE() {
        return Collections.singletonList(new RowIndexFE());
    }

    @Named("tableIndexFE")
    public List<FeatureExtractor> tableIndexFE() {
        return Collections.singletonList(new TableNumberFE());
    }
}