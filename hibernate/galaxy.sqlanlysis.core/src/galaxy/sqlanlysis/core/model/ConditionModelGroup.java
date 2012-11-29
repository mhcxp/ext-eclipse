package galaxy.sqlanlysis.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件集合
 * 
 * @author caiyu
 * @date 2012-11-28
 */
public class ConditionModelGroup extends SqlElementModel {
	private List<ConditionModel> children = new ArrayList<ConditionModel>();

	public void addChild(ConditionModel model, int index) {
		children.add(index, model);
	}

	public void removeChild(int index) {
		children.remove(index);
	}

	public ConditionModel getChild(int index) {
		return children.get(index);
	}

	public int getSize() {
		return children.size();
	}

	public boolean isEmpty() {
		return children.size() == 0;
	}
}
