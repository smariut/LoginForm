<?php 
//inserare rand nou
$response = array(); 

if (isset($_GET['username']) && isset($_GET['password'])) { 

	$username = $_GET['username']; 
	$password = $_GET['password']; 
 
	require_once __DIR__ . '/connect.php'; 
	$db = new DB_CONNECT(); 
 
	$result = @mysql_query("INSERT INTO utilizator(username, password) VALUES('$username', '$password')"); 

	if ($result) { 
	
	$response["success"] = 1; 
	$response["message"] = "new USER saved"; 

	echo json_encode($response); 
	} else { 
	
	$response["success"] = 0; 
	$response["message"] = "An error occurred."; 
	
	echo json_encode($response); 
	} 
	} else { 
	$response["success"] = 0; 
	$response["message"] = "Required field(s) is missing"; 
	
	echo json_encode($response); 
	} 
?> 