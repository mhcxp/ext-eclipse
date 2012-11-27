package galaxy.sqlanlysis.core.function;

import galaxy.sqlanlysis.core.type.Type;

import java.util.List;

/**
 * ±ê×¼SQLº¯Êý
 * 
 * @author caiyu
 * @date 2012-11-26
 */
public class StandardSQLFunction implements SQLFunction {
	private final String name;
	private final Type type;

	public StandardSQLFunction(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	public StandardSQLFunction(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean hasArguments() {
		return true;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return true;
	}

	@Override
	public String render(List args) {
		StringBuffer buf = new StringBuffer();
		buf.append(name).append('(');
		for (int i = 0; i < args.size(); i++) {
			buf.append(args.get(i));
			if (i < args.size() - 1) {
				buf.append(", ");
			}
		}
		return buf.append(')').toString();
	}

}
