package galaxy.sqlanlysis.core.model.value;

import galaxy.sqlanlysis.core.model.SqlElementModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Öµ×é
 * 
 * @author caiyu
 * @date 2012-11-27
 */
public class ValueGroup extends SqlElementModel {
	private List<ValueModel> values = new ArrayList<ValueModel>();

	public void addValue(ValueModel model, int index) {
		values.add(index, model);
	}

	public void removeValue(int index) {
		values.remove(index);
	}

	public ValueModel getValue(int index) {
		return values.get(index);
	}

	public int getSize() {
		return values.size();
	}

	public boolean isEmpty() {
		return values.size() == 0;
	}
}
