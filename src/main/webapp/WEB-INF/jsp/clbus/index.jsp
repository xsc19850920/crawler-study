<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/include.jsp"%>
<html>
<head>
<title>index</title>
</head>
<body>
	<div style="padding:5px;">
		<input style="width: 500px;" class="easyui-textbox" id="clbusUrl" name="url" value="http://www.clbus.com/busiInfo/getBusiInfoByColumnType/1?pageSize=5&pageNum=2" /> 
		<a style="margin-left: 50px;" id="clbus" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-magnifier_zoom_in'">Crawler Clbus</a>
		<a style="margin-left: 50px;" id="getClbusContext" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-magnifier_zoom_in'">Show Crawler Clbus</a>
	</div>
	
	<div style="padding:10px;" id="clbustDiv">
	
	</div>
	<script type="text/javascript">
		$(function() {
			$('#clbus').click(function() {
				popupProgressbar('message','loading..',1000);
				$.post('${ctx}/clbus',{url:$("#clbusUrl").val(),t:new Date().getTime()}, function(r) {
					if(r.flag){
							closeProgressbar();
					}
				}, 'json');
			});
			
			$('#getClbusContext').click(function() {
				popupProgressbar('message','loading..',1000);
				$.post('${ctx}/getClbusContext',{t:new Date().getTime()}, function(r) {
					if(r.flag){
							closeProgressbar();
							$('#clbustDiv').html(r.content);
					}
				}, 'json');
			});
		})
	</script>
</body>
</html>
