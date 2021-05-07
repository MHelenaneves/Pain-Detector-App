package fr.enssat.caronnantel.algorithm;


import fr.enssat.caronnantel.model.PainNopain;

public class PredictionResult {

    private PainNopain expected;
    private PainNopain predicted;

    public PredictionResult(PainNopain expected, PainNopain predicted) {
        this.expected = expected;
        this.predicted = predicted;
    }

    public PainNopain getExpected() {
        return expected;
    } //used in the confusion matrix

    public void setExpected(PainNopain expected) {
        this.expected = expected;
    }

    public PainNopain getPredicted() {
        return predicted;
    }//used in the confusion matrix

    public void setPredicted(PainNopain predicted) {
        this.predicted = predicted;
    }
}
