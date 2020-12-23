package com.example.speechrecognitionproject.Model;

import java.util.ArrayList;
import java.util.Hashtable;

public class CountryDataSource {
    //22.661388, 75.833971

    public static final String COUNTRY_KEY = "country";
    public static final float MINIMUM_CONFIDENCE_LEVEL = 0.4f;
    public static final String DEFAULT_COUNTRY_NAME = "India";
    public static final double DEFAULT_COUNTRY_LATITUDE = 22.661388;
    public static final double DEFAULT_COUNTRY_LONGITUTDE = 75.833971;
    public static final String DEFAULT_COUNTRY_MESSAGE = "Welcome";

    private Hashtable<String, String> countriesAndMessages;

    public CountryDataSource(Hashtable<String, String> countriesAndMessages) {
        this.countriesAndMessages = countriesAndMessages;
    }

    public String matchWithMinimumConfidenceLevelOfUserWords(ArrayList<String> userWords,
                                                             float[] confidenceLevels) {
        if(userWords == null || confidenceLevels == null){
            return DEFAULT_COUNTRY_NAME;
        }

        int numberOfUserWords = userWords.size();

    }
}
