package syway.txxs.com.syway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;

/**
 * Created by jianghuimin on 2017/10/19.
 */

public class AroundFragment extends Fragment  implements LocationSource, AMapLocationListener,AMap.OnMapClickListener
        ,AMap.OnMarkerClickListener{

    private MapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_around, container, false);
        initview(savedInstanceState,view);
        return view;
    }

    private void initview( Bundle savedInstanceState,View view){
        mapView= (MapView) view.findViewById(R.id.main_map);
        mapView.onCreate(savedInstanceState);
        if (aMap==null){
            aMap=mapView.getMap();
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE );
        myLocationStyle.interval(2000);
        myLocationStyle.strokeColor(1000);
        myLocationStyle.radiusFillColor(1000);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.mylocation);
        myLocationStyle.myLocationIcon(bitmapDescriptor);
        myLocationStyle.anchor(0.5f, 1);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        uiBasic();
    }

    public void uiBasic(){
        mUiSettings = aMap.getUiSettings();
        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
        mUiSettings.setRotateGesturesEnabled(false);//不允许手势旋转地图
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        //点击地图随机的位置，会触发相关的动作
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
