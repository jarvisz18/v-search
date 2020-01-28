java.nio.file.AccessDeniedException: …/elasticsearch-7.4.0/config/jvm.options
切换root用户，修改elastic用户权限
chown -R elastic:elastic  /usr/local/elasticsearch/

sh /usr/local/src/java/elasticsearch-6.2.4/bin/elasticsearch &
sh /usr/local/src/java/kibana-6.2.4/bin/kibana &

ERROR: [4] bootstrap checks failed
[1]: max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
修改/etc/security/limits.conf文件，增加配置，用户退出后重新登录生效
vi /etc/security/limits.conf

*               soft    nofile          65536
*               hard    nofile          65536

[2]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
elasticsearch用户拥有的内存权限太小，至少需要262144；
解决办法：
在   /etc/sysctl.conf文件最后添加一行
vi /etc/sysctl.conf
vm.max_map_count=262144
即可永久修改

[3]: JVM is using the client VM [Java HotSpot(TM) Client VM] but should be using a server VM for the best performance
JVM正在使用客户机VM [Java HotSpot(TM)客户机VM]，但是为了获得最佳性能，应该使用服务器VM
解决方案；
找到 jre安装目录 /lib /i386 /jvm.cfg 文件，JVM默认是client版本 如图所示，第一行和第二行互换位置即可，谁在上面就是谁。目前是Server VM
[4]: system call filters failed to install; check the logs and fix your configuration or disable system call filters at your own risk
原因：
这是在因为Centos6不支持SecComp，而ES5.2.0默认bootstrap.system_call_filter为true进行检测，所以导致检测失败，失败后直接导致ES不能启动。
解决：
在elasticsearch.yml中配置bootstrap.system_call_filter为false，注意要在Memory下面:
bootstrap.memory_lock: false
bootstrap.system_call_filter: false

Linux系统下ZIP文件解压和压缩命令
zip all.zip *.jpg   #将所有.jpg的文件压缩成一个zip包

unzip all.zip    #将all.zip中的所有文件解压到当前目录中

unzip all.zip -d all #将all.zip 中的所有文件解压到当前目录中的all文件夹中

zip -r hy.zip hy  #将当前目录下的hy文件夹压缩为hy.zip

zip -r hy.zip hy 123.txt  #将当前目录下的hy文件夹和123.txt压缩为hy.zip

-----------------------------------------------------------------------
./kibana: line 24: /usr/local/src/java/kibana-6.2.4/bin/../node/bin/node: cannot execute binary file
问题原因:可能属于环境不匹配

# lsof -i:3690
-bash: lsof: command not found
解决方法 我们可以通过yum来安装:
# yum install lsof

-bash: unzip: command not found
解决方案: yum install -y unzip zip

