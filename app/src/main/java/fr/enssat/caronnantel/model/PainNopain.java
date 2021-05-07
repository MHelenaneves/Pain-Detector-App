package fr.enssat.caronnantel.model;

public enum PainNopain { //change name IrisClass for PainNopain when I have the files
    //replace with our pain/no pain
    //SETOSA("Iris-setosa"), VERSICOLOUR("Iris-versicolor"), VIRGINICA("Iris-virginica");
    PAIN("1"), NoPain("0");


    private String value;

    PainNopain(String value) {
        this.value = value;
    }

    public int toInt(){
        return Integer.parseInt(value);
    }

    public static PainNopain forValue(String value) {
        for (PainNopain type : PainNopain.values()) {
            if (type.value.equals(value)) {
                return type;//example: input Iris-setosa and returns IrisClass.SETOSA
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}