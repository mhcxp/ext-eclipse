<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty sessionScope.username}">
	<jsp:forward page="/auth/login.jsp" />
</c:if>
<fmt:setBundle basename="nls.perm" />
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
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
			name : 'pid',
			mapping : 'pid'
		}, {
			name : 'name',
			mapping : 'name'
		}, {
			name : 'desc',
			mapping : 'desc'
		}, {
			name : 'category',
			mapping : 'category'
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
				url : '/jspHttpServlet?service=jsp_showPerms&rid='
						+ sel.get('rid')
			}),
			reader : jsonReader
		});

		firstGridStore.load();
		onshow = function() {
			sel = parent.grid.getSelectionModel().getSelected();
			firstGridStore.proxy = new Ext.data.HttpProxy({
				url : '/jspHttpServlet?service=jsp_showPerms&rid='
						+ sel.get('rid')
			});
			firstGridStore.removeAll(false);
			firstGridStore.reload();

			secondGridStore.proxy = new Ext.data.HttpProxy({
				url : '/jspHttpServlet?service=jsp_showPerms&ex_rid='
						+ sel.get('rid')
			});
			secondGridStore.removeAll(false);
			secondGridStore.reload();
		}

		parent.winPermissions.on('show', function() {
			onshow();
		})

		// Column Model shortcut array
		var cols = [{
			id : 'pid',
			header : "ID",
			width : 80,
			sortable : true,
			dataIndex : 'pid',
			hidden : true
		}, {
			id : 'name',
			header : "名称",
			width : 80,
			sortable : true,
			dataIndex : 'name'
		}, {
			header : "描述",
			width : 50,
			sortable : true,
			dataIndex : 'desc'
		}, {
			header : "分类",
			width : 50,
			sortable : true,
			dataIndex : 'category'
		} ];

		// declare the source Grid
		var firstGrid = new Ext.grid.GridPanel({
			ddGroup : 'secondGridDDGroup',
			store : firstGridStore,
			columns : cols,
			enableDragDrop : true,
			stripeRows : true,
			autoExpandColumn : 'name',
			title : '已关联权限'
		});

		var secondGridStore = new Ext.data.Store({
			url : '/jspHttpServlet?service=jsp_showPerms&ex_rid='
					+ sel.get('rid'),
			reader : jsonReader
		});
		secondGridStore.load();
		
		var secondGridStore2 = new Ext.data.Store({
			url : '/jspHttpServlet?service=jsp_showPerms&ex_rid='
					+ sel.get('rid'),
			reader : jsonReader
		});
		secondGridStore2.load();
		
		var searchPer = new Ext.form.TextField({
			name : 'searchPer',
			width : 150,
			emptyText : '请输入查询条件:名称',
			listeners:{			//监听   值改变则从新过滤
				change:function(){
					var search= searchPer.getValue();
					var num = secondGridStore.getCount();
					secondGridStore2.removeAll();
					for(var i = 0;i<num;i++){
						var name = secondGridStore.getAt(i).get("name");
						if(search==""){
							secondGridStore2.add(secondGridStore.getAt(i));
						}
						if(search!="" && name.indexOf(search) != -1){	//合格的
							secondGridStore2.add(secondGridStore.getAt(i));
						}
					}
				}
			}
		});
		// create the destination Grid
		var secondGrid = new Ext.grid.GridPanel({
			ddGroup : 'firstGridDDGroup',
			store : secondGridStore2,
			tbar: [searchPer],
			columns : cols,
			enableDragDrop : true,
			stripeRows : true,
			autoExpandColumn : 'name',
			title : '未关联权限'
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
						Ids.push(firstGrid.getStore().getAt(i).get('pid'));
					}

					Ext.Ajax.request({
						url : "/jspHttpServlet",
						method : "POST",
						params : {
							service : "jsp_modifyRole",
							rid : sel.get('rid'),
							Ids : Ids.join(",")
						},
						success : function(response, opts) {
							grid.getStore().reload();
						}
					});
					parent.closeWinPermissions();
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
						secondGridStore.remove(records);
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
						alert(records.get("name"));
						Ext.each(records, ddSource.grid.store.remove,
								ddSource.grid.store);
						secondGridStore.remove(records);
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
