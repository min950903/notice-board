var main = {
	init : function() {
		var _this = this;
		
		$('#btn-save').on('click', function () {
			_this.save();
		});
		$('#btn-update').on('click', function () {
			_this.update();
		});
		$('#btn-delete').on('click', function () {
			_this.delete();
		});
	},
	save : function() {
		var data = {
		    title : $('#title').val(),
		    content : $('#content').val(),
		    author : $('#author').val()
		};
		
		$.ajax({
			type: 'POST',
			url: '/api/notice',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(data)
		}).done(function () {
			alert('성공적으로 등록되었습니다.');
			window.location.href='/';
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	},
	update : function () {
		var data = {
				title : $('#title').val(),
				content : $('#content').val(),
				author : $('#author').val()
		};
		
		var id = $('#id').val();
		
		$.ajax ({
			type : 'PUT',
			url : '/api/notice/' + id,
			datatype : 'json',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(data)
		}).done(function () {
			alert('성공적으로 수정되었습니다.');
			window.location.href = '/';
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	},
	delete : function() {
		var id = $('#id').val();
		
		 $.ajax({
	            type: 'DELETE',
	            url: '/api/notice/'+id,
	            dataType: 'json',
	            contentType:'application/json; charset=utf-8'
	        }).done(function() {
	            alert('글이 삭제되었습니다.');
	            window.location.href = '/';
	        }).fail(function (error) {
	            alert(JSON.stringify(error));
	        });
	}
};

main.init();