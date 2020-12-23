package com.example.speechrecognitionproject.Model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class CountryDataSource {
    //22.661388, 75.833971

    public static final String COUNTRY_KEY = "country";
    public static final float MINIMUM_CONFIDENCE_LEVEL = 0.4f;
    public static final String DEFAULT_COUNTRY_NAME = "India";
    public static final double DEFAULT_COUNTRY_LATITUDE = 22.661388;
    public static final double DEFAULT_COUNTRY_LONGITUDE = 75.833971;
    public static final String DEFAULT_COUNTRY_MESSAGE = "Hope you enjoy your stay";

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

        Enumeration<String> countries;

        for(int i = 0; i < numberOfUserWords && i < confidenceLevels.length; i++){
            if(confidenceLevels[i] < MINIMUM_CONFIDENCE_LEVEL){
                break;
            }

            String acceptedUserWord = userWords.get(i);

            countries = countriesAndMessages.keys();

            while(countries.hasMoreElements()) {
                String selectedCountry = countries.nextElement();

                if(acceptedUserWord.equalsIgnoreCase(selectedCountry)){
                    return acceptedUserWord;
                }
            }
        }
        return DEFAULT_COUNTRY_NAME;
    }

    public String getCountryInfo(String country){
        return countriesAndMessages.get(country);
    }
}
