package com.example.hanlian.MyApplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.activeandroid.util.Log;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xinbo.app.BaseApp;

import utils.CardGoodsInfo;

public class MyApplication extends Application{
	
//	public void finishAllActivity() {
//	}
//	public void saveActivity(Activity activity) {
//	}
//	public void removeActivity(Activity activity) {
//	}
	public static DisplayImageOptions options;

	@Override
	public void onCreate() {
		super.onCreate();
//		ActiveAndroid.initialize(this);
		initImageLoader(getApplicationContext());
		initializeDB();


//		PushAgent mPushAgent = PushAgent.getInstance(this);
//		//注册推送服务，每次调用register方法都会回调该接口
//		mPushAgent.register(new IUmengRegisterCallback() {
//
//		    @Override
//		    public void onSuccess(String deviceToken) {
//		        //注册成功会返回device token
//		    	Log.e("deviceToken" , deviceToken);
//
//		    }
//
//		    @Override
//		    public void onFailure(String s, String s1) {
//		    	Log.e("deviceToken" , s);
//		    }
//		});
		options = new DisplayImageOptions.Builder() 
				.showImageOnLoading(R.drawable.err)
				// 设置图片在下载期间显示的默认图片
				.showImageForEmptyUri(R.drawable.err)
				// 设置URI错误或者为空时显示的图片
				.showImageOnFail(R.drawable.err)
				// 设置图片加载或者解析出现错误时设置的图片
				.cacheInMemory(true)
				// 是否内存缓存
				.cacheOnDisk(true)
				// 是否外存缓存
				.considerExifParams(true)
				// .displayer(new RoundedBitmapDisplayer(100))// 是否设置图片圆角
				.displayer(new FadeInBitmapDisplayer(200))// 图片加载好之后渐入的时间
				.build();
	}

	protected void initializeDB() {
		Configuration.Builder configurationBuilder = new Configuration.Builder(this);
		configurationBuilder.addModelClasses(CardGoodsInfo.class);
		ActiveAndroid.initialize(configurationBuilder.create());
	}

//	@Override
//	public void onTerminate() {
//		super.onTerminate();
//		ActiveAndroid.dispose();
//	}
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom.You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024)
				// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs()// Remove for release app
//				.threadPoolSize(5)
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
