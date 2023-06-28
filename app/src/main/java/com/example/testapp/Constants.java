package com.example.testapp;

public class Constants {
    public static final String[] ACTIONS = {
            "ACTION_UPDATE",
            "ACTION_CHECK_FOR_UPDATES",
            "ACTION_UNINSTALL"
    };

    public static final AppInfo[] APPS = {
            new AppInfo("Adobe Acrobat", "com.adobe.reader"),
            new AppInfo("AKZ Winkelmaß", "com.dekra.mobility.sensorfusion"),
            new AppInfo("AudaMobile", "com.audatex.audamobile.inhouse"),
            new AppInfo("Barcode Scanner", "com.google.zxing.client.android"),
            new AppInfo("DEKRA AuG", "dekra.aug.app"),
            new AppInfo("DEKRA Bildbearbeitung", "com.dekra.pictureedit"),
            new AppInfo("DEKRA DSN Mobile Survey", "de.dekra.dsn.mobilesurvey"),
            new AppInfo("Dekra Print Service", "com.dekra.mobility.printservice"),
            new AppInfo("dekra-professional-second", "com.dekra.professional.second"),
            new AppInfo("Fisheye-Evaluator", "de.dekra.android.fisheye.production"),
            new AppInfo("Gerätesuche", "com.dekra.discovery.discovery_service"),
            new AppInfo("m-GApp ITU", "com.dekra.automobil.mobs.ggp.itu"),
            new AppInfo("Serviceportal", "com.dekra.dsp"),
            new AppInfo("Messe Frankfurt", "com.mwaysolutions.messefrankfurt.app")

    };

    public static final int REQUEST_CODE = 1;
}

