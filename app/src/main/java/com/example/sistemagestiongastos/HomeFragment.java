package com.example.sistemagestiongastos;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import newadapter.MovementAdapter;
import newadapter.SpinnerNewAdapter;

public class HomeFragment extends Fragment {
    int selecteditem;
    TextView tvingresos1, tvingresos2, tvgastos1, tvgastos2, tvtransferencia1, tvtransferencia2;
    TextView tvmesactual, tvingreso, tvbalanceanterior, tvgasto, tvbalanceactual;
    TextView tvsignobalanceanterior;
    Button btlistamovimientos;
    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money,
            R.drawable.piggy_bank};
    MedioTransporte medio;
    Spinner spinner;

    Controller controller;
    String ingresos, balanceanterior, gastos, balanceactual;

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

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        tvmesactual = view.findViewById(R.id.tvmesactual);
        tvmesactual.setText(meses[month] + ", " + year);

        tvingreso = view.findViewById(R.id.tvingreso);
        tvbalanceanterior = view.findViewById(R.id.tvbalanceanterior);
        tvgasto = view.findViewById(R.id.tvgastos);
        tvbalanceactual = view.findViewById(R.id.tvbalanceactual);

        Context ctx = getContext();
        controller = new Controller(ctx);

        ingresos = controller.ObtenerIngresosString(spinner.getSelectedItemPosition() + 1, month + 1, year);
        gastos = controller.ObtenerGastosString(spinner.getSelectedItemPosition() + 1, month + 1, year);
        balanceanterior = controller.ObtenerBalanceAcumulado(spinner.getSelectedItemPosition() + 1, month==0 ? 12:month, year);
        balanceactual = controller.ObtenerBalanceAcumulado(spinner.getSelectedItemPosition() + 1, month + 1, year);

        tvingreso.setText("Bs. " + ingresos);
        tvgasto.setText("Bs. " + gastos);
        tvbalanceanterior.setText("Bs. " + balanceanterior);
        tvbalanceactual.setText("Bs. " + balanceactual);

        tvsignobalanceanterior = view.findViewById(R.id.tvsignobalanceanterior);
        if (balanceanterior.charAt(0) != '-') {
            tvsignobalanceanterior.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            tvsignobalanceanterior.setTextColor(Color.parseColor("#F44336"));
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecteditem = position;
                ingresos = controller.ObtenerIngresosString(spinner.getSelectedItemPosition() + 1, month + 1, year);
                gastos = controller.ObtenerGastosString(spinner.getSelectedItemPosition() + 1, month + 1, year);
                balanceanterior = controller.ObtenerBalanceAcumulado(spinner.getSelectedItemPosition() + 1, month==0 ? 12:month, year);
                balanceactual = controller.ObtenerBalanceAcumulado(spinner.getSelectedItemPosition() + 1, month + 1, year);

                tvingreso.setText("Bs. " + ingresos);
                tvgasto.setText("Bs. " + gastos);
                tvbalanceanterior.setText("Bs. " + balanceanterior);
                tvbalanceactual.setText("Bs. " + balanceactual);

                if (balanceanterior.charAt(0) != '-') {
                    tvsignobalanceanterior.setText("+");
                    tvsignobalanceanterior.setTextColor(Color.parseColor("#4CAF50"));
                } else {
                    tvsignobalanceanterior.setText("—");
                    tvsignobalanceanterior.setTextColor(Color.parseColor("#F44336"));
                }

                if (balanceactual.charAt(0) != '-') {
                    tvbalanceactual.setTextColor(Color.parseColor("#4CAF50"));
                } else {
                    tvbalanceactual.setTextColor(Color.parseColor("#F44336"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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