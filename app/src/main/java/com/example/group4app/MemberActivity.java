package com.example.group4app;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;


public class MemberActivity extends AppCompatActivity {
    boolean[] checker = new boolean[9];
    DatabaseHelper db;

    String memShipID = "";
    String memID = "";
    long pnr = 0;
    String fName = "";
    String lName = "";
    String mail = "";
    int number = 0;
    String address = "";
    int postalCode = 0;
    String city = "";
    String password = "";

    int price = 0;
    int length = 0;
    String startDate = "";
    String endDate = "";
    String expDate = "";
    String payID = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber);
        db = new DatabaseHelper(this);
        Cursor rs = db.getAllFromTable("Membership");
        Log.d("DB DATA NUM", ""+rs.getCount());
        Arrays.fill(checker,Boolean.TRUE);

        memShipID = getIntent().getStringExtra("memshipID");
        price = getIntent().getIntExtra("price",249);
        length = getIntent().getIntExtra("length",3);
        final TextView pnrT = findViewById(R.id.pnrT);
        final TextView fnameT = findViewById(R.id.fnameT);
        final TextView lnameT = findViewById(R.id.lnameT);
        final TextView mejlT = findViewById(R.id.mejlT);
        final TextView numberT = findViewById(R.id.numberT);
        final TextView addressT = findViewById(R.id.addressT);
        final TextView postalCodeT = findViewById(R.id.postalCodeT);
        final TextView cityT = findViewById(R.id.cityT);
        final TextView passwordT = findViewById(R.id.passT);

        final EditText pnrF = findViewById(R.id.pnrF);
        final EditText fnameF = findViewById(R.id.fnameF);
        final EditText lnameF = findViewById(R.id.lnameF);
        final EditText mejlF = findViewById(R.id.mejlF);
        final EditText numberF = findViewById(R.id.numberF);
        final EditText addressF = findViewById(R.id.addressF);
        final EditText postalCodeF = findViewById(R.id.postalCodeF);
        final EditText cityF = findViewById(R.id.cityF);
        final EditText passwordF = findViewById(R.id.passF);

        Button backBtn = findViewById(R.id.backBtn2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MemShipActivity.class);
                startActivity(i);
            }
        });



        Button bliMedlembtn = findViewById(R.id.bliMedlemBtn);

        bliMedlembtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (!pnrF.getText().equals("") && pnrF.getText().length() == 12 && String.valueOf(pnrF.getText()).matches("[0-9]+")) {
                    pnrT.setTextColor(Color.rgb(0, 0, 0));
                    pnr = Long.parseLong(String.valueOf(pnrF.getText()));
                    checker[0] = true;
                } else {
                    checker[0] = false;
                    pnrT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (!fnameF.getText().equals("")) {
                    fnameT.setTextColor(Color.rgb(0, 0, 0));
                    fName = String.valueOf(fnameF.getText());
                    checker[1] = true;
                } else {
                    checker[1] = false;
                    fnameT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (!lnameF.getText().equals("")) {
                    lnameT.setTextColor(Color.rgb(0, 0, 0));
                    lName = String.valueOf(lnameF.getText());
                    checker[2] = true;
                } else {
                    checker[2] = false;
                    lnameT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (!mejlF.getText().equals("") && isValid(String.valueOf(mejlF.getText()))) {
                    mejlT.setTextColor(Color.rgb(0, 0, 0));
                    mail = String.valueOf(mejlF.getText());
                    checker[3] = true;
                } else {
                    checker[3] = false;
                    mejlT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (!numberF.getText().equals("") && String.valueOf(numberF.getText()).matches("[0-9]+") && numberF.getText().length() <= 10) {
                    numberT.setTextColor(Color.rgb(0, 0, 0));
                    number = Integer.parseInt(String.valueOf(numberF.getText()));
                    checker[4] = true;
                } else {
                    checker[4] = false;
                    numberT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (!addressF.getText().equals("")) {
                    addressT.setTextColor(Color.rgb(0, 0, 0));
                    address = String.valueOf(addressF.getText());
                    checker[5] = true;
                } else {
                    checker[5] = false;
                    addressT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (!postalCodeF.getText().equals("") && String.valueOf(postalCodeF.getText()).matches("[0-9]+") && postalCodeF.getText().length() == 5) {
                    postalCodeT.setTextColor(Color.rgb(0, 0, 0));
                    postalCode = Integer.parseInt(String.valueOf(postalCodeF.getText()));
                    checker[6] = true;
                } else {
                    checker[6] = false;
                    postalCodeT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (!cityF.getText().equals("")) {
                    cityT.setTextColor(Color.rgb(0, 0, 0));
                    city = String.valueOf(cityF.getText());
                    checker[7] = true;
                } else {
                    checker[7] = false;
                    cityT.setTextColor(Color.rgb(255, 0, 0));

                }
                if (!passwordF.getText().equals("")) {
                    passwordT.setTextColor(Color.rgb(0, 0, 0));
                    password = String.valueOf(passwordF.getText());
                    checker[8] = true;
                } else {
                    checker[8] = false;
                    passwordT.setTextColor(Color.rgb(255, 0, 0));
                }
                if (areAllTrue(checker)) {
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    startDate = sdf.format(calendar.getTime());
                    calendar.add(Calendar.MONTH, length);
                    endDate = sdf.format(calendar.getTime());
                    calendar.setTime(date);
                    calendar.add(Calendar.DAY_OF_MONTH, 14);
                    expDate = sdf.format(calendar.getTime());


                    long nuOFRows = db.getNuOfRows("Member");
                    if (nuOFRows < 10) {
                        memID = "mem0000" + nuOFRows;
                    } else if (nuOFRows < 100) {
                        memID = "mem000" + nuOFRows;
                    } else if (nuOFRows < 1000) {
                        memID = "mem00" + nuOFRows;
                    } else if (nuOFRows < 10000) {
                        memID = "mem0" + nuOFRows;
                    } else if (nuOFRows < 100000) {
                        memID = "mem" + nuOFRows;
                    }
                    nuOFRows = db.getNuOfRows("Payment");
                    if (nuOFRows < 10) {
                        payID = "pay0000" + nuOFRows;
                    } else if (nuOFRows < 100) {
                        payID = "pay000" + nuOFRows;
                    } else if (nuOFRows < 1000) {
                        payID = "pay00" + nuOFRows;
                    } else if (nuOFRows < 10000) {
                        payID = "pay0" + nuOFRows;
                    } else if (nuOFRows < 100000) {
                        payID = "pay" + nuOFRows;
                    }
                    Toast.makeText(getApplicationContext(), memID + "\n" + pnr + "\n" + fName + "\n" + lName + "\n" + mail + "\n" + number + "\n" + address + "\n" + postalCode + "\n" + city + "\n" + password + "\n" + memShipID, Toast.LENGTH_LONG).show();
                    if (db.insertIntoMember(memID, pnr, fName, lName, mail, number, address, postalCode, city, password, memShipID)) {
                        if (db.insertIntoContract(memID, memShipID, startDate, 0, endDate)) {
                            if (db.insertIntoPayment(payID, "Autogiro", startDate, expDate, memID, price)) {
                                showMemAdded(fName,lName);
                            }
                        }
                    }
                }
            }
        });


    }

    public void showMemAdded (String fName,String lName){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Välkommen!");
        builder.setMessage("Välkommen " + fName + " " + lName + " du är nu en Fitness AB medlem!");
        builder.setPositiveButton("Till logga in!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    public static boolean areAllTrue(boolean[] array){
        for(boolean b : array) if(!b) return false;
        return true;
    }
}
