package com.designsrich.EncryptionYakupZengin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

public class CaesarEncryptionMain extends AppCompatActivity {

    private EditText keyInput;
    private EditText newKeyInput;
    private EditText textInput;
    private TextView encryptionText;
    private TextView decodedText;
    private Button decodeBtn;
    private Button encryptionBtn;
    // private String alfabe = "abcçdefgğhıijklmnoöprsştuüvyz";
    private String alfabe = "abcdefghijklmnopqrstuvwxyz";
    private String receivedText;
    private String cryptogram;
    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_caesar_main );

        keyInput = findViewById( R.id.keyInput );
        newKeyInput= findViewById( R.id.newKeyInputCaesar );
        textInput = findViewById( R.id.textInput );
        encryptionText = findViewById( R.id.encryptedText );
        encryptionBtn = findViewById( R.id.encryptionBtn );
        decodedText = findViewById( R.id.decodedText );
        decodeBtn = findViewById( R.id.decodeBtn );

        keyInput.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptionText.setText( "" );
                decodedText.setText( "" );
            }
        } );

        encryptionBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(textInput.getText().toString()) && !TextUtils.isEmpty(keyInput.getText().toString())) {
                    receivedText = textInput.getText().toString().toLowerCase();
                    key = Integer.parseInt( keyInput.getText().toString());
                    encyriptionFunction( receivedText, key );
                    textInput.setVisibility( View.GONE );
                    keyInput.setVisibility(View.GONE );
                    newKeyInput.setVisibility( View.VISIBLE );
                    encryptionBtn.setVisibility( View.GONE );


                }
                else {
                    Toast.makeText( CaesarEncryptionMain.this, "Lütfen Alanları Boş Bırakmayınız", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        
        decodeBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty( newKeyInput.getText().toString())) {
                    cryptogram = encryptionText.getText().toString();
                    decyrptionFunction( cryptogram, Integer.parseInt( newKeyInput.getText().toString() ) );
                }else{
                    Toast.makeText( CaesarEncryptionMain.this, "Lütfen İlk Önce Metni Şifrele!", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    /**
     *
     * @param plainText
     * @param key
     * @throws StringIndexOutOfBoundsException
     */

    public void encyriptionFunction(String plainText, int key) throws StringIndexOutOfBoundsException{

        String cyrptic="";
        for(int i =0;i<plainText.length();i++){
            for(int j=0;j<26;j++){
                if(receivedText.charAt(i)==alfabe.charAt(j)){
                    cyrptic += alfabe.charAt((j+key)%26);
                }
            }
        }
        encryptionText.setText(cyrptic);
    }

    /**
     *
     * @param cryptogram
     * @param key
     * @throws StringIndexOutOfBoundsException
     */

    public void decyrptionFunction(String cryptogram, int key) throws StringIndexOutOfBoundsException{

        String decodeText="";
        for(int i =0;i<cryptogram.length();i++){
            for(int j=0;j<26;j++){
                if(cryptogram.charAt(i)==alfabe.charAt(j)){
                    if((j-key)<0){
                        int deneme = j-key;
                        Log.i( "DenemeLog",String.valueOf(deneme));
                        decodeText +=alfabe.charAt(((j+26*key)-key)%26);
                    }
                    else{
                        decodeText +=alfabe.charAt((j-key)%26);
                    }
                }
            }
        }
        decodedText.setText(decodeText);
    }


}
