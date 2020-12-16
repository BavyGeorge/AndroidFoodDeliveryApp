package com.bavy.android_assignment_1;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bavy.android_assignment_1.DataModel.Address;

import java.util.List;

public class address_recyclerviewadapter extends RecyclerView.Adapter<address_listviewholder> {
    List<Address> data = null;

    public address_recyclerviewadapter(){
        new fillrecyclerview().execute();
    }

    @NonNull
    @Override
    public address_listviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_addresslist,parent,false);
        return new address_listviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull address_listviewholder holder, int position) {
    holder.address = data.get(position);
    holder.tv1.setText("The House Number is: "+String.valueOf(holder.address.getAddress_sa_no()));
    holder.tv2.setText("The street name is: "+(holder.address.getAddress_streetname()));
    holder.tv3.setText("The City or Town is: "+holder.address.getAddress_citytown());
    holder.tv4.setText("The Post Code is: "+String.valueOf(holder.address.getAddress_postcode()));
    }

    @Override
    public int getItemCount() {
        if(data.size()!=0){
            return data.size();
        }
        else {
            return 0; }
    }

    public class fillrecyclerview extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            data=login_screen.myDao.readalladdresses();
            return null;
        }
    }
}
