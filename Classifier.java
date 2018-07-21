
/**
 * A classifer which can identifiy the type of properties using Decision Tree Alrogithm with 
 * training data 
 *
 * @author Fu Zhang, Michael Owen Jones, A K M Naharul Hayat and Noyan Raquib
 * @version 30/03/2018
 */
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.*;
import java.util.*;


import weka.classifiers.Evaluation;
import weka.classifiers.*;
//import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.REPTree;
    
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.converters.ArffLoader;

public class Classifier {
    
    private String errorMaxholder = "";
    
    private Instances trainingDataContainer;
    private Instances features;
    weka.classifiers.Classifier cls;
    
    /**
     * the algorithm is initialised
     * the data is loaded 
     */
    
    public Classifier(){
        // init a new J48 Clssifier 
        // J48 is a implmentation of C4.5 made by Weka
        cls = new J48();
         
        try {
            // loading traing dataset from local
            trainingDataContainer = getDataset("train_dataset.arff",4);
            train();
        }
        catch(Exception e){
            
            e.printStackTrace();
            
        }
       
       
     }

    
     /**
      *  the function that call classify function to classify the data passed in 
      *  
      *  
      *  @param neighbourhood of propertiy
      *  @param pr price of propertiy
      *  @param min minimum available night of propertiy
      *  @param available times of propertiy
      *  
      */
     
    public double[] Classify(int nei,int pr,int min,int ava){
        
       return classify(nei,pr,min,ava);
        
    }
     
     
    /**
     * this function load the data from local and convert it into a instances which are needed 
     * for classification algorithm 
     * 
     * @param filePath the path of the data
     * @param classIndex the index of the class which you want to use as output(classes)
     * 
     * @return A instaces of traning data
     * 
     */
    private Instances getDataset(String filePath, int classIndex) throws IOException{
        
        FastVector featureVector = makeFeatureVector();
        
        /// load the Arff data
        ArffLoader loader = new ArffLoader();
        loader.setSource(Classifier.class.getResourceAsStream("/" + filePath));
        
        //Create the training Instances which can be understood as a set of features vectors
        Instances dataSet = new Instances("train",featureVector,60000);
        dataSet = loader.getDataSet();
        
        // set the class that's output class 
        dataSet.setClassIndex(classIndex);
        return dataSet;
        
    }
    
    /**
     * This function train the model with traing data 
     * model needs to be trained before being used 
     */
    
    private void train() throws Exception{
        
        
        cls.buildClassifier(trainingDataContainer);
        
        Evaluation test = new Evaluation(trainingDataContainer);
        test.evaluateModel(cls,trainingDataContainer);
        
        String strSummary = test.toSummaryString();
        
        //OUTPUT System.out.println(strSummary);
        
        // Store the error martix
        errorMaxholder = test.toMatrixString();
     
    }
    
    /**
     * This is the function which actually do the classification works, 
     * the input data passed in is converted into a instances first firstly(set up with the feature 
     * vector (label )), then set the training data to the training data used for training. lastly,go through
     * the decision tree to make decision 
     * 
     * @param neighbourhood of propertiy
     * @param pr price of propertiy
     * @param min minimum available night of propertiy
     * @param available times of propertiy
     * 
     * 
     * @return  probabilities of all classes
     */
    
    private double[] classify(int nei,int pr,int min,int ava){
    
     FastVector featureVector = makeFeatureVector();  
     features = new Instances("actual",featureVector,10);
     Instance td = new DenseInstance(4);
     double[] result = {0,0,0};
     
     //set up the input data with a feature vector (instance)
     td.setValue((Attribute)featureVector.elementAt(0),nei);
     td.setValue((Attribute)featureVector.elementAt(1),pr);
     td.setValue((Attribute)featureVector.elementAt(2),min);
     td.setValue((Attribute)featureVector.elementAt(3),ava);
     
      
     // set the knoweldge base of the input
     td.setDataset(trainingDataContainer);
     
     try{
         // classify it 
         result = cls.distributionForInstance(td);
        
     } 
     catch(Exception e)
     {
         e.printStackTrace();
     }
     
     //return the result 
     return result;
    
    }
    
    
    /**
     * define the feature vector format
     */
   
    private FastVector makeFeatureVector(){
        
        
         Attribute Attribute1 = new Attribute("neighbourhood");
         Attribute Attribute2 = new Attribute("price");
         Attribute Attribute3 = new Attribute("miniNight");
         Attribute Attribute4 = new Attribute("avaDay");
         
        // generate the feature vector
        FastVector toReturn = new FastVector(3);
        
        //add the attributes into feature vector 
        toReturn.addElement(Attribute1);
        toReturn.addElement(Attribute2);
        toReturn.addElement(Attribute3);
        toReturn.addElement(Attribute4);
        
        
        return toReturn;
        
    }
    
    
    /**
     * get the errorMaxholder
     */
    
    public String getErrorMaxholder(){
        
        return this.errorMaxholder;
        
    }
    
    
}
