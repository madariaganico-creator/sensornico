package com.sensornico.freestyle2.nfc

import android.app.Activity
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class NfcReaderActivity : Activity() {
    
    companion object {
        private const val TAG = "NfcReaderActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleNfcIntent(intent)
    }
    
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleNfcIntent(intent)
    }
    
    private fun handleNfcIntent(intent: Intent) {
        val action = intent.action
        
        if (NfcAdapter.ACTION_TECH_DISCOVERED == action) {
            val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
            tag?.let {
                val nfcReader = NfcReader()
                val glucoseReading = nfcReader.readSensorData(it)
                
                if (glucoseReading != null) {
                    Log.d(TAG, "Lectura exitosa: ${glucoseReading.glucoseValue} mg/dL")
                    Toast.makeText(this, "Glucosa: ${glucoseReading.glucoseValue} mg/dL", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "Error al leer sensor")
                    Toast.makeText(this, "Error al leer sensor", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
