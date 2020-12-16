package com.bavy.android_assignment_1;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bavy.android_assignment_1.DataModel.Creditcardinfo;

import java.util.List;

public class creditcardRecyclerviewadapter extends RecyclerView.Adapter<creditcardListviewholder> {
    List<Creditcardinfo> data = null;
    int putinselected;

    public creditcardRecyclerviewadapter(){
        new fillrecyclerviewadapterdata().execute();
    }

    @NonNull
    @Override
    public creditcardListviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_creditcardlist,parent,false);
        return new creditcardListviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull creditcardListviewholder holder, int position) {
    holder.creditcardinfo = data.get(position);
    holder.cardno.setText("The credit card number is :"+String.valueOf(holder.creditcardinfo.getCno()));
    holder.nameoncard.setText("The name on the card is :"+holder.creditcardinfo.getCname());
    holder.expirydate.setText("The expiry date on the card is :"+String.valueOf(holder.creditcardinfo.getExpdate()));
    holder.cvvno.setText("The expiry date on the card is :"+String.valueOf(holder.creditcardinfo.getCvvno()));
    }

    @Override
    public int getItemCount() {
        if(data.size()!=0){
        return data.size();
        }
        else {
            return 0; }
    }

    public class fillrecyclerviewadapterdata extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            data = login_screen.myDao.readAllCards();
            return null;
        }
    }
}
