package galaxy.sqlanlysis.core.dialect;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;
import galaxy.sqlanlysis.core.dialect.layout.AnylsisSqlSorter;
import galaxy.sqlanlysis.core.dialect.layout.DefaultSqlLayoutAdapter;
import galaxy.sqlanlysis.core.dialect.layout.IAdapter;
import galaxy.sqlanlysis.core.dialect.layout.IAnlysisSqlLayout;
import galaxy.sqlanlysis.core.dialect.layout.IDeleteLayout;
import galaxy.sqlanlysis.core.dialect.layout.IInsertLayout;
import galaxy.sqlanlysis.core.dialect.layout.ISelectLayout;
import galaxy.sqlanlysis.core.dialect.layout.IUpdateLayout;
import galaxy.sqlanlysis.core.exception.AnlysisSqlException;
import galaxy.sqlanlysis.core.exception.IllegalModelTypeException;
import galaxy.sqlanlysis.core.function.SQLFunction;
import galaxy.sqlanlysis.core.model.AliasModel;
import galaxy.sqlanlysis.core.model.ColumnModel;
import galaxy.sqlanlysis.core.model.ColumnModelGroup;
import galaxy.sqlanlysis.core.model.DeleteModel;
import galaxy.sqlanlysis.core.model.FunctionModel;
import galaxy.sqlanlysis.core.model.IAnlysisSqlModel;
import galaxy.sqlanlysis.core.model.InsertModel;
import galaxy.sqlanlysis.core.model.SelectModel;
import galaxy.sqlanlysis.core.model.TableModel;
import galaxy.sqlanlysis.core.model.UpdateModel;
import galaxy.sqlanlysis.core.model.ValueGroup;
import galaxy.sqlanlysis.core.model.ValueModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SQL方言模型，提供对应数据库的关键字和SQL语句布局，实质上是针对不同数据库的SQL模型包装类
 * 
 * @author caiyu
 * @date 2012-11-9
 */
public abstract class Dialect {

	public final static String INSERT_HEAD = "INSERT INTO";
	public final static String SELECT_HEAD = "SELECT";
	public final static String DELETE_HEAD = "DELETE";
	public final static String UPDATE_HEAD = "UPDATE";

	public final static String WHERE = "WHERE";
	public final static String VALUES = "VALUES";
	public final static String FROM = "FROM";
	public final static String GROUP_BY = "GROUP BY";
	public final static String ORDER_BY = "ORDER BY";

	// private final TypeNames typeNames = new TypeNames();
	private final DefaultSqlLayoutAdapter defaultSqlLayout = new DefaultSqlLayoutAdapter();
	protected IAdapter sqlLayoutAdapter;
	protected IAnlysisSqlModel model;
	protected IAnlysisSqlLayout sqlLayout;

	private Map<String, SQLFunction> sqlFunctions = new ConcurrentHashMap<String, SQLFunction>();

	protected void registerFunction(String name, SQLFunction function) {
		sqlFunctions.put(name, function);
	}

	/**
	 * 为方言注册当前分析的Sql模型
	 * 
	 * @param model
	 */
	public void registerAnlysisSqlModel(IAnlysisSqlModel model) {
		this.model = model;
		sqlLayout = getLayout(model.getClass());
	}

	/**
	 * 反注册当前分析的Sql模型
	 * 
	 * @param model
	 */
	public void unregisterAnlysisSqlModel() {
		this.model = null;
	}

	/**
	 * 注册布局适配器
	 * 
	 * @param adapter
	 */
	protected void registerLayoutAdapter(IAdapter adapter) {
		sqlLayoutAdapter = adapter;
	}

	/**
	 * 获取布局
	 * 
	 * @return
	 */
	public IAnlysisSqlLayout getLayout(Class<?> modelClazz) {
		IAdapter adapter;
		if (sqlLayoutAdapter == null)
			adapter = defaultSqlLayout;
		else
			adapter = sqlLayoutAdapter;
		Class<?> clazz = null;
		if (modelClazz == SelectModel.class) {
			clazz = ISelectLayout.class;
		} else if (modelClazz == InsertModel.class) {
			clazz = IInsertLayout.class;
		} else if (modelClazz == UpdateModel.class) {
			clazz = IUpdateLayout.class;
		} else if (modelClazz == DeleteModel.class) {
			clazz = IDeleteLayout.class;
		}
		return (IAnlysisSqlLayout) adapter.getAdapter(clazz);
	}

	/**
	 * 根据SQL语句模型来获取方言对应的SQL语句
	 * 
	 * @param model
	 * @return
	 */
	public String getSqlString() {
		if (model == null) {
			throw new AnlysisSqlException();
		}
		AnylsisSqlSorter sorter = sqlLayout.getSorter();
		SqlBuffer sb = new SqlBuffer();
		for (int key : sorter.getSort()) {
			sb.append(getValue(key));
		}
		return sb.getSql();
	}

	protected String getValue(int key) {
		String value = null;
		switch (key) {
		case AnlysisSqlKeys.HEAD:
		case AnlysisSqlKeys.DISTINCT:
			value = String.valueOf(getElementSql(key));
			break;
		case AnlysisSqlKeys.COLUMNS:
			ColumnModelGroup columns = (ColumnModelGroup) model
					.getElement(AnlysisSqlKeys.COLUMNS);
			value = analyzeColumnsSql(columns);
			break;
		case AnlysisSqlKeys.TARGET_TABLE:
			value = analyzeTargetTableSql();
			break;
		case AnlysisSqlKeys.VALUES:
			ValueGroup values = (ValueGroup) model
					.getElement(AnlysisSqlKeys.VALUES);
			value = analyzeValuesSql(values);
			break;
		case AnlysisSqlKeys.LIMIT:
			// TODO
			break;
		case AnlysisSqlKeys.WHERE:
			// TODO
			
			break;
		case AnlysisSqlKeys.GROUP_BY:
			// TODO
			break;
		case AnlysisSqlKeys.ORDER_BY:
			// TODO
			break;
			
		default:
			break;
		}
		return value;
	}

	/**
	 * 分析值列表SQL
	 * 
	 * @param valueGroup
	 * @return
	 */
	private String analyzeValuesSql(ValueGroup values) {
		// TODO 判比对字段列表长度和值列长度
		ColumnModelGroup columns = (ColumnModelGroup) model
				.getElement(AnlysisSqlKeys.COLUMNS);
		if (columns != null && columns.getSize() != 0
				&& columns.getSize() != values.getSize()) {
			throw new IllegalModelTypeException("字段列表长度[" + columns.getSize()
					+ "]和值列表[" + values.getSize() + "]长度不一致");
		}
		int size = values.getSize();
		SqlBuffer buffer = new SqlBuffer();
		for (int i = 0; i < size; i++) {
			ValueModel value = values.getValue(i);
			String content = value.getName();
			if (i == size - 1)
				buffer.append(content);
			else
				buffer.appendWithComma(content);
		}
		return buffer.getSql();
	}

	/**
	 * 解析目标表
	 * 
	 * @return
	 */
	protected String analyzeTargetTableSql() {
		TableModel tableModel = (TableModel) model
				.getElement(AnlysisSqlKeys.TARGET_TABLE);
		if (tableModel == null)
			throw new IllegalModelTypeException();
		String name = tableModel.getName();
		AliasModel alias = tableModel.getAlias();
		SqlBuffer buffer = new SqlBuffer();
		buffer.append(name);
		if (alias != null)
			buffer.append(alias.toString());
		return buffer.getSql();
	}

	/**
	 * 解析字段列表
	 * 
	 * @return
	 */
	protected String analyzeColumnsSql(ColumnModelGroup columns) {
		if (columns == null)
			return null;
		List<String> columnList = new ArrayList<String>();
		if (!columns.isEmpty()) {
			int size = columns.getSize();
			for (int i = 0; i < size; i++) {
				ColumnModel column = columns.getChild(i);
				String columnSql = analyzeColumnSql(column);
				columnList.add(columnSql);
			}
		}
		return sqlLayout.render(columnList, AnlysisSqlKeys.COLUMNS);
	}

	/**
	 * 解析单个字段
	 * 
	 * @param column
	 * @return
	 */
	protected String analyzeColumnSql(ColumnModel column) {
		if (column instanceof FunctionModel) {
			String name = ((FunctionModel) column).getName();
			SQLFunction sqlFunction = getSQLFunction(name);
			List<Object> args = ((FunctionModel) column).getArguments();
			return sqlFunction.render(args);
		}
		return column.hasAlias() ? column.getName() + getAsToken()
				+ column.getAlias() : column.getName();
	}

	protected String getAsToken() {
		return " AS ";
	}

	public SQLFunction getSQLFunction(String name) {
		return sqlFunctions.get(name);
	}

	protected Object getElementSql(int key) {
		return model.getElement(key);
	}
}
