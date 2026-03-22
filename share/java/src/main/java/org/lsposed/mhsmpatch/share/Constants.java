package org.lsposed.mhsmpatch.share;

public class Constants {

    final static public String CONFIG_ASSET_PATH = "assets/mhsmpatch/config.json";
    final static public String LOADER_DEX_ASSET_PATH = "assets/mhsmpatch/loader.dex";
    final static public String META_LOADER_DEX_ASSET_PATH = "assets/mhsmpatch/metaloader.dex";
    final static public String PROVIDER_DEX_ASSET_PATH = "assets/mhsmpatch/mtprovider.dex";
    final static public String ORIGINAL_APK_ASSET_PATH = "assets/mhsmpatch/origin.apk";
    final static public String EMBEDDED_MODULES_ASSET_PATH = "assets/mhsmpatch/modules/";

    final static public String PATCH_FILE_SUFFIX = "-mhsmpatched.apk";
    final static public String PROXY_APP_COMPONENT_FACTORY = "org.lsposed.mhsmpatch.metaloader.LSPAppComponentFactoryStub";
    final static public String MANAGER_PACKAGE_NAME = "org.lsposed.mhsmpatch";
    final static public String REAL_GMS_PACKAGE_NAME = "com.google.android.gms";
    final static public int MIN_ROLLING_VERSION_CODE = 400;

    final static public int SIGBYPASS_LV_DISABLE = 0;
    final static public int SIGBYPASS_LV_PM = 1;
    final static public int SIGBYPASS_LV_PM_OPENAT = 2;
    final static public int SIGBYPASS_LV_SVC = 3;
}
