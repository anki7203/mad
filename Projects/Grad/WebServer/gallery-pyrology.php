<?php 
/**
Template Page for the gallery overview

Follow variables are useable :

	$gallery     : Contain all about the gallery
	$images      : Contain all images, path, title
	$pagination  : Contain the pagination content

 You can check the content when you insert the tag <?php var_dump($variable) ?>
 If you would like to show the timestamp of the image ,you can use <?php echo $exif['created_timestamp'] ?>

?ngg_force_update=1 <-append to url to force cache refresh

**/
?>
<?php if (!defined ('ABSPATH')) die ('No direct access allowed'); ?><?php if (!empty ($gallery)) : ?>



<?php if ($gallery->show_piclens) { ?>
	<!-- Piclense link -->
	<div class="piclenselink">
		<a class="piclenselink" href="<?php echo nextgen_esc_url($gallery->piclens_link) ?>">
			<?php _e('[View with PicLens]','nggallery'); ?>
		</a>
	</div>
<?php } ?>
	<div class="ngg-thumb-holder">
	<!-- Thumbnails -->
    <?php $i = 0; ?>
	<?php foreach ( $images as $image ) : ?>
	
			<a class="cover-thumb col-xs-12 col-sm-4 col-md-3 col-lg-2" href="#"
               title="<?php echo esc_attr($image->description) ?>"
               data-src="<?php echo nextgen_esc_url($image->imageURL); ?>"
               data-thumbnail="<?php echo nextgen_esc_url($image->thumbnailURL); ?>"
               data-image-id="<?php echo esc_attr($image->pid); ?>"
               data-title="<?php echo esc_attr($image->alttext); ?>"
               data-description="<?php echo esc_attr($image->description); ?>"
            >
				<?php if ( !$image->hidden ) { ?>
				<img title="<?php echo esc_attr($image->alttext) ?>" alt="<?php echo esc_attr($image->alttext) ?>" src="<?php echo nextgen_esc_url($image->thumbnailURL) ?>"/>
				<?php } ?>
			</a>

    <?php if ( $image->hidden ) continue; ?>
    <?php if ($gallery->columns > 0): ?>
        <?php if ((($i + 1) % $gallery->columns) == 0 ): ?>
            <br style="clear: both" />
        <?php endif; ?>
    <?php endif; ?>
    <?php $i++; ?>

 	<?php endforeach; ?>
    </div>	
	<!-- Pagination -->
 	<?php echo $pagination ?>
 	
<?php endif; ?>


<div class="pyrology-cover">
	<div class="control close"><i class="fa fa-times"></i></div>
	<div class="control go-prev"><i class="fa fa-angle-left"></i></div>
	<div class="control go-next"><i class="fa fa-angle-right"></i></div>
	<ul></ul>
</div>