package com.example.sistemagestiongastos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import models.MovementModel;
import newadapter.MovementAdapter;
import newadapter.SpinnerNewAdapter;

public class MovementListFragment extends Fragment {


    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money, R.drawable.piggy_bank};
    Spinner spinner;
    SpinnerNewAdapter adapter;

    Spinner spmes, spanio;
    ArrayAdapter<String> adaptermes, adapteranio;
    String[] mes = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String[] anio = new String[]{"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};

    ArrayList<MovementModel> lista;
    RecyclerView rvproducto;
    MovementAdapter adaptador;
    Controller controller;

    String balance;

    TextView tvbalance;

    public MovementListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_movimientos, container, false);

        spinner = view.findViewById(R.id.spfuentelista);
        adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
        spinner.setAdapter(adapter);

        spinner.setAdapter(adapter);
        int index;
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        } else {
            index = 0;
        }
        spinner.setSelection(index);

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
        spanio.setSelection(year - 2020);

        Context ctx = getContext();
        controller = new Controller(ctx);
        lista = new ArrayList<>();

        adaptador = new MovementAdapter(lista);
        rvproducto = view.findViewById(R.id.rvDataMovimientos);
        rvproducto.setLayoutManager(new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false));

        lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
        adaptador.setLista(lista);
        adaptador.notifyDataSetChanged();
        rvproducto.setAdapter(adaptador);

        balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
        tvbalance = view.findViewById(R.id.tvbalance);
        tvbalance.setText(balance);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lista = controller.obtenerMovimientos(position + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                adaptador.setLista(lista);
                adaptador.notifyDataSetChanged();
                rvproducto.setAdapter(adaptador);

                balance = controller.ObtenerBalance(position + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                tvbalance.setText(balance);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spmes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, position + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                adaptador.setLista(lista);
                adaptador.notifyDataSetChanged();
                rvproducto.setAdapter(adaptador);

                balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, position + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                tvbalance.setText(balance);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spanio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                adaptador.setLista(lista);
                adaptador.notifyDataSetChanged();
                rvproducto.setAdapter(adaptador);

                balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                tvbalance.setText(balance);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        return view;
    }


}