# PowerShell script để tạo release tag và push lên GitHub
# Usage: .\scripts\create-release.ps1 <version>
# Example: .\scripts\create-release.ps1 1.0.0

param(
    [Parameter(Mandatory=$true)]
    [string]$Version
)

$Tag = "v$Version"

Write-Host "Creating release tag: $Tag" -ForegroundColor Green

# Kiểm tra xem tag đã tồn tại chưa
$existingTag = git rev-parse "$Tag" 2>$null
if ($existingTag) {
    Write-Host "Tag $Tag already exists!" -ForegroundColor Red
    exit 1
}

# Tạo tag
git tag -a "$Tag" -m "Release version $Version"

# Push tag lên GitHub
git push origin "$Tag"

Write-Host "Tag $Tag đã được tạo và push lên GitHub!" -ForegroundColor Green
Write-Host "JitPack sẽ tự động build trong vài phút." -ForegroundColor Yellow
Write-Host "Kiểm tra tại: https://jitpack.io/#hoangvannhatanh/CommonScreenSDK" -ForegroundColor Cyan

