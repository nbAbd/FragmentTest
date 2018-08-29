package com.example.xector.fragmenttest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Objects;

public class ContactFragment extends Fragment{

    String nums[] = {"1","2","3"};
    private Cursor mCursor;
    private ArrayList<Contact> mContactList;
    private int counter;
    private Handler updateBarHandler;
    private ProgressDialog pDialog;
    ContentResolver mContenResolver;
    private ContentResolver contentResolver;
    RecyclerView rv;
    MyItemRecyclerViewAdapter recyclerViewAdapter;
    public ContactFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        rv = view.findViewById(R.id.rvlist);
        updateBarHandler = new Handler();
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Read contact...");
        pDialog.setCancelable(false);
        pDialog.show();
        MyItemRecyclerViewAdapter mAdapter = new MyItemRecyclerViewAdapter(new ArrayList<Contact>());
        rv.setAdapter(mAdapter);
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                getContact();

//                MyItemRecyclerViewAdapter recyclerViewAdapter = new MyItemRecyclerViewAdapter(mContactList, getContext());
//                rv.setAdapter(recyclerViewAdapter);
            }
        }).start();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        mContenResolver = Objects.requireNonNull(getActivity()).getContentResolver();
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
                       // pDialog.show();
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
                        mContactList.add(new Contact(cont_name,phoneNum,1));
                    }
                    phoneCursor.close();
                   // mCursor.close();

                   // mContactList.add(new Contact(listInfo.get(0),listInfo.get(1),1));
                }

            }
            new Runnable(){
                @Override
                public void run() {
                    rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    rv.setAdapter(new MyItemRecyclerViewAdapter(mContactList));
                }
            };

            // Dismiss the progressbar after 500 millisecondds
            updateBarHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    pDialog.cancel();
                }
            }, 500);
        }
    }



}