package ua.dnigma.fragment2fragment_sendobject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Даниил on 14.12.2016.
 */

public class AlbumModel implements Parcelable{
    private int count;
    private List<PictureModel> pictures;
    private String date;

    public AlbumModel(int count, List<PictureModel> pictures, String date) {
        this.count = count;
        this.pictures = pictures;
        this.date = date;
    }

    protected AlbumModel(Parcel in) {
        count = in.readInt();
        pictures = in.createTypedArrayList(PictureModel.CREATOR);
//        in.readTypedList(pictures, PictureModel.CREATOR);
        date = in.readString();
    }

    public static final Creator<AlbumModel> CREATOR = new Creator<AlbumModel>() {
        @Override
        public AlbumModel createFromParcel(Parcel in) {
            return new AlbumModel(in);
        }

        @Override
        public AlbumModel[] newArray(int size) {
            return new AlbumModel[size];
        }
    };

    public int getCount() {
        return count;
    }

    public List<PictureModel> getPictures() {
        return pictures;
    }

    public String getDate() {
        return date;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPictures(List<PictureModel> pictures) {
        this.pictures = pictures;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(count);
        parcel.writeTypedList(pictures);
        parcel.writeString(date);

    }
}
