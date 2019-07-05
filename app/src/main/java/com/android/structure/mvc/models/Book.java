package com.android.structure.mvc.models;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("title")
    public Object title;
    @SerializedName("type")
    public Object type;
    @SerializedName("date")
    public Object date;
    @SerializedName("format")
    public Object format;
    @SerializedName("description")
    public Object description;
    @SerializedName("subject")
    public Object subject;
    @SerializedName("relation")
    public Object relation;
    @SerializedName("identifier")
    public Object identifier;
    @SerializedName("language")
    public Object language;
}
