//package com.example.android.justjava;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjavaapp;



import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 1;
    public void submitOrder(View view) {
        EditText customer_name_et = (EditText)findViewById(R.id.customer_name);
        String customer_name = customer_name_et.getText().toString();


        CheckBox whipped_cream = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolate_topping = (CheckBox)findViewById(R.id.chocolate_checkbox);

        boolean hasWhippedCream = whipped_cream.isChecked();
        boolean hasChocolateTopping = chocolate_topping.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolateTopping);

        String priceMessage = createOrderSummary(customer_name,price, hasWhippedCream, hasChocolateTopping);


        //display(quantity);

        //displayPrice(quantity*30);
        //disPlayMessage(priceMessage);
        sendEmail(priceMessage);

    }

    public void increase(View view){
        quantity += 1;
        display(quantity);
    }

    public void decrease(View view){
        quantity += -1;
        display(quantity);
    }

    private void sendEmail(String message){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        String mail_data = "mailto:ruhshan.abir@bkash.com"+
                "?cc=" + "" +
                "&subject=Sample Sublect"+
                "&body="+message;
        emailIntent.setData(Uri.parse(mail_data));
        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolateTopping){
        int base_price = 5;

        if(hasWhippedCream)
            base_price+=1;

        if(hasChocolateTopping)
            base_price+=2;

        return quantity * base_price;
    }

    private String createOrderSummary(String customer_name,int price, boolean whipped_cream, boolean chocolate_topping){
        String priceMessage = "Name: "+customer_name+"\n";
        priceMessage = priceMessage + "Added Whipped cream? " +whipped_cream +"\n";
        priceMessage = priceMessage + "Added Chocolate Topping? " +chocolate_topping +"\n";
        priceMessage = priceMessage + "Quantity: "+quantity+"\n";
        priceMessage = priceMessage + "Total : $"+price;
        priceMessage = priceMessage + "\nThank You!";

        return priceMessage;

    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    private void disPlayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}