var $ = jQuery.noConflict();

$(window).load(function(){


	var winH = $(window).height();
	var winW = $(window).width();

	$('body').removeAttr('id');
	$('.load-spinner').hide();
	$('.hero').removeClass('off');

	var curPath = window.location.pathname;
	$('nav a').each(function() {
		if(  curPath.indexOf($(this).attr('href')) > -1 ){$(this).addClass('active')}
	});

	$('.next-btn').on('click',function(e){
		$("html, body").animate({ scrollTop: winH }, 2000);
	});

	$('.big-logo').on('click',function(e){
		window.location.href = "/";
	});

	$('.loc-btn').on('click',function(e){ homeLocateSubmit(); });
	$('.loc-in').on('focus',function(e){ 
		$(document).on("keydown", function(e){ 
			if(e.which == 13){ homeLocateSubmit(); } 
		});
	 });
	$('.loc-in').on('blur', function(e){ $(document).off("keydown"); });

	function homeLocateSubmit(){
		var loc = encodeURIComponent($('.loc-in').val());
		if(loc){ window.location.href = "/locations?locate="+loc; }
		else{ alert("The field was left blank!"); }
	}

	var curURL = document.URL;
	if( curURL.indexOf("goto") > -1 ){
		var goTo = curURL.split("goto=");
		var offset = ($('.'+goTo[1]).offset().top)-120;
		$("html, body").animate({ scrollTop: offset }, 2000);
	}

	if(curURL.indexOf('locate') > -1){
		var locate = curURL.split("locate=");
		var loc = decodeURIComponent( locate[1] );
		$('#addressInput').val(loc);
		setTimeout(function(e){ 
			$('#searchForm').submit(); 
			$("html, body").animate({ scrollTop: winH-100 }, 2000); 
		}, 1000);
	}

	if(curURL.indexOf('locations') > -1){ 
		$('#addressSubmit').replaceWith('<input type="submit" value="Find" class="locSubmit"></input>'); 
		$('#searchForm').on('submit',function(e){ 
			$("html, body").animate({ scrollTop: winH-100 }, 2000); 
		});
	}
	



	function setSectionHeight(bypass){

		var winH = $(window).height();
		var winW = $(window).width();

		if(winW>768 || bypass){

			$('.hero').css({'min-height':winH+'px'});

			$('section').not( $( "section.content" ) ).css({'min-height':(winH-70)+'px'});
			$('section.gallery').css({'height':(winH-70)+'px'});

			$("#map_tr").before($("#cm_mapTR"))

		}

		if(winW<=768){ 
			$('section').removeClass('off').removeAttr('style').css({'min-height': winH+'px'}); 
			$("#map_tr").after($("#cm_mapTR"))
		}

		$('section').each(function() {
			var imgH = $(this).children('.bg').height();
			var secH = $(this).height();

			var imgW = $(this).children('.bg').width();
			var secW = $(this).width();

		    if (secH>imgH) {
				$(this).addClass('tall');
		    }

		    if (secW>imgW) {
		    	$(this).removeClass('tall');
		    }

		    var margin = (secH - $(this).children('.copy').height())/2;
		    var coverMargin = (secH - $(this).children('.cover-link').children('h1').height())/2;

		    $(this).children('.copy').css({'margin-top':margin+'px'});
		    $(this).children('.cover-link').children('h1').css({'margin-top':coverMargin+'px'});

		});
	}

	setSectionHeight(true);

	setTimeout(function(e){ 
		$('.hero, .hero .big-logo, header, .hero .next-btn, .social').removeClass('off'); 
		$('.big-logo').addClass('on');
	},2000);

	var resizeTimer = false;
	$( window ).resize(function() {
		clearTimeout(resizeTimer);
	  	resizeTimer = setTimeout(function(){ 
	  		setSectionHeight(false);
	  		clearCoverflow(true);
	  		$('header nav').removeAttr('style');
	  		$('.nav-btn').addClass('fa-bars').removeClass('fa-times');
	  	},500);
	});

	$(function () {
	     var $win = $(window);

	     $win.scroll(function () {
	        if ($win.scrollTop() == 0){
	        	$('header').removeClass('shrink');
	        	$('.big-logo').css({'opacity':1});
	        	$('.gallery').addClass('off');
	        }else{
	            $('header').addClass('shrink');
	            $('.big-logo').css({'opacity':0});
	        }
	    });
	});

	$('nav').on('click','a',function(e){
		$('nav a').removeClass('active');
		$(this).addClass('active');
	});

	$('.hero .next-btn').on('click',function(e){
		$('body').scrollTo('.gallery');
	});

	$('.nav-btn').on('click',function(e){
		$(this).toggleClass('fa-bars').toggleClass('fa-times');
		$('header').addClass('shrink');
		$('header nav').toggle();
	});





	$(window).on('scroll',function(event){

		var winW = $(window).width();

		if(winW<=768){ 
			$('section').removeClass('off').removeAttr('style').css({'min-height': winH+'px'}); 
		    $('.nav-btn').addClass('fa-bars').removeClass('fa-times');
		    $('header nav').hide();
		}

		if(winW>768){
			$('section').each(function() {

				var offset = $(this).offset().top; 

				if ( $(window).scrollTop() >= (offset-(winH/2))) {
					$(this).removeClass('off');
			    }

			    if ( $(window).scrollTop() >= (offset-(winH*.6))) {
					$(this).siblings('section').addClass('off');
			    }

			});
		}

	});

	var userFeed = new Instafeed({
		get: 'user',
		userId: 145296380,
		accessToken: '145296380.467ede5.f9cb91f544634e26a52dd5d8e0d1606c',
		limit: 12,
		resolution: 'standard_resolution',
		template: '<a class="cover-thumb col-xs-12 col-sm-4 col-md-3 col-lg-2" href="#" data-link="{{link}}" data-src="{{image}}" ><img alt="{{caption}}" src="{{image}}" /></a>',
		sortBy: 'most-recent',
		after: function() {
		    if (!this.hasNext()) {
		    	$('.instafeed-more').addClass('no-more').text('No More Images!').off('click');
		    }else{
		    	$('.instafeed-more').on('click', function() { userFeed.next(); });
		    }
		    setupCoverflow();
		},
	});

	if($('#instafeed').hasClass('full')){ userFeed.options.limit = 24; }

	userFeed.run();

	$('.instagrab-more').on('click', function() { 
		var startId = $(this).data('startid');
		$.getJSON("http://www.pyrologyglass.com/galleries/community/?more="+startId , function(data) {
		 	$('.instagrab-more').data('startid',data.startId);
		 	$('.community-gallery').append(data.output);
		 	if(!data.output){ $('.instagrab-more').addClass('no-more').text('No More Images!').off('click'); }
		 	setupCoverflow();
		});
	});

	if( $(window).scrollTop() > 100){
		$('header').addClass('shrink');
	}

	function setupCoverflow(){
		//if there is a gallery on the page...
		if($('.cover-thumb').length){

			if(window.location.href.indexOf("collabs") > -1){
				$('.backtogalleries').html('<i class="fa fa-angle-left"></i> ALL COLLABS').on('click',function(){ parent.history.back(); return false; });
			}

			//and I click on a thumbnail...
			$('.cover-thumb').off('click');
			$('.cover-thumb').on('click', function(e){
				e.preventDefault();

				$(window).on('swipeleft', function(e){ setClasses('next'); });
				$(window).on('swiperight', function(e){ setClasses('prev'); });

				$(document).on("keydown", function(e){
					if(e.which == 39 || e.which == 68 || e.which == 102){ setClasses('next'); }
					if(e.which == 37 || e.which == 65 || e.which == 100){ setClasses('prev'); }
				});

				$(this).add('.pyrology-cover').addClass('active');
				//$('.pyrology-cover').addClass('hideImgs');
				$('html').css('overflow','hidden');

				//populate coverflow with images that link to larger version.
				var imgCount = 0;
				$('.cover-thumb').each(function(i,e){
					var imgSrc = $(this).data('src');
					var imgLink = $(this).data('link');
					var username = $(this).data('username');
					if(username){ username = '<p>@'+username+'</p>';}else{ username = ''; }
					if(!imgLink){ imgLink = imgSrc }
					var liClass = '';
					if($(this).hasClass('active')){ liClass = 'class="active"'; }
					$('.pyrology-cover ul').append('<li '+liClass+'><a target="_blank" href="'+imgLink+'"><img src="'+imgSrc+'"></a>'+username+'</li>');
					imgCount++;
				});

				//When an image is loaded, get its height and width and set margins (this centers the '.active' image on the page)
				var loadCount = 0;
				$('.pyrology-cover ul li img').load(function(e){
					loadCount++;
					console.log(loadCount+' of '+imgCount);

					var liHeight = $(this).height();
					var liWidth = $(this).width();

					var tMargin = (liHeight/2)*-1;
					var lMargin = (liWidth/2)*-1;

					if(liWidth+100 > liHeight){ $(this).parents('li').addClass('landscape'); }

					$(this).parents('li').attr('style','margin-top:'+tMargin+'px; margin-left:'+lMargin+'px').addClass('set');

					if(loadCount >= imgCount){ 
						setClasses('init');
						setTimeout(function(e){ $('.pyrology-cover').removeClass('hideImgs'); },500); 
					}

				});


				function setClasses(direction){

					$('.pyrology-cover li.active').siblings().removeClass('active prev next prev-ondeck next-ondeck');

					if(direction == 'next' && $('.pyrology-cover li.active').next('li').length ){
						$('.pyrology-cover li.active').removeClass('active').addClass('prev').next().addClass('active');
					}else if(direction == 'prev' && $('.pyrology-cover li.active').prev('li').length ){
						$('.pyrology-cover li.active').removeClass('active').addClass('next').prev().addClass('active');
					}else if(direction != 'init'){
						$('.pyrology-cover li.active').addClass('shake');
						setTimeout(function(e){ $('.pyrology-cover li.active').removeClass('shake'); },500);
					}
					
					$('.pyrology-cover li.active').prev().addClass('prev').prevAll().addClass('prev-ondeck');
					$('.pyrology-cover li.active').next().addClass('next').nextAll().addClass('next-ondeck');

				}

				$('.pyrology-cover .go-prev').on('click',function(e){ setClasses('prev'); });
				$('.pyrology-cover .go-next').on('click',function(e){ setClasses('next'); });

				//If I click on the parent ul or '.close' button, reset coverflow and hide it.
				$('.pyrology-cover ul').on('click', function(e){ if( e.target !== this ){return}; clearCoverflow(); });

				$('.pyrology-cover .close').on('click', function(e){ clearCoverflow(); });

			});

		}
	}
	setupCoverflow();

	function clearCoverflow(reset){ 
	   $('.pyrology-cover ul').empty();
	   $('.cover-thumb').add('.pyrology-cover').removeClass('active');
	   $('.pyrology-cover .go-prev, .pyrology-cover .go-next, .pyrology-cover ul, .pyrology-cover .close').unbind('click');
	   $('html').css('overflow','visible');
	   $(window).unbind('swipeleft swiperight');
	   $(document).off("keydown");
	}

});

// Generated by CoffeeScript 1.3.3
(function(){var e,t;e=function(){function e(e,t){var n,r;this.options={target:"instafeed",get:"popular",resolution:"thumbnail",sortBy:"none",links:!0,mock:!1,useHttp:!1};if(typeof e=="object")for(n in e)r=e[n],this.options[n]=r;this.context=t!=null?t:this,this.unique=this._genKey()}return e.prototype.hasNext=function(){return typeof this.context.nextUrl=="string"&&this.context.nextUrl.length>0},e.prototype.next=function(){return this.hasNext()?this.run(this.context.nextUrl):!1},e.prototype.run=function(t){var n,r,i;if(typeof this.options.clientId!="string"&&typeof this.options.accessToken!="string")throw new Error("Missing clientId or accessToken.");if(typeof this.options.accessToken!="string"&&typeof this.options.clientId!="string")throw new Error("Missing clientId or accessToken.");return this.options.before!=null&&typeof this.options.before=="function"&&this.options.before.call(this),typeof document!="undefined"&&document!==null&&(i=document.createElement("script"),i.id="instafeed-fetcher",i.src=t||this._buildUrl(),n=document.getElementsByTagName("head"),n[0].appendChild(i),r="instafeedCache"+this.unique,window[r]=new e(this.options,this),window[r].unique=this.unique),!0},e.prototype.parse=function(e){var t,n,r,i,s,o,u,a,f,l,c,h,p,d,v,m,g,y,b,w,E,S;if(typeof e!="object"){if(this.options.error!=null&&typeof this.options.error=="function")return this.options.error.call(this,"Invalid JSON data"),!1;throw new Error("Invalid JSON response")}if(e.meta.code!==200){if(this.options.error!=null&&typeof this.options.error=="function")return this.options.error.call(this,e.meta.error_message),!1;throw new Error("Error from Instagram: "+e.meta.error_message)}if(e.data.length===0){if(this.options.error!=null&&typeof this.options.error=="function")return this.options.error.call(this,"No images were returned from Instagram"),!1;throw new Error("No images were returned from Instagram")}this.options.success!=null&&typeof this.options.success=="function"&&this.options.success.call(this,e),this.context.nextUrl="",e.pagination!=null&&(this.context.nextUrl=e.pagination.next_url);if(this.options.sortBy!=="none"){this.options.sortBy==="random"?d=["","random"]:d=this.options.sortBy.split("-"),p=d[0]==="least"?!0:!1;switch(d[1]){case"random":e.data.sort(function(){return.5-Math.random()});break;case"recent":e.data=this._sortBy(e.data,"created_time",p);break;case"liked":e.data=this._sortBy(e.data,"likes.count",p);break;case"commented":e.data=this._sortBy(e.data,"comments.count",p);break;default:throw new Error("Invalid option for sortBy: '"+this.options.sortBy+"'.")}}if(typeof document!="undefined"&&document!==null&&this.options.mock===!1){a=e.data,this.options.limit!=null&&a.length>this.options.limit&&(a=a.slice(0,this.options.limit+1||9e9)),n=document.createDocumentFragment(),this.options.filter!=null&&typeof this.options.filter=="function"&&(a=this._filter(a,this.options.filter));if(this.options.template!=null&&typeof this.options.template=="string"){i="",o="",l="",v=document.createElement("div");for(m=0,b=a.length;m<b;m++)s=a[m],u=s.images[this.options.resolution].url,this.options.useHttp||(u=u.replace("http://","//")),o=this._makeTemplate(this.options.template,{model:s,id:s.id,link:s.link,image:u,caption:this._getObjectProperty(s,"caption.text"),likes:s.likes.count,comments:s.comments.count,location:this._getObjectProperty(s,"location.name")}),i+=o;v.innerHTML=i,S=[].slice.call(v.childNodes);for(g=0,w=S.length;g<w;g++)h=S[g],n.appendChild(h)}else for(y=0,E=a.length;y<E;y++)s=a[y],f=document.createElement("img"),u=s.images[this.options.resolution].url,this.options.useHttp||(u=u.replace("http://","//")),f.src=u,this.options.links===!0?(t=document.createElement("a"),t.href=s.link,t.appendChild(f),n.appendChild(t)):n.appendChild(f);document.getElementById(this.options.target).appendChild(n),r=document.getElementsByTagName("head")[0],r.removeChild(document.getElementById("instafeed-fetcher")),c="instafeedCache"+this.unique,window[c]=void 0;try{delete window[c]}catch(x){}}return this.options.after!=null&&typeof this.options.after=="function"&&this.options.after.call(this),!0},e.prototype._buildUrl=function(){var e,t,n;e="https://api.instagram.com/v1";switch(this.options.get){case"popular":t="media/popular";break;case"tagged":if(typeof this.options.tagName!="string")throw new Error("No tag name specified. Use the 'tagName' option.");t="tags/"+this.options.tagName+"/media/recent";break;case"location":if(typeof this.options.locationId!="number")throw new Error("No location specified. Use the 'locationId' option.");t="locations/"+this.options.locationId+"/media/recent";break;case"user":if(typeof this.options.userId!="number")throw new Error("No user specified. Use the 'userId' option.");if(typeof this.options.accessToken!="string")throw new Error("No access token. Use the 'accessToken' option.");t="users/"+this.options.userId+"/media/recent";break;default:throw new Error("Invalid option for get: '"+this.options.get+"'.")}return n=""+e+"/"+t,this.options.accessToken!=null?n+="?access_token="+this.options.accessToken:n+="?client_id="+this.options.clientId,this.options.limit!=null&&(n+="&count="+this.options.limit),n+="&callback=instafeedCache"+this.unique+".parse",n},e.prototype._genKey=function(){var e;return e=function(){return((1+Math.random())*65536|0).toString(16).substring(1)},""+e()+e()+e()+e()},e.prototype._makeTemplate=function(e,t){var n,r,i,s,o;r=/(?:\{{2})([\w\[\]\.]+)(?:\}{2})/,n=e;while(r.test(n))i=n.match(r)[1],s=(o=this._getObjectProperty(t,i))!=null?o:"",n=n.replace(r,""+s);return n},e.prototype._getObjectProperty=function(e,t){var n,r;t=t.replace(/\[(\w+)\]/g,".$1"),r=t.split(".");while(r.length){n=r.shift();if(!(e!=null&&n in e))return null;e=e[n]}return e},e.prototype._sortBy=function(e,t,n){var r;return r=function(e,r){var i,s;return i=this._getObjectProperty(e,t),s=this._getObjectProperty(r,t),n?i>s?1:-1:i<s?1:-1},e.sort(r.bind(this)),e},e.prototype._filter=function(e,t){var n,r,i,s,o;n=[],i=function(e){if(t(e))return n.push(e)};for(s=0,o=e.length;s<o;s++)r=e[s],i(r);return n},e}(),t=typeof exports!="undefined"&&exports!==null?exports:window,t.Instafeed=e}).call(this);