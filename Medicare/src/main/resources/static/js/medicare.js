function callAjax(url, container, callType) {

	$.ajax({
		url : url,
		type : callType,
		crossDomain : true,
		timeout : 50000,
		success : function(response) {
			$('.' + container).html(response);
		},
		error : function(xhr, status, error) {

		}
	});
}

