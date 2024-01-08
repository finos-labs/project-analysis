#报错No Activity found to handle Intent

Owner: didi

Repo: DoKit

Labels: bug 

## chinachance (27 Dec 2018)

 手机：oppo r9m
系统：5.1
报错信息：
Caused by: android.content.ActivityNotFoundException: No Activity found to handle Intent { act=android.settings.action.MANAGE_OVERLAY_PERMISSION dat=package:com.xbkj.caruser }
        at android.app.Instrumentation.checkStartActivityResult(Instrumentation.java:1792)
        at android.app.Instrumentation.execStartActivity(Instrumentation.java:1512)
        at android.app.Activity.startActivityForResult(Activity.java:3896)
        at androidx.fragment.app.FragmentActivity.startActivityForResult(FragmentActivity.java:767)
        at android.app.Activity.startActivityForResult(Activity.java:3845)
        at androidx.fragment.app.FragmentActivity.startActivityForResult(FragmentActivity.java:754)
        at android.app.Activity.startActivity(Activity.java:4232)
        at android.app.Activity.startActivity(Activity.java:4147)
        at com.didichuxing.doraemonkit.util.PermissionUtil.requestDrawOverlays(PermissionUtil.java:89)
        at com.didichuxing.doraemonkit.DoraemonKit.requestPermission(DoraemonKit.java:175)
        at com.didichuxing.doraemonkit.DoraemonKit.access$100(DoraemonKit.java:46)
        at com.didichuxing.doraemonkit.DoraemonKit$1.onActivityResumed(DoraemonKit.java:85)
        at android.app.Application.dispatchActivityResumed(Application.java:216)
        at android.app.Activity.onResume(Activity.java:1248)
        at androidx.fragment.app.FragmentActivity.onResume(FragmentActivity.java:514)
        at com.trello.rxlifecycle2.components.support.RxAppCompatActivity.onResume(RxAppCompatActivity.java:73)
        at com.xbkj.caruser.framwork.BaseActivity.onResume(BaseActivity.java:250)
        at android.app.Instrumentation.callActivityOnResume(Instrumentation.java:1267)
        at android.app.Activity.performResume(Activity.java:6337)
        at android.app.ActivityThread.performResumeActivity(ActivityThread.java:3422)


## wanglikun7342 (29 Dec 2018)

1.0.5版本已修复，请更新

