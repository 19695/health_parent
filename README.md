# health_parent

## 技术栈

### 后端

整体技术架构基于 ssm
分布式解决方案为 dubbo + zk
数据库为阿里云 rds
权限框架采用 spring security
批量导入基于 poi
定时任务基于 quartz
应用服务器采用基于 maven 插件形式的嵌入式 tomcat 容器
缓存中间件采用 redis
文件存储采用七牛云存储
> 为什么采用七牛云而不是阿里云？
> 因为 oss 之前玩过了

### 前端

整体基于 h5 + vue + elementui
静态页面使用模板引擎技术 freemarker
