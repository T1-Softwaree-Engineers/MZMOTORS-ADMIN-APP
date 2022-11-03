<?php
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
	header('Access-Control-Allow-Headers: X-Requested-With');
	header('Content-Type: application/json');

	include_once 'db.php';
	
	$user = new Database();


	$api = $_SERVER['REQUEST_METHOD'];

	$id = intval($_GET['id'] ?? '');

	if ($api == 'GET') {
		
	  if ($id != 0) {
	    $data = $user->fetch($id);
	  } else {
	    $data = $user->fetch();
	  }
	  echo json_encode($data);
	}

	if ($api == 'POST') {
	  $nombre = $user->test_input($_POST['nombre']);
	  $email = $user->test_input($_POST['email']);
	  $contacto = $user->test_input($_POST['contacto']);
      $password = $user->test_input($_POST['pwd']);
      $password = hash('sha512', $password);
	  if ($user->insert($nombre, $email, $contacto, $password)) {
	    echo $user->message('User added successfully!',false);
	  } else {
	    echo $user->message('Failed to add an user!',true);
	  }
	}

    
	if ($api == 'PUT') {
	  parse_str(file_get_contents('php://input'), $post_input);

	  $nombre = $user->test_input($post_input['nombre']);
	  $email = $user->test_input($post_input['email']);
	  $contacto = $user->test_input($post_input['contacto']);
      $password = $user->test_input($post_input['pwd']);
      $password = hash('sha512', $password);

	  if ($id != null) {
	    if ($user->update($nombre, $email, $contacto, $id)) {
	      echo $user->message('User updated successfully!',false);
	    } else {
	      echo $user->message('Failed to update an user!',true);
	    }
	  } else {
	    echo $user->message('User not found!',true);
	  }
	}

	if ($api == 'DELETE') {
	  if ($id != null) {
	    if ($user->delete($id)) {
	      echo $user->message('User deleted successfully!', false);
	    } else {
	      echo $user->message('Failed to delete an user!', true);
	    }
	  } else {
	    echo $user->message('User not found!', true);
	  }
	}

?>