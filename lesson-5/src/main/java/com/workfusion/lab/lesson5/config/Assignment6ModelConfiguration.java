/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import com.workfusion.vds.sdk.api.hypermodel.annotation.Import;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.workfusion.lab.lesson5.config.Assignment5ModelConfiguration.NER_TYPE;

/**
 * Assignment 6
 */
@ModelConfiguration
@Import(
        configurations = {
                @Import.Configuration(Assignment5ModelConfiguration.class)
        }
)
public class Assignment6ModelConfiguration {


        @Named("featureExtractors")
        public List<FeatureExtractor<Element>> featureExtractors() {
                return Collections.singletonList(new Assignment6IsNerPresentFE());
        }}


/**
 * The feature extractor to be used in the configuration. It searches for {@link NamedEntity} elements with {@code type = NER_TYPE}
 * that cover {@link Token} elements and creates features with {@code name = FEATURE_NAME} for those tokens.
 */
class Assignment6IsNerPresentFE<T extends Element> implements FeatureExtractor<T> {

        /**
         * Name of {@link Feature} the feature extractor produces.
         */
        private static final String FEATURE_NAME = "isNer";

        @Override
        public Collection<Feature> extract(Document document, T element) {
                List<Feature> result = new ArrayList<>();
                List<NamedEntity> namedEntity = document.findCovering(NamedEntity.class, element);
                for (NamedEntity ner : namedEntity) {
                        if (ner.getType().equalsIgnoreCase(NER_TYPE)
                                && ner.getText().endsWith(".com")) {
                                result.add(new Feature(FEATURE_NAME, 1.0));
                        }
                }
                return result;
        }

}