<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/include.jsp"%>
<html>
<head>
<title>index</title>
</head>
<body>
	<div style="padding:5px;">
		<input style="width: 500px;" class="easyui-textbox" id="productUrl" name="url" value="http://product.yesky.com/electronic/list3.html" /> 
		<a style="margin-left: 50px;" id="product" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-magnifier_zoom_in'">Crawler Product</a>
		<a style="margin-left: 50px;" id="getProductContext" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-magnifier_zoom_in'">Show Crawler Product</a>
	</div>
	<div style="padding:10px;" id="productDiv">
	
	</div>
	
	<script type="text/javascript">
		$(function() {
			$('#product').click(function() {
				popupProgressbar('message','loading..',1000);
				$.post('${ctx}/product',{url:$("#productUrl").val(),t:new Date().getTime()}, function(r) {
					if(r.flag){
							closeProgressbar();
					}
				}, 'json');
			});
			
			$('#getProductContext').click(function() {
				popupProgressbar('message','loading..',1000);
				$.post('${ctx}/getProductContext',{t:new Date().getTime()}, function(r) {
					if(r.flag){
							closeProgressbar();
							$('#productDiv').html(r.content);
					}
				}, 'json');
			});
			
			
		})
	</script>
</body>
</html>
