package com.android.structure.mvc.models;

import com.google.gson.annotations.SerializedName;

public class Collection {

    @SerializedName("created")
    public String created;
    @SerializedName("modified")
    public String modified;
    @SerializedName("baseUrl")
    public String baseUrl;
    @SerializedName("collectionUrn")
    public String collectionUrn;
    @SerializedName("contactDepartment")
    public String contactDepartment;
    @SerializedName("contactName")
    public String contactName;
    @SerializedName("systemId")
    public int systemId;
    @SerializedName("setDescription")
    public String setDescription;
    @SerializedName("setName")
    public String setName;
    @SerializedName("setSpec")
    public String setSpec;
    @SerializedName("thumbnailUrn")
    public String thumbnailUrn;
}
