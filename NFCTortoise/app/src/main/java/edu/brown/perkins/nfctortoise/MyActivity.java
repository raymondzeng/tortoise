package edu.brown.perkins.nfctortoise;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Locale;
import java.nio.charset.Charset;

import android.nfc.*;
import android.content.*;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.app.PendingIntent;

public class MyActivity extends Activity {

    private NfcAdapter mNfcAdapter;
    private TextView mTextView;
    private PendingIntent mNfcPendingIntent;
    private NdefMessage mNdefMessage;
    private IntentFilter ndefDetected;
    private IntentFilter[] mNdefExchangeFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter[] filters = new IntentFilter[] { tagDetected };
    }

    @Override
    public void onResume() {
        super.onResume();
        PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        NfcAdapter.getDefaultAdapter(this).enableForegroundDispatch(this,intent,
                null, null);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (NfcAdapter.getDefaultAdapter(this) != null)
            NfcAdapter.getDefaultAdapter(this).disableForegroundDispatch(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
