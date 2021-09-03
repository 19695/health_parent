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
>
> 因为 oss 之前玩过了



### 前端



整体基于 h5 + vue + elementui

静态页面使用模板引擎技术 freemarker



## 问题记录



### idea  2020.3 版本管理变动

原本窗口上方的 CVS 变成了 Git （在配置好 git 插件之后（配置之前也不是原本的 CVS））

![image-20210904000011071](imgs/image-20210904000011071.png)

下方的窗口也从 Version Control 变成了 Git，并且原本的窗口中有 Local change

![image-20210904000117773](imgs/image-20210904000117773.png)



问题：好久没有使用过 share project  on github 了，突然本次同步到 github 想用一下，查了百度和 Google 都没有 2020.3 这个问题的有效描述及解决办法，最终还是采用了现在 GitHub 建 repo 然后本地 gitbash push 的方式（轻车熟路）

```shell
Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git remote add origin https://github.com/19695/health_parent.git

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git branch -M master

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git push -u origin master
Enumerating objects: 262, done.
Counting objects: 100% (262/262), done.
Delta compression using up to 8 threads
Compressing objects: 100% (191/191), done.
Writing objects: 100% (262/262), 4.06 MiB | 1.27 MiB/s, done.
Total 262 (delta 39), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (39/39), done.
To https://github.com/19695/health_parent.git
 * [new branch]      master -> master
Branch 'master' set up to track remote branch 'master' from 'origin'.
```





### git pull 异常



```
	Update failed
	unable to access 'https://github.com/19695/health_parent.git/': OpenSSL SSL_read: Connection was reset, errno 10054
```



![image-20210903235458687](imgs/image-20210903235458687.png)

操作记录：

```shell
Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git config --global -l
user.name=19695
user.email=1969575050@qq.com
http.sslverify=false

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git pull origin master
fatal: unable to access 'https://github.com/19695/health_parent.git/': OpenSSL SSL_read: Connection was reset, errno 10054

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git config --global http.sslVerify "false"

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git config --global -l
user.name=19695
user.email=1969575050@qq.com
http.sslverify=false

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git pull origin master
remote: Enumerating objects: 4, done.
remote: Counting objects: 100% (4/4), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 3 (delta 1), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), 1.02 KiB | 130.00 KiB/s, done.
From https://github.com/19695/health_parent
 * branch            master     -> FETCH_HEAD
   ce9a2bc..0c80952  master     -> origin/master
Updating ce9a2bc..0c80952
Fast-forward
 README.md | 22 ++++++++++++++++++++++
 1 file changed, 22 insertions(+)
 create mode 100644 README.md

```

push 的时候遇到了同样的问题
![img.png](imgs/img.png)

```shell
Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git config --global http.sslVerify "false"

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git push origin master
fatal: unable to access 'https://github.com/19695/health_parent.git/': OpenSSL SSL_read: Connection was reset, errno 10054

```

没有起到作用，考虑将 https 替换为 git

```shell
Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
git remote -v
origin  https://github.com/19695/health_parent.git (fetch)
origin  https://github.com/19695/health_parent.git (push)

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git remote -v
origin  https://github.com/19695/health_parent.git (fetch)
origin  https://github.com/19695/health_parent.git (push)

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git remote --help

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git remote rm origin

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git remote -v

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git remote add origin git@github.com:19695/health_parent.git

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git remote -v
origin  git@github.com:19695/health_parent.git (fetch)
origin  git@github.com:19695/health_parent.git (push)

Colm@SURFACE-BOOK3 MINGW64 /e/Workspaces/IdeaProjects/health_parent (master)
$ git push origin master
The authenticity of host 'github.com (52.74.223.119)' can't be established.
RSA key fingerprint is SHA256:nThbg6kXUpJWGl7E1IGOCspRomTxdCARLviKw6E5SY8.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added 'github.com,52.74.223.119' (RSA) to the list of known hosts.
Enumerating objects: 9, done.
Counting objects: 100% (9/9), done.
Delta compression using up to 8 threads
Compressing objects: 100% (7/7), done.
Writing objects: 100% (7/7), 93.97 KiB | 239.00 KiB/s, done.
Total 7 (delta 1), reused 0 (delta 0), pack-reused 0
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To github.com:19695/health_parent.git
   0c80952..ec99349  master -> master

```