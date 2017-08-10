package com.samir.andrew.eftkadnorthshoubra.utlities;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;


/**
 * Created by lenovo on 6/28/2017.
 */

public class HandleListDialog {
    private static Context context;
    private static HandleListDialog instance = null;
    private InterfaceDailogClicked clickListener;
    private static FirebaseDatabase database;
    private static DatabaseReference myRef;

    public static HandleListDialog getInstance(Context context) {

        HandleListDialog.context = context;

        if (instance == null) {
            instance = new HandleListDialog();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);

        }
        return instance;
    }

    public void setClickDialogListener(InterfaceDailogClicked itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public void callGetJobs(final String flag) {

        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child("jobs");

            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {

                        DataSnapshot myChild = myChildren.iterator().next();

                        stringArrayList.add(myChild.getValue().toString());
                    }
                    populate(stringArrayList, flag, context.getString(R.string.jobs));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });

        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public void callGetQualifications(final String flag) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child("qualifications");

            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {

                        DataSnapshot myChild = myChildren.iterator().next();

                        stringArrayList.add(myChild.getValue().toString());
                    }
                    populate(stringArrayList, flag, context.getString(R.string.qualifications));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });

        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public void callGetChurchs(final String flag) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child("churchs");
            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {
                        DataSnapshot myChild = myChildren.iterator().next();
                        stringArrayList.add(myChild.getKey());
                    }
                    populate(stringArrayList, flag, context.getString(R.string.churchs));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });

        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }

    }

/////////////////////////////////////////////////////////////////////////////////////////

    public void callGetAreas(final String flag, String church) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                    .child(church)
                    .child(context.getString(R.string.fire_areas));
            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {
                        DataSnapshot myChild = myChildren.iterator().next();
                        stringArrayList.add(myChild.getKey());
                    }
                    populate(stringArrayList, flag, context.getString(R.string.areas));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });
        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////

    public void callGetStreets(final String flag, String church, String area) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                    .child(church)
                    .child(context.getString(R.string.fire_areas))
                    .child(area);
            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {
                        DataSnapshot myChild = myChildren.iterator().next();
                        stringArrayList.add(myChild.getKey());
                    }
                    populate(stringArrayList, flag, context.getString(R.string.streets));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });
        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////

    public void callGetFathers(final String flag, String church) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                    .child(church)
                    .child(context.getString(R.string.fire_fathers));

            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {
                        DataSnapshot myChild = myChildren.iterator().next();
                        stringArrayList.add(myChild.child(context.getString(R.string.fire_father_name)).getValue().toString());
                    }
                    populate(stringArrayList, flag, context.getString(R.string.fathers));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });
        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    public void callGetFathersLogin(final String flag, String church) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                    .child(church)
                    .child(context.getString(R.string.fire_fathers));

            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    ArrayList<String> mailArrayList = new ArrayList<String>();

                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {
                        DataSnapshot myChild = myChildren.iterator().next();
                        stringArrayList.add(myChild.child(context.getString(R.string.fire_father_name)).getValue().toString());
                        mailArrayList.add(myChild.child(context.getString(R.string.fire_father_app_mail)).getValue().toString());
                    }
                    populate(stringArrayList, mailArrayList, flag, context.getString(R.string.fathers));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });
        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////

    public void callGetMeetingsSearch(final String flag, String church) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                    .child(church)
                    .child(context.getString(R.string.fire_meetings));

            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {
                        DataSnapshot myChild = myChildren.iterator().next();
                        stringArrayList.add(myChild.getValue().toString());
                    }
                    populate(stringArrayList, flag, context.getString(R.string.fathers));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });
        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////

    public void callGetMeetings(final String flag, String church) {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();
        if (isOnline()) {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.fire_churchs))
                    .child(church)
                    .child(context.getString(R.string.fire_meetings));

            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    Iterable<DataSnapshot> myChildren = dataSnapshot.getChildren();

                    while (myChildren.iterator().hasNext()) {
                        DataSnapshot myChild = myChildren.iterator().next();
                        stringArrayList.add(myChild.getValue().toString());
                    }
                    populateCheckboxes(stringArrayList, flag, context.getString(R.string.meetings));
                    progressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                    Log.d("valueError", error.toString());
                }
            });
        } else {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////

    private void populate(ArrayList<String> jobItems, String flag, String dialogTitle) {

        if (jobItems.size() > 0) {
            ShowDIalog(jobItems, flag, dialogTitle);
        }
    }

    private void populate(ArrayList<String> jobItems, ArrayList<String> mailText, String flag, String dialogTitle) {

        if (jobItems.size() > 0) {
            ShowDIalog(jobItems, mailText, flag, dialogTitle);
        }
    }

    private void populateCheckboxes(ArrayList<String> jobItems, String flag, String dialogTitle) {

        if (jobItems.size() > 0) {
            showDialogCheckboxes(jobItems, flag, dialogTitle);
        }
    }

    private void ShowDIalog(ArrayList<String> arrName, final String flag, String dialogTitle) {
        new MaterialDialog.Builder(context)
                .title(dialogTitle)
                .items(arrName)

                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        // String name=text+"";

                        if (clickListener != null)
                            clickListener.onClickDialog(text + "", flag);
                        //Log.e("loge", text + "" + which);
                        dialog.dismiss();
                        return true;
                    }
                })
                .negativeText(context.getString(R.string.cancel))
                .show();
    }

    private void ShowDIalog(final ArrayList<String> arrName, final ArrayList<String> mailText, final String flag, String dialogTitle) {
        new MaterialDialog.Builder(context)
                .title(dialogTitle)
                .items(arrName)

                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        // String name=text+"";

                        if (clickListener != null)
                            clickListener.onClickDialog(text + "," + mailText.get(arrName.indexOf(text)), flag);
                        //Log.e("loge", text + "" + which);
                        dialog.dismiss();
                        return true;
                    }
                })
                .negativeText(context.getString(R.string.cancel))
                .show();
    }

    public void showDialogCheckboxes(final ArrayList<String> jobItems, final String flag, String dialogTitle) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_checkboxes);

        final LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.linearDialogCustomCheckboxes);

        for (String s : jobItems) {

            CheckBox checkBox = new CheckBox(context);
            checkBox.setText(s);
            checkBox.setId(jobItems.indexOf(s));
            linearLayout.addView(checkBox);
        }


        TextView tvDialogAction = (TextView) dialog.findViewById(R.id.tvDialogAction);
        final TextView tvDialogBack = (TextView) dialog.findViewById(R.id.tvDialogBack);


        tvDialogAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choosenMeetings = "";
                for (String s : jobItems) {

                    CheckBox checkBox = (CheckBox) dialog.findViewById(jobItems.indexOf(s));
                    if (checkBox.isChecked()) {
                        if (choosenMeetings.length() > 0) {
                            choosenMeetings += "," + s;
                        } else {
                            choosenMeetings += s;
                        }
                    }

                }

                clickListener.onClickDialog(choosenMeetings + "", flag);

                dialog.dismiss();
            }
        });

        tvDialogBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public Boolean isOnline() {
        try {
            Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
