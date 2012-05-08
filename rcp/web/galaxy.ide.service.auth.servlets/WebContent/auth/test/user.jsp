<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setBundle basename="nls.user" />

<c:set var="users" value="${requestScope.users}" />
<c:set var="max_row_count" value="2" />
<c:set var="current_page" value="1" />

<head>
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
<script type="text/javascript" src="/auth/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/auth/js/ext-extend.js"></script>
<script>

	var UserRecord = Ext.data.Record.create([ {
		name : 'id',
		type : 'string'
		//dataFormat:
	}, {
		name : 'code',
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
	
	var reader = new Ext.data.JsonReader({
		root:'rows',
		totalProperty: 'totalCount',
		idProperty:'id'
		}
	
	var store = new Ext.data.Store({
		nocache : true,
		reader : reader,
		autoLoad : true,
		remoteSort : true,
		proxy : new Ext.data.HttpProxy({
			url : '/auth/test/get_user.jsp',
			method : 'GET'
		})
		});

	var columns = new Ext.grid.ColumnModel([ {
		header : '用户名',
		dataIndex : 'code',
		sortable : true
		//width:
		//renderer:
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
		trackMouseOver:false,
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : Ext.emptyFn
		}),
		viewConfig : {
			forceFit : true
		},
		bbar : new Ext.PagingToolbar({
			pageSize : ${max_row_count},
			store : store,
			displayInfo : true
		})
	});
	
	// render it
    grid.render();
	
    store.load({
		params : {
			start : ${current_page} * ${max_row_count},
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
