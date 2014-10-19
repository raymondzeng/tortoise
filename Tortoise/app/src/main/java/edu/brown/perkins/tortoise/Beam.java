package edu.brown.perkins.tortoise;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.Charset;

public class Beam extends Activity implements CreateNdefMessageCallback {
    NfcAdapter mNfcAdapter;
    TextView textView;

    @Override
    /**
       Called when Activity started; can think of as a constructor
    **/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        //TextView textView = (TextView) findViewById(R.id.textView);
        
        // Check for available NFC Adapter
        System.out.println("Hi");
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            // finish() is part of the Activity class
            finish();
            return;
        }
        Log.d("oncreate", "working");
	System.out.println("mNfcAdapter is not null!");

        // Register callback
        mNfcAdapter.setNdefPushMessageCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        Log.d("oncreate", "working");
	System.out.println("Created an NDEF message!");
        String text = ("Beam me up, Android!\n\n" +
                       "Beam Time: " + System.currentTimeMillis());
        Log.e(text, text);
        NdefMessage msg = 
            new NdefMessage(new NdefRecord[]{
                    //NdefRecord.createUri("dsafasfasdfasdf"),
                    NdefRecord.createMime("text/plain", text.getBytes()),
                    // creates an AAR so this beamed message will always
                    // launch this application or prompt Google Play to
                    // show this app
                    // NdefRecord.createApplicationRecord("com.brown.tortoise")
                });
        return msg;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        //textView = (TextView) findViewById(R.id.textView);
        Parcelable[] rawMsgs = 
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        textView.setText(new String(msg.getRecords()[0].getPayload()));
    }
}
