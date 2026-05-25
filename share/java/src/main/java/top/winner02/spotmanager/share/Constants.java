package top.winner02.spotmanager.share;

public class Constants {

    final static public String CONFIG_ASSET_PATH = "assets/spotmanager/config.json";
    final static public String LOADER_DEX_ASSET_PATH = "assets/spotmanager/loader.bin";
    final static public String META_LOADER_DEX_ASSET_PATH = "assets/spotmanager/metaloader.dex";
    final static public String PROVIDER_DEX_ASSET_PATH = "assets/spotmanager/mtprovider.dex";
    final static public String ORIGINAL_APK_ASSET_PATH = "assets/spotmanager/origin.apk";
    final static public String EMBEDDED_MODULES_ASSET_PATH = "assets/spotmanager/modules/";

    final static public String PATCH_FILE_SUFFIX = "-spotmanagered.apk";
    final static public String PROXY_APP_COMPONENT_FACTORY = "top.winner02.spotmanager.metaloader.LSPAppComponentFactoryStub";
    final static public String MANAGER_PACKAGE_NAME = "top.winner02.spotmanager";
    final static public String REAL_GMS_PACKAGE_NAME = "com.google.android.gms";
    final static public int MIN_ROLLING_VERSION_CODE = 633;

    public static final int SIGBYPASS_NONE = 0;
    public static final int SIGBYPASS_BASIC = 1;
    public static final int SIGBYPASS_HIGH = 2;
    public static final int SIGBYPASS_EXTREME = 3;
    public static final int SIGBYPASS_SECCOMP = 4;
}
