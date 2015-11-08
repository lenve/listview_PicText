package com.example.listview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	private List<News> list;
	private String HTTPURL = "http://litchiapi.jstv.com/api/GetFeeds?column=3&PageSize=20&pageIndex=1&val=100511D3BE5301280E0992C73A9DEC41";
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				MyAdaper adapter = new MyAdaper(list);
				lv.setAdapter(adapter);
				break;

			default:
				break;
			}
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) this.findViewById(R.id.lv);
		initData();
	}

	private void initData() {
		list = new ArrayList<News>();

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(HTTPURL).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {

			@Override
			public void onResponse(Response response) throws IOException {
				try {
					JSONObject jo1 = new JSONObject(response.body().string());
					JSONObject jo2 = jo1.getJSONObject("paramz");
					JSONArray ja = jo2.getJSONArray("feeds");
					News news = null;
					for (int i = 0; i < ja.length(); i++) {
						JSONObject data = ja.getJSONObject(i).getJSONObject(
								"data");
						String imageUrl = "http://litchiapi.jstv.com"
								+ data.getString("cover");
						String title = data.getString("subject");
						String summary = data.getString("summary");
						news = new News(imageUrl, title, summary);
						list.add(news);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				mHandler.obtainMessage(0).sendToTarget();
			}

			@Override
			public void onFailure(Request arg0, IOException arg1) {

			}
		});
	}
}
