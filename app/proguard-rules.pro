# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


## Retrofit
-keep class com.servpal.android.model.** { *; }
# ^ ProGuard likes to strip out models that are used by Retrofit because they aren't explicitly instantiated.
# Make sure they're kept in the apk if minify is enabled

-keep class com.servpal.android.api.** { *; }
# ^ ProGuard also likes removing annonymous inner classes like callbacks since they're not explicity instantiated.