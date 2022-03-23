var scroll_position=0;
$(document).ready(function(){
    $('.kinds button').on('click', function(e){
    	alert('1');
    })
    $('#img').on('change', function(e){//파일 업로드 액션
        if($(this).val()!=""){
            var ext=$(this).val().split(".").pop().toLowerCase();

            //확장자 체크
            if($.inArray(ext, ["gif", "jpg", "jpeg", "png"])==-1){
                alert("이미지 파일만 업로드 해주세요.");
                $(this).val('');
                return;
            }
            
            //용량 체크
            var fileSize=this.files[0].size;
            var maxSize=(1024*1024)*10;
            if(fileSize>maxSize){
                alert('파일용량 10MB를 초과했습니다.');
                $(this).val('');
            }
            
            var formData=new FormData($('#ajaxform')[0]);
            formData.append("note_id", getParam('num'));
			//readURL(this);
            $.ajax({
        		type:'POST',
        		url:'./NoteImg_Change.pl',
        		data:formData,
        		async: true,
                processData : false,
                contentType : false,
        		success:function(data){
        			//alert('잠시 후 새로고침 해주시면 적용됩니다.');
        			location.reload();
        		},
        		error:function(data){
        			alert('파일 업로드 실패');
        			console.log('파일 업로드 실패');
        		}
        	})
            
        }
    }); 
    $('.select .view1').on('click', function(){
        $('.plan_route table').hide();
        $('.plan_route .plan_info').show();
        $('.plan_nav').show();
        $(this).css('color', '#006cff');
        $('.view2').css('color', 'black');
    })
    $('.select .view2').on('click', function(){
        $('.plan_route table').show();
        $('.plan_route .plan_info').hide();
        $('.plan_nav').hide();
        $('.view1').css('color', 'black');
        $(this).css('color', '#006cff');
    })
    $(window).scroll(function(event){
        for(var i=0; i<$('.plan_route .plan_info').length; i++){
            if($('.plan_route .plan_info').eq(i).offset().top-100<=$(this).scrollTop()){
                scroll_position=i;
                $('.nav_route li').eq(i).css('color', '#0093ff');
                $('.nav_route li').eq(i).siblings().css('color', '#898989');
            }
        }
    })
    var placeholder='메모가 없습니다';
    $(".post").focus(function() {
        if ($(this).text() == placeholder) {
            $(this).text("");
        }
    }).focusout(function() {
        if (!$(this).text().length) {
            $(this).text(placeholder);
        }
        $.ajax({
    		type:'POST',
    		url:'./NoteMemo_Save.pl',
    		data:{
    			Note_ID:getParam('num'),
    			orders:$(this).data('orders'),
    			day_orders:$(this).data('day_orders'),
    			memo:$(this).html()
    		},
    		async: true,
    		success:function(data){
    			console.log('메모 저장 성공');
    		},
    		error:function(data){
    			alert('메모 저장 실패');
    			console.log('메모 저장 실패');
    		}
    	})
    });
    $('.like').on('click', function(){
        var src=$('.like>img').attr('src');
        
        var Note_ID=getParam('num');
        
        var YN;
        if(src=='./mynote_jpg/footprint.png'){ //좋아요
            $('.like>img').attr('src', './mynote_jpg/footprint2.png');
            YN=1;
        }
        else{//좋아요 취소
            $('.like>img').attr('src', './mynote_jpg/footprint.png');
            YN=0;
        }
        
        $.ajax({
    		type:'POST',
    		url:'./NoteLike.pl',
    		data: {
    			Note_ID: Note_ID,
    			YN:YN
    		},
    		async: true,
    		success:function(data){

    		},
    		error:function(data){
    			alert('좋아요 실패');
    		}
    	})
        
    }) 

    $('.add_mynote').on('click', function(event){
         $("#dialog").dialog("open");
    })
    $('.follow').on('click', function(){
        var text=$(this).text();
        var Note_ID=getParam("num");
        var follow_YN;
        if(text=='팔로우+'){ //추가
            $(this).text('팔로워');
            $(this).css({
                'background-color':'#0076ff',
                'border-color':'white',
                'color':'white'
            });
            follow_YN=1;
        }
        else if(text=='팔로워'){ //취소
            $(this).text('팔로우+');
            $(this).css({
                'background-color':'white',
                'border-color':'#0076ff',
                'color':'#0076ff'
            });
            follow_YN=0;
        }
        
        $.ajax({
    		type:'POST',
    		url:'./MemberFollowAction.me',
    		data: {
    			Note_ID: Note_ID,
    			follow_YN:follow_YN
    		},
    		async: true,
    		success:function(data){

    		},
    		error:function(data){
    			alert('팔로우 실패');
    		}
    	})
    })
});


function fnMove(seq){ //네비게이션 이동 해당 day로 바로 이동
    scroll_position=seq;
    var offset=$('.plan_route .plan_info').eq(seq).offset();
    $('html, body').animate({scrollTop : offset.top}, 400);
    $('.nav_route li').eq(seq).css('color', '#0093ff');
    $('.nav_route li').eq(seq).siblings().css('color', '#898989');
}

function arrow_Move(seq){ //네비바 이동 위아래 이동
   scroll_position+=seq;
    if(scroll_position<0){
        alert('처음위치 입니다.');
        scroll_position=0;
        return;
    }
    else if(scroll_position>($('.nav_route li').length-1)){
        alert('마지막위치 입니다.');
        scroll_position=$('.nav_route li').length-1;
        return;
    }
    else{
        var offset=$('.plan_route .plan_info').eq(scroll_position).offset();
        $('html, body').animate({scrollTop : offset.top}, 400);
        $('.nav_route li').eq(scroll_position).css('color', '#0093ff');
        $('.nav_route li').eq(scroll_position).siblings().css('color', '#898989');
    }
}

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#note_img').css('background-image', 'url('+e.target.result+')');
        }

      reader.readAsDataURL(input.files[0]);
    }
}

//url 에서 parameter 추출
function getParam(sname) {

    var params = location.search.substr(location.search.indexOf("?") + 1);

    var sval = "";

    params = params.split("&");

    for (var i = 0; i < params.length; i++) {

        temp = params[i].split("=");

        if ([temp[0]] == sname) { sval = temp[1]; }

    }

    return sval;

}
