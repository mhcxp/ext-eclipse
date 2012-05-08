<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"
	import="java.util.*,galaxy.ide.auth.role.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty sessionScope.username}">
	<jsp:forward page="/auth/login.jsp" />
</c:if>
<fmt:setBundle basename="nls.admin" />
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
		var _label = new Ext.form.Label({
			id : "labelID",
			text : ""
		});
		var _err = new Ext.form.Label({
			id : "err",
			text : "",
			cls : "ERROR"
		});

		var _uid = new Ext.form.TextField({
			name : 'uid'
		});
		_uid.hide();

		var _name = new Ext.form.TextField({
			fieldLabel : '昵称',
			name : 'name',
			minLength : 3,
			regex : regex_chinese,
			maxLength : 33,
			listeners : text_listeners
		});

		var _pass1 = new Ext.form.TextField({
			fieldLabel : '新密码',
			name : 'password1',
			minLength : 6,
			maxLength : 33,
			inputType : 'password',
			allowBlank : false,
			listeners : text_listeners
		});

		var _pass2 = new Ext.form.TextField({
			fieldLabel : '重复密码',
			name : 'password2',
			minLength : 6,
			maxLength : 33,
			inputType : 'password',
			allowBlank : false,
			listeners : text_listeners,
			validator : function() {
				if (_pass1.getValue() != this.getValue()) {
					return "两段密码不一致";
				} else
					return true;
			}
		});

		reset = function() {
			_uid.reset();
			_name.reset();
			_pass1.reset();
			_pass2.reset();
			button_ok.setDisabled(true);
		}


		var button_ok = new Ext.Button({
			text : '修改',
			disabled : true,
			handler : function() {
				if (panel.getForm().isValid()) {
					panel.getForm().submit();
					parent.closeWinModify();
					setTimeout('parent.refreshStore()', 200);
					setTimeout('reset()', 500);
				} else {
					this.setDisabled(true);
				}
			}
		});

		var panel = new Ext.FormPanel({
			labelWidth : 75, // label settings here cascade unless overridden
			url : SERVLET_URL + "?" + KEY_SERVICE + "="
					+ SERVICE_JSP_MODIFYADMIN,
			frame : true,
			title : '修改管理员信息',
			bodyStyle : 'padding:5px 5px 0',
			width : bd.width,
			height : bd.heigh,
			defaults : {
				width : 230
			},
			defaultType : 'textfield',
			items : [ _label, _err, _uid, _name, _pass1, _pass2 ],
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
			_uid.setValue(sel.get("uid"));
			_name.setValue(sel.get("name"));
			_label.setText("修改管理员" + sel.get("uid") + "的信息");
		}

		Ext.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
			panel.render(document.body);
			onshow();
		});
	</script>
</body>