

下载workbench
mysql-workbench-community-6.3.9-osx-x86_64.dmg

关闭服务器防火墙
$ systemctl status firewalld
$ sudo systemctl stop firewalld
$ sudo systemctl disable firewalld

创建MySQL远程用户
CREATE USER 'root'@'%' IDENTIFIED BY '12345678';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;

