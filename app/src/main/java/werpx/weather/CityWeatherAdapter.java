package werpx.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CityWeatherAdapter extends Adapter<CityWeather> {

    public CityWeatherAdapter(Context context, ArrayList<CityWeather> cities) {
        super(context, 0, cities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        CityWeather cityWeather = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_city, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.city_name);
            holder.temp = (TextView) convertView.findViewById(R.id.city_temp);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(cityWeather.getName());
        holder.temp.setText(cityWeather.getTemp());

        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView temp;

    }
}