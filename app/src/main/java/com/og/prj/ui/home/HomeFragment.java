package com.og.prj.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.og.prj.R;
import com.og.prj.ui.cellphone.CellPhoneFragment;
import com.og.prj.ui.computer.ComputerFragment;
import com.og.prj.ui.musicalinstruments.MusicalInstrumentsFragment;
import com.og.prj.ui.nav.StoreLocation;
import com.og.prj.ui.personalcare.PersonalCareFragment;

public class HomeFragment extends Fragment {
    Toolbar toolbar;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button cellButton = (Button) root.findViewById(R.id.cellphone);
        cellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Cell Phones");
                CellPhoneFragment nextFrag= new CellPhoneFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button computerButton = (Button) root.findViewById(R.id.computer);
        computerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Computers");
                ComputerFragment nextFrag= new ComputerFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button musicalInstrumentsButton = (Button) root.findViewById(R.id.musical);
        musicalInstrumentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Musical Instruments");
                MusicalInstrumentsFragment nextFrag= new MusicalInstrumentsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button personalCareButton = (Button) root.findViewById(R.id.personalcare);
        personalCareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Personal Care");
                PersonalCareFragment nextFrag= new PersonalCareFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(), nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button storeLocationButton = (Button) root.findViewById(R.id.storelocation);
        storeLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                toolbar.setTitle("Store Location");
              Intent intent = new Intent(getActivity(), StoreLocation.class);
              startActivity(intent);
            }
        });
       /* final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
           /    textView.setText(s);
            }
        }); */
        return root;
    }

}