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

public class EnterNewArea extends AppCompatActivity implements InterfaceDailogClicked, InterfaceAddDataToFirebase {

    //dropdown church
    @Bind(R.id.textDropdownChurch)
    TextView textDropdownChurch;

    @OnClick(R.id.linearDropdownChurch)
    public void onClicklinearDropdownChurch() {
        HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetChurchs.name());
    }

    //=================================================================================//

    @Bind(R.id.edtEnterAreaArea)
    EditText edtEnterAreaArea;


    @OnClick(R.id.btnEnterAreaAdd)
    public void onClickbtnEnterAreaAdd() {
        // TODO
        HandleAddDataToFirebase.getInstance(this).callAddArea(DataEnum.callAddArea.name(),
                edtEnterAreaArea.getText().toString(),
                textDropdownChurch.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_area);
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
        TastyToast.makeText(this, getString(R.string.area_added), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
        onBackPressed();
    }

    @Override
    public void onDataAddedFailed(String flag) {
        TastyToast.makeText(this, getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    @Override
    public void onDataAddedRepeated(String flag) {
        TastyToast.makeText(this, getString(R.string.area_repeated), TastyToast.LENGTH_SHORT, TastyToast.WARNING);
    }
}
