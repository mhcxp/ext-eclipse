package galaxy.ide.service.auth.servlets.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionBean {
	private static List<String> colNameList;
	{
		colNameList = new ArrayList<String>();
		colNameList.add("c_id");
		colNameList.add("c_name");
		colNameList.add("vc_desc");
	}

	public static List<String> getColName() {
		return colNameList;
	}

	public static Map<String, List<String>> getAllPermissions() {
		Map<String, List<String>> perm_map = new HashMap<String, List<String>>();
		List<String> row = new ArrayList<String>();
		row.add("admin");
		row.add("≤Ã”");
		row.add("≤‚ ‘”√ªß");
		perm_map.put("admin", row);
		return perm_map;
	}
}
