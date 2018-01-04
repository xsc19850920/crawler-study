<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/include.jsp"%>
<html>
<head>
<title>index</title>
<script type="text/javascript" src="${ctx }/resources/js/common/basic.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tree").tree({
			url : '${ctx}/resources/js/common/tree_data.json',
			method : 'get',
			onClick : function(node) {
				if (node.attributes.folder == '1') {
					return;
				}
				var url;
				if (node.attributes.url) {
					url = node.attributes.url;
				} else {
					url = '404.jsp';
				}
				layout_center_addTabFun({
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					href : url
				});
			}
		});
	})
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:'false'" style="height: 70px; background: rgb(1, 104, 170); padding: 10px; overflow: hidden">
		<h1>
			<font style="color: white; font-style: italic; font-size: 28px; padding: 20 20px;">GENPACT</font> <font style="color: white; font-style: italic; font-size: 20px;"> -- Crawler</font>
		</h1>
	</div>
	<div data-options="region:'west', width:'300',title:'Menu',split:true">
		<div class="easyui-tree" data-options="lines:'true'" id="tree" style="padding: 10px"></div>
	</div>
	<div data-options="region:'center',title:'Context'">
		<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true">
			<div title="Welcome" data-options="iconCls:'icon-tip',closable:'true'" >
				<div>
					<pre style="font-size: 16px;">
	Crawler starts with an entrance URL to visit as a seed. As the crawler visit the Web Site, 
	it identifies all the hyperlinks in the page and adds them to the list of URLs to visit. 
	URLs from the frontier are recursively visited according to a set of policies. 
	If the crawler is performing archiving of websites it copies and saves the information as it goes. 
    The large volume implies the crawler can only download a limited number of the Web pages within a given time, 
    so it needs to prioritize its downloads. Given that the bandwidth for conducting crawls is neither infinite nor free, 
    it is becoming essential to crawl the Web in not only a scalable, but efficient way, 
    if some reasonable measure of quality or freshness is to be maintained.
					</pre>	
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:'false'" style="height: 35px; background: rgb(1, 104, 170); padding: 8px; overflow: hidden;">
		<div style="float: right; padding: 0px 20px; color: white;">
			Copyright © Genpact 2015. All Rights Reserved. <a class="privacy-link" href="http://www.genpact.com/home/privacy" style="color: #72ACE3;">Privacy</a> | <a class="terms-and-conditions-link" href="http://www.genpact.com/home/terms-conditions" style="color: #72ACE3;">Terms &amp; Conditions</a>
		</div>
	</div>
</body>
</html>
