$(function() {
	$('#menu').tree({
		url: '/menu/getMenus',
		method: 'get',
		lines: true,
		onClick: function(node) {
			var tab = $('#tt').tabs('getTab', node.text);
			if (tab) {
				
			} else {
				$('#tt').tabs('add', {
					title: node.text,
					selected: true,
					href: '/menu/getMenus'
				});
			}
		}
	});
	
	function verify(v, r, i) {
		return !v ? '是' : '否';
	}
	
	$('#dg').datagrid({
	    url:'/data/owner.json',
	    method: 'get',
	    columns: [[    
	    	{field:'id', title:'序号', width:100},
	        {field:'qq', title:'QQ', width:100},
	        {field:'buildingNo', title:'栋', width:100},
	        {field:'unit', title:'单元', width:100},
	        {field:'floor', title:'楼', width:100},
	        {field:'houseNo', title:'房号', width:100},
	        {field:'verify', title:'验证', width:100, formatter: verify}
	    ]]
	}); 
});