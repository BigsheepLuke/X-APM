package github.tornaco.xposedmoduletest.xposed.submodules;

import android.util.Log;
import android.view.KeyEvent;

import java.util.Set;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import github.tornaco.xposedmoduletest.xposed.XAppBuildVar;

/**
 * Created by guohao4 on 2017/10/31.
 * Email: Tornaco@163.com
 */
public class InputManagerSubModule extends AndroidSubModuleModule {

    public static final String EVENT_SOURCE = "InputManagerSubModule";

    @Override
    public String needBuildVar() {
        return XAppBuildVar.APP_RFK;
    }

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) {
        super.initZygote(startupParam);
        hookInjectInputEvent();
    }

    private void hookInjectInputEvent() {
        logOnBootStage("hookInjectInputEvent...");
        try {
            Class clz = XposedHelpers.findClass("android.hardware.input.InputManager", null);
            Set unHooks = XposedBridge.hookAllMethods(clz,
                    "injectInputEvent", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            super.afterHookedMethod(param);

                            Throwable e = param.getThrowable();
                            if (e != null) {
                                // This event will not be handle.
                                return;
                            }

                            KeyEvent keyEvent = (KeyEvent) param.args[0];
                            getBridge().onKeyEvent(keyEvent, EVENT_SOURCE);
                        }
                    });
            logOnBootStage("hookInjectInputEvent OK:" + unHooks);
            setStatus(unhooksToStatus(unHooks));
        } catch (Exception e) {
            logOnBootStage("Fail hookInjectInputEvent:" + e);
            setStatus(SubModuleStatus.ERROR);
            setErrorMessage(Log.getStackTraceString(e));
        }
    }
}
