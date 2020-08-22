package com.yourcard.activities_fragments.activity_home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.yourcard.R;
import com.yourcard.activities_fragments.activity_home.HomeActivity;
import com.yourcard.databinding.FragmentEditProfileBinding;
import com.yourcard.databinding.FragmentProfileBinding;
import com.yourcard.preferences.Preferences;

import io.paperdb.Paper;

public class Fragment_EditProfile extends Fragment {
    private HomeActivity activity;
    private FragmentEditProfileBinding binding;
    private Preferences preferences;
    private String lang;

    public static Fragment_EditProfile newInstance() {
        return new Fragment_EditProfile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();


    }


    private void initView() {
        activity = (HomeActivity) getActivity();
        preferences = Preferences.getInstance();
        Paper.init(activity);

//        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                //  Vertical offset == 0 indicates appBar is fully expanded.
//                Log.e("l;ll",Math.abs(verticalOffset)+"");
//                if (Math.abs(verticalOffset) > 200) {
//                    binding.llshow.setVisibility(View.VISIBLE);
//                    binding.llshow2.setVisibility(View.GONE);
//
//                } else {
//                    binding.llshow.setVisibility(View.GONE);
//                    binding.llshow2.setVisibility(View.VISIBLE);
//
//
//                }
//            }
//        });
    }


}
