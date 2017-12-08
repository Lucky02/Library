package com.example.blaise.library;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/*
*@startuml
* scale 700*500
*
* AppCompatActivity <|-- MainActivity
* MainActivity o- DBAdapter
*
* class MainActivity {
* - DBAdapter db
* # void onCreate()
* + void AddContact()
* + void GetContacts()
* + void GetContact()
* + void UpdateContact()
* + void DeleteContact()
* + void DisplayContact()
*
* }
* @enduml
*
 */
public class MainActivity extends AppCompatActivity {

    DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBAdapter(this);

        AddContact();
        GetContacts();
        GetContact();
        UpdateContact();
        DeleteContact();
    }

    public void AddContact() {
        //---add a contact---
        db.open();
        if (db.insertContact("Wei-Meng Lee", "weimenglee@learn2develop.net") >= 0){
            Toast.makeText(this, "Add successful.", Toast.LENGTH_LONG).show();
        }

        if (db.insertContact("Mary Jackson", "mary@jackson.com") >= 0) {
            Toast.makeText(this, "Add successful.", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void GetContacts() {
        //--get all contacts---
        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst())
        {
            do {
                DisplayContact(c);
            } while (c.moveToNext());
        }
        db.close();
    }

    public void GetContact() {
        //---get a contact---
        db.open();
        Cursor c = db.getContact(2);
        if (c.moveToFirst())
            DisplayContact(c);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void UpdateContact() {
        //---update a contact---
        db.open();
        if (db.updateContact(1, "Wei-Meng Lee", "weimenglee@gmail.com"))
            Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void DeleteContact() {
        db.open();
        if (db.deleteContact(1))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void DisplayContact(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Email:  " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }
}
