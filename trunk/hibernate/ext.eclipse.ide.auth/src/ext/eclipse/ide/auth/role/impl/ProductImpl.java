package ext.eclipse.ide.auth.role.impl;

import ext.eclipse.ide.auth.role.IProduct;

public class ProductImpl implements IProduct {

	private String id;

	@SuppressWarnings("unused")
	private void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
