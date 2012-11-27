package galaxy.sqlanlysis.core.dialect;

import galaxy.sqlanlysis.core.contant.DataTypeConstant;
import galaxy.sqlanlysis.core.function.NoArgSQLFunction;
import galaxy.sqlanlysis.core.function.StandardSQLFunction;
import galaxy.sqlanlysis.core.function.VarArgsSQLFunction;
import galaxy.sqlanlysis.core.model.AnlysisStatementModel;

/**
 * HSQL·½ÑÔ
 * 
 * @author caiyu
 * @date 2012-11-12
 */
public class HSQLDialect extends Dialect {
	public HSQLDialect() {
		registerFunction("ascii", new StandardSQLFunction("ascii",
				DataTypeConstant.INTEGER));
		registerFunction("char", new StandardSQLFunction("char",
				DataTypeConstant.CHARACTER));
		registerFunction("length", new StandardSQLFunction("length",
				DataTypeConstant.LONG));
		registerFunction("lower", new StandardSQLFunction("lower"));
		registerFunction("upper", new StandardSQLFunction("upper"));
		registerFunction("lcase", new StandardSQLFunction("lcase"));
		registerFunction("ucase", new StandardSQLFunction("ucase"));
		registerFunction("soundex", new StandardSQLFunction("soundex",
				DataTypeConstant.STRING));
		registerFunction("ltrim", new StandardSQLFunction("ltrim"));
		registerFunction("rtrim", new StandardSQLFunction("rtrim"));
		registerFunction("reverse", new StandardSQLFunction("reverse"));
		registerFunction("space", new StandardSQLFunction("space",
				DataTypeConstant.STRING));
		registerFunction("rawtohex", new StandardSQLFunction("rawtohex"));
		registerFunction("hextoraw", new StandardSQLFunction("hextoraw"));

		registerFunction("user", new NoArgSQLFunction("user",
				DataTypeConstant.STRING));
		registerFunction("database", new NoArgSQLFunction("database",
				DataTypeConstant.STRING));

		registerFunction("current_date", new NoArgSQLFunction("current_date",
				DataTypeConstant.DATE, false));
		registerFunction("curdate", new NoArgSQLFunction("curdate",
				DataTypeConstant.DATE));
		registerFunction("current_timestamp", new NoArgSQLFunction(
				"current_timestamp", DataTypeConstant.TIMESTAMP, false));
		registerFunction("now", new NoArgSQLFunction("now",
				DataTypeConstant.TIMESTAMP));
		registerFunction("current_time", new NoArgSQLFunction("current_time",
				DataTypeConstant.TIME, false));
		registerFunction("curtime", new NoArgSQLFunction("curtime",
				DataTypeConstant.TIME));
		registerFunction("day", new StandardSQLFunction("day",
				DataTypeConstant.INTEGER));
		registerFunction("dayofweek", new StandardSQLFunction("dayofweek",
				DataTypeConstant.INTEGER));
		registerFunction("dayofyear", new StandardSQLFunction("dayofyear",
				DataTypeConstant.INTEGER));
		registerFunction("dayofmonth", new StandardSQLFunction("dayofmonth",
				DataTypeConstant.INTEGER));
		registerFunction("month", new StandardSQLFunction("month",
				DataTypeConstant.INTEGER));
		registerFunction("year", new StandardSQLFunction("year",
				DataTypeConstant.INTEGER));
		registerFunction("week", new StandardSQLFunction("week",
				DataTypeConstant.INTEGER));
		registerFunction("quater", new StandardSQLFunction("quater",
				DataTypeConstant.INTEGER));
		registerFunction("hour", new StandardSQLFunction("hour",
				DataTypeConstant.INTEGER));
		registerFunction("minute", new StandardSQLFunction("minute",
				DataTypeConstant.INTEGER));
		registerFunction("second", new StandardSQLFunction("second",
				DataTypeConstant.INTEGER));
		registerFunction("dayname", new StandardSQLFunction("dayname",
				DataTypeConstant.STRING));
		registerFunction("monthname", new StandardSQLFunction("monthname",
				DataTypeConstant.STRING));

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
		registerFunction("cot", new StandardSQLFunction("cot",
				DataTypeConstant.DOUBLE));
		registerFunction("exp", new StandardSQLFunction("exp",
				DataTypeConstant.DOUBLE));
		registerFunction("log", new StandardSQLFunction("log",
				DataTypeConstant.DOUBLE));
		registerFunction("log10", new StandardSQLFunction("log10",
				DataTypeConstant.DOUBLE));
		registerFunction("sin", new StandardSQLFunction("sin",
				DataTypeConstant.DOUBLE));
		registerFunction("sqrt", new StandardSQLFunction("sqrt",
				DataTypeConstant.DOUBLE));
		registerFunction("tan", new StandardSQLFunction("tan",
				DataTypeConstant.DOUBLE));
		registerFunction("pi", new NoArgSQLFunction("pi",
				DataTypeConstant.DOUBLE));
		registerFunction("rand", new StandardSQLFunction("rand",
				DataTypeConstant.FLOAT));

		registerFunction("radians", new StandardSQLFunction("radians",
				DataTypeConstant.DOUBLE));
		registerFunction("degrees", new StandardSQLFunction("degrees",
				DataTypeConstant.DOUBLE));
		registerFunction("roundmagic", new StandardSQLFunction("roundmagic"));

		registerFunction("ceiling", new StandardSQLFunction("ceiling"));
		registerFunction("floor", new StandardSQLFunction("floor"));

		// Multi-param dialect functions...
		registerFunction("mod", new StandardSQLFunction("mod",
				DataTypeConstant.INTEGER));

		// function templates
		registerFunction("concat", new VarArgsSQLFunction(
				DataTypeConstant.STRING, "(", "||", ")"));
	}

	public String getSqlString(AnlysisStatementModel model) {

		return null;
	}
}
