<?php

$con = mysql_connect("localhost","root","teeth109");

if (!$con) {
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Garage", $con);

$result = mysql_query("SELECT * FROM `".$_GET["table"]."` WHERE `reading_Id` > '" . $_GET["lastReadingId"] . "' LIMIT 0 , 1") or die ("Error");

while($row = mysql_fetch_array($result)) {
echo $row['reading_Id']."/".$row['timestamp']."/".$row['reading_Value'];
}

mysql_close($con);
?>