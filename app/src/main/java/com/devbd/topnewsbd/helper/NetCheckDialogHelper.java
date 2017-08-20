package com.devbd.topnewsbd.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * Created by morshed on 8/16/17.
 */

public class NetCheckDialogHelper {

    public static void dialogNotConnected(Context context){

        Toast.makeText(context, "Check internet", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    public static boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }
}
