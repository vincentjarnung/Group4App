package com.example.group4app;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String dbName = "FitnessAB.sqlite";


    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Membership (memshipID TEXT primary key, memship text not null, price integer not null)");
        db.execSQL("CREATE TABLE Member (memID text primary key, pnr bigint not null unique, first_name text not null, last_name text not null, mail text not null unique, phoneNr integer not null unique, address text not null, postalCode integer not null, city text not null, password text not null, memshipID text not null, foreign key (memshipID) references Membership (memshipID))");
        db.execSQL("CREATE TABLE Payment (paymentID text not null primary key, paymentType text not null, date date not null, expiration date not null, memID text not null, price INTEGER, foreign key (memID) references Member (memID))");
        db.execSQL("CREATE TABLE Contract (memID text not null, memshipID text not null, time date not null, discount integer not null default 0, endDate date, primary key (memID, memshipID), foreign key (memID) references Member (memID), foreign key (memshipID) references Membership (memshipID))");
        db.execSQL("CREATE TABLE Gym (gymID text not null primary key, address text not null, city text not null, name text not null, openingHours text not null)");
        db.execSQL("CREATE TABLE Room (roomID text not null primary key, name text not null, capacity integer not null, gymID text not null, foreign key (gymID) references Gym (gymID))");
        db.execSQL("CREATE TABLE Weight (weightID text not null primary key, name text not null, weight integer not null, count integer, gymID text not null, foreign key (gymID) references Gym (gymID))");
        db.execSQL("CREATE TABLE Machine (machineID text not null primary key, name text not null, count integer, trainArea text not null, gymID text not null, foreign key (gymID) references Gym (gymID))");
        db.execSQL("CREATE TABLE Instructor (employeeID text not null primary key, first_name text not null, last_name text not null, age integer not null)");
        db.execSQL("CREATE TABLE trainType(trainTypeID TEXT PRIMARY KEY, name TEXT NOT NULL)");
        db.execSQL("CREATE TABLE MemberGymRelation (memID text not null, gymID text not null, visitingTime integer not null default 0, primary key (memID, gymID), foreign key (memID) references Member (memID), foreign key (gymID) references Gym (gymID))");
        db.execSQL("CREATE TABLE GroupTraining (grouptrainID text not null primary key, name text not null, trainTypeID text not null, beginTime datetime, roomID text not null, employeeID text not null, date date, endTime datetime, foreign key (trainTypeID) references trainType (trainTypeID), foreign key (roomID) references Room (roomID), foreign key (employeeID) references Instructor (employeeID))");
        db.execSQL("CREATE TABLE BookingGroupTraining (memID text not null, groupTrainID text not null, primary key (memID, groupTrainID), foreign key (memID) references Member (memID), foreign key (groupTrainID) references GroupTraining (groupTrainID))");
        db.execSQL("CREATE TABLE onlineTraining (onlineTrainID text not null primary key, videoName text not null, urlVideo text not null, description text not null, trainTypeID text not null, foreign key (trainTypeID) references trainType (trainTypeID))");
        db.execSQL("CREATE TABLE OnlineTrainingRecord (memID text not null, onlineTrainID text not null, review integer, primary key (memID, onlineTrainID), foreign key (onlineTrainID) references onlineTraining (onlineTrainID), foreign key (memID) references Member (memID))");

        db.execSQL("INSERT INTO Membership VALUES('gym3','Gymträning',249)");
        db.execSQL("INSERT INTO Membership VALUES('gym6','Gymträning',229)");
        db.execSQL("INSERT INTO Membership VALUES('gym12','Gymträning',199)");
        db.execSQL("INSERT INTO Membership VALUES('group3','Gruppträning',299)");
        db.execSQL("INSERT INTO Membership VALUES('group6','Gruppträning',279)");
        db.execSQL("INSERT INTO Membership VALUES('group12','Gruppträning',249)");

        db.execSQL("INSERT INTO Gym VALUES('gym01','Björkvägen 12','Göteborg','Björkvägen','Öppet dygnet runt')");
        db.execSQL("INSERT INTO Gym VALUES('gym02','Aspvägen 3','Göteborg','Aspvägen','Öppet dygnet runt')");
        db.execSQL("INSERT INTO Gym VALUES('gym03','Ekdalen 32','Göteborg','Ekdalen','Öppet dygnet runt')");


        db.execSQL("INSERT INTO Instructor VALUES('emp001','Lisa','Vanderpump',34)");
        db.execSQL("INSERT INTO Instructor VALUES('emp002','Dorit','Kemsley',36)");
        db.execSQL("INSERT INTO Instructor VALUES('emp003','NeNe','Leakes',28)");
        db.execSQL("INSERT INTO Instructor VALUES('emp004','Ramona','Singer',42)");
        db.execSQL("INSERT INTO Instructor VALUES('emp005','Erika','Jayne',28)");


        db.execSQL("INSERT INTO Room VALUES('room101','Bamse',40,'gym01')");
        db.execSQL("INSERT INTO Room VALUES('room102','Skalman',20,'gym01')");
        db.execSQL("INSERT INTO Room VALUES('room103','Lileskutt',15,'gym01')");
        db.execSQL("INSERT INTO Room VALUES('room201','Troja',40,'gym02')");
        db.execSQL("INSERT INTO Room VALUES('room202','Akilles',20,'gym02')");
        db.execSQL("INSERT INTO Room VALUES('room203','Afrodite',15,'gym02')");
        db.execSQL("INSERT INTO Room VALUES('room301','Ida',40,'gym03')");
        db.execSQL("INSERT INTO Room VALUES('room302','Emil',20,'gym03')");
        db.execSQL("INSERT INTO Room VALUES('room303','Alfred',15,'gym03')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllFromTable(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + table,null);
        return rs;
    }

    public Boolean insertIntoMember(String memID, long pnr, String fname, String lname, String mejl, Integer number, String address, Integer postalCode,String city, String password,String memshipID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("memID",memID);
        cv.put("pnr",pnr);
        cv.put("first_name",fname);
        cv.put("last_name",lname);
        cv.put("mail",mejl);
        cv.put("phoneNr",number);
        cv.put("address",address);
        cv.put("postalCode",postalCode);
        cv.put("city",city);
        cv.put("password",password);
        cv.put("memshipID",memshipID);

        long res = db.insert("Member",null,cv);
        if (res==-1)
            return false;
        else
            return true;
    }

    public Boolean insertIntoContract(String memID,String memshipID,String time,int discount,String endDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("memID",memID);
        cv.put("memshipID",memshipID);
        cv.put("time",time);
        cv.put("discount",discount);
        cv.put("endDate",endDate);

        long res = db.insert("Contract",null,cv);
        if (res==-1)
            return false;
        else
            return true;
    }

    public Boolean insertIntoPayment(String payID,String payType,String date,String expiration,String memID,int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("paymentID",payID);
        cv.put("paymentType",payType);
        cv.put("date",date);
        cv.put("expiration",expiration);
        cv.put("memID",memID);
        cv.put("price",price);

        long res = db.insert("Payment",null,cv);
        if (res==-1)
            return false;
        else
            return true;
    }



    public long getNuOfRows(String table){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, table);
        return count;
    }


}
