package com.example.shiz.fetchimagerxretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Worldpopulation {

    @SerializedName("rank")
    @Expose
    Integer rank;
    @SerializedName("country")
    @Expose
    String country;
    @SerializedName("population")
    @Expose
    Long population;
    @SerializedName("flag")
    @Expose
    String flag;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
