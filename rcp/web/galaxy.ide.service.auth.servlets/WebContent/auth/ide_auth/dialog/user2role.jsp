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
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
<script type="text/javascript" src="/auth/ext/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
	/*!
	 * Ext JS Library 3.2.1
	 * Copyright(c) 2006-2010 Ext JS, Inc.
	 * licensing@extjs.com
	 * http://www.extjs.com/license
	 */
	Ext.onReady(function() {
		var sel = parent.grid.getSelectionModel().getSelected();
		// Generic fields array to use in both store defs.
		var fields = [ {
			name : 'rid',
			mapping : 'rid'
		}, {
			name : 'name',
			mapping : 'name'
		}, {
			name : 'desc',
			mapping : 'desc'
		} ];

		var jsonReader = new Ext.data.JsonReader({
			root : 'rows',
			totalProperty : 'totalCount',
			remoteSort : true,
			fields : fields
		});
		// create the data store
		var firstGridStore = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : '/jspHttpServlet?service=jsp_showRoles&uid='
						+ sel.get('uid')
			}),
			reader : jsonReader
		});
		firstGridStore.load();
		onshow = function() {
			sel = parent.grid.getSelectionModel().getSelected();
			firstGridStore.proxy = new Ext.data.HttpProxy({
				url : '/jspHttpServlet?service=jsp_showRoles&uid='
						+ sel.get('uid')
			});
			firstGridStore.removeAll(false);
			firstGridStore.reload();
			secondGridStore.proxy = new Ext.data.HttpProxy({
				url : '/jspHttpServlet?service=jsp_showRoles&ex_uid='
						+ sel.get('uid')
			});
			secondGridStore.removeAll(false);
			secondGridStore.reload();
		}

		parent.winRoles.on('show', function() {
			onshow();
		})
		// Column Model shortcut array
		var cols = [ {
			id : 'rid',
			header : "ID",
			width : 80,
			sortable : true,
			dataIndex : 'rid'
		}, {
			id : 'name',
			header : "名",
			width : 80,
			sortable : true,
			dataIndex : 'name'
		}, {
			header : "描述",
			width : 50,
			sortable : true,
			dataIndex : 'desc'
		} ];

		// declare the source Grid
		var firstGrid = new Ext.grid.GridPanel({
			ddGroup : 'secondGridDDGroup',
			store : firstGridStore,
			columns : cols,
			enableDragDrop : true,
			stripeRows : true,
			autoExpandColumn : 'name',
			title : '已关联角色'
		});

		var secondGridStore = new Ext.data.Store({
			url : '/jspHttpServlet?service=jsp_showRoles&ex_uid='
					+ sel.get('uid'),
			reader : jsonReader
		});
		secondGridStore.load();
		// create the destination Grid
		var secondGrid = new Ext.grid.GridPanel({
			ddGroup : 'firstGridDDGroup',
			store : secondGridStore,
			columns : cols,
			enableDragDrop : true,
			stripeRows : true,
			autoExpandColumn : 'name',
			title : '未关联角色'
		});

		//Simple 'border layout' panel to house both grids
		var displayPanel = new Ext.Panel({
			width : 650,
			height : 300,
			layout : 'hbox',
			renderTo : 'panel',
			defaults : {
				flex : 1
			}, //auto stretch
			layoutConfig : {
				align : 'stretch'
			},
			items : [ firstGrid, secondGrid ],
			bbar : [ '->', {
				text : '保存',
				handler : function() {
					var Ids = [];
					for ( var i = 0; i < firstGrid.getStore().getCount(); i++) {
						Ids.push(firstGrid.getStore().getAt(i).get('rid'));
					}
					Ext.Ajax.request({
						url : "/jspHttpServlet",
						method : "POST",
						params : {
							service : "jsp_modifyUser",
							uid : sel.get('uid'),
							Ids : Ids.join(",")
						},
						success : function(response, opts) {
							grid.getStore().reload();
						}
					});
					parent.closeWinRoles();
					setTimeout('parent.refreshStore()', 200);
				}
			}, {
				text : '重置',
				handler : function() {
					onshow();
				}
			} ]
		});

		// used to add records to the destination stores
		var blankRecord = Ext.data.Record.create(fields);

		/****
		 * Setup Drop Targets
		 ***/
		// This will make sure we only drop to the  view scroller element
		var firstGridDropTargetEl = firstGrid.getView().scroller.dom;
		var firstGridDropTarget = new Ext.dd.DropTarget(firstGridDropTargetEl,
				{
					ddGroup : 'firstGridDDGroup',
					notifyDrop : function(ddSource, e, data) {
						var records = ddSource.dragData.selections;
						Ext.each(records, ddSource.grid.store.remove,
								ddSource.grid.store);
						firstGrid.store.add(records);
						firstGrid.store.sort('name', 'ASC');
						return true
					}
				});

		// This will make sure we only drop to the view scroller element
		var secondGridDropTargetEl = secondGrid.getView().scroller.dom;
		var secondGridDropTarget = new Ext.dd.DropTarget(
				secondGridDropTargetEl, {
					ddGroup : 'secondGridDDGroup',
					notifyDrop : function(ddSource, e, data) {
						var records = ddSource.dragData.selections;
						Ext.each(records, ddSource.grid.store.remove,
								ddSource.grid.store);
						secondGrid.store.add(records);
						secondGrid.store.sort('name', 'ASC');
						return true
					}
				});

	});
</script>

<body>
	<div id="panel"></div>
</body>
