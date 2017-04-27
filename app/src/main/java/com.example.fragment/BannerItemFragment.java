package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.example.hanlian.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import utils.TCHConstants;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BannerItemFragment extends Fragment {

	public BannerItemFragment() 
	{
	}
	private int[] mBannerImgRes = new int[]{
			        R.drawable.banner01,
					R.drawable.banner01,
					R.drawable.banner02,
					R.drawable.banner03,
					R.drawable.banner04
	};

	private int position;

	public BannerItemFragment(int position) {
		this.position = position;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 View layout = inflater.inflate(R.layout.fragment_banner_item, container, false);

		 Bundle bundle = getArguments();
		 String url = bundle.getString("imgurl");//图片路径
		 ImageView imageView = (ImageView) layout.findViewById(R.id.imageView1);
		 ImageLoader.getInstance().displayImage
		 (TCHConstants.url.imgurl+url ,imageView);	
		 return layout;
	}

}
