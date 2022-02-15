package com.hpecds.testaplication.Model;

import java.util.ArrayList;

public class Metrics {

    private int NumberOfRowsWithMissingFields = 0;
    private int NumberOfMessagesWithBlankContent = 0;
    private int numberOfRowsWithErrors = 0;
    private ArrayList<CallsNumberCC> NumberOfOriginCallsByCC; //Hace referencia a las llamadas que se hacen de un mismo sitio
    private ArrayList<CallsNumberCC> NumberOfDestinationCallsByCC; //Hace referencia que se reciben en un mismo sitio
    private ArrayList<CallsDurationCC> AverageCallDurationByCC;
    private float RelationBetweenOKKO;

    public Metrics(int numberOfRowsWithMissingFields, int numberOfMessagesWithBlankContent, int numberOfRowsWithErrors, ArrayList<CallsNumberCC> numberOfOriginCallsByCC, ArrayList<CallsNumberCC> numberOfDestinationCallsByCC, ArrayList<CallsDurationCC> averageCallDurationByCC, float relationBetweenOKKO) {
        this.NumberOfRowsWithMissingFields = numberOfRowsWithMissingFields;
        this.NumberOfMessagesWithBlankContent = numberOfMessagesWithBlankContent;
        this.numberOfRowsWithErrors = numberOfRowsWithErrors;
        this.NumberOfOriginCallsByCC = numberOfOriginCallsByCC;
        this.NumberOfDestinationCallsByCC = numberOfDestinationCallsByCC;
        this.AverageCallDurationByCC = averageCallDurationByCC;
        this.RelationBetweenOKKO = relationBetweenOKKO;
    }

    public Metrics() {
    }

    public int getNumberOfRowsWithMissingFields() {
        return NumberOfRowsWithMissingFields;
    }

    public void setNumberOfRowsWithMissingFields(int numberOfRowsWithMissingFields) {
        NumberOfRowsWithMissingFields = numberOfRowsWithMissingFields;
    }

    public int getNumberOfMessagesWithBlankContent() {
        return NumberOfMessagesWithBlankContent;
    }

    public void setNumberOfMessagesWithBlankContent(int numberOfMessagesWithBlankContent) {
        NumberOfMessagesWithBlankContent = numberOfMessagesWithBlankContent;
    }

    public int getNumberOfRowsWithErrors() {
        return numberOfRowsWithErrors;
    }

    public void setNumberOfRowsWithErrors(int numberOfRowsWithErrors) {
        this.numberOfRowsWithErrors = numberOfRowsWithErrors;
    }

    public ArrayList<CallsNumberCC> getNumberOfOriginCallsByCC() {
        return NumberOfOriginCallsByCC;
    }

    public void setNumberOfOriginCallsByCC(ArrayList<CallsNumberCC> numberOfOriginCallsByCC) {
        NumberOfOriginCallsByCC = numberOfOriginCallsByCC;
    }

    public ArrayList<CallsNumberCC> getNumberOfDestinationCallsByCC() {
        return NumberOfDestinationCallsByCC;
    }

    public void setNumberOfDestinationCallsByCC(ArrayList<CallsNumberCC> numberOfDestinationCallsByCC) {
        NumberOfDestinationCallsByCC = numberOfDestinationCallsByCC;
    }

    public ArrayList<CallsDurationCC> getAverageCallDurationByCC() {
        return AverageCallDurationByCC;
    }

    public void setAverageCallDurationByCC(ArrayList<CallsDurationCC> averageCallDurationByCC) {
        AverageCallDurationByCC = averageCallDurationByCC;
    }

    public float getRelationBetweenOKKO() {
        return RelationBetweenOKKO;
    }

    public void setRelationBetweenOKKO(float relationBetweenOKKO) {
        RelationBetweenOKKO = relationBetweenOKKO;
    }

    @Override
    public String toString() {
        return "Metrics: " +
                "\n Number Of Rows With Missing Fields=" + NumberOfRowsWithMissingFields +
                "\n Number Of Messages With Blank Content=" + NumberOfMessagesWithBlankContent +
                "\n Number Of Rows With Errors=" + numberOfRowsWithErrors +
                "\n Number Of Origin Calls By Country Code=" + NumberOfOriginCallsByCC +
                "\n Number Of Destination Calls By Country Code=" + NumberOfDestinationCallsByCC +
                "\n Average Call Duration By Country Code=" + AverageCallDurationByCC +
                "\n Relation Between OK/KO calls=" + RelationBetweenOKKO;
    }
}
