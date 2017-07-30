package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

public class EnterNewJob extends AppCompatActivity implements InterfaceAddDataToFirebase {

    @Bind(R.id.edtEnterJobJob)
    EditText edtEnterJobJob;

    @OnClick(R.id.btnEnterJob)
    public void onClickbtnEnterJob() {
        // TODO submit data to server...
        if (edtEnterJobJob.getText().toString().length() > 0)
            HandleAddDataToFirebase.getInstance(EnterNewJob.this).callAddJob(DataEnum.callAddJob.name(), edtEnterJobJob.getText().toString());
        else {
            edtEnterJobJob.setError(getString(R.string.required_field));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        HandleAddDataToFirebase.getInstance(this).setClickDialogListener(this);
        HelpMe.getInstance(this).hideKeyBoard(this);

    }


    @Override
    public void onDataAddedSuccess(String flag) {

        if (flag.equals(DataEnum.callAddJob.name())) {

            TastyToast.makeText(this, getString(R.string.job_added), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
            onBackPressed();
        }
    }

    @Override
    public void onDataAddedFailed(String flag) {

        if (flag.equals(DataEnum.callAddJob.name())) {

            TastyToast.makeText(this, getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);

        }

    }

    @Override
    public void onDataAddedRepeated(String flag) {
        if (flag.equals(DataEnum.callAddJob.name())) {

            TastyToast.makeText(this, getString(R.string.job_repeated), TastyToast.LENGTH_SHORT, TastyToast.WARNING);

        }
    }
}
