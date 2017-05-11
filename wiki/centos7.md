显示网卡列表
nmcli d

显示IP地址
ip addr

网卡配置文件
/etc/sysconfig/network-scripts/ifcfg-*

端口映射规则
10.0.2.ip:port
port=1024+port+ip-10
10.0.2.10:22
1046=1024+22+10-10

从Mac OS X访问
LC_ALL=en_US.UTF-8 ssh -p 1046 wanghg@localhost
LC_ALL=en_US.UTF-8 sftp -P 1046 wanghg@localhost

用命令挂载光盘
mkdir /media/c7-1511
sudo mount /dev/cdrom /media/c7-1511

用配置挂在光盘
$ sudo vi /etc/fstab
/dev/cdrom /media/c7-1511 iso9660 ro 0 0

修改yum配置文件
/etc/yum.repos.d/CentOS-Media.repo
baseurl=file:///media/c7-1511/

用命令列出光盘上的repo信息
yum --disablerepo=\* --enablerepo=c7-media repolist

添加yum别名
vi .bashrc
alias yum='yum --noplugins --disablerepo=* --enablerepo=c7-media'
alias sudo='sudo '

Connection name:                linuxconfig ( can be any descriptive name )
VPN connetion type:             PPTP
PPTP VPN server IP or domain:   123.123.1.1
CHAP Username:                  admin
CHAP User password:             00000000
# yum install pptp
# modprobe nf_conntrack_pptp
# echo 'admin PPTP 00000000 *' >> /etc/ppp/chap-secrets
/etc/ppp/peers/linuxconfig
    pty "pptp 123.123.1.1 --nolaunchpppd"
    name admin
    remotename PPTP
    require-mppe-128
    file /etc/ppp/options.pptp
    ipparam linuxconfig
# pppd call linuxconfig
/var/log/messages
# pkill pppd

net-tools
sudo route add -net 0.0.0.0 dev ppp0
sudo route del -net 0.0.0.0 dev ppp0

