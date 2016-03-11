<?php
	$con = mysql_connect("localhost","root","teeth109");
	
	if (!$con) {
		die('Could not connect: ' . mysql_error());
	}
	
	mysql_select_db("Garage", $con);
	$result = mysql_query("SELECT  * 
						   FROM  `".$_GET["table"]."` 
						   ORDER BY  `reading_Id` ASC ") or die ("Error");
	$i = 0;
	$readingIdData = [];
	$data = [];
	$readingTimestamp = [];
	while($row = mysql_fetch_array($result)) {
		$data[$i] = $row['reading_Value'];
		$readingIdData[$i] = $row['reading_Id'];
		$readingTimestamp[$i] = $row['timestamp'];
		$i++;
	}
	var_dump($readingTimestamp);
	$sum = 0;
	for($i=0; $i<count($data);$i++){
		$sum += $data[$i];
	}
	
	$upperBound = 3*($sum/count($data));
	echo $upperBound;
	
	$newData = [];
	$newReadingId = [];
	$newTimestamp = [];
	for($i=0; $i<count($data);$i++){
		if($i%1 == 0){
			$newData[] = $data[$i];
			$newReadingId[] = $readingIdData[$i];
			$newTimestamp[] = $readingTimestamp[$i];
		} else if($data[$i] >= $upperBound){
			$newData[] = $data[$i];
			$newReadingId[] = $readingIdData[$i];
			$newTimestamp[] = $readingTimestamp[$i];
		}
	}
	$i = 0;
	while($i<count($newData)) {
		echo $newReadingId[$i]."/".$newTimestamp[$i]."/".$newData[$i]."/";
		$i++;
	}

	mysql_close($con);
?>
