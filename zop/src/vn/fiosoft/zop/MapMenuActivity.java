package vn.fiosoft.zop;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.accounts.LoginActivity;
import vn.fiosoft.zop.data.ZOPMenuItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MapMenuActivity extends Activity implements OnItemClickListener {
	
	private List<ZOPMenuItem> menuItems;
	private ListView listMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_map_menu);
		menuItems = new ArrayList<ZOPMenuItem>();
		menuItems.add(new ZOPMenuItem(ZOPMenuItem.MENU_LOGIN, R.drawable.collections_view_as_grid, getString(R.string.sign_in)));
		menuItems.add(new ZOPMenuItem(ZOPMenuItem.MENU_HELP, R.drawable.collections_view_as_grid, getString(R.string.help)));
		
		listMenu = (ListView) findViewById(R.id.list_menu);
		listMenu.setBackgroundColor(Color.WHITE);
		listMenu.setAdapter(new MapMenuAdapter(this, R.layout.dialog_menu_list_item, menuItems));
		listMenu.setOnItemClickListener(this);		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		if (menuItems == null)
			return;
		ZOPMenuItem menuItem = menuItems.get(position);
		
		//sign in menu
		if (menuItem.getId() == ZOPMenuItem.MENU_LOGIN){
			startActivity(new Intent(this, LoginActivity.class));
			this.finish();
			return;
		}
		
			
	}
}
