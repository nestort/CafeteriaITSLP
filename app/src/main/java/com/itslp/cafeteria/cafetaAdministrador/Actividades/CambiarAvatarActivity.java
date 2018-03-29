package com.itslp.cafeteria.cafetaAdministrador.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.itslp.cafeteria.cafetaAdministrador.R;

public class CambiarAvatarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_avatar);
        getSupportActionBar().setTitle(R.string.titulo_actividad_cambiar_avatar);
    }

    public void chooseAvatar(View view) {
        switch (view.getId()){
            case R.id.avatar_1:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",1).apply();
                break;
            case R.id.avatar_2:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",2).apply();
                break;
            case R.id.avatar_3:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",3).apply();
                break;
            case R.id.avatar_4:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",4).apply();
                break;
            case R.id.avatar_5:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",5).apply();
                break;
            case R.id.avatar_6:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",6).apply();
                break;
            case R.id.avatar_7:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",7).apply();
                break;
            case R.id.avatar_8:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",8).apply();
                break;
            case R.id.avatar_9:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",9).apply();
                break;
            case R.id.avatar_10:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",10).apply();
                break;
            case R.id.avatar_11:
                getSharedPreferences("prefs",MODE_PRIVATE).edit().putInt("AVATAR",11).apply();
                break;
        }
        finish();
    }
}
