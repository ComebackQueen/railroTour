var formData=new FormData();
var words=new Array(); //워드클라우드 배열

$(document).ready(function(){

    (function( $ ) {
    "use strict";
    $(function() {
        function animated_contents() {
            $(".zt-skill-bar > div ").each(function (i) {
                var $this  = $(this),
                    skills = $this.data('width');

                $this.css({'width' : skills + '%'});

            });
        }
        
        if(jQuery().appear) {
            $('.zt-skill-bar').appear().on('appear', function() {
                animated_contents();
            });
        } else {
            animated_contents();
        }
    });
}(jQuery));
    
    $.ajax({
    	type:'POST',
    	url:'./Wordcloud.se',
    	data:{
    		contentid:getParameterByName('contentid'),
    		contenttypeid:getParameterByName('contenttypeid')
    	},
    	success:function(data){
    		for(var i=0; i<data.words.length; i++){
    			words.push({
    				text:data.words[i].word,
    				weight:Number(data.words[i].size)
    			});
    		}
    		if(data.words.length==0){
    			words.push({
    				text:'없음',
    				weight:50
    			});
    		}
    	     $(function() {
    	         $("#wordcloud").jQCloud(words);
    	     });
    	},
		error:function(request,status,error){
			console.log('워드 클라우드 완료');
			 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
    })
    
    $(document).on('click', '.page_num_group a', function(e){
    	e.preventDefault();
    	var num=$(this).text();
    	var contentid=getParameterByName('contentid');
    	var contenttypeid=getParameterByName('contenttypeid');
    	$('.reviews .review').remove();
    	$(this).addClass('selected');
    	$(this).siblings().removeClass('selected');
    	$.ajax({
    		type:'POST',
    		url:'./Review_Additional.se',
    		data:{
    			num:num,
    			contentid:contentid,
    			contenttypeid:contenttypeid
    		},
    		async:false,
    		dataType:"json",
    		success:function(data){
    			console.log('리뷰 가져옴');
    			
    			for(var i=0; i<data.reviews.length; i++){
    				var review='<li class="review">';
        			review+='<div class="date">';
        			review+='<img src="'+data.reviews[i].profileimg+'" alt="">';
        			review+='<span class="nikname">'+data.reviews[i].nikname+'</span>';
        			review+='<span class="datetime">'+data.reviews[i].datetime+'</span>';
        			review+='<button class="remove" data-num="'+data.reviews[i].review_num+'">삭제</button>';
        			if(Number(data.reviews[i].like_yn)!=0){
        				review+='<img src="./detail_info_img/like2.png" alt="" class="likeimg">';
        				review+='<span class="like"> - 좋아해요</span>';
        			}
        			else{
        				review+='<img src="./detail_info_img/like1.png" alt="" class="likeimg">';
        				review+='<span class="like"> - 싫어해요</span>';
        			}
        			review+='</div>';
        			review+='<div class="contents">';
        			review+=data.reviews[i].memo;
        			review+='</div>';
        			if(data.reviews[i].files.length!=0){
        			review+='<div class="imgs">';
        				for(var o=0; o<data.reviews[i].files.length; o++){
        					$.each(data.reviews[i].files[o], function(key, value){
        						review+='<img src="'+value+'" alt="" onclick="pop(this)">';
        					});
        				}
        			review+='</div>';
        			}
        			if(data.reviews[i].tags.length!=0){
        			review+='<div class="tags">';
        				for(var p=0; p<data.reviews[i].tags.length; p++){
        					$.each(data.reviews[i].tags[p], function(key, value){
        					    review+='<span class="tag">#'+value+'</span>';
        					});
        				}
        			review+='</div>';
        			}
        			review+='</li>';
        			
        			$('.reviews').append(review);
                    //console.log(review);
    			}
    		},
    		error:function(request,status,error){
    			console.log('리뷰 가져오기 실패');
    			 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    		}
    	})
    })
    
    $(document).on('click', '.review .date .remove', function(){
    	var num=$(this).data('num');
		var eq=$(this).parent().parent().index();
    	$.ajax({
    		type:'POST',
    		url:'./Review_Remove.se',
    		data:{
    			num:num,
    			contentid:getParameterByName('contentid'),
    			contenttypeid:getParameterByName('contenttypeid')
    		},
    		async: true,
    		success:function(data){
    			console.log('삭제완료');
    			$('.review').eq(eq).remove();
    		},
    		error:function(data){
    			console.log('리뷰삭제 실패');
    		}
    	})
    })
    
    $('input[type="text"]').keydown(function() {
        if (event.keyCode === 13) {
            event.preventDefault();
        }
    });
    
	$('#submit').on('click', function(){
		if(ID==null){
			alert('로그인 후 이용해주세요.');
			return;
		}
		if($('textarea').val()==''){
			alert('내용을 입력해주세요.');
			return;
		}
		formData.append('textarea', $('textarea').val().replace(/\n/g, '<br>'));
		formData.append('ID', ID);
		formData.append('contentid', getParameterByName('contentid'));
		formData.append('contenttypeid', getParameterByName('contenttypeid'));
        if($('#like').is(':checked')){
            formData.set('like', 1);
        }
        else{
            formData.set('like', 0);
        }
        
        $('textarea').val('');
        $('.comment .tags').remove();
        $('.preview').remove();
        $("input:checkbox[id='like']").prop("checked", false);
        $('.like_btn').attr('src', './detail_info_img/like1.png');
        $.ajax({
    		type:'POST',
    		url:'./Review_Add.se',
    		data:formData,
    		async: true,
            processData : false,
            contentType : false,
    		dataType:"json",
    		success:function(data){
    			formData=new FormData();
    			console.log('파일업로드 성공');
    			console.log(data.date);
    			if($('.reviews .review').length>4){
    				$('.reviews .review').last().remove();
    			}
    			var review='<li class="review">';
    			review+='<div class="date">';
    			review+='<img src="'+data.profile+'" alt="">';
    			review+='<span class="nikname">'+data.nikname+'</span>';
    			review+='<span class="datetime">'+data.date+'</span>';
    			review+='<button class="remove" data-num="'+data.review_num+'">삭제</button>';
    			if(Number(data.like_yn)!=0){
    				review+='<img src="./detail_info_img/like2.png" alt="" class="likeimg">';
    				review+='<span class="like"> - 좋아해요</span>';
    			}
    			else{
    				review+='<img src="./detail_info_img/like1.png" alt="" class="likeimg">';
    				review+='<span class="like"> - 싫어해요</span>';
    			}
    			review+='</div>';
    			review+='<div class="contents">';
    			review+=data.memo;
    			review+='</div>';
    			if(data.files.length!=0){
    			review+='<div class="imgs">';
    				for(var i=0; i<data.files.length; i++){
    					review+='<img src="'+data.files[i]+'" alt="" onclick="pop(this)">';
    				}
    			review+='</div>';
    			}
    			if(data.tags.length!=0){
    			review+='<div class="tags">';
    				for(var i=0; i<data.tags.length; i++){
    					review+='<span class="tag">#'+data.tags[i]+'</span>';
    				}
    			review+='</div>';
    			}
    			review+='</li>';
                console.log(review);
                
                $('.reviews').prepend(review);
                
    		},
    		error:function(data){
    			alert('파일 업로드 실패');
    			console.log('파일 업로드 실패');
    		}
    	})
    	

	})
    $('#like').on('change', function(){
        if($('#like').is(':checked')){
            $('.like_btn').attr('src', './detail_info_img/like2.png');
            formData.set('like', 1);
        }
        else{
            $('.like_btn').attr('src', './detail_info_img/like1.png');
            formData.set('like', 0);
        }
    })
    $('#comment_add textarea').on('focus', function(){
        var text=$(this).attr('placeholder');
        if(text=='로그인 후 이용가능합니다.'){
            alert('로그인 후 이용 가능합니다.');
            $(this).blur();
            location.reload();
        }
    })
    $(document).on('click', '.imgfile .preview',function(){
    	for(var i=$(this).index(); i<$('.preview').length; i++){
    		if(i>$(this).index()){
    			formData.set('file'+i-1, formData.get('file'+i));
    			console.log((i)+'에서'+(i-1)+'로 이동');
    		}
    		if(i==$('.preview').length-1){
    			formData.delete('file'+i);
    			console.log(i+'번 삭제');
    		}
    	}
        $(this).remove();
    })
    $('#img').on('change', function(){
        if($(this).val()!=""){
            var ext=$(this).val().split(".").pop().toLowerCase();
            if($.inArray(ext, ["gif", "jpg", "jpeg", "png"])==-1){
                swal('이미지 파일만 업로드 해주세요.');
                $(this).val('');
                return;
            }
            var fileSize=this.files[0].size;
            var maxSize=(1024*1024)*10;
            if(fileSize>maxSize){
                swal('파일용량 10MB를 초과했습니다.');
                $(this).val('');
                return;
            }
            
            if($('.imgfile .preview').length>4){
                swal('이미지는 5개까지만 첨부할 수 있습니다.');
                $(this).val('');
                return;
            }
            formData.append("file"+$('.preview').length, $('#img')[0].files[0]);
            readURL(this);
            $(this).val('');
        }
    })
    $(document).on('keydown', '.input_tag',function(key){
        if(key.keyCode==13){//엔터키가 들어오면
            if($('.comment .tags').length<10){
                var tag=$(this).val();
                if(tag!=''){
                	//formData.set('tag'+$('.tags').length, tag);

                    $('.tag ul .input_tag').before('<li class="tags">#'+tag+'<img src="./detail_info_img/cancel-button.png" alt=""></li>');
                    for(var i=0; i<$('.tag1 .tags').length; i++){
                    	formData.set('tag'+i, $('.tags').eq(i).text().substr(1));
                    	console.log('추가'+i);
                    }
                }
            }
            else{
                swal('태그는 최대 10개까지 등록할 수 있습니다.');
            }
            $(this).val('');
        }

    })
    $(document).on('click', '.tag1 .tags img', function(){
    	for(var i=0; i<$('.comment .tag1 .tags').length; i++){
    		formData.delete('tag'+i);
    		console.log('삭제'+i);
    	}
        $(this).parent().remove();
        for(var i=0; i<$('.comment .tags').length; i++){
        	formData.set('tag'+i, $('.tags').eq(i).text().substr(1));
        	console.log('추가'+i);
        }
    })
   $('.btn_group .Previous').on("click", function(){
       for(var i=0; i<$('.img_collection li').length; i++){
           if($('.img_collection li').eq(i).css('display')=='block'){
               $('.img_collection li').eq(i).fadeOut();
               $('.img_collection li').eq(i).css('display', 'none');
               if(i<1){
                   var num=$('.img_collection li').length;
                   $('.img_collection li').eq(num-1).fadeIn(500);
                   $('.img_collection li').eq(num-1).css('display', 'block');
               }
               else{
                   $('.img_collection li').eq(i-1).fadeIn(500);
                   $('.img_collection li').eq(i-1).css('display', 'block');
               }
               break;
           }
       }
   })
    $('.btn_group .Next').on("click", function(){
        for(var i=0; i<$('.img_collection li').length; i++){
           if($('.img_collection li').eq(i).css('display')=='block'){
               $('.img_collection li').eq(i).fadeOut();
               $('.img_collection li').eq(i).css('display', 'none');
               if(i==$('.img_collection li').length-1){
                   $('.img_collection li').eq(0).fadeIn(500);
                   $('.img_collection li').eq(0).css('display', 'block');
               }
               else{
                   $('.img_collection li').eq(i+1).fadeIn(500);
                   $('.img_collection li').eq(i+1).css('display', 'block');
               }
               break;
           }
       }
    })

})

function readURL(input){
    if(input.files&&input.files[0]){
        var reader=new FileReader();
        reader.onload=function(e){
            var vimg='<ul class="preview" style="background-image: url('+e.target.result+')"><li>삭제</li></ul>';
            $('#img').before(vimg);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


function pop(img) {
	 var win = window.open('', 'Detail', 'width=0, height=0, menubar=0, toolbar=0, directories=0, scrollbars=1, status=0, location=0, resizable=1');
	 op="<html><head><title>크게 보기</title></head>";
	 op+="<body leftmargin='0' topmargin='0'>";
	 op+="<img src='"+ img.src +"' border='0' style='cursor:pointer' onclick='window.close();' onload='window.resizeTo(this.width+30, this.height+90); window.moveTo( (screen.width-this.width)/2 ,  (screen.height-this.height)/2-50 )'>";
	 op+="</body></html>";

	 win.document.write(op);
	}