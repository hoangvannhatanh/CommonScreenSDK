# Quick Start - Publish lên JitPack

## Bước 1: Cập nhật thông tin GitHub

1. Mở file `app/build.gradle`
2. Thay `USERNAME` bằng username GitHub của bạn ở 2 chỗ:
   - `groupId = 'com.github.USERNAME'` → `groupId = 'com.github.YOUR_USERNAME'`
   - `url = 'https://github.com/USERNAME/CommonScreenSDK'` → `url = 'https://github.com/YOUR_USERNAME/CommonScreenSDK'`

## Bước 2: Push code lên GitHub

```bash
git init
git add .
git commit -m "Initial commit - CommonScreenSDK"
git remote add origin https://github.com/YOUR_USERNAME/CommonScreenSDK.git
git branch -M main
git push -u origin main
```

## Bước 3: Tạo Release Tag

### Windows (PowerShell):
```powershell
.\scripts\create-release.ps1 1.0.0
```

### Linux/Mac:
```bash
chmod +x scripts/create-release.sh
./scripts/create-release.sh 1.0.0
```

### Hoặc thủ công:
```bash
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

## Bước 4: Kiểm tra trên JitPack

Sau vài phút, kiểm tra tại:
```
https://jitpack.io/#YOUR_USERNAME/CommonScreenSDK
```

Nếu build thành công, bạn sẽ thấy version `v1.0.0` có sẵn.

## Bước 5: Sử dụng trong dự án khác

### Thêm repository:
```gradle
// settings.gradle hoặc build.gradle (project level)
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Thêm dependency:
```gradle
// build.gradle (app module)
dependencies {
    implementation 'com.github.YOUR_USERNAME:CommonScreenSDK:v1.0.0'
}
```

## Troubleshooting

- **Build failed trên JitPack**: Kiểm tra log tại JitPack website
- **Không tìm thấy artifact**: Đợi vài phút, JitPack cần thời gian build
- **Version không hiển thị**: Đảm bảo tag name đúng format (v1.0.0 hoặc 1.0.0)

