package com.example.megabest.data.dataSource.RemoteDataSource.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieTrailer implements Parcelable {
    private String id ;
    private String key ;
    private String name ;

    public MovieTrailer(String id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }

    public MovieTrailer() {
    }

    protected MovieTrailer(Parcel in) {
        id = in.readString();
        key = in.readString();
        name = in.readString();
    }

    public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
        @Override
        public MovieTrailer createFromParcel(Parcel in) {
            return new MovieTrailer(in);
        }

        @Override
        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(key);
        parcel.writeString(name);
    }
}
