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
import com.samir.andrew.eftkadnorthshoubra.models.newArea.ModelNewArea;
import com.samir.andrew.eftkadnorthshoubra.models.newFather.ModelNewFather;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;
import com.samir.andrew.eftkadnorthshoubra.utlities.HelpMe;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterFather extends AppCompatActivity implements InterfaceDailogClicked, InterfaceAddDataToFirebase {

    //dropdown father
    @Bind(R.id.textDropdownChurch)
    TextView textDropdownChurch;

    @OnClick(R.id.linearDropdownChurch)
    public void onClicklinearDropdownChurch() {
        textDropdownChurch.setError(null);
        HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetChurchs.name());
    }

    //=====================================================================//

    @Bind(R.id.edtEnterFatherName)
    EditText edtEnterFatherName;

    @Bind(R.id.edtEnterFatherMobile_1)
    EditText edtEnterFatherMobile_1;

    @Bind(R.id.edtEnterFatherMobile_2)
    EditText edtEnterFatherMobile_2;

    @OnClick(R.id.btnEnterFatherAdd)
    public void onClickbtnEnterFatherAdd() {
        if (validate())
            addNewFather();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_father);
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
        TastyToast.makeText(this, getString(R.string.father_added), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
    }

    @Override
    public void onDataAddedFailed(String flag) {
        TastyToast.makeText(this, getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
    }

    @Override
    public void onDataAddedRepeated(String flag) {
    }

    private void addNewFather() {

        ModelNewFather modelNewFather = new ModelNewFather();
        modelNewFather.setFather_consercation_date("e");
        modelNewFather.setFather_name(edtEnterFatherName.getText().toString());
        modelNewFather.setFather_mobile_1(edtEnterFatherMobile_1.getText().toString());
        modelNewFather.setFather_mobile_2(edtEnterFatherMobile_2.getText().toString());

        HandleAddDataToFirebase.getInstance(this).callAddFather(DataEnum.callAddFather.name(),
                textDropdownChurch.getText().toString(),
                modelNewFather);
    }

    private boolean validate() {

        boolean valid = true;

        if (edtEnterFatherName.getText().toString().length() == 0) {
            valid = false;
            edtEnterFatherName.setFocusable(true);
            edtEnterFatherName.requestFocus();
            edtEnterFatherName.setError(getString(R.string.required_field));
        }
        if (textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            valid = false;
            textDropdownChurch.setFocusable(true);
            textDropdownChurch.requestFocus();
            textDropdownChurch.setError(getString(R.string.required_field));
        }

        return valid;
    }


}
