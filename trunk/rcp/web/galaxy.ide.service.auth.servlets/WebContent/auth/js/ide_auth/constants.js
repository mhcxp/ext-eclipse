//���÷���
var text_listeners = {
	'change' : function() {
		changeHandler();
	}
};

changeHandler = function() {
	button_ok.setDisabled(false);
};

// ������֤
var regex_chinese = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]*$/;
var regex_english = /^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;
var regex_16 = /[A-Fa-f0-9]{4}/;
// �Ի����С�޶�

// servlet url
var SERVLET_URL = '/jspHttpServlet';

// ��ֵ����ؼ���
var KEY_SERVICE = 'service';

// �û�
var SERVICE_JSP_SHOWUSERS = "jsp_showUsers";

var SERVICE_JSP_ADDUSER = "jsp_addUser";

var SERVICE_JSP_REMOVEUSERS = "jsp_removeUser";

var SERVICE_JSP_MODIFYUSER = "jsp_modifyUser";

// ��ɫ
var SERVICE_JSP_SHOWROLES = "jsp_showRoles";

var SERVICE_JSP_ADDROLES = "jsp_addRole";

var SERVICE_JSP_REMOVEROLES = "jsp_removeRole";

var SERVICE_JSP_MODIFYROLE = "jsp_modifyRole";

// Ȩ��
var SERVICE_JSP_SHOWPERMS = "jsp_showPerms";

var SERVICE_JSP_ADDPERM = "jsp_addPerm";

var SERVICE_JSP_REMOVEPERMS = "jsp_removePerms";

var SERVICE_JSP_MODIFYPERM = "jsp_modifyPerm";

// ����
var SERVICE_JSP_SHOWPCATES = "jsp_showPCates";

var SERVICE_JSP_ADDPCATE = "jsp_addPCate";

var SERVICE_JSP_REMOVEPCATES = "jsp_removePCates";

var SERVICE_JSP_MODIFYPCATE = "jsp_modifyPCate";

// ִ��sql
var EXEC_SQL = "exec_sql";

// ����Ա
var SERVICE_JSP_SHOWADMINS = "jsp_showAdmins";

var SERVICE_JSP_MODIFYADMIN = "jsp_modifyAdmin";

var SERVICE_JSP_REMOVEADMINS = "jsp_removeAdmins";

var SERVICE_JSP_ADDADMIN = "jsp_addAdmin";