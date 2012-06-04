/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package ext.eclipse.ide.auth.role;


/**
 * @author dzh
 * @date Nov 15, 2011 11:16:40 AM Ȩ��
 */
public interface IPermission {

	String getId();

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	void setCategory(IPCategory cate);

	IPCategory getCategory();

	IPermission clone();
}
