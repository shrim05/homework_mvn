/**
 * 
 */
  var contentArea = $('#contentArea');
  var pagingArea =$('#pagingArea');
  var prodInfoArea = $('#prodInfoArea');
    $('#menu').on('click','li',function () { 
        $(this).siblings().removeClass("active");
        $(this).addClass("active");
    });
    $('#create').on('click',function(){
        $('#myModal').modal('show');
        $('#confirmBtn').show();    
        $('#resetBtn').show().click();  
        $('#deleteBtn').hide();
        $('#updateBtn').hide();
        prodInfoArea.hide();
    });
    
    $('#contentArea').on('click','tr',function () {
    	$('#myModal').modal('show');
        $('#deleteBtn').show();
        $('#updateBtn').show();
        $('#confirmBtn').hide();    
        $('#resetBtn').hide();  
        prodInfoArea.show();
            $.ajax({
                type: "get",
                url:  contextPath+"/BuyerController",
                data: "command=readOne&buyer_id="+$(this).prop("id"),
                dataType: "json",
                success: function (v) {
                	$("input[name='buyer_add1']").val(v.buyer_add1);
                    $("input[name='buyer_add2']").val(v.buyer_add2);
                    $("input[name='buyer_bank']").val(v.buyer_bank);
                    $("input[name='buyer_bankname']").val(v.buyer_bankname);
                    $("input[name='buyer_bankno']").val(v.buyer_bankno);
                    $("input[name='buyer_charger']").val(v.buyer_charger);
                    $("input[name='buyer_comtel']").val(v.buyer_comtel);
                    $("input[name='buyer_fax']").val(v.buyer_fax);
                    $("input[name='buyer_id']").val(v.buyer_id);
                    $("input[name='buyer_lgu']").val(v.buyer_lgu);
                    $("input[name='buyer_mail']").val(v.buyer_mail);
                    $("input[name='buyer_name']").val(v.buyer_name);
                    $("input[name='buyer_zip']").val(v.buyer_zip);
                    let code="<table><tr>";
                    code+="<th>상품코드</th><th>상품명</th><th>구분</th><th>가격</th></tr>";
                    if(v.prodList[0].prod_id==null){
                    	code += "<td colspan='4'>보유상품없음</td>";
                    }else{
	                    $.each(v.prodList,function(i,prod){
	                    	code +="<tr><td>"+prod.prod_id+"</td><td>"+prod.prod_name+"</td><td>"+prod.prod_lgu+"</td>";
	                    	code += "<td>"+prod.prod_price+"</td></tr>"
	                    });
	                    code+="</table>";
                    }
                    prodInfoArea.html(code);
                }
            });
        }   
    );

  $('#readList').on('click',function () { 
	  ajaxReadList(1);
    });
  
  function ajaxReadList(page){
      $.ajax({
          type: "get",
          url: contextPath+"/BuyerController",
          data: {"command":"readList","page":page},
          dataType: "json",
          success: function (response) {  
              let code = "<table id='listTB' class='table'>";
              code += "<thead class='thead-dark'> <tr><th>아이디</th><th>이름</th><th>구분</th><th>대표자</th><th>이메일</th></thead>";
              code += "<tbody>"
              var dataList=response.dataList;
              $.each(dataList, function (i, v) { 
                   code+="<tr id="+v.buyer_id+"><td>"+v.buyer_id+"</td><td>"+v.buyer_name+"</td><td>"+v.buyer_lgu+"</td>";
                   code+="<td>"+v.buyer_charger+"</td><td>"+v.buyer_mail+"</td>";
              });
              code+="</tbody></table>"
              contentArea.html(code);
              pagingArea.html(response.pagingHTML);
          }   
      });
  }
	$(pagingArea).on("click","a",function(){
		var page = $(this).data("page");
		if(page>0){
			ajaxReadList(page);
		}
	});
    
    $('#confirmBtn').on('click', function () {
    	var formData = $('#editForm').serialize();
        $.ajax({
            type: "post",
            url: contextPath+"/BuyerController",
            data: formData,
            dataType: "json",
            success: function (response) {
             	alert(response.result);
             	$('#myModal').modal('hide');
            }
        });
    });
    $('#updateBtn').on('click', function () {
    	var formData = $('#editForm').serialize();
        $.ajax({
            type: "post",
            url: contextPath+"/BuyerUpdate",
            data: formData,
            dataType: "text",
            success: function (response) {
             	alert(response);
             	$('#myModal').modal('hide');
            }
        });
    });
    $('#deleteBtn').on('click', function () {
    	var formData = $('#editForm').serialize();
        $.ajax({
            type: "post",
            url: contextPath+"/BuyerDeleteController",
            data: formData,
            dataType: "text",
            success: function (response) {
            	alert(response);
            	$('#myModal').modal('hide');
            }
        });
    });