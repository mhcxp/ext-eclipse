/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.auth.role;


/**
 * @author dzh
 * @date Nov 15, 2011 11:16:40 AM х╗оч
 */
public interface IPermission {

	String getID();

	String getName();

	void setName(String name);

	String getDescription();

	void setDesciption(String description);

	void setCategory(IPCategory cate);

	IPCategory getCategory();

	IPermission clone();
}
