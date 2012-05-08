checkform = function() {
	var pass = Ext.get("pass");
	var pass_display=Ext.get("pass_display");
	var name = Ext.get("name");
	var message = Ext.get("message");
	if (name.getValue().trim() == ""||name.getValue()==null) {
		// showMessage("ÇëÌîÐ´ÓÃ»§Ãû£¡");
		message.dom.innerHTML = "ÇëÌîÐ´ÓÃ»§Ãû£¡";
		return false;
	}
	if (pass_display.getValue().trim() == ""||pass_display==null) {
		// showMessage("ÇëÌîÐ´ÃÜÂë£¡");
		message.dom.innerHTML = "ÇëÌîÐ´ÃÜÂë£¡";
		return false;
	}

//	pass.dom.value = hex_md5(pass_display.getValue());
	pass.dom.value = pass_display.getValue();
	return true;
};

showMessage = function(str) {
	Ext.MessageBox.alert("ÌáÊ¾", str);
}

Ext.onReady(function() {
			var name = Ext.get("name");
			var message = Ext.get("message");
			var pass_display = Ext.get("pass_display");
			name.on('keydown', function(e) {
						message.dom.innerHTML = "&nbsp;";
					});
			pass_display.on('keydown', function(e) {
						message.dom.innerHTML = "&nbsp;";
					});
		});