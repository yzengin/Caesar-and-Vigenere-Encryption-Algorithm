package com.designsrich.EncryptionYakupZengin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Yakup Zengin - yakup [at] designsrich [dot] com
 * http://www.designsrich.com
 * @version 0.0.1
 */

public class MainActivity extends AppCompatActivity {
    private Button caesarButton;
    private Button vigenereButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        caesarButton = findViewById( R.id.caesarBtn);
        vigenereButton = findViewById(R.id.vigenereBtn);

        caesarButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent caesar = new Intent( MainActivity.this,CaesarEncryptionMain.class);
                startActivity( caesar);
            }
        } );
        vigenereButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vigenere = new Intent( MainActivity.this,VigenereEncryptionMain.class);
                startActivity( vigenere);
            }
        } );
    }
}
