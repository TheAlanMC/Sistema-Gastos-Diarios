package com.example.sistemagestiongastos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

import newadapter.SpinnerNewAdapter;

public class ExpenseFragment extends Fragment {

    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money, R.drawable.piggy_bank};
    Spinner spinner;
    SpinnerNewAdapter adapter;

    String[] textArrayCat = {"Otros", "Ropa", "Bebidas", "Educacion", "Comida", "Combustible", "Diversion", "Salud", "Viaje", "Hotel",
            "Mercaderia", "Personal", "Mascotas", "Restaurante", "Propinas", "Transporte"};
    Integer[] imageArrayCat = {R.drawable.question, R.drawable.clothes, R.drawable.drink, R.drawable.education, R.drawable.food,
            R.drawable.fuel, R.drawable.fun, R.drawable.healthcare, R.drawable.road, R.drawable.hotel, R.drawable.box,
            R.drawable.hands, R.drawable.paw, R.drawable.restaurant, R.drawable.tip, R.drawable.taxi};
    Spinner spinnerCat;
    SpinnerNewAdapter adapterCat;

    EditText etDate;
    EditText etTime;

    Button btcancelar;
    MedioTransporte medio;

    public ExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        spinner = view.findViewById(R.id.spfuentegastos);
        adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
        spinner.setAdapter(adapter);
        int index;
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        } else {
            index = 0;
        }
        spinner.setSelection(index);
        spinnerCat = view.findViewById(R.id.spcategoriagastos);
        adapterCat = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArrayCat, imageArrayCat);
        spinnerCat.setAdapter(adapterCat);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        etDate = view.findViewById(R.id.etfechagasto);
        etDate.setText(day + " / " + (month + 1) + " / " + year);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        etTime = view.findViewById(R.id.ethoragasto);
        etTime.setText(hour + ":" + minute);
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        medio = (MedioTransporte) getActivity();
        btcancelar = view.findViewById(R.id.btcancelargasto);
        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medio.home();
            }
        });
        return view;
    }

    private void showTimePickerDialog() {
        TimePickerFragment newFragment = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                etTime.setText(time);
            }
        });
        newFragment.show(getChildFragmentManager(), "timePicker");
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                etDate.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}
