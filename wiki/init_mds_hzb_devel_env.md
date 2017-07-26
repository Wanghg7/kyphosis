
size 512m autoextend on next 512m maxsize unlimited;


create tablespace TBS_SLDT_SSM datafile 'D:\window\app\admin\oradata\orcl\TBS_SLDT_SSM.DBF' size 512m autoextend on next 512m maxsize unlimited;

datafile 'D:\window\app\admin\oradata\orcl\TBS_SLDT_META.DBF'

create tablespace TBS_SLDT_META datafile 'D:\window\app\admin\oradata\orcl\TBS_SLDT_META.DBF' size 4096m autoextend on next 4096m maxsize unlimited;

# init mds database(orace hzb) #

## init mds ##

创建表空间：
CREATE TABLESPACE TBS_SLDT_META DATAFILE '/home/oracle/app/oracle/oradata/orcl/TBS_SLDT_META.DBF' SIZE 4096M AUTOEXTEND ON NEXT 4096M MAXSIZE UNLIMITED;

创建用户：
CREATE USER SLDT_META IDENTIFIED BY "123456" DEFAULT TABLESPACE TBS_SLDT_META;

用户授权：
GRANT CONNECT, RESOURCE, DBA TO SLDT_META;

导入数据：
imp SLDT_META/123456@orcl file=~/downloads/SLDT_META.dmp log=~/SLDT_META_$(date +%Y-%m-%d)_imp.log ignore=y full=y

## init ssm ##

创建表空间：
CREATE TABLESPACE TBS_SLDT_SSM DATAFILE '/home/oracle/app/oracle/oradata/orcl/TBS_SLDT_SSM.DBF' SIZE 512M AUTOEXTEND ON NEXT 512M MAXSIZE UNLIMITED;

创建用户：
CREATE USER SLDT_SSM IDENTIFIED BY "123456" DEFAULT TABLESPACE TBS_SLDT_SSM;

用户授权：
GRANT CONNECT, RESOURCE, DBA TO SLDT_SSM;

导入数据：
imp SLDT_SSM/123456@orcl file=~/downloads/SLDT_SSM.dmp log=~/SLDT_SSM_$(date +%Y-%m-%d)_imp.log ignore=y full=y


${catalina.base}/conf/Catalina/localhost/mds.xml
================================================================================
<?xml version="1.0" encoding="UTF-8"?>
<Context docBase="${catalina.base}/../sldt_mds/WebContent"
	antiJARLocking="false"
	antiResourceLocking="false"
	cookies="true"
	crossContext="true"
	debug="0"
	path="/mds"
	privileged="true">
	<Resource name="jdbc/Datasource_dmc"
		auth="Container"
		type="javax.sql.DataSource"
		driverClassName="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:orcl"
		username="SLDT_META"
		password="123456"
		maxIdle="30"
		maxWait="10000"
		maxActive="100"/>
</Context>

${catalina.base}/conf/Catalina/localhost/ssm.xml
================================================================================
<Context docBase="${catalina.base}/../sldt_ssm/WebRoot"
	antiJARLocking="false"
	antiResourceLocking="false"
	cookies="true"
	crossContext="true"
	debug="0" 
	path="/ssm"
	privileged="true">
	<Resource name="jdbc/Datasource_sm"
		auth="Container"
		type="javax.sql.DataSource"
		driverClassName="oracle.jdbc.driver.OracleDriver"
		url="jdbc:oracle:thin:@localhost:1521:orcl"
		username="SLDT_SSM"
		password="123456"
		maxIdle="30"
		maxWait="10000"
		maxActive="100"/>
</Context>

admin/1234abc@http://localhost:8080/ssm

jdk6
tomcat6
chrome

