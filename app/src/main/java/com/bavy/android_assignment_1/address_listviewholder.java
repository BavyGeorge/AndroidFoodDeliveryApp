package com.bavy.android_assignment_1;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bavy.android_assignment_1.DataModel.Address;

public class address_listviewholder extends RecyclerView.ViewHolder {

    Address address;
    TextView tv1,tv2,tv3,tv4;
    CardView cardView1;

    public address_listviewholder(@NonNull final View itemView) {
        super(itemView);
        tv1= itemView.findViewById(R.id.atextview1);
        tv2= itemView.findViewById(R.id.atextview2);
        tv3= itemView.findViewById(R.id.atextview3);
        tv4= itemView.findViewById(R.id.atextview4);

        cardView1=itemView.findViewById(R.id.cardview2);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(),addressinformation.class);
                intent.putExtra(login_screen.ADDRESSOBJECT,address);
                itemView.getContext().startActivity(intent);
            }
        });
    }

}
