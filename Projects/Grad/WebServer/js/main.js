var CID = '';

$(document).ready(function(){

	var home = 'http://andrewkiproff.com/gd-blogify/';
	var feed = 'feed.xml';

	$.getFeed({
	   url: feed,
	   success: function(feed) {
	     createFeed(feed);
	   }
	 });



	function createFeed(feed){

		var initStr = "";

		$.each(feed.items, function(index, value) {
			var id = this.id.split(" ")[0];
			var title = '<h2>'+this.title+'</h2>';
			var link = '<button data-id="'+id+'" data-link="'+this.link+'">Read & React</button>';
			var info = '<div class="content">'+this.description+'</div>';

			initStr += id+'|'+this.title+'|'+this.link+'|'+this.description+'||';

		   $("#feed").append('<article>'+title+info+link+'</article>');
		   $("#feed .content a").replaceWith($("a").text());
		});
		console.log(initStr);

		$.ajax({
			method: "POST",
			url: "controller.php", 
			data: { init: initStr }
		}).done(function(data){
			alert(data);
		});

		$("button").on('click', function(){
			var link = $(this).attr('data-link');
			var id = $(this).attr('data-id');
			window.location.href = "read?GDreact="+id+"&url="+link;
		});
	}



	$(".back").on('click', function(){
		window.location.href = "http://andrewkiproff.com/gd-blogify";
	});

	$(".react button").on('click', function(){
		var id = window.location.href.split('=')[1];
		var username = $(".react input").val();
		var msg = $(".react textarea").val();
		$.ajax({
			method: "POST",
			url: "../controller.php", 
			data: { react: id, message: msg, name: username }
		});
		$('.react-form').hide();
		$('.react .success').fadeToggle();
	});

	if($(".reactions").length){

		var id = window.location.href.split('=')[1];

		if(id){
			$.ajax({
				method: "POST",
				url: "../controller.php", 
				data: { reactions: id }
			}).done(function(data) {
				var reactions = data.split('||');

				$.each(reactions, function(index, value) {
					var info = value.split("|");
					if(info[0].length > 0){
						var name = '<h2>Reaction by '+info[0]+'</h2>';
						var message = '<p>'+info[1]+'</p>';

				   		$("#feed").append('<article>'+name+message+'</article>');
				   		$("#feed .content a").replaceWith($("a").text());
				   	}
				});
			});
		}else{
			$("#feed").append('<article><h2>There are no reactions for this article!</h2></article>');
		}
	}

	if($(".favorites").length){

		var favs = window.location.href.split('=')[1].toString();


		if(favs){
			$.ajax({
				method: "POST",
				url: "../controller.php", 
				data: { favorites: favs }
			}).done(function(data) {

				var favsArr = data.split('||');
				$.each(favsArr, function(index, value) {
					var info = value.split("|");
					console.log(info[0]);
					if(info[0].length > 0){
				   		$("#feed").append('<article><a href="../read/?url='+info[0]+'"><h2>'+info[1]+'</h2></a></article>');
				   		$("#feed .content a").replaceWith($("a").text());
				   	}
				});
			});
		}else{
			$("#feed").append('<article><h2>You Don\'t Have Any Favorites... Yet!</h2></article>');
		}
	}

});