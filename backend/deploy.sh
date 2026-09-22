#!/usr/bin/env bash
# 一键构建 AMD64 镜像并推送到阿里云 ACR（供 FC 部署使用）
#
# 用法：
#   ./deploy.sh          # 自动把版本号 +1 并构建推送
#
# 注意：
#   1. 推送前需要已执行过 docker login（登录 ACR）
#   2. 需要 Colima 处于运行状态（colima start）
#   3. 推送完成后，到 FC 控制台把函数镜像切换到新版本号，
#      等待"镜像资源准备完成"即可生效

set -e

REGISTRY="crpi-obx7agw4lvuzttzv.cn-beijing.personal.cr.aliyuncs.com"
NAMESPACE="dianmu"
REPO="app-api"

# 版本号管理：从 VERSION 文件读取当前版本，自动 +1
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
VERSION_FILE="${SCRIPT_DIR}/VERSION"

if [ -f "$VERSION_FILE" ]; then
    CURRENT=$(cat "$VERSION_FILE" | tr -d '[:space:]')
else
    CURRENT=4   # 当前线上已运行 v4
fi

NEXT=$((CURRENT + 1))
TAG="v${NEXT}"
IMAGE="${REGISTRY}/${NAMESPACE}/${REPO}:${TAG}"

echo "==> 构建并推送 ${IMAGE} (linux/amd64)"
docker buildx build \
    --platform linux/amd64 \
    --provenance=false \
    --sbom=false \
    -t "${IMAGE}" \
    --push \
    "${SCRIPT_DIR}"

echo "${NEXT}" > "$VERSION_FILE"

echo ""
echo "==> 推送完成: ${IMAGE}"
echo "==> 下一步：FC 控制台 → 函数 app-api → 配置 → 容器镜像 → 切换到 ${TAG}"
echo "             等待「镜像资源准备完成」后即可访问验证"
