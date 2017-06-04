# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Soft_Install\Java_Soft\studio\android-sdk/tools/proguard/proguard-android.txt
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

###-----------基本配置-不能被混淆的------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

#support.v4/v7包不混淆
-keep class android.support.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.**
-keep interface android.support.v7.app.** { *; }
-dontwarn android.support.**    # 忽略警告

#保持注解继承类不混淆
-keep class * extends java.lang.annotation.Annotation {*;}
#保持Serializable实现类不被混淆
-keepnames class * implements java.io.Serializable


#自定义组件不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

#ormlite混淆配置
#-libraryjars libs/ormlite-android-5.0.jar
#-libraryjars libs/ormlite-core-5.0.jar
-dontwarn com.j256.ormlite.**
-keep class com.j256.ormlite.** { *;}
-keep class com.envy15.cherry.base.orm.** { *;}

#universalimageloader图片加载框架不混淆
-keep class com.nostra13.universalimageloader.** { *; }
-dontwarn com.nostra13.universalimageloader.**
#Gson相关的不混淆配置
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keep class com.envy15.cherry.fragment.crossover.model.** { *; }
-dontwarn com.envy15.cherry.fragment.crossover.model.**
-keep class com.envy15.cherry.fragment.discover.model.** { *; }
-dontwarn com.envy15.cherry.fragment.discover.model.**
-keep class com.envy15.cherry.fragment.local.model.** { *; }
-dontwarn com.envy15.cherry.fragment.local.model.**
-keep class com.envy15.cherry.fragment.setting.model.** { *; }
-dontwarn com.envy15.cherry.fragment.setting.model.**
#prt-lib下拉刷新框架不混淆
-keep class in.srain.cube.views.ptr.** { *; }
-dontwarn in.srain.cube.views.ptr.**
#PullToRefreshLibrary下拉刷新框架不混淆
-keep class com.handmark.pulltorefresh.library.** { *; }
-dontwarn com.handmark.pulltorefresh.library.**

-keep class cn.smssdk.**{*;}
-keep class com.mob.**{*;}

-dontwarn com.mob.**
-dontwarn cn.smssdk.**