<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/auth/include/check_user.jsp"/>
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
<script type="text/javascript" src="/auth/js/ide_auth/constants.js"></script>
<script>
//新窗口
var win = new Ext.Window({
	title : '',
	width : 400,
	height : 300,
	renderTo : document.body,
	closeAction : 'hide',
	closable : false,
	layout : 'fit',
	html : '<iframe src="/auth/ide_auth/dialog/add_user.jsp" width=400 height=300></iframe>'
});
closeWin = function() {
	win.hide();
}

var winRoles = new Ext.Window(
		{
			title : '',
			width : 660,
			height : 350,
			renderTo : document.body,
			closeAction : 'hide',
			layout : 'fit',
			html : '<iframe src="/auth/ide_auth/dialog/user2role.jsp" width=800 height=400></iframe>'
		});

closeWinRoles= function() {
	winRoles.hide();
}

var winModify = new Ext.Window({
	title : '',
	width : 400,
	height : 300,
	renderTo : document.body,
	closeAction : 'hide',
	closable : false,
	layout : 'fit',
	html : '<iframe src="/auth/ide_auth/dialog/modify_user.jsp" width=400 height=300></iframe>'
});

closeWinModify = function() {
	winModify.hide();
}

// 创建工具栏
var tbar = new Ext.Toolbar({
	renderTo : Ext.grid.GridPanel.tbar,// 其中grid是上边创建的grid容器
	items : [{
				text : '添加',
				icon : '/auth/images/add.gif',
				handler : function() {
					win.show();
				}
			}, {
				xtype : "tbseparator"
			}, {
				text : '关联角色',
				icon : '/auth/images/relate.gif',
				handler : function() {
					var sel =grid.getSelectionModel().getSelected();
					if(sel){
					winRoles.setTitle("角色管理:"+sel.get("name"));
					winRoles.html='<iframe src="/auth/ide_auth/dialog/user2role.jsp" width=800 height=300></iframe>';
					winRoles.show();}
				}
			}, {
				xtype : "tbseparator"
			}, {
				text : "删除",
				icon : '/auth/images/del.gif',
				handler : function() {
					var sm = grid.getSelectionModel();
					var rs = sm.getSelections();
					if (sm.hasSelection()) {
						Ext.MessageBox.confirm("请确认", "是否真的要删除指定的用户", function(
										button) {
									if (button == 'yes') {
										var Ids = [];
										for (var i = 0; i < rs.length; i++) {
											Ids.push(rs[i].get('uid'));
											grid.getStore().remove(rs[i]);
										}
										Ext.Ajax.request({
													url : SERVLET_URL,
													method : "POST",
													params : {
														Ids : Ids.join(","),
														service : SERVICE_JSP_REMOVEUSERS
													},
													success : function(
															response, opts) {
														grid.getStore()
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
			}]
});

var UserRecord = Ext.data.Record.create([{
			name : 'id',
			type : 'string'
		}, {
			name : 'uid',
			type : 'string'
		}, {
			name : 'name',
			type : 'string'
		}, {
			name : 'info',
			type : 'string'
		}, {
			name : 'roles',
			type : 'string'
		}]);

var columns = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '登录名',
			dataIndex : 'uid',
			sortable : true
		}, {
			header : '昵称',
			dataIndex : 'name'
		}, {
			header : '用户信息',
			dataIndex : 'info'
		}, {
			header : '用户角色',
			dataIndex : 'roles'
		}]);
columns.defaultSortable = true;

	var store = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({
			url:SERVLET_URL+"?"+KEY_SERVICE+"="+SERVICE_JSP_SHOWUSERS
		}),
		reader : new Ext.data.JsonReader({
			fields:['uid','name','info','roles'],
			root:'rows'
		}, UserRecord),
		remoteSort : true
	});

	
	// grid start
	var grid = new Ext.grid.GridPanel({
		region : 'center',
		title : '用户信息列表',
		loadMask : true,
		store:store,
		cm : columns,
		viewConfig : {
			forceFit : true
		},
		bbar : new Ext.PagingToolbar({
			pageSize : ${max_row_count},
			store : store,
			displayInfo : true,
	        displayMsg: '显示条目{0} - {1} of {2}',
	        emptyMsg: "没有可显示的条目存在"
		}),
		tbar : [ tbar]
	});
	
	grid.on('rowdblclick',function(){ 
		winModify.show();
		});
	
	store.load({
		params : {
			service:SERVICE_JSP_SHOWUSERS,
			start : 0,
			limit : ${max_row_count}
		}
	});

	refreshStore = function() {
		store.reload();
	}
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
