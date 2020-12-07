var myVueapp = new Vue({
	el: "#user"
	,data:{
		users: []
	},
	created:function(){
			var url = 'http://localhost:8082/user/1';
			$.ajax({
				url: url,
				success: function(data){
					this.users = data.userList;
				}.bind(this)
			});
	}
});
console.log(myVueapp.members);