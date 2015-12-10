<?php show_admin_bar(false); ?>
<!doctype html>  

<!--[if IEMobile 7 ]> <html <?php language_attributes(); ?>class="no-js iem7"> <![endif]-->
<!--[if lt IE 7 ]> <html <?php language_attributes(); ?> class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html <?php language_attributes(); ?> class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html <?php language_attributes(); ?> class="no-js ie8"> <![endif]-->
<!--[if (gte IE 9)|(gt IEMobile 7)|!(IEMobile)|!(IE)]><!--><html <?php language_attributes(); ?> class="no-js"><!--<![endif]-->
	
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><?php wp_title( '|', true, 'right' ); ?></title>	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
  		<link rel="pingback" href="<?php bloginfo('pingback_url'); ?>">
  		<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,600,400italic,700italic,600italic,800,800italic' rel='stylesheet' type='text/css'>

		<!-- wordpress head functions -->
		<?php wp_head(); ?>
		<!-- end of wordpress head -->
		<!-- IE8 fallback moved below head to work properly. Added respond as well. Tested to work. -->
			<!-- media-queries.js (fallback) -->
		<!--[if lt IE 9]>
			<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>			
		<![endif]-->

		<!-- html5.js -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->	
		
			<!-- respond.js -->
		<!--[if lt IE 9]>
		          <script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js"></script>
		<![endif]-->	
	</head>

	<body <?php body_class(); ?>>
				
		<header class="main" role="banner">
			<div class="logo">
				<a href="/"><img alt="Standard Ceramic Supply Company" src="<?php echo wp_get_attachment_url(5); ?>"/></a>
				<div class="special-alerts-btn"><i class="fa fa-bullseye"></i> Special Notices</div>
			</div>
			<div class="contact-bar">
				<ul class="social-links">
					<li><a target="_blank" href="https://www.facebook.com/The-Clay-Place-at-Standard-135977123193398/"><i class="fa fa-facebook-square"></i></a></li>
					<!--li><a target="_blank" href="https://twitter.com/@clayplace"><i class="fa fa-twitter-square"></i></a></li-->
					<li><a target="_blank" href="https://instagram.com/clayplaceatstandard/"><i class="fa fa-instagram"></i></a></li>
					<!--li><a target="_blank" href="https://www.linkedin.com/company/standard-ceramic-supply-co"><i class="fa fa-linkedin-square"></i></a></li-->
				</ul>
				<ul class="phone-numbers">
					<li><a href="tel://412-276-6333"><i class="fa fa-phone"></i> 412.276.6333</a></li>
					<li><a href="tel://412-276-7124"><i class="fa fa-fax"></i> 412.276.7124</a></li>
				</ul>
			</div>
			<nav>
				<?php wp_nav_menu( array('menu' => 'Main Nav' )); ?>
			</nav>
		
		</header>

		<div class="mobile-nav">
			<div class="mobile-nav-btn"><i class="fa fa-bars"></i></div>
			<nav>
				<?php wp_nav_menu( array('menu' => 'Mobile Nav' )); ?>
			</nav>
			<ul class="social-links">
				<li><a target="_blank" href="https://www.facebook.com/standardceramic"><i class="fa fa-facebook-square"></i></a></li>
				<li><a target="_blank" href="https://twitter.com/@clayplace"><i class="fa fa-twitter-square"></i></a></li>
				<li><a target="_blank" href="https://instagram.com/clayplaceatstandard/"><i class="fa fa-instagram"></i></a></li>
				<li><a target="_blank" href="https://www.linkedin.com/company/standard-ceramic-supply-co"><i class="fa fa-linkedin-square"></i></a></li>
			</ul>
		</div>
		
		<main class="fluid-container">
