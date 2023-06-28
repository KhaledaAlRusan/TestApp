package com.example.testapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerApp;
    private Spinner spinnerAction;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchUpdateMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerApp = findViewById(R.id.spinnerApp);
        spinnerAction = findViewById(R.id.spinnerAction);
        switchUpdateMode = findViewById(R.id.switchUpdateMode);

        initializeSpinners();

        setupListeners();
    }

    private void initializeSpinners() {
        ArrayAdapter<String> actionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Constants.ACTIONS);
        spinnerAction.setAdapter(actionAdapter);

        ArrayAdapter<AppInfo> appAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Constants.APPS);
        spinnerApp.setAdapter(appAdapter);
    }

    private void setupListeners() {
        spinnerApp.setOnItemSelectedListener(getAppItemSelectedListener());
        spinnerAction.setOnItemSelectedListener(getActionItemSelectedListener());
    }

    private AdapterView.OnItemSelectedListener getAppItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                launchIntent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    private AdapterView.OnItemSelectedListener getActionItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                launchIntent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    private void launchIntent() {
        AppInfo selectedApp = (AppInfo) spinnerApp.getSelectedItem();
        String selectedAction = (String) spinnerAction.getSelectedItem();
        boolean isCentral = switchUpdateMode.isChecked();
        SdSwvRequest request = new SdSwvRequest("user", "token", "cookie", "http://server.url", selectedApp.getPackageName(), false);
        Intent intent = null;

        if (isCentral) {
            switch (selectedAction) {
                case "ACTION_UPDATE":
                    intent = IntentFactory.getStartCentralUpdateIntent(this, request);
                    break;
                case "ACTION_CHECK_FOR_UPDATES":
                    intent = IntentFactory.getStartCentralCheckForUpdatesIntent(this, request);
                    break;
                case "ACTION_UNINSTALL":
                    intent = IntentFactory.getStartCentralUninstallIntent(this, request);
                    break;
            }
        } else {
            switch (selectedAction) {
                case "ACTION_UPDATE":
                    intent = IntentFactory.getStartLocalUpdateIntent(this, request);
                    break;
                case "ACTION_CHECK_FOR_UPDATES":
                    intent = IntentFactory.getStartLocalCheckForUpdatesIntent(this, request);
                    break;
                case "ACTION_UNINSTALL":
                    intent = IntentFactory.getStartLocalUninstallIntent(this, request);
                    break;
            }
        }

        if (intent != null) {
            startActivityForResult(intent, Constants.REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                String result = data.getStringExtra("RESULT");
                Toast.makeText(this, "Result: " + result, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No result returned", Toast.LENGTH_LONG).show();
            }
        }
    }
}
