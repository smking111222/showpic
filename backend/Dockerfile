# ===== 构建阶段 =====
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
# 使用阿里云 Maven 镜像加速依赖下载（国内环境）
RUN mkdir -p /root/.m2 && printf '%s\n' \
    '<?xml version="1.0" encoding="UTF-8"?>' \
    '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">' \
    '  <mirrors>' \
    '    <mirror>' \
    '      <id>aliyunmaven</id>' \
    '      <mirrorOf>central</mirrorOf>' \
    '      <name>Aliyun Maven Mirror</name>' \
    '      <url>https://maven.aliyun.com/repository/public</url>' \
    '    </mirror>' \
    '  </mirrors>' \
    '</settings>' > /root/.m2/settings.xml
COPY pom.xml .
# 先单独拉取依赖，利用 Docker 层缓存加速后续构建
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B package -DskipTests

# ===== 运行阶段 =====
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
# Render / Koyeb 等平台通过 PORT 环境变量指定端口
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
