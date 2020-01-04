# CTF-AWD 训练平台

[TOC]

## 项目简介

一个简单的CTF-AWD平台，用于内部小型CTF对抗训练以及培训使用。

![1](img/1.png)

![2](img/2.png)



![](img/3.png)

## 特点

- docker化，简易部署
- 可部署在公网上，远程AWD攻防
- 训练环境可自定义扩展



## 基本使用方式

### pre.py

```bash
python pre.py web_chinaz 10
```

web_chinaz 为应用文件名称，10表示要生成的队伍数量



### start.py

```bash
python start.py 10 
```

启动10个实例，以及check和flag服务。



### stop_clean.py

```bash
python stop_clean.py
```

暂停所有服务，并删除临时文件

>  注意，这里会删除所有现运行的容器，请谨慎使用。



### pass.txt

存储队伍用户名密码，以及ssh、web端口

```bash
team01	ctf:308d66	ssh:2201	port:8801
team02	ctf:024b1d	ssh:2202	port:8802
team03	ctf:d5cbcc	ssh:2203	port:8803
team04	ctf:e29190	ssh:2204	port:8804
```



### Host.list

用户以及内网ip的对应关系



### Write_ups

包含各类环境的WP



### Web_xxx

预置四个Web环境（web_chinaz,web_simplecms,web_gotsctf2018,web_javatsctf2018)。

xxx代表环境名称。

如果为二进制，预期名称为Bin_xxx.



## 平台信息

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

   得到细化状态日志并得到**队伍token**

   **实时flag**也可以通过管理员界面查看

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


## 如何向此平台提供攻防环境

> 本项目提供相关示例，方便提供攻防环境.

### [web_example1]()

Web 简单部署版，只需要对外开启80以及22端口的，可以采用我推送的moxiaoxi/example为基础模块，进行后续开发。

```bash
.
├── checker.py check脚本
├── html Web代码
├── docker.sh 运行docker脚本
├── run.sh 基本运行脚本
└── tmp 临时文件夹
```

开发过程如下：

1. 将web环境代码拷贝至html目录
2. 自定义配置run.sh文件，比如配置数据库，多用户等等自定义配置
3. 为环境撰写checker.py，用于检测服务是否挂了。如果没有checker文件，将默认启动check_example.此时，check将不会生效，只会动态更新flag。

### [web_example2]()

Web自定义部署版，该版本实际为web_example的更高级自定义版本，主要用于支持某些环境可能依赖环境比较复杂，或许需要开启较多端口依赖，可在此处进行配置。

```bash
.
├── Dockerfile
├── apache2.conf
├── build_images.sh
├── checker.py
├── docker.sh
├── html
│   └── index.php
├── reset_docker.sh
├── run.sh
└── tmp

```

该版本，我们可以通过定制化Dockerfile进行更高级的定制化。如果需要多开端口，需要增加配置`-p {other_port1}:8088`，具体参考[web_gotsctf2018](https://github.com/m0xiaoxi/AWD_CTF_Platform/blob/master/web_gotsctf2018/docker.sh)环境。

1. 配置Dockerfile，docker.sh，并通过build_images.sh建立基础镜像。**（注：本步骤为必须操作）**
2. 将web环境代码拷贝至html目录
3. 自定义配置run.sh文件，比如配置数据库，多用户等等自定义配置
4. 为环境撰写checker.py，用于检测服务是否挂了。如果没有checker文件，将默认启动check_example.此时，check将不会生效，只会动态更新flag。
5. reset_docker.sh,主要用于重启单个容器（可忽略，不影响基础功能）。

### [Bin_example]()

先参考bin_pwn吧，有空写：）



## 致谢

- [Zhl20018#awd-platform](https://github.com/zhl2008/awd-platform)
- [Henryzhao#gotsctf2018](https://github.com/Henryzhao96)
- [Wulasite#javatsctf2018](https://github.com/wulasite)
- [HECTF#awd_platform](https://github.com/HECTF/awd_platform)
- [ishandsomedog#bin_pwn1](https://github.com/ishandsomedog)

# License

The AWD_CTF_Platform is released under the [GPLv3](https://github.com/m0xiaoxi/AWD_CTF_Platform/blob/master/LICENSE)