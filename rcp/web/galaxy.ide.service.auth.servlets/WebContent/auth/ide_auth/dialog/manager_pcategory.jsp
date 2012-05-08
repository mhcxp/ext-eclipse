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
var tbar1=new Ext.Toolbar({
	renderTo:Ext.grid.GridPanel.tbar,
	items:[{
				xtype:'label',
	        	text:'新分类名称：'
	        	  },
			{
	        	xtype:'textfield',
	        	id:'newName',
	        	enableKeyEvents : true,
	        	listeners : { 
	                "change" : function() {
	                	checkValidInput();
	                	}
	                } 
	        },{
				xtype : "tbseparator"
			}, {
				xtype:'label',
				text:'新分类描述：'
				},
			{
				xtype:'textfield',
				id:'newDesc',
				listeners : { 
	                "change" : function() {
	                	checkValidInput();
	                	}
	                } 
				}
				]
});
checkValidInput=function(){
	var name=Ext.get('newName').getValue();
	var desc=Ext.get('newDesc').getValue();
	if(name!=null&&name!=""&&desc!=null&&desc!=""){
		btn_add.setDisabled(false);
	}else{
		btn_add.setDisabled(true);
	}
};

hasSelect=function(){
	var sel =grid.getSelectionModel().getSelected();
	if(sel)
		btn_del.setDisabled(false);
	else 
		btn_del.setDisabled(true);
}

var btn_add=new Ext.Button({
	text : '添加',
	icon : '/auth/images/add.gif',
	disabled:true,
	handler : function() {
		var newName=Ext.get('newName').getValue();
		var newDesc=Ext.get('newDesc').getValue();
		Ext.Ajax.request({
			url : SERVLET_URL,
			method : "POST",
			params : {
				newName:newName,
				newDesc:newDesc,
				service : SERVICE_JSP_ADDPCATE
			},
			success : function(
					response, opts) {
				grid.getStore()
						.reload();
			}
		});
	}
});

var btn_del=new Ext.Button({
	text : "删除",
	icon : '/auth/images/del.gif',
	disabled:true,
	handler : function() {
		var sm = grid.getSelectionModel();
		var rs = sm.getSelections();
		if (sm.hasSelection()) {
			Ext.MessageBox.confirm("请确认", "是否真的要删除指定的分类", function(
							button) {
						if (button == 'yes') {
							var Ids = [];
							for (var i = 0; i < rs.length; i++) {
								Ids.push(rs[i].get('pcid'));
								grid.getStore().remove(rs[i]);
							}
							Ext.Ajax.request({
										url : SERVLET_URL,
										method : "POST",
										params : {
											Ids : Ids.join(","),
											service : SERVICE_JSP_REMOVEPCATES
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
});

// 创建工具栏
var tbar = new Ext.Toolbar({
	renderTo : Ext.grid.GridPanel.tbar,// 其中grid是上边创建的grid容器
	items : [btn_add,{
				xtype : "tbseparator"
			}, btn_del, {
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
			name : 'rid',
			type : 'string'
		}, {
			name : 'ID',
			type : 'string'
		}, {
			name : 'Name',
			type : 'string'
		}, {
			name : 'Description',
			type : 'string'
		}]);

var columns = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
			header : '分类ID',
			dataIndex : 'ID',
			hidden:true
		}, {
			header : '分类名称',
			dataIndex : 'Name',
				sortable : true
		}, {
			header : '分类描述',
			dataIndex : 'Description'
		}]);
columns.defaultSortable = true;
	var store = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({
			url:SERVLET_URL+'?'+KEY_SERVICE+'='+SERVICE_JSP_SHOWPCATES
		}),
		reader : new Ext.data.JsonReader({
			fields:['ID','Name','Description'],
			root:'rows'
		}, UserRecord),
		remoteSort : true
	});
	
	
	// grid start
	var grid = new Ext.grid.GridPanel({
		region : 'center',
		title : '分类信息列表',
		loadMask : true,
		store:store,
		cm : columns,
		viewConfig : {
			forceFit : true
		},
		bbar : [new Ext.PagingToolbar({
			pageSize : ${max_row_count},
			store : store,
			displayInfo : true,
	        displayMsg: '显示条目{0} - {1} of {2}',
	        emptyMsg: "没有可显示的条目存在"
		})],
		tbar : [],
		listeners : {
			'rowclick':function(){
				hasSelect();
			},
			 'render' : function(){
				 tbar1.render(this.tbar);
				 tbar.render(this.tbar); 
			 }
			 }
	});
	
	
	store.load({
		params : {
			service:SERVICE_JSP_SHOWPCATES,
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
