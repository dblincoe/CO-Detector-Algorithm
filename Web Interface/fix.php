<?php

$con = mysql_connect("localhost","root","teeth109");

if (!$con) {
die('Could not connect: ' . mysql_error());
}

mysql_select_db("Garage", $con);

$result = mysql_query("SELECT * FROM `readings`") or die ("Error");

$iterator = 0;
$data = [];
while($row = mysql_fetch_array($result)) {
$data[$iterator][] = $row['reading_Id'];
$data[$iterator][] = $row['reading_Value'];
$iterator++;
}

 echo "UPDATE `readings` SET `reading_Value` = ".$data[0][1]*(90/11)." WHERE `reading_Id` = ".$data[0][0];

for($i=0; i<48; $i++) {
	mysql_query("UPDATE `readings` SET `reading_Value` = ".$data[$i][1]*(90/11)." WHERE `reading_Id` = ".$data[$i][0]);
	
}

var_dump($data);

mysql_close($con);

?>