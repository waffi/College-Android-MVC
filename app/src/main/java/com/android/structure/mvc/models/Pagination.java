package com.android.structure.mvc.models;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("maxPageableSet")
    public String maxPageableSet;
    @SerializedName("numFound")
    public int numFound;
    @SerializedName("query")
    public String query;
    @SerializedName("limit")
    public int limit;
    @SerializedName("start")
    public int start;
}
