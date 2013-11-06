package vn.fiosoft.zop;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.data.Account;
import vn.fiosoft.zop.data.Friend;
import vn.fiosoft.zop.data.Group;
import vn.fiosoft.zop.util.Utils;
import vn.fiosoft.zop.xml.AccountStorage;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnCameraChangeListener,
		OnClickListener {

	private static final int	CODE_ADD_FRIEND		= 1000;
	private static final int	CODE_CHANGE_GROUP	= 1001;
	private static final int	DIALOG_ERROR_WIFI	= 4000;
	private static final int	DIALOG_RADAR		= 4001;

	private static final int	DIALOG_SETTINGS		= 5000;

	private final float			MIN_ZOOM			= 18;

	private GoogleMap			mMap;
	private Marker				mMyMarker;

	private Account				mAccount;

	private List<Friend>		friends;

	private boolean				isNeedUpdateMap;
	private boolean				isShowMyLocation;

	private ImageButton			buttonMenu;
	private ImageButton			buttonFullScreen;
	private ImageButton			buttonMyLocation;
	private ImageButton			buttonListFriend;
	private ImageButton			buttonListGroup;
	private View				mapMenu;
	private boolean				isFullScreen;
	private LatLng				myLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		boolean isNetworkConnected = Utils.isNetworkConnected(this);
		if (isNetworkConnected == false) {
			showDialog(DIALOG_ERROR_WIFI);
			return;
		}

		isNeedUpdateMap = false;
		isShowMyLocation = false;

		myLocation = null;
		friends = new ArrayList<Friend>();

		setUpMapIfNeeded();

		/* Use the LocationManager class to obtain GPS locations */
		LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		LocationListener mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
				mLocationListener);

		AccountStorage accountStorage = new AccountStorage(this);
		mAccount = accountStorage.getAccount();

		mapMenu = findViewById(R.id.map_menu);
		buttonMenu = (ImageButton) findViewById(R.id.menu);
		buttonFullScreen = (ImageButton) findViewById(R.id.full_screen);
		buttonMyLocation = (ImageButton) findViewById(R.id.my_location);
		buttonListFriend = (ImageButton) findViewById(R.id.list_friend);
		buttonListGroup = (ImageButton) findViewById(R.id.list_group);

		buttonMenu.setOnClickListener(this);
		buttonFullScreen.setOnClickListener(this);
		buttonMyLocation.setOnClickListener(this);
		buttonListFriend.setOnClickListener(this);
		buttonListGroup.setOnClickListener(this);

		exitFullScreen();
	}

	private void exitFullScreen() {
		isFullScreen = false;
		buttonFullScreen.setImageResource(R.drawable.av_full_screen);
		mapMenu.setVisibility(View.VISIBLE);
	}

	private void fullScreen() {
		isFullScreen = true;
		buttonFullScreen.setImageResource(R.drawable.av_return_from_full_screen);
		mapMenu.setVisibility(View.GONE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	/**
	 * Sets up the map if it is possible to do so (i.e., the Google Play
	 * services APK is correctly installed) and the map has not already been
	 * instantiated.. This will ensure that we only ever call
	 * {@link #setUpMap()} once when {@link #mMap} is not null.
	 * <p>
	 * If it isn't installed {@link SupportMapFragment} (and
	 * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt
	 * for the user to install/update the Google Play services APK on their
	 * device.
	 * <p>
	 * A user can return to this FragmentActivity after following the prompt and
	 * correctly installing/updating/enabling the Google Play services. Since
	 * the FragmentActivity may not have been completely destroyed during this
	 * process (it is likely that it would only be stopped or paused),
	 * {@link #onCreate(Bundle)} may not be called again so we should call this
	 * method in {@link #onResume()} to guarantee that it will be called.
	 */
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
					.getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	/**
	 * This is where we can add markers or lines, add listeners or move the
	 * camera. In this case, we just add a marker near Africa.
	 * <p>
	 * This should only be called once and when we are sure that {@link #mMap}
	 * is not null.
	 */
	private void setUpMap() {
		isNeedUpdateMap = true;
	}

	private void updateMapView() {
		mMap.clear();
		LatLng latLng;
		LatLngBounds.Builder builder = new LatLngBounds.Builder();

		for (Friend friend : friends) {
			latLng = new LatLng(friend.getLatitude(), friend.getLongitude());
			mMap.addMarker(new MarkerOptions().position(latLng).title(friend.getName())
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.yourmarker)));
			builder.include(latLng);
		}

		if (myLocation != null) {
			mMyMarker = mMap.addMarker(new MarkerOptions().position(myLocation)
					.title(getString(R.string.my_location))
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.mymarker)));
			builder.include(myLocation);
		}

		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 20);
		mMap.moveCamera(cameraUpdate);
	}

	@Override
	public void onCameraChange(CameraPosition position) {
		// if (isNeedUpdateMap == true) {
		// updateMapView();
		// isNeedUpdateMap = false;
		// }
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch (id) {
			case DIALOG_ERROR_WIFI:

				builder.setTitle(getString(R.string.network_failure))
						.setMessage(getString(R.string.network_failure_message))
						.setPositiveButton(getString(R.string.exit),
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										finish();
									}
								});
				break;
			case DIALOG_RADAR:
				break;
			case DIALOG_SETTINGS:

				break;
		}

		return builder.create();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CODE_ADD_FRIEND) {

			if (resultCode == RESULT_OK) {
				Friend friend = (Friend) data.getExtras().get("result");
				if (friend != null) {
					friends.add(friend);
					// update location for friend
					// update map
					updateMapView();
				}
			}
		}
		
		if (requestCode == CODE_CHANGE_GROUP) {

			if (resultCode == RESULT_OK) {
				Group group = (Group) data.getExtras().get("result");
				if (group != null) {
					//clear friends
					friends.clear();
					//add friend
//					int n = group.getCount();
//					for (int i = 0; i < n; i++)
//						friends.add(group.getFriend(i));					
					//update map
					updateMapView();
					//start update from web
				}
			}
		}
	}

	public class MyLocationListener implements LocationListener {

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			if (location != null)
				myLocation = new LatLng(location.getLatitude(), location.getLongitude());
		}

	}

	@Override
	public void onClick(View v) {
		if (v == buttonMenu) {
			startActivity(new Intent(this, MapMenuActivity.class));
			return;
		}

		if (v == buttonFullScreen) {
			if (isFullScreen)
				exitFullScreen();
			else
				fullScreen();
			return;
		}

		if (v == buttonMyLocation) {
			moveMyLocation();
			return;
		}

		if (v == buttonListFriend) {
			startActivityForResult(new Intent(this, FriendActivity.class), CODE_ADD_FRIEND);
			return;
		}

		if (v == buttonListGroup) {
			startActivityForResult(new Intent(this, GroupActivity.class), CODE_CHANGE_GROUP);
		}
	}

	private void moveMyLocation() {
		if (myLocation == null)
			return;

		if (mMyMarker != null)
			mMyMarker.remove();

		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(myLocation, MIN_ZOOM);
		mMyMarker = mMap.addMarker(new MarkerOptions().position(myLocation)
				.title(getString(R.string.my_location))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.mymarker)));
		mMyMarker.showInfoWindow();
		mMap.moveCamera(cameraUpdate);
	}

}