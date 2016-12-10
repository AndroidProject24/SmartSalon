package com.techart.smartsalon.mvp.model.offers;

/**
 * Created by Toan.IT
 * Date: 18/06/2016
 */

public class OffersDetails {
    private String user_id;
    private String offer_id;
    private String updated_at;
    private String created_at;
    private int id;
    private String CreatedText;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedText() {
        return CreatedText;
    }

    public void setCreatedText(String CreatedText) {
        this.CreatedText = CreatedText;
    }
}
