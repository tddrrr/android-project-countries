package com.example.proiect_bd_tema;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//import java.sql.Struct;
@Entity(tableName = "countries")
public class Country implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String capital;
    private String language;
    private String currency;
    private String systemOfGovernment;
    private String population;
    private String flagURL;

    public Country(String name, String capital, String language, String currency, String systemOfGovernment, String population, String flagURL) {
        this.name = name;
        this.capital = capital;
        this.language = language;
        this.currency = currency;
        this.systemOfGovernment = systemOfGovernment;
        this.population = population;
        this.flagURL = flagURL;
    }

    protected Country(Parcel in) {
        id = in.readInt();
        name = in.readString();
        capital = in.readString();
        language = in.readString();
        currency = in.readString();
        systemOfGovernment = in.readString();
        population = in.readString();
        flagURL = in.readString();
    }

    @Override
    public String toString() {
        return "Country{" +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", language='" + language + '\'' +
                ", currency='" + currency + '\'' +
                ", systemOfGovernment='" + systemOfGovernment + '\'' + ", population='" + population + '\'' +
                '}';
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSystemOfGovernment() {
        return systemOfGovernment;
    }

    public void setSystemOfGovernment(String systemOfGovernment) {
        this.systemOfGovernment = systemOfGovernment;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public void setFlagURL(String flagURL) {
        this.flagURL = flagURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(capital);
        dest.writeString(language);
        dest.writeString(currency);
        dest.writeString(systemOfGovernment);
        dest.writeString(population);
        dest.writeString(flagURL);
    }
}
