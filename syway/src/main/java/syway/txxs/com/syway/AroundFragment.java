package syway.txxs.com.syway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;


/**
 * Created by jianghuimin on 2017/10/19.
 */

public class AroundFragment extends Fragment implements LocationSource,AMapLocationListener{
    private MapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;
    private View view;

    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_around, container, false);
        mapView= (MapView) view.findViewById(R.id.main_map);
        mapView.onCreate(savedInstanceState);
        initview();
        return view;
    }

    private void initview(){
        if(aMap==null){
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
        mUiSettings.setTiltGesturesEnabled(false);//禁止倾斜手势
        mUiSettings.setZoomControlsEnabled(false);//隐藏下方的缩放按钮
    }

    /**
     * 设置自定义风格
     */
    public void customLocationStyle(){
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_point));
        myLocationStyle.strokeColor(1000);
        myLocationStyle.radiusFillColor(1000);
        myLocationStyle.showMyLocation(true);
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);//设置地图类型
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
    }

    /**
     * 位置发生变化的时候
     * @param amapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            }else{
                Toast.makeText(getContext(),"Syway定位失败",Toast.LENGTH_LONG).show();
            }

        }
    }

    /**
     * 激活定位
     * @param onLocationChangedListener
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getContext());
            mLocationOption = new AMapLocationClientOption();
            mlocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setOnceLocation(false);
            //mLocationOption.setGpsFirst(true);
            mLocationOption.setInterval(1000);
            mLocationOption.setOnceLocationLatest(true);
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if(null != mlocationClient){
            mlocationClient.onDestroy();
        }
    }
}