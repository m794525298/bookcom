$(function(){

		
	
		var page=getUrlParam("page");
		var postID=getUrlParam("postID");
	
		if(page=="null"||page==null||page=="NaN"||page==NaN){
			page=1;
			
		}else{
			
			page=Number(page);
			if(page==0){
				page=1;
			}
		}
		
		
		$("#first_page1").click(function(){
			
			href="personal_comment.jsp?page=1&postID="+postID;
			window.location.href=href;
		})
		$("#last_page1").click(function(){
			
			href="personal_comment.jsp?page="+storage.getItem('totalPage')+"&postID="+postID;
			window.location.href=href;
		})
		$("#previous_page1").click(function(){
			
			if(Number(storage.getItem('totalPage'))>=page&&page>1){
				page=page-1;
			href="personal_comment.jsp?page="+page+"&postID="+postID;}else{href="personal_comment.jsp?page="+page+"&postID="+postID;}
			window.location.href=href;
		})
		$("#next_page1").click(function(){
			if(1<=page&&page<Number(storage.getItem('totalPage'))){
				
				page=page+1;
				href="personal_comment.jsp?page="+page+"&postID="+postID;}else{
					
					href="personal_comment.jsp?page="+page+"&postID="+postID;}
				window.location.href=href;
		})
		

		
	})