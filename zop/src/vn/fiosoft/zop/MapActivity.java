package vn.fiosoft.zop;

import vn.fiosoft.zop.util.Utils;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements
		OnCameraChangeListener {

	public static final int CODE_FRIEND = 1000;
	public static final int CODE_GROUP = 2000;

	private final float MIN_ZOOM = 18;
	private final int TIME_WAIT = 16500; // in miliseconds
	private final int TIME_DOWN = 4000; // in miliseconds
	private LatLng latLng;

	private GoogleMap mMap;

	private boolean mAnimateMyLocation;
	private Marker mMyMarker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		boolean isNetworkConnected = Utils.isNetworkConnected(this);
		if (isNetworkConnected == false) {
			showDialog(1);
			return;
		}

		mAnimateMyLocation = true;

		setUpMapIfNeeded();

		mMap.setOnCameraChangeListener(this);
		/* Use the LocationManager class to obtain GPS locations */
		LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		LocationListener mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
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
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
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

		// List<ZOPLocation> locations = new ArrayList<ZOPLocation>();
		//
		// if (mId != 0) {
		// ZOPLocationFactory locationFactory = new ZOPLocationFactory();
		// locations = locationFactory.list(mId);
		//
		// } else {
		// finish();
		// }
		//
		// LatLngBounds.Builder builder = new LatLngBounds.Builder();
		// for (ZOPLocation location : locations) {
		// mMap.addMarker(new MarkerOptions()
		// .position(
		// new LatLng(location.getLatitude(), location
		// .getLongitude())).title(location.getName()));
		// builder.include(new LatLng(location.getLatitude(), location
		// .getLongitude()));
		// }
		//
		// mBounds = builder.build();
		// mUpdateCamemra = true;
		// mStatusCamera = StatusCamera.Start;
		// mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(10,
		// 10)));

		// startTimer = true;
	}

	int count = 0;
	private int mCurrentZoom;
	boolean startTimer = false;

	@Override
	public void onCameraChange(CameraPosition position) {
		// if (startTimer){
		// mCurrentZoom += 5;
		// new CountDownTimer(3000, 3000) {
		//
		// public void onTick(long millisUntilFinished) {
		//
		// }
		//
		// public void onFinish() {
		//
		//
		// CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new
		// LatLng(10, 10), mCurrentZoom);
		// mMap.animateCamera(cameraUpdate);
		//
		// count+=1;
		// Log.e("test", count + "");
		// }
		// }.start();
		// if (mCurrentZoom >= MIN_ZOOM)
		// startTimer = false;
		// }
		// TODO Auto-generated method stub

		// switch (mStatusCamera) {
		// case Start:
		// mMap.animateCamera(CameraUpdateFactory.newLatLng(mBounds.southwest));
		// mStatusCamera = StatusCamera.End;
		// break;
		//
		// default:
		// int padding = 150; // offset from edges of the map in pixels
		// CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(
		// mBounds, padding);
		// mMap.animateCamera(cameraUpdate);
		// break;
		// }

		// if (mUpdateCamemra == true){
		// int padding = 150; // offset from edges of the map in pixels
		// CameraUpdate cameraUpdate =
		// CameraUpdateFactory.newLatLngBounds(mBounds, padding);
		// mMap.animateCamera(cameraUpdate);
		//
		// mUpdateCamemra = false;
		// }else{
		// if (position.zoom > MIN_ZOOM){
		// mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
		// }
		// }
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Network failure")
				.setMessage(
						"This application requires a working data connection.")
				.setPositiveButton("Exit",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								finish();
							}
						});

		return builder.create();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.map_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_add_friend:
			Intent intentFriend = new Intent(MapActivity.this,
					FriendActivity.class);
			startActivityForResult(intentFriend, CODE_FRIEND);
			return true;
		case R.id.menu_add_group:
			Intent intentGroup = new Intent(MapActivity.this,
					GroupActivity.class);
			startActivityForResult(intentGroup, CODE_GROUP);
			return true;
		case R.id.menu_help:

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {

			if (resultCode == RESULT_OK) {
				String result = data.getStringExtra("result");
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}
	}

	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {

			latLng = new LatLng(location.getLatitude(), location.getLongitude());

			if (mAnimateMyLocation == true && latLng != null) {
				mAnimateMyLocation = false;
				mMyMarker = null;
				mCurrentZoom = 0;
				new CountDownTimer(TIME_WAIT, TIME_DOWN) {

					public void onTick(long millisUntilFinished) {
						CameraUpdate cameraUpdate = CameraUpdateFactory
								.newLatLngZoom(latLng, mCurrentZoom);
						mMap.animateCamera(cameraUpdate);
						mCurrentZoom += 6;
					}

					public void onFinish() {						
						mMyMarker = mMap.addMarker(new MarkerOptions()
								.position(latLng).title("My Location"));
					}
				}.start();
			} else {
				if (mMyMarker != null){
					mMyMarker.remove();
					mMyMarker = mMap.addMarker(new MarkerOptions().position(latLng)
						.title("My Location"));
				}
						

			}
		}

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

	}

}