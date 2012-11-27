package galaxy.sqlanlysis.core.function;

import galaxy.sqlanlysis.core.exception.IllegalFunctionException;
import galaxy.sqlanlysis.core.type.Type;

import java.util.List;

/**
 * 可变参SQL函数
 * 
 * @author caiyu
 * @date 2012-11-26
 */
public class VarArgsSQLFunction implements SQLFunction {
	private final String begin;
	private final String sep;
	private final String end;
	private final Type type;

	/**
	 * Constructs a VarArgsSQLFunction instance with a 'static' return type. An
	 * example of a 'static' return type would be something like an
	 * <tt>UPPER</tt> function which is always returning a SQL VARCHAR and thus
	 * a string type.
	 * 
	 * @param type
	 *            The return type.
	 * @param begin
	 *            The beginning of the function templating.
	 * @param sep
	 *            The separator for each individual function argument.
	 * @param end
	 *            The end of the function templating.
	 */
	public VarArgsSQLFunction(Type type, String begin, String sep, String end) {
		this.type = type;
		this.begin = begin;
		this.sep = sep;
		this.end = end;
	}

	/**
	 * Constructs a VarArgsSQLFunction instance with a 'dynamic' return type.
	 * For a dynamic return type, the type of the arguments are used to resolve
	 * the type. An example of a function with a 'dynamic' return would be
	 * <tt>MAX</tt> or <tt>MIN</tt> which return a double or an integer etc
	 * based on the types of the arguments.
	 * 
	 * @param begin
	 *            The beginning of the function templating.
	 * @param sep
	 *            The separator for each individual function argument.
	 * @param end
	 *            The end of the function templating.
	 * 
	 * @see #getReturnType Specifically, the 'columnType' argument is the
	 *      'dynamic' type.
	 */
	public VarArgsSQLFunction(String begin, String sep, String end) {
		this(null, begin, sep, end);
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * Always returns true here.
	 */
	public boolean hasArguments() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * Always returns true here.
	 */
	public boolean hasParenthesesIfNoArguments() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(List args) throws IllegalFunctionException {
		StringBuffer buf = new StringBuffer().append(begin);
		for (int i = 0; i < args.size(); i++) {
			buf.append(transformArgument((String) args.get(i)));
			if (i < args.size() - 1) {
				buf.append(sep);
			}
		}
		return buf.append(end).toString();
	}

	/**
	 * Called from {@link #render} to allow applying a change or transformation
	 * to each individual argument.
	 * 
	 * @param argument
	 *            The argument being processed.
	 * @return The transformed argument; may be the same, though should never be
	 *         null.
	 */
	protected String transformArgument(String argument) {
		return argument;
	}
}
