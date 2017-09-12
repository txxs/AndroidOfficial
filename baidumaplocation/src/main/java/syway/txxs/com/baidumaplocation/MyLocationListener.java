package syway.txxs.com.baidumaplocation;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by jianghuimin on 2017/9/11.
 */

public class MyLocationListener implements BDLocationListener {

    private BaiduMap baiduMap;

    private MapView mapView;

    boolean isFirstLoc = true;// 是否首次定位

    public MyLocationListener(){

    }

    public MyLocationListener(BaiduMap mBaiduMap,MapView mapView){
        this.baiduMap=mBaiduMap;
        this.mapView=mapView;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {

        if (location == null || mapView == null)
            return;

        MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        baiduMap.setMyLocationData(locData);    //设置定位数据


        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng nowPoint = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(nowPoint, 16);   //设置地图中心点以及缩放级别

            //构建Marker图标
            BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.share);
            OverlayOptions markerOptions = new MarkerOptions().position(nowPoint).icon(mCurrentMarker);
            baiduMap.addOverlay(markerOptions);
            baiduMap.animateMapStatus(u);
        }
    }

}
