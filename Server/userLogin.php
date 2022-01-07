<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
    include 'connect.php';
    $User = $_POST['tendangnhap'];
    $Password = $_POST['matkhau'];

    // include 'connect.php';
    // $User = $_GET['tendangnhap'];
    // $Password = $_GET['matkhau'];
    
    $Sql_Query = "select * from account where user = '$User' and password = '$Password' ";
    
    $check = mysqli_fetch_array(mysqli_query($conn,$Sql_Query));
    

    if(isset($check)){
        echo "Data Matched";
    }
    else {
        echo "Wrong Data";
    }
 }
 else {
    echo "Check Again";
 }

mysqli_close($conn);

?>