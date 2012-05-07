package galaxy.ide.service.auth.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// DerbyAuthAdapter daa = new DerbyAuthAdapter();
		// System.out.println(daa.getAuthMsg("admin_01", "admin"));
		//
		// daa.dispose();

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (i == 17)
				continue;
			list.add(i);
		}
		int p = findBreakNumber(list);
		System.out.println(p);
	}

	private static int findBreakNumber(List<Integer> list) {
		int s = list.size();
		System.out.println(s);
		if (list.get(s - 1) - list.get(0) == s) {
			return list.get(s - 1) + 1;
		}
		
		return 0;
	}
}
