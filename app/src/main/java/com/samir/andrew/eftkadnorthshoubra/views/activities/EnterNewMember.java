package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.samir.andrew.eftkadnorthshoubra.models.newMember.ModelMember;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;
import com.samir.andrew.eftkadnorthshoubra.utlities.HelpMe;
import com.sdsmdg.tastytoast.TastyToast;
import com.wdullaer.materialdatetimepicker.date.DatePickerController;

import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterNewMember extends AppCompatActivity implements InterfaceDailogClicked, InterfaceAddDataToFirebase, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private Calendar calendar;

    private String textDate;

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
        textDate = DataEnum.Birthdate.name();
        getCalendar();
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
        textDate = DataEnum.Baptism.name();
        getCalendar();
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

    }

    @Bind(R.id.edtEnterNewMemberMiraggeDate)
    TextView edtEnterNewMemberMiraggeDate;

    @OnClick(R.id.edtEnterNewMemberMiraggeDate)
    public void onClickedtEnterNewMemberMiraggeDate() {
        textDate = DataEnum.Mirrage.name();
        getCalendar();
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

    @OnClick(R.id.btnEnterNewMemberAdd)
    public void onClickbtnEnterNewMemberAdd() {
        if (validate()) {
            callAddNewMember();
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

        if (edtEnterNewMemberBirthdate.getText().toString().equals(getString(R.string.hint_birthdate))) {
            modelMember.setMember_birthdate(edtEnterNewMemberBirthdate.getText().toString());
        } else {
            modelMember.setMember_birthdate("e");
        }
        if (edtEnterNewMemberBaptismDate.getText().toString().equals(getString(R.string.hint_baptism))) {
            modelMember.setMember_baptism_data(edtEnterNewMemberBaptismDate.getText().toString());
        } else {
            modelMember.setMember_baptism_data("e");
        }
        if (edtEnterNewMemberMiraggeDate.getText().toString().equals(getString(R.string.hint_mirrage))) {
            modelMember.setMember_marriage_date(edtEnterNewMemberMiraggeDate.getText().toString());
        } else {
            modelMember.setMember_marriage_date("e");
        }

        if (textDropdownQualification.getText().toString().equals(getString(R.string.choose_qualification))) {
            modelMember.setMember_qualification(textDropdownQualification.getText().toString());
        } else {
            modelMember.setMember_qualification("e");
        }

        if (textDropdownJob.getText().toString().equals(getString(R.string.choose_Job))) {
            modelMember.setMember_job(textDropdownJob.getText().toString());
        } else {
            modelMember.setMember_job("e");
        }

        if (edtEnterNewMemberNationalId.getText().toString().length() > 0) {
            modelMember.setMember_national_id(edtEnterNewMemberNationalId.getText().toString());
        } else {
            modelMember.setMember_national_id("e");
        }

        if (edtEnterNewMemberMail.getText().toString().length() > 0) {
            modelMember.setMember_mail(edtEnterNewMemberMail.getText().toString());
        } else {
            modelMember.setMember_mail("e");
        }


        if (edtEnterNewMemberFather.getText().toString().length() > 0) {
            modelMember.setFather(edtEnterNewMemberFather.getText().toString());
        } else {
            modelMember.setFather("e");
        }

        if (edtEnterNewMemberRelationshipInFamily.getText().toString().length() > 0) {
            modelMember.setMember_discription_in_family(edtEnterNewMemberRelationshipInFamily.getText().toString());
        } else {
            modelMember.setMember_discription_in_family("e");
        }

        if (edtEnterNewMemberFacebook.getText().toString().length() > 0) {
            modelMember.setMember_facebook_link(edtEnterNewMemberFacebook.getText().toString());
        } else {
            modelMember.setMember_facebook_link("e");
        }


        if (edtEnterNewMemberGraduationYear.getText().toString().length() > 0) {
            modelMember.setMember_graduation_year(edtEnterNewMemberGraduationYear.getText().toString());
        } else {
            modelMember.setMember_graduation_year("e");
        }
        if (edtEnterNewMemberSocialStatus.getText().toString().length() > 0) {
            modelMember.setMember_social_status(edtEnterNewMemberSocialStatus.getText().toString());
        } else {
            modelMember.setMember_social_status("e");
        }
        if (edtEnterNewMemberNotes.getText().toString().length() > 0) {
            modelMember.setNotes(edtEnterNewMemberNotes.getText().toString());
        } else {
            modelMember.setNotes("e");
        }


        HandleAddDataToFirebase.getInstance(this).callAddMember(DataEnum.callAddMember.name(),
                textDropdownChurch.getText().toString(),
                textDropdownArea.getText().toString(),
                textDropdownStreet.getText().toString(),
                modelMember);
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

    @Override
    public void onDataAddedSuccess(String flag) {

        if (flag.equals(DataEnum.callAddMember.name())) {

            TastyToast.makeText(this, "تم إضافة عضو جديد بنجاح", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
            onBackPressed();
        }

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

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        if (textDate.equals(DataEnum.Birthdate.name())) {
            edtEnterNewMemberBirthdate.setText(simpleDateFormat.format(calendar.getTime()));
        } else if (textDate.equals(DataEnum.Baptism.name())) {
            edtEnterNewMemberBaptismDate.setText(simpleDateFormat.format(calendar.getTime()));
        } else if (textDate.equals(DataEnum.Mirrage.name())) {
            edtEnterNewMemberMiraggeDate.setText(simpleDateFormat.format(calendar.getTime()));
        }
    }

    private void getCalendar() {

        calendar = Calendar.getInstance();
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                EnterNewMember.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        //  dpd.setThemeDark(true);

        dpd.setMaxDate(calendar);
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        // dpd.showYearPickerFirst(true);
        dpd.setVersion(com.wdullaer.materialdatetimepicker.date.DatePickerDialog.Version.VERSION_2);
        dpd.setAccentColor(getResources().getColor(R.color.colorAccent));
        // dpd.setVersion(showVersion2.isChecked() ? DatePickerDialog.Version.VERSION_2 : DatePickerDialog.Version.VERSION_1);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
