package galaxy.sqlanlysis.core.dialect;

import galaxy.sqlanlysis.core.contant.DataTypeConstant;
import galaxy.sqlanlysis.core.function.NoArgSQLFunction;
import galaxy.sqlanlysis.core.function.NvlFunction;
import galaxy.sqlanlysis.core.function.SQLFunctionTemplate;
import galaxy.sqlanlysis.core.function.StandardSQLFunction;
import galaxy.sqlanlysis.core.function.VarArgsSQLFunction;

public class OracleDialect extends Dialect {
	public OracleDialect() {
		registerFunction("abs", new StandardSQLFunction("abs"));
		registerFunction("sign", new StandardSQLFunction("sign",
				DataTypeConstant.INTEGER));

		registerFunction("acos", new StandardSQLFunction("acos",
				DataTypeConstant.DOUBLE));
		registerFunction("asin", new StandardSQLFunction("asin",
				DataTypeConstant.DOUBLE));
		registerFunction("atan", new StandardSQLFunction("atan",
				DataTypeConstant.DOUBLE));
		registerFunction("cos", new StandardSQLFunction("cos",
				DataTypeConstant.DOUBLE));
		registerFunction("cosh", new StandardSQLFunction("cosh",
				DataTypeConstant.DOUBLE));
		registerFunction("exp", new StandardSQLFunction("exp",
				DataTypeConstant.DOUBLE));
		registerFunction("ln", new StandardSQLFunction("ln",
				DataTypeConstant.DOUBLE));
		registerFunction("sin", new StandardSQLFunction("sin",
				DataTypeConstant.DOUBLE));
		registerFunction("sinh", new StandardSQLFunction("sinh",
				DataTypeConstant.DOUBLE));
		registerFunction("stddev", new StandardSQLFunction("stddev",
				DataTypeConstant.DOUBLE));
		registerFunction("sqrt", new StandardSQLFunction("sqrt",
				DataTypeConstant.DOUBLE));
		registerFunction("tan", new StandardSQLFunction("tan",
				DataTypeConstant.DOUBLE));
		registerFunction("tanh", new StandardSQLFunction("tanh",
				DataTypeConstant.DOUBLE));
		registerFunction("variance", new StandardSQLFunction("variance",
				DataTypeConstant.DOUBLE));

		registerFunction("round", new StandardSQLFunction("round"));
		registerFunction("trunc", new StandardSQLFunction("trunc"));
		registerFunction("ceil", new StandardSQLFunction("ceil"));
		registerFunction("floor", new StandardSQLFunction("floor"));

		registerFunction("chr", new StandardSQLFunction("chr",
				DataTypeConstant.CHARACTER));
		registerFunction("initcap", new StandardSQLFunction("initcap"));
		registerFunction("lower", new StandardSQLFunction("lower"));
		registerFunction("ltrim", new StandardSQLFunction("ltrim"));
		registerFunction("rtrim", new StandardSQLFunction("rtrim"));
		registerFunction("soundex", new StandardSQLFunction("soundex"));
		registerFunction("upper", new StandardSQLFunction("upper"));
		registerFunction("ascii", new StandardSQLFunction("ascii",
				DataTypeConstant.INTEGER));
		registerFunction("length", new StandardSQLFunction("length",
				DataTypeConstant.LONG));

		registerFunction("to_char", new StandardSQLFunction("to_char",
				DataTypeConstant.STRING));
		registerFunction("to_date", new StandardSQLFunction("to_date",
				DataTypeConstant.TIMESTAMP));

		registerFunction("current_date", new NoArgSQLFunction("current_date",
				DataTypeConstant.DATE, false));
		registerFunction("current_time", new NoArgSQLFunction(
				"current_timestamp", DataTypeConstant.TIME, false));
		registerFunction("current_timestamp", new NoArgSQLFunction(
				"current_timestamp", DataTypeConstant.TIMESTAMP, false));

		registerFunction("last_day", new StandardSQLFunction("last_day",
				DataTypeConstant.DATE));
		registerFunction("sysdate", new NoArgSQLFunction("sysdate",
				DataTypeConstant.DATE, false));
		registerFunction("systimestamp", new NoArgSQLFunction("systimestamp",
				DataTypeConstant.TIMESTAMP, false));
		registerFunction("uid", new NoArgSQLFunction("uid",
				DataTypeConstant.INTEGER, false));
		registerFunction("user", new NoArgSQLFunction("user",
				DataTypeConstant.STRING, false));

		registerFunction("rowid", new NoArgSQLFunction("rowid",
				DataTypeConstant.LONG, false));
		registerFunction("rownum", new NoArgSQLFunction("rownum",
				DataTypeConstant.LONG, false));

		// Multi-param string dialect functions...
		registerFunction("concat", new VarArgsSQLFunction(
				DataTypeConstant.STRING, "", "||", ""));
		registerFunction("instr", new StandardSQLFunction("instr",
				DataTypeConstant.INTEGER));
		registerFunction("instrb", new StandardSQLFunction("instrb",
				DataTypeConstant.INTEGER));
		registerFunction("lpad", new StandardSQLFunction("lpad",
				DataTypeConstant.STRING));
		registerFunction("replace", new StandardSQLFunction("replace",
				DataTypeConstant.STRING));
		registerFunction("rpad", new StandardSQLFunction("rpad",
				DataTypeConstant.STRING));
		registerFunction("substr", new StandardSQLFunction("substr",
				DataTypeConstant.STRING));
		registerFunction("substrb", new StandardSQLFunction("substrb",
				DataTypeConstant.STRING));
		registerFunction("translate", new StandardSQLFunction("translate",
				DataTypeConstant.STRING));

		registerFunction("substring", new StandardSQLFunction("substr",
				DataTypeConstant.STRING));
		registerFunction("locate", new SQLFunctionTemplate(
				DataTypeConstant.INTEGER, "instr(?2,?1)"));
		registerFunction("bit_length", new SQLFunctionTemplate(
				DataTypeConstant.INTEGER, "vsize(?1)*8"));
		registerFunction("coalesce", new NvlFunction());

		// Multi-param numeric dialect functions...
		registerFunction("atan2", new StandardSQLFunction("atan2",
				DataTypeConstant.FLOAT));
		registerFunction("log", new StandardSQLFunction("log",
				DataTypeConstant.INTEGER));
		registerFunction("mod", new StandardSQLFunction("mod",
				DataTypeConstant.INTEGER));
		registerFunction("nvl", new StandardSQLFunction("nvl"));
		registerFunction("nvl2", new StandardSQLFunction("nvl2"));
		registerFunction("power", new StandardSQLFunction("power",
				DataTypeConstant.FLOAT));

		// Multi-param date dialect functions...
		registerFunction("add_months", new StandardSQLFunction("add_months",
				DataTypeConstant.DATE));
		registerFunction("months_between", new StandardSQLFunction(
				"months_between", DataTypeConstant.FLOAT));
		registerFunction("next_day", new StandardSQLFunction("next_day",
				DataTypeConstant.DATE));

		registerFunction("str", new StandardSQLFunction("to_char",
				DataTypeConstant.STRING));
	}

	protected String getAsToken() {
		return " ";
	}
}
