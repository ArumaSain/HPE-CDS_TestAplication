package com.hpecds.testaplication.Services;

import com.hpecds.testaplication.Model.*;

import java.util.ArrayList;

public interface TestService {

    public abstract ArrayList<String> ReadFile(String file);
    public abstract ArrayList<CallsNumberCC> OriginCalls (ArrayList<Call> calls);
    public abstract ArrayList<CallsNumberCC> DestinationCalls (ArrayList<Call> calls);
    public abstract ArrayList<CallsDurationCC> CallsDuration (ArrayList<Call> calls);
    public abstract Metrics Metrics (ArrayList<String> lines);
    public abstract Kpis Kpis (ArrayList<String> lines);

}
