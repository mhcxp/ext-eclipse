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
package ext.eclipse.rcp.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * @version 1.0 2012-5-3 下午4:01:43
 */
public class ExtCommonActionProvider extends CommonActionProvider {

	protected List<IAction> updatableActions = new ArrayList<IAction>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator
	 * .ICommonActionExtensionSite)
	 */
	@Override
	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);
		buildActions();
	}

	/**
	 * 在其中添加动作
	 */
	protected void buildActions() {
	}

	public void fillContextMenu(IMenuManager menu) {
	}

	public void fillActionBars(IActionBars actionBars) {
	}

	public void updateActionBars() {
		for (int i = 0; i < updatableActions.size(); i++) {
			IAction action = updatableActions.get(i);
			ISelection selection = getContext().getSelection();
			if (action instanceof ISelectionChangedListener) {
				((ISelectionChangedListener) action)
						.selectionChanged(new SelectionChangedEvent(
								getSelectionSource(), selection));
			} else if (action instanceof ISelectionListener) {
				((ISelectionListener) action).selectionChanged(getPart(),
						selection);
			} else if (action instanceof SelectionListenerAction) {
				((SelectionListenerAction) action)
						.selectionChanged((IStructuredSelection) selection);
			}
		}
	}

	protected ISelectionProvider getSelectionSource() {
		return getActionSite().getStructuredViewer();
	}

	protected IWorkbenchPart getPart() {
		ICommonViewerSite site = getActionSite().getViewSite();
		if (site instanceof ICommonViewerWorkbenchSite) {
			return ((ICommonViewerWorkbenchSite) site).getPart();
		}
		return null;
	}

	public void dispose() {
		super.dispose();
		updatableActions.clear();
		updatableActions = null;
	}

}
