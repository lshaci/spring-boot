$(function() {
	$("#img").off("click").on("click", function() {
		$("#file").click();
	});
	
	$("#file").off("change").on("change", function() {
		var imgUrl = getObjectURL(this.files[0]);
		$("#img").attr("src", imgUrl);
	});
	
	$("#submit_bt").off("click").on("click", function() {
		
	});
	
	function getObjectURL(file) {
		console.debug(file);
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}
	
});