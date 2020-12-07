package com.example.cafebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class FoodTableConfirmation extends AppCompatActivity {

    ImageView ivImage1;
    TextView tvOrderInfo, tvDetails;
    Button btnMap2, btnGetDetails;
    EditText etQuantity;
    DecimalFormat f = new DecimalFormat("0.00");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_table_confirmation);


        ivImage1 = findViewById(R.id.ivImage1);
        tvOrderInfo = findViewById(R.id.tvOrderInfo);
        tvDetails = findViewById(R.id.tvDetails);
        btnGetDetails = findViewById(R.id.btnGetDetails);
        etQuantity = findViewById(R.id.etQuantity);
        btnMap2 = findViewById(R.id.btnMap2);

        final String date = getIntent().getStringExtra("date");
        final String time = getIntent().getStringExtra("time");
        final int tableNumber = getIntent().getIntExtra("tableNumber", 0);
        final String foodPrice = getIntent().getStringExtra("foodPrice");
        final String foodName = getIntent().getStringExtra("foodName");
        final int Image = getIntent().getIntExtra("image", 0);


        ivImage1.setImageResource(Image);
        tvOrderInfo.setText("You selected : " + foodName);

        btnGetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Quantity = etQuantity.getText().toString();

                if (Quantity.isEmpty()) {
                    Toast.makeText(FoodTableConfirmation.this, "Please enter the Quantity", Toast.LENGTH_SHORT).show();
                } else {

                    final double totalPrice = (Double.parseDouble(foodPrice) +
                            Double.parseDouble(foodPrice) * 0.055 +
                            Double.parseDouble(foodPrice) * 0.055) * Integer.parseInt(Quantity);


                    String Details = "You selected : " + foodName + "\n" +
                            "Price        : " + foodPrice + "\n" +
                            "Quantity : " + Quantity + "\n" +
                            "Total Price(including 11% gst)  : " + f.format(totalPrice) + "\n" +
                            "Date         :" + date + "\n" +
                            "Time         :" + time + "\n" +
                            "Table Number :" + tableNumber + "\n";

                    tvDetails.setText(Details);

                }
            }
        });


        btnMap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String address = "MKSSS's Cummins College of Engineering,Pune";
               // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir//cummins+college/"));
                //startActivity(intent);
                String address = "Cummins, COE, PUNE";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address) );
                startActivity(intent);
            }
        });



    }
}
