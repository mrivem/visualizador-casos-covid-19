package com.teamsolemne.pruebaapi.model;

public class StatsGlobal {
    private final String date;
    private final long newConfirmed;
    private final long newDeaths;
    private final long newRecovered;
    private final long totalConfirmed;
    private final long totalDeaths;
    private final long totalRecovered;

    public StatsGlobal(String date, long newConfirmed, long newDeaths, long newRecovered, long totalConfirmed, long totalDeaths, long totalRecovered) {
        this.date = date;
        this.newConfirmed = newConfirmed;
        this.newDeaths = newDeaths;
        this.newRecovered = newRecovered;
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
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
