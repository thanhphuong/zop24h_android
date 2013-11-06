package vn.fiosoft.zop.accounts;

import vn.fiosoft.http.HttpZOPConnection;
import vn.fiosoft.zop.MapActivity;
import vn.fiosoft.zop.R;
import vn.fiosoft.zop.data.Account;
import vn.fiosoft.zop.xml.AccountStorage;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity implements View.OnClickListener {

	private static final int DIALOG_LOGIN_FAIL = 4000;

	private EditText mUserName;
	private EditText mPassword;
	private View mLogin;
	private ProgressDialog progressDialog;
	private int mId; // get id from server

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mUserName = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mLogin = findViewById(R.id.login);

		mLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == mLogin) {

			progressDialog = ProgressDialog.show(LoginActivity.this, "",
					"Please wait...");

			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					if (mId <= 0)
						showDialog(DIALOG_LOGIN_FAIL);

				}
			});
			new Thread(new Runnable() {

				@Override
				public void run() {
					String username = mUserName.getText().toString();
					String password = mPassword.getText().toString();
					HttpZOPConnection httpConnection = new HttpZOPConnection();
					mId = httpConnection.login(username, password);

					if (mId != 0) {

						Account account = new Account(mId, username, password);
						// save to database
						AccountStorage accountStorage = new AccountStorage(
								LoginActivity.this);
						accountStorage.saveAccount(account);

						finish();

					}

					if (progressDialog != null)
						progressDialog.cancel();
				}
			}).start();

		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch (id) {
		case DIALOG_LOGIN_FAIL:
			builder.setTitle(getString(R.string.login))
					.setMessage(getString(R.string.the_email_and_password))
					.setPositiveButton(getString(R.string.ok),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

								}
							});
			break;
		}

		return builder.create();
	}

}
