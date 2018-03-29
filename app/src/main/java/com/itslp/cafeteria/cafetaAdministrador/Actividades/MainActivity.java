package com.itslp.cafeteria.cafetaAdministrador.Actividades;

import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.itslp.cafeteria.cafetaAdministrador.Carrito;
import com.itslp.cafeteria.cafetaAdministrador.R;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Carrito.ActualizarCarriroListener {

    ImageView profileImageView;
    TextView emailIdTextView;

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Carrito.getInstance().setActualizarCarriroListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        profileImageView = (ImageView) findViewById(R.id.avatarImageView);
        emailIdTextView = (TextView) findViewById(R.id.emailIdTextView);
        emailIdTextView.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        setAvatar();

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CambiarAvatarActivity.class));
            }
        });



    }

    private void setAvatar() {
        switch (getSelectedAvatar()){
            case 1:
                profileImageView.setImageResource(R.drawable.avatar_1);
                break;
            case 2:
                profileImageView.setImageResource(R.drawable.avatar_2);
                break;
            case 3:
                profileImageView.setImageResource(R.drawable.avatar_3);
                break;
            case 4:
                profileImageView.setImageResource(R.drawable.avatar_4);
                break;
            case 5:
                profileImageView.setImageResource(R.drawable.avatar_5);
                break;
            case 6:
                profileImageView.setImageResource(R.drawable.avatar_6);
                break;
            case 7:
                profileImageView.setImageResource(R.drawable.avatar_7);
                break;
            case 8:
                profileImageView.setImageResource(R.drawable.avatar_8);
                break;
            case 9:
                profileImageView.setImageResource(R.drawable.avatar_9);
                break;
            case 10:
                profileImageView.setImageResource(R.drawable.avatar_10);
                break;
            case 11:
                profileImageView.setImageResource(R.drawable.avatar_11);
                break;
        }
    }

    private int getSelectedAvatar() {
        return getSharedPreferences("prefs",MODE_PRIVATE).getInt("AVATAR",new Random().nextInt(11));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAvatar();
        try {
            onUpdate(Carrito.getInstance().getCarritoLista().size());
        }catch (NullPointerException ignored){}
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
                return true;
            default:
                return false;
        }
    }

    public void launchMenu(View view) {
        Intent intent = new Intent(this,ItemsListActivity.class);
        switch (view.getId()){
            case R.id.comidas:
                intent.putExtra("categoria", "comidas");
                break;
            case R.id.desayuno:
                intent.putExtra("categoria","desayunos");
                break;
            case R.id.snack:
                intent.putExtra("categoria","snack");
                break;
            case R.id.postres:
                intent.putExtra("categoria","postres");
                break;
        }
        startActivity(intent);

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
                    Toast.makeText(this, "El carrito esta vacio", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    public void launchFavoritos (View view){

        Snackbar.make(view, "Aun no cuentas con promociones", Snackbar.LENGTH_LONG).show();
        //startActivity(new Intent(this,Categorias.class));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    public void launchCategorias (View view){
        startActivity(new Intent(this,Categorias.class));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    public void launchHistory(View view) {
        startActivity(new Intent(this,HistoriaActivity.class));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    public void CerrarSesion(View view) {
        Toast.makeText(this, "Hasta pronto!..", Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,Login.class));
    }

    @Override
    public void onUpdate(int count) {
        MenuItem itemCart = menu.findItem(R.id.action_cart);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        Carrito.setBadgeCount(this, icon, count);
    }


}
