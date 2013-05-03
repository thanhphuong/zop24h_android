package vn.fiosoft.zop;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import vn.fiosoft.zop.data.Friend;
import vn.fiosoft.zop.data.FriendFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FriendFragment extends ListFragment {
	
	String[] values;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_friend, container, false);
		
		FriendFactory friendFactory = new FriendFactory();
		List<Friend> objects = friendFactory.list();
		
		FriendAdapter adapter = new FriendAdapter(this.getActivity(), R.layout.fragment_friend_item, objects);
		setListAdapter(adapter);
		return view;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {		
		super.onListItemClick(l, v, position, id);
		
		startActivity(new Intent(getActivity(), MapActivity.class));
	}
}
