package com.teamsolemne.pruebaapi.model;

public class StatsCountry {
    private final String countryName;
    private final String countryCode;
    private final String countrySlug;
    private final String date;
    private final long newConfirmed;
    private final long newDeaths;
    private final long newRecovered;
    private final long totalConfirmed;
    private final long totalDeaths;
    private final long totalRecovered;

    public StatsCountry(String countryName, String countryCode, String countrySlug, String date, long newConfirmed, long newDeaths, long newRecovered, long totalConfirmed, long totalDeaths, long totalRecovered) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countrySlug = countrySlug;
        this.date = date;
        this.newConfirmed = newConfirmed;
        this.newDeaths = newDeaths;
        this.newRecovered = newRecovered;
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountrySlug() {
        return countrySlug;
    }

    public String getDate() {
        return date;
    }

    public long getNewConfirmed() {
        return newConfirmed;
    }

    public long getNewDeaths() {
        return newDeaths;
    }

    public long getNewRecovered() {
        return newRecovered;
    }

    public long getTotalConfirmed() {
        return totalConfirmed;
    }

    public long getTotalDeaths() {
        return totalDeaths;
    }

    public long getTotalRecovered() {
        return totalRecovered;
    }
    
    
}
