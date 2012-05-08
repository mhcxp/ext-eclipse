<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="nls.user" />

<c:if test="${empty requestScope.users}">
	<c:redirect url="/jspHttpServlet">
		<c:param name="service" value="jsp_showUsers" />
	</c:redirect>
</c:if>
<c:set var="users" value="${requestScope.users}" />
<c:set var="max_row_count" value="6" />
<c:set var="current_page" value="0" />

<head>
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all-debug.js"></script>
<script type="text/javascript" src="/auth/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="/auth/js/ext-extend.js"></script>
<script>

	var UserRecord = Ext.data.Record.create([ {
		name : 'id',
		type : 'string'
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

	var store = new Ext.data.Store({
		data:[
		  	<c:forEach var="user" items="${users}">
		  		[,"${user.ID}","${user.name}","1","2","3"],
		  		[,"${user.ID}","${user.name}","1","2","3"],
		  		[,"${user.ID}","${user.name}","1","2","3"],
		  		[,"${user.ID}","${user.name}","1","2","3"],
		  		[,"${user.ID}","${user.name}","1","2","3"],
		  		[,"${user.ID}","${user.name}","1","2","3"],
		  		[,"${user.ID}","${user.name}","1","2","3"],
		  	</c:forEach>
		  		[]],
		proxy : new Ext.data.HttpProxy({
			url : '/list.jsp'//待定
		}),
		reader : new Ext.data.ArrayReader({
			idIndex:'0'
		}, UserRecord),
		pageSize : ${max_row_count},
		remoteSort : true
	});
	

	var columns = new Ext.grid.ColumnModel([ {
		header : '用户名',
		dataIndex : 'code',
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
	
	
	store.load({
		params : {
			start : 0,
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
</head>
<body class="x-gray">
	<script type="text/javascript" src="../shared/examples.js"></script>
	<!-- EXAMPLES -->
	<script type="text/javascript" src="../ux/PagingMemoryProxy.js"></script>
	<script type="text/javascript" src="multi-lang.js"></script>
	<div>
		<div style="float: left; padding: 3px 5px 0 0;">Language
			selector:&nbsp;</div>
		<div id="languages" style="float: left;"></div>
	</div>
	<br />
	<br />
	<h2>Email Field</h2>
	<div id="emailfield"></div>
	<br />
	<h2>Datepicker</h2>
	<br />
	<div id="datefield"></div>
	<br />
	<h2>Grid</h2>
	<br />
	<div id="grid"></div>
</body>
</html>