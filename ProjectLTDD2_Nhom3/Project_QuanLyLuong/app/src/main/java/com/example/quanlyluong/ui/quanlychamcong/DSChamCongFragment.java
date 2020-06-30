package com.example.quanlyluong.ui.quanlychamcong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlyluong.R;

public class DSChamCongFragment extends Fragment {

    private DSChamCongViewModel dsChamCongViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dsChamCongViewModel =
                ViewModelProviders.of(this).get(DSChamCongViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dschamcong, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        dsChamCongViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}