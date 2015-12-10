jQuery.noConflict();
(function( $ ) {

var url = window.location.href;

var isHome = url.split('/');
    isHome = isHome[1];

   // alert(isHome);

if(!$('body').hasClass('home')){
	$('#menu-main-nav').children().first().addClass('show');
}else{
	$('#menu-main-nav').children().first().next().addClass('set');
}

$(document).on('ready',function(e){
	
	$('.home-slider li').first().addClass('active');
	setInterval(function(){ 
		if($('.home-slider li.active').next('li').length){
			$('.home-slider li.active').removeClass('active').next().addClass('active');
		}else{
			$('.home-slider li.active').removeClass('active');
			$('.home-slider li').first().addClass('active');
		}
	}, 6000);


	$('.special-alerts-btn').on('click', function(e){

		if($('body').hasClass('home') && $(window).width() > 700){

			if($('.special-alerts').hasClass('on')){
				$('.special-alerts article').removeClass('on');
				setTimeout(function(){ $('.special-alerts').removeClass('on') }, 500);
			}else{
				$('.special-alerts').addClass('on');
				setTimeout(function(){ $('.special-alerts article').addClass('on') }, 2000);
			}

		}else{
			window.location.href = "/standard/special-alerts";
		}

	});

	$('.mobile-nav-btn').on('click', function(e){
		$('.mobile-nav').toggleClass('on');
		$(this).children('i').toggleClass('fa-times-circle-o').toggleClass('fa-bars');
	});

	var menuDelayTimer;
	$('.menu-item').on('mouseenter', function(e){
		clearTimeout(menuDelayTimer);
		if($(this).children('.sub-menu').length > 0){
			$(this).children('.sub-menu').addClass('on');
		}
	});
	$('.menu-item').on('mouseleave', function(e){
		menuDelayTimer = setTimeout(function () {
        	$('.sub-menu').removeClass('on');
    	}, 200);
	});

});

})( jQuery );