package com.example.sistemagestiongastos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MedioTransporte {
    HomeFragment homeF;
    IncomeFragment ingresosF;
    ExpenseFragment gastosF;
    TransferFragment transferenciasF;
    MovementListFragment movimientosF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home");
        ingresosF = new IncomeFragment();
        gastosF = new ExpenseFragment();
        transferenciasF = new TransferFragment();
        movimientosF = new MovementListFragment();
        homeF = new HomeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.flContainer, homeF).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuHome:
                this.setTitle("Home");
                homeF = new HomeFragment();
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, homeF).commit();
                break;
            case R.id.menuIngresos:
                this.setTitle("Ingresos");
                ingresosF = new IncomeFragment();
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, ingresosF).commit();
                break;
            case R.id.menuGastos:
                this.setTitle("Gastos");
                gastosF = new ExpenseFragment();
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, gastosF).commit();
                break;
            case R.id.menuTrasferencias:
                this.setTitle("Transferencias");
                transferenciasF = new TransferFragment();
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, transferenciasF).commit();
                break;
            case R.id.menuListaMovimientos:
                this.setTitle("Lista de Movimientos");
                movimientosF = new MovementListFragment();
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, movimientosF).commit();
                break;
            case android.R.id.home:
                this.setTitle("Home");
                homeF = new HomeFragment();
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, homeF).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ingresos(int fuenteid) {
        this.setTitle("Ingresos");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bolsa = new Bundle();
        bolsa.putInt("index", fuenteid);
        ingresosF = new IncomeFragment();
        ingresosF.setArguments(bolsa);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, ingresosF).commit();
    }

    @Override
    public void gastos(int fuenteid) {
        this.setTitle("Gastos");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bolsa = new Bundle();
        bolsa.putInt("index", fuenteid);
        gastosF = new ExpenseFragment();
        gastosF.setArguments(bolsa);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, gastosF).commit();
    }

    @Override
    public void transfer(int fuenteid) {
        this.setTitle("Transferencias");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bolsa = new Bundle();
        bolsa.putInt("index", fuenteid);
        transferenciasF = new TransferFragment();
        transferenciasF.setArguments(bolsa);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, transferenciasF).commit();

    }

    @Override
    public void movimientos(int fuenteid) {
        this.setTitle("Lista de Movimientos");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bolsa = new Bundle();
        bolsa.putInt("index", fuenteid);
        movimientosF = new MovementListFragment();
        movimientosF.setArguments(bolsa);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, movimientosF).commit();
    }

    @Override
    public void home(int fuenteid) {
        this.setTitle("Home");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Bundle bolsa = new Bundle();
        bolsa.putInt("index", fuenteid);
        homeF = new HomeFragment();
        homeF.setArguments(bolsa);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, homeF).commit();
    }

    @Override
    public void onBackPressed() {
        this.setTitle("Home");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (homeF.isVisible()) {
            super.onBackPressed();
        } else {
            homeF = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, homeF).commit();
        }
    }

}