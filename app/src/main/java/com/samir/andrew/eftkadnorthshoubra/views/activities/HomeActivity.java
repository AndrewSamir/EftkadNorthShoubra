package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.samir.andrew.eftkadnorthshoubra.models.newMember.ModelMember;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, InterfaceDailogClicked {


    @Bind(R.id.edtEnterNewMemberName)
    EditText edtEnterNewMemberName;

    @Bind(R.id.edtEnterNewMemberBlockNumber)
    EditText edtEnterNewMemberBlockNumber;

    @Bind(R.id.edtEnterNewMemberFloor)
    EditText edtEnterNewMemberFloor;

    @Bind(R.id.edtEnterNewMemberFlat)
    EditText edtEnterNewMemberFlat;

    @Bind(R.id.edtEnterNewMemberMobile_1)
    EditText edtEnterNewMemberMobile_1;

    @Bind(R.id.edtEnterNewMemberMobile_2)
    EditText edtEnterNewMemberMobile_2;

    @Bind(R.id.edtEnterNewMemberPhone_1)
    EditText edtEnterNewMemberPhone_1;

    @Bind(R.id.edtEnterNewMemberPhone_2)
    EditText edtEnterNewMemberPhone_2;

    @Bind(R.id.edtEnterNewMemberBirthdate)
    TextView edtEnterNewMemberBirthdate;

    @Bind(R.id.edtEnterNewMemberNationalId)
    EditText edtEnterNewMemberNationalId;

    @Bind(R.id.edtEnterNewMemberMail)
    EditText edtEnterNewMemberMail;

    @Bind(R.id.edtEnterNewMemberFather)
    EditText edtEnterNewMemberFather;

    @Bind(R.id.edtEnterNewMemberBaptismDate)
    TextView edtEnterNewMemberBaptismDate;

    @Bind(R.id.edtEnterNewMemberRelationshipInFamily)
    EditText edtEnterNewMemberRelationshipInFamily;

    @Bind(R.id.edtEnterNewMemberFacebook)
    EditText edtEnterNewMemberFacebook;

    @Bind(R.id.edtEnterNewMemberGraduationYear)
    EditText edtEnterNewMemberGraduationYear;

    //dropdown area
    @Bind(R.id.textDropdownJob)
    TextView textDropdownJob;

    @OnClick(R.id.linearDropdownJob)
    public void onClicklinearDropdownlinearDropdownJob() {

        HandleListDialog.getInstance(this).callGetJobs(DataEnum.callGetJobs.name());

    }

    @Bind(R.id.edtEnterNewMemberMiraggeDate)
    TextView edtEnterNewMemberMiraggeDate;

    @Bind(R.id.edtEnterNewMemberSocialStatus)
    EditText edtEnterNewMemberSocialStatus;

    //dropdown church
    @Bind(R.id.textDropdownChurch)
    TextView textDropdownChurch;

    @OnClick(R.id.linearDropdownChurch)
    public void onClicklinearDropdownChurch() {
        textDropdownChurch.setError(null);
        HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetChurchs.name());
        textDropdownArea.setText(getString(R.string.choose_area));
        textDropdownStreet.setText(getString(R.string.choose_Street));
    }

    //dropdown area
    @Bind(R.id.textDropdownArea)
    TextView textDropdownArea;

    @OnClick(R.id.linearDropdownArea)
    public void onClicklinearDropdownArea() {

        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            textDropdownArea.setError(null);
            HandleListDialog.getInstance(this).callGetAreas(DataEnum.callGetAreas.name(), textDropdownChurch.getText().toString());
            textDropdownStreet.setText(getString(R.string.choose_Street));
        } else {
            textDropdownChurch.setError(getString(R.string.required_field));
        }
    }

    //dropdown church
    @Bind(R.id.textDropdownQualification)
    TextView textDropdownQualification;

    @OnClick(R.id.linearDropdownQualification)
    public void onClicklinearDropdownQualification() {
        textDropdownQualification.setError(null);
        HandleListDialog.getInstance(this).callGetQualifications(DataEnum.callGetQualifications.name());
    }


    //dropdown street
    @Bind(R.id.textDropdownStreet)
    TextView textDropdownStreet;

    @OnClick(R.id.linearDropdownStreet)
    public void onClicklinearDropdownStreet() {

        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            if (!textDropdownArea.getText().toString().equals(getString(R.string.choose_area))) {

                textDropdownStreet.setError(null);
                HandleListDialog.getInstance(this).callGetStreets(DataEnum.callGetStreets.name(),
                        textDropdownChurch.getText().toString()
                        , textDropdownArea.getText().toString());
            } else {
                textDropdownArea.setError(getString(R.string.required_field));
            }

        } else {
            textDropdownChurch.setError(getString(R.string.required_field));
        }
    }

    @Bind(R.id.edtEnterNewMemberNotes)
    EditText edtEnterNewMemberNotes;

    @Bind(R.id.btnEnterNewMemberAdd)
    Button btnEnterNewMemberAdd;

    @OnClick(R.id.btnEnterNewMemberAdd)
    public void onClickbtnEnterNewMemberAdd() {

        if (textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {

            textDropdownChurch.setError(getString(R.string.required_field));
            textDropdownChurch.setFocusableInTouchMode(true);
            textDropdownChurch.setFocusable(true);
            textDropdownChurch.requestFocus();

        } else {
            createCheckValues();
            search();
        }
    }

    @OnClick(R.id.btnAddMemberMeeting)
    public void onClickbtnAddMemberMeeting() {
        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church)))
            HandleListDialog.getInstance(this).callGetMeetings(DataEnum.callGetMettings.name(), textDropdownChurch.getText().toString());
        else {

            textDropdownChurch.setError(getString(R.string.required_field));
            textDropdownChurch.setFocusable(true);
            textDropdownChurch.requestFocus();
        }
    }

    @Bind(R.id.linearMemberMeetings)
    LinearLayout linearMemberMeetings;


    private FirebaseDatabase database;
    private DatabaseReference myRef;

    String chName = "";
    String chArea = "";
    String chStreet = "";
    String chMobile = "";
    String chPhone = "";
    String chFather = "";
    String chQualification = "";
    String chJob = "";
    String chSocialStatue = "";

    public static List<ModelMember> modelMemberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        initLayout();
        HandleListDialog.getInstance(this).setClickDialogListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void initLayout() {

        edtEnterNewMemberBlockNumber.setVisibility(View.GONE);
        edtEnterNewMemberFloor.setVisibility(View.GONE);
        edtEnterNewMemberFlat.setVisibility(View.GONE);
        edtEnterNewMemberMobile_2.setVisibility(View.GONE);
        edtEnterNewMemberPhone_2.setVisibility(View.GONE);
        edtEnterNewMemberNationalId.setVisibility(View.GONE);
        edtEnterNewMemberMail.setVisibility(View.GONE);
        edtEnterNewMemberBaptismDate.setVisibility(View.GONE);
        edtEnterNewMemberRelationshipInFamily.setVisibility(View.GONE);
        edtEnterNewMemberFacebook.setVisibility(View.GONE);
        edtEnterNewMemberGraduationYear.setVisibility(View.GONE);
        edtEnterNewMemberMiraggeDate.setVisibility(View.GONE);
        edtEnterNewMemberNotes.setVisibility(View.GONE);
        edtEnterNewMemberBirthdate.setVisibility(View.GONE);
        btnEnterNewMemberAdd.setText(getString(R.string.search));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_enter_fathers) {
            startActivity(new Intent(this, EnterFather.class));
        } else if (id == R.id.nav_enter_new_area) {
            startActivity(new Intent(this, EnterNewArea.class));

        } else if (id == R.id.nav_enter_new_job) {
            startActivity(new Intent(this, EnterNewJob.class));

        } else if (id == R.id.nav_enter_meetings) {
            startActivity(new Intent(this, EnterNewMeeting.class));

        } else if (id == R.id.nav_enter_new_member) {
            startActivity(new Intent(this, EnterNewMember.class));

        } else if (id == R.id.nav_enter_new_parent) {
            startActivity(new Intent(this, EnterNewRelationship.class));

        } else if (id == R.id.nav_enter_new_street) {
            startActivity(new Intent(this, EnterNewStreet.class));

        } else if (id == R.id.nav_enter_new_user) {
            startActivity(new Intent(this, EnterNewUser.class));

        } else if (id == R.id.nav_enter_new_qualification) {
            startActivity(new Intent(this, EnterQualifaction.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void search() {

        final Dialog progressDialog = new ProgressDialog(this, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        modelMemberList = new ArrayList<>();
        DatabaseReference myRefJobs = myRef.child(getString(R.string.fire_churchs)).child("مارمرقس شبرا").child("areas");
        myRefJobs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot areas : dataSnapshot.getChildren()) {

                    for (DataSnapshot street : areas.getChildren()) {

                        if (!areas.getKey().equals(chArea) && !chArea.equals("")) {
                            continue;
                        }
                        for (final DataSnapshot member : street.child(getString(R.string.fire_members)).getChildren()) {

                            if (!street.getKey().equals(chStreet) && !chStreet.equals("")) {
                                continue;
                            }

                            if (member.child(getString(R.string.fire_member_name)).getValue().toString().equals(chName) ||
                                    chName.equals("")) {

                                if (member.child(getString(R.string.fire_member_mobile_1)).getValue().toString().equals(chMobile) ||
                                        member.child(getString(R.string.fire_member_mobile_2)).getValue().toString().equals(chMobile) ||
                                        chMobile.equals("")) {

                                    if (member.child(getString(R.string.fire_member_phone_1)).getValue().toString().equals(chPhone) ||
                                            member.child(getString(R.string.fire_member_phone_2)).getValue().toString().equals(chPhone) ||
                                            chPhone.equals("")) {

                                        if (member.child(getString(R.string.fire_member_father)).getValue().toString().equals(chFather) ||
                                                chFather.equals("")) {

                                            if (member.child(getString(R.string.fire_member_job)).getValue().toString().equals(chJob) ||
                                                    chJob.equals("")) {

                                                if (member.child(getString(R.string.fire_member_social_statue)).getValue().toString().equals(chSocialStatue) ||
                                                        chSocialStatue.equals("")) {

                                                    if (member.child(getString(R.string.fire_member_qualification)).getValue().toString().equals(chQualification) ||
                                                            chQualification.equals("")) {

                                                        myRef.child("test search").push().setValue(member.child(getString(R.string.fire_member_name)).getValue().toString());

                                                        ModelMember member_ = member.getValue(ModelMember.class);
                                                        modelMemberList.add(member_);

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Log.d("valueError", error.toString());
            }
        });
        progressDialog.dismiss();
        startActivity(new Intent(HomeActivity.this, SearchResult.class));
    }

    private void createCheckValues() {

        if (edtEnterNewMemberName.getText().toString().length() > 0) {
            chName = edtEnterNewMemberName.getText().toString();
        }
        if (edtEnterNewMemberMobile_1.getText().toString().length() > 0) {
            chMobile = edtEnterNewMemberMobile_1.getText().toString();
        }
        if (edtEnterNewMemberPhone_1.getText().toString().length() > 0) {
            chPhone = edtEnterNewMemberPhone_1.getText().toString();
        }
        if (edtEnterNewMemberFather.getText().toString().length() > 0) {
            chFather = edtEnterNewMemberFather.getText().toString();
        }
        if (!textDropdownQualification.getText().toString().equals(getString(R.string.choose_qualification))) {
            chQualification = textDropdownQualification.getText().toString();
        }
        if (!textDropdownJob.getText().toString().equals(getString(R.string.choose_Job))) {
            chJob = textDropdownJob.getText().toString();
        }
        if (edtEnterNewMemberSocialStatus.getText().toString().length() > 0) {
            chSocialStatue = edtEnterNewMemberSocialStatus.getText().toString();
        }
        if (!textDropdownArea.getText().toString().equals(getString(R.string.choose_area))) {
            chArea = textDropdownArea.getText().toString();
        }
        if (!textDropdownStreet.getText().toString().equals(getString(R.string.choose_Street))) {
            chStreet = textDropdownStreet.getText().toString();
        }

    }

    @Override
    public void onClickDialog(String name, String flag) {

        if (flag.equals(DataEnum.callGetChurchs.name())) {
            textDropdownChurch.setError(null);
            textDropdownChurch.setText(name);
        } else if (flag.equals(DataEnum.callGetAreas.name())) {

            textDropdownArea.setText(name);
        } else if (flag.equals(DataEnum.callGetStreets.name())) {

            textDropdownStreet.setText(name);
        } else if (flag.equals(DataEnum.callGetJobs.name())) {

            textDropdownJob.setText(name);
        } else if (flag.equals(DataEnum.callGetQualifications.name())) {

            textDropdownQualification.setText(name);
        } else if (flag.equals(DataEnum.callGetMettings.name())) {

            if (name.contains(",")) {
                String[] meetings = name.split(",");

                for (String s : meetings) {

                    TextView textview = new TextView(this);
                    textview.setText(s);
                    textview.setGravity(Gravity.RIGHT);
                    linearMemberMeetings.addView(textview);
                }

            } else {
                TextView textview = new TextView(this);
                textview.setText(name);
                linearMemberMeetings.addView(textview);

            }
        }

    }

}
