-dontobfuscate
-keep class com.beust.jcommander.** { *; }
-keep class org.lsposed.oqpatch.Patcher$Options { *; }
-keep class org.lsposed.oqpatch.share.LSPConfig { *; }
-keep class org.lsposed.oqpatch.share.PatchConfig { *; }
-keep class org.lsposed.lspd.nativebridge.** { *; }
-keep class org.lsposed.oqpatch.loader.SigBypass { *; }
-keepclassmembers class org.lsposed.patch.OQPatch {
    private <fields>;
}
-dontwarn com.google.auto.value.AutoValue$Builder
-dontwarn com.google.auto.value.AutoValue
