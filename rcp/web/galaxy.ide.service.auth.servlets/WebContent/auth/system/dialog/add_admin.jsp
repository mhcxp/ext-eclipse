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

		var listeners = {
			'valid' : function() {
				validHandler();
			},
			'invalid' : function() {
				invalidHandler();
			}
		};
		var _components = [];
		var _uid = new Ext.form.TextField({
			fieldLabel : '登录名',
			name : 'uid',
			minLength : 6,
			maxLength : 33,
			regex : regex_english,
			allowBlank : false,
			listeners : listeners
		});
		_components.push(_uid);

		var _name = new Ext.form.TextField({
			fieldLabel : '昵称',
			name : 'name',
			minLength : 3,
			regex : regex_chinese,
			maxLength : 33,
			listeners : listeners
		});
		_components.push(_name);
		var _pass1 = new Ext.form.TextField({
			fieldLabel : '输入密码',
			name : 'password1',
			minLength : 6,
			maxLength : 33,
			inputType : 'password',
			allowBlank : false,
			listeners : listeners
		});
		_components.push(_pass1);
		var _pass2 = new Ext.form.TextField({
			fieldLabel : '重复密码',
			name : 'password2',
			minLength : 6,
			maxLength : 33,
			inputType : 'password',
			enableKeyEvents : true,
			listeners : listeners,
			validator : function() {
				if (_pass1.getValue() != this.getValue()) {
					return "两段密码不一致";
				} else
					return true;
			},
			allowBlank : false
		});
		_components.push(_pass2);
		reset = function() {
			_uid.reset();
			_name.reset();
			_pass1.reset();
			_pass2.reset();
		}

		var button_ok = new Ext.Button({
			text : '保存',
			disabled : true,
			handler : function() {
				panel.getForm().submit();
				parent.closeWin();
				setTimeout('parent.refreshStore()', 200);
				setTimeout('reset()', 500);
			}
		});
		validHandler = function() {
			var flag = true;
			for ( var i = 0; i < _components.length; i++) {
				if (!_components[i].isValid(false)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				button_ok.setDisabled(false);
			}
		};

		invalidHandler = function() {
			button_ok.setDisabled(true);
		};

		var panel = new Ext.FormPanel({
			labelWidth : 75, // label settings here cascade unless overridden
			url : SERVLET_URL + "?" + KEY_SERVICE + "=" + SERVICE_JSP_ADDADMIN,
			frame : true,
			title : '创建新管理员',
			bodyStyle : 'padding:5px 5px 0',
			width : bd.width,
			height : bd.heigh,
			defaults : {
				width : 230
			},
			defaultType : 'textfield',

			items : [ _uid, _name, _pass1, _pass2 ],

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