package com.example.sistemagestiongastos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Calendar;

import newadapter.SpinnerNewAdapter;

public class ListaMovimientos extends Fragment {


    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money, R.drawable.piggy_bank};
    Spinner spinner;
    SpinnerNewAdapter adapter;

    Spinner spmes, spanio;
    ArrayAdapter<String> adaptermes, adapteranio;
    String[] mes = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String[] anio = new String[]{"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};

    public ListaMovimientos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_movimientos, container, false);

        spinner = view.findViewById(R.id.spfuentelista);
        adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
        spinner.setAdapter(adapter);


        spmes = view.findViewById(R.id.spmes);
        spanio = view.findViewById(R.id.spanio);
        adaptermes = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, mes);
        adapteranio = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, anio);
        spmes.setAdapter(adaptermes);
        spanio.setAdapter(adapteranio);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        spmes.setSelection(month);
        spanio.setSelection(year-2020);
        return view;
    }
}