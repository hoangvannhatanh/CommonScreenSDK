# Hướng dẫn Setup JitPack

> **⚠️ LƯU Ý:** Đảm bảo bạn đã thay `YOUR_USERNAME` bằng username GitHub thực tế trong file `app/build.gradle` trước khi push code!

## Bước 1: Push code lên GitHub

1. Tạo repository mới trên GitHub (ví dụ: `CommonScreenSDK`)
2. Push code lên GitHub:

```bash
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/YOUR_USERNAME/CommonScreenSDK.git
git push -u origin main
```

## Bước 2: Tạo Release/Tag

Để JitPack có thể build và publish SDK, bạn cần tạo một tag (release):

### Cách 1: Tạo tag từ command line

```bash
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

### Cách 2: Tạo Release trên GitHub

1. Vào repository trên GitHub
2. Click "Releases" → "Create a new release"
3. Tạo tag mới: `v1.0.0` (hoặc `1.0.0`)
4. Điền title và description
5. Click "Publish release"

## Bước 3: Cấu hình trong build.gradle

**QUAN TRỌNG:** Trước khi push, cần cập nhật `app/build.gradle`:

1. Thay `USERNAME` bằng username GitHub của bạn trong:
   - `groupId = 'com.github.USERNAME'`
   - `url = 'https://github.com/USERNAME/CommonScreenSDK'`

2. Hoặc nếu muốn sử dụng format JitPack mặc định:
   ```gradle
   groupId = 'com.github.YOUR_USERNAME'
   artifactId = 'CommonScreenSDK' // Tên repository
   ```

## Bước 4: Sử dụng trong dự án khác

### Thêm JitPack repository

Trong `build.gradle` (project level) hoặc `settings.gradle`:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // Thêm dòng này
    }
}
```

Hoặc trong `build.gradle` (project level) cũ:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Thêm dependency

Trong `build.gradle` (app module):

```gradle
dependencies {
    // Sử dụng tag version
    implementation 'com.github.YOUR_USERNAME:CommonScreenSDK:v1.0.0'
    
    // Hoặc sử dụng commit hash
    // implementation 'com.github.YOUR_USERNAME:CommonScreenSDK:COMMIT_HASH'
    
    // Hoặc sử dụng branch
    // implementation 'com.github.YOUR_USERNAME:CommonScreenSDK:main-SNAPSHOT'
}
```

## Format JitPack

JitPack sử dụng format:
- **GroupId**: `com.github.USERNAME`
- **ArtifactId**: `REPOSITORY_NAME` (hoặc tên module nếu có)
- **Version**: Tag name (v1.0.0) hoặc commit hash

## Kiểm tra JitPack Build

Sau khi push tag, bạn có thể kiểm tra build status tại:
```
https://jitpack.io/#YOUR_USERNAME/CommonScreenSDK
```

Nếu build thành công, bạn sẽ thấy version có sẵn để sử dụng.

## Troubleshooting

### Build failed trên JitPack

1. Kiểm tra `jitpack.yml` có đúng không
2. Đảm bảo tất cả dependencies có sẵn trên Maven Central
3. Kiểm tra log build trên JitPack website

### Không tìm thấy artifact

1. Đợi vài phút sau khi push tag (JitPack cần thời gian build)
2. Kiểm tra tag name đúng format (v1.0.0 hoặc 1.0.0)
3. Kiểm tra repository name và username đúng

## Lưu ý

- JitPack sẽ tự động build từ source code trên GitHub
- Không cần publish thủ công
- Mỗi tag/commit mới sẽ tạo một version mới
- Build log có thể xem trên JitPack website

