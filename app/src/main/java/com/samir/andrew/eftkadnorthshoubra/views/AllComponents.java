package com.samir.andrew.eftkadnorthshoubra.views;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.samir.andrew.eftkadnorthshoubra.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllComponents extends AppCompatActivity {

    @Bind(R.id.linearDropdown)
    LinearLayout linearDropdown;

    @Bind(R.id.arrowDropdown)
    ImageView arrowDropdown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_components);
        ButterKnife.bind(this);


       /* LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.color_yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.colorGrey), PorterDuff.Mode.SRC_ATOP);*/

    }

    @OnClick(R.id.linearDropdown)
    void onClicklinearDropdownGeneral() {
        GradientDrawable drawable = (GradientDrawable) linearDropdown.getBackground();
        drawable.setStroke(3, getResources().getColor(R.color.colorStrokeSelected));
        arrowDropdown.setColorFilter(ContextCompat.getColor(this,R.color.colorStrokeSelected));

    }

}
