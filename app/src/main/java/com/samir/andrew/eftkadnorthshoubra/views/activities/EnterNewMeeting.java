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
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;
import com.samir.andrew.eftkadnorthshoubra.utlities.HelpMe;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterNewMeeting extends AppCompatActivity implements InterfaceDailogClicked, InterfaceAddDataToFirebase {

    //dropdown church
    @Bind(R.id.textDropdownChurch)
    TextView textDropdownChurch;

    @OnClick(R.id.linearDropdownChurch)
    public void onClicklinearDropdownChurch() {
        textDropdownChurch.setError(null);
        HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetChurchs.name());
    }

    //=================================================================================//

    @Bind(R.id.edtEnterMeeting)
    EditText edtEnterMeeting;

    @OnClick(R.id.btnEnterMeetingAdd)
    public void onClickbtnEnterMeetingAdd() {

        if (validate()) {

            HandleAddDataToFirebase.getInstance(this).callAddMeeting(DataEnum.callAddMeeting.name(),
                    textDropdownChurch.getText().toString(),
                    edtEnterMeeting.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_meeting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        HelpMe.getInstance(this).hideKeyBoard(this);

        HandleListDialog.getInstance(this).setClickDialogListener(this);
        HandleAddDataToFirebase.getInstance(this).setClickDialogListener(this);

    }

    @Override
    public void onClickDialog(String name, String flag) {

        if (flag.equals(DataEnum.callGetChurchs.name())) {

            textDropdownChurch.setText(name);
        }
    }

    @Override
    public void onDataAddedSuccess(String flag) {

        TastyToast.makeText(this, "تم إضافة الاجتماع بنجاح", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
        onBackPressed();
    }

    @Override
    public void onDataAddedFailed(String flag) {

    }

    @Override
    public void onDataAddedRepeated(String flag) {

    }

    private boolean validate() {

        boolean valid = true;

        if (textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            valid = false;
            textDropdownChurch.setFocusable(true);
            textDropdownChurch.requestFocus();
            textDropdownChurch.setError(getString(R.string.required_field));
        }
        if (edtEnterMeeting.getText().toString().length() == 0) {
            valid = false;
            edtEnterMeeting.setFocusable(true);
            edtEnterMeeting.requestFocus();
            edtEnterMeeting.setError(getString(R.string.required_field));
        }


        return valid;
    }
}
