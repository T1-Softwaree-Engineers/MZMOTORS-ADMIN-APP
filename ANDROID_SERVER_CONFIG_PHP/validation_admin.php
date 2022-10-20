<?php
include 'conn.php';

    $email = $_POST['email'];
    $password = $_POST['password'];

    //$email = "prueba@correo.com";
    //$password = "123tamarindo";
    $password 	= hash('sha512', $password);

    $sel_smt = $conn->prepare("SELECT * FROM usuarios WHERE email = ? AND pwd = ? and rol = 1");
    $sel_smt->bind_Param("ss", $email, $password);
    $sel_smt->execute();

    $result = $sel_smt->get_result();
    
    
    if($email == "" && $password == "")
    {
        echo "empty_data";
    }
    else if($email == "")
    {
        echo "no_email";
    }
    else if($password == "")
    {
        echo "empty_password";
    }
    else if ($fila = $result->fetch_assoc())
    {
        echo "1";
    }
    else
    {
        echo "no_verif";
    }

    $sel_smt->close();
    $conn->close();
?>