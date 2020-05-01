package com.example.earthquake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter( Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }
    private static final String LOCATION_SEPEARTOR = "of";


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }
        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView)listItemView.findViewById(R.id.mag);
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitudeView.setText(formattedMagnitude);

        /**
         * @color of magnintude
         */
        GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        /**
         * Splition original location into
         * Offsset_location and
         * primarty Location
         */
        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;
        if (originalLocation.contains(LOCATION_SEPEARTOR))
        {
            String []parts = originalLocation.split(LOCATION_SEPEARTOR);
            locationOffset=parts[0]+LOCATION_SEPEARTOR;
            primaryLocation=parts[1];
        }else {
            locationOffset= getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView offsetView = (TextView)listItemView.findViewById(R.id.location_offset);
        offsetView.setText(locationOffset);

        TextView primaryView = (TextView)listItemView.findViewById(R.id.primary_location);
        primaryView.setText(primaryLocation);
        /**
         * @end
         */

        Date dateObject = new Date(currentEarthquake.getTimeInMilliSecond());
        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView)listItemView.findViewById(R.id.time);
        String  formattedTime  = formatTime(dateObject);
        timeView.setText(formattedTime);
        return  listItemView;
    }
    private String formatDate(Date dateObject)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return  dateFormat.format(dateObject);
    }
    private String formatTime(Date dateObject)
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude)
    {
        DecimalFormat magFormatter= new DecimalFormat("0.0");
        return magFormatter.format(magnitude);
    }

    // magnitude color
    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResouurceId;
        int magnitudeFloor = (int)Math.floor(magnitude);
        switch (magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudeColorResouurceId=R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResouurceId=R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResouurceId=R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResouurceId=R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResouurceId=R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResouurceId=R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResouurceId=R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResouurceId=R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResouurceId=R.color.magnitude9;
                break;
            default:
                magnitudeColorResouurceId=R.color.magnitude10;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResouurceId);
    }



}
