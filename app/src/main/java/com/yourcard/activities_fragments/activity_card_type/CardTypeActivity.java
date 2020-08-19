package com.yourcard.activities_fragments.activity_card_type;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.yourcard.R;
import com.yourcard.databinding.ActivityCardDetialsBinding;
import com.yourcard.databinding.ActivityCardTypeBinding;
import com.yourcard.interfaces.Listeners;
import com.yourcard.language.Language;

import java.util.Locale;

import io.paperdb.Paper;

//import com.yourcard.Animate.CircleAnimationUtil;

public class CardTypeActivity extends AppCompatActivity implements Listeners.BackListener, Listeners.CardDetialsAction {
    private ActivityCardTypeBinding binding;
    private String lang;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_type);

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
        Intent intent = new Intent(CardTypeActivity.this, CardTypeActivity.class);
        startActivity(intent);
    }
}
