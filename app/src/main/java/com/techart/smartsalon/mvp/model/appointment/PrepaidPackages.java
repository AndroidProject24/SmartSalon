package com.techart.smartsalon.mvp.model.appointment;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class PrepaidPackages {
    private String service_id;
    private String service_name;
    private String prepaid_id;
    private String buy;
    private String free;
    private String used;
    private String unit_price;
    private String total;
    private String created_at;
    private String invoice_id;
    private String invoice_status;
    private String paid_date;
    private String CreatedText;
    private String InvoiceNumber;
    private String StatusText;
    private String PaidDateText;

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getPrepaid_id() {
        return prepaid_id;
    }

    public void setPrepaid_id(String prepaid_id) {
        this.prepaid_id = prepaid_id;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }

    public String getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(String paid_date) {
        this.paid_date = paid_date;
    }

    public String getCreatedText() {
        return CreatedText;
    }

    public void setCreatedText(String CreatedText) {
        this.CreatedText = CreatedText;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String InvoiceNumber) {
        this.InvoiceNumber = InvoiceNumber;
    }

    public String getStatusText() {
        return StatusText;
    }

    public void setStatusText(String StatusText) {
        this.StatusText = StatusText;
    }

    public String getPaidDateText() {
        return PaidDateText;
    }

    public void setPaidDateText(String PaidDateText) {
        this.PaidDateText = PaidDateText;
    }
}