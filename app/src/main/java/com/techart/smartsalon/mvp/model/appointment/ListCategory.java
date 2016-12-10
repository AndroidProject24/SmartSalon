package com.techart.smartsalon.mvp.model.appointment;

/**
 * Created by Toan.IT on 12/10/16.
 * Email: huynhvantoan.itc@gmail.com
 */

public class ListCategory {
    private String category_name;
    private String category_id;
    private String Joined;
    private String LastUpdate;
    private String LastLogin;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getJoined() {
        return Joined;
    }

    public void setJoined(String Joined) {
        this.Joined = Joined;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String LastUpdate) {
        this.LastUpdate = LastUpdate;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(String LastLogin) {
        this.LastLogin = LastLogin;
    }
}
