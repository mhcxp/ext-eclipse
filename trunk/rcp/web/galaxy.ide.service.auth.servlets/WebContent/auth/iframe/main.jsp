<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/auth/include/check_user.jsp"/>

<c:if test="${param.language=='zh'}">
	<fmt:setLocale value="zh" scope="session" />
</c:if>

<fmt:setBundle basename="nls.main" />

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />  
<title><fmt:message key="page.head.title" /></title>

<%
	String javaVersion = System.getProperty("java.version");
%>

<c:if test="${param.language=='zh'}">
	<fmt:setLocale value="zh" scope="session" />
</c:if>
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all-debug.js"></script>
<script type="text/javascript" src="/auth/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	var tab = new Ext.TabPanel({
		region : 'center',
		deferredRender : false,
		activeTab : 0,
		resizeTabs : true, // turn on tab resizing
		minTabWidth : 115,
		tabWidth : 135,
		enableTabScroll : true
	});

	function createNode(nid, title, img) {
		var node = new Ext.tree.TreeNode({
			id : nid,
			icon : img,
			text : title
		});
		return node;
	}

	function createSubNode(nid, title, url, img) {
		var node = new Ext.tree.TreeNode(
				{
					id : nid,
					icon : img,
					text : title,
					listeners : {
						'click' : function(node, event) {
							event.stopEvent();
							var n = tab.getComponent(node.id);
							if (!n) { // 判断是否已经打开该面板
								n = tab
										.add({
											'id' : node.id,
											'title' : node.text,
											closable : true, // 通过html载入目标页
											html : '<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'
													+ url + '"></iframe>'
										});
							}
							tab.setActiveTab(n);
						}
					}
				});
		return node;
	}

	Ext
			.onReady(function() {
				var v = new Ext.Viewport(
						{
							layout : "border",
							defaults : {
								bodyStyle : 'paddin:5px'
							},
							items : [
									{
										region : 'west',
										id : 'west-panel',
										split : true,
										width : 200,
										minSize : 175,
										maxSize : 400,
										margins : '0 0 0 0',
										layout : 'accordion',
										title : '',
										collapsible : true,
										layoutConfig : {
											animate : true
										},
										items : [
												{
													title : '管理配置',
													border : false,
													html : '<div id="tree1" style="overflow:auto;width:100%;height:100%"></div>'
												// iconCls:'nav'
												},
												{
													title : '信息中心',
													border : false,
													// iconCls:'settings',
													html : '<div id="tree2" style="overflow:auto;width:100%;height:100%"></div>'
												} ]
									},
									tab,
									{
										region : 'north',
										html : '<iframe src="/auth/iframe/top.jsp" width="100%"/>',
										height : 65,
										margin : '5 0 1 0'
									},
									{
										region : 'south',
										html : '<iframe src="/auth/iframe/foot.jsp" width="100%"/>',
										height : 40,
										margin : '1 0 5 0'
									} ]
						});

				new Ext.tree.TreePanel({
					renderTo : "tree1",
					root : manager_root, // 对应 根节点
					animate : true,
					enableDD : false,
					border : false,
					rootVisible : false,
					containerScroll : true
				});

				new Ext.tree.TreePanel({
					renderTo : "tree2",
					root : info_root, // 对应 根节点
					animate : true,
					enableDD : false,
					border : false,
					rootVisible : false,
					containerScroll : true
				});
			});
	/**
	 *管理配置
	 */
	var manager_root = new Ext.tree.TreeNode({
		id : "manager_root",
		text : "tree_root"
	});

	//管理员配置节点
	var n_m_config = createNode('n_m_config', '后台配置', '/auth/images/img_u.gif');
	n_m_config.appendChild(createSubNode('sn_m_admin', '管理员账户',
			'/auth/system/admin.jsp', '/auth/images/user1.gif'));

	manager_root.appendChild(n_m_config);

	//权限配置树
	var n_a_config = createNode('n_a_config', 'IDE权限配置', '/auth/images/img_u.gif');
	n_a_config.appendChild(createSubNode('sn_a_user', '用户配置',
			'/auth/ide_auth/user.jsp', '/auth/images/user.gif'));
	n_a_config.appendChild(createSubNode('sn_a_role', '角色配置',
			'/auth/ide_auth/role.jsp', '/auth/images/im2.gif'));
	n_a_config.appendChild(createSubNode('sn_a_perm', '权限配置',
			'/auth/ide_auth/permission.jsp', '/auth/images/HardWare.gif'));
	manager_root.appendChild(n_a_config);

	/**
	 *信息中心
	 */
	var info_root = new Ext.tree.TreeNode({
		id : "info_root",
		text : "tree_root"
	});
	//系统信息
	var n_i_system = createSubNode('n_i_system', '系统信息',
			'/auth/iframe/sysinfo.jsp', '/auth/images/im2.gif');
	info_root.appendChild(n_i_system);
</script>

<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />

</head>

<body>
	<div id="north"></div>
	<div id="west"></div>
	<div id="center"></div>
	<div id="south"></div>
</body>
</html>
