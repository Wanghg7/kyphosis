
下载MySQL
MySQL-5.6.37-1.el7.x86_64.rpm-bundle.tar

找安装指南
google: how to install mysql community server on centos 7

删除mariadb-libs
$ sudo rpm -e postfix
$ sudo rpm -e mariadb-libs

安装前置依赖包
$ sudo yum install -y net-tools perl perl-Data-Dumper

安装MySQL
$ sudo rpm -ivh MySQL-client-*.rpm MySQL-server-*.rpm

启动MySQL
$ sudo chkconfig mysql on
$ sudo systemctl start mysql
$ sudo systemctl status mysql

查看端口
$ netstat -lnp | grep 3306

修改密码
$ DEFAULT_PASSWORD=`sudo cat /root/.mysql_secret | grep ': *[0-9a-zA-Z]\+ *$' | sed 's/.*: *\([0-9a-zA-Z]\+\) *$/\1/'`
$ mysql -uroot -p$DEFAULT_PASSWORD
mysql> SET PASSWORD FOR 'root'@'localhost' = PASSWORD('12345678');
mysql> show databases;

