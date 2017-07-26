package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.samir.andrew.eftkadnorthshoubra.models.newMember.ModelMember;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;
import com.samir.andrew.eftkadnorthshoubra.utlities.HelpMe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterNewMember extends AppCompatActivity implements InterfaceDailogClicked, InterfaceAddDataToFirebase {

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


    @OnClick(R.id.edtEnterNewMemberBirthdate)
    public void onClickedtEnterNewMemberBirthdate() {
        // TODO submit data to server...
    }

    @Bind(R.id.edtEnterNewMemberNationalId)
    EditText edtEnterNewMemberNationalId;

    @Bind(R.id.edtEnterNewMemberMail)
    EditText edtEnterNewMemberMail;

    @Bind(R.id.edtEnterNewMemberFather)
    EditText edtEnterNewMemberFather;

    @Bind(R.id.edtEnterNewMemberBaptismDate)
    TextView edtEnterNewMemberBaptismDate;

    @OnClick(R.id.edtEnterNewMemberBaptismDate)
    public void onClickedtEnterNewMemberBaptismDate() {
        // TODO submit data to server...
    }

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

     /*   if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            textDropdownArea.setError(null);
            HandleListDialog.getInstance(this).callGetAreas(DataEnum.callGetAreas.name(), textDropdownChurch.getText().toString());

        } else {
            textDropdownChurch.setError(getString(R.string.required_field));
        }*/
    }

    @Bind(R.id.edtEnterNewMemberMiraggeDate)
    TextView edtEnterNewMemberMiraggeDate;

    @OnClick(R.id.edtEnterNewMemberMiraggeDate)
    public void onClickedtEnterNewMemberMiraggeDate() {
        // TODO submit data to server...
    }


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

    @OnClick(R.id.btnEnterNewMemberAdd)
    public void onClickbtnEnterNewMemberAdd() {
        if (validate()) {
            callAddNewMember();
        }
    }

    private void callAddNewMember() {

        ModelMember modelMember = new ModelMember();
        modelMember.setMember_name(edtEnterNewMemberName.getText().toString());

        if (edtEnterNewMemberBlockNumber.getText().toString().length() > 0) {
            modelMember.setMember_block_no(edtEnterNewMemberBlockNumber.getText().toString());
        } else {
            modelMember.setMember_block_no("e");
        }

        if (edtEnterNewMemberFloor.getText().toString().length() > 0) {
            modelMember.setMember_floor_no(edtEnterNewMemberFloor.getText().toString());
        } else {
            modelMember.setMember_floor_no("e");
        }


        if (edtEnterNewMemberFlat.getText().toString().length() > 0) {
            modelMember.setMember_flat_no(edtEnterNewMemberFlat.getText().toString());
        } else {
            modelMember.setMember_flat_no("e");
        }


        if (edtEnterNewMemberMobile_1.getText().toString().length() > 0) {
            modelMember.setMember_mobile_1(edtEnterNewMemberMobile_1.getText().toString());
        } else {
            modelMember.setMember_mobile_1("e");
        }

        if (edtEnterNewMemberMobile_2.getText().toString().length() > 0) {
            modelMember.setMember_mobile_2(edtEnterNewMemberMobile_2.getText().toString());
        } else {
            modelMember.setMember_mobile_2("e");
        }


        if (edtEnterNewMemberPhone_1.getText().toString().length() > 0) {
            modelMember.setMember_phone_1(edtEnterNewMemberPhone_1.getText().toString());
        } else {
            modelMember.setMember_phone_1("e");
        }


        if (edtEnterNewMemberPhone_2.getText().toString().length() > 0) {
            modelMember.setMember_phone_2(edtEnterNewMemberPhone_2.getText().toString());
        } else {
            modelMember.setMember_phone_2("e");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        HandleListDialog.getInstance(this).setClickDialogListener(this);
        HandleAddDataToFirebase.getInstance(this).setClickDialogListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HelpMe.getInstance(this).hideKeyBoard(this);
    }

    @Override
    public void onClickDialog(String name, String flag) {

        if (flag.equals(DataEnum.callGetChurchs.name())) {

            textDropdownChurch.setText(name);
        } else if (flag.equals(DataEnum.callGetAreas.name())) {

            textDropdownArea.setText(name);
        } else if (flag.equals(DataEnum.callGetStreets.name())) {

            textDropdownStreet.setText(name);
        } else if (flag.equals(DataEnum.callGetJobs.name())) {

            textDropdownJob.setText(name);
        }

    }

    @Override
    public void onDataAddedSuccess(String flag) {

    }

    @Override
    public void onDataAddedFailed(String flag) {

    }

    @Override
    public void onDataAddedRepeated(String flag) {

    }

    private boolean validate() {

        boolean valid = true;

        if (textDropdownStreet.getText().toString().equals(getString(R.string.choose_Street))) {

            valid = false;
            textDropdownStreet.setFocusable(true);
            textDropdownStreet.requestFocus();
            textDropdownStreet.setError(getString(R.string.required_field));
        }
        if (textDropdownArea.getText().toString().equals(getString(R.string.choose_area))) {

            valid = false;
            textDropdownArea.setFocusable(true);
            textDropdownArea.requestFocus();
            textDropdownArea.setError(getString(R.string.required_field));
        }
        if (textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {

            valid = false;
            textDropdownChurch.setFocusable(true);
            textDropdownChurch.requestFocus();
            textDropdownChurch.setError(getString(R.string.required_field));
        }

        if (edtEnterNewMemberName.getText().toString().length() == 0) {
            valid = false;
            edtEnterNewMemberName.setFocusable(true);
            edtEnterNewMemberName.requestFocus();
            edtEnterNewMemberName.setError(getString(R.string.required_field));
        }

        return valid;
    }
}
