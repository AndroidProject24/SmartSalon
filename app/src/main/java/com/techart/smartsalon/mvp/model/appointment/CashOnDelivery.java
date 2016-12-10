package com.techart.smartsalon.mvp.model.appointment;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class CashOnDelivery {
    private String id;
    private String price;
    private String name;
    private String duration;

    @Override
    public String toString() {
        return "CashOnDelivery{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", color_tag='" + color_tag + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

    private String color_tag;
    private String description;
    private String currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getColor_tag() {
        return color_tag;
    }

    public void setColor_tag(String color_tag) {
        this.color_tag = color_tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
