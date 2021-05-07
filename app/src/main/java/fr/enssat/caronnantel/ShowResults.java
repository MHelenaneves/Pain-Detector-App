package fr.enssat.caronnantel;

import android.content.res.Resources;
import android.util.Log;
import android.util.Pair;

import fr.enssat.caronnantel.algorithm.DistanceType;
import fr.enssat.caronnantel.algorithm.KNNAlgorithm;
import fr.enssat.caronnantel.algorithm.PredictionResult;
//import fr.enssat.caronnantel.model.Flower;
import fr.enssat.caronnantel.model.FrequencyBands;
//import fr.enssat.caronnantel.model.IrisClass;
import fr.enssat.caronnantel.model.PainNopain;
import fr.enssat.caronnantel.utilities.DataImporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowResults {

    /**
     * The best choice of K depends upon the data.
     * Generally, larger values of K reduce the effect of noise on the classification
     */
    private static final String TAG1 = "res1";
    private static final String TAG2 = "res2";
    private static final String TAG3 = "res3";
    private static final String TAG4 = "res4";
    //private static final String TAG5 = "res5";
    private static final int K = 7; // replace with optimal K found in matlab

    private static final DistanceType distanceType = DistanceType.EUCLIDIENNE;

    private KNNAlgorithm algorithm;

    public ShowResults(Resources resources) throws IOException {
        algorithm = new KNNAlgorithm(K);

        DataImporter importer = new DataImporter();
        Map<FrequencyBands, PainNopain> trainingSet = importer.getTrainingSet(resources);
        algorithm.setTrainingSet(trainingSet);
    }


    public static void temp(Resources resources) throws IOException, NoSuchFieldException {
        //this method trains and tests the classifier
        KNNAlgorithm algorithm = new KNNAlgorithm(K);

        DataImporter importer = new DataImporter();
        Map<FrequencyBands, PainNopain> trainingSet = importer.getTrainingSet(resources);
        Map<FrequencyBands, PainNopain> testSet = importer.getTestSet(resources);

        algorithm.setTrainingSet(trainingSet);

        //System.out.println("Executing the " + K + "-Nearest Neighbors algorithm...");
        int goodPredictions = 0;
        List<PredictionResult> predictionsResults = new ArrayList<>();
        for (Map.Entry<FrequencyBands, PainNopain> entry : testSet.entrySet()) {
            PainNopain prediction = algorithm.predict(entry.getKey(), distanceType);

            PredictionResult result = new PredictionResult(entry.getValue(), prediction);
            predictionsResults.add(result);

            if (prediction == entry.getValue()) { //if the prediction is the same as the true
                goodPredictions++;
            }
            //System.out.println("prediction = " + prediction + " | expected = " + entry.getValue());
        }

        //System.out.println("-----");
        //System.out.println("For K=" + K + " and DistanceType " + distanceType + " results are:");
        /*Log.i(TAG1,"For K=" + K + " and DistanceType " + distanceType + " results are:");
        Log.i(TAG4,"     - Total predictions = " + testSet.size() + " | good = " + goodPredictions + " | bad = " + (testSet.size() - goodPredictions));
        Log.i(TAG2, "     - Total predictions = " + testSet.size() + " | good = " + goodPredictions + " | bad = " + (testSet.size() - goodPredictions));
        double accuracy = goodPredictions * 1.0 / testSet.size();
        //Log.i(TAG5,"     - Accuracy = " + accuracy * 100 + "%");
        Log.i(TAG3, "     - Accuracy = " + accuracy * 100 + "%");*/

    }




    private Pair<List<PredictionResult>, Integer> testRT(int count, Resources resources) throws IOException, NoSuchFieldException{//test real time data

        DataImporter rtimporter = new DataImporter();
        Map<FrequencyBands, PainNopain> testRT = rtimporter.getRealTime(count, resources);

        int goodPredictionsRT = 0;
        List<PredictionResult> predictionsResultsRT = new ArrayList<>();
        for (Map.Entry<FrequencyBands, PainNopain> entryRT : testRT.entrySet()) {
            PainNopain predictionRT = algorithm.predict(entryRT.getKey(), distanceType); //the parameter is frequency bands and should be real time; i think it has to do with the methods being public

            PredictionResult resultRT = new PredictionResult(entryRT.getValue(), predictionRT);
            predictionsResultsRT.add(resultRT);

            if (predictionRT == entryRT.getValue()) { //if the prediction is the same as the true
                goodPredictionsRT++;
            }

        }
        return new Pair<>(predictionsResultsRT, goodPredictionsRT );
    }


    //i think i have to create a new method to attribute a class(pain/no pain) to each file to show the patient's current state
    public String RTstate(int count, Resources resources)  {
        List<PredictionResult> predictionsResultsRT = null;
        int goodPredictionsRT = 2;

        try {
            Pair<List<PredictionResult>, Integer> pair = this.testRT(count, resources);
            predictionsResultsRT = pair.first;
            goodPredictionsRT = pair.second;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        int occurrences;
        String rtstate = null;
        if (goodPredictionsRT >= 0 * predictionsResultsRT.size()){
        //if (goodPredictionsRT >= 0.6 * predictionsResultsRT.size()){ //remove 0.6
            occurrences = mode(this.toIntArray(predictionsResultsRT));
            System.out.println("occurrences=" + occurrences);
            if (occurrences == 1){
                rtstate = "Pain";
            }
            else if (occurrences == 0){
                rtstate = "No Pain";
            }
            else {
                rtstate = "Current state not available";
            }

        }
        return rtstate;
    }

    private  int[] toIntArray(List<PredictionResult> predictionsResultsRT){
        int[] array = new int[predictionsResultsRT.size()] ;
        for (int i = 0; i < predictionsResultsRT.size(); i++){
            array[i] =  predictionsResultsRT.get(i).getPredicted().toInt();
        }
        return array;
    }

    public int mode(int[] array) {
        int mode = array[0];
        int maxCount = 0;
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int count = 1;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == value) count++;
                if (count > maxCount) {
                    mode = value;
                    maxCount = count;
                }
            }
        }
        return mode;
    }


}
