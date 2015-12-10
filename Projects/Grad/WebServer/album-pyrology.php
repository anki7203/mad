<?php
/**
Template Page for the album overview

Follow variables are useable :

    $album     	 : Contain information about the first album
    $albums    	 : Contain information about all albums
	$galleries   : Contain all galleries inside this album
	$pagination  : Contain the pagination content

 You can check the content when you insert the tag <?php var_dump($variable) ?>
 If you would like to show the timestamp of the image ,you can use <?php echo $exif['created_timestamp'] ?>
**/
?>
<?php if (!defined ('ABSPATH')) die ('No direct access allowed'); ?><?php if (!empty ($galleries)) : ?>

	<!-- List of galleries -->
	<?php 
		foreach ($galleries as $gallery){
			echo '<a class="col-xs-12 col-sm-4 col-md-3 col-lg-3" href="'.nextgen_esc_url($gallery->pagelink).'"><img src="'.nextgen_esc_url($gallery->previewurl).'"><p>'.esc_attr($gallery->title).'</p></a>';
		}
	?>


<?php endif; ?>
