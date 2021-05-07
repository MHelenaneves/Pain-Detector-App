package fr.enssat.caronnantel.algorithm;


import fr.enssat.caronnantel.model.PainNopain;

class Result {

    private double distance;
    private PainNopain painnopain;

     Result(double distance, PainNopain painnopain) {
        this.distance = distance;
        this.painnopain = painnopain;
    }

     double getDistance() {
        return distance;
    }

     void setDistance(double distance) {
        this.distance = distance;
    }

    PainNopain getPainnoPainClass() {
        return painnopain;
    }

     void setPainnoPainClass(PainNopain painnopain) {
        this.painnopain = painnopain;
    }
}
