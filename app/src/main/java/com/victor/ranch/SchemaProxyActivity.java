package com.victor.ranch;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.victor.ranch.module.SchemaManager;

public class SchemaProxyActivity extends AppCompatActivity {

    public static void intentStart(Context ctx, Uri uri) {
        if (ctx == null)
            return;
        final Context context = ctx.getApplicationContext();
        Intent scheme = new Intent(context, SchemaProxyActivity.class);
        scheme.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        scheme.setData(uri);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, scheme, PendingIntent.FLAG_CANCEL_CURRENT);
        try {
            pendingIntent.send(context, 0, scheme);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri temp = getIntent().getData();
        dispatchScheme(temp);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dispatchScheme(intent.getData());
    }

    private void dispatchScheme(Uri data) {
        SchemaManager.dispatchSchema(this, data);
        finish();
    }

}
