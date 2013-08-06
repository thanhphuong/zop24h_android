package vn.fiosoft.zop;

import java.util.ArrayList;
import java.util.List;

import vn.fiosoft.zop.data.ZOPLocation;
import vn.fiosoft.zop.data.ZOPLocationFactory;
import vn.fiosoft.zop.util.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements
		OnCameraChangeListener {

	public static final String KEY_LIST_LOCATION = "listlocation";
	private final float MIN_ZOOM = 5.0f;

	private GoogleMap mMap;
	private int mId;
	private LatLngBounds mBounds;
	private boolean mUpdateCamemra;
	private StatusCamera mStatusCamera;

	private enum StatusCamera {
		Start, End
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

//		mId = getIntent().getIntExtra(KEY_LIST_LOCATION, 0);
//		mUpdateCamemra = false;
		
		boolean isNetworkConnected = Utils.isNetworkConnected(this);
		if (isNetworkConnected == false){
			showDialog(1);
			return;
		}
		
		setUpMapIfNeeded(); 

		mMap.setOnCameraChangeListener(this);
		/* Use the LocationManager class to obtain GPS locations */
		LocationManager mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		LocationListener mLocationListener = new MyLocationListener();
		mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				0, 0, mLocationListener);
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

	}

	@Override
	public void onCameraChange(CameraPosition position) {
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
        	   .setMessage("This application requires a working data connection.")
               .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       finish();
                   }
               });

		return builder.create();
	}

	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			Log.e("test", "test");
			LatLng latLng = new LatLng(location.getLatitude(), location
					.getLongitude());
			mMap.clear();
			mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
			mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
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