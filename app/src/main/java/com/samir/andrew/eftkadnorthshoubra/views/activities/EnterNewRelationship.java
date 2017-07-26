package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.utlities.HelpMe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterNewRelationship extends AppCompatActivity {

    @Bind(R.id.edtEnterRelationship)
    EditText edtEnterRelationship;

    @OnClick(R.id.btnEnterRelationship)
    public void onClickbtnEnterRelationship() {
        // TODO submit data to server...
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_relationship);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        HelpMe.getInstance(this).hideKeyBoard(this);

        ButterKnife.bind(this);
    }

}
