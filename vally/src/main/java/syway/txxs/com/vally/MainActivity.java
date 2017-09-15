package syway.txxs.com.vally;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mTextView = (TextView) findViewById(R.id.vally);
        final ImageButton imageButtonOne = (ImageButton)findViewById(R.id.imageButton);
        final ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.baidu.com";

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText(response.substring(0,500));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("出错了！");
                    }
        });

        String url1= "http://7xqsae.com1.z0.glb.clouddn.com/11.png";
        ImageRequest imageRequest = new ImageRequest(url1,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageButtonOne.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageButtonOne.setImageResource(R.mipmap.ic_launcher);
            }
        });

        String url2= "http://7xqsae.com1.z0.glb.clouddn.com/13.png";
        ImageRequest imageRequest1 = new ImageRequest(url2,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView1.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView1.setImageResource(R.mipmap.ic_launcher);
            }
        });

        String url4= "http://7xqsae.com1.z0.glb.clouddn.com/14.png";
        ImageRequest imageRequest2 = new ImageRequest(url4,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView2.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView2.setImageResource(R.mipmap.ic_launcher);
            }
        });

        queue.add(stringRequest);
        queue.add(imageRequest);
        queue.add(imageRequest1);
        queue.add(imageRequest2);

        //加载popup 对应的界面
        View root = getLayoutInflater().inflate(R.layout.pop, null);

        //创建 PopupWindow 对象
        final PopupWindow popupWindow = new PopupWindow(root, 280, 360);
        imageButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(v);  //方式1  显示在 popupwindow 的下方
            }
        });
    }
}
