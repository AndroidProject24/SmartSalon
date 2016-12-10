package com.techart.smartsalon;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.techart.smartsalon.injector.component.ApplicationComponent;
import com.techart.smartsalon.injector.component.DaggerApplicationComponent;
import com.techart.smartsalon.injector.module.ApplicationModule;
import com.techart.smartsalon.utils.Utils;

/**
 * Created by Toan.IT
 * Date:2016/6/6
 * Time:20:59
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private ApplicationComponent applicationComponent;
    private static String cacheDir = "";
    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        initData();
        mInstance = this;
    }
    private void initInjector(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
    private void initData(){
        try {
            if (getApplicationContext().getExternalCacheDir() != null && Utils.ExistSDCard()) {
                cacheDir = getApplicationContext().getExternalCacheDir().toString();
            } else {
                cacheDir = getApplicationContext().getCacheDir().toString();
            }
            DrawerImageLoader.init(new AbstractDrawerImageLoader() {
                @Override
                public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                    Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
                }

                @Override
                public void cancel(ImageView imageView) {
                    Glide.clear(imageView);
                }

                @Override
                public Drawable placeholder(Context ctx, String tag) {
                    if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                        return DrawerUIUtils.getPlaceHolder(ctx);
                    } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                        return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
                    } else if ("customUrlItem".equals(tag)) {
                        return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
                    }
                    return super.placeholder(ctx, tag);
                }
            });
        } catch (Exception e) {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }
    public static BaseApplication getInstance() {
        return mInstance;
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
