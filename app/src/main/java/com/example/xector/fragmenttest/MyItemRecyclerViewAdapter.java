package com.example.xector.fragmenttest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mContactList;
    MyItemRecyclerViewAdapter(List<Contact> contacts) {
        mContactList = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.cont_num.setText(mContactList.get(position).contact_phone);
        holder.cont_name.setText(mContactList.get(position).contact_name);
        holder.cont_img.setImageResource(R.drawable.du);
        holder.mCont = mContactList.get(position);

    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       // final View mView;
        final ImageView cont_img;
        final TextView cont_name;
        final TextView cont_num;
        Contact mCont;
        ViewHolder(View view) {
            super(view);
            //mView = view;
            cont_img = (ImageView) view.findViewById(R.id.contact_img);
            cont_name = (TextView) view.findViewById(R.id.contact_name);
            cont_num = (TextView) view.findViewById(R.id.contact_phone_number);
        }

    }
}
