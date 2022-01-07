<?php
    require "connect.php";

    $idUser1 = $_POST['tendangnhap'];
    $IDFilm1 = $_POST['idphim'];


    $query = "SELECT * FROM favorite_film WHERE id_user = '$idUser1' AND id_film = '$IDFilm1'";
    $data = mysqli_query($conn, $query);

    if (mysqli_num_rows($data) > 0) {
        echo "YES";
    }
    else {
        echo "NO";
    }
?>