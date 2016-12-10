package com.techart.smartsalon.mvp.view.contact;

import com.techart.smartsalon.mvp.model.contact.Contact;
import com.techart.smartsalon.mvp.model.contact.Freedback;
import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface ContactView extends BaseView {
    void getContactList(List<Contact> contactList);
    void sendFreedback(Freedback freedback);
}
