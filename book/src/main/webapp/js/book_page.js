$(function(){

		
		 var keyword=getUrlParam("keywords");
		
		var bookType=getUrlParam("bookType");
		var searchType=getUrlParam("searchType");
		var page=getUrlParam("page");
		
		if(keywords=="null"||keywords==null){
			keywords=null;
		}
		if(searchType=="null"||searchType==null){
			searchType=null;
		}
		if(bookType=="null"||bookType==null){
			bookType=null;
		}
		if(page=="null"||page==null||page=="NaN"||page==NaN){
			page=1;
			
		}else{
			
			page=Number(page);
			if(page==0){
				page=1;
			}
		}
		
		
		$("#first_page1").click(function(){
			
			href="search.jsp?keywords="+keyword+"&bookType="+bookType+"&page=1"+"&searchType="+searchType;
			window.location.href=href;
		})
		$("#last_page1").click(function(){
			
			href="search.jsp?keywords="+keyword+"&bookType="+bookType+"&page="+storage.getItem('totalPage')+"&searchType="+searchType;
			window.location.href=href;
		})
		$("#previous_page1").click(function(){
			
			if(Number(storage.getItem('totalPage'))>=page&&page>1){
				page=page-1;
			href="search.jsp?keywords="+keyword+"&bookType="+bookType+"&page="+page+"&searchType="+searchType;}else{href="search.jsp?keywords="+keyword+"&bookType="+bookType+"&page="+page+"&searchType="+searchType;}
			window.location.href=href;
		})
		$("#next_page1").click(function(){
			if(1<=page&&page<Number(storage.getItem('totalPage'))){
				
				page=page+1;
				href="search.jsp?keywords="+keyword+"&bookType="+bookType+"&page="+page+"&searchType="+searchType;}else{
					
					href="search.jsp?keywords="+keyword+"&bookType="+bookType+"&page="+page+"&searchType="+searchType;}
				window.location.href=href;
		})
		

		
	})