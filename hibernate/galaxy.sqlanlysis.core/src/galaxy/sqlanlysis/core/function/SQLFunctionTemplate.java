package galaxy.sqlanlysis.core.function;

import galaxy.sqlanlysis.core.type.Type;

import java.util.ArrayList;
import java.util.List;

public class SQLFunctionTemplate implements SQLFunction {

	private final Type type;
	private final boolean hasArguments;
	private final boolean hasParenthesesIfNoArgs;

	private final String template;
	private final String[] chunks;
	private final int[] paramIndexes;

	public SQLFunctionTemplate(Type type, String template) {
		this(type, template, true);
	}

	public SQLFunctionTemplate(Type type, String template,
			boolean hasParenthesesIfNoArgs) {
		this.type = type;
		this.template = template;

		List chunkList = new ArrayList();
		List paramList = new ArrayList();
		StringBuffer chunk = new StringBuffer(10);
		StringBuffer index = new StringBuffer(2);

		for (int i = 0; i < template.length(); ++i) {
			char c = template.charAt(i);
			if (c == '?') {
				chunkList.add(chunk.toString());
				chunk.delete(0, chunk.length());

				while (++i < template.length()) {
					c = template.charAt(i);
					if (Character.isDigit(c)) {
						index.append(c);
					} else {
						chunk.append(c);
						break;
					}
				}

				paramList.add(new Integer(
						Integer.parseInt(index.toString()) - 1));
				index.delete(0, index.length());
			} else {
				chunk.append(c);
			}
		}

		if (chunk.length() > 0) {
			chunkList.add(chunk.toString());
		}

		chunks = (String[]) chunkList.toArray(new String[chunkList.size()]);
		paramIndexes = new int[paramList.size()];
		for (int i = 0; i < paramIndexes.length; ++i) {
			paramIndexes[i] = ((Integer) paramList.get(i)).intValue();
		}

		hasArguments = paramIndexes.length > 0;
		this.hasParenthesesIfNoArgs = hasParenthesesIfNoArgs;
	}

	public String render(List args) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < chunks.length; ++i) {
			if (i < paramIndexes.length) {
				Object arg = paramIndexes[i] < args.size() ? args
						.get(paramIndexes[i]) : null;
				if (arg != null) {
					buf.append(chunks[i]).append(arg);
				}
			} else {
				buf.append(chunks[i]);
			}
		}
		return buf.toString();
	}

	public boolean hasArguments() {
		return hasArguments;
	}

	public boolean hasParenthesesIfNoArguments() {
		return hasParenthesesIfNoArgs;
	}

	public String toString() {
		return template;
	}

}
