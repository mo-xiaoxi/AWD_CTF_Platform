# 细化说明

[TOC]



## 提交平台

1. 首页

   http://localhost:9090/

2. 查看总榜

   http://localhost:9090/score/

3. 管理员登陆页面：

   http://localhost:9090/accounts/login/?next=/admin/

   账户：moxiaoxi

   密码：moxiaoxi123456

   管理界面：

   http://localhost:9090/admin/

   可以用于手动修正靶机状态

4. 管理员排行榜信息

   http://localhost:9090/admin/table/

   得到细化状态日志并得到队伍token

   实时flag也可以通过管理员界面查看

   此外，check信息也可以通过状态日志查看。

5. 提交flag

   1. 直接在首页提交

   2. curl提交

      ```bash
      curl http://localhost:9090 -d "flag=85e630d8bb65e4cda2bd69185363af54&token=97e361a1df6b0cd7bfda8c1f7be7bdb3"
      ```

   提交状态如下：

   ```js
   <script>hulla.send("flag提交成功！", "success");</script>
   <script>hulla.send("flag已提交", "warning");</script>
   <script>hulla.send("flag已过期", "warning");</script>
   <script>hulla.send("token错误", "warning");</script>
   <script>hulla.send("flag错误", "warning");</script>
   ```

   


## check 服务器

用于运行状态检测服务器，并用于定期更新flag

## Web_xxx

```bash
.
├── Dockerfile 基本dockerfile
├── apache2.conf 基本配置
├── build_images.sh 构建images
├── checker.py check脚本
├── chinaz 应用名称
├── docker.sh 运行docker脚本
├── flag.py 更新flag脚本
├── reset_docker.sh 重置docker脚本
├── run.sh 基本运行脚本
└── tmp 临时文件夹
```

必须存在文件：

1. docker.sh 基本的运行启动文件
2. flag.py 更新flag脚本
3. Run.sh 基本的运行文件
4. checker文件，最好有。如果没有checker文件，将默认启动check_example.此时，check将不会生效，只会动态更新flag。
5. Chinaz 应用文件,可配置

其余文件均可配置与调控

提供两种模式，向此平台提供攻防环境。

- Docker原始数据

  提供docker构建环境，然后参考现在的示例提供即可

- 推送到dockerhub上，直接使用即可。

  