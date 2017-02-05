package ua.dnigma.fragment2fragment_sendobject.model;

import android.os.Parcel;
import android.os.Parcelable;

import ua.dnigma.fragment2fragment_sendobject.enums.GenderType;

/**
 * Created by Даниил on 14.12.2016.
 */

public class UserProfileModel implements Parcelable{
    private long userID;
    private String name;
    private String surName;
    private int age;
    private GenderType gender;
    private String avatar;
    private AlbumModel albom;

    public UserProfileModel(long userID, String name, String surName, GenderType gender, String avatar) {
        this.userID = userID;
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.avatar = avatar;
    }

    protected UserProfileModel(Parcel in) {
        userID = in.readLong();
        name = in.readString();
        surName = in.readString();
        age = in.readInt();
        gender = GenderType.valueOf(in.readString());
        avatar = in.readString();
        albom = in.readParcelable(AlbumModel.class.getClassLoader());
    }

    public static final Creator<UserProfileModel> CREATOR = new Creator<UserProfileModel>() {
        @Override
        public UserProfileModel createFromParcel(Parcel in) {
            return new UserProfileModel(in);
        }

        @Override
        public UserProfileModel[] newArray(int size) {
            return new UserProfileModel[size];
        }
    };

    public long getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }


    public UserProfileModel(GenderType gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public GenderType getGender() {
        return gender;
    }

    public AlbumModel getAlbom() {
        return albom;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setAge(int age) throws NumberFormatException {
        if(age <= 120 && age > 0) {
        this.age = age;
        }else {
            throw new NumberFormatException();
        }

    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setAlbom(AlbumModel albom) {
        this.albom = albom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(userID);
        parcel.writeString(name);
        parcel.writeString(surName);
        parcel.writeInt(age);
        parcel.writeString(gender.name());
        parcel.writeString(avatar);
        parcel.writeParcelable(albom, 1);

    }
}
