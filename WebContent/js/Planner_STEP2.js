var NoteName;
var day_array=new Array(0);
var day_marker_array=new Array(0);
var path=new Array(0);
var linePath;
var lineLine = new daum.maps.Polyline();
var polyline=new Array(0);

for(var i=0; i<$('.day_arrange>button').length; i++){
    day_array.push(new Array());
}

$('.area_name .area').text($('.day_arrange button:first-child .date_area').text());
var markerNum;
$(document).ready(function(){
	
	//초기 데이터 가져오는 부분
	$.ajax({
		type:'POST',
		url:'./Note_Plans_Info2_Import.pl',
		data: {
			NoteID: NoteID,
		},
		async: false,
		dataType:"json",
		success:function(data){
			for(var i=0; i<data.items.length; i++){
				day_array[data.items[i].day_orders].push({
					Content_ID:data.items[i].content_id,
					Content_Type_ID:data.items[i].content_type_id,
					sigungucode:data.items[i].sigungu_code,
					areacode:data.items[i].area_code,
					Title:data.items[i].route_name,
					Kind1:data.items[i].kinds_1,
					Kind2:data.items[i].kinds_2,
					img:data.items[i].img_src,
					latlng:new daum.maps.LatLng(data.items[i].mapx, data.items[i].mapy),
					lat:data.items[i].mapx,
					lng:data.items[i].mapy
				})
			}
			 //일정 추가시 해당 위치 표시 마커 처리
	        var Food_marker_Src="./daum_map/marker_img/Food_Select.png";
	        var Tour_marker_Src="./daum_map/marker_img/Tour_Select.png";
	        var Start_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png";
	        var End_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png";
	        
	        var imageSize = new daum.maps.Size(42, 43);
	        

	        for(var i=0; i<day_array[0].length; i++){
	        	var markerImage;
	        	if(i==0){ //출발지
	        		markerImage=new daum.maps.MarkerImage(Start_marker_Src, imageSize);
	        	}
	        	else if(i==(day_array[0].length-1)){//도착지
	        		markerImage=new daum.maps.MarkerImage(End_marker_Src, imageSize);
	        	}
	        	else if(day_array[0][i].Content_Type_ID==12){//관광지 이면
	        		markerImage=new daum.maps.MarkerImage(Tour_marker_Src, imageSize);
	        	}
	        	else if(day_array[0][i].Content_Type_ID==39){//음식점 이면
	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
	        	}
	        	else if(day_array[0][i].Content_Type_ID==0){//해시태그 이면
	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
	        	}
	        	day_marker_array.push(new daum.maps.Marker({
	    			map:map,
	    			position: new daum.maps.LatLng(day_array[0][i].lat, day_array[0][i].lng), 
	        		image: markerImage,
	        		title:day_array[0][i].Title
	    		}))
	        }
	        for (var i = 0; i < day_array[0].length; i++) {
	            if (i != 0) {
	                linePath = [ day_array[0][i - 1].latlng, day_array[0][i].latlng ] //라인을 그리려면 두 점이 있어야하니깐 두 점을 지정했습니다
	            }
	            
	            lineLine.setPath(linePath); // 선을 그릴 라인을 세팅합니다
	            if(i>0){
	            polyline.push(new daum.maps.Polyline({
	            	endArrow:true,
	                map : map, // 선을 표시할 지도입니다 
	                path : linePath,
	                strokeWeight : 3, // 선의 두께입니다 
	                strokeColor : '#db4040', // 선의 색깔입니다
	                strokeOpacity : 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	                strokeStyle : 'solid' // 선의 스타일입니다
	            }));
	            }
	        }
			display(day_array[0]);
		},
		error:function(data){
			alert('데이터를 가져오는데에 실패하였습니다.');
		}
	})
	
	
	$(document).on('mouseover', '.search_data', function(){ //검색된 데이터들 마우스 오버시 이벤트
		
		type_id=$(this).data('contenttypeid');
		var id=$(this).data('contentid');
		var lat=$(this).data('lat');
		var lng=$(this).data('lng');
		
		
		for(var i=0; i<positions.length; i++){
			if(positions[i].contentid==id){
				markerNum=i;
				break;
			}
		}
		
	
	    ItemoverImage = new daum.maps.MarkerImage('./daum_map/marker_img/hover.png', new daum.maps.Size(44, 46));
	    
		markers[markerNum].setImage(ItemoverImage);
	
	})
	$(document).on('mouseleave', '.search_data', function(){ //검색된 데이터들 마우스 아웃시 이벤트
		var normalImage;
		
	    if(Number(type_id)==12){
	    	normalImage = new daum.maps.MarkerImage(TourMarkerImg, imageSize);
	    }
	    else if(Number(type_id)==39){
	    	normalImage = new daum.maps.MarkerImage(FoodMarkerImg, imageSize);
	    }
		markers[markerNum].setImage(normalImage);
	})
	
    $('.hash_add>.hash_add_btn').on('click', function(){ //해시태그 장소 추가 이벤트
        var hash_tag=$(this).siblings('input').val();
        var select=$('.radio_group>input[name=kind]:checked').val();
        var memo=$('.hash_add>textarea').val();
        
        if(hash_tag==''){
            alert('해시태그를 입력해주세요.');
            return;
        }
        else if(select==undefined){
            alert('음식점, 관광지, 이동수단 중 하나를 선택하여 주세요');
            return;
        }
        
        //각 일차에 맞게 일정 추가
        var day_cnt=$('.day_arrange>button').length;

        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        
        var lat=$(".day_arrange>button").eq(num).data('lat');
        var lng=$(".day_arrange>button").eq(num).data('lng');
        
        sigungucode=$(".day_arrange>button").eq(num).data('docode');
        areacode=$(".day_arrange>button").eq(num).data('areacode');
        date=$(".day_arrange>button").eq(num).find('.date_num').text();
        week=$(".day_arrange>button").eq(num).find('.day_str').text();
        day=$(".day_arrange>button").eq(num).find('.day_num').text();
        areaname=$(".day_arrange>button").eq(num).find('.date_area').text();
        
        $.ajax({
    		type:'POST',
    		url:'./Note_Plans_Save.pl',
    		data: {
    			NoteID: NoteID,
    			Content_ID:0,
    			Content_Type_ID:0,
    			Title:hash_tag,
    			Kind1:'해시',
    			Kind2:select,
    			sigungucode:sigungucode,
    			areacode:areacode,
    			areaname:areaname,
    			date:date,
    			week:week,
    			day:day,
    			order:day_array[num].length,
    			day_orders:num,
    			memo:memo
    		},
    		async: false,
    		success:function(data){
    			
    			day_array[num].push({
    	            Content_ID:0,
    	            Content_Type_ID:0,
    	            Title:hash_tag,
    	            Kind1:'해시',
    	            Kind2:select,
    	            img:'./planner_Step2_JPG/hashtag.png',
    	            latlng : new daum.maps.LatLng(lat, lng),
    	            lat:lat,
    	            lng:lng,
    	            memo:memo,
    	            sigungucode:sigungucode,
    	            areacode:areacode,
    	            date:date,
    	            week:week,
    	            day:day,
    	            areaname:areaname
    	        })
    	        
    	      //일정 추가시 해당 위치 표시 마커 처리
    	        var Food_marker_Src="./daum_map/marker_img/Food_Select.png";
    	        var Tour_marker_Src="./daum_map/marker_img/Tour_Select.png";
    	        var Start_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png";
    	        var End_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png";
    	        
    	        var imageSize = new daum.maps.Size(42, 43);
    	        
    	        for(var i=0; i<day_marker_array.length; i++){
    	        	day_marker_array[i].setMap(null);
    	        }
    	        day_marker_array=new Array(0);
    	        for(var i=0; i<day_array[num].length; i++){
    	        	var markerImage;
    	        	if(i==0){ //출발지
    	        		markerImage=new daum.maps.MarkerImage(Start_marker_Src, imageSize);
    	        	}
    	        	else if(i==(day_array[num].length-1)){//도착지
    	        		markerImage=new daum.maps.MarkerImage(End_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==12){//관광지 이면
    	        		markerImage=new daum.maps.MarkerImage(Tour_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==39){//음식점 이면
    	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==0){//해시태그 이면
    	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
    	        	}
    	        	day_marker_array.push(new daum.maps.Marker({
    	    			map:map,
    	    			position: new daum.maps.LatLng(day_array[num][i].lat, day_array[num][i].lng), 
    	        		image: markerImage,
    	        		title:day_array[num][i].Title
    	    		}))
    	        }
    	        
    	        for(var i=0; i<polyline.length; i++){
    	        	polyline[i].setMap(null);
    	        }
    	        polyline=new Array(0);
    	        for (var i = 0; i < day_array[num].length; i++) {
    	            if (i != 0) {
    	                linePath = [ day_array[num][i - 1].latlng, day_array[num][i].latlng ] //라인을 그리려면 두 점이 있어야하니깐 두 점을 지정했습니다
    	            }
    	            
    	            lineLine.setPath(linePath); // 선을 그릴 라인을 세팅합니다
    	            if(i>0){
    	            polyline.push(new daum.maps.Polyline({
    	            	endArrow:true,
    	                map : map, // 선을 표시할 지도입니다 
    	                path : linePath,
    	                strokeWeight : 3, // 선의 두께입니다 
    	                strokeColor : '#db4040', // 선의 색깔입니다
    	                strokeOpacity : 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    	                strokeStyle : 'solid' // 선의 스타일입니다
    	            }));
    	            }
    	        }
    	        
    	        display(day_array[num]);
    		},
    		error:function(data){
    			alert('일정 초기화에 실패하였습니다.');
    		}
    	})

        $(this).siblings('input').val('');
        $('.hash_add>textarea').val('');
    })
    
    $('.radio_group>label').on("click", function(){ //버튼 이벤트
        $(this).find('img').css('border', '1px solid black');
        $(this).siblings().find('img').css('border', '1px solid white');
    })
    
    var day, index, num;
    $(".edit_route #route_add").sortable({ //드래그 앤 드랍 이벤트
      axis: 'y',
        start:function(event, ui){
            index=ui.item.index();
                /* 각 Day에 맞게 일정 추가 */
            var day_cnt=$('.day_arrange>button').length;

            var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드

            for(var i=0; i<day_cnt; i++){
                compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
                if(Color_Hex==compareColor){
                    num=i;
                    break;
                }
            }
        },
        stop:function(event, ui){
            var save;
            /* day_array 2차원 배열에 들어가 있는 것들
             		Content_ID:Content_ID,
    	            Content_Type_ID:Content_Type_ID,
    	            sigungucode:sigungucode,
    	            areacode:areacode,
    	            areaname:areaname,
    	            date:date,
    	            week:week,
    	            day:day,
    	            Title:Title,
    	            Kind1:'일반',
    	            Kind2:Kind2,
    	            img:image,
    	            latlng : new daum.maps.LatLng(lat, lng),
    	            lat:lat,
    	            lng:lng
             */
            if(ui.item.index()<index){
                for(var i=index; ui.item.index()<i; i--){
                    save=day_array[num][i];
                    day_array[num][i]=day_array[num][i-1];
                    day_array[num][i-1]=save;
                    
                }
            }
            else{
                for(var i=index; i<ui.item.index(); i++){
                    save=day_array[num][i];
                    day_array[num][i]=day_array[num][i+1];
                    day_array[num][i+1]=save;
                    
                }
            }
            
            $.ajax({
        		type:'GET',
        		url:'./Note_Plans_Update.pl',
        		async: false,
        		data: {
        			StartIndex:index,
        			EndIndex:ui.item.index(),
        			NoteID:NoteID,
        			day_orders:num,
                    sigungucode:$(".day_arrange>button").eq(num).data('docode'),
                	areacode:$(".day_arrange>button").eq(num).data('areacode'),
                	date:$(".day_arrange>button").eq(num).find('.date_num').text(),
                	day:$(".day_arrange>button").eq(num).find('.day_num').text(),
                	Content_ID:$('#route_add .route').eq(ui.item.index()).data('id'),
                	Content_Type_ID:$('#route_add .route').eq(ui.item.index()).data('type'),
        		},
        		async: false,
        		success:function(data){	
        			
        			var Food_marker_Src="./daum_map/marker_img/Food_Select.png";
        	        var Tour_marker_Src="./daum_map/marker_img/Tour_Select.png";
        	        var Start_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png";
        	        var End_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png";
        	        for(var i=0; i<day_marker_array.length; i++){
        	        	day_marker_array[i].setMap(null);
        	        }
        	        day_marker_array=new Array(0);
        	        for(var i=0; i<day_array[num].length; i++){
        	        	var markerImage;
        	        	if(i==0){ //출발지
        	        		markerImage=new daum.maps.MarkerImage(Start_marker_Src, imageSize);
        	        	}
        	        	else if(i==(day_array[num].length-1)){//도착지
        	        		markerImage=new daum.maps.MarkerImage(End_marker_Src, imageSize);
        	        	}
        	        	else if(day_array[num][i].Content_Type_ID==12){//관광지 이면
        	        		markerImage=new daum.maps.MarkerImage(Tour_marker_Src, imageSize);
        	        	}
        	        	else if(day_array[num][i].Content_Type_ID==39){//음식점 이면
        	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
        	        	}
        	        	else if(day_array[num][i].Content_Type_ID==0){//해시태그 이면
        	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
        	        	}
        	        	day_marker_array.push(new daum.maps.Marker({
    	        			map:map,
    	        			position: new daum.maps.LatLng(day_array[num][i].lat, day_array[num][i].lng), 
    	            		image: markerImage,
    	            		title:day_array[num][i].Title
    	        		}))
        	        }
        			for(var i=0; i<polyline.length; i++){ //일정 마커 선 지우기
        	        	polyline[i].setMap(null);
        	        }
        			
        			  polyline=new Array(0);
        			  
        			for (var i = 0; i < day_array[num].length; i++) {
        	            if (i != 0) {
        	                linePath = [ day_array[num][i - 1].latlng, day_array[num][i].latlng ] //라인을 그리려면 두 점이 있어야하니깐 두 점을 지정했습니다
        	            }
        	            
        	            lineLine.setPath(linePath); // 선을 그릴 라인을 세팅합니다
        	            if(i>0){
        	            polyline.push(new daum.maps.Polyline({
        	            	endArrow:true,
        	                map : map, // 선을 표시할 지도입니다 
        	                path : linePath,
        	                strokeWeight : 3, // 선의 두께입니다 
        	                strokeColor : '#db4040', // 선의 색깔입니다
        	                strokeOpacity : 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        	                strokeStyle : 'solid' // 선의 스타일입니다
        	            }));
        	            }
        	        }
        	      
        	        
        	        display(day_array[num]);
        		},
        		error:function(request,status,error){
        	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        	    }
        	}).disableSelection();
        	
        }
    });
    
    $(document).on("click", ".search_data .img",function(){ //추가 정보 이벤트
        var content_id=$(this).parent().data("contentid");
        var content_type_id=$(this).parent().data("contenttypeid");
        
        $.ajax({
        	type:'post',
        	url:'./Note_More_Info.pl',
        	data:{
        		contentid:content_id,
        		contenttypeid:content_type_id
        	},
        	dataType:"json",
        	async: false,
    		success:function(data){
    			/*
    			 * JSON 만든 것
    			 * Content_id = 고유번호
    			 * Content_type_id = 타입 번호(관광지/음식점)
    			 * Title = 제목
    			 * Address = 주소
    			 * Overview = 개요
    			 * Infocenter = 전화번호
    			 * Mainimg= 메인이미지
    			 * Opentime = 오픈시각
    			 * Mainmenu=메인메뉴
    			 */
    			$('.More_Info').attr('data-contentid', data.Content_id);
    			$('.More_Info').attr('data-contenttypeid', data.Content_type_id);
    			$('.info_scrolling>.thumbnail').attr('src', data.Mainimg); //메인 이미지
    			$('.Main_Title>h1').text(data.Title); //제목
    			$('.info_scrolling .info').html(data.Overview); //내용
    			$('.description .phone').html(data.Infocenter); //전화번호
    			$('.description .addr').text(data.Address); //주소
    			
    			if(data.Content_type_id==12){
    				$('.description .open').css('display', 'none');
    				$('.description .open_time').css('display', 'none');
    				$('.description .menu').css('display', 'none');
    				$('.description .menu_list').css('display', 'none');
    			}
    			else if(data.Content_type_id==39){
    				$('.description .open_time').text(data.Opentime); //영업시간
    				$('.description .menu_list').html(data.Mainmenu); //메인메뉴
    				$('.description .open').css('display', 'block');
    				$('.description .open_time').css('display', 'block');
    				$('.description .menu').css('display', 'block');
    				$('.description .menu_list').css('display', 'block');
    			}
    		},
    		error:function(request,status,error){
    	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	    }
        })
        
        $(".More_Info").animate({"margin-right":'+=500'});
    })
    $(".More_Info>.top>.right").on("click", function(){ // 추가정보 닫기 이벤트
        $(".More_Info").animate({"margin-right":'-=500'});
    })
    $(".day_group>.refresh").on("click", function(){ //일정 초기화
        /* 각 Day에 맞게 일정 추가 */
        var day_cnt=$('.day_arrange>button').length;

        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        var input=confirm('해당 일정을 초기화 하시겠습니까?');
        
        if(input==true){
            
            $.ajax({
        		type:'POST',
        		url:'./Note_Plans_RESET.pl',
        		data: {
        			NoteID: NoteID,
        			day_orders:num
        		},
        		async: false,
        		success:function(data){
        			for(var i=0; i<polyline.length; i++){
        				polyline[i].setMap(null);
        			}
        			
        			polyline=new Array(0);
        			
        			day_array[num]=new Array(0); //일정 리스트 초기화
        			
        			for(var i=0; i<day_marker_array.length; i++){ //표시된 마커 지우기
        				day_marker_array[i].setMap(null);
        			}
        			
        			day_marker_array=new Array(0); //일정 마커리스트 초기화
        			display(day_array[num]);
        		},
        		error:function(data){
        			alert('일정 초기화에 실패하였습니다.');
        		}
        	})
        }

    })
    
    $(document).on("click", ".add_btn>.route_add_btn",function(){ //일정 추가 이벤트

        /* 각 Day에 맞게 일정 추가 */
        var day_cnt=$('.day_arrange>button').length;
//        alert($(".day_arrange>button").eq(0).find(".date_area").text());
        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        
        // 콘텐츠 ID, 이름, 종류   
        sigungucode=$(".day_arrange>button").eq(num).data('docode');
        areacode=$(".day_arrange>button").eq(num).data('areacode');
        areaname=$(".day_arrange>button").eq(num).find('.date_area').text();
        date=$(".day_arrange>button").eq(num).find('.date_num').text();
        week=$(".day_arrange>button").eq(num).find('.day_str').text();
        day=$(".day_arrange>button").eq(num).find('.day_num').text();
        Content_ID=$(this).parent().parent().data('contentid');
        Content_Type_ID=$(this).parent().parent().data('contenttypeid');
        Title=$(this).parent().siblings('.info_group').find('.title').text();
        Kind2=$(this).parent().siblings('.info_group').find('.sub_title').text();
        image=$(this).parent().siblings('.img').find('img').attr('src');
        lat=$(this).parent().parent().data('lat');
        lng=$(this).parent().parent().data('lng');
        
        
        $.ajax({
    		type:'POST',
    		url:'./Note_Plans_Save.pl',
    		data: {
    			NoteID: NoteID,
    			Content_ID:Content_ID,
    			Content_Type_ID:Content_Type_ID,
    			Title:Title,
    			Kind1:'일반',
    			Kind2:Kind2,
    			sigungucode:sigungucode,
    			areacode:areacode,
    			areaname:areaname,
    			date:date,
    			week:week,
    			day:day,
    			order:day_array[num].length,
    			day_orders:num
    		},
    		async: false,
    		success:function(data){

    	        day_array[num].push({
    	            Content_ID:Content_ID,
    	            Content_Type_ID:Content_Type_ID,
    	            sigungucode:sigungucode,
    	            areacode:areacode,
    	            areaname:areaname,
    	            date:date,
    	            week:week,
    	            day:day,
    	            Title:Title,
    	            Kind1:'일반',
    	            Kind2:Kind2,
    	            img:image,
    	            latlng : new daum.maps.LatLng(lat, lng),
    	            lat:lat,
    	            lng:lng
    	        })
    	        
    	        //일정 추가시 해당 위치 표시 마커 처리
    	        var Food_marker_Src="./daum_map/marker_img/Food_Select.png";
    	        var Tour_marker_Src="./daum_map/marker_img/Tour_Select.png";
    	        var Start_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png";
    	        var End_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png";
    	        
    	        var imageSize = new daum.maps.Size(42, 43);
    	        
    	        for(var i=0; i<day_marker_array.length; i++){
    	        	day_marker_array[i].setMap(null);
    	        }
    	        day_marker_array=new Array(0);
    	        for(var i=0; i<day_array[num].length; i++){
    	        	var markerImage;
    	        	if(i==0){ //출발지
    	        		markerImage=new daum.maps.MarkerImage(Start_marker_Src, imageSize);
    	        	}
    	        	else if(i==(day_array[num].length-1)){//도착지
    	        		markerImage=new daum.maps.MarkerImage(End_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==12){//관광지 이면
    	        		markerImage=new daum.maps.MarkerImage(Tour_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==39){//음식점 이면
    	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
    	        	}
    	        	
    	        	day_marker_array.push(new daum.maps.Marker({
	        			map:map,
	        			position: new daum.maps.LatLng(day_array[num][i].lat, day_array[num][i].lng), 
	            		image: markerImage,
	            		title:day_array[num][i].Title,
	            		zIndex:9999
	        		}))
    	        }
    	        
    	        for(var i=0; i<polyline.length; i++){
    	        	polyline[i].setMap(null);
    	        }
    	        polyline=new Array(0);
    	        for (var i = 0; i < day_array[num].length; i++) {
    	            if (i != 0) {
    	                linePath = [ day_array[num][i - 1].latlng, day_array[num][i].latlng ] //라인을 그리려면 두 점이 있어야하니깐 두 점을 지정했습니다
    	            }
    	            
    	            lineLine.setPath(linePath); // 선을 그릴 라인을 세팅합니다
    	            if(i>0){
    	            polyline.push(new daum.maps.Polyline({
    	            	endArrow:true,
    	                map : map, // 선을 표시할 지도입니다 
    	                path : linePath,
    	                strokeWeight : 3, // 선의 두께입니다 
    	                strokeColor : '#db4040', // 선의 색깔입니다
    	                strokeOpacity : 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    	                strokeStyle : 'solid' // 선의 스타일입니다
    	            }));
    	            }
    	        }
    	        
    	        display(day_array[num]);
    		},
    		error:function(data){
    			alert('추가에 실패하였습니다.');
    			return;
    		}
    	})
       
        
    })
    
    $(".Note_title").on("click", function(){ //클릭시 수정할 수 있는 폼으로 변환
        NoteName=$(this).text();
        Save_Note_Name=$(this).text();
        
        $(this).css("display", "none");
        $(".Note_edit_title .Note_title_input").val(NoteName);
        $(".Note_edit_title").css("display", "block");
    })
    $(".Note_title_submit").on("click", function(){ //수정폼에서 확정 폼
        NoteName=$(this).siblings(".Note_title_input").val();

        if(NoteName==""){
            alert("공백은 입력하실 수 없습니다");
            return false;
        }
        else if(NoteName!=Save_Note_Name){
        	$.ajax({
        		type:'get',
        		url:'./Note_Name_Update.pl',
        		data: {
        			NoteName: NoteName,
        			NoteID: NoteID
        		},
        		success:function(data){
        			alert('전송완료');
        		},
        		error:function(data){
        			alert('변경에 실패하였습니다.');
        			return;
        		}
        	})
        }
        
        $(this).parent().css("display", "none");
        $(".Note_title").text(NoteName);
        $(".Note_title").css("display", "block");
    })
    $(".day_arrange>button").on("click", function(){ //일정 변경 이벤트
        for(var i=0; i<overlay.length; i++){ //커스텀 오버레이 모두 다 닫기
        	overlay[i].setMap(null);
        }
        
        for(var i=0; i<markers.length; i++){ //마커 표시 지우기
      	   markers[i].setMap(null);
        }
        for(var i=0; i<day_marker_array.length; i++){ //일정 마커 표시 지우기
        	day_marker_array[i].setMap(null);
        }
        for(var i=0; i<polyline.length; i++){ //일정 마커 선 지우기
        	polyline[i].setMap(null);
        }
        polyline=new Array(0);
        
        day_marker_array=new Array(0);
        
    	$('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL2.png');
        $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
        $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
        $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
        $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
    	
        var position=$(this).index();
        
        $(this).css("backgroundColor", "#1a7ad9");
        $(this).siblings().css("backgroundColor", "#203341");
        
        day_num=$(this).find(".day_num").text(); //DAY 숫자
        day_str=$(this).find(".day_str").text(); //요일
        date_num=$(this).find(".date_num").text(); //날짜
        date_area=$(this).find(".date_area").text(); //지역명
        lat=$(this).data('lat');//위도
        lng=$(this).data('lng');//경도
        
        map.panTo(new daum.maps.LatLng(lat, lng));
        
        $(".area_name>ul>.area").text(date_area);
        $(".day_group>.day").text(day_num);
        $(".day_group>.date").text(date_num);
        $(".day_group>.week").text('('+day_str+')');
        
        
        display(day_array[position]);
        
        /* 각 Day에 맞게 일정 추가 */
        var day_cnt=$('.day_arrange>button').length;
//        alert($(".day_arrange>button").eq(0).find(".date_area").text());
        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        
        document.getElementById('search_data').innerHTML='';

        
        positions=[];
        overlay=[];
        markers=[];
        
        var sigungucode=$(".day_arrange>button").eq(num).data('docode');
        var areacode=$(".day_arrange>button").eq(num).data('areacode');
        

        $.ajax({
        	type:'post',
        	url:'./Note_Filter_Search.pl',
        	data:{
        		contenttypeid:0,
        		sigungucode:sigungucode,
        		areacode:areacode
        	},
        	dataType:"json",
        	async: false,
        	success:function(data){
        		for (var i = 0; i < day_array[num].length; i++) {
    	            if (i != 0) {
    	                linePath = [ day_array[num][i - 1].latlng, day_array[num][i].latlng ] //라인을 그리려면 두 점이 있어야하니깐 두 점을 지정했습니다
    	            }
    	            
    	            lineLine.setPath(linePath); // 선을 그릴 라인을 세팅합니다
    	            if(i>0){
    	           	polyline.push(new daum.maps.Polyline({
    	           		endArrow:true,
    	           		map : map, // 선을 표시할 지도입니다 
    	           		path : linePath,
    	            	strokeWeight : 3, // 선의 두께입니다 
    	            	strokeColor : '#db4040', // 선의 색깔입니다
    	            	strokeOpacity : 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    	            	strokeStyle : 'solid' // 선의 스타일입니다
    	           	}));
    	            }
    	        }
        		
        		$.each(data, function(key, value) {
        		    if(key=='item'){
        		    	search_data=value;

        		    }
        		    else if(key=='totalCount'){
        		    	totalCount=Number(value);
        		    }
        		    
        		});
        		
        		for(var i=0; i<Number(totalCount); i++){
        			document.getElementById('search_data').innerHTML+=append(data.item[i].areacode, data.item[i].sigungucode, data.item[i].cat1, data.item[i].cat2, data.item[i].cat3, data.item[i].contentid, data.item[i].contenttypeid, data.item[i].lat, data.item[i].lng, data.item[i].addr1, data.item[i].addr2, data.item[i].title, data.item[i].firstimage);
        		
        			var title=data.item[i].title;
            		var lat=data.item[i].lat;
            		var lng=data.item[i].lng;
            		var type=data.item[i].contenttypeid;
            		var id=data.item[i].contentid;
            		var addr1=data.item[i].addr1;
            		var addr2=data.item[i].addr2;
            		var img=data.item[i].firstimage;
            		
            		if(addr2==null)
            			addr2="";
            		
            		positions.push({
            			title:title,
            			latlng: new daum.maps.LatLng(lat, lng),
            			contenttypeid:type,
            			contentid:id,
            			addr1:addr1,
            			addr2:addr2,
            			img:img
            		});
        		}
        		
                for(var i=0; i<positions.length; i++){
            		// 마커 이미지의 이미지 크기 입니다
            	    var imageSize = new daum.maps.Size(42, 43); 
            	    var markerImage;
            	    // 마커 이미지를 생성합니다    
            	    if(positions[i].contenttypeid==12){ //해당 위치가 관광지이면
            	    	markerImage = new daum.maps.MarkerImage(TourMarkerImg, imageSize); 
            	    }
            	    else if(positions[i].contenttypeid==39){ //해당 위치가 음식점이면
            	    	markerImage = new daum.maps.MarkerImage(FoodMarkerImg, imageSize);
            	    }
            	    
            	    markers.push(addMarker(positions[i].latlng, positions[i].contenttypeid, markerImage, positions[i].title, i, positions[i].contentid, positions[i].img, positions[i].addr1, positions[i].addr2));
            	}
                var Food_marker_Src="./daum_map/marker_img/Food_Select.png";
    	        var Tour_marker_Src="./daum_map/marker_img/Tour_Select.png";
    	        var Start_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png";
    	        var End_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png";
    	        for(var i=0; i<day_marker_array.length; i++){
    	        	day_marker_array[i].setMap(null);
    	        }
    	        day_marker_array=new Array(0);
    	        for(var i=0; i<day_array[num].length; i++){
    	        	var markerImage;
    	        	if(i==0){ //출발지
    	        		markerImage=new daum.maps.MarkerImage(Start_marker_Src, imageSize);
    	        	}
    	        	else if(i==(day_array[num].length-1)){//도착지
    	        		markerImage=new daum.maps.MarkerImage(End_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==12){//관광지 이면
    	        		markerImage=new daum.maps.MarkerImage(Tour_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==39){//음식점 이면
    	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
    	        	}
    	        	
    	        	day_marker_array.push(new daum.maps.Marker({
	        			map:map,
	        			position: new daum.maps.LatLng(day_array[num][i].lat, day_array[num][i].lng), 
	            		image: markerImage,
	            		title:day_array[num][i].Title
	        		}))
    	        }
                
        	},
    		error:function(request,status,error){
    	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	    }
        })
    })
    $(".kind_select>div").on("click", function(){
        var class_name=$(this).attr('class');
        var totalCount=0;
        /* 각 Day에 맞게 일정 추가 */
        var day_cnt=$('.day_arrange>button').length;
//        alert($(".day_arrange>button").eq(0).find(".date_area").text());
        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        
        
        
        
        
        document.getElementById('search_data').innerHTML='';
        
        var sigungucode=$(".day_arrange>button").eq(num).data('docode');
        var areacode=$(".day_arrange>button").eq(num).data('areacode');
        
        var search_data;
        
        for(var i=0; i<overlay.length; i++){ //커스텀 오버레이 모두 다 닫기
        	overlay[i].setMap(null);
        }
        
        positions=[];
        overlay=[];
        
        
        if(class_name=='all'){
            $(this).find('img').attr('src', './planner_Step2_JPG/ALL2.png');
            $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
            $('.search_result>.all').css('display', 'block');
            $('.search_result>.all').siblings().css('display', 'none');

            $.ajax({
            	type:'post',
            	url:'./Note_Filter_Search.pl',
            	data:{
            		contenttypeid:0,
            		sigungucode:sigungucode,
            		areacode:areacode
            	},
            	dataType:"json",
            	async: false,
            	success:function(data){
            		$.each(data, function(key, value) {
            		    if(key=='item'){
            		    	search_data=value;

            		    }
            		    else if(key=='totalCount'){
            		    	totalCount=Number(value);
            		    }
            		});
            		for(var i=0; i<Number(totalCount); i++){
            			document.getElementById('search_data').innerHTML+=append(data.item[i].areacode, data.item[i].sigungucode, data.item[i].cat1, data.item[i].cat2, data.item[i].cat3, data.item[i].contentid, data.item[i].contenttypeid, data.item[i].lat, data.item[i].lng, data.item[i].addr1, data.item[i].addr2, data.item[i].title, data.item[i].firstimage);
            		
            			var title=data.item[i].title;
                		var lat=data.item[i].lat;
                		var lng=data.item[i].lng;
                		var type=data.item[i].contenttypeid;
                		var id=data.item[i].contentid;
                		var addr1=data.item[i].addr1;
                		var addr2=data.item[i].addr2;
                		var img=data.item[i].firstimage;
                		
                		if(addr2==null)
                			addr2="";
                		
                		positions.push({
                			title:title,
                			latlng: new daum.maps.LatLng(lat, lng),
                			contenttypeid:type,
                			contentid:id,
                			addr1:addr1,
                			addr2:addr2,
                			img:img
                		});
            		}
            		
                    for(var i=0; i<markers.length; i++){
                 	   markers[i].setMap(null);
                    }
                    markers=[];
                    for(var i=0; i<positions.length; i++){
                		// 마커 이미지의 이미지 크기 입니다
                	    var imageSize = new daum.maps.Size(42, 43); 
                	    var markerImage;
                	    // 마커 이미지를 생성합니다    
                	    if(positions[i].contenttypeid==12){ //해당 위치가 관광지이면
                	    	markerImage = new daum.maps.MarkerImage(TourMarkerImg, imageSize); 
                	    }
                	    else if(positions[i].contenttypeid==39){ //해당 위치가 음식점이면
                	    	markerImage = new daum.maps.MarkerImage(FoodMarkerImg, imageSize);
                	    }
                	    
                	    markers.push(addMarker(positions[i].latlng, positions[i].contenttypeid, markerImage, positions[i].title, i, positions[i].contentid, positions[i].img, positions[i].addr1, positions[i].addr2));
                	}
            		
            	},
        		error:function(request,status,error){
        	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        	    }
            })
        }
        else if(class_name=='camera'){
            $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/camera2.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
            $('.search_result>.all').css('display', 'block');
            $('.search_result>.all').siblings().css('display', 'none');
            $.ajax({
            	type:'post',
            	url:'./Note_Filter_Search.pl',
            	data:{
            		contenttypeid:12,
            		sigungucode:sigungucode,
            		areacode:areacode
            	},
            	dataType:"json",
            	async: false,
            	success:function(data){
            		$.each(data, function(key, value) {
            		    if(key=='item'){
            		    	search_data=value;

            		    }
            		    else if(key=='totalCount'){
            		    	totalCount=Number(value);
            		    }
            		});
            		for(var i=0; i<Number(totalCount); i++){
            			document.getElementById('search_data').innerHTML+=append(data.item[i].areacode, data.item[i].sigungucode, data.item[i].cat1, data.item[i].cat2, data.item[i].cat3, data.item[i].contentid, data.item[i].contenttypeid, data.item[i].lat, data.item[i].lng, data.item[i].addr1, data.item[i].addr2, data.item[i].title, data.item[i].firstimage);
            		
            			var title=data.item[i].title;
                		var lat=data.item[i].lat;
                		var lng=data.item[i].lng;
                		var type=data.item[i].contenttypeid;
                		var id=data.item[i].contentid;
                		var addr1=data.item[i].addr1;
                		var addr2=data.item[i].addr2;
                		var img=data.item[i].firstimage;
                		
                		if(addr2==null)
                			addr2="";
                		
                		positions.push({
                			title:title,
                			latlng: new daum.maps.LatLng(lat, lng),
                			contenttypeid:type,
                			contentid:id,
                			addr1:addr1,
                			addr2:addr2,
                			img:img
                		});
            		}
            		
                    for(var i=0; i<markers.length; i++){
                 	   markers[i].setMap(null);
                    }
                    markers=[];
                    for(var i=0; i<positions.length; i++){
                		// 마커 이미지의 이미지 크기 입니다
                	    var imageSize = new daum.maps.Size(42, 43); 
                	    var markerImage;
                	    // 마커 이미지를 생성합니다    
                	    if(positions[i].contenttypeid==12){ //해당 위치가 관광지이면
                	    	markerImage = new daum.maps.MarkerImage(TourMarkerImg, imageSize); 
                	    }
                	    else if(positions[i].contenttypeid==39){ //해당 위치가 음식점이면
                	    	markerImage = new daum.maps.MarkerImage(FoodMarkerImg, imageSize);
                	    }
                	    
                	    markers.push(addMarker(positions[i].latlng, positions[i].contenttypeid, markerImage, positions[i].title, i, positions[i].contentid, positions[i].img, positions[i].addr1, positions[i].addr2));
                	}
            		
            	},
        		error:function(request,status,error){
        	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        	    }
            })

        }
        else if(class_name=='food'){
            $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
            $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/food2.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
            $('.search_result>.all').css('display', 'block');
            $('.search_result>.all').siblings().css('display', 'none');
            $.ajax({
            	type:'post',
            	url:'./Note_Filter_Search.pl',
            	data:{
            		contenttypeid:39,
            		sigungucode:sigungucode,
            		areacode:areacode
            	},
            	dataType:"json",
            	async: false,
            	success:function(data){
            		$.each(data, function(key, value) {
            		    if(key=='item'){
            		    	search_data=value;

            		    }
            		    else if(key=='totalCount'){
            		    	totalCount=Number(value);
            		    }
            		});
            		for(var i=0; i<Number(totalCount); i++){
            			document.getElementById('search_data').innerHTML+=append(data.item[i].areacode, data.item[i].sigungucode, data.item[i].cat1, data.item[i].cat2, data.item[i].cat3, data.item[i].contentid, data.item[i].contenttypeid, data.item[i].lat, data.item[i].lng, data.item[i].addr1, data.item[i].addr2, data.item[i].title, data.item[i].firstimage);
            		
            			var title=data.item[i].title;
                		var lat=data.item[i].lat;
                		var lng=data.item[i].lng;
                		var type=data.item[i].contenttypeid;
                		var id=data.item[i].contentid;
                		var addr1=data.item[i].addr1;
                		var addr2=data.item[i].addr2;
                		var img=data.item[i].firstimage;
                		
                		if(addr2==null)
                			addr2="";
                		
                		positions.push({
                			title:title,
                			latlng: new daum.maps.LatLng(lat, lng),
                			contenttypeid:type,
                			contentid:id,
                			addr1:addr1,
                			addr2:addr2,
                			img:img
                		});
            		}
            		
                    for(var i=0; i<markers.length; i++){
                 	   markers[i].setMap(null);
                    }
                    markers=[];
                    for(var i=0; i<positions.length; i++){
                		// 마커 이미지의 이미지 크기 입니다
                	    var imageSize = new daum.maps.Size(42, 43); 
                	    var markerImage;
                	    // 마커 이미지를 생성합니다    
                	    if(positions[i].contenttypeid==12){ //해당 위치가 관광지이면
                	    	markerImage = new daum.maps.MarkerImage(TourMarkerImg, imageSize); 
                	    }
                	    else if(positions[i].contenttypeid==39){ //해당 위치가 음식점이면
                	    	markerImage = new daum.maps.MarkerImage(FoodMarkerImg, imageSize);
                	    }
                	    
                	    markers.push(addMarker(positions[i].latlng, positions[i].contenttypeid, markerImage, positions[i].title, i, positions[i].contentid, positions[i].img, positions[i].addr1, positions[i].addr2));
                	}
            		
            	},
        		error:function(request,status,error){
        	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        	    }
            })
        }
        else if(class_name=='cart'){
            $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
            $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/cart2.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
            $('.search_result>.all').html('');
            $('.search_result>.all').css('display', 'block');
            $('.search_result>.all').siblings().css('display', 'none');
            for(var i=0; i<markers.length; i++){
          	   markers[i].setMap(null);
             }
        }
        else if(class_name=='tag'){
            $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
            $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/tag2.png');
            $('.search_result>.hash_add').css('display', 'block');
            $('.search_result>.all').html('');
            $('.search_result>.hash_add').siblings().css('display', 'none');
            for(var i=0; i<markers.length; i++){
           	   markers[i].setMap(null);
              }
        }
        
  
    })
    $('.search_form .area_search').keydown(function(key){ //검색 이벤트
        
        if (key.keyCode == 13) { //엔터 누를 시 검색시작
            var keyword=$(this).val();
            var day_cnt=$('.day_arrange>button').length;

            var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
            var num; // 선택되어 있는 index 번호저장
            for(var i=0; i<day_cnt; i++){
                compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
                if(Color_Hex==compareColor){
                    num=i;
                    break;
                }
            }
            
            var sigungucode=$(".day_arrange>button").eq(num).data('docode');
            var areacode=$(".day_arrange>button").eq(num).data('areacode');
            var search_type=$('input:radio[name="search_type"]:checked').val()
            
            $.ajax({
                type : "POST",
                url : "./Note_Place_Search.pl",
                data:{
                	sigungucode: sigungucode,
                	areacode: areacode,
                	keyword:keyword,
                	search_type:search_type
                },
                async: false,
        		error:function(request,status,error){
        	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        	    },
                success : function(data){
                	
                	document.getElementById('search_data').innerHTML=''; //비우기
                	
                    for(var i=0; i<overlay.length; i++){ //커스텀 오버레이 모두 다 닫기
                    	overlay[i].setMap(null);
                    }
                    
                    positions=[]; //위치들 초기화
                    overlay=[]; //오버레이 초기화
                	
                    var search_data, totalCount;
                    
                	$.each(data, function(key, value) {
            		    if(key=='item'){
            		    	search_data=value;
            		    }
            		    else if(key=='totalCount'){
            		    	totalCount=Number(value);
            		    }
            		});
                	
   
            		for(var i=0; i<Number(totalCount); i++){
            			
            			document.getElementById('search_data').innerHTML+=append(data.item[i].areacode, data.item[i].sigungucode, data.item[i].cat1, data.item[i].cat2, data.item[i].cat3, data.item[i].contentid, data.item[i].contenttypeid, data.item[i].lat, data.item[i].lng, data.item[i].addr1, data.item[i].addr2, data.item[i].title, data.item[i].firstimage);
            		
            			var title=data.item[i].title;
                		var lat=data.item[i].lat;
                		var lng=data.item[i].lng;
                		var type=data.item[i].contenttypeid;
                		var id=data.item[i].contentid;
                		var addr1=data.item[i].addr1;
                		var addr2=data.item[i].addr2;
                		var img=data.item[i].firstimage;
                		
                		if(addr2==null)
                			addr2="";
                		
                		positions.push({
                			title:title,
                			latlng: new daum.maps.LatLng(lat, lng),
                			contenttypeid:type,
                			contentid:id,
                			addr1:addr1,
                			addr2:addr2,
                			img:img
                		});
            		}
            		
                    for(var i=0; i<markers.length; i++){ //마커 삭제
                 	   markers[i].setMap(null);
                    }
                    markers=[]; //마커 초기화
                    
                    for(var i=0; i<positions.length; i++){ //마커 이미지 셋팅
                		// 마커 이미지의 이미지 크기 입니다
                	    var imageSize = new daum.maps.Size(42, 43); 
                	    var markerImage;
                	    // 마커 이미지를 생성합니다    
                	    if(positions[i].contenttypeid==12){ //해당 위치가 관광지이면
                	    	markerImage = new daum.maps.MarkerImage(TourMarkerImg, imageSize); 
                	    }
                	    else if(positions[i].contenttypeid==39){ //해당 위치가 음식점이면
                	    	markerImage = new daum.maps.MarkerImage(FoodMarkerImg, imageSize);
                	    }
                	    
                	    markers.push(addMarker(positions[i].latlng, positions[i].contenttypeid, markerImage, positions[i].title, i, positions[i].contentid, positions[i].img, positions[i].addr1, positions[i].addr2));
                	}
                }
            })
            $(this).val('');
        }
    })

    $(document).on("click", ".delete_btn",function(){ //삭제 이벤트
        var day_cnt=$('.day_arrange>button').length;

        
        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        
        var position=$(this).parent().parent().index();
        $.ajax({
    		type:'POST',
    		url:'./Note_Plans_Delete.pl',
    		data: {
    			NoteID: NoteID,
    			day_orders:num,
    			position:position
    		},
    		async: false,
    		success:function(data){
    			day_array[num].splice(position, 1); //삭제된 일정 잘라내기
    			//day_marker_array[position].setMap(null); //삭제된 일정의 마커 위치 없애기
    			//day_marker_array.splice(position, 1); // 삭제된 일정의 마커 잘라내기

    			var Food_marker_Src="./daum_map/marker_img/Food_Select.png";
    	        var Tour_marker_Src="./daum_map/marker_img/Tour_Select.png";
    	        var Start_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/red_b.png";
    	        var End_marker_Src="http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/blue_b.png";
    	        for(var i=0; i<day_marker_array.length; i++){
    	        	day_marker_array[i].setMap(null);
    	        }
    	        day_marker_array=new Array(0);
    	        for(var i=0; i<day_array[num].length; i++){
    	        	var markerImage;
    	        	if(i==0){ //출발지
    	        		markerImage=new daum.maps.MarkerImage(Start_marker_Src, imageSize);
    	        	}
    	        	else if(i==(day_array[num].length-1)){//도착지
    	        		markerImage=new daum.maps.MarkerImage(End_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==12){//관광지 이면
    	        		markerImage=new daum.maps.MarkerImage(Tour_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==39){//음식점 이면
    	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
    	        	}
    	        	else if(day_array[num][i].Content_Type_ID==0){//해시태그 이면
    	        		markerImage=new daum.maps.MarkerImage(Food_marker_Src, imageSize);
    	        	}
    	        	
    	        	day_marker_array.push(new daum.maps.Marker({
	        			map:map,
	        			position: new daum.maps.LatLng(day_array[num][i].lat, day_array[num][i].lng), 
	            		image: markerImage,
	            		title:day_array[num][i].Title
	        		}))
    	        }
    			
    			
    			for(var i=0; i<polyline.length; i++){
    				polyline[i].setMap(null);
    			}
    			
    			polyline=new Array(0);
    			
    			for (var i = 0; i < day_array[num].length; i++) {
    	            if (i != 0) {
    	                linePath = [ day_array[num][i - 1].latlng, day_array[num][i].latlng ] //라인을 그리려면 두 점이 있어야하니깐 두 점을 지정했습니다
    	            }
    	            
    	            lineLine.setPath(linePath); // 선을 그릴 라인을 세팅합니다
    	            if(i>0){
    	           	polyline.push(new daum.maps.Polyline({
    	           		endArrow:true,
    	           		map : map, // 선을 표시할 지도입니다 
    	           		path : linePath,
    	            	strokeWeight : 3, // 선의 두께입니다 
    	            	strokeColor : '#db4040', // 선의 색깔입니다
    	            	strokeOpacity : 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
    	            	strokeStyle : 'solid' // 선의 스타일입니다
    	           	}));
    	            }
    	        }
    			
    			display(day_array[num]);
    		},
    		error:function(data){
    			alert('일정 삭제에 실패하였습니다.');
    		}
    	})
           

    })
})



function rgb2hex(rgb) {
     if (  rgb.search("rgb") == -1 ) {
          return rgb;
     } else {
          rgb = rgb.match(/^rgba?\((\d+),\s*(\d+),\s*(\d+)(?:,\s*(\d+))?\)$/);
          function hex(x) {
               return ("0" + parseInt(x).toString(16)).slice(-2);
          }
          return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
     }
}


function display(array){
    document.getElementById('route_add').innerHTML="";
    for(var i=0; i<array.length; i++){
        document.getElementById('route_add').innerHTML+=create(i, array[i].img, array[i].Title, array[i].Kind2, array[i].lat, array[i].lng, array[i].Content_ID, array[i].Content_Type_ID);
    }
}
function create(num, img, title, kind, lat, lng, ID, type_ID){ //동적 추가 
    return '<div class="route" data-ID="'+ID+'" data-type="'+type_ID+'" data-lat="'+lat+'" data-lng="'+lng+'"><div class="curcle">'+(num+1)+'</div><img src="'+img+'" alt="" width="80px" height="75px"><ul class="route_info"><li class="title">'+title+'</li><li class="kind">'+kind+'</li></ul><div class="btn_group"><img src="./jpg/cancel_btn.png" class="delete_btn"></div></div>'
}

function append(areacode, sigungucode, cat1, cat2, cat3, contentid, contenttypeid, lat, lng, addr1, addr2, title, img){
	var kind;
	if(Number(contenttypeid)==12){
		kind='관광지';
	}
	else if(Number(contenttypeid)==39){
		kind='음식점';
	}

    return '<div class="search_data" data-areacode='+areacode+' data-sigungucode='+sigungucode+' data-cat1='+cat1+' data-cat2='+cat2+' data-cat3='+cat3+' data-contentid='+contentid+' data-contenttypeid='+contenttypeid+' data-lat='+lat+' data-lng='+lng+' data-addr1="'+addr1+'"'+' data-addr2="'+addr2+'">'+' <div class="img">'+' <img src="'+img+'" alt="" width="100px" height="100px">'+' </div>'+' <ul class="info_group">'+' <input type="hidden" class="content_id" value="0">'+' <li class="title">'+title+'</li>'+' <li class="sub_title">'+kind+'</li>' +' <li class="comment_like_group">'+' <div>'+' <img src="./planner_Step2_JPG/comment.png" alt="" width="15px">'+' 523'+' </div>'+' <div>|</div>'+' <div>'+' <img src="./planner_Step2_JPG/stamp.png" alt="" width="15px">'+' 24'+' </div>'+' </li>'+' <div class="day_marker">'+' <!-- DAY1 -->'+' </div>'+' </ul>'+' <div class="add_btn">'+' <img src="./map_image/add.png" alt="" class="route_add_btn">'+' </div>'+' </div>';
}