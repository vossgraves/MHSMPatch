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
-keepclassmembers class org.lsposed.patch.OQPatch {
    @com.beust.jcommander.Parameter *;
}

-keep class com.beust.jcommander.** { *; }
-keep class org.lsposed.oqpatch.database.** { *; }
-keep class org.lsposed.oqpatch.manager.ConfigProvider { *; }
-keep class org.lsposed.oqpatch.Patcher$Options { *; }
-keep class org.lsposed.oqpatch.share.LSPConfig { *; }
-keep class org.lsposed.oqpatch.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class org.lsposed.oqpatch.loader.SigBypass { *; }
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
-dontwarn com.squareup.moshi.**
-dontwarn retrofit2.**
-dontwarn okio.**