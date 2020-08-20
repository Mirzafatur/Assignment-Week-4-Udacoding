<?php

include_once('koneksidb.php');

if (!empty($_POST['id'])) {
	
	$id = $_POST['id'];
	$query = "DELETE FROM mahasiswa WHERE id_mahasiswa = '$id'";

	$delete = mysqli_query($connect, $query);

	if ($delete) {
		response(true, "Sukses hapus data");
	} else {
		response(false, "Gagal hapus data");
	}
} else {
	response(false, "Untuk hapus data id harus diisi");
}

function response($isSuccess, $message) {
	$result = array(
		'isSuccess' => $isSuccess,
		'message' => $message);

	echo json_encode($result);
}