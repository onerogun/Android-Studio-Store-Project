package com.og.prj.ui.musicalinstruments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.og.prj.R;

public class MusicalInstrumentsFragment extends Fragment {

    private MusicalInstrumentsViewModel mViewModel;

    public static MusicalInstrumentsFragment newInstance() {
        return new MusicalInstrumentsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.musical_instruments_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MusicalInstrumentsViewModel.class);
        // TODO: Use the ViewModel
    }

}
