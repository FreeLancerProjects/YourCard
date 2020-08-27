package com.yourcard.activities_fragments.activity_transfer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yourcard.R;
import com.yourcard.activities_fragments.activity_card_type.CardTypeActivity;
import com.yourcard.activities_fragments.activitypaytype.PayTypeActivity;
import com.yourcard.databinding.ActivityBankTransferBinding;
import com.yourcard.databinding.ActivityBillBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;

import java.util.Locale;

import io.paperdb.Paper;

//import com.yourcard.Animate.CircleAnimationUtil;

public class TransFerActivity extends AppCompatActivity implements Listeners.BackListener, Listeners.CardDetialsAction {
    private ActivityBankTransferBinding binding;
    private String lang;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(Language.updateResources(base, Language.getLanguage(base)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bank_transfer);
        YoYo.with(Techniques.SlideInUp)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());
        initView();


    }


    private void initView() {

        Paper.init(this);

        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setBackListener(this);
        binding.setLang(lang);


    }


    @Override
    public void back() {
//        if (isDataAdded) {
//            setResult(RESULT_OK);
//        }
        finish();
    }


    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
//            createOrderModel = preferences.getCartData(this);
//            if (createOrderModel == null) {
//                createOrderModel = new CreateOrderModel();
//                createOrderModel.setMarkter_id(market.getId());
//                binding.setCartCount(0);
//                isDataAdded = true;
//
//            } else {
//
//                binding.setCartCount(createOrderModel.getProducts().size());
//            }
//        }
        }
    }


    @Override
    public void choosecard() {
        Intent intent = new Intent(TransFerActivity.this, CardTypeActivity.class);
        startActivity(intent);
    }
}
