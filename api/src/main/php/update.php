<?php
	$conn = new MySQLi('localhost','root','','sfcs');
	mysqli_set_charset($conn,'utf8');
?>
<form method="post">
	<table width="100%">
		<tr>
			<th>NAME</th>
			<th>PRICE</th>
			<th>STATUS</th>
			<th></th>
		</tr>
			<?php
				$id = $_GET['id'];
				$sql = "SELECT * FROM LISTFOOD WHERE ID = '$id'";
				$result = mysqli_query($conn,$sql);
				$r = mysqli_fetch_assoc($result);
				$NAME = $r['NAME'];
				$PRICE = $r['PRICE'];
				$STATUS = $r['STATUS'];
				echo("<tr>");
				echo("<td><input type='text' name='NAME' value='$NAME'/></td>");
				echo("<td><input type='text' name='PRICE' value='$PRICE'/></td>");
				echo("<td><input type='text' name='STATUS' value='$STATUS'/></td>");
				echo("<td><input type='submit' value='EDIT' name='EDIT' placeholder='$STATUS'/></td>");
				echo("</tr>");
				if(isset($_POST['EDIT']))
				{
					$NAME = $_POST['NAME'];
					$PRICE = $_POST['PRICE'];
					$STATUS = $_POST['STATUS'];
					$sql = "UPDATE LISTFOOD SET ID='$ID',ID='$id',NAME='$NAME',PRICE='$PRICE',STATUS='$STATUS'WHERE ID='$id'";
					mysqli_query($conn,$sql);
					header('location:http://localhost/CNPM/front-end/ManagementListFood.php');
				}
			?>
	</table>
</form>