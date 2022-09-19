package com.example.sistemagestiongastos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import newadapter.SpinnerNewAdapter;

public class IncomeFragment extends Fragment {

    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money,
            R.drawable.piggy_bank};
    Spinner spinner;
    SpinnerNewAdapter adapter;

    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        spinner = view.findViewById(R.id.spfuenteingresos);
        adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
        spinner.setAdapter(adapter);
        int index;
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        } else {
            index = 0;
        }
        Log.d("index", String.valueOf(index));
        spinner.setSelection(index);
        return view;
    }
}