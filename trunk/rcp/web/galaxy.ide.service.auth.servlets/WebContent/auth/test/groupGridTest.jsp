<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="nls.user" />

<c:set var="max_row_count" value="5" />
<c:set var="current_page" value="0" />

<head>
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
<script type="text/javascript" src="/auth/ext/ext-lang-zh_CN.js"></script>
<script>
	Ext.BLANK_IMAGE_URL = 'lib/extjs/resources/images/default/s.gif';
	Ext
			.onReady(function() {

				Ext.QuickTips.init();

				var xg = Ext.grid;
				//获取数据
				var store = new Ext.data.GroupingStore({
					url : '/jspHttpServlet?service=jsp_showPerms',
					reader : new Ext.data.JsonReader({
						root : 'rows',
						totalProperty : 'totalCount',
						remoteSort : true,
						fields : [ {
							name : 'pid'
						}, {
							name : 'name'
						}, {
							naem : 'desc'
						}, {
							name : 'category'
						}]
					}),
					sortInfo : {
						field : 'category',
						direction : 'ASC'
					},
					groupField : [ 'category' ]
				});
				store.load();
				//====================================================================//

				var grid = new xg.GridPanel(
						{
							store : store,
							columns : [ new Ext.grid.RowNumberer(),//获得行号
							{
								header : "项目编号",
								sortable : true,
								width : 300,
								dataIndex : "pid"
							}, {
								header : "部门名称",
								sortable : true,
								width : 300,
								dataIndex : "name"
							}, {
								header : "项目类型",
								sortable : true,
								width : 300,
								dataIndex : "desc"
							}, {
								header : "项目标题",
								sortable : true,
								width : 300,
								dataIndex : "category"
							}],
							view : new Ext.grid.GroupingView(
									{
										forceFit : true,
										groupTextTpl : '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
									}),
							frame : true,
							width : 900,
							height : 450,
							collapsible : true,
							animCollapse : false,
							title : '协作信息',
							iconCls : 'icon-grid',
							renderTo : document.body
						});
			});
</script>
<title>分组表格测试</title>
</head>
<body>
	<div id="head" style="font-weight: bold; font-size: 200%;"></div>
	<div id="foot" style="text-align: right;"></div>
</body>
