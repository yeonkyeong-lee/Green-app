package com.yklee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {

    TextView idView;
    EditText nameBox;
    EditText bNameBox;
    EditText averageBox;
    EditText firstDayBox;
    EditText lastDayBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplant);

        nameBox = (EditText) findViewById(R.id.addPlant_nameInput);
        bNameBox = (EditText) findViewById(R.id.addPlant_bNameInput);
        averageBox = (EditText) findViewById(R.id.addPlant_averageDay);
        firstDayBox = (EditText) findViewById(R.id.addPlant_firstDay);
        lastDayBox = (EditText) findViewById(R.id.addPlant_lastWaterDay);
    }

//    public void newProduct(View view){
//        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
//
//        int quantity = Integer.parseInt(quantityBox.getText().toString());
//        PlantItem plant = new PlantItem(productBox.getText().toString(), quantity);
//
//        dbHandler.addProduct(plant);
//        productBox.setText("");
//        quantityBox.setText("");
//    }
//
//    public void lookupProduct(View view){
//        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
//
//        Product product = dbHandler.findProduct(productBox.getText().toString());
//
//        if(product != null){
//            idView.setText(String.valueOf(product.getID()));
//
//            quantityBox.setText(String.valueOf(product.getQuantity()));
//        } else{
//            idView.setText("No Match Found");
//
//        }
//    }
//
//    public void removeProduct (View view){
//        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
//
//        boolean result = dbHandler.deleteProduct(productBox.getText().toString());
//
//        if(result){
//            idView.setText("Record Deleted");
//            productBox.setText("");
//            quantityBox.setText("");
//        }
//        else
//            idView.setText("No Match Found");
//    }
}

