package com.samir.andrew.eftkadnorthshoubra.utlities;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.samir.andrew.eftkadnorthshoubra.models.newArea.ModelNewArea;
import com.samir.andrew.eftkadnorthshoubra.models.newFather.ModelNewFather;

import java.util.ArrayList;

import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;


/**
 * Created by lenovo on 6/28/2017.
 */

public class HandleAddDataToFirebase {
    private static Context context;
    private static HandleAddDataToFirebase instance = null;
    private InterfaceAddDataToFirebase clickListener;
    private static FirebaseDatabase database;
    private static DatabaseReference myRef;

    public static HandleAddDataToFirebase getInstance(Context context) {

        HandleAddDataToFirebase.context = context;

        if (instance == null) {
            instance = new HandleAddDataToFirebase();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);

        }
        return instance;
    }

    public void setClickDialogListener(InterfaceAddDataToFirebase itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public void callAddJob(final String flag, String job) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        DatabaseReference myRefJobs = myRef.child("jobs");

        myRefJobs.push().setValue(job, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError error, DatabaseReference ref) {

                if (error == null) {
                    clickListener.onDataAddedSuccess(flag);
                } else {
                    clickListener.onDataAddedFailed(flag);
                }

                progressDialog.dismiss();
            }
        });

    }

    public void callAddArea(final String flag, final String area, final String church) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        myRef.child(church).child(area).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    clickListener.onDataAddedSuccess(flag);
                } else {
                    DatabaseReference myRefJobs = myRef.child(church).child(area);
                    myRefJobs.push().setValue(context.getString(R.string.empty_field), new DatabaseReference.CompletionListener() {
                        public void onComplete(DatabaseError error, DatabaseReference ref) {

                            if (error == null) {
                                clickListener.onDataAddedSuccess(flag);
                            } else {
                                clickListener.onDataAddedFailed(flag);
                            }

                            progressDialog.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void callAddFather(final String flag, String church, ModelNewFather modelNewFather) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs)).child(church).child(context.getString(R.string.fire_fathers));

        myRefJobs.push().setValue(modelNewFather, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError error, DatabaseReference ref) {

                if (error == null) {
                    clickListener.onDataAddedSuccess(flag);
                } else {
                    clickListener.onDataAddedFailed(flag);
                }

                progressDialog.dismiss();
            }
        });

    }


}
