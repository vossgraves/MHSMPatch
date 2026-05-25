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
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod
-keep class com.beust.jcommander.** { *; }
-keep interface com.beust.jcommander.** { *; }
-keep class top.winner02.spotmanager.patch.SpotManager { *; }
-keepclassmembers class top.winner02.spotmanager.patch.SpotManager {
    @com.beust.jcommander.Parameter <fields>;
}

-keepclassmembers class top.winner02.spotmanager.database.dao.** { *; }
-keep class top.winner02.spotmanager.database.entity.** { *; }
-keep class top.winner02.spotmanager.manager.ConfigProvider { *; }
-keep class top.winner02.spotmanager.Patcher$Options { *; }
-keep class top.winner02.spotmanager.share.LSPConfig { *; }
-keep class top.winner02.spotmanager.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class top.winner02.spotmanager.loader.SigBypass { *; }
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
-dontwarn com.squareup.moshi.**
-dontwarn retrofit2.**
-dontwarn okio.**