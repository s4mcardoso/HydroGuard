package org.example.model;

public class QualidadedeAgua {
    private String sensorId;
    private float temperature;
    private float ph;
    private float turbidity;
    private String timestamp;

    public QualidadedeAgua(String sensorId, float temperature, float ph, float turbidity, String timestamp) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.ph = ph;
        this.turbidity = turbidity;
        this.timestamp = timestamp;
    }

    // Getters
    public String getSensorId() {
        return sensorId;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPh() {
        return ph;
    }

    public float getTurbidity() {
        return turbidity;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
