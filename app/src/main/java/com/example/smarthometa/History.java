package com.example.smarthometa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {
    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("status")
    @Expose
    private String status;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
