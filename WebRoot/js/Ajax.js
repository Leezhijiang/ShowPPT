function getRoomIDAndPD(){
    $.ajax({
        type: "GET",
        timeout: 80000,
        url: "servlet/GetRoomIDServlet?randID=" + Math.random(),
        success: function(msg){
            if (msg != "") {
				var obj = jQuery.parseJSON(""+msg+"");
				$("#roomID").val("Room ID = "+obj.roomID);
				$("#roomPD").val("Password = "+obj.pd);
			
            }
        }
    });
}
