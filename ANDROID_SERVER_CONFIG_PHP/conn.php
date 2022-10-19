<?php
    $hostname = "localhost";
    $username = "u584847502_mzmotors";
    $password = "dvm>uErPz!4";
    $database = "u584847502_mzmotors";

    $conn = new mysqli($hostname, $username, $password, $database);
    if($conn->connect_errno)
    {
        echo "Failed to connect to MySQL";
    } else
    {
        echo "Connected to MySQL";
    }
?>