package galaxy.sqlanlysis.core.function;

import galaxy.sqlanlysis.core.exception.IllegalFunctionException;

import java.util.List;

public class NvlFunction implements SQLFunction {

	public boolean hasArguments() {
		return true;
	}

	public boolean hasParenthesesIfNoArguments() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public String render(@SuppressWarnings("rawtypes") List args)
			throws IllegalFunctionException {
		int lastIndex = args.size() - 1;
		Object last = args.remove(lastIndex);
		if (lastIndex == 0)
			return last.toString();
		Object secondLast = args.get(lastIndex - 1);
		String nvl = "nvl(" + secondLast + ", " + last + ")";
		args.set(lastIndex - 1, nvl);
		return render(args);
	}

}
