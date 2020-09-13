function consultarLibro() {
	var textSearch = $("#textSearch").val();
	var parametro = $("#formConsultaLibro").serialize();
	var url = $("#formConsultaLibro").attr("action");
	url = url+ '/'+ textSearch;
	$.ajax({
		type : 'GET',
		url : url,
		data : parametro,
		success : function(data) {
			console.log(data);
			location.href = '/dairis/chatbot/index/';
			$('#listResult').replaceWith(data);
		},
		error : function(data){
			var informacionError = data.responseText;
			var jsonResponse = JSON.parse(informacionError);
		}
	});
}