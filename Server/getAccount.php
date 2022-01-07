<?php
    require "connect.php";
    $query = "SELECT * FROM ".$tablename;
    $data = mysqli_query($conn, $query);

    class UserAccount{ 
        public $User;
        public $Password;
        
        function __construct($user, $password) {
            $this->User = $user;
            $this->Password = $password;
        }
    }

    $mang_UserAccount = array();
    while ($row = mysqli_fetch_assoc($data)) {

        $object = new UserAccount($row['user'], $row['password']);

        array_push($mang_UserAccount, $object);

        //$mangUserAccount[] = $row;
    }

    echo json_encode($mang_UserAccount);

?>