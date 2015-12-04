<?php
   $favs = "";
   if(isset($_GET['favs'])){
   	$favs = $_GET['favs'];
   }
?>

<!doctype html>
<html class="no-js favorites" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>GD Blogify: Favorites</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link href='https://fonts.googleapis.com/css?family=Laila:400,700|Merriweather:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="../css/normalize.min.css">
        <link rel="stylesheet" href="../css/main.css">

        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
        <header>
            <a href="http://andrewkiproff.com/gd-blogify"><i class="fa fa-chevron-left back"></i></a>
            <img class="logo" alt="Welcome to GD Blogify!" src="../img/logo.png">
        </header>

        <section class="read react">
            <section id="feed" class="react-form">
            </section>
        </section>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

        <script src="../js/plugins.js"></script>
        <script src="../js/main.js"></script>
    </body>
</html>
