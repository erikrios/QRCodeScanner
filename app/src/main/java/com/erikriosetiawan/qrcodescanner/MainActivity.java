package com.erikriosetiawan.qrcodescanner;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonScan;
    private TextView textViewScanFormat, textViewScanContent;
    private LinearLayout linearLayoutSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScan = findViewById(R.id.button_scan);
        textViewScanFormat = findViewById(R.id.text_view_scan_format);
        textViewScanContent = findViewById(R.id.text_view_scan_content);
        linearLayoutSearch = findViewById(R.id.linear_search);

        buttonScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        linearLayoutSearch.setVisibility(View.GONE);
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan QRcode");
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                linearLayoutSearch.setVisibility(View.GONE);
                Toast.makeText(this, "Keluar", Toast.LENGTH_LONG).show();
            } else {
                linearLayoutSearch.setVisibility(View.VISIBLE);
                textViewScanContent.setText(result.getContents());
                textViewScanFormat.setText(result.getFormatName());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}