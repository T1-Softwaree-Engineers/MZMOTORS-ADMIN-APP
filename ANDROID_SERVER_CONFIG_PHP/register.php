<?php
include 'conn.php';

		
		//$nombre = "Enrique";
		//$email = "contact@enriqu2.com";
		//$contacto = "3141111111";
		//$password = "123tamarindo";
	

		$nombre 	= $_POST['nombre'];
		$email		= $_POST['email'];	//input nombre "txt_email"
		$contacto 	= $_POST['contacto'];
        $password   = $_POST['password'];
        
		$password = hash('sha512', $password);
		if(empty($nombre))
		{
			echo "empty_name";	//Revisar email input no vacio
		}
		else if(empty($email))
		{
			echo "empty_email";	//Revisar email input no vacio
		}
		else if(empty($contacto))
		{
			echo "emtpy_contact";	//Revisar email input no vacio
		}
		else if(!filter_var($email, FILTER_VALIDATE_EMAIL))
		{
			echo "invalid_email";	//Verificar formato de email
		}
		else if(empty($password))
		{
			echo "empty_password";	//Revisar password vacio o nulo
		}
		else if(strlen($password) < 6)
		{
			echo "small_password";	//Revisar password 6 caracteres
		}
		else
		{	
			try
			{	
				$select_stmt=$db->prepare("SELECT email FROM usuarios WHERE email=:uemail"); // consulta sql  
				$select_stmt->bindParam(":uemail",$email);      //parámetros de enlace
				$select_stmt->execute();
				$row=$select_stmt->fetch(PDO::FETCH_ASSOC);	

				if($row["email"] == $email)
				{
					echo "email_duplicated";	//Verificar email existente
				}
				else if(!isset($errorMsg))
				{
					$insert_stmt=$db->prepare("INSERT INTO usuarios(nombre, email, contacto, pwd) VALUES(:unombre,:uemail,:ucontacto,:upassword)"); //Consulta sql de insertar

					$insert_stmt->bindParam(":unombre",$nombre);	
					$insert_stmt->bindParam(":uemail",$email);
					$insert_stmt->bindParam(":ucontacto",$contacto);	  		//parámetros de enlace
					$insert_stmt->bindParam(":upassword",$password);

					if($insert_stmt->execute())
					{
						echo "register_success";	//Registro exitoso
					}
				}
			}
			catch(PDOException $e)
			{
				echo $e->getMessage();
			}
		}
?>