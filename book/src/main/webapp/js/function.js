	$(function(){
		
		$(".p").focus(function(){
			var p_name=this.name;
			switch (p_name){
			case "userAccount" :
				alert("1");
				this.next().empty();
				break;
				;
			case "userPassWord" :
				this.next().empty();
				break;
				;
			case "rePassWord" :
				this.next().empty();
				break;
				;
			case "email" :
				this.next().empty();
				break;
				;
			
			
			}
			
			
		})
	})