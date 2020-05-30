$(function(){
		
		
	
		var page=getUrlParam("page");
		
	
		if(page=="null"||page==null||page=="NaN"||page==NaN){
			page=1;
			
		}else{
			
			page=Number(page);
			if(page==0){
				page=1;
			}
		}
		
		
		$("#first_page1").click(function(){
			
			href="index.jsp?page=1";
			window.location.href=href;
		})
		$("#last_page1").click(function(){
			
			href="index.jsp?page="+storage.getItem('totalPage');
			window.location.href=href;
		})
		$("#previous_page1").click(function(){
			
			if(Number(storage.getItem('totalPage'))>=page&&page>1){
				page=page-1;
			href="index.jsp?page="+page;}else{href="index.jsp?page="+page;}
			window.location.href=href;
		})
		$("#next_page1").click(function(){
			if(1<=page&&page<Number(storage.getItem('totalPage'))){
				
				page=page+1;
				href="index.jsp?page="+page;}else{
					
					href="index.jsp?page="+page;}
				window.location.href=href;
		})
		

		
	})