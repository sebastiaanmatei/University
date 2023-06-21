<?php
$serverName = "localhost";
				$dbName = "ajaxLABS";
				$conn = new mysqli($serverName, "root", "", $dbName);

				if ($conn->connect_error)
					die("Connection failed to my sql ".$conn->connect_error);
				else
					echo "Connected to my sql";


				$result = mysqli_query($conn, "SELECT DISTINCT Oras2 FROM routesv where Oras1= );

				while($row = mysqli_fetch_array($result)){
					//echo "<option value=" . $row . ">" . $row . "</option>";
					echo $row[0];
				}


				mysqli_close($conn);
?>
