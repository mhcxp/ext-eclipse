package galaxy.sqlanlysis.core.function;

import galaxy.sqlanlysis.core.exception.IllegalFunctionException;
import galaxy.sqlanlysis.core.type.Type;

import java.util.List;

/**
 * 无参SQL函数
 * 
 * @author caiyu
 * @date 2012-11-26
 */
public class NoArgSQLFunction implements SQLFunction {
	private Type returnType;
	private boolean hasParenthesesIfNoArguments;
	private String name;

	public NoArgSQLFunction(String name, Type returnType) {
		this(name, returnType, true);
	}

	public NoArgSQLFunction(String name, Type returnType,
			boolean hasParenthesesIfNoArguments) {
		this.returnType = returnType;
		this.hasParenthesesIfNoArguments = hasParenthesesIfNoArguments;
		this.name = name;
	}

	@Override
	public boolean hasArguments() {
		return false;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return hasParenthesesIfNoArguments;
	}

	@Override
	public String render(List args) throws IllegalFunctionException {
		if (args.size() > 0) {
			throw new IllegalFunctionException("函数不包含参数: " + name);
		}
		return hasParenthesesIfNoArguments ? name + "()" : name;
	}

}
