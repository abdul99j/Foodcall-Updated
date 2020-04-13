package com.example.foodcall.ui.AboutUs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodcall.R;

public class AboutUsFragment extends Fragment {

    private AboutUsViewModel aboutUsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutUsViewModel =
                ViewModelProviders.of(this).get(AboutUsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);
        final TextView textView = root.findViewById(R.id.aboutus);
        aboutUsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}