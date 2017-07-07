<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${ctx}/staticfile/components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/staticfile/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/staticfile/components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
<SCRIPT type="text/javascript">

	
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[{id:1,pId:4,name:"灵巧披风",checked:true},{id:2,pId:4,name:"十字镐"},{id:3,pId:4,name:"暴风大剑"},{id:4,name:"无尽之刃"}]
		
		$(document).ready(function(){
			$.fn.zTree.init($("#htZtree"), setting, zNodes);

			var zTreeObj = $.fn.zTree.getZTreeObj("htZtree");
			zTreeObj.expandAll(true);		//展开所有树节点
		});
		
		
		
	</SCRIPT>

</head>

<body>
<h1>Ztree入门教例</h1>
<div style="padding:30px;">
		<ul id="htZtree" class="ztree"></ul>
</div>
 
</div>
 
 
</form>
</body>
</html>

