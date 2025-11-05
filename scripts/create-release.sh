#!/bin/bash

# Script để tạo release tag và push lên GitHub
# Usage: ./scripts/create-release.sh <version>
# Example: ./scripts/create-release.sh 1.0.0

if [ -z "$1" ]; then
    echo "Usage: ./scripts/create-release.sh <version>"
    echo "Example: ./scripts/create-release.sh 1.0.0"
    exit 1
fi

VERSION=$1
TAG="v${VERSION}"

echo "Creating release tag: ${TAG}"

# Kiểm tra xem tag đã tồn tại chưa
if git rev-parse "${TAG}" >/dev/null 2>&1; then
    echo "Tag ${TAG} already exists!"
    exit 1
fi

# Cập nhật version trong build.gradle (nếu cần)
# Có thể thêm script để tự động cập nhật version ở đây

# Tạo tag
git tag -a "${TAG}" -m "Release version ${VERSION}"

# Push tag lên GitHub
git push origin "${TAG}"

echo "Tag ${TAG} đã được tạo và push lên GitHub!"
echo "JitPack sẽ tự động build trong vài phút."
echo "Kiểm tra tại: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK"

