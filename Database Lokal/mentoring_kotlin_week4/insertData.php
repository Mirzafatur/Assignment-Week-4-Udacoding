<?php

include_once('koneksidb.php');

if (!empty($_POST['nama']) && !empty($_POST['nohp']) && !empty($_POST['alamat'])) {
	
	$nama = $_POST['nama'];
	$nohp = $_POST['nohp'];
	$alamat = $_POST['alamat'];



	$query = "INSERT INTO mahasiswa(mahasiswa_nama, mahasiswa_nohp, mahasiswa_alamat) VALUES('$nama', '$nohp', '$alamat')";

	$insert = mysqli_query($connect, $query);

	if ($insert) {
		response(true, "Sukses memasukkan data");
	} else {
		response(false, "Gagal memasukkan data");
	}
} else {
	response(false, "Parameter nama, nohp, dan alamat harus diisi");
}

function response($isSuccess, $message) {
	$result = array(
		'isSuccess' => $isSuccess,
		'message' => $message);

	echo json_encode($result);
}