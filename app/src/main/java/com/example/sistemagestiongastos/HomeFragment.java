package com.example.sistemagestiongastos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import newadapter.SpinnerNewAdapter;

public class HomeFragment extends Fragment {
    int selecteditem;
    TextView tvingresos1, tvingresos2, tvgastos1, tvgastos2, tvtransferencia1, tvtransferencia2;
    TextView tvingreso, tvbalanceanterior, tvgasto, tvbalanceactual;
    Button btlistamovimientos;
    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money,
            R.drawable.piggy_bank};
    MedioTransporte medio;
    Spinner spinner;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        spinner = view.findViewById(R.id.spfuente);
        SpinnerNewAdapter adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
        spinner.setAdapter(adapter);
        tvingresos1 = view.findViewById(R.id.tvingresosa);
        tvingresos2 = view.findViewById(R.id.tvingresosb);
        tvgastos1 = view.findViewById(R.id.tvgastosa);
        tvgastos2 = view.findViewById(R.id.tvgastosb);
        tvtransferencia1 = view.findViewById(R.id.tvtrafera);
        tvtransferencia2 = view.findViewById(R.id.tvtraferb);

        btlistamovimientos = view.findViewById(R.id.btlistamovimientos);

        medio = (MedioTransporte) getActivity();

        int index;
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        } else {
            index = 0;
        }
        spinner.setSelection(index);

        tvingresos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteditem = spinner.getSelectedItemPosition();
                medio.ingresos(selecteditem);
            }
        });
        tvingresos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteditem = spinner.getSelectedItemPosition();
                medio.ingresos(selecteditem);
            }
        });
        tvgastos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteditem = spinner.getSelectedItemPosition();
                medio.gastos(selecteditem);
            }
        });
        tvgastos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteditem = spinner.getSelectedItemPosition();
                medio.gastos(selecteditem);
            }
        });
        tvtransferencia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteditem = spinner.getSelectedItemPosition();
                medio.transfer(selecteditem);
            }
        });
        tvtransferencia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteditem = spinner.getSelectedItemPosition();
                medio.transfer(selecteditem);
            }
        });
        btlistamovimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteditem = spinner.getSelectedItemPosition();
                medio.movimientos(selecteditem);
            }
        });
        return view;
    }
}