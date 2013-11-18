package vn.fiosoft.zop.friends;


import vn.fiosoft.zop.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FriendAddActivity extends Activity implements OnClickListener{

	private EditText name;
	private EditText userName;
	private EditText email;
	private Button buttonAddToList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_add);
		
		name = (EditText) findViewById(R.id.name);
		userName = (EditText) findViewById(R.id.username);
		email = (EditText) findViewById(R.id.email);		
		buttonAddToList = (Button) findViewById(R.id.add_to_list);
		
		buttonAddToList.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == buttonAddToList){
			return;
		}
		
	}
}
