<?php
/*
Template Name: Pyrology gComm
*/
?>

<?php 
	$output = '';
	$startId = 0;
	$post = get_post($post->ID); 
	$content = apply_filters('the_content', $post->post_content); 
	echo $content;  

	$mysqli = new mysqli("localhost", "drotwosi_pyro", "W]lzuJA0NtIX", "drotwosi_pyrology");
	if ($mysqli->connect_errno) {
	    echo "Failed to connect to server.";
	}

	if(isset($_GET['more'])){

		$startId = $_GET['more'];

		$results = $mysqli->query("SELECT * FROM instaGrab WHERE selected = 1 AND id < $startId ORDER BY id DESC LIMIT 24"); 

		while($row = $results->fetch_object()) {
			$output .= '<a class=\"cover-thumb col-xs-12 col-sm-4 col-md-3 col-lg-2\" href=\"#\" data-link=\"'.$row->link.'\" data-src=\"'.$row->imgSrc.'\" data-username=\"'.$row->username.'\"><img src=\"'.$row->imgSrc.'\"><p>@'.$row->username.'</p></a>'; 
			$startId = $row->id; 
		}  

		$json = '{"startId":'.$startId.',"output":"'.$output.'"}';

		echo $json;
		exit;

	}else{

		$results = $mysqli->query("SELECT * FROM instaGrab WHERE selected = 1 AND id > $startId ORDER BY id DESC LIMIT 24"); 

		$output .= '<div class="community-gallery">';
		while($row = $results->fetch_object()) {
			$output .= '<a class="cover-thumb col-xs-12 col-sm-4 col-md-3 col-lg-2" href="#" data-link="'.$row->link.'" data-src="'.$row->imgSrc.'" data-username="'.$row->username.'"><img src="'.$row->imgSrc.'"><p>@'.$row->username.'</p></a>';
			$startId = $row->id; 
		}  

		$output .= '</div>';

	}


	$mysqli->close();
?>

<?php get_header(); ?>
			
<?php 
  $feature_image_id = get_post_thumbnail_id($id); 
  $feature_image_meta = wp_get_attachment_image_src($feature_image_id, 'full');
  $featuredImg = $feature_image_meta[0];

?> 
			
<div class="hero sub off row" style="background-image: url(<?php echo $featuredImg; ?>);">

	<div class="big-logo col-xs-12 col-sm-6 col-sm-offset-3"><img src="/imgs/PyrologyGlass_Logo.png" /></div>

</div>
<main class="row <?php echo strtolower(get_the_title( $post->ID )); ?>">
	<section class="content col-xs-10 col-xs-offset-1 col-sm-10 col-sm-offset-1">
		<h1><?php echo get_the_title( $post->ID ); ?></h1>
		<p class="lead"><a href="../" class="backtogalleries"><i class="fa fa-angle-left"></i> ALL GALLERIES</a></p>

		<?php echo $output; ?>

		<a data-startId="<?php echo $startId; ?>" class="instagrab-more"><i class="fa fa-plus"></i> Load More Images</a>
	</section>
</main>

<div class="pyrology-cover">
	<div class="control close"><i class="fa fa-times"></i></div>
	<div class="control go-prev"><i class="fa fa-angle-left"></i></div>
	<div class="control go-next"><i class="fa fa-angle-right"></i></div>
	<ul></ul>
</div>

<?php get_footer(); ?>