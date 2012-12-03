package galaxy.sqlanlysis.core.dialect;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;
import galaxy.sqlanlysis.core.dialect.layout.AnylsisSqlSorter;
import galaxy.sqlanlysis.core.dialect.layout.DefaultSqlLayoutAdapter;
import galaxy.sqlanlysis.core.dialect.layout.IAdapter;
import galaxy.sqlanlysis.core.dialect.layout.IAnlysisSqlLayout;
import galaxy.sqlanlysis.core.exception.AnlysisSqlException;
import galaxy.sqlanlysis.core.exception.IllegalModelTypeException;
import galaxy.sqlanlysis.core.function.SQLFunction;
import galaxy.sqlanlysis.core.model.IAnlysisSqlModel;
import galaxy.sqlanlysis.core.model.TableModel;
import galaxy.sqlanlysis.core.model.column.ColumnModel;
import galaxy.sqlanlysis.core.model.column.ColumnModelGroup;
import galaxy.sqlanlysis.core.model.column.FunctionModel;
import galaxy.sqlanlysis.core.model.condition.ConditionModelGroup;
import galaxy.sqlanlysis.core.model.value.ValueGroup;
import galaxy.sqlanlysis.core.model.value.ValueModel;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SQL����ģ�ͣ��ṩ��Ӧ���ݿ�Ĺؼ��ֺ�SQL��䲼�֣�ʵ��������Բ�ͬ���ݿ��SQLģ�Ͱ�װ��
 * 
 * @author caiyu
 * @date 2012-11-9
 */
public abstract class Dialect {

	public final static String WHERE = "WHERE";
	public final static String VALUES = "VALUES";
	public final static String FROM = "FROM";
	public final static String GROUP_BY = "GROUP BY";
	public final static String ORDER_BY = "ORDER BY";

	public final static String DISTINCT = "DISTINCT";

	// private final TypeNames typeNames = new TypeNames();
	private final DefaultSqlLayoutAdapter defaultSqlLayout = new DefaultSqlLayoutAdapter();
	protected IAdapter sqlLayoutAdapter;
	protected IAnlysisSqlModel model;
	protected IAnlysisSqlLayout sqlLayout;

	private Map<String, SQLFunction> sqlFunctions = new ConcurrentHashMap<String, SQLFunction>();
	private Map<Expression, String> sqlExpressions = new ConcurrentHashMap<Expression, String>();

	protected void registerFunction(String name, SQLFunction function) {
		sqlFunctions.put(name, function);
	}

	protected void registerExpression(Expression exp, String dialectExp) {
		sqlExpressions.put(exp, dialectExp);
	}

	/**
	 * Ϊ����ע�ᵱǰ������Sqlģ��
	 * 
	 * @param model
	 */
	public void registerAnlysisSqlModel(IAnlysisSqlModel model) {
		this.model = model;
		sqlLayout = getLayout(model.getClass());
	}

	/**
	 * ��ע�ᵱǰ������Sqlģ��
	 * 
	 * @param model
	 */
	public void unregisterAnlysisSqlModel() {
		this.model = null;
		this.sqlLayout = null;
	}

	/**
	 * ע�᲼��������
	 * 
	 * @param adapter
	 */
	protected void registerLayoutAdapter(IAdapter adapter) {
		sqlLayoutAdapter = adapter;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public IAnlysisSqlLayout getLayout(Class<?> modelClazz) {
		IAdapter adapter;
		if (sqlLayoutAdapter == null)
			adapter = defaultSqlLayout;
		else
			adapter = sqlLayoutAdapter;
		return (IAnlysisSqlLayout) adapter.getAdapter(modelClazz);
	}

	/**
	 * ����SQL���ģ������ȡ���Զ�Ӧ��SQL���
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
			String value = getValue(key);
			if (value == null)
				continue;
			sb.append(value);
		}
		return sb.getSql();
	}

	protected String getValue(int key) {
		String value = null;
		switch (key) {
		case AnlysisSqlKeys.HEAD:
			value = analyzeHeadSql();
			break;
		case AnlysisSqlKeys.DISTINCT:
			value = DISTINCT;
			break;
		case AnlysisSqlKeys.COLUMNS:
			ColumnModelGroup columns = (ColumnModelGroup) getStatementElement(AnlysisSqlKeys.COLUMNS);
			value = analyzeColumnsSql(columns);
			break;
		case AnlysisSqlKeys.TARGET_TABLE:
			value = analyzeTargetTableSql();
			break;
		case AnlysisSqlKeys.VALUES:
			ValueGroup values = (ValueGroup) getStatementElement(AnlysisSqlKeys.VALUES);
			value = analyzeValuesSql(values);
			break;
		case AnlysisSqlKeys.LIMIT:
			// TODO
			break;
		case AnlysisSqlKeys.WHERE:
			// TODO
			ConditionModelGroup conditions = (ConditionModelGroup) getStatementElement(AnlysisSqlKeys.WHERE);
			value = analyzeConditionsSql(conditions);
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
	 * �����������
	 * 
	 * @param conditions
	 * @return
	 */
	protected String analyzeConditionsSql(ConditionModelGroup conditions) {
		// TODO Auto-generated method stub
		if (conditions == null)
			return null;
		return null;
	}

	private String analyzeHeadSql() {
		return sqlLayout.render(null, AnlysisSqlKeys.HEAD);
	}

	/**
	 * ����ֵ�б�SQL
	 * 
	 * @param valueGroup
	 * @return
	 */
	private String analyzeValuesSql(ValueGroup values) {
		ColumnModelGroup columns = (ColumnModelGroup) getStatementElement(AnlysisSqlKeys.COLUMNS);
		if (columns != null && columns.getSize() != 0
				&& columns.getSize() != values.getSize()) {
			throw new AnlysisSqlException("�ֶ��б���[" + columns.getSize()
					+ "]��ֵ�б�[" + values.getSize() + "]���Ȳ�һ��");
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
	 * ����Ŀ���
	 * 
	 * @return
	 */
	protected String analyzeTargetTableSql() {
		TableModel tableModel = (TableModel) getStatementElement(AnlysisSqlKeys.TARGET_TABLE);
		if (tableModel == null)
			throw new IllegalModelTypeException();
		List<TableModel> args = new ArrayList<TableModel>();
		args.add(tableModel);
		return sqlLayout.render(args, AnlysisSqlKeys.TARGET_TABLE);
	}

	/**
	 * �����ֶ��б�
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
	 * ���������ֶ�
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

	public String getAsToken() {
		return " AS ";
	}

	public SQLFunction getSQLFunction(String name) {
		return sqlFunctions.get(name);
	}

	public String getSQLExpression(Expression exp) {
		return sqlExpressions.get(exp);
	}

	protected Object getStatementElement(int key) {
		return model.getElement(key);
	}

	public String render(List<? extends Object> args, int key) {
		if (sqlLayout == null) {
			throw new IllegalModelTypeException("����δע��ģ��");
		}
		return sqlLayout.render(args, key);
	}
}
