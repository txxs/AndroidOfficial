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
import com.amap.api.maps.model.MyLocationStyle;

/**
 * Created by jianghuimin on 2017/10/19.
 */

public class AroundFragment extends Fragment implements LocationSource,AMapLocationListener {
    private MapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_around, container, false);
        mapView= (MapView) view.findViewById(R.id.main_map);
        mapView.onCreate(savedInstanceState);
        initview(savedInstanceState,view);
        return view;
    }

    private void initview( Bundle savedInstanceState,View view){
        if(aMap == null){
            aMap = mapView.getMap();
            basicUIOnMap();
            customLocationStyle();
        }
    }

    /**
     * 设置地图的基本图标的显示
     */
    public void basicUIOnMap(){
        mUiSettings = aMap.getUiSettings();
        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
        mUiSettings.setRotateGesturesEnabled(false);//不允许手势旋转地图
        mUiSettings.setZoomControlsEnabled(false);//隐藏下方的缩放按钮
    }

    /**
     * 设置自定义风格
     */
    public void customLocationStyle(){
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    /**
     * 以下方法需要重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}