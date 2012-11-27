package aexp.push;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.c2dm.C2DMBaseReceiver;
import com.google.android.c2dm.C2DMessaging;

import java.io.IOException;

/**
 * Broadcast receiver that handles Android Cloud to Data Messaging (AC2DM) messages, initiated
 * by the JumpNote App Engine server and routed/delivered by Google AC2DM servers. The
 * only currently defined message is 'sync'.
 */
public class C2DMReceiver extends C2DMBaseReceiver {
    static final String LOG_TAG = "C2DMRECEIVER";

    public C2DMReceiver() {
        super( Config.C2DM_SENDER);
    }

    @Override
    public void onError(Context context, String errorId) {
        Toast.makeText(context, "Messaging registration error: " + errorId,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        String message = intent.getExtras().getString( "message" );
        Log.d( LOG_TAG, "onMessage: "+message );
        Push p = Push.getRef();
        p.displayMessage( message );
    }

    public void onRegistered(Context context, String registrationId) throws IOException {
        Log.d( LOG_TAG, "onRegistered()" );
        Push p = Push.getRef();
        Account acc = p.getSelectedAccount();
        Log.d( LOG_TAG, "onRegistered() sendRegistrationId" );
        NetworkCommunication.sendRegistrationId( acc, context, registrationId );
        Log.d( LOG_TAG, "onRegistered() p.onRegistered()" );
        p.onRegistered();
        Log.d( LOG_TAG, "onRegistered() done" );
    }

    public void onUnregistered(Context context) {
        Push p = Push.getRef();
        Account acc = p.getSelectedAccount();
        NetworkCommunication.sendRegistrationId( acc, context, "" );
        p.onUnregistered();
    }

}
