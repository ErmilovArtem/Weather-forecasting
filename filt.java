package com.company;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

import weka.classifiers.Classifier;

import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;


public class filt {

    public  Instances give_normal(Instances dataset) throws Exception {
    try {
        Normalize normalize = new Normalize();
        normalize.setInputFormat(dataset);
        Instances newdata = Filter.useFilter(dataset, normalize);
        return newdata;
    }
    catch(Exception ex){

    }
    return dataset;
    }

}
