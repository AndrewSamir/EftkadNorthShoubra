package com.samir.andrew.eftkadnorthshoubra.utlities;

import android.app.Dialog;
import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.models.newFather.ModelNewFather;
import com.samir.andrew.eftkadnorthshoubra.models.newMember.ModelMember;
import com.samir.andrew.eftkadnorthshoubra.models.newStreet.streetData;

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
                    DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                            .child(church)
                            .child(context.getString(R.string.fire_areas))
                            .child(area);

                    myRefJobs.setValue(context.getString(R.string.empty_field), new DatabaseReference.CompletionListener() {
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

    public void callAddStreet(final String flag, String church, String area, String streetName, streetData modelNewStreet) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                .child(church)
                .child(context.getString(R.string.fire_areas))
                .child(area)
                .child(streetName)
                .child(context.getString(R.string.fire_street_data));

        myRefJobs.setValue(modelNewStreet, new DatabaseReference.CompletionListener() {
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

    public void callAddMeeting(final String flag, final String church, final String meeting) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                .child(church)
                .child(context.getString(R.string.fire_meetings));

        myRefJobs.push().setValue(meeting, new DatabaseReference.CompletionListener() {
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

    public void callAddMember(final String flag, String church, String area, String streetName, ModelMember modelMember) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                .child(church)
                .child(context.getString(R.string.fire_areas))
                .child(area)
                .child(streetName)
                .child(context.getString(R.string.fire_members));

        myRefJobs.push().setValue(modelMember, new DatabaseReference.CompletionListener() {
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
