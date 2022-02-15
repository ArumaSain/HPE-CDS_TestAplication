package com.hpecds.testaplication.Services.IMPL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hpecds.testaplication.Model.*;
import com.hpecds.testaplication.Services.TestService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service()
public class TestServiceIMPL implements TestService {
    /**
     * Method that reads the file given.
     * @param name
     * @return an Array of strings that defines the objects in the file
     */
    @Override
    public ArrayList<String> ReadFile(String name) {
        String file = "logs/" + name;
        String content;
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            content = "";
            while(content != null){
                content = br.readLine();
                if (content != null) {
                    System.out.println(content);
                    lines.add(content);
                }

            }
            System.out.println("TAMAÑO DEL FICHERO: " + lines.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Method that identify the origin of the call and stores it.
     * @param calls
     * @return an Array that contains the number of calls made from each diferent country
     */
    @Override
    public ArrayList<CallsNumberCC> OriginCalls(ArrayList<Call> calls) {
        ArrayList<CallsNumberCC> originCalls = new ArrayList<>();
        ArrayList<Integer> CountryCodes = getCountryCodes(calls, "origin");
        int auxCode= 0;

        for (Integer cc : CountryCodes) {
            CallsNumberCC match = new CallsNumberCC(cc, 0);
            for (Call call : calls) {
                auxCode = Integer.parseInt(Integer.toString(call.getOrigin()).substring(0,2));
                if (cc == auxCode) {
                    match.IncreaseNumber();
                }
            }
            originCalls.add(match);
        }
        return originCalls;
    }

    /**
     * Method that identify the destination of the call and stores it.
     * @param calls
     * @return an Array that contains the number of calls recieved in diferent countries
     */
    @Override
    public ArrayList<CallsNumberCC> DestinationCalls(ArrayList<Call> calls) {
        ArrayList<CallsNumberCC> destinationCalls = new ArrayList<>();
        ArrayList<Integer> CountryCodes = getCountryCodes(calls, "destination");
        int auxCode= 0;

        for (Integer cc : CountryCodes) {
            CallsNumberCC match = new CallsNumberCC(cc, 0);
            for (Call call : calls) {
                auxCode = Integer.parseInt(Integer.toString(call.getDestination()).substring(0,2));
                if (cc == auxCode) {
                    match.IncreaseNumber();
                }
            }
            destinationCalls.add(match);
        }
        return destinationCalls;
    }

    /**
     * method that calculates the average time of calls made from a country
     * @param calls
     * @return an Array that contains the average time by country
     */
    @Override
    public ArrayList<CallsDurationCC> CallsDuration(ArrayList<Call> calls) {
        ArrayList<CallsDurationCC> callsDurationCC = new ArrayList<>();
        ArrayList<Integer> CountryCodes = getCountryCodes(calls, "origin");
        int auxCode, time;
        for (Integer cc : CountryCodes) {
            CallsDurationCC match = new CallsDurationCC(cc, 0);
            for (Call call : calls) {
                auxCode = Integer.parseInt(Integer.toString(call.getOrigin()).substring(0,2));
                if (cc == auxCode) {
                    time = call.getDuration();
                    match.SumDuration(time);
                }
            }
            match.CalculateAverage(CountryCodes.size());
            callsDurationCC.add(match);
        }


        return callsDurationCC;
    }


    /**
     * Method that processes the data on the file .
     * @param lines
     * @return a Metric object with all information the endpoint "/metrics" needs.
     */
    @Override
    public Metrics Metrics(ArrayList<String> lines) {
        //other
        ArrayList<Call> calls = new ArrayList<>();
        ArrayList<Msg> msgs = new ArrayList<>();
        JsonParser parser = new JsonParser();

        //Metric fields
        int numberMissingFields = 0;
        int numberOfMessagesWithBlankContent = 0;
        int numberOfRowsWithErrors = 0;
        ArrayList<CallsNumberCC> numberOfOriginCallsByCC;
        ArrayList<CallsNumberCC> numberOfDestinationCallsByCC;
        ArrayList<CallsDurationCC> averageCallDuration = new ArrayList<>();
        CallsOKKO OKKOCalls = new CallsOKKO();

        //Common fields
        String message_type;
        int timestamp;
        int origin;
        int destination;

        //Call fields
        int duration;
        String status_code;
        String status_description;

        //Msg fields
        String message_content;
        String message_status;

        for (String line : lines) {
            message_type="";
            timestamp=0;
            origin=0;
            destination=0;
            duration =0;
            status_code ="";
            status_description="";
            message_content="";
            message_status="";

            //create json object
            JsonObject json = parser.parse(line).getAsJsonObject();

            //Assign common fields values to variables
            if (json.get("message_type") != null && json.get("message_type").getAsJsonPrimitive().isString()) {
                message_type = json.get("message_type").getAsString();
            }else {
                numberMissingFields++;
                continue; //interrupts the execution and continues from the next element.
            }
            if (json.get("timestamp") != null && json.get("timestamp").getAsJsonPrimitive().isNumber()) {
                timestamp = json.get("timestamp").getAsInt();
            }else {
                numberMissingFields++;
                continue;
            }
            if (json.get("origin") != null && json.get("origin").getAsJsonPrimitive().isNumber()) {
                origin = json.get("origin").getAsInt();
            }else {
                numberMissingFields++;
                continue;
            }
            if (json.get("destination") != null && json.get("destination").getAsJsonPrimitive().isNumber()) {
                destination = json.get("destination").getAsInt();
            }else {
                numberMissingFields++;
                continue;
            }

            //Specific variables for calls
            if (message_type.equals("CALL")) {
                if (json.get("duration") != null && json.get("duration").getAsJsonPrimitive().isNumber()) {
                    duration = json.get("duration").getAsInt();
                }else {
                    numberMissingFields++;
                    continue;
                }
                if (json.get("status_code") != null && json.get("status_code").getAsJsonPrimitive().isString()) {
                    status_code = json.get("status_code").getAsString();

                    if (!status_code.equals("OK") && !status_code.equals("KO")) {
                        numberOfRowsWithErrors++;
                    }else{
                        if (status_code.equals("OK")) {
                            OKKOCalls.IncreaseOk();
                        } else {
                            OKKOCalls.IncreaseKo();
                        }
                    }

                }else {
                    numberMissingFields++;
                    continue;
                }
                if (json.get("status_description") != null && json.get("status_description").getAsJsonPrimitive().isString()) {
                    status_description = json.get("status_description").getAsString();
                }else {
                    numberMissingFields++;
                    continue;
                }
                calls.add(new Call(message_type, timestamp, origin, destination, duration, status_code, status_description));

            }
            //Specific Variables for messages
            else if (message_type.equals("MSG")) {
                if (json.get("message_content") != null && json.get("message_content").getAsJsonPrimitive().isString()) {
                    message_content = json.get("message_content").getAsString();
                    if (message_content.equals("")) {
                        numberOfMessagesWithBlankContent++;
                    }

                }else {
                    numberMissingFields++;
                    continue;
                }
                if (json.get("message_status") != null && json.get("message_status").getAsJsonPrimitive().isString()) {
                    message_status = json.get("message_status").getAsString();
                    if (!message_status.equals("DELIVERED") && !message_status.equals("SEEN")) {

                    }
                }else {
                    numberMissingFields++;
                    continue;
                }
                msgs.add(new Msg(message_type, timestamp, origin, destination, message_content, message_status));


            } else {
                numberOfRowsWithErrors++;
                continue;
            }
        }//END FOREACH

        float relationBetweenOkKoCalls = OKKOCalls.getOk()/OKKOCalls.getKo();
        numberOfOriginCallsByCC = OriginCalls(calls);
        numberOfDestinationCallsByCC = DestinationCalls(calls);
        averageCallDuration = CallsDuration(calls);
        Metrics m = new Metrics(numberMissingFields, numberOfMessagesWithBlankContent, numberOfRowsWithErrors, numberOfOriginCallsByCC, numberOfDestinationCallsByCC, averageCallDuration, relationBetweenOkKoCalls);
        System.out.println(m.toString());
        return m;
    }

    /**
     * Method that processes the data inside a file
     * @param lines
     * @return a Kpis object with all information the endpoint "/kpis" needs.
     */
    @Override
    public Kpis Kpis(ArrayList<String> lines) {
        //other
        ArrayList<Call> calls = new ArrayList<>();
        ArrayList<Msg> msgs = new ArrayList<>();
        JsonParser parser = new JsonParser();

        //Kpis fields
        int totalNumberOfProcessedJson = 0;
        int totalNumberOfRows = 0;
        int totalNumberOfcalls = 0;
        int totalNumberOfMsgs = 0;
        int totalNumberOfOriginCC = 0;
        int totalNumberOfDestinationCC = 0;
        long DurationOfProcessedJson;
        long DurationStart = System.currentTimeMillis();
        long DurationEnd;

        //Common fields
        String message_type;
        int timestamp;
        int origin;
        int destination;

        //Call fields
        int duration;
        String status_code;
        String status_description;

        //Msg fields
        String message_content;
        String message_status;

        for (String line : lines) {
            message_type="";
            timestamp=0;
            origin=0;
            destination=0;
            duration =0;
            status_code ="";
            status_description="";
            message_content="";
            message_status="";

            //create json object
            JsonObject json = parser.parse(line).getAsJsonObject();

            //Assign common fields values to variables
            if (json.get("message_type") != null && json.get("message_type").getAsJsonPrimitive().isString()) {
                message_type = json.get("message_type").getAsString();
            }
            if (json.get("timestamp") != null && json.get("timestamp").getAsJsonPrimitive().isNumber()) {
                timestamp = json.get("timestamp").getAsInt();
            }
            if (json.get("origin") != null && json.get("origin").getAsJsonPrimitive().isNumber()) {
                origin = json.get("origin").getAsInt();
            }
            if (json.get("destination") != null && json.get("destination").getAsJsonPrimitive().isNumber()) {
                destination = json.get("destination").getAsInt();
            }

            //Specific variables for calls
            if (message_type.equals("CALL")) {
                if (json.get("duration") != null && json.get("duration").getAsJsonPrimitive().isNumber()) {
                    duration = json.get("duration").getAsInt();
                }
                if (json.get("status_code") != null && json.get("status_code").getAsJsonPrimitive().isString()) {
                    status_code = json.get("status_code").getAsString();
                }
                if (json.get("status_description") != null && json.get("status_description").getAsJsonPrimitive().isString()) {
                    status_description = json.get("status_description").getAsString();
                }

                calls.add(new Call(message_type, timestamp, origin, destination, duration, status_code, status_description));
            }
            //Specific Variables for messages
            else if (message_type.equals("MSG")) {
                if (json.get("message_content") != null && json.get("message_content").getAsJsonPrimitive().isString()) {
                    message_content = json.get("message_content").getAsString();
                }
                if (json.get("message_status") != null && json.get("message_status").getAsJsonPrimitive().isString()) {
                    message_status = json.get("message_status").getAsString();
                }

                msgs.add(new Msg(message_type, timestamp, origin, destination, message_content, message_status));
            }

        }//END FOREACH
        totalNumberOfProcessedJson = 1;
        totalNumberOfRows = lines.size();
        totalNumberOfcalls = calls.size();
        totalNumberOfMsgs = msgs.size();
        totalNumberOfOriginCC = getCountryCodes(calls, "origin").size();
        totalNumberOfDestinationCC = getCountryCodes(calls, "destination").size();

        DurationEnd = System.currentTimeMillis();
        DurationOfProcessedJson = DurationEnd - DurationStart;
        Kpis k = new Kpis(totalNumberOfProcessedJson, totalNumberOfRows, totalNumberOfcalls, totalNumberOfMsgs, totalNumberOfOriginCC, totalNumberOfDestinationCC, DurationOfProcessedJson);
        System.out.println(k.toString());

        return k;
    }

    /**
     * This method searchs for the different country codes inside the file.
     * @param calls
     * @param selector
     * @return a list of unique values that represent the codes of each country
     */
    private ArrayList<Integer> getCountryCodes(ArrayList<Call> calls, String selector) {
        int auxCode= 0;
        ArrayList<Integer> CountryCodes = new ArrayList<>();

        if (selector.equalsIgnoreCase("destination")){
            for (Call c: calls) {
                auxCode = Integer.parseInt(Integer.toString(c.getDestination()).substring(0,2));
                CountryCodes.add(auxCode);
            }
        }
        if (selector.equalsIgnoreCase("origin")){
            for (Call c: calls) {
                auxCode = Integer.parseInt(Integer.toString(c.getOrigin()).substring(0,2));
                CountryCodes.add(auxCode);
            }
        }

        Set<Integer> hashSet = new HashSet<>(CountryCodes);
        CountryCodes.clear();
        CountryCodes.addAll(hashSet);
        return CountryCodes;
    }

}
