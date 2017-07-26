package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.samir.andrew.eftkadnorthshoubra.models.newStreet.streetData;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleAddDataToFirebase;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;
import com.samir.andrew.eftkadnorthshoubra.utlities.HelpMe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterNewStreet extends AppCompatActivity implements InterfaceDailogClicked {

    //dropdown church
    @Bind(R.id.textDropdownChurch)
    TextView textDropdownChurch;

    @OnClick(R.id.linearDropdownChurch)
    public void onClicklinearDropdownChurch() {
        textDropdownChurch.setError(null);
        HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetChurchs.name());
    }

    //=============================================================================================//
    //dropdown area
    @Bind(R.id.textDropdownArea)
    TextView textDropdownArea;

    @OnClick(R.id.linearDropdownArea)
    public void onClicklinearDropdownArea() {

        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            textDropdownArea.setError(null);
            HandleListDialog.getInstance(this).callGetAreas(DataEnum.callGetAreas.name(), textDropdownChurch.getText().toString());

        } else {
            textDropdownChurch.setError(getString(R.string.required_field));
        }
    }
//=============================================================================================//

    //dropdown father
    @Bind(R.id.textDropdownFather)
    TextView textDropdownFather;

    @OnClick(R.id.linearDropdownFather)
    public void onClicklinearDropdownFather() {
        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            textDropdownFather.setError(null);
            HandleListDialog.getInstance(this).callGetFathers(DataEnum.callGetFathers.name(), textDropdownChurch.getText().toString());
        } else {
            textDropdownChurch.setError(getString(R.string.required_field));
        }
    }
//=============================================================================================//

    @Bind(R.id.edtُEnterNewStreetName)
    EditText edtُEnterNewStreetName;

    @Bind(R.id.edtُEnterNewStreetSaint)
    EditText edtُEnterNewStreetSaint;


    @Bind(R.id.tvEvenNumbersFrom)
    EditText tvEvenNumbersFrom;
    @Bind(R.id.tvEvenNumbersTo)
    EditText tvEvenNumbersTo;
    @Bind(R.id.tvOddNumbersFrom)
    EditText tvOddNumbersFrom;
    @Bind(R.id.tvOddNumbersTo)
    EditText tvOddNumbersTo;


    @Bind(R.id.edtEnterNewStreetNotes)
    EditText edtEnterNewStreetNotes;

    @OnClick(R.id.btnEnterNewStreetAdd)
    public void onClickbtnEnterNewStreetAdd() {
        if (validate()) {
            callSetStreetData();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_street);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        HelpMe.getInstance(this).hideKeyBoard(this);

        HandleListDialog.getInstance(this).setClickDialogListener(this);

    }

    @Override
    public void onClickDialog(String name, String flag) {

        if (flag.equals(DataEnum.callGetChurchs.name())) {

            textDropdownChurch.setText(name);
            textDropdownArea.setText(getString(R.string.choose_area));
            textDropdownFather.setText(getString(R.string.choose_Father));

        } else if (flag.equals(DataEnum.callGetAreas.name())) {

            textDropdownArea.setText(name);

        } else if (flag.equals(DataEnum.callGetFathers.name())) {

            textDropdownFather.setText(name);
        }
    }

    private void callSetStreetData() {

        streetData streetData = new streetData();
        streetData.setStreet_area(textDropdownArea.getText().toString());
        streetData.setStreet_name(edtُEnterNewStreetName.getText().toString());

        if (edtُEnterNewStreetSaint.getText().toString().length() > 0) {
            streetData.setStreet_saint(edtُEnterNewStreetSaint.getText().toString());
        } else {
            streetData.setStreet_saint("e");
        }

        if (tvEvenNumbersFrom.getText().toString().length() > 0) {
            streetData.setStreet_start_even(tvEvenNumbersFrom.getText().toString());
        } else {
            streetData.setStreet_start_even("e");
        }

        if (tvEvenNumbersTo.getText().toString().length() > 0) {
            streetData.setStreet_finish_even(tvEvenNumbersTo.getText().toString());
        } else {
            streetData.setStreet_finish_even("e");
        }

        if (tvOddNumbersFrom.getText().toString().length() > 0) {
            streetData.setStreet_start_odd(tvOddNumbersFrom.getText().toString());
        } else {
            streetData.setStreet_start_odd("e");
        }

        if (tvOddNumbersTo.getText().toString().length() > 0) {
            streetData.setStreet_finish_odd(tvOddNumbersTo.getText().toString());
        } else {
            streetData.setStreet_finish_odd("e");
        }

        if (edtEnterNewStreetNotes.getText().toString().length() > 0) {
            streetData.setStreet_notes(edtEnterNewStreetNotes.getText().toString());
        } else {
            streetData.setStreet_notes("e");
        }

        HandleAddDataToFirebase.getInstance(this).callAddStreet(DataEnum.callAddStreet.name(),
                textDropdownChurch.getText().toString(),
                textDropdownArea.getText().toString(),
                edtُEnterNewStreetName.getText().toString(),
                streetData);
    }

    private boolean validate() {

        boolean valid = true;

        if (edtُEnterNewStreetName.getText().toString().length() == 0) {
            valid = false;
            edtُEnterNewStreetName.setFocusable(true);
            edtُEnterNewStreetName.requestFocus();
            edtُEnterNewStreetName.setError(getString(R.string.required_field));
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

        return valid;
    }
}
