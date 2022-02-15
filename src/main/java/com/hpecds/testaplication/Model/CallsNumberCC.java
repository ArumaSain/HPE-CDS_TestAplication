package com.hpecds.testaplication.Model;

public class CallsNumberCC {

    private int CountryCode;
    private int Number;

    public CallsNumberCC(int countryCode, int number) {
        CountryCode = countryCode;
        Number = number;
    }

    public CallsNumberCC() {
    }

    public int getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(int countryCode) {
        CountryCode = countryCode;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public void IncreaseNumber() {
        ++this.Number;
    }

    @Override
    public String toString() {
        return "CallsNumberCC{" +
                "CountryCode=" + CountryCode +
                ", Number=" + Number +
                '}';
    }
}
