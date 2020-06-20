<?php
	$conn = new MySQLi('localhost','root','','sfcs');
	mysqli_set_charset($conn,'utf8');
	$id = $_GET['id'];
	$sql = "DELETE FROM LISTFOOD WHERE ID = '$id'";
	mysqli_query($conn,$sql);
	header('location:http://localhost/CNPM/front-end/ManagementListFood.php');
?>