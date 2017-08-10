package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.interfaces.InterfaceDailogClicked;
import com.samir.andrew.eftkadnorthshoubra.utlities.DataEnum;
import com.samir.andrew.eftkadnorthshoubra.utlities.HandleListDialog;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import developer.mokadim.projectmate.SharedPrefUtil;
import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;

public class Login extends AppCompatActivity implements InterfaceDailogClicked {

    //dropdown church
    @Bind(R.id.textDropdownChurch)
    TextView textDropdownChurch;

    @OnClick(R.id.linearDropdownChurch)
    public void onClicklinearDropdownChurch() {
        textDropdownChurch.setError(null);
        HandleListDialog.getInstance(this).callGetChurchs(DataEnum.callGetChurchs.name());
    }

    //dropdown father
    @Bind(R.id.textDropdownFather)
    TextView textDropdownFather;

    @OnClick(R.id.linearDropdownFather)
    public void onClicklinearDropdownFather() {
        if (!textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {
            textDropdownFather.setError(null);
            HandleListDialog.getInstance(this).callGetFathersLogin(DataEnum.callGetFathers.name(), textDropdownChurch.getText().toString());
        } else {
            textDropdownChurch.setError(getString(R.string.required_field));
        }
    }

    @Bind(R.id.edtLoginPassword)
    EditText edtLoginPassword;


    @OnClick(R.id.btnLogin)
    public void onClickbtnLogin() {
        if (isValid()) {
            Login();
        }
    }

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private boolean isValid() {
        boolean valid = true;

        if (textDropdownChurch.getText().toString().equals(getString(R.string.choose_church))) {

            valid = false;
            textDropdownChurch.setFocusable(true);
            textDropdownChurch.requestFocus();
            textDropdownChurch.setError(getString(R.string.required_field));

        }
        if (textDropdownFather.getText().toString().equals(getString(R.string.choose_Father))) {

            valid = false;
            textDropdownFather.setFocusable(true);
            textDropdownFather.requestFocus();
            textDropdownFather.setError(getString(R.string.required_field));

        }
        if (edtLoginPassword.getText().toString().length() == 0) {

            valid = false;
            edtLoginPassword.setFocusable(true);
            edtLoginPassword.requestFocus();
            edtLoginPassword.setError(getString(R.string.required_field));
        }

        return valid;
    }

    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        HandleListDialog.getInstance(this).setClickDialogListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClickDialog(String name, String flag) {

        if (flag.equals(DataEnum.callGetChurchs.name())) {

            textDropdownChurch.setText(name);
            textDropdownFather.setText(getString(R.string.choose_Father));

        } else if (flag.equals(DataEnum.callGetFathers.name())) {

            String[] gottenText = name.split(",");
            textDropdownFather.setText(gottenText[0]);
            mail = gottenText[1];
        }
    }

    private void Login() {

        final Dialog progressDialog = new ProgressDialog(this, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(mail, edtLoginPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();

                            TastyToast.makeText(Login.this, "invalid username or password", TastyToast.LENGTH_SHORT, TastyToast.ERROR);

                        } else {

                            SharedPrefUtil.getInstance(Login.this).write(DataEnum.shChurch.name(), textDropdownChurch.getText().toString());
                            SharedPrefUtil.getInstance(Login.this).write(DataEnum.shFather.name(), textDropdownFather.getText().toString());

                            startActivity(new Intent(Login.this, HomeActivity.class));
                            progressDialog.dismiss();
                        }

                        // ...
                    }
                });
    }


}
