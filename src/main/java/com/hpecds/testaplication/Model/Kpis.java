package com.hpecds.testaplication.Model;

public class Kpis {

    private int NumberOfFiles;
    private int NumberOfRows;
    private int NumberOfCalls;
    private int NumberOfMsgs;
    private int NumberOfDIfferentOriginCC;
    private int NumberOfDifferentDestinationCC;
    private long DurationOfPRocessedJson;

    public Kpis(int numberOfFiles, int numberOfRows, int numberOfCalls, int numberOfMsgs, int numberOfDIfferentOriginCC, int numberOfDifferentDestinationCC, long durationOfPRocessedJson) {
        NumberOfFiles = numberOfFiles;
        NumberOfRows = numberOfRows;
        NumberOfCalls = numberOfCalls;
        NumberOfMsgs = numberOfMsgs;
        NumberOfDIfferentOriginCC = numberOfDIfferentOriginCC;
        NumberOfDifferentDestinationCC = numberOfDifferentDestinationCC;
        DurationOfPRocessedJson = durationOfPRocessedJson;
    }

    public Kpis() {
    }

    public int getNumberOfFiles() {
        return NumberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }

    public int getNumberOfRows() {
        return NumberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        NumberOfRows = numberOfRows;
    }

    public int getNumberOfCalls() {
        return NumberOfCalls;
    }

    public void setNumberOfCalls(int numberOfCalls) {
        NumberOfCalls = numberOfCalls;
    }

    public int getNumberOfMsgs() {
        return NumberOfMsgs;
    }

    public void setNumberOfMsgs(int numberOfMsgs) {
        NumberOfMsgs = numberOfMsgs;
    }

    public int getNumberOfDIfferentOriginCC() {
        return NumberOfDIfferentOriginCC;
    }

    public void setNumberOfDIfferentOriginCC(int numberOfDIfferentOriginCC) {
        NumberOfDIfferentOriginCC = numberOfDIfferentOriginCC;
    }

    public int getNumberOfDifferentDestinationCC() {
        return NumberOfDifferentDestinationCC;
    }

    public void setNumberOfDifferentDestinationCC(int numberOfDifferentDestinationCC) {
        NumberOfDifferentDestinationCC = numberOfDifferentDestinationCC;
    }

    public long getDurationOfPRocessedJson() {
        return DurationOfPRocessedJson;
    }

    public void setDurationOfPRocessedJson(long durationOfPRocessedJson) {
        DurationOfPRocessedJson = durationOfPRocessedJson;
    }

    @Override
    public String toString() {
        return "Kpis: " +
                "\n Number Of Files=" + NumberOfFiles +
                "\n Number Of Rows=" + NumberOfRows +
                "\n Number Of Calls=" + NumberOfCalls +
                "\n Number Of Msgs=" + NumberOfMsgs +
                "\n Number Of DIfferent Origin Country Codes=" + NumberOfDIfferentOriginCC +
                "\n Number Of Different Destination Country Codes=" + NumberOfDifferentDestinationCC +
                "\n Duration Of Processed Json=" + DurationOfPRocessedJson + "Milliseconds";
    }
}
