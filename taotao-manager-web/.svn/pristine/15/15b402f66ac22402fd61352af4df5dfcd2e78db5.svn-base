<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<a class="easyui-linkbutton" onclick="importAll()">同步所有索引</a>
</div>
<script type="text/javascript">
	function importAll(){
		$.post("/index/import",null,function(){
			$.messager.alert('提示','商品数据导入完成!');
		});
	}
</script>
