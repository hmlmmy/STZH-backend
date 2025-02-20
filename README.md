# 铁路大模型AI 

## 一、项目概述
本应用是一个基于前端 Vue 和后端 SSM（Spring + Spring MVC + MyBatis）架构的大模型对话网页应用，结合本地部署的 DeepSeek - R1 模型，实现用户与模型的实时对话，并将对话记录存储到 MySQL 数据库中。

## 二、技术栈
1. **前端**：Vue.js，用于构建用户界面，实现与用户的交互。
2. **后端**：
    - **Spring**：提供依赖注入、面向切面编程等功能，管理应用的业务逻辑组件。
    - **Spring MVC**：处理 Web 请求，实现前后端的数据交互。
    - **MyBatis**：持久层框架，负责与 MySQL 数据库进行交互，执行数据的增删改查操作。
3. **数据库**：MySQL，用于存储用户与模型的对话记录。
4. **模型**：本地部署的 DeepSeek - R1 模型，用于生成对话回复。

## 三、项目结构
### 前端
```
src/
├── components/
│   └── ChatComponent.vue # 对话组件，包含对话展示和输入框
├── App.vue # 主应用组件，引入对话组件
└── main.js # 前端入口文件，初始化 Vue 应用
```

### 后端
```
src/
├── main/
│   ├── java/
│   │   └── org/
│   │       └── ztshy/
│   │           └── chat/
│   │               ├── config/
│   │               │   └── CorsConfig.java # 处理跨域
│   │               ├── controller/
│   │               │   └── ChatController.java # 处理聊天请求的控制器
│   │               ├── entity/
│   │               │   └── ChatRecord.java # 对话记录实体类
│   │               ├── mapper/
│   │               │   └── ChatRecordMapper.java # 操作对话记录数据库的 Mapper 接口
│   │               └── service/
│   │                   └── ChatService.java # 处理聊天业务逻辑的服务类
│   ├── resources/
│   │   ├── applicationContext.xml # Spring 配置文件
│   │   ├──applicationContext.xml
│   │   └── mybatis-config.xml # MyBatis 配置文件
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── web.xml # Web 应用配置文件
│       │   └── views # 存放视图文件（如果有）
│       └── index.jsp # 初始页面（如果有）
└── pom.xml # Maven 项目配置文件
```

## 四、环境要求
1. **前端**：Node.js（建议版本 14 及以上）
2. **后端**：
    - Java Development Kit (JDK) 19 
    - Apache Maven 3.6 及以上
3. **数据库**：MySQL 8.0 及以上
4. **模型**：已本地部署好的 DeepSeek - R1 模型

## 五、安装与启动
### 前端
1. 进入前端项目目录，执行 `npm install` 安装依赖。
2. 安装完成后，执行 `npm run serve` 启动前端应用，默认访问地址为 `http://localhost:8080`。

### 后端
1. 确保 MySQL 数据库已安装并启动，创建数据库 `chat_db` 及相关表（参考数据库部分说明）。
2. 进入后端项目目录，执行 `mvn clean install` 进行项目构建和依赖下载。
3. 构建完成后，使用 IDE 启动后端应用（如在 IntelliJ IDEA 中直接运行主类），或打包成 WAR 包部署到 Servlet 容器（如 Tomcat）中。

## 六、数据库配置
在 `src/main/resources/applicationContext.xml` 中配置数据库连接信息：
```xml
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/stzh-database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC" />
    <property name="username" value="root" />
    <property name="password" value="password" />
</bean>
```

## 七、接口说明
### `/api/model/generate`
- **请求方法**：POST
- **请求参数**：
    - **question**：用户输入的问题，字符串类型，必填。
- **响应参数**：
    - **answer**：DeepSeek - R1 模型生成的回复，字符串类型。

## 八、跨域处理
在后端添加 `CorsConfig` 类进行配置：
```java
package com.example.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
```

## 九、常见问题与解决
1. **依赖无法解析**：参考 [依赖解析问题解决指南](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)，检查网络连接、本地仓库缓存、镜像配置等。
2. **数据库连接失败**：确保数据库配置正确，数据库服务已启动，用户名和密码正确。
3. **模型调用失败**：检查 DeepSeek - R1 模型的部署和配置，确保模型服务正常运行。

## 十、贡献指南
1. Fork 本项目到你的 GitHub 仓库。
2. 创建新的分支进行开发，分支命名规范：`feature/功能描述` 或 `fix/问题描述`。
3. 提交代码时，遵循统一的代码风格和规范。
4. 开发完成后，提交 Pull Request 到本项目的主分支。

## 十一、联系方式
如果在使用过程中遇到问题或有任何建议，欢迎通过以下方式联系：
- **邮箱**：your_email@example.com
- **GitHub**：[hmlmmy](https://github.com/hmlmmy)
