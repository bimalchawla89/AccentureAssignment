# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes *Annotation*, InnerClasses, LineNumberTable, Signature, Exceptions, SourceFile

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class com.example.weather.**$$serializer { *; }
-keepclasseswithmembers class **.*$Companion {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep class com.example.weather.** { *; }
-keep class com.google.android.gms.internal.** { *; }
-keep class com.google.android.gms.internal.firebase-auth-api.** { *; }

# kotlinx-coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.conscrypt.ConscryptHostnameVerifier

# Rendescript
-keepclasseswithmembernames class * {
   native <methods>;
}

# Keep class names of Hilt injected ViewModels since their name are used as a multibinding map key.
-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel

-keep class * extends androidx.lifecycle.ViewModel {
    <init>();
}
-keep class * extends androidx.lifecycle.AndroidViewModel {
    <init>(android.app.Application);
}

# Kotlin reflection
-keep interface kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader
-keep class kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInsLoaderImpl

-keep class kotlin.** { *; }
-keepclassmembers class com.example.weather.** {
  <init>(...);
  <fields>;
}