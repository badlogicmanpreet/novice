package aexp.push;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class AccountSelector extends ListActivity {
	
	private static final String LOG_TAG = "AccountManager";
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView( R.layout.accounts );
        Log.d( LOG_TAG, "In onCreate of AccountSelector" );
        AccountManager accountManager = AccountManager.get( this );
        Account[] googleAccounts = accountManager.getAccountsByType(null);
        ArrayList<String> items = new ArrayList<String>();
        for( int i = 0 ; i < googleAccounts.length ; ++i )
            items.add( googleAccounts[i].name );
        accountAdapter = new ArrayAdapter<String>(this, R.layout.account_row, items);
        setListAdapter( accountAdapter );
    }

    protected void onListItemClick(
            ListView l,
            View v,
            int position,
            long id ) {
        String accountName = accountAdapter.getItem( position );
        Intent extras = new Intent();
        extras.putExtra( "account",accountName );
        setResult( RESULT_OK, extras );
        finish();
    }

    private ArrayAdapter<String> accountAdapter;
}
