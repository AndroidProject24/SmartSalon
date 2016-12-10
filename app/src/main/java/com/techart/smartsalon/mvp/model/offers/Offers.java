package com.techart.smartsalon.mvp.model.offers;

/**
 * Created by Toan.IT
 * Date: 18/06/2016
 */

public class Offers {
    private String id;
    private String shop_id;
    private String title;
    private String expiration_date;
    private String content;
    private String created_at;
    private String updated_at;
    private String is_read;
    private String CreatedText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public String getCreatedText() {
        return CreatedText;
    }

    public void setCreatedText(String CreatedText) {
        this.CreatedText = CreatedText;
    }
}
