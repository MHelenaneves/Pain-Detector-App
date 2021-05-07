package fr.enssat.caronnantel.algorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fr.enssat.caronnantel.model.FrequencyBands;
import fr.enssat.caronnantel.model.InvalidDistanceType;
import fr.enssat.caronnantel.model.PainNopain;
import fr.enssat.caronnantel.utilities.ListUtils;

//import fr.enssat.caronnantel.model.IrisClass;

public class KNNAlgorithm {

    private int k; // k-nearest neighbor to consider
    private Map<FrequencyBands, PainNopain> trainingSet;

    public KNNAlgorithm(int k) {
        this.k = k;
    }

    public void setTrainingSet(Map<FrequencyBands, PainNopain> trainingSet) {
        this.trainingSet = trainingSet;
    }

    public PainNopain predict(FrequencyBands testInstance, DistanceType distanceType) {
        List<Result> result = new ArrayList<>();
        for (Map.Entry<FrequencyBands, PainNopain> entry : trainingSet.entrySet()) {
            result.add(new Result(calculDistance(distanceType, entry.getKey(), testInstance), entry.getValue()));
        }
        Collections.sort(result, new DistanceComparator());

        List<PainNopain> neighborsToConsider = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            if (i >= result.size()) { // Avoid too big value of K
                break;
            }
            neighborsToConsider.add(result.get(i).getPainnoPainClass());
        }

        return ListUtils.mostCommonItem(neighborsToConsider);
    }


    // ====================
    // DISTANCE CALCULATION
    // ====================

    private double calculDistance(DistanceType type, FrequencyBands from, FrequencyBands to) {
        switch (type) {
            case EUCLIDIENNE:
                return computeEuclidianDistance(from,to);
            case MANHATTAN:
                return  computeManhattanDistance(from,to);
            case MINKOWSKI:
                return computeMinkowskiDistance(from,to, 4); // 4 because we have 4 features for each flower
        }
        throw new InvalidDistanceType();
    }

    /**
     * Calcul de la distance Euclidienne entre les deux entitiées
     * Formule de https://fr.wikipedia.org/wiki/Distance_(math%C3%A9matiques)
     */
    private double computeEuclidianDistance(FrequencyBands from, FrequencyBands to) {
        double distance = 0.0;
        /*distance += Math.pow(from.getAveragedelta() - to.getSepalLength(), 2);
        distance += Math.pow(from.getSepalWidth() - to.getSepalWidth(), 2);
        distance += Math.pow(from.getPetalLength() - to.getPetalLength(), 2);
        distance += Math.pow(from.getPetalWidth() - to.getPetalWidth(), 2);
        return Math.sqrt(distance);*/
        distance += Math.pow(from.getAveragedelta() - to.getAveragedelta(), 2);
        distance += Math.pow(from.getAveragetheta() - to.getAveragetheta(), 2);
        distance += Math.pow(from.getAveragealpha() - to.getAveragealpha(), 2);
        distance += Math.pow(from. getAveragebeta() - to. getAveragebeta(), 2);
        distance += Math.pow(from.getAveragegamma() - to.getAveragegamma(), 2);
        return Math.sqrt(distance);
    }

    private double computeManhattanDistance(FrequencyBands from, FrequencyBands to) {
        double distance = 0.0;
        /*distance += Math.abs(from.getSepalLength() - to.getSepalLength());
        distance += Math.abs(from.getSepalWidth() - to.getSepalWidth());
        distance += Math.abs(from.getPetalLength() - to.getPetalLength());
        distance += Math.abs(from.getPetalWidth() - to.getPetalWidth());
        return distance;*/
        distance += Math.abs(from.getAveragedelta() - to.getAveragedelta());
        distance += Math.abs(from.getAveragetheta() - to.getAveragetheta());
        distance += Math.abs(from.getAveragealpha() - to.getAveragealpha());
        distance += Math.abs(from. getAveragebeta() - to. getAveragebeta());
        distance += Math.abs(from.getAveragegamma() - to.getAveragegamma());
        return distance;
    }

    private double computeMinkowskiDistance(FrequencyBands from, FrequencyBands to, int p) { // p-distance
        assert p != 0 : "p mut be > 0 !";
        double distance = 0;
        /*distance += Math.pow(from.getSepalLength() - to.getSepalLength(), p);
        distance += Math.pow(from.getSepalWidth() - to.getSepalWidth(), p);
        distance += Math.pow(from.getPetalLength() - to.getPetalLength(), p);
        distance += Math.pow(from.getPetalWidth() - to.getPetalWidth(), p);
        return Math.pow(distance, 1/p);*/
        distance += Math.pow(from.getAveragedelta() - to.getAveragedelta(), p);
        distance += Math.pow(from.getAveragetheta() - to.getAveragetheta(), p);
        distance += Math.pow(from.getAveragealpha() - to.getAveragealpha(), p);
        distance += Math.pow(from. getAveragebeta() - to. getAveragebeta(), p);
        distance += Math.pow(from.getAveragegamma() - to.getAveragegamma(), p);
        return Math.pow(distance, 1/p);
    }

}
