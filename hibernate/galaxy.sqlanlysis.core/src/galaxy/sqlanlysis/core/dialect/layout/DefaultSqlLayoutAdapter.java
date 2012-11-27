package galaxy.sqlanlysis.core.dialect.layout;

/**
 * 默认的SQL布局
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
			if (clazz == ISelectLayout.class) {
				return selectLayout;
			} else if (clazz == IDeleteLayout.class) {
				return deleteLayout;
			} else if (clazz == IInsertLayout.class) {
				return insertLayout;
			} else if (clazz == IUpdateLayout.class) {
				return updateLayout;
			}
		}
		return null;
	}
}
