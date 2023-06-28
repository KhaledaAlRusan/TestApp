package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

public class IntentFactory {
    public static Intent getStartLocalUpdateIntent(Activity activity, SdSwvRequest request) {
        return getStartUpdateActivityIntent(activity, request, "DPC2", "ACTION_UPDATE");
    }

    public static Intent getStartLocalCheckForUpdatesIntent(Activity activity, SdSwvRequest request) {
        return getStartUpdateActivityIntent(activity, request, "DPC2", "ACTION_CHECK_FOR_UPDATES");
    }

    public static Intent getStartLocalUninstallIntent(Activity activity, SdSwvRequest request) {
        return getStartUpdateActivityIntent(activity, request, "DPC2", "ACTION_UNINSTALL");
    }

    public static Intent getStartCentralUpdateIntent(Activity activity, SdSwvRequest request) {
        return getStartUpdateActivityIntent(activity, request, "CENTRAL", "ACTION_UPDATE");
    }

    public static Intent getStartCentralCheckForUpdatesIntent(Activity activity, SdSwvRequest request) {
        return getStartUpdateActivityIntent(activity, request, "CENTRAL", "ACTION_CHECK_FOR_UPDATES");
    }

    public static Intent getStartCentralUninstallIntent(Activity activity, SdSwvRequest request) {
        return getStartUpdateActivityIntent(activity, request, "CENTRAL", "ACTION_UNINSTALL");
    }
    private static Intent getStartUpdateActivityIntent(Activity activity,
                                                       SdSwvRequest request, String serverType, String action) {
        Intent intent = new Intent("com.dekra.d3m.ui.UpdateActivity");
        intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_ACTION", action);
        if ("CENTRAL".equals(serverType)) {

            intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_UPDATE_USER", request.getUser());

            intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_UPDATE_TOKEN", request.getToken());

            intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_UPDATE_COOKIE", request.getCookie());
        } else if (request.getSWVServerUrl() != null) {

            intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_SERVER_URL", request.getSWVServerUrl());
        }
        if (serverType != null) {

            intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_UPDATE_SERVER_TYP E", serverType);
        }
        String packageName = request.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            packageName = activity.getPackageName();
        }

        intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_PACKAGE_NAME",
                packageName);

        intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_UPGRADE_ONLY",
                request.isUpdateOnly());
        intent.putExtra("com.dekra.swv.core.model.constants.EXTRA_AUTO_CLOSE",
                true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.dekra.swd");
        if (!isIntentAvailable(activity, intent)) {
            intent.setPackage((String) null);
        }
        if (isIntentAvailable(activity, intent)) {
            return intent;
        } else {
            Toast.makeText(activity, "Das Update konnte nicht gestartet werden, ist der SWV Client auf dem Gerät installiert?", Toast.LENGTH_LONG).show();
            return null;
        }
    }
    private static boolean isIntentAvailable(Activity activity, Intent intent) {
        PackageManager packageManager = activity.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
