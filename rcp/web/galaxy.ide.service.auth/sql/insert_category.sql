DELETE FROM perm_category;

-- INIT the category of permission table
INSERT INTO perm_category(c_id,c_name,vc_desc)
	VALUES('0000','技术组件',''),
	('0001','业务组件',''),
	('0002','交易模型',''),
	('0003','交易',''),
	('0004','交易模板',''),
	('0005','数据库模型',''),
	('0006','数据字典',''),
	('0007','项目','')