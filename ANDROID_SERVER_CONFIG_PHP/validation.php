<?php
    include 'conn.php';

		$email		=$_POST["email"];	//textbox nombre "txt_email"
		$password	=$_POST["password"];	//textbox nombre "txt_password"
		
		//$email = "eochoa11@ucol.mx";
		//$password = "123tamarindo";
		
		$password 	= hash('sha512', $password);
		
		//echo "<br>" . $email ."<br>", $password;
		
		if(empty($email))
		{						
			echo "empty_email";
		}
		else if(empty($password))
		{
			echo "empty_password";
		}

		else if($email AND $password)
		{
			try
			{
				$select_stmt=$db->prepare("SELECT email, pwd FROM usuarios WHERE email=:uemail AND pwd=:upassword AND rol = 0"); 
				$select_stmt->bindParam(":uemail",$email);
				$select_stmt->bindParam(":upassword",$password);
				$select_stmt->execute();	//execute query
				
				while($row=$select_stmt->fetch(PDO::FETCH_ASSOC))	
				{
					$dbemail	=$row["email"];
					$dbpassword	=$row["pwd"];
					//echo "<br><br>";
					//echo $row ['email'];
					//echo "<br>";
					//echo $row ['pwd'];
					//echo "<br>";
				}
				if($email!=null AND $password!=null)	
				{
					if($select_stmt->rowCount()>0)
					{
						if($email==$dbemail and $password==$dbpassword)
						{
                            echo "1";
						}
						else
						{
							echo "error_todo";
						}
					}
					else
					{
						echo "error_todo_2";
					}
				}
			}
			catch(PDOException $e)
			{
				$e->getMessage();
			}		
		}
		else
		{
			echo "empty_all";
		}
?>