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
<script type="text/javascript" src="/auth/js/ide_auth/constants.js"></script>
<body>
	<script type="text/javascript">
		var regexText_chinese = "合法输入为中文、英文、数字以及下划线";
		var bd = Ext.getBody();
		var _err = new Ext.form.Label({
			id : "err",
			text : ""
		});

		var _pid = new Ext.form.TextField({
			name : 'pid'
		});
		_pid.hide();

		var _name = new Ext.form.TextField({
			fieldLabel : '权限名称',
			name : 'name',
			regex : regex_chinese,
			regexText : regexText_chinese,
			minLength : 3,
			maxLength : 33,
			listeners : text_listeners
		});

		var _desc = new Ext.form.TextField({
			fieldLabel : '权限描述',
			name : 'desc',
			maxLength : 100,
			listeners : text_listeners
		});
		//权限分类store
		var pcstore = new Ext.data.JsonStore({
			autoDestroy : true,
			url : SERVLET_URL + "?" + KEY_SERVICE + "="
					+ SERVICE_JSP_SHOWPCATES,
			storeId : 'myStore',
			// reader configs
			root : 'rows',
			idProperty : 'pcid',
			fields : [ 'pcid', 'pcname', 'pcdesc' ]
		});
		pcstore.load();
		var _category = new Ext.form.ComboBox({
			fieldLabel : '权限分类',
			name : 'category',
			hiddenName : 'category_id',
			typeAhead : true,
			triggerAction : 'all',
			lazyRender : true,
			mode : 'local',
			store : pcstore,
			valueField : 'pcid',
			displayField : 'pcname',
			emptyText : "请输入数据",
			listeners : text_listeners
		});

		reset = function() {
			_pid.reset();
			_name.reset();
			_desc.reset();
			_category.reset();
		}

		var button_ok = new Ext.Button({
			text : '修改',
			disabled : true,
			handler : function() {
				if (panel.getForm().isValid()) {
					panel.getForm().submit();
					parent.closeWinModify();
					setTimeout('parent.refreshStore()', 200);
				} else {
					this.setDisabled(true);
				}
			}
		});

		var panel = new Ext.FormPanel({
			labelWidth : 75, // label settings here cascade unless overridden
			//			url : SERVLET_URL + "?" + KEY_SERVICE + "="
			//					+ SERVICE_JSP_MODIFYPERM,
			url : "/jspHttpServlet?service=jsp_modifyPerm",
			frame : true,
			title : '修改权限信息',
			bodyStyle : 'padding:5px 5px 0',
			defaults : {
				width : 230
			},
			defaultType : 'textfield',
			items : [ _err, _pid, _name, _desc, _category ],
			buttons : [ button_ok, {
				text : '取消',
				handler : function() {
					reset();
					parent.closeWinModify();
				}
			} ]
		});

		parent.winModify.on('show', function() {
			onshow();
		})
		onshow = function() {
			var sel = parent.grid.getSelectionModel().getSelected();
			reset();
			_pid.setValue(sel.get("pid"));
			_name.setValue(sel.get("name"));
			_desc.setValue(sel.get("desc"));
			_category.setRawValue(sel.get("category"));
			_category.setValue(sel.get("category_id"));
		}

		Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
			panel.render("center");
			onshow();
		});
	</script>
	<div id="center"></div>
	<div id="south"></div>
</body>