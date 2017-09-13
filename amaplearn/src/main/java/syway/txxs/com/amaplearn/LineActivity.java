package syway.txxs.com.amaplearn;

import android.graphics.Color;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

/**
 * Created by jianghuimin on 2017/9/13.
 */

public class LineActivity implements LocationSource,AMapLocationListener {

    private AMap aMap;
    private Polyline polyline;

    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    //以前的定位点
    private LatLng oldLatLng;
    //是否是第一次定位
    private boolean isFirstLatLng;

    public LineActivity(){}

    public LineActivity(AMap aMap){
        this.aMap=aMap;
        makeFixedLine();
    }

    /**
     * 在地图上的固定的几个点划线
     */
    public void makeFixedLine(){
        // 绘制一个三角形
        polyline = aMap.addPolyline((new PolylineOptions())
                .add(Constants.SHANGHAI, Constants.BEIJING, Constants.CHENGDU)
                .width(10).color(Color.argb(255, 1, 1, 1)));

        // 绘制一个乌鲁木齐到哈尔滨的线
        aMap.addPolyline((new PolylineOptions()).add(
                new LatLng(43.828, 87.621), new LatLng(45.808, 126.55)).color(
                Color.RED));
        //绘制一条成都到郑州的虚线
        aMap.addPolyline((new PolylineOptions()).add(
                Constants.CHENGDU, Constants.ZHENGZHOU).color(
                Color.RED)).setDottedLine(true);
        //绘制一条广州到乌鲁木齐的大地曲线
        aMap.addPolyline((new PolylineOptions()).add(
                new LatLng(23.15,113.26), new LatLng(43.828, 87.621)).color(
                Color.RED)).setGeodesic(true);
    }

    /**绘制两个坐标点之间的线段,从以前位置到现在位置*/
    private void setUpMap(LatLng oldData,LatLng newData ) {

        // 绘制一个大地曲线
        aMap.addPolyline((new PolylineOptions())
                .add(oldData, newData)
                .geodesic(true).color(Color.GREEN));

    }

    /**
     * 定位成功后回调函数
     * http://blog.csdn.net/i_do_can/article/details/50571657
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
//                //定位成功
                LatLng newLatLng = new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
//                Log.e("Amap", amapLocation.getLatitude() + "," + amapLocation.getLongitude());
//                Toast.makeText(this, amapLocation.getLatitude() + "," + amapLocation.getLongitude() , Toast.LENGTH_SHORT).show();

                if(isFirstLatLng){
                    //记录第一次的定位信息
                    oldLatLng = newLatLng;
                    isFirstLatLng = false;
                }
                //位置有变化
                if(oldLatLng != newLatLng){
                    Log.e("Amap", amapLocation.getLatitude() + "," + amapLocation.getLongitude());
                    setUpMap( oldLatLng , newLatLng );
                    oldLatLng = newLatLng;
                }

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
//                Toast.makeText(this, errText, Toast.LENGTH_SHORT).show();
                if(isFirstLatLng){
                    //Toast.makeText(this, errText, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
