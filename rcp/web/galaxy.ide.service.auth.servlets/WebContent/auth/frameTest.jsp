var fm = Ext.form;
   	//新增或编辑的Form
	formPanelinsert = new Ext.FormPanel({
		id : 'form-panel',
		header : false,
		border : false,
		autoScroll : true,
		frame : true,
		iconCls : 'icon-detail-form',
		labelAlign : 'left',
		items : [new Ext.form.FieldSet({
					title : '技改项目管理信息',
					border : true,
					layout : 'form',
					anchor : '97%',
					items : [ {
								layout : 'form',
								columnWidth : .33,
								bodyStyle : 'border: 0px;',
								items : [new fm.ComboBox(fc['sjType'])]
							},{
								layout : 'form',
								columnWidth : 1,
								bodyStyle : 'border: 0px;',
								items : [new fm.TextField(fc['title'])]
							},{
								layout : 'form',
								columnWidth : 1,
								bodyStyle : 'border: 0px;',
								items : [new fm.TextArea(fc['memo'])]							
							}, {
								layout : 'column',
								bodyStyle : 'border: 0px;',
								hidden : true,
								items : [{
									layout : 'form',
									width : 0,
									bodyStyle : 'border: 0px;',
									items : [new fm.TextField(fc['uids']),
									new fm.TextField(fc['unitId']),
									new fm.TextField(fc['userId']),
									new fm.TextField(fc['billState']),
									new fm.TextField(fc['state']),
									new fm.TextField(fc['state1']),
									new fm.TextField(fc['pid']),									
									new fm.TextField(fc['reportType']),
									new fm.DateField(fc.createDate),
									new fm.DateField(fc.returnDate),
									new fm.DateField(fc.reportType)									
									]
								}]
							}]
				})],
		buttons : [{
					id : 'save',
					text : '保存',
					handler : formSave
				}, {
					id : 'cancel',
					text : '取消',
					handler : function() {
						ifLoadGrid = true
						formWindow.hide();
					}
				}]
	});