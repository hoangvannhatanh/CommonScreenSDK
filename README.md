# CommonScreenSDK

SDK cho màn hình Splash Screen với animation và callback hỗ trợ.

## Cài đặt

### Cách 1: Sử dụng như một module local

1. Copy thư mục `app` vào dự án của bạn hoặc thêm như một submodule
2. Trong `settings.gradle` của dự án:

```gradle
include ':app', ':commonscreensdk'
project(':commonscreensdk').projectDir = new File('../CommonScreenSDK/app')
```

3. Trong `build.gradle` (app module) của dự án:

```gradle
dependencies {
    implementation project(':commonscreensdk')
}
```

**Lưu ý:** Nếu dự án của bạn đã có module tên `:app`, bạn có thể đổi tên module SDK trong `settings.gradle` của SDK:
```gradle
rootProject.name = "CommonScreenSDK"
include ':commonscreensdk'
project(':commonscreensdk').projectDir = new File('app')
```

Sau đó trong dự án khác, sử dụng:
```gradle
include ':app', ':commonscreensdk'
project(':commonscreensdk').projectDir = new File('../CommonScreenSDK/commonscreensdk')
```

### Cách 2: Publish lên Maven Local (để sử dụng trong các dự án khác)

1. Publish SDK:
```bash
./gradlew :app:publishToMavenLocal
```

2. Thêm vào `build.gradle` (app module) của dự án khác:

```gradle
repositories {
    mavenLocal()
}

dependencies {
    implementation 'com.nhatanh.commonscreensdk:app:1.0.0'
}
```

## Sử dụng

### Khởi tạo và gọi Splash Screen

```kotlin
import com.nhatanh.commonscreensdk.splash.SplashSDK
import com.nhatanh.commonscreensdk.interface.ISplashCallback

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Gọi splash screen với callback đơn giản
        SplashSDK.launch(this, object : ISplashCallback {
            override fun onNextAction() {
                // Chuyển sang màn hình chính sau khi splash kết thúc
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                finish()
            }
        })
    }
}
```

### Tùy chỉnh Splash Screen

```kotlin
// Gọi với các tùy chọn tùy chỉnh
SplashSDK.launch(
    context = this,
    callback = object : ISplashCallback {
        override fun onNextAction() {
            // Xử lý sau khi splash kết thúc
        }
    },
    logoId = R.id.custom_logo,           // ID của logo view (optional)
    nameAppId = R.id.custom_app_name,    // ID của app name view (optional)
    loadingId = R.id.custom_loading,      // ID của loading view (optional)
    splashDuration = 2000L               // Thời gian hiển thị (milliseconds, default: 1500ms)
)
```

## Yêu cầu

- Min SDK: 24
- Target SDK: 36
- Kotlin

## Dependencies

SDK này sử dụng các thư viện sau:
- AndroidX Core KTX
- AndroidX AppCompat
- Material Components
- Lottie (cho animation)
- SDP/SSP (cho responsive sizing)

## Cấu trúc

- `SplashSDK`: Class chính để khởi động splash screen
- `SplashActivity`: Activity hiển thị splash screen
- `ISplashCallback`: Interface callback khi splash kết thúc
- `SplashSDKConfig`: Class cấu hình cho SDK

## License

[Thêm license của bạn ở đây]

