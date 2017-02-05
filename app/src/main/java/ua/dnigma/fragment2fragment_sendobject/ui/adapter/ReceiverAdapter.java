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
import ua.dnigma.fragment2fragment_sendobject.model.PictureModel;

/**
 * Created by Даниил on 20.12.2016.
 */

public class ReceiverAdapter extends ArrayAdapter <PictureModel>{
    public ReceiverAdapter(Context context, List<PictureModel> pictureList) {
        super(context, 0, pictureList);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.receiver_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        final PictureModel pictureModel = getItem(position);
        viewHolder.title.setText(String.valueOf(pictureModel.getId()));
        viewHolder.image.setImageResource(pictureModel.getResource());
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), pictureModel.getResource(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }


    private class ViewHolder {
        public TextView title;
        public ImageView image;
        public ViewHolder (View view) {
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
