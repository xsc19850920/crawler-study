// 全局函数

// 弹出progressBar
// 
function popupProgressbar(titleStr, msgStr, intervalStr) {
	var win = $.messager.progress({
		title : titleStr,
		msg : msgStr,
		interval : intervalStr
	// 设置时间间隔较长
	});

}
// 关闭progressBar
function closeProgressbar() {
	$.messager.progress('close');
}


function layout_center_addTabFun(opts) {
	var t = $('#layout_center_tabs');
	if (t.tabs('exists', opts.title)) {
		t.tabs('select', opts.title);
	} else {
		t.tabs('add', opts);
	}
}
