<?php
include 'conn.php';

    $email = $_POST['email'];
    $password = $_POST['password'];

    //$email = "prueba@correo.com";
    //$password = "hola";

    $sel_smt = $conn->prepare("SELECT * FROM usuarios WHERE email = ? AND pwd = ?");
    $sel_smt->bind_Param("ss", $email, $password);
    $sel_smt->execute();

    $result = $sel_smt->get_result();
    if ($fila = $result->fetch_assoc())
    {
        echo json_encode($fila, JSON_UNESCAPED_UNICODE);
    }
    else{
        echo "No se encontraron resultados";
    }

    $sel_smt->close();
    $conn->close();
?>