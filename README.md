# Jetty-Webservice-Android
Jetty-Webservice-Android

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
