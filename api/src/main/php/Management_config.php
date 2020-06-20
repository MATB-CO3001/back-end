<?php
$conn = new MySQLi('localhost','root','','sfcs');
mysqli_set_charset($conn,'utf8');
?>
<table width="100%" cellpadding="10" cellspacing="0" border="1">
	<tr>
		<th>ID</th>
		<th>NAME</th>
		<th>PRICE</th>
		<th>STATUS</th>
		<th>HANDLING</th>
	</tr>
	<?php
		$sql = "SELECT * FROM LISTFOOD ORDER BY ID ASC";
		$result = mysqli_query($conn,$sql);
		if(mysqli_num_rows($result)>0)
		{
			while($r = mysqli_fetch_assoc($result))
			{
				$id = $r['ID'];
				$name = $r['NAME'];
				$price = $r['PRICE'];
				$status = $r['STATUS'];
				echo("<tr>");
				echo("<td>$id</td>");
				echo("<td>$name</td>");
				echo("<td>$price</td>");
				echo("<td>$status</td>");
				echo("<td><a href='http://localhost/ManagementForVendor/delete.php?id=$id'>Delete</a> | <a href='http://localhost/ManagementForVendor/update.php?id=$id'>Edit</a></td>");
				echo("</tr>");
			}
		}
		else
			echo("NO DATA!");
	?>
</table></br></br>
<form method="post">
	<table width="100%">
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>PRICE</th>
			<th>STATUS</th>
			<th></th>
		</tr>
		<tr>
			<td>
				<input type="text" name="ID"/>
			</td>
			<td>
				<input type="text" name="NAME"/>
			</td>
			<td>
				<input type="text" name="PRICE"/>
			</td>
			<td>
				<input type="text" name="STATUS"/>
			</td>
			<td>
				<input type="submit" name="add" value="ADD"/>
			</td>
		</tr>
	</table>
</form>
<?php
	if(isset($_POST['add']) && $_POST['ID'] != '')
	{
		$ID = $_POST['ID'];
		$NAME = $_POST['NAME'];
		$PRICE = $_POST['PRICE'];
		$STATUS = $_POST['STATUS'];
		$sql = "INSERT INTO LISTFOOD VALUES('$ID','$NAME','$PRICE','$STATUS')";
		mysqli_query($conn,$sql);
		header('location:http://localhost/CNPM/front-end/ManagementListFood.php');
	}
?>