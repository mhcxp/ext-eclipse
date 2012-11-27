package galaxy.sqlanlysis.core.dialect.layout;

import java.util.ArrayList;
import java.util.List;

/**
 * ÅÅÐòÆ÷
 * 
 * @author caiyu
 * @date 2012-11-16
 */
public class AnylsisSqlSorter {
	List<Integer> list = new ArrayList<Integer>();

	public void put(int element) {
		list.add(element);
	}

	public int[] getSort() {
		Integer[] is = list.toArray(new Integer[0]);
		int len = is.length;
		int[] sort = new int[len];
		for (int i = 0; i < len; i++) {
			sort[i] = is[i];
		}
		return sort;
	}
}
