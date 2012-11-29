package galaxy.sqlanlysis.core.dialect.layout;

import galaxy.sqlanlysis.core.model.DeleteModel;
import galaxy.sqlanlysis.core.model.InsertModel;
import galaxy.sqlanlysis.core.model.SelectModel;
import galaxy.sqlanlysis.core.model.UpdateModel;

/**
 * Ĭ�ϵ�SQL����
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public class DefaultSqlLayoutAdapter implements IAdapter {
	protected IAnlysisSqlLayout deleteLayout = new DefaultDeleteLayout();
	protected IAnlysisSqlLayout selectLayout = new DefaultSelectLayout();
	protected IAnlysisSqlLayout insertLayout = new DefaultInsertLayout();
	protected IAnlysisSqlLayout updateLayout = new DefaultUpdateLayout();

	private class DefaultDeleteLayout extends AbstractDeleteLayout {

		@Override
		public AnylsisSqlSorter getSorter() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	private class DefaultSelectLayout extends AbstractSelectLayout {

		@Override
		public AnylsisSqlSorter getSorter() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	private class DefaultInsertLayout extends AbstractInsertLayout {
	}

	private class DefaultUpdateLayout extends AbstractUpdateLayout {

		@Override
		public AnylsisSqlSorter getSorter() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public IAnlysisSqlLayout getAdapter(Object obj) {
		if (obj instanceof Class<?>) {
			Class<?> clazz = (Class<?>) obj;
			if (clazz == SelectModel.class) {
				return selectLayout;
			} else if (clazz == DeleteModel.class) {
				return deleteLayout;
			} else if (clazz == InsertModel.class) {
				return insertLayout;
			} else if (clazz == UpdateModel.class) {
				return updateLayout;
			}
		}
		return null;
	}
}
