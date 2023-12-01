# Week-5-Lab

Dự án này là một ứng dụng web dựa trên Java được phát triển bằng Spring Boot và Gradle. Nó sử dụng một số công nghệ như: Spring MVC, Spring Security, Spring Data JPA, Hibernate, Thymeleaf, Bootstrap,...

## Mô tả dự án

Ứng dụng này là một cổng thông tin việc làm nơi các công ty có thể đăng tuyển dụng và ứng viên có thể nộp đơn xin việc. Nó bao gồm các tính năng như đăng tuyển, quản lý hồ sơ ứng viên, xin việc và giới thiệu công việc dựa trên kỹ năng của ứng viên. 

## Công nghệ được sử dụng

- Java: Dự án được viết bằng Java, một ngôn ngữ lập trình hướng đối tượng, dựa trên lớp, cấp cao phổ biến.
- Spring Boot: Dự án này sử dụng Spring Boot, là một framework mã nguồn mở dựa trên Java được sử dụng để tạo các ứng dụng dựa trên Spring cấp độ sản xuất, độc lập.
- Gradle: Gradle được sử dụng làm công cụ xây dựng cho dự án này. Nó giúp tự động hóa việc xây dựng, thử nghiệm, xuất bản, triển khai và nhiều gói phần mềm hoặc các loại dự án khác.

## Cài đặt
- Java 11
- Gradle 7.0.2
- MySQL 8.0.25
- Thư viện: Spring Boot, Spring Security, Spring Data JPA, Hibernate, Thymeleaf, Bootstrap,...
- Build.gradle:
```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
    annotationProcessor 'org.projectlombok:lombok'
    providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    implementation 'com.neovisionaries:nv-i18n:1.29'
//    Database REST
    implementation 'org.springframework.boot:spring-boot-starter-data-rest'
    implementation 'org.webjars:bootstrap:5.3.2'
// Send mail
    implementation 'org.springframework.boot:spring-boot-starter-mail:3.2.0'
}
```

## Thiết lập và cài đặt

1. Sao chép kho lưu trữ vào máy cục bộ của bạn.
2. Điều hướng đến thư mục dự án.
3. Chạy `./gradlew bootRun` để khởi động ứng dụng.

## Cách sử dụng

Khi ứng dụng đang chạy, bạn có thể truy cập giao diện web bằng cách điều hướng đến `http://localhost:8080` trong trình duyệt web của bạn.

## Đóng góp

Đóng góp được chào đón. Vui lòng phân nhánh kho lưu trữ và tạo yêu cầu kéo với những thay đổi của bạn.
