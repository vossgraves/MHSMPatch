-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
 public static void check*(...);
 public static void throw*(...);
}
-assumenosideeffects class java.util.Objects {
    public static ** requireNonNull(...);
}
-assumenosideeffects public class kotlin.coroutines.jvm.internal.DebugMetadataKt {
   private static ** getDebugMetadataAnnotation(...) return null;
}
-keep class com.beust.jcommander.** { *; }
-keep interface com.beust.jcommander.** { *; }
-keepclassmembers class org.lsposed.patch.MHSMPatch {
    @com.beust.jcommander.Parameter *;
}

-keep class com.beust.jcommander.** { *; }
-keep class org.lsposed.mhsmpatch.database.** { *; }
-keep class org.lsposed.mhsmpatch.manager.ConfigProvider { *; }
-keep class org.lsposed.mhsmpatch.Patcher$Options { *; }
-keep class org.lsposed.mhsmpatch.share.LSPConfig { *; }
-keep class org.lsposed.mhsmpatch.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class org.lsposed.mhsmpatch.loader.SigBypass { *; }
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
-dontwarn com.squareup.moshi.**
-dontwarn retrofit2.**
-dontwarn okio.**