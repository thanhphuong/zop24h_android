package vn.fiosoft.zop;

import vn.fiosoft.http.HttpZOPConnection;
import vn.fiosoft.zop.data.Account;
import vn.fiosoft.zop.data.AccountXML;
import vn.fiosoft.zop.util.FileManager;
import vn.fiosoft.zop.util.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity implements View.OnClickListener {	 
	
	private static final String FILE_ACCOUNT = "account.xml";
	
	private EditText mUserName;
	private EditText mPassword;
	private View mLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mUserName = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mLogin = findViewById(R.id.login);

		mLogin.setOnClickListener(this);

		loadForm();
		
		// check wifi or 3g
		boolean isNetworkConnected = Utils.isNetworkConnected(this);
		if (isNetworkConnected == false) {
			Utils.showDialogNetwork(this);
			return;
		}
	}
	
	protected void loadForm(){
		FileManager fileManager = new FileManager();
		String xml = fileManager.readFromFile(this, FILE_ACCOUNT);
		
		AccountXML accountXML = new AccountXML();
		Account account  = accountXML.parseXMLToAccount(xml);
		
		if(account != null && account.isExists() == true){
			mUserName.setText(account.getUserName());
			mPassword.setText(account.getPassword());
		}
	}
	
	@Override
	public void onClick(View v) {
		if (v == mLogin) {
			
			String username = mUserName.getText().toString();
			String password = mPassword.getText().toString();
			HttpZOPConnection httpConnection = new HttpZOPConnection();
			int id = httpConnection.login(username, password);
			
			if (id != 0){
				
				Account account = new Account(id, username, password);
				// save to database
				FileManager fileManager = new FileManager(); 
				AccountXML accountXML = new AccountXML();
				String xml = accountXML.parseAccountToXML(account);
				fileManager.writeToFile(this, FILE_ACCOUNT, xml);
				
				//start map activity
				Intent intent = new Intent(this, MapActivity.class);
				startActivity(intent);
			}
				
		}

	}
	
	

}
