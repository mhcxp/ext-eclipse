package galaxy.sqlanlysis.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Ä£ÐÍ×é
 * 
 * @author caiyu
 * @date 2012-11-26
 */
public class ColumnModelGroup extends SqlElementModel {
	private List<ColumnModel> chidren = new ArrayList<ColumnModel>();

	public void addChild(ColumnModel model, int index) {
		chidren.add(index, model);
	}

	public void removeChild(int index) {
		chidren.remove(index);
	}

	public ColumnModel getChild(int index) {
		return chidren.get(index);
	}

	public int getSize() {
		return chidren.size();
	}

	public boolean isEmpty() {
		return chidren.size() == 0;
	}
}
