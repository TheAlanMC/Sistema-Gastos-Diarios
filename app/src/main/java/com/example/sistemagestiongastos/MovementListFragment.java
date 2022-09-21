package com.example.sistemagestiongastos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import models.ExpenseModel;
import models.IncomeModel;
import models.MovementModel;
import models.TransferModel;
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
    RecyclerView rvmovimiento;
    MovementAdapter adaptador;
    Controller controller;

    String balance;

    TextView tvbalance;

    String[] textArrayCatInc = {"Prestamo", "Salario", "Ventas"};
    Integer[] imageArrayCatInc = {R.drawable.loan, R.drawable.salary, R.drawable.sales};
    String[] textArrayCatExp = {"Otros", "Ropa", "Bebidas", "Educacion", "Comida", "Combustible", "Diversion", "Salud", "Viaje", "Hotel",
            "Mercaderia", "Personal", "Mascotas", "Restaurante", "Propinas", "Transporte"};
    Integer[] imageArrayCatExp = {R.drawable.question, R.drawable.clothes, R.drawable.drink, R.drawable.education, R.drawable.food,
            R.drawable.fuel, R.drawable.fun, R.drawable.healthcare, R.drawable.road, R.drawable.hotel, R.drawable.box,
            R.drawable.hands, R.drawable.paw, R.drawable.restaurant, R.drawable.tip, R.drawable.taxi};

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
        rvmovimiento = view.findViewById(R.id.rvDataMovimientos);
        rvmovimiento.setLayoutManager(new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false));

        lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
        adaptador.setLista(lista);
        adaptador.notifyDataSetChanged();
        rvmovimiento.setAdapter(adaptador);

        balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
        tvbalance = view.findViewById(R.id.tvbalance);
        tvbalance.setText(balance);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lista = controller.obtenerMovimientos(position + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                adaptador.setLista(lista);
                adaptador.notifyDataSetChanged();
                rvmovimiento.setAdapter(adaptador);

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
                rvmovimiento.setAdapter(adaptador);

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
                rvmovimiento.setAdapter(adaptador);

                balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                tvbalance.setText(balance);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        rvmovimiento.addOnItemTouchListener(new Toques(ctx, rvmovimiento, new Toques.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(ctx, "Edicion", Toast.LENGTH_SHORT).show();
                alertaEdicion(position, ctx);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(ctx, "Eliminacion", Toast.LENGTH_SHORT).show();
                alertaBaja(position, ctx);
            }
        }));

        return view;
    }

    private void alertaBaja(int position, Context ctx) {
        TextView tvfechahora, tvcategoria, tvmonto, tvdescripcion, tvingresogasto;
        ImageView ivimagen;

        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_movimiento, null);
        tvfechahora = view.findViewById(R.id.tvFechaHora);
        tvcategoria = view.findViewById(R.id.tvCategoria);
        tvmonto = view.findViewById(R.id.tvMonto);
        tvdescripcion = view.findViewById(R.id.tvDescripcion);
        tvingresogasto = view.findViewById(R.id.tvIngresoGasto);
        ivimagen = view.findViewById(R.id.ivMovimiento);

        String[] fechahora = lista.get(position).getFechaHoraMovimiento().split(" ");
        String[] fecha = fechahora[0].split("-");
        String[] hora = fechahora[1].split(":");
        tvfechahora.setText(fecha[2] + " de " + mes[Integer.parseInt(fecha[1]) - 1] + " del " + fecha[0] + " a las " + hora[0] + ":" + hora[1]);

        int tipo = lista.get(position).getTipoMovimiento();

        String descripcion = "";
        if (tipo == 1) {
            tvcategoria.setText(textArrayCatInc[lista.get(position).getCategoriaIdMovimiento() - 1]);
            ivimagen.setImageResource(imageArrayCatInc[lista.get(position).getCategoriaIdMovimiento() - 1]);
            tvingresogasto.setText("+");
            tvingresogasto.setTextColor(Color.parseColor("#4CAF50"));
            tvdescripcion.setText(lista.get(position).getDescripcionMovimiento());
        } else if (tipo == 2) {
            tvcategoria.setText(textArrayCatExp[lista.get(position).getCategoriaIdMovimiento() - 1]);
            ivimagen.setImageResource(imageArrayCatExp[lista.get(position).getCategoriaIdMovimiento() - 1]);
            tvingresogasto.setText("-");
            tvingresogasto.setTextColor(Color.parseColor("#F44336"));
            tvdescripcion.setText(lista.get(position).getDescripcionMovimiento());
        } else if (tipo == 3) {
            tvcategoria.setText("Transferencia");
            ivimagen.setImageResource(R.drawable.left_right);
            tvingresogasto.setText("-");
            tvingresogasto.setTextColor(Color.parseColor("#F44336"));
            descripcion = lista.get(position).getDescripcionMovimiento() + (" (Transferencia de ") +
                    textArray[lista.get(position).getFuenteIdMovimiento() - 1] + (" a ") +
                    textArray[lista.get(position).getCategoriaIdMovimiento() - 1] + (")");
            tvdescripcion.setText(descripcion);
        } else if (tipo == 4) {
            tvcategoria.setText("Transferencia");
            ivimagen.setImageResource(R.drawable.left_right);
            tvingresogasto.setText("+");
            tvingresogasto.setTextColor(Color.parseColor("#4CAF50"));
            descripcion = lista.get(position).getDescripcionMovimiento() + (" (Transferencia de ") +
                    textArray[lista.get(position).getFuenteIdMovimiento() - 1] + (" a ") +
                    textArray[lista.get(position).getCategoriaIdMovimiento() - 1] + (")");
            tvdescripcion.setText(descripcion);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        String monto = df.format(lista.get(position).getMontoMovimiento());
        tvmonto.setText("Bs." + monto);

        AlertDialog.Builder alerta = new AlertDialog.Builder(ctx);
        alerta.setView(view);
        alerta.setTitle("Confirmar");
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar el movimiento?");
        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int res;
                switch (tipo) {
                    case 1:
                        res = controller.EliminarIngreso(lista.get(position).getIdMovimiento());
                        break;
                    case 2:
                        res = controller.EliminarGasto(lista.get(position).getIdMovimiento());
                        break;
                    default:
                        res = controller.EliminarTransferencia(lista.get(position).getIdMovimiento());
                        break;
                }
                if (res > 0) {
                    Toast.makeText(ctx, "Movimiento Eliminado", Toast.LENGTH_SHORT).show();
                    lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                    adaptador.setLista(lista);
                    adaptador.notifyDataSetChanged();
                    rvmovimiento.setAdapter(adaptador);
                    balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                    tvbalance.setText(balance);

                } else {
                    Toast.makeText(ctx, "Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alerta.show();
    }

    private void alertaEdicion(int position, Context ctx) {

        Dialog dialog = new Dialog(ctx, R.style.Dialog);
        int tipo = lista.get(position).getTipoMovimiento();
        LayoutInflater inflater = this.getLayoutInflater();
        View view;
        if (tipo == 1) {
            view = inflater.inflate(R.layout.fragment_income, null);
            dialog.setContentView(view);
            dialog.setTitle("Editar Ingreso");
            dialog.setCancelable(false);

            Window window = dialog.getWindow();
            window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dialog.show();

            Spinner spinnerFact = view.findViewById(R.id.spfuenteingresos);
            SpinnerNewAdapter adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
            spinnerFact.setAdapter(adapter);
            spinnerFact.setSelection(lista.get(position).getFuenteIdMovimiento() - 1);

            Spinner spinnerCat = view.findViewById(R.id.spcategoriaingresos);
            SpinnerNewAdapter adapterCat = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArrayCatInc, imageArrayCatInc);
            spinnerCat.setAdapter(adapterCat);
            spinnerCat.setSelection(lista.get(position).getCategoriaIdMovimiento() - 1);

            EditText etmonto = view.findViewById(R.id.etnumingreso);
            etmonto.setText(String.valueOf(lista.get(position).getMontoMovimiento()));

            EditText etdescripcion = view.findViewById(R.id.etdescingreso);
            etdescripcion.setText(lista.get(position).getDescripcionMovimiento());

            String fulldate[] = lista.get(position).getFechaHoraMovimiento().split(" ");

            EditText etDate = view.findViewById(R.id.etfechaingreso);
            String date[] = fulldate[0].split("-");
            String fecha = date[2] + " / " + date[1] + " / " + date[0];
            etDate.setText(fecha);
            etDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog();
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
            });

            EditText etTime = view.findViewById(R.id.ethoraingreso);
            String time[] = fulldate[1].split(":");
            etTime.setText(time[0] + ":" + time[1]);
            etTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTimePickerDialog();
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
            });

            Button btcancelar = view.findViewById(R.id.btcancelaringreso);
            btcancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Button btguardar = view.findViewById(R.id.btguardaringreso);
            btguardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (etmonto.getText().toString().isEmpty() || etdescripcion.getText().toString().isEmpty()) {
                        Toast.makeText(getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        int id = lista.get(position).getIdMovimiento();
                        Double monto = Double.parseDouble(etmonto.getText().toString());
                        int idfuente = spinnerFact.getSelectedItemPosition() + 1;
                        int idcategoria = spinnerCat.getSelectedItemPosition() + 1;
                        String descripcion = etdescripcion.getText().toString();
                        String date[] = etDate.getText().toString().split(" / ");
                        String fechahora = (date[2] + "-" + date[1] + "-" + date[0]) + " " + etTime.getText().toString() + ":00";

                        IncomeModel objingreso = new IncomeModel(id, monto, idfuente, idcategoria, descripcion, fechahora);
                        long res = controller.ModificarIngreso(objingreso);
                        if (res > 0) {
                            Toast.makeText(getContext(), "Ingreso Modificado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                        }
                        lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                        adaptador.setLista(lista);
                        adaptador.notifyDataSetChanged();
                        rvmovimiento.setAdapter(adaptador);
                        balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                        tvbalance.setText(balance);
                        dialog.dismiss();
                    }


                }
            });
        } else if (tipo == 2) {
            {
                view = inflater.inflate(R.layout.fragment_expense, null);
                dialog.setContentView(view);
                dialog.setTitle("Editar Gasto");
                dialog.setCancelable(false);

                Window window = dialog.getWindow();
                window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                dialog.show();

                Spinner spinnerFact = view.findViewById(R.id.spfuentegastos);
                SpinnerNewAdapter adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
                spinnerFact.setAdapter(adapter);
                spinnerFact.setSelection(lista.get(position).getFuenteIdMovimiento() - 1);

                Spinner spinnerCat = view.findViewById(R.id.spcategoriagastos);
                SpinnerNewAdapter adapterCat = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArrayCatExp, imageArrayCatExp);
                spinnerCat.setAdapter(adapterCat);
                spinnerCat.setSelection(lista.get(position).getCategoriaIdMovimiento() - 1);

                EditText etmonto = view.findViewById(R.id.etnumgasto);
                etmonto.setText(String.valueOf(lista.get(position).getMontoMovimiento()));

                EditText etdescripcion = view.findViewById(R.id.etdescgasto);
                etdescripcion.setText(lista.get(position).getDescripcionMovimiento());

                String fulldate[] = lista.get(position).getFechaHoraMovimiento().split(" ");

                EditText etDate = view.findViewById(R.id.etfechagasto);
                String date[] = fulldate[0].split("-");
                String fecha = date[2] + " / " + date[1] + " / " + date[0];
                etDate.setText(fecha);
                etDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog();
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
                });

                EditText etTime = view.findViewById(R.id.ethoragasto);
                String time[] = fulldate[1].split(":");
                etTime.setText(time[0] + ":" + time[1]);
                etTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTimePickerDialog();
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
                });

                Button btcancelar = view.findViewById(R.id.btcancelargasto);
                btcancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button btguardar = view.findViewById(R.id.btguardargasto);
                btguardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (etmonto.getText().toString().isEmpty() || etdescripcion.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                        } else {
                            int id = lista.get(position).getIdMovimiento();
                            Double monto = Double.parseDouble(etmonto.getText().toString());
                            int idfuente = spinnerFact.getSelectedItemPosition() + 1;
                            int idcategoria = spinnerCat.getSelectedItemPosition() + 1;
                            String descripcion = etdescripcion.getText().toString();
                            String date[] = etDate.getText().toString().split(" / ");
                            String fechahora = (date[2] + "-" + date[1] + "-" + date[0]) + " " + etTime.getText().toString() + ":00";

                            ExpenseModel objgasto = new ExpenseModel(id, monto, idfuente, idcategoria, descripcion, fechahora);
                            long res = controller.ModificarGasto(objgasto);
                            if (res > 0) {
                                Toast.makeText(getContext(), "Gasto Modificado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                            }
                            lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                            adaptador.setLista(lista);
                            adaptador.notifyDataSetChanged();
                            rvmovimiento.setAdapter(adaptador);
                            balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                            tvbalance.setText(balance);
                            dialog.dismiss();
                        }


                    }
                });
            }


        } else {
            {
                view = inflater.inflate(R.layout.fragment_transfer, null);
                dialog.setContentView(view);
                dialog.setTitle("Editar Transferencia");
                dialog.setCancelable(false);

                Window window = dialog.getWindow();
                window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                dialog.show();

                Spinner spinnerOrg = view.findViewById(R.id.spfuenteorigen);
                SpinnerNewAdapter adapter = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
                spinnerOrg.setAdapter(adapter);
                spinnerOrg.setSelection(lista.get(position).getFuenteIdMovimiento() - 1);

                Spinner spinnerDest = view.findViewById(R.id.spfuentedestino);
                SpinnerNewAdapter adapterCat = new SpinnerNewAdapter(getContext(), R.layout.spinner_value_layout, textArray, imageArray);
                spinnerDest.setAdapter(adapterCat);
                spinnerDest.setSelection(lista.get(position).getCategoriaIdMovimiento() - 1);

                EditText etmonto = view.findViewById(R.id.etnumtransfer);
                etmonto.setText(String.valueOf(lista.get(position).getMontoMovimiento()));

                EditText etdescripcion = view.findViewById(R.id.etdesctransfer);
                etdescripcion.setText(lista.get(position).getDescripcionMovimiento());

                String fulldate[] = lista.get(position).getFechaHoraMovimiento().split(" ");

                EditText etDate = view.findViewById(R.id.etfechatransfer);
                String date[] = fulldate[0].split("-");
                String fecha = date[2] + " / " + date[1] + " / " + date[0];
                etDate.setText(fecha);
                etDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog();
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
                });

                EditText etTime = view.findViewById(R.id.ethoratransfer);
                String time[] = fulldate[1].split(":");
                etTime.setText(time[0] + ":" + time[1]);
                etTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTimePickerDialog();
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
                });

                Button btcancelar = view.findViewById(R.id.btcancelartransferencia);
                btcancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button btguardar = view.findViewById(R.id.btguardartransferencia);
                btguardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etmonto.getText().toString().isEmpty() || etdescripcion.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                        } else if (spinnerOrg.getSelectedItemPosition() == spinnerDest.getSelectedItemPosition()) {
                            Toast.makeText(getContext(), "La cuenta de origen y la cuenta de destino deben ser diferentes.", Toast.LENGTH_SHORT).show();
                        } else {
                            int id = lista.get(position).getIdMovimiento();
                            Double monto = Double.parseDouble(etmonto.getText().toString());
                            int idfuenteorigen = spinnerOrg.getSelectedItemPosition() + 1;
                            int idfuentedestino= spinnerDest.getSelectedItemPosition() + 1;
                            String descripcion = etdescripcion.getText().toString();
                            String date[] = etDate.getText().toString().split(" / ");
                            String fechahora = (date[2] + "-" + date[1] + "-" + date[0]) + " " + etTime.getText().toString() + ":00";

                            TransferModel objtransfer= new TransferModel(id, monto, idfuenteorigen, idfuentedestino, descripcion, fechahora);
                            long res = controller.ModificarTransferencia(objtransfer);
                            if (res > 0) {
                                Toast.makeText(getContext(), "Transferencia Modificada", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                            }
                            lista = controller.obtenerMovimientos(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                            adaptador.setLista(lista);
                            adaptador.notifyDataSetChanged();
                            rvmovimiento.setAdapter(adaptador);
                            balance = controller.ObtenerBalance(spinner.getSelectedItemPosition() + 1, spmes.getSelectedItemPosition() + 1, Integer.parseInt(spanio.getSelectedItem().toString()));
                            tvbalance.setText(balance);
                            dialog.dismiss();
                        }


                    }
                });
            }


        }


    }
}