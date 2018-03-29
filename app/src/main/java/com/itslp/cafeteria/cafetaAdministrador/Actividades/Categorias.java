package com.itslp.cafeteria.cafetaAdministrador.Actividades;

import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.itslp.cafeteria.cafetaAdministrador.Carrito;
import com.itslp.cafeteria.cafetaAdministrador.R;

public class Categorias extends AppCompatActivity implements Carrito.ActualizarCarriroListener {
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
    }

    public void launchMenu(View view) {
        Intent intent = new Intent(this,ItemsListActivity.class);
        switch (view.getId()){
            case R.id.comidas:
                intent.putExtra("categoria", "Comidas");
                break;
            case R.id.snack:
                intent.putExtra("categoria","Snacks");
                break;
            case R.id.postres:
                intent.putExtra("categoria","Postres");
                break;
            case R.id.desayuno:
                intent.putExtra("categoria","Desayunos");
                break;
        }
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            onUpdate(Carrito.getInstance().getCarritoLista().size());
        }catch (NullPointerException ignored){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);
        this.menu = menu;
        onUpdate(Carrito.getInstance().getCarritoLista().size());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_cart:
                if (!Carrito.getInstance().isEmpty())
                    startActivity(new Intent(this,CarroActivity.class));
                else
                    Toast.makeText(this, "Carrito vacio!", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    public void onUpdate(int count) {

        MenuItem itemCart = menu.findItem(R.id.action_cart);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        Carrito.setBadgeCount(this, icon, count);
    }
}
