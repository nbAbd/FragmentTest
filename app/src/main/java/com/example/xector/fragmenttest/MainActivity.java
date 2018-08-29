package com.example.xector.fragmenttest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    // list of contacts
    List<Contact> mContactList;

    //Cursor
    Cursor mCursor;

    private ProgressDialog pDialog;

    private Handler updateBarHandler;

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new ContactFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.headlines_fragment,fragment).commit();


/*
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Reading contacts...");
        pDialog.setCancelable(false);
        pDialog.show();

        updateBarHandler = new Handler();

        // Reading contacts will take more time that's why should run it in other thread

        new Thread(new Runnable() {
            @Override
            public void run() {
           getContact();
            }
        }).start();
    }


   void getContact(){
       mContactList = new ArrayList<>();
       String phone_number;
       String name;

       Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
       String _ID = ContactsContract.Contacts._ID;
       String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
       String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

       // there is used CommonDataKinds
       Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
       String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
       String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

       ArrayList<String> listInfo = new ArrayList<>();
       ContentResolver mContenResolver = getContentResolver();
       mCursor = mContenResolver.query(CONTENT_URI,null,null,null,null);

       // iterate every contact in the phone

       assert mCursor != null;
       if(mCursor.getCount() >0){
             counter = 0;
           while(mCursor.moveToNext()){
            updateBarHandler.post(new Runnable() {
                @Override
                public void run() {
                    pDialog.setMessage("Reading contact : " +counter++ +"/"+mCursor.getCount());
                }
            });
            String cont_name = mCursor.getString(mCursor.getColumnIndex(DISPLAY_NAME));
            int hasPhoneNumber = Integer.parseInt(mCursor.getString(mCursor.getColumnIndex(HAS_PHONE_NUMBER)));

            if(hasPhoneNumber>0){
                //String cont_phone = mCursor.getString(mCursor.getColumnIndex())
                listInfo.add(0,"Name:"+cont_name);

                @SuppressLint("Recycle")
                Cursor phoneCursor = mContenResolver.query(PhoneCONTENT_URI,null,Phone_CONTACT_ID + "= ?",null,null);
                assert phoneCursor != null;
                while (phoneCursor.moveToNext()){
                    String phoneNum = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                    listInfo.add(1,"\nphone:"+phoneNum);
                }
                phoneCursor.close();

                mContactList.add(new Contact(listInfo.get(0),listInfo.get(1),1));
            }

       }
    }*/
    }
}
