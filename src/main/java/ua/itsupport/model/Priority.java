package ua.itsupport.model;


public enum Priority {
    LOW(1.0),
    MEDIUM(1.5),
    HIGH(2.0),
    CRITICAL(3.0);

    private final double weightFactor;

    Priority(double weightFactor) {
        this.weightFactor = weightFactor;
    }

    public double getWeightFactor() {
        return weightFactor;
    }
}