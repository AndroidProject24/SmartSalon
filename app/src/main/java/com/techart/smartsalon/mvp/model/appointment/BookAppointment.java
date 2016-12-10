package com.techart.smartsalon.mvp.model.appointment;

/**
 * Created by Toan.IT
 * Date: 16/06/2016
 */

public class BookAppointment {
    private String provider_id;
    private String service_id;
    private String date;
    private String slot;
    private int type;
    private String prepaid_id;
    private int status;
    private String price;
    private String comment;
    private String updated_at;
    private String created_at;
    private int id;
    private String TypeText;
    private String StatusText;
    private String LastUpdateAgo;
    private ListStatusBean ListStatus;
    private String InvoiceNumber;
    private String InvoiceStatusText;

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPrepaid_id() {
        return prepaid_id;
    }

    public void setPrepaid_id(String prepaid_id) {
        this.prepaid_id = prepaid_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getTypeText() {
        return TypeText;
    }

    public void setTypeText(String TypeText) {
        this.TypeText = TypeText;
    }

    public String getStatusText() {
        return StatusText;
    }

    public void setStatusText(String StatusText) {
        this.StatusText = StatusText;
    }

    public String getLastUpdateAgo() {
        return LastUpdateAgo;
    }

    public void setLastUpdateAgo(String LastUpdateAgo) {
        this.LastUpdateAgo = LastUpdateAgo;
    }

    public ListStatusBean getListStatus() {
        return ListStatus;
    }

    public void setListStatus(ListStatusBean ListStatus) {
        this.ListStatus = ListStatus;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String InvoiceNumber) {
        this.InvoiceNumber = InvoiceNumber;
    }

    public String getInvoiceStatusText() {
        return InvoiceStatusText;
    }

    public void setInvoiceStatusText(String InvoiceStatusText) {
        this.InvoiceStatusText = InvoiceStatusText;
    }

    public static class ListStatusBean {
        /**
         * value : 1
         * text : Not Checked In
         */

        private PendingBean pending;
        /**
         * value : 2
         * text : Checked In
         */

        private CheckedInBean checked_in;
        /**
         * value : -1
         * text : Cancelled
         */

        private CancelledBean cancelled;

        public PendingBean getPending() {
            return pending;
        }

        public void setPending(PendingBean pending) {
            this.pending = pending;
        }

        public CheckedInBean getChecked_in() {
            return checked_in;
        }

        public void setChecked_in(CheckedInBean checked_in) {
            this.checked_in = checked_in;
        }

        public CancelledBean getCancelled() {
            return cancelled;
        }

        public void setCancelled(CancelledBean cancelled) {
            this.cancelled = cancelled;
        }

        public static class PendingBean {
            private int value;
            private String text;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        public static class CheckedInBean {
            private int value;
            private String text;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }

        public static class CancelledBean {
            private int value;
            private String text;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
