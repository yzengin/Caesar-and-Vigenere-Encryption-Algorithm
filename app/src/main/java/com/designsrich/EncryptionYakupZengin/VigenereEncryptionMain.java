package com.designsrich.EncryptionYakupZengin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author Yakup Zengin - yakup [at] designsrich [dot] com
 * http://www.designsrich.com
 * @version 0.0.1
 */


public class VigenereEncryptionMain extends AppCompatActivity {

    private EditText textInput;
    private EditText keyInput;
    private EditText newKeyInput;
    private TextView encryptedText;
    private TextView decryptedText;
    private Button encryptionButton;
    private Button decryptionButton;
    private String mkey ;
    private String mtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_vigenere_main );
        textInput = findViewById(R.id.textInput);
        keyInput =  findViewById(R.id.keyInput);
        newKeyInput =  findViewById(R.id.newKeyInput);
        encryptedText = findViewById(R.id.encryptedText);
        decryptedText = findViewById(R.id.decryptedText);

        encryptionButton= findViewById( R.id.encryptionBtn );
        decryptionButton= findViewById( R.id.decryptionBtn );
        encryptionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(keyInput.getText().toString()) && !TextUtils.isEmpty( textInput.getText().toString() )){
                    encryptedText.setText(encryptionFunction());
                    keyInput.setVisibility(View.GONE );
                    newKeyInput.setVisibility( View.VISIBLE );
                    encryptionButton.setVisibility( View.GONE );
                    textInput.setVisibility( View.GONE );
                }else{
                    Toast.makeText( VigenereEncryptionMain.this, "Lütfen Boş Alanları Giriniz", Toast.LENGTH_SHORT ).show();

                }

            }
        } );

        decryptionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(keyInput.getText().toString()) && TextUtils.isEmpty( textInput.getText().toString())){
                    Toast.makeText( VigenereEncryptionMain.this, "Lütfen İlk Önce Metni Şifrele", Toast.LENGTH_SHORT ).show();
                }
                else if(TextUtils.isEmpty(textInput.getText().toString()) && !TextUtils.isEmpty(keyInput.getText().toString() ) ){
                    Toast.makeText( VigenereEncryptionMain.this, "Lütfen ilk once metni şifrele!", Toast.LENGTH_SHORT ).show();
                }
                else if(!TextUtils.isEmpty( newKeyInput.getText().toString())){
                    decryptedText.setText(decryptionFunction(newKeyInput.getText().toString()));
                }
                else {
                    Toast.makeText( VigenereEncryptionMain.this, "Lütfen eski anahtarı gir!", Toast.LENGTH_SHORT ).show();
                }

            }
        } );
    }

    public String encryptionFunction() {
        String encrypt="";
        mtext = textInput.getText().toString().toLowerCase();
        mkey = keyInput.getText().toString().toLowerCase();
        char karakter1,karakter2;
        for (int i = 0; i < mtext.length(); i++) {
            if (' ' != mtext.charAt(i)) {
                if (mkey.charAt(i % mkey.length()) != ' ')
                    karakter2 = mkey.charAt(i % mkey.length());
                else
                    karakter2 = 'a' - 1;
                karakter1 = (char) (mtext.charAt(i) + karakter2 - 'a');
                if (karakter1 > 'z') karakter1 -= 26;
            } else
                karakter1 = ' ';
            encrypt += String.valueOf(karakter1);
        }

        return encrypt;
    }

    public String decryptionFunction(final String mkey) {
        String decyrpt = "";
        String yeniText = encryptionFunction();
        char karakter;
        for (int i = 0, j = 0; i < yeniText.length(); i++) {
            karakter = yeniText.charAt(i);
            if (karakter < 'a' || karakter > 'z') continue;
            decyrpt += (char)((karakter - mkey.charAt(j) + 26) % 26 + 'a');
            j = ++j % mkey.length();
        }
        return decyrpt;
    }

}
