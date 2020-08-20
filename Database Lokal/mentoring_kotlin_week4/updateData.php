<?php

include_once('koneksidb.php');

if (!empty($_POST['id']) && !empty($_POST['nama']) && !empty($_POST['nohp']) && !empty($_POST['alamat'])) {
	
	$id = $_POST['id'];
	$nama = $_POST['nama'];
	$nohp = $_POST['nohp'];
	$alamat = $_POST['alamat'];

	$query = "UPDATE mahasiswa set mahasiswa_nama = '$nama', mahasiswa_nohp = '$nohp', mahasiswa_alamat = '$alamat' WHERE id_mahasiswa = '$id'";

	$update = mysqli_query($connect, $query);

	if ($update) {
		response(true, "Sukses update data");
	} else {
		response(false, "Gagal mengupdate data");
	}	
} else {
	response(false, "id, nama, no hp dan alamat harus diisi");
}

function response($isSuccess, $message) {
	$result = array(
		'isSuccess' => $isSuccess,
		'message' => $message);

	echo json_encode($result);
}