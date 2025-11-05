# Cách sửa lỗi: Could not find com.github.hoangvannhatanh:CommonScreenSDK

## ❌ Lỗi bạn gặp phải:

```
Could not find com.github.hoangvannhatanh:CommonScreenSDK:v1.0.0
```

## ✅ Giải pháp nhanh:

### Bước 1: Thêm JitPack Repository

**Mở file `settings.gradle` của dự án bạn đang sử dụng SDK** (không phải SDK project này) và thêm:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // ← THÊM DÒNG NÀY
    }
}
```

**Hoặc nếu dự án bạn dùng `build.gradle` cũ (không có dependencyResolutionManagement):**

Mở `build.gradle` (project level) và thêm:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // ← THÊM DÒNG NÀY
    }
}
```

### Bước 2: Kiểm tra Dependency

Đảm bảo trong `build.gradle` (app module) của dự án bạn có:

```gradle
dependencies {
    implementation 'com.github.hoangvannhatanh:CommonScreenSDK:v1.0.0'
}
```

**Lưu ý:** 
- Nếu tag trên GitHub là `1.0.0` (không có `v`), dùng: `'com.github.hoangvannhatanh:CommonScreenSDK:1.0.0'`
- Nếu tag là `v1.0.0`, dùng: `'com.github.hoangvannhatanh:CommonScreenSDK:v1.0.0'`

### Bước 3: Sync Gradle

1. Click **File → Sync Project with Gradle Files** trong Android Studio
2. Hoặc click biểu tượng **Sync Now** khi có thông báo

### Bước 4: Kiểm tra JitPack Build Status

Truy cập: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK

- ✅ Nếu version `v1.0.0` hiển thị và có dấu tích xanh → Build thành công
- ❌ Nếu không có hoặc có dấu X đỏ → Cần đợi hoặc tạo tag mới

### Bước 5: Nếu vẫn lỗi - Clear Cache

Trong Android Studio:
1. **File → Invalidate Caches / Restart**
2. Chọn **"Invalidate and Restart"**

Hoặc từ terminal:
```bash
./gradlew clean build --refresh-dependencies
```

## 📋 Checklist nhanh:

- [ ] Đã thêm `maven { url 'https://jitpack.io' }` vào repositories?
- [ ] Đã sync Gradle sau khi thêm repository?
- [ ] Version trong dependency đúng với tag trên GitHub?
- [ ] JitPack đã build xong version đó? (kiểm tra tại jitpack.io)
- [ ] Đã clear cache và rebuild?

## 🔗 Liên kết hữu ích:

- JitPack Status: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK
- GitHub Repository: https://github.com/hoangvannhatanh/CommonScreenSDK
- Xem chi tiết: [TROUBLESHOOTING.md](TROUBLESHOOTING.md)

