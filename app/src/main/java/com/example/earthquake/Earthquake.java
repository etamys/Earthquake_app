package com.example.earthquake;


import androidx.appcompat.app.AppCompatActivity;

public class Earthquake extends AppCompatActivity {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliSecond;

    public Earthquake(double magnitude,String location,long date)
    {
        mMagnitude=magnitude;
        mLocation=location;
        mTimeInMilliSecond=date;
    }
    public double getMagnitude() { return mMagnitude; }
    public String getLocation(){return mLocation;}
    public long getTimeInMilliSecond(){return mTimeInMilliSecond;}

}
