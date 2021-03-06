package github.tornaco.xposedmoduletest.xposed.service;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

/**
 * Created by guohao4 on 2017/11/1.
 * Email: Tornaco@163.com
 */

public interface IAppGuardBridge extends IModuleBridge {

    // API for AppGuard.
    boolean interruptPackageRemoval(String pkg);

    boolean onEarlyVerifyConfirm(String pkg, String reason);

    void verify(Bundle options, String pkg, int uid, int pid, VerifyListener listener);

    Intent checkIntent(Intent from);

    long wrapCallingUidForIntent(long from, Intent intent);

    boolean isBlurForPkg(String pkg);

    int getBlurRadius() throws RemoteException;

    float getBlurScale();

    boolean interruptFPSuccessVibrate();

    boolean interruptFPErrorVibrate();

    boolean isActivityStartShouldBeInterrupted(ComponentName componentName);
}
