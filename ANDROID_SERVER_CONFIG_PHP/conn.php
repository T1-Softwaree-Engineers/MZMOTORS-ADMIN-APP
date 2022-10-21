<?php
	$db_host = "localhost"; //localhost server 
	$db_user = "";	//database username
	$db_password = "";	//database password   
	$db_name = "";	//database name
	try
	{
	  global $db;
		$db=new PDO("mysql:host={$db_host};dbname={$db_name}",$db_user,$db_password);
		$db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		//echo "conexion exitosa";
	}
	
	catch(PDOEXCEPTION $e)
	{
		$e->getMessage();
	}
?>