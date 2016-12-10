package com.techart.smartsalon.mvp.model.appointment;

/**
 * Created by Toan.IT
 * Date: 12/06/2016
 */
public class Appointment {
    private String id;
    private String date;
    private String slot;
    private String status;
    private ServicesBean services;
    private ProviderBean provider;
    private String TypeText;
    private String StatusText;
    private String LastUpdateAgo;
    private ListStatusBean ListStatus;
    private String InvoiceNumber;
    private String InvoiceStatusText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ServicesBean getServices() {
        return services;
    }

    public void setServices(ServicesBean services) {
        this.services = services;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
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

    public Object getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String InvoiceNumber) {
        this.InvoiceNumber = InvoiceNumber;
    }

    public Object getInvoiceStatusText() {
        return InvoiceStatusText;
    }

    public void setInvoiceStatusText(String InvoiceStatusText) {
        this.InvoiceStatusText = InvoiceStatusText;
    }

    public static class ServicesBean {
        private String id;
        private String name;
        private String duration;
        private String price;
        private String currency;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }

    public static class ProviderBean {
        private String id;
        private String last_name;
        private String first_name;
        private String phone;
        private String avatar;
        private String AvatarFullPath;
        private String LastUpdateAgo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatarFullPath() {
            return AvatarFullPath;
        }

        public void setAvatarFullPath(String AvatarFullPath) {
            this.AvatarFullPath = AvatarFullPath;
        }

        public String getLastUpdateAgo() {
            return LastUpdateAgo;
        }

        public void setLastUpdateAgo(String LastUpdateAgo) {
            this.LastUpdateAgo = LastUpdateAgo;
        }
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
