package github.tornaco.xposedmoduletest.loader;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import github.tornaco.xposedmoduletest.model.CommonPackageInfo;
import github.tornaco.xposedmoduletest.xposed.app.XAppGuardManager;

/**
 * Created by guohao4 on 2017/10/18.
 * Email: Tornaco@163.com
 */

public interface BlurPackageLoader {

    @NonNull
    List<CommonPackageInfo> loadInstalled(boolean blur);

    class Impl implements BlurPackageLoader {

        public static BlurPackageLoader create(Context context) {
            return new Impl(context);
        }

        private Context context;

        private Impl(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public List<CommonPackageInfo> loadInstalled(boolean blur) {

            List<CommonPackageInfo> out = new ArrayList<>();

            XAppGuardManager appGuardManager = XAppGuardManager.get();
            if (!appGuardManager.isServiceAvailable()) return out;

            String[] packages = appGuardManager.getBlurApps(blur);

            for (String pkg : packages) {
                CommonPackageInfo p = LoaderUtil.constructCommonPackageInfo(context, pkg);
                if (p != null) out.add(p);
            }

            LoaderUtil.commonSort(out);

            return out;
        }
    }
}
