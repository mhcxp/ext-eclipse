package galaxy.sqlanlysis.core.model.column;

import galaxy.sqlanlysis.core.model.SqlElementModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Ä£ÐÍ×é
 * 
 * @author caiyu
 * @date 2012-11-26
 */
public class ColumnModelGroup extends SqlElementModel {
	private List<ColumnModel> children = new ArrayList<ColumnModel>();

	public void addChild(ColumnModel model, int index) {
		children.add(index, model);
	}

	public void removeChild(int index) {
		children.remove(index);
	}

	public ColumnModel getChild(int index) {
		return children.get(index);
	}

	public int getSize() {
		return children.size();
	}

	public boolean isEmpty() {
		return children.size() == 0;
	}
}
