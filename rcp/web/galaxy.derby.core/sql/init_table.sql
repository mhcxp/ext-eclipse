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
--   VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', 'GALA','��Ʒ','�����˺�');
INSERT INTO admins(c_id, c_password, c_name, c_proj_group,vc_department,vc_remark)
    VALUES ('admin', 'admin', 'admin', 'GALA','��Ʒ','�����˺�');



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
    VALUES ('admin', 'admin', 'admin', 'GALA','��Ʒ','��¼�ù����');

	
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
	VALUES('0002','������Ա',''),
			('0001','��Ŀ����','ӵ��ȫ��Ȩ��');

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
	VALUES('0000','�����������','0000',''),
	('0100','ɾ���������','0000',''),
	('0200','�޸ļ������','0000',''),
	('0400','���������','0000',''),
	('0500','�鿴�������','0000',''),
	('0700','������������ĵ�','0000',''),
	('0001','����ҵ�����','0001',''),
	('0101','ɾ��ҵ�����','0001',''),
	('0201','�޸�ҵ�����','0001',''),
	('0301','����ҵ�����','0001',''),
	('0401','����ҵ�����','0001',''),
	('0501','�鿴ҵ�����','0001',''),
	('0701','����ҵ������ĵ�','0001',''),
	('0002','��������ģ��','0002',''),
	('0102','ɾ������ģ��','0002',''),
	('0202','�޸Ľ���ģ��','0002',''),
	('0302','���뽻��ģ��','0002',''),
	('0402','������ģ��','0002',''),
	('0502','�鿴����ģ��','0002',''),
	('0702','��������ģ���ĵ�','0002',''),
	('0003','��������','0003',''),
	('0103','ɾ������','0003',''),
	('0203','�޸Ľ���','0003',''),
	('0303','���뽻��','0003',''),
	('0403','������','0003',''),
	('0503','�鿴����','0003',''),
	('0703','���������ĵ�','0003',''),
	('0004','��������ģ��','0004',''),
	('0104','ɾ������ģ��','0004',''),
	('0204','�޸Ľ���ģ��','0004',''),
	('0504','�鿴����ģ��','0004',''),
	('0704','��������ģ���ĵ�','0004',''),
	('0005','�������ݿ�ģ��','0005',''),
	('0105','ɾ�����ݿ�ģ��','0005',''),
	('0205','�޸����ݿ�ģ��','0005',''),
	('0305','�������ݿ�ģ��','0005',''),
	('0405','�������ݿ�ģ��','0005',''),
	('0505','�鿴���ݿ�ģ��','0005',''),
	('0106','ɾ�������ֵ�','0006',''),
	('0506','�鿴�����ֵ�','0006',''),
	('0606','ͬ�������ֵ�','0006','');

-- CREATE the category of permission table
CREATE TABLE perm_category(
c_id CHAR(50) PRIMARY KEY NOT NULL,
c_name CHAR(50) UNIQUE NOT NULL,
vc_desc VARCHAR(100)
);

-- INIT the category of permission table
INSERT INTO perm_category(c_id,c_name,vc_desc)
	VALUES('0000','�������',''),
	('0001','ҵ�����',''),
	('0002','����ģ��',''),
	('0003','����',''),
	('0004','����ģ��',''),
	('0005','���ݿ�ģ��',''),
	('0006','�����ֵ�','')