package com.example.sistemagestiongastos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import models.ModeloIngreso;
import newadapter.SpinnerNewAdapter;

public class IncomeFragment extends Fragment {

    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money, R.drawable.piggy_bank};
    Spinner spinner;
    SpinnerNewAdapter adapter;

    String[] textArrayCat = {"Prestamo", "Salario", "Ventas"};
    Integer[] imageArrayCat = {R.drawable.loan, R.drawable.salary, R.drawable.sales};
    Spinner spinnerCat;
    SpinnerNewAdapter adapterCat;

    EditText etDate;
    EditText etTime;

    Button btcancelar;
    MedioTransporte medio;

    Button btguardar;
    Controller controller;

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
        spinner.setSelection(index);
        spinnerCat = view.findViewById(R.id.spcategoriaingresos);
        adapterCat = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArrayCat, imageArrayCat);
        spinnerCat.setAdapter(adapterCat);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        etDate = view.findViewById(R.id.etfechaingreso);
        etDate.setText(day + " / " + (month + 1) + " / " + year);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        etTime = view.findViewById(R.id.ethoraingreso);
        etTime.setText(hour + ":" + minute);
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        medio = (MedioTransporte) getActivity();
        btcancelar = view.findViewById(R.id.btcancelaringreso);
        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medio.home();
            }
        });


        btguardar = view.findViewById(R.id.btguardaringreso);
        btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller = new Controller(getContext());
                EditText etmonto = view.findViewById(R.id.etnumingreso);
                EditText etdescripcion = view.findViewById(R.id.etdescingreso);

                Double monto = Double.parseDouble(etmonto.getText().toString());
                int idfuente = spinner.getSelectedItemPosition() +1;
                int idcategoria = spinnerCat.getSelectedItemPosition() +1;
                String descripcion = etdescripcion.getText().toString();
                String date[] = etDate.getText().toString().split(" / ");
                String fechahora = (date[2]+"-"+date[1]+"-"+date[0])+" "+etTime.getText().toString()+":00";

                ModeloIngreso objingreso = new ModeloIngreso(monto, idfuente, idcategoria, descripcion, fechahora);
                long res = controller.altaIngreso(objingreso);
                if (res>0){
                    Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
                }
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
