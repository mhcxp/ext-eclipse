<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty sessionScope.username}">
	<jsp:forward page="/auth/login.jsp" />
</c:if>
<fmt:setBundle basename="nls.role" />
<!-- Include Ext stylesheets here: -->
<link rel="stylesheet" type="text/css"
	href="/auth/ext/resources/css/ext-all.css" />
<!-- Include YUI utilities and Ext: -->
<script type="text/javascript" src="/auth/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/auth/ext/ext-all.js"></script>
<script type="text/javascript" src="/auth/js/ide_auth/constants.js"></script>
<body>
	<script type="text/javascript">
		var bd = Ext.getBody();
		var _err = new Ext.form.Label({
			id : "err",
			text : ""
		});

		var _rid = new Ext.form.TextField({
			name : 'rid'
		});
		_rid.hide();

		var _name = new Ext.form.TextField({
			fieldLabel : '角色名',
			name : 'name',
			minLength : 3,
			regex : regex_chinese,
			listeners : text_listeners,
			maxLength : 33
		});

		var _desc = new Ext.form.TextField({
			fieldLabel : '角色描述',
			name : 'desc',
			listeners : text_listeners,
			maxLength : 33
		});

		reset = function() {
			_rid.reset();
			_name.reset();
			_desc.reset();
		}

		var button_ok = new Ext.Button({
			text : '修改',
			disabled : false,
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
			url : SERVLET_URL + "?" + KEY_SERVICE + "="
			+ SERVICE_JSP_MODIFYROLE,
			frame : true,
			title : '修改角色信息',
			bodyStyle : 'padding:5px 5px 0',
			width : bd.width,
			height : bd.heigh,
			defaults : {
				width : 230
			},
			defaultType : 'textfield',
			items : [ _err, _rid, _name, _desc ],
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
			_rid.setValue(sel.get("rid"));
			_name.setValue(sel.get("name"));
			_desc.setValue(sel.get("desc"));
		}

		Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
			panel.render(document.body);
			onshow();
		});
	</script>
</body>