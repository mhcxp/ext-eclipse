package galaxy.sqlanlysis.core.model.column;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 函数
 * 
 * @author caiyu
 * @date 2012-11-13
 */
public abstract class FunctionModel extends ColumnModel {
	private String name;
	private List<Object> args = new ArrayList<Object>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addArgument(Object arg) {
		if (arg != null)
			args.add(arg);
	}

	/**
	 * 获取全部参数
	 * 
	 * @return
	 */
	public List<Object> getArguments() {
		return new ArrayList<Object>(args);
	}

	public int getArgumentsSize() {
		return args.size();
	}
}
