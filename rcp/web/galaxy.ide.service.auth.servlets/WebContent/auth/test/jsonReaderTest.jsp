<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty sessionScope.username}">
	<jsp:forward page="/auth/login.jsp" />
</c:if>
<fmt:setBundle basename="nls.user" />

<c:if test="${empty sessionScope.users}">
	<c:redirect url="/jspHttpServlet">
		<c:param name="service" value="jsp_showUsers" />
	</c:redirect>
</c:if>
<c:set var="users" value="${sessionScope.users}" />
<c:set var="max_row_count" value="2" />
<c:set var="current_page" value="1" />

<head>
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
<script type="text/javascript" src="/auth/ext/ext-lang-zh_CN.js" charset="gb2312"></script>
<script>
var win =  new Ext.Window({ 
    title: '', 
    width: 400, 
    height: 300, 
    renderTo: document.body, 
    closeAction: 'hide', 
    layout: 'fit',
    html:'<iframe src="/auth/ide_auth/add_user.jsp" width=100% height=100%></iframe>'
    }); 
closeWin = function(){win.hide();}

var tbar = new Ext.Toolbar({
	renderTo : Ext.grid.GridPanel.tbar,// 其中grid是上边创建的grid容器
	items : [ {
		text : '添加',
		icon: '/auth/images/add.gif',
		handler: function() {
			win.show();
		}
	}, {
		xtype : "tbseparator"
	}, {
		text : "删除",
		icon: '/auth/images/del.gif',
		handler: function() {
			var sm = grid.getSelectionModel();
			var sel = sm.getSelected();
			if (sm.hasSelection()){
			Ext.MessageBox.confirm("请确认","是否真的要删除指定的内容",function(button){
		        if(button=='yes'){
		        	grid.getStore().remove(sel);
		            Ext.MessageBox.alert("提示","删除成功！");
		        }
		        else
		            return;
			});
			}else{
				Ext.MessageBox.alert("提示","没有选中任何行");
			}
		}
	} , {
		xtype : "tbseparator"
	}, {
		text : "刷新",
		icon: '/auth/images/refresh.gif',
		handler: function() {
			
		}
	}]
});

//创建选项框 

	var UserRecord = Ext.data.Record.create([ {
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
	}, {
		name : 'op',
		type : 'string'
	} ]);

	var store = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({
			url:'/auth/test/json.jsp'
		}),
		reader : new Ext.data.JsonReader({
			fields:['uid','name','info','roles','op'],
			root:'rows'
		}, UserRecord),
		pageSize : ${max_row_count},
		remoteSort : true
	});
	

	var columns = new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),{
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
	}, {
		header : '操作',
		dataIndex : 'op'
	} ]);
	columns.defaultSortable = true;

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
			displayInfo : true
		}),
		tbar : [ tbar]
	});
	
	
	
	
	store.load({
		params : {
			start : ${current_page},
			limit : ${max_row_count}
		}
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
