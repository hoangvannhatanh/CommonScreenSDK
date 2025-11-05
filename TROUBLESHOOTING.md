# Troubleshooting - Xử lý lỗi JitPack

## Lỗi: Could not find com.github.hoangvannhatanh:CommonScreenSDK:v1.0.0

### Nguyên nhân 1: Chưa thêm JitPack repository

**Giải pháp:** Thêm JitPack repository vào `settings.gradle` hoặc `build.gradle` của dự án bạn đang sử dụng SDK.

#### Cách 1: Thêm vào `settings.gradle` (khuyến nghị cho Gradle mới)

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // ← Thêm dòng này
    }
}
```

#### Cách 2: Thêm vào `build.gradle` (project level - nếu dùng Gradle cũ)

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // ← Thêm dòng này
    }
}
```

#### Cách 3: Thêm vào `build.gradle` (app module - nếu không dùng dependencyResolutionManagement)

```gradle
repositories {
    google()
    mavenCentral()
    maven { url 'https://jitpack.io' } // ← Thêm dòng này
}

dependencies {
    implementation 'com.github.hoangvannhatanh:CommonScreenSDK:v1.0.0'
}
```

### Nguyên nhân 2: JitPack chưa build xong hoặc tag chưa tồn tại

**Kiểm tra:**
1. Truy cập: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK
2. Kiểm tra xem version `v1.0.0` có hiển thị không
3. Nếu chưa có, cần tạo tag trên GitHub trước

**Giải pháp:**

#### Tạo tag trên GitHub:

```bash
# Tạo tag
git tag -a v1.0.0 -m "Release version 1.0.0"

# Push tag lên GitHub
git push origin v1.0.0
```

#### Hoặc tạo Release trên GitHub:
1. Vào https://github.com/hoangvannhatanh/CommonScreenSDK
2. Click "Releases" → "Create a new release"
3. Tạo tag `v1.0.0`
4. Click "Publish release"

#### Đợi JitPack build:
- Sau khi push tag, đợi 2-5 phút
- JitPack sẽ tự động build
- Kiểm tra lại tại: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK

### Nguyên nhân 3: Version không đúng format

**Lưu ý:** JitPack yêu cầu tag bắt đầu bằng `v` (ví dụ: `v1.0.0`)

Nếu tag của bạn là `1.0.0` (không có `v`), sử dụng:
```gradle
implementation 'com.github.hoangvannhatanh:CommonScreenSDK:1.0.0'
```

### Nguyên nhân 4: Repository name không đúng

**Kiểm tra:** Đảm bảo repository name là `CommonScreenSDK` (chính xác, phân biệt hoa thường)

Nếu repository name khác, điều chỉnh `artifactId` trong dependency:
```gradle
implementation 'com.github.hoangvannhatanh:REPOSITORY_NAME:v1.0.0'
```

### Nguyên nhân 5: Build cache

**Giải pháp:** Clear cache và rebuild:

```bash
# Windows
gradlew clean build --refresh-dependencies

# Linux/Mac
./gradlew clean build --refresh-dependencies
```

Hoặc trong Android Studio:
- File → Invalidate Caches / Restart
- Chọn "Invalidate and Restart"

## Các lỗi khác

### Lỗi: Failed to resolve

1. Kiểm tra internet connection
2. Kiểm tra JitPack status: https://status.jitpack.io/
3. Thử sử dụng commit hash thay vì tag:
   ```gradle
   implementation 'com.github.hoangvannhatanh:CommonScreenSDK:COMMIT_HASH'
   ```

### Lỗi: Build failed trên JitPack

1. Kiểm tra log tại: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK
2. Xem chi tiết lỗi build
3. Đảm bảo `jitpack.yml` đúng cấu hình
4. Kiểm tra các dependencies có sẵn trên Maven Central

## Kiểm tra nhanh

1. ✅ Đã thêm `maven { url 'https://jitpack.io' }` vào repositories?
2. ✅ Tag `v1.0.0` đã tồn tại trên GitHub?
3. ✅ JitPack đã build xong (kiểm tra tại jitpack.io)?
4. ✅ Version trong dependency đúng format?
5. ✅ Đã sync Gradle sau khi thêm repository?

## Liên kết hữu ích

- JitPack Build Status: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK
- GitHub Repository: https://github.com/hoangvannhatanh/CommonScreenSDK
- JitPack Status: https://status.jitpack.io/

