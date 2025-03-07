### YamlHelper

yaml(yml)文件助手，提供树视图，JSON视图查看yaml文件。<br/>
可以配置yaml key 备注，在树视图中展示备注。<br/>
可以在树视图中，复制key，value，key-value，node path。<br/>
可以从树视图跳转到yaml文件对应行

本项目参考了以下项目：

Json-Assistant : https://github.com/MemoryZy/Json-Assistant

Maven Helper : https://github.com/krasa/MavenHelper/

### 使用方法：

#### 树视图

![](/pic/Snipaste_2025-03-07_13-25-15.png)

#### JSON视图

![](/pic/Snipaste_2025-03-07_13-25-01.png)

#### 右键菜单

![](/pic/Snipaste_2025-03-07_12-07-54.png)

#### 定位

![](/pic/16.gif)

#### 配置文件示例 

keys_mark.yaml

```yaml
name: 名称
serviceCode: 服务编码
serviceScene: 服务场景
tranCode: 交易码
mock: 是否mock
active: 环境
url: 地址
username: 用户名
password: 密码
driverClassName: 驱动
db-type: 数据库类型
cache: 是否缓存
prefix: 前缀
static-locations: 静态资源
port: 端口
sensitive-column: 敏感字段
db-name: 数据库名
queryConfig: 查询字段
mapper-locations: mapper路径
map-underscore-to-camel-case: 下划线转驼峰
encodeKey: 加密key
mocks: 是否mock
element: 元素
profiles: 配置
```

![](/pic/Snipaste_2025-03-07_13-31-34.png)

![](/pic/Snipaste_2025-03-07_13-34-29.png)