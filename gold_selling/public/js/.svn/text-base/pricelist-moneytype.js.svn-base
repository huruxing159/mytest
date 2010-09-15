function moneytype(moneycurrency){
	getPricelist($("#server").val(),moneycurrency);
}

function getPricelist(serverName,moneycurrency){
		if(serverName==''||serverName==undefined){
			alert("please choose  a server!!");
		}else{
			 var gold_amount=$("input[@name='goldamount']:checked").val();
			    $("div.resultsloading").show();
				$.ajax({
				  type: 'POST',
				  url: "/pricelist",
				  data:"serverName="+serverName+"&factionName="+$("input[@name='faction']:checked").val()+"&moneycurrency="+moneycurrency,
				  cache: false,
				  timeout: 60000,
				  success: function(html){
					$("div.resultsloading").hide();
				    $("div.results").empty().append(html);
				    $("div.results").show();
				    pricechange();
				    priceselectchange();
				    currencychange(serverName,moneycurrency);
				    $("input[@name='goldamount']").filter('[value='+gold_amount+']').attr('checked', true);
					 $("#amount").val(gold_amount);
				     updatemoney();
				     custom_amount()
				    only_number_input("input[name=custom_gold_amount]");
				    calculate_price_by_custom_amount_on_load();
				  },
				 error: function(XMLHttpRequest, textStatus, errorThrown){
					 ajax_error_handler(XMLHttpRequest, textStatus, errorThrown);
				}
				});
		}
}

function ajax_error_handler(XMLHttpRequest, textStatus, errorThrown){
	switch(textStatus)
	   {
	   case "timeout":
		alert("Server is busy ,retry !");
	     break
	   case "error":
		alert("Something Bad!!");
	     break
	   default:
	   }
}


function custom_amount(){
	iefocus("input[name=want_custom_amount]");
	$("div.custom_amount_class").hide();
	$("div.custom_amount_class").css("display","none");//for ie
	$('input[name=want_custom_amount]').change(function(){
		if($('input[name=want_custom_amount]').is(':checked')){
			$("div.custom_amount_class").show();
			$("#detailmoney").hide();
			if ($.browser.msie) {
					setTimeout( function() {
					  $("input[name='custom_gold_amount']").focus();
					} , 100);
					
				}else{
					$("input[name='custom_gold_amount']").focus();
				}

			
			$("input[@name='goldamount']").removeAttr("checked");
		}else{
			$("div.custom_amount_class").hide();
			$("div.custom_amount_class").css("display","none");//for ie
			clear_custom_amount();
			$("#detailmoney").show();
			
		}
	});
	
}

function clear_custom_amount(){
	$('input[name=custom_gold_amount]').val("");
	$("#customdetailmoney").empty();
}

function only_number_input(element_input){
	$(element_input).ForceNumericOnly();
}

function currencychange(serverName,moneycurrency){
	$("#price").val(moneycurrency);
	$("#price").change(function(){
		getPricelist(serverName,$("#price").val());
	});
}

function pricechange(){
	
	  iefocus("input[@name='goldamount']");
	  $("input[@name='goldamount']").change(function(){
           $("#amount").val($("input[@name='goldamount']:checked").val());
		  	updatemoney();
      });
	
}

function updatemoney(){
	if($('input[name=want_custom_amount]').attr("checked")==true){
		$("div.custom_amount_class").hide();
		$('input[name=want_custom_amount]').trigger("click") ;
		$("div.custom_amount_class").css("display","none");//for ie
		clear_custom_amount();
		$("#detailmoney").show();
	}
	 $("#detailmoney").empty().append($("input[@name='goldamount']:checked").parent().siblings(".moneytd").text());
	
}


function iefocus(element){
	if ($.browser.msie) {
    	$(element).click(function() {
	      this.blur();
	      this.focus();
	    });
     }
}
function priceselectchange(){
	$("#amount").change(function(){
		var selectvalue=$("#amount option:selected").val();
		$("input[@name='goldamount']").filter('[value='+selectvalue+']').attr('checked', true);
		updatemoney();
	});
}

function calculate_price_by_custom_amount_on_load(){
	$("div.processing").append("<img src='images/process.gif'/>").hide();
	$('input[name=custom_gold_amount]').blur(function() {
		calculate_price_by_custom_amount('usd');
	});
	$("#customprice").change(function(){
		calculate_price_by_custom_amount($("#customprice").val());
	});

}
function calculate_price_by_custom_amount(moneycurrency){
	    $("div.processing").show();
		var serverName=$("#server").val();
		var custom_gold_amount=parseInt($('input[name=custom_gold_amount]').val());
		if(isNaN(custom_gold_amount) ||custom_gold_amount <500){
			$('input[name=custom_gold_amount]').val("500");
			custom_gold_amount=500;
		}
		var server_gold_amount=parseInt($("div.server_gold_amount").text().replace(",",""));
		if(isNaN(custom_gold_amount) ||custom_gold_amount >server_gold_amount){
			$('input[name=custom_gold_amount]').val(server_gold_amount);
			custom_gold_amount=server_gold_amount;
		}
		
		if(serverName==''||serverName==undefined){
			alert("please choose  a server!!");
			$("div.processing").hide();
		}else if (custom_gold_amount==''||custom_gold_amount==undefined){
			alert("please input  gold amount!!");
			$("div.processing").hide();
		}
		else{
			$.ajax({
				type: 'POST',
				url:"/calculate_price_custom_amount",
				data:"moneycurrency="+moneycurrency+"&serverName="+serverName+"&custom_gold_amount="+custom_gold_amount+"&factionName="+$("input[@name='faction']:checked").val(),
				cache: false,
				success: function(html){
				   $("#customdetailmoney").empty().append(html);
			       $("div.processing").hide();
				}
			});
	    }
}
function   checkout_validate(){  //validate all element   required  before  submit to check out!!!
	$('input.checkout').click(function() {
		try{
			validate_input($("input[name='region']:checked").val(),"select  region");
			validate_input($("input[name='faction']:checked").val(),"Choose Your Faction");
			validate_input($("#server").val(),"Select Your Server");

			if($('input[name=want_custom_amount]').is(':checked')){
				validate_input($('input[name=custom_gold_amount]').val(),"add custom amount!!");
			}else{
				validate_input($('input[name=goldamount]:checked').val(),"select amount!!");
			}
			validate_input($('input[name=character]').val(),"input your wow character name!!");
			validate_input($('input[name=trade_method]:checked').val(),"select Trade Method  'In-game mail' , 'Face to face trade' or 'Auction Hourse Buyout' ");
			validate_input($('input[name=payment_method]:checked').val(),"select payment method ! we now  are only support PayPal!");
			if($("input[@name='trade_method']:checked").val() == 'Auction House Buyout'){
      			validate_input($('input[name=auction_item_name]').val(),"input auction item name!!");
    		}
			$("div.checkouting").append("<img src='images/loading.gif'/>").show();
			$('#checkoutform').submit();
			$('input.checkout').hide();
		}catch(err){
			return;
		}
		
	});
}
function email_validate(){
	    email=$('input[name=email]').val();
    	validate_input(email,"input your email !");
		var pattern=/^([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})$/;
		if(pattern.test(email)){
			return ;
		}
		alert("input a email like 'xxx@xx.com'");
		throw "email fail";
}

function phone_validate(phone,message){
	var telephone=/(?:\(?[0\+]\d{2,3}\)?)[\s-]?(?:(?:\(0{1,3}\))?[0\d]{1,4})[\s-](?:[\d]{7,8}|[\d]{3,4}[\s-][\d]{3,4})/;
	var celphone=/(?:\(?[0\+]?\d{1,3}\)?)[\s-]?(?:0|\d{1,4})[\s-]?(?:(?:13\d{9})|(?:\d{7,8}))/;
	if(telephone.test(phone)||celphone.test(phone)){
		return ;
	}
	alert(message);
	throw message;
	
}
function validate_input(element,message){
	if(element==undefined||element==""){
		alert(message);
		throw message;
	}
	
}


function getserverList(){
	  if ($("input[@name='region']:checked").val()==undefined ||$("input[@name='faction']:checked").val()==undefined){return ;}
      if (($("input[@name='region']:checked").val() == 'EU'||'US')&&($("input[@name='faction']:checked").val() == 'Alliance'||'Horde')){
            $("#server").attr('disabled', 'disabled'); 
      		$("div.resultsloading").show();
      		$.ajax({
      		  type: 'POST',
      		  url: "/serverList",
      		  data:"contryName="+$("input[@name='region']:checked").val()+"&factionName="+$("input[@name='faction']:checked").val(),
      		  cache: false,
      		  success: function(html){
      		    $("#server").val("");
				$("div.server-wrap").css("background-image","url('../images/server-bg.png')");
      		    var showhtml='['+html.substring(0,html.length-1)+']';
      		    $("div.resultsloading").hide();
      		    $("div.results").show();
      		    $("#server").flushCache();
      		    $("#server").autocomplete(eval(showhtml));
      		    
      		    var server_position=$("#server").position();
      		    var popupPanel_style="display: block; position: absolute; z-index: 100; top:"+server_position.top+";"+"left:"+server_position.left+";";
      		    $("div.popupPanel").attr("style",popupPanel_style);
      		    
      		    
      		    orginal=eval(showhtml);
              var hash=new Array();
              for(orginal_each in orginal)
              {
                    var achar=orginal[orginal_each].substring(0,1);
                    if(hash[achar]==undefined)
                    {
                      hash[achar]= new Array();
                    }
                    var he= hash[achar].push(orginal[orginal_each]);
               }
               $("#search").empty();
               var showhtml="";
               var count=0;
               for(i in hash)
               {
                    if(count%3==0){
                      if(count<2){
                        showhtml+="<div id='row'><div id ='firstcolumn'>";
                      }else{
                        showhtml+="</div><div id='row'><div id ='firstcolumn'>";
                      }
                      
                     
                    }else{
                      showhtml+="<div id ='column'>";
                    }
                    showhtml+="<div id ='alphabet'>"+hash[i][0].substring(0,1)+"</div>";
                    showhtml+="<ul class='search_ul'>"
                     for (j in hash[i])
                     {
                       showhtml+="<li><a href='' target='_blank'>"+hash[i][j]+"</a></li>";

                     }
                     showhtml+="</ul>";
                     showhtml+="</div>";
                     count++;
                }
                showhtml+="</div>"
                $("#search").append(showhtml);
                $("#server").popup($("div.popuplayer"));
                $("div.results").empty();
                $("#server").attr('disabled', "");
      		  }
      		});
      }		
      else{
        alert("NULL");
      }
        
 
  }

jQuery.fn.ForceNumericOnly =function(){
    return this.each(function()
    {
        $(this).keydown(function(e)
        {
            var key = e.charCode || e.keyCode || 0;
		    if(key==13){
			    calculate_price_by_custom_amount($("#customprice").val());
						
			}
            // allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
            return (
                key == 8 || 
                key == 9 ||
                key == 46 ||
                (key >= 37 && key <= 40) ||
                (key >= 48 && key <= 57) ||
                (key >= 96 && key <= 105));
        })
    })
};

