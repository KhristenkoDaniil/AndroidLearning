package ua.dnigma.fragment2fragment_sendobject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Даниил on 14.12.2016.
 */

public class PictureModel implements Parcelable{
    private int id;
    private int resource;

    public PictureModel(int id, int resource) {
        this.id = id;
        this.resource = resource;
    }

    protected PictureModel(Parcel in) {
        id = in.readInt();
        resource = in.readInt();
    }

    public static final Creator<PictureModel> CREATOR = new Creator<PictureModel>() {
        @Override
        public PictureModel createFromParcel(Parcel in) {
            return new PictureModel(in);
        }

        @Override
        public PictureModel[] newArray(int size) {
            return new PictureModel[size];
        }
    };

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(resource);

    }
}
