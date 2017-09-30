package syway.txxs.com.syway;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;

public class MainActivity extends Activity {

    private UiSettings mUiSettings;
    private MapView mapView;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏Activity才可行
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
    }
    /**
     * 初始化AMap对象
     */
    private void init() {
        //显示地图
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        //显示定位的一些信息
        location();
        //界面显示的基本内容
        uiBasic();

    }

    public void  location(){
        //定位的一些显示
        MyLocationStyle myLocationStyle;
        //初始化定位蓝点样式类
        myLocationStyle = new MyLocationStyle();
        //不随着地图的移动而移动
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE );
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //将蓝圈设置为不可见
        myLocationStyle.strokeColor(1000);
        myLocationStyle.radiusFillColor(1000);
        //添加自定义的图标
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.share);
        myLocationStyle.myLocationIcon(bitmapDescriptor);
        //添加锚点，解释http://www.jianshu.com/p/94ba4de209ed
        myLocationStyle.anchor(0.5f, 1);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
        //设置缩放级别，数字越大显示的越小3-19
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    public void uiBasic(){
        //实例化UiSettings类对象
        mUiSettings = aMap.getUiSettings();
        //显示指南针
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

}
