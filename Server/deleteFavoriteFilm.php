<?php
    require "connect.php";

    $idUser = $_POST['tendangnhap'];
    $idFilm = $_POST['idphim'];

    $deleteQuery = "DELETE FROM favorite_film WHERE id_user = '$idUser' AND id_film = '$idFilm' ";

    $checkQuery = mysqli_query($conn, "SELECT * FROM favorite_film WHERE  id_user = '$idUser' AND id_film = '$idFilm'");

    $check = mysqli_query($conn, $deleteQuery);

    if (mysqli_num_rows($checkQuery) < 1) {
        echo "Phim yêu thích không tồn tại~";
    }
    else if($check == true) {
        echo "Bỏ yêu thích thành công~";
    }
    else {
        echo "Lỗi kết nối~";
    }


?>