function monitor(data) 
{
	if(data.status == "begin")
	{
		return $('div#ajaxLoader').modal();						
	} 
	else if(data.status == "complete") 	
	{
		return $('div#ajaxLoader').modal('hide');			
	}
}