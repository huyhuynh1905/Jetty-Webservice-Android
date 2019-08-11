# Jetty-Webservice-Android
Jetty-Webservice-Android

Ứng dụng được viết sử dụng Jersey trên webserver để xử lí restful từ ứng dụng android gửi lên. Server được chạy bằng Java Jetty xử lí các request web bằng Servlet và xử lí các request android bằng Jersey!
--

Đối với api 23+ thì cần phải cấp quyền truy cập internet thêm:
-- Trong AndroidManifest.xml:
```
<application 
    android:networkSecurityConfig="@xml/network_security_config"
    ...>
```

-- Và tạo trong  res / xml / network_security_config.xml 
```
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="true">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>
</network-security-config>
```
