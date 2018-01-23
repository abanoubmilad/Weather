package werpx.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import werpx.weather.R;
import werpx.weather.data.Forecast;

public class ForecastAdapter extends Adapter<Forecast> {

    private final SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public ForecastAdapter(Context context, ArrayList<Forecast> forecastDays) {
        super(context, 0, forecastDays);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Forecast forecastDay = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_forecast, parent, false);
            holder = new ViewHolder();
            holder.timestamp = (TextView) convertView.findViewById(R.id.timestamp);
            holder.temp = (TextView) convertView.findViewById(R.id.day_temp);
            holder.min = (TextView) convertView.findViewById(R.id.min_temp);
            holder.max = (TextView) convertView.findViewById(R.id.max_temp);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.timestamp.setText(dateFormater.format(new Date(forecastDay.getTimeStamp()*1000)));
        holder.temp.setText(forecastDay.getDayTemp()+"");
        holder.min.setText(forecastDay.getMinTemp()+"");
        holder.max.setText(forecastDay.getMaxTemp()+"");

        return convertView;
    }

    private static class ViewHolder {
        TextView timestamp;
        TextView temp;
        TextView min;
        TextView max;

    }
}