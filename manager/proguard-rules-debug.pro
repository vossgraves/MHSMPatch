-dontobfuscate
-keep class com.beust.jcommander.** { *; }
-keep class top.winner02.spotmanager.Patcher$Options { *; }
-keep class top.winner02.spotmanager.share.LSPConfig { *; }
-keep class top.winner02.spotmanager.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class top.winner02.spotmanager.loader.SigBypass { *; }
-keepclassmembers class org.lsposed.patch.SpotManager {
    private <fields>;
}
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
