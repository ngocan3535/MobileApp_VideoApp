<?php
    require "connect.php";

    $idUser = $_GET['id_user'];
    $IDFilm = $_POST['id_phim'] ?? "";
    $TenFilm = $_POST['ten_phim'] ?? "";
    $ImgUrl = $_POST['hinh_phim'] ?? "";
    $ImgCover = $_POST['anh_bia'] ?? "";
    $MoTa = $_POST['mo_ta'] ?? "";

    $query = "SELECT * FROM favorite_film WHERE id_user = '$idUser'";
    $data = mysqli_query($conn, $query);

    class FavoriteFilm{ 
        public $ID;
        public $IdPhim;
        public $TenPhim;
        public $ImgUrl;
        public $ImgCover;
        public $MoTa;
        
        function __construct($id, $id_phim, $ten_film, $imgUrl, $imgCover, $description) {
            $this -> ID = $id;
            $this -> IdPhim = $id_phim;
            $this -> TenPhim = $ten_film;
            $this -> ImgUrl = $imgUrl;
            $this -> ImgCover = $imgCover;
            $this -> MoTa = $description;
        }
    }

    $mang_FavoriteFilm = array();
    while ($row = mysqli_fetch_assoc($data)) {

        //3 cái biến thì giống 3 cột trong PHP
        $object = new FavoriteFilm($row['id'], $row['id_film'], $row['ten_film'], 
                                    $row['imgUrl'], $row['imgCover'], $row['description']);

        array_push($mang_FavoriteFilm, $object);

    }

    echo json_encode($mang_FavoriteFilm);

?>