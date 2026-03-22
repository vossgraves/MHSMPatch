-dontobfuscate
-keep class com.beust.jcommander.** { *; }
-keep class org.lsposed.mhsmpatch.Patcher$Options { *; }
-keep class org.lsposed.mhsmpatch.share.LSPConfig { *; }
-keep class org.lsposed.mhsmpatch.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class org.lsposed.mhsmpatch.loader.SigBypass { *; }
-keepclassmembers class org.lsposed.patch.MHSMPatch {
    private <fields>;
}
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
