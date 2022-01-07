<?php
    require "connect.php";

    $IdUser = $_POST['id_user'] ?? "";
    $IdFilm = $_POST['id_phim'] ?? "";
    $TenFilm = $_POST['ten_phim'] ?? "";
    $HinhAnhFilm = $_POST['hinh_anh_phim'] ?? "";

    // $IdUser = "a";
    // $IdFilm = "11s1";
    // $TenFilm = "!";
    // $HinhAnhFilm = "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg";


    $checkQuery = mysqli_query($conn, "SELECT * FROM favorite_film WHERE id_user = '$IdUser' AND id_film = '$IdFilm'");
    
    $insertQuery = "INSERT INTO favorite_film (id_user, id_film, ten_film, imgUrl) 
    VALUES ('$IdUser', '$IdFilm', '$TenFilm', '$HinhAnhFilm')";

    if (mysqli_num_rows($checkQuery) > 0) {
        echo "Phim đã thích~";
    }
    else if (mysqli_query($conn, $insertQuery)) {
        echo "Thêm yêu thích thành công~";
    }
    else {
        echo "Lỗi kết nối~";
    }

    mysqli_close($conn);
    

?>

