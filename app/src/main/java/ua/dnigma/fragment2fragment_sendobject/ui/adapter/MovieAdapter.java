package ua.dnigma.fragment2fragment_sendobject.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ua.dnigma.fragment2fragment_sendobject.R;
import ua.dnigma.fragment2fragment_sendobject.model.MovieModel;
import ua.dnigma.fragment2fragment_sendobject.model.PictureModel;

/**
 * Created by Даниил on 10.01.2017.
 */

public class MovieAdapter extends ArrayAdapter <MovieModel> {
    public MovieAdapter(Context context, List<MovieModel> movieModels) {
        super(context, 0, movieModels);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.movie_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        MovieAdapter.ViewHolder viewHolder = (MovieAdapter.ViewHolder) convertView.getTag();
        MovieModel movieModel = getItem(position);
        viewHolder.title.setText(String.valueOf(movieModel.getTitlle()));
        viewHolder.url.setText(String.valueOf(movieModel.getUrl()));
        viewHolder.dataStart.setText(String.valueOf(movieModel.getDataStart()));
        viewHolder.dataEnd.setText(String.valueOf(movieModel.getDataEnd()));

        return convertView;
    }


    private class ViewHolder {
        public TextView title;
        public TextView url;
        public TextView dataStart;
        public TextView dataEnd;
        public ViewHolder (View view) {
            title = (TextView) view.findViewById(R.id.title);
            url = (TextView) view.findViewById(R.id.url);
            dataStart = (TextView) view.findViewById(R.id.data_start);
            dataEnd = (TextView) view.findViewById(R.id.data_end);


        }
    }
}
