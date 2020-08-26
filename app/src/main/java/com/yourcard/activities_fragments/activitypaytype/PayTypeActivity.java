package com.yourcard.activities_fragments.activitypaytype;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yourcard.R;
import com.yourcard.databinding.ActivityPayBinding;
import com.yourcard.databinding.ActivityRechargeBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;

import java.util.Locale;

import io.paperdb.Paper;

//import com.yourcard.Animate.CircleAnimationUtil;

public class PayTypeActivity extends AppCompatActivity implements Listeners.BackListener {
    private ActivityPayBinding binding;
    private String lang;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pay);
        YoYo.with(Techniques.ZoomInDown)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());
        initView();


    }


    private void initView() {

        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setBackListener(this);

    }


    @Override
    public void back() {
//        if (isDataAdded) {
//            setResult(RESULT_OK);

//        }
        YoYo.with(Techniques.ZoomOut)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        YoYo.with(Techniques.ZoomInDown)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());
    }

    @Override
    public void onBackPressed() {
        back();
    }


}
