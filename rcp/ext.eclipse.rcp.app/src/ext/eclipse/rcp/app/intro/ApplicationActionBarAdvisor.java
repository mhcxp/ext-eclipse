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

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;

@SuppressWarnings("restriction")
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	// private IWorkbenchAction introAction;

	private final IWorkbenchWindow window;

	private IAction copyAction;

	private IAction pasteAction;

	private IAction selectAllAction;

	private IAction closeAction;

	private IAction closeAllAction;

	private IAction importAction;

	private IAction exportAction;

	private IAction undoAction;

	private IAction redoAction;

	private IWorkbenchAction lockUnlockAction;

	private IWorkbenchAction editActionSetsAction;

	private IWorkbenchAction preferenceAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		window = configurer.getWindowConfigurer().getWindow();
	}

	protected void makeActions(IWorkbenchWindow window) {
		// introAction = ActionFactory.INTRO.create(window);
		// register(introAction);


		copyAction = ActionFactory.COPY.create(window);
		register(copyAction);

		pasteAction = ActionFactory.PASTE.create(window);
		register(pasteAction);

		selectAllAction = ActionFactory.SELECT_ALL.create(window);
		register(selectAllAction);

		lockUnlockAction = ActionFactory.LOCK_TOOL_BAR.create(window);
		register(lockUnlockAction);

		editActionSetsAction = ActionFactory.EDIT_ACTION_SETS.create(window);
		register(editActionSetsAction);

		closeAction = ActionFactory.CLOSE.create(window);
		register(closeAction);

		closeAllAction = ActionFactory.CLOSE_ALL.create(window);
		register(closeAllAction);

		importAction = ActionFactory.IMPORT.create(window);
		register(importAction);

		exportAction = ActionFactory.EXPORT.create(window);
		register(exportAction);

		undoAction = ActionFactory.UNDO.create(window);
		register(undoAction);

		redoAction = ActionFactory.REDO.create(window);
		register(redoAction);

		preferenceAction = ActionFactory.PREFERENCES.create(window);
		register(preferenceAction);

	
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		menuBar.add(createFileMenu());
		menuBar.add(createEditMenu());
		menuBar.add(createProjectMenu());
		menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuBar.add(createWindowMenu());
		menuBar.add(createHelpMenu());
	}

	protected IMenuManager createFileMenu() {
		IMenuManager menu = new MenuManager(
				IDEWorkbenchMessages.Workbench_file,
				IWorkbenchActionConstants.M_FILE);

		menu.add(new Separator());
		menu.add(closeAction);
		menu.add(closeAllAction);
		menu.add(new Separator());
		menu.add(importAction);
		menu.add(exportAction);
		menu.add(new Separator());
		return menu;
	}

	protected IMenuManager createEditMenu() {
		IMenuManager menu = new MenuManager(
				IDEWorkbenchMessages.Workbench_edit,
				IWorkbenchActionConstants.M_EDIT);

		menu.add(undoAction);
		menu.add(redoAction);
		menu.add(new Separator());
		menu.add(copyAction);
		menu.add(pasteAction);
		menu.add(selectAllAction);
		menu.add(new Separator());

		return menu;
	}

	private MenuManager createProjectMenu() {
		MenuManager menu = new MenuManager(
				IDEWorkbenchMessages.Workbench_project,
				IWorkbenchActionConstants.M_PROJECT);
		return menu;
	}

	private MenuManager createWindowMenu() {
		MenuManager menu = new MenuManager(
				IDEWorkbenchMessages.Workbench_window,
				IWorkbenchActionConstants.M_WINDOW);
		menu.add(preferenceAction);

		return menu;
	}

	private IMenuManager createHelpMenu() {
		MenuManager menu = new MenuManager(IDEWorkbenchMessages.Workbench_help,
				IWorkbenchActionConstants.M_HELP);
		return menu;
	}

	protected void fillCoolBar(ICoolBarManager coolBar) {
		coolBar.add(createEditBar(window, coolBar.getStyle()));

		IMenuManager menuManager = new MenuManager();
		coolBar.setContextMenuManager(menuManager);
		menuManager.add(lockUnlockAction);
		menuManager.add(editActionSetsAction);
	}

	private IToolBarManager createEditBar(IWorkbenchWindow window, int style) {
		IToolBarManager manager = new ToolBarManager(style);
		manager.add(copyAction);
		manager.add(pasteAction);
		return manager;

	}

}
