package com.baichang.library.test.common;

import android.content.Context;
import android.widget.ImageView;

import com.baichang.android.widget.banner.loader.ImageLoader;
import com.baichang.library.test.R;
import com.baichang.library.test.base.APIConstants;
import com.bumptech.glide.Glide;

/**
 * Created by iscod.
 * Time:2016/12/15-10:19.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(APIConstants.API_LOAD_IMAGE + path).error(R.mipmap.ic_launcher).into(imageView);
    }
}
