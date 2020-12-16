package com.bavy.android_assignment_1;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bavy.android_assignment_1.DataModel.Creditcardinfo;
import com.bavy.android_assignment_1.DataModel.Userinfo;

public class creditcardListviewholder extends RecyclerView.ViewHolder {

    Creditcardinfo creditcardinfo;
    TextView cardno,nameoncard,expirydate,cvvno;
    CardView cardView;

    public creditcardListviewholder(@NonNull final View itemView) {
        super(itemView);
        cardno = itemView.findViewById(R.id.ctextview1);
        nameoncard= itemView.findViewById(R.id.ctextview2);
        expirydate=itemView.findViewById(R.id.ctextview3);
        cvvno=itemView.findViewById(R.id.ctextview4);

        cardView=itemView.findViewById(R.id.cardview1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(itemView.getContext(),Creditcardinformation.class);
            intent.putExtra(login_screen.CARDOBJECT,creditcardinfo);
            itemView.getContext().startActivity(intent);
//            Toast.makeText(itemView.getContext(),"Card has been clicked",Toast.LENGTH_LONG).show();
            }
        });
    }
}
