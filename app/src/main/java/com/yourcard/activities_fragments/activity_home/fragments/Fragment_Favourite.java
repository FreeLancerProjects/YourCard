package com.yourcard.activities_fragments.activity_home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.yourcard.R;
import com.yourcard.activities_fragments.activity_home.HomeActivity;
import com.yourcard.adapters.FavouriteProduct_Adapter;
import com.yourcard.databinding.FragmentFavouriteBinding;
import com.yourcard.models.SingleRestaurantModel;
import com.yourcard.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class Fragment_Favourite extends Fragment {
    private HomeActivity activity;
    private FragmentFavouriteBinding binding;
    private Preferences preferences;
    private String lang;
    private List<SingleRestaurantModel.MenuImages> favouriteDataList;
    private GridLayoutManager manager;
    private FavouriteProduct_Adapter favouriteProduct_adapter;

    public static Fragment_Favourite newInstance() {
        return new Fragment_Favourite();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        YoYo.with(Techniques.SlideInUp)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());

    }


    private void initView() {
        favouriteDataList = new ArrayList<>();
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

        manager = new GridLayoutManager(activity, 1);
        binding.recView.setLayoutManager(manager);
        favouriteProduct_adapter = new FavouriteProduct_Adapter(favouriteDataList, activity);
        binding.recView.setAdapter(favouriteProduct_adapter);
//        binding.recView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0) {
//                    int total = binding.recView.getAdapter().getItemCount();
//
//                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//
//
//                    if (total > 6 && (total - lastVisibleItem) == 2 && !isLoading) {
//                        isLoading = true;
//                        int page = current_page + 1;
//                        productModelList.add(null);
//                        adapter.notifyDataSetChanged();
//                        loadMore(page);
//                    }
//                }
//            }
//        });*/

        updateUI();
    }

    private void updateUI() {
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteDataList.add(new SingleRestaurantModel.MenuImages());
        favouriteProduct_adapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        YoYo.with(Techniques.SlideInUp)
                .duration(900)
                .repeat(0)
                .playOn(binding.getRoot());
    }
}
