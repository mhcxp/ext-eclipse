<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/auth/include/check_user.jsp" />

<fmt:setBundle basename="nls.perm" />

<c:set var="max_row_count" value="100" />
<c:set var="current_page" value="0" />

<head>
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
<script type="text/javascript" src="/auth/js/ide_auth/constants.js"></script>
<script>
	Ext.QuickTips.init();

	var win = new Ext.Window(
			{
				title : '',
				width : 400,
				height : 300,
				renderTo : document.body,
				closeAction : 'hide',
				closable : false,
				layout : 'fit',
				html : '<iframe src="/auth/ide_auth/dialog/add_perm.jsp" width=400 height=300></iframe>'
			});
	closeWin = function() {
		win.hide();
	}

	var winCategory = new Ext.Window(
			{
				title : '',
				width : 600,
				height : 410,
				renderTo : document.body,
				closeAction : 'hide',
				layout : 'fit',
				html : '<iframe src="/auth/ide_auth/dialog/manager_pcategory.jsp" width=600 height=400></iframe>'
			});

	closeWinCategory = function() {
		winCategory.hide();
	}

	var winModify = new Ext.Window(
			{
				title : '',
				width : 420,
				height : 300,
				renderTo : document.body,
				closeAction : 'hide',
				closable : false,
				layout : 'fit',
				html : '<iframe src="/auth/ide_auth/dialog/modify_perm.jsp" width=400 height=300></iframe>'
			});

	closeWinModify = function() {
		winModify.hide();
	}

	var tbar = new Ext.Toolbar(
			{
				hidden:true,
				renderTo : Ext.grid.GridPanel.tbar,// 其中grid是上边创建的grid容器
				items : [
						{
							text : '添加',
							icon : '/auth/images/add.gif',
							handler : function() {
								win.show();
							}
						},
						{
							xtype : "tbseparator"
						},
						{
							text : '分类管理',
							icon : '/auth/images/category.gif',
							handler : function() {
								winCategory.show();
							}
						},
						{
							xtype : "tbseparator"
						},
						{
							text : "删除",
							icon : '/auth/images/del.gif',
							handler : function() {
								var sm = grid.getSelectionModel();
								var rs = sm.getSelections();
								if (sm.hasSelection()) {
									Ext.MessageBox
											.confirm(
													"请确认",
													"是否真的要删除指定的权限",
													function(button) {
														if (button == 'yes') {
															var Ids = [];
															for ( var i = 0; i < rs.length; i++) {
																Ids
																		.push(rs[i]
																				.get('pid'));
																grid
																		.getStore()
																		.remove(
																				rs[i]);
															}
															Ext.Ajax
																	.request({
																		url : SERVLET_URL,
																		method : "POST",
																		params : {
																			Ids : Ids
																					.join(","),
																			service : SERVICE_JSP_REMOVEPERMS
																		},
																		success : function(
																				response,
																				opts) {
																			grid
																					.getStore()
																					.reload();
																		}
																	});

														} else
															return;
													});
								} else {
									Ext.MessageBox.alert("提示", "没有选中任何行");
								}
							}
						}, {
							xtype : "tbseparator"
						}, {
							text : "刷新",
							icon : '/auth/images/refresh.gif',
							handler : function() {
								refreshStore();
							}
						} ]
			});
	var xg = Ext.grid;
	//获取数据
	var store = new Ext.data.GroupingStore({
		url : SERVLET_URL + '?' + KEY_SERVICE + '=' + SERVICE_JSP_SHOWPERMS,
		reader : new Ext.data.JsonReader({
			root : 'rows',
			totalProperty : 'totalCount',
			remoteSort : true,
			fields : [ {
				name : 'pid'
			}, {
				name : 'name'
			}, {
				name : 'desc'
			}, {
				name : 'category'
			}, {
				name : 'category_id'
			} ]
		}),
		sortInfo : {
			field : 'category',
			direction : 'ASC'
		},
		groupField : [ 'category' ]
	});
	store.load();

	refreshStore = function() {
		store.reload();
	}
	//====================================================================//

	var grid = new xg.GridPanel(
			{
				store : store,
				columns : [ new Ext.grid.RowNumberer(),//获得行号
				{
					header : "权限ID",
					sortable : true,
					width : 300,
					dataIndex : "pid",
					hidden : false
				}, {
					header : "权限名称",
					sortable : true,
					width : 300,
					dataIndex : "name"
				}, {
					header : "权限描述",
					sortable : true,
					width : 300,
					dataIndex : "desc"
				}, {
					header : "权限分类",
					sortable : true,
					width : 300,
					dataIndex : "category"
				}, {
					header : "权限分类ID",
					sortable : true,
					width : 300,
					dataIndex : 'category_id',
					hidden : true
				} ],
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
				title : '权限信息列表',
				iconCls : 'icon-grid',
				tbar : [ tbar ],
				region : 'center'
			});

	grid.on('rowdblclick', function() {
		//winModify.show();
	});
	// grid end
	Ext.onReady(function() {
		Ext.QuickTips.init();
		// layout start
		var viewport = new Ext.Viewport({
			layout : 'border',
			items : [ {
				region : 'north',
				contentEl : 'head'
			}, grid, {
				region : 'west',
				html : ''
			}, {
				region : 'south',
				contentEl : 'foot'
			} ]
		});
		// layout end
	});
</script>
<title>Ext.Data Test</title>
</head>
<body>
	<div id="head" style="font-weight: bold; font-size: 200%;"></div>
	<div id="foot" style="text-align: right;"></div>
</body>
