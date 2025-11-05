# Checklist - Publish SDK lên JitPack

## Trước khi push code

- [x] Đã cấu hình `app/build.gradle` với `hoangvannhatanh`
- [ ] Kiểm tra `versionName` trong `app/build.gradle` đúng với version bạn muốn
- [ ] Đảm bảo tất cả code đã được commit
- [ ] Kiểm tra `.gitignore` đã có đầy đủ các file cần ignore

## Setup GitHub Repository

- [x] Repository đã được tạo: `hoangvannhatanh/CommonScreenSDK`
- [ ] Push code lên GitHub (nếu chưa push):
  ```bash
  git init
  git add .
  git commit -m "Initial commit"
  git remote add origin https://github.com/hoangvannhatanh/CommonScreenSDK.git
  git push -u origin main
  ```

## Tạo Release

- [ ] Tạo tag version đầu tiên (ví dụ: `v1.0.0`)
  ```bash
  git tag -a v1.0.0 -m "Release version 1.0.0"
  git push origin v1.0.0
  ```
- [ ] Hoặc sử dụng script: `.\scripts\create-release.ps1 1.0.0` (Windows) hoặc `./scripts/create-release.sh 1.0.0` (Linux/Mac)

## Kiểm tra JitPack

- [ ] Đợi 2-5 phút sau khi push tag
- [ ] Kiểm tra build status tại: `https://jitpack.io/#hoangvannhatanh/CommonScreenSDK`
- [ ] Xác nhận version `v1.0.0` (hoặc version bạn đã tạo) có sẵn và build thành công

## Test trong dự án khác

- [ ] Thêm JitPack repository vào dự án test
- [ ] Thêm dependency: `implementation 'com.github.hoangvannhatanh:CommonScreenSDK:v1.0.0'`
- [ ] Sync và build dự án
- [ ] Test import và sử dụng SDK
- [ ] Kiểm tra không có lỗi

## Cập nhật version mới

Khi cần release version mới:

- [ ] Cập nhật `versionName` trong `app/build.gradle`
- [ ] Commit và push code
- [ ] Tạo tag mới với version mới
- [ ] Kiểm tra trên JitPack

