package com.example.xector.fragmenttest;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Contact {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Contact> list_contact = new ArrayList<Contact>();
        String contact_name;
        String contact_phone;
        Blob contact_image;
        int contact_img;

    public Contact(String contact_name, String contact_phone, int contact_img) {
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_img = contact_img;
    }


}
