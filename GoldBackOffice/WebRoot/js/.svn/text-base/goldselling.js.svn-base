function jmesa_a_link(id) {
	$("a.deleteLinkClass").click(function(event) {
		event.preventDefault();
		if (confirm("确定删除？")) {
			$.get($(this).attr('href'), function(data) {
				onInvokeAction(id);
			})
		}
	});

	$("a.editLinkClass").click(function(event) {
		event.preventDefault();
		$.get($(this).attr('href'), function(data) {
			$("#winEditRole").html(data);
			$("#winEditRole").window('open');
		});

	});

}

function iefocus(element) {
	if ($.browser.msie) {
		$(element).click(function() {
			this.blur();
			this.focus();
		});
	}
}

$.fn.datebox.defaults.formatter = function(date) {// 定义 “yyyy－MM－dd” easyui
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
};
$.fn.datebox.defaults.parser = function(s) {
	if ($.browser.msie) {// ie下不能正常使用
		return new Date();
	}
	if (s) {
		var d = new Date(s);
		return d;
	} else {
		return new Date();
	}

};