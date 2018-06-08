# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# --------------------
# Retrofit2.0
# --------------------

# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# --------------------
# Retrofit2.0
# --------------------


# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**


# ==================================================================================================
# otto
# ==================================================================================================
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# okio

-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**

# ==================================================================================================
# okhttp3
# ==================================================================================================


-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# retrolambda
-dontwarn java.lang.invoke.*

# ==================================================================================================
# Reflect
# ==================================================================================================

-keepclassmembernames class com.facebook.imagepipeline.core.ImagePipeline {
	com.facebook.imagepipeline.cache.BufferedDiskCache mMainBufferedDiskCache;
}
-keepclassmembernames class com.facebook.imagepipeline.cache.BufferedDiskCache {
	com.facebook.imagepipeline.cache.StagingArea mStagingArea;
}
-keep class android.support.v7.widget.** {*;}
-keep class android.support.v7.content.res.** {*;}
-keepclassmembernames class android.support.v4.widget.SwipeRefreshLayout {
    android.support.v4.widget.CircleImageView mCircleView;
    android.support.v4.view.NestedScrollingParentHelper mNestedScrollingParentHelper;
    boolean mNestedScrollInProgress;
    float mTotalUnconsumed;
    void finishSpinner(float);
}

# ==================================================================================================
# RxJava
# ==================================================================================================

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keep class rx.internal.util.** { *;}