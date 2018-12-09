package com.r00t.ai.models;

public class Statistics {
    private String startTime;
    private String endTime;
    private String targetDNA;
    private String outputDNA;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTargetDNA() {
        return targetDNA;
    }

    public void setTargetDNA(Integer[] targetDNA) {
        this.targetDNA = integerArrayToString(targetDNA);
    }

    public String getOutputDNA() {
        return outputDNA;
    }

    public void setOutputDNA(Integer[] outputDNA) {
        this.outputDNA = integerArrayToString(outputDNA);
    }

    private String integerArrayToString(Integer[] data) {
        StringBuilder converted = new StringBuilder();
        for (Integer temp : data)
            converted.append(temp).append(" ");
        return converted.toString();
    }
}
