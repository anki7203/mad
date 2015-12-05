<?php



$mysqli = new mysqli("localhost", "XXXXXXXXXX", "XXXXXXXXXX", "XXXXXXXXXX");

if ($mysqli->connect_errno) {

    echo "Failed to connect to server..oops! Call your web admin for help.";

}





if(isset($_POST['init'])){

	$initStr = $_POST['init'];

	$feedArr = explode( '||', $initStr );

	foreach ($feedArr as $article) {

	    $info = explode( '|', $article );

	    $postId = $info[0];

	    $title = $info[1];

	    $link = $info[2];

	    $info = $info[3];



	    $results = $mysqli->query("SELECT * FROM feed WHERE postId = $postId");



	    if($results->num_rows <= 0 && $postId.length > 0){

	    	$insert_row = $mysqli->query("INSERT INTO feed (postId, title, link, info, reactions) VALUES ('$postId', '$title', '$link', '$info', '')");

	    }

	}

}else if(isset($_POST['react'])){



	$postId = $_POST['react'];

	$msg = $_POST['message'];

	$name = $_POST['name'];



    $results = $mysqli->query("SELECT * FROM feed WHERE postId = $postId");



    $row = $results->fetch_assoc();



    $output = $name.'|'.$msg.'||'.$row["reactions"];



    $mysqli->query("UPDATE feed SET reactions = '$output' WHERE postId = $postId");



}else if(isset($_POST['reactions'])){



	$postId = $_POST['reactions'];



	$results = $mysqli->query("SELECT * FROM feed WHERE postId = $postId");



	$row = $results->fetch_assoc();



	echo $row["reactions"];



}else if(isset($_POST['favorites'])){



	$favStr = "";



	$favorites = $_POST['favorites'];

	$favArr = explode( ',', $favorites );

	foreach ($favArr as $fav) {



		if($fav.length > 0 ){



		    $results = $mysqli->query("SELECT * FROM feed WHERE postId = $fav");



		    $row = $results->fetch_assoc();



		    $favStr .= $row["link"].'|'.$row["title"].'||';

		}



	}



	echo $favStr;



}



?>
