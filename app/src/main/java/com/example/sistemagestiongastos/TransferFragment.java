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
import android.widget.Toast;

import java.util.Calendar;

import models.TransferModel;
import newadapter.SpinnerNewAdapter;

public class TransferFragment extends Fragment {

    String[] textArray = {"Tarjeta", "Efectivo", "Ahorros"};
    Integer[] imageArray = {R.drawable.credit_card, R.drawable.money, R.drawable.piggy_bank};
    Spinner spinner;
    SpinnerNewAdapter adapter;

    Spinner spinnerDest;
    SpinnerNewAdapter adapterDest;

    EditText etDate;
    EditText etTime;

    Button btcancelar;
    MedioTransporte medio;

    Button btguardar;
    Controller controller;

    public TransferFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);
        spinner = view.findViewById(R.id.spfuenteorigen);
        adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
        spinner.setAdapter(adapter);
        int index;
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        } else {
            index = 0;
        }
        spinner.setSelection(index);
        spinnerDest = view.findViewById(R.id.spfuentedestino);
        adapterDest = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
        spinnerDest.setAdapter(adapterDest);

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
        etTime.setText(((hour < 10) ? "0" + hour : hour) + ":" + ((minute < 10) ? "0" + minute : minute));
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        medio = (MedioTransporte) getActivity();
        btcancelar = view.findViewById(R.id.btcancelartransferencia);
        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medio.home(spinner.getSelectedItemPosition());
            }
        });

        btguardar = view.findViewById(R.id.btguardartransferencia);
        btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller = new Controller(getContext());
                EditText etmonto = view.findViewById(R.id.etnumtransfer);
                EditText etdescripcion = view.findViewById(R.id.etdesctransfer);
                if (etmonto.getText().toString().isEmpty() || etdescripcion.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                } else if (spinner.getSelectedItemPosition() == spinnerDest.getSelectedItemPosition()) {
                    Toast.makeText(getContext(), "La cuenta de origen y la cuenta de destino deben ser diferentes.", Toast.LENGTH_SHORT).show();
                } else {
                    Double monto = Double.parseDouble(etmonto.getText().toString());
                    int idfuenteorigen = spinner.getSelectedItemPosition() + 1;
                    int idfuentedestino = spinnerDest.getSelectedItemPosition() + 1;
                    String descripcion = etdescripcion.getText().toString();
                    String date[] = etDate.getText().toString().split(" / ");
                    String fechahora = (date[2] + "-" + date[1] + "-" + date[0]) + " " + etTime.getText().toString() + ":00";

                    TransferModel objtransfer = new TransferModel(monto, idfuenteorigen, idfuentedestino, descripcion, fechahora);
                    long res = controller.altaTransferencia(objtransfer);
                    if (res > 0) {
                        Toast.makeText(getContext(), "Transferencia Registrada", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                    }
                    etmonto.setText("");
                    etdescripcion.setText("");
                    etDate.setText(day + " / " + (month + 1) + " / " + year);
                    etTime.setText(((hour < 10) ? "0" + hour : hour) + ":" + ((minute < 10) ? "0" + minute : minute));
                }

            }
        });
        return view;
    }

    private void showTimePickerDialog() {
        TimePickerFragment newFragment = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String time = (hourOfDay < 10 ? "0" + hourOfDay : hourOfDay) + ":" + (minute < 10 ? "0" + minute : minute);
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
