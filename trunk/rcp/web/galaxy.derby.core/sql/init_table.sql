-- CREATE the admin table
CREATE TABLE admins (
c_id CHAR(50) PRIMARY KEY NOT NULL,
c_password CHAR(50) NOT NULL,
c_name CHAR(50) NOT NULL,
c_proj_group CHAR(40),
vc_department VARCHAR(50),
vc_remark VARCHAR(100)
) ;

-- INIT table admins
--INSERT INTO admins(c_id, c_password, c_name, c_proj_group,vc_department,vc_remark)
--   VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'GALA','产品','管理账号');
INSERT INTO admins(c_id, c_password, c_name, c_proj_group,vc_department,vc_remark)
    VALUES ('admin', 'admin', 'admin', 'GALA','产品','管理账号');



-- CREATE the users table
CREATE TABLE users (
c_id CHAR(50) PRIMARY KEY NOT NULL,
c_password CHAR(20) NOT NULL,
c_name CHAR(50) NOT NULL,
c_proj_group CHAR(40),
vc_department VARCHAR(50),
vc_remark VARCHAR(100)
) ;

-- INIT table users
INSERT INTO users(c_id, c_password, c_name, c_proj_group,vc_department,vc_remark)
    VALUES ('admin', 'admin', 'admin', 'GALA','产品','登录用管理号');

	
-- CREATE the relative table of users to roles 
CREATE TABLE re_user_role(
c_uid CHAR(50) NOT NULL,
c_rid CHAR(50) NOT NULL
);

-- INIT the relative table of users to roles 
INSERT INTO re_user_role(c_uid,c_rid)
	VALUES('admin','0001')
	;

-- CREATE the role table
CREATE TABLE roles(
c_id CHAR(50) PRIMARY KEY NOT NULL,
c_name CHAR(50) UNIQUE NOT NULL,
vc_desc VARCHAR(100)
);

-- INIT the role table
INSERT INTO roles(c_id,c_name,vc_desc)
	VALUES('0002','开发人员',''),
			('0001','项目经理','拥有全部权限');

-- CREATE the relative table of role to permission 
CREATE TABLE re_role_permission(
c_rid CHAR(50),
c_pid CHAR(50)
);

-- INIT the relative table of role to permission 

-- CREATE the permission table
CREATE TABLE permission(
c_id CHAR(50)PRIMARY KEY NOT NULL,
c_name CHAR(50) UNIQUE NOT NULL,
c_category CHAR(50) NOT NULL,
vc_desc VARCHAR(100)
);

-- INIT the permission table
-- INIT the permission table
INSERT INTO permission(c_id,c_name,c_category,vc_desc)
	VALUES('0000','新增技术组件','0000',''),
	('0100','删除技术组件','0000',''),
	('0200','修改技术组件','0000',''),
	('0400','部署技术组件','0000',''),
	('0500','查看技术组件','0000',''),
	('0700','导出技术组件文档','0000',''),
	('0001','新增业务组件','0001',''),
	('0101','删除业务组件','0001',''),
	('0201','修改业务组件','0001',''),
	('0301','编译业务组件','0001',''),
	('0401','部署业务组件','0001',''),
	('0501','查看业务组件','0001',''),
	('0701','导出业务组件文档','0001',''),
	('0002','新增交易模型','0002',''),
	('0102','删除交易模型','0002',''),
	('0202','修改交易模型','0002',''),
	('0302','编译交易模型','0002',''),
	('0402','部署交易模型','0002',''),
	('0502','查看交易模型','0002',''),
	('0702','导出交易模型文档','0002',''),
	('0003','新增交易','0003',''),
	('0103','删除交易','0003',''),
	('0203','修改交易','0003',''),
	('0303','编译交易','0003',''),
	('0403','部署交易','0003',''),
	('0503','查看交易','0003',''),
	('0703','导出交易文档','0003',''),
	('0004','新增交易模板','0004',''),
	('0104','删除交易模板','0004',''),
	('0204','修改交易模板','0004',''),
	('0504','查看交易模板','0004',''),
	('0704','导出交易模板文档','0004',''),
	('0005','新增数据库模型','0005',''),
	('0105','删除数据库模型','0005',''),
	('0205','修改数据库模型','0005',''),
	('0305','编译数据库模型','0005',''),
	('0405','部署数据库模型','0005',''),
	('0505','查看数据库模型','0005',''),
	('0106','删除数据字典','0006',''),
	('0506','查看数据字典','0006',''),
	('0606','同步数据字典','0006','');

-- CREATE the category of permission table
CREATE TABLE perm_category(
c_id CHAR(50) PRIMARY KEY NOT NULL,
c_name CHAR(50) UNIQUE NOT NULL,
vc_desc VARCHAR(100)
);

-- INIT the category of permission table
INSERT INTO perm_category(c_id,c_name,vc_desc)
	VALUES('0000','技术组件',''),
	('0001','业务组件',''),
	('0002','交易模型',''),
	('0003','交易',''),
	('0004','交易模板',''),
	('0005','数据库模型',''),
	('0006','数据字典','')