/**
 * Copyright 2012 Ext-Eclipse Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ext.eclipse.rcp.app.intro;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class ExtPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		String editorArea = layout.getEditorArea();

		IFolderLayout f_wn = layout.createFolder("west-north",
				IPageLayout.LEFT, 0.25f, editorArea);
		f_wn.addView("ext.eclipse.rcp.navigator");

//		IFolderLayout f_bottom = layout.createFolder("bottom",
//				IPageLayout.BOTTOM, 0.6f, "west-north");
//		f_bottom.addView(IPageLayout.ID_PROP_SHEET);
	}
}
