package syway.txxs.com.amaplearn;

import android.graphics.Color;
import android.location.Location;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;

/**
 * Created by jianghuimin on 2017/9/13.
 */

public class LineActivity {

    private AMap aMap;
    private Polyline polyline;

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

}
