# Hướng dẫn Publish CommonScreenSDK

## Publish lên Maven Local

Để sử dụng SDK trong các dự án khác trên cùng máy:

```bash
./gradlew :app:publishToMavenLocal
```

Sau đó trong dự án khác, thêm vào `build.gradle` (app module):

```gradle
repositories {
    mavenLocal()
    // ... các repositories khác
}

dependencies {
    implementation 'com.nhatanh.commonscreensdk:commonscreensdk:1.0.0'
}
```

## Publish lên Maven Repository riêng

Nếu bạn muốn publish lên một Maven repository riêng (ví dụ: GitHub Packages, JitPack, hoặc Maven Central), thêm cấu hình repository vào `app/build.gradle`:

```gradle
afterEvaluate {
    publishing {
        publications {
            release(MavenPublication) {
                // ... existing configuration ...
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/yourusername/CommonScreenSDK")
                credentials {
                    username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
                }
            }
        }
    }
}
```

## Sử dụng như một Module Local

1. Copy thư mục `app` vào dự án của bạn
2. Hoặc thêm như một Git submodule:
```bash
git submodule add <repository-url> <path-to-module>
```

3. Trong `settings.gradle`:
```gradle
include ':app', ':commonscreensdk'
project(':commonscreensdk').projectDir = new File('path/to/CommonScreenSDK/app')
```

4. Trong `build.gradle` (app module):
```gradle
dependencies {
    implementation project(':commonscreensdk')
}
```

## Cập nhật Version

Khi cập nhật version, sửa các chỗ sau:
1. `app/build.gradle`: `versionCode` và `versionName` trong `defaultConfig`
2. `app/build.gradle`: `version` trong `publishing` block
3. `README.md`: Cập nhật version trong ví dụ sử dụng

