<?php
    require "connect.php";
    
    $id = $_POST['tendangnhap'];
    $pass = $_POST['matkhau'];

    $checkQuery = mysqli_query($conn, "SELECT user FROM account WHERE user like '$id'");

    if (strlen(($id) > 0 && strlen($id) <= 15 && strlen($pass) > 0 && strlen($pass) <= 15)) {
        $query = "INSERT INTO account(user, password) VALUES ('$id', '$pass')";
        if (mysqli_num_rows($checkQuery) > 0)
            echo "Tài khoản đã tồn tại";
        else if(mysqli_query($conn, $query))
            echo "Tạo tài khoản thành công!";
        else 
            echo "Lỗi kết nối";
    }
    else {
        echo "Dữ liệu không hợp lệ";
    }
    
    mysqli_close($conn);

?>