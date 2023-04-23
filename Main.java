package com.company;

import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;

import weka.core.Instances;

import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.ArrayList;

import java.io.*;


public class Main {
    public static final String DATASETPATH1 = "C:\\Users\\march\\Downloads\\write(";
    public static final String DATASETPATH2 = ").arff";
    public static final String MODElPATH = "C:\\Users\\march\\Downloads\\model.bin";

    public static void main(String[] args) throws Exception {
            DataSource source = new DataSource(DATASETPATH1 + String.valueOf(1) + DATASETPATH2);
            Instances dataset = source.getDataSet();
            dataset.setClassIndex(dataset.numAttributes() - 1);

            int trainSize = (int) Math.round(dataset.numInstances() * 0.7);
            int testSize = dataset.numInstances() - trainSize;

            Instances traindataset = new Instances(dataset, 0, trainSize);
            Instances testdataset = new Instances(dataset, trainSize, testSize);

            MultilayerPerceptron m = new MultilayerPerceptron();
            m.buildClassifier(traindataset);

            m.setGUI(true);

            m.setLearningRate(10000);
            m.setMomentum(10000);
            m.setSeed(42);
            m.setTrainingTime(100000);
            m.setValidationSetSize(10);
            m.setNominalToBinaryFilter(true);
            m.setNormalizeAttributes(true);
            m.setNormalizeNumericClass(true);
            m.setReset(true);


            Evaluation eval = new Evaluation(traindataset);
            eval.evaluateModel(m, testdataset);

            ArrayList pred = eval.predictions();

            System.out.println("-_-_-_-_-_-_-_-_-\n -_-_-_-_-_-_-_-_-\n-_-_-_-_-_-_-_-_-\n -_-_-_-_-_-_-_-_-\n-_-_-_-_-_-_-_-_-\n -_-_-_-_-_-_-_-_-");
            System.out.println("Evaluation: " + eval.toSummaryString("", true));

            System.out.println(pred.toString());
            String x = "";
            for (int i = 0; i != 2270; i++)
                x = x + pred.get(i).toString().substring(11, 17) + "\n";

            try (FileWriter writer = new FileWriter("pred" + String.valueOf(1) + ".txt", false)) {
                writer.write(x);
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(Float.parseFloat(pred.get(0).toString().substring(11, 17)));
            SerializationHelper.write(MODElPATH, m);
  //      }
        HelloApplication.lunch_this();
    }
}

