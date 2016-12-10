package com.techart.smartsalon.mvp.model.contact;

/**
 * Created by Toan.IT
 * Date: 20/06/2016
 */

public class Freedback {
    private String customer_id;
    private String subject;
    private String content;
    private String updated_at;
    private String created_at;
    private int id;
    private String CreatedText;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
