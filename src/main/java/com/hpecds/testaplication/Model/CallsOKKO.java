package com.hpecds.testaplication.Model;

public class CallsOKKO {

    private int ok;
    private int ko;

    public CallsOKKO(int ok, int ko) {
        this.ok = ok;
        this.ko = ko;
    }

    public CallsOKKO() {
    }

    public int getOk() {
        return ok;
    }

    public int getKo() {
        return ko;
    }

    public void IncreaseOk() {
        ++this.ok;
    }

    public void IncreaseKo() {
        ++this.ko;
    }

}
