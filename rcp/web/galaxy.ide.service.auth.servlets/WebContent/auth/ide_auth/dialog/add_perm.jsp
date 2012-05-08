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
		var regexText_16 = "请输入四位十六进制ID";
		var bd = Ext.getBody();

		var _pid = new Ext.form.TextField({
			fieldLabel : '权限ID',
			name : 'pid',
			regex : regex_16,
			regexText : regexText_16,
			minLength : 4,
			maxLength : 4,
			listeners : text_listeners,
			allowBlank : false
		});

		var _name = new Ext.form.TextField({
			fieldLabel : '权限名称',
			name : 'name',
			regex : regex_chinese,
			regexText : regexText_chinese,
			minLength : 3,
			maxLength : 33,
			listeners : text_listeners,
			allowBlank : false
		});

		var _desc = new Ext.form.TextField({
			fieldLabel : '权限描述',
			name : 'desc',
			listeners : text_listeners,
			maxLength : 100
		});

		//权限分类store
		var _pcstore = new Ext.data.JsonStore({
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
			typeAhead : true,
			triggerAction : 'all',
			lazyRender : true,
			mode : 'local',
			store : pcstore,
			valueField : 'pcid',
			hiddenName : 'category',
			displayField : 'pcname',
			listeners : text_listeners,
			allowBlank : false
		});

		reset = function() {
			_pid.reset();
			_name.reset();
			_desc.reset();
			_category.reset();
		}

		var button_ok = new Ext.Button({
			text : '保存',
			disabled : true,
			handler : function() {
				if (panel.getForm().isValid()) {
					panel.getForm().submit();
					parent.closeWin();
					setTimeout('parent.refreshStore()', 200);
					setTimeout('reset()', 500);
				} else {
					this.setDisabled(true);
				}
			}
		});

		var panel = new Ext.FormPanel({
			labelWidth : 75, // label settings here cascade unless overridden
			url : SERVLET_URL + "?" + KEY_SERVICE + "=" + SERVICE_JSP_ADDPERM,
			frame : true,
			title : '创建新权限',
			bodyStyle : 'padding:5px 5px 0',
			width : bd.width,
			height : bd.heigh,
			defaults : {
				width : 230
			},
			defaultType : 'textfield',

			items : [ _pid, _name, _desc, _category ],

			buttons : [ button_ok, {
				text : '取消',
				handler : function() {
					reset();
					parent.closeWin();
				}
			} ]
		});

		Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
			panel.render(document.body);
		});
	</script>
</body>