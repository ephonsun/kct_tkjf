var cityJson;
$(document).ready(function(){
		$("dd:not(:first)").hide();
		$(".left_slide dt").click(function(){
			$('dd:visible').slideUp("fast");
			$(this).next().slideDown("fast");
			return false;
		});
    //获取省市区JSON数据
    $.getJSON("data/cities.json", function(obj) {
               cityJson = obj;
             var sb = new StringBuffer();
              $.each(obj, function(i, val) {
                if(val.item_code.substr(2, 4) == '0000'){
                      sb.append("<option value='"+val.item_code+"'>"+val.item_name+"</option>");
                  }
              });
              $("#choosePro").after(sb.toString());
          }
      );
    //全选操作
$("#chk_all").click(function(){
     $("input[name='chk_list']").attr("checked",$(this).attr("checked"));
});

});




















    // 省值变化时 处理市
    function doProvAndCityRelation(){
      var city = $("#citys");
      var county  = $("#county ");
      if(city.children().length > 1){
          city.empty();
      }
      if(county.children().length > 1){
          county.empty();
      }
      if($("#chooseCity").length == 0){
          city.append("<option id='chooseCity' value=''>地级市</option>");
      }
      if($("#chooseCounty ").length == 0){
          county.append("<option id='chooseCounty' value=''>县级市</option>");
      }
      var sb = new StringBuffer();
      $.each(cityJson, function(i, val) {
          if(val.item_code.substr(0, 2) == $("#province").val().substr(0, 2) && val.item_code.substr(2, 4) != '0000' && val.item_code.substr(4, 2) == '00'){
              sb.append("<option value='"+val.item_code+"'>"+val.item_name+"</option>");
          }
      });
      $("#chooseCity").after(sb.toString());
    }
    　　　　　　　　　 // 市值变化时 处理区/县
    function doCityAndCountyRelation(){
      var cityVal = $("#citys").val();
      var county = $("#county");
      if(county.children().length > 1){
          county.empty();
      }
      if($("#chooseCounty").length == 0){
          county.append("<option id='chooseCounty' value=''>县级市</option>");
      }
      var sb = new StringBuffer();
      $.each(cityJson, function(i, val) {
          if(cityVal=='110100' || cityVal=="120100" || cityVal=="310100" || cityVal=="500100"){
              if(val.item_code.substr(0, 3) == cityVal.substr(0, 3) && val.item_code.substr(4, 2) != '00'){
                  sb.append("<option value='"+val.item_code+"'>"+val.item_name+"</option>");
              }
          }else{
              if(val.item_code.substr(0, 4) == cityVal.substr(0, 4) && val.item_code.substr(4, 2) != '00'){
                  sb.append("<option value='"+val.item_code+"'>"+val.item_name+"</option>");
              }
          }
      });
      $("#chooseCounty").after(sb.toString());

    }

    function StringBuffer(str){    
      var arr = [];    
      str = str || "";
          var size = 0 ;  // 存放数组大小
          arr.push(str);
          // 追加字符串
          this.append = function(str1) {        
              arr.push(str1);        
              return this;    
          };
          // 返回字符串
          this.toString = function(){        
              return arr.join("");    
          };
          // 清空  
          this.clear = function(key){  
              size = 0 ;  
              arr = [] ;  
          }
          // 返回数组大小  
          this.size = function(){  
              return size ;  
          }
          // 返回数组  
          this.toArray = function(){  
              return buffer ;  
          }
          // 倒序返回字符串  
          this.doReverse = function(){  
              var str = buffer.join('') ;   
              str = str.split('');    
              return str.reverse().join('');  
          }
      };



//新闻发布ajax
// $('#pub_news').on('click',function(){
//     $.ajax({
//         type:"post",
//         url:"demo.jsp",
//         async:true,
//         data:$('#pub_news_form').serialize()
//         success:function(msg){
//             alert(msg);
//         }
//     });
// })


































/*
**登录页云彩
*/
// Cloud Float...
    var $main = $cloud = mainwidth = null;
    var offset1 = 450;
	var offset2 = 0;
	
	var offsetbg = 0;
    
    $(document).ready(
        function () {
            $main = $("#mainBody");
			$body = $("body");
            $cloud1 = $("#cloud1");
			$cloud2 = $("#cloud2");
			
            mainwidth = $main.outerWidth();
         
        }
    );

    /// 飘动
    setInterval(function flutter() {
        if (offset1 >= mainwidth) {
            offset1 =  -580;
        }

        if (offset2 >= mainwidth) {
			 offset2 =  -580;
        }
		
        offset1 += 1.1;
		offset2 += 1;
        $cloud1.css("background-position", offset1 + "px 100px")
		
		$cloud2.css("background-position", offset2 + "px 460px")
    }, 70);
	
	
	setInterval(function bg() {
        if (offsetbg >= mainwidth) {
            offsetbg =  -580;
        }

        offsetbg += 0.9;
        $body.css("background-position", -offsetbg + "px 0")
    }, 90 );

