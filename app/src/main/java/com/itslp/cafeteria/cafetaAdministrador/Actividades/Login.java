package com.itslp.cafeteria.cafetaAdministrador.Actividades;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.itslp.cafeteria.cafetaAdministrador.R;

import java.util.Collections;



public class Login extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST_CONST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                            .setTheme(R.style.GreenTheme)
                            .build(),
                    SIGN_IN_REQUEST_CONST);

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == SIGN_IN_REQUEST_CONST) {
            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                startActivity(new Intent(this,Login.class));
                finish();
            } else {
                if (resultCode == RESULT_CANCELED){
                    Toast.makeText(this,"solicitud cancelada", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,R.string.error_conexion, Toast.LENGTH_LONG).show();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setMessage(R.string.mensaje_reintentar)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivityForResult(
                                        AuthUI.getInstance()
                                                .createSignInIntentBuilder()
                                                .setProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                                                .build(),
                                        SIGN_IN_REQUEST_CONST);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });
                builder.create().show();
            }
        }
    }
}
