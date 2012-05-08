<%@ page contentType="text/html;charset=utf-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	StringBuffer sb = new StringBuffer();
	sb.append("{'totalCount':'3','rows':[");

	sb.append("{\"id\":\"17\",\"instructions_no\":\"CO081027_00439\",\"unit_name\":\"部门名称1\",\"category_name\":\"楼书\",");
	sb.append("\"request_title\":\"部门名称1 楼书广告\",\"request_context\":\"部门名称1\",\"require_completion_time\":\"2008-10-31\",");
	sb.append("\"require_personnel\":\"\",\"action_name\":\"批准\",\"remark\":\"部门名称1\",\"annex\":\"3\"},");

	sb.append("{\"id\":\"18\",\"instructions_no\":\"CO081027_00440\",\"unit_name\":\"部门名称2二期\",\"category_name\":\"楼书\",");
	sb.append("\"request_title\":\"部门名称2 楼书广告\",\"request_context\":\"部门名称2 楼书广告\",\"require_completion_time\":\"2008-11-21\",");
	sb.append("\"require_personnel\":\"\",\"action_name\":\"批准\",\"remark\":\"部门名称2 楼书广告\",\"annex\":\"0\"},");

	sb.append("{\"id\":\"19\",\"instructions_no\":\"CO081027_00440\",\"unit_name\":\"部门名称2二期\",\"category_name\":\"楼书\",");
	sb.append("\"request_title\":\"部门名称1 楼书广告\",\"request_context\":\"部门名称2 楼书广告\",\"require_completion_time\":\"2008-11-21\",");
	sb.append("\"require_personnel\":\"\",\"action_name\":\"批准\",\"remark\":\"部门名称2 楼书广告\",\"annex\":\"0\"},");
	
	sb.append("{\"id\":\"20\",\"instructions_no\":\"CO081027_00443\",\"unit_name\":\"部门名称2二期\",\"category_name\":\"楼书\",");
	sb.append("\"request_title\":\"部门名称2 楼书广告\",\"request_context\":\"部门名称2 楼书广告\",\"require_completion_time\":\"2008-11-21\",");
	sb.append("\"require_personnel\":\"\",\"action_name\":\"批准\",\"remark\":\"部门名称2 楼书广告\",\"annex\":\"0\"}");

	
	
	sb.append("]}");
%>

<%=sb%>