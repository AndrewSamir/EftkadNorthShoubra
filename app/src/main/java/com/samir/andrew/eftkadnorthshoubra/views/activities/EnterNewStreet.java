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
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterNewStreet extends AppCompatActivity implements InterfaceDailogClicked {

    //dropdown church
    @Bind(R.id.textDropdownChurch)
    TextView textDropdownChurch;

    @OnClick(R.id.linearDropdownChurch)
    public void onClicklinearDropdownChurch() {
        HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetChurchs.name());
    }

    //=============================================================================================//
    //dropdown area
    @Bind(R.id.textDropdownArea)
    TextView textDropdownArea;

    @OnClick(R.id.linearDropdownArea)
    public void onClicklinearDropdownArea() {

        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetAreas.name());

        }
    }
//=============================================================================================//

    //dropdown father
    @Bind(R.id.textDropdownFather)
    TextView textDropdownFather;

    @OnClick(R.id.linearDropdownFather)
    public void onClicklinearDropdownFather() {
        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetFathers.name());
        }
    }
//=============================================================================================//

    @Bind(R.id.edtُEnterNewStreetName)
    EditText edtُEnterNewStreetName;

    @Bind(R.id.edtُEnterNewStreetSaint)
    EditText edtُEnterNewStreetSaint;

    @OnClick(R.id.tvEvenNumbersFrom)
    public void onClicktvEvenNumbersFrom() {
        // TODO submit data to server...
    }

    @OnClick(R.id.tvEvenNumbersTo)
    public void onClicktvEvenNumbersTo() {
        // TODO submit data to server...
    }

    @OnClick(R.id.tvOddNumbersFrom)
    public void onClicktvOddNumbersFrom() {
        // TODO submit data to server...
    }

    @OnClick(R.id.tvOddNumbersTo)
    public void onClicktvOddNumbersTo() {
        // TODO submit data to server...
    }

    @Bind(R.id.edtEnterNewStreetNotes)
    EditText edtEnterNewStreetNotes;

    @OnClick(R.id.btnEnterNewStreetAdd)
    public void onClickbtnEnterNewStreetAdd() {
        // TODO submit data to server...
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_street);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        HandleListDialog.getInstance(this).setClickDialogListener(this);

    }

    @Override
    public void onClickDialog(String name, String flag) {

        if (flag.equals(DataEnum.callGetChurchs)) {

            textDropdownChurch.setText(name);
            textDropdownArea.setText(getString(R.string.choose_area));
            textDropdownFather.setText(getString(R.string.choose_Father));

        } else if (flag.equals(DataEnum.callGetAreas)) {

            textDropdownArea.setText(name);

        } else if (flag.equals(DataEnum.callGetFathers)) {

            textDropdownFather.setText(name);
        }

    }
}
