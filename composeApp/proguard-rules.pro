# Keep Kotlin Serialization metadata and serializers
-keepattributes *Annotation*,InnerClasses,EnclosingMethod
-keepclassmembers class **$Companion {
    ** serializer(...);
}
-keepclassmembers class ** {
    **$serializer(...);
}
-keepclassmembers class kotlinx.serialization.** {
    *;
}
-keepclassmembers @kotlinx.serialization.Serializable class * {
    <fields>;
}
-keep class kotlinx.serialization.** { *; }
-keep class kotlinx.serialization.internal.** { *; }

# Keep application routes referenced by name/reflection
-keep class com.yoesuv.kmplanguage.core.route.AppRoute { *; }
