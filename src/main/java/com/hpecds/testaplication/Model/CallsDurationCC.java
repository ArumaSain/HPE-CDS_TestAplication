package com.hpecds.testaplication.Model;

public class CallsDurationCC {

    private int countryCode;
    private int duration;
    private float average;

    public CallsDurationCC(int countryCode, int duration) {
        this.countryCode = countryCode;
        this.duration = duration;
    }

    public CallsDurationCC() {
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void SumDuration(int time) {
        this.duration += time;
    }

    public void CalculateAverage(int numberOfCountries){
        this.average = this.duration/numberOfCountries;
    }

    @Override
    public String toString() {
        return "CallsDurationCC{" +
                "countryCode=" + countryCode +
                ", duration=" + duration +
                ", average=" + average +
                '}';
    }
}
