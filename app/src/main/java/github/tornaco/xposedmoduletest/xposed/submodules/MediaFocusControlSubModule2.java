package github.tornaco.xposedmoduletest.xposed.submodules;

import android.os.Binder;
import android.util.Log;

import java.util.Set;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import github.tornaco.xposedmoduletest.util.OSUtil;
import github.tornaco.xposedmoduletest.xposed.util.XposedLog;

/**
 * Created by guohao4 on 2017/10/31.
 * Email: Tornaco@163.com
 */

class MediaFocusControlSubModule2 extends IntentFirewallAndroidSubModule {

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) {
        super.initZygote(startupParam);
        if (!OSUtil.isMOrAbove()) hookMediaFocusCtrlForL();
    }

    @Override
    public void handleLoadingPackage(String pkg, XC_LoadPackage.LoadPackageParam lpparam) {
        if (OSUtil.isMOrAbove()) hookMediaFocusCtrlForMOrAbove(lpparam);
    }

    private void hookMediaFocusCtrlForMOrAbove(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedLog.verbose("hookMediaFocusCtrlForMOrAbove @ABANDON...");
        try {
            Class ams = XposedHelpers.findClass("com.android.server.audio.MediaFocusControl",
                    lpparam.classLoader);
            Set unHooks = XposedBridge.hookAllMethods(ams, "abandonAudioFocus", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    int res = (int) param.getResult();
                    int callingUid = Binder.getCallingUid();
                    getIntentFirewallBridge().onAbandonAudioFocus(res, callingUid, null);
                }
            });
            XposedLog.verbose("hookMediaFocusCtrlForMOrAbove @ABANDON OK:" + unHooks);
            setStatus(unhooksToStatus(unHooks));
        } catch (Exception e) {
            XposedLog.verbose("Fail hook hookMediaFocusCtrlForMOrAbove @ABANDON");
            setStatus(SubModuleStatus.ERROR);
            setErrorMessage(Log.getStackTraceString(e));
        }
    }

    private void hookMediaFocusCtrlForL() {
        XposedLog.verbose("hookMediaFocusCtrlForL @ABANDON...");
        try {
            Class ams = XposedHelpers.findClass("android.media.MediaFocusControl", null);
            Set unHooks = XposedBridge.hookAllMethods(ams, "abandonAudioFocus", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    int res = (int) param.getResult();
                    int callingUid = Binder.getCallingUid();
                    getIntentFirewallBridge().onAbandonAudioFocus(res, callingUid, null);
                }
            });
            XposedLog.verbose("hookMediaFocusCtrlForL @ABANDON OK:" + unHooks);
            setStatus(unhooksToStatus(unHooks));
        } catch (Exception e) {
            XposedLog.verbose("Fail hook hookMediaFocusCtrlForL @ABANDON");
            setStatus(SubModuleStatus.ERROR);
            setErrorMessage(Log.getStackTraceString(e));
        }
    }
}
