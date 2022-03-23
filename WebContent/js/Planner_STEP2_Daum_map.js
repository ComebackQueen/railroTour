var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = {
        center : new daum.maps.LatLng($('.day_arrange button').first().data('lat'), $('.day_arrange button').first().data('lng')), // 지도의 중심좌표
        level : 9
    // 지도의 확대 레벨
    };
 	
    var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    var overlay=new Array(0);
	var positions=new Array(0);
	
	for(var i=0; i<$('.search_data').length; i++){ //다중 마커 찍기위해 데이터 수집
		var title=$('.search_data').eq(i).find('.title').text();
		var lat=$('.search_data').eq(i).data('lat');
		var lng=$('.search_data').eq(i).data('lng');
		var type=$('.search_data').eq(i).data('contenttypeid');
		var id=$('.search_data').eq(i).data('contentid');
		var addr1=$('.search_data').eq(i).data('addr1');
		var addr2=$('.search_data').eq(i).data('addr2');
		var img=$('.search_data').eq(i).find('img').attr('src');
		
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
	
	var TourMarkerImg = "./daum_map/marker_img/TourMarker.png"; //이미지 마커 주소
	var FoodMarkerImg = "./daum_map/marker_img/FoodMarker.png"; //이미지 마커 주소
	var TourHoverMarkerImg = "./daum_map/marker_img/TourMarker_Hover.png";
	var FoodHoverMarkerImg = "./daum_map/marker_img/FoodMarker_Hover.png";
	
	var markers=new Array(0);
	
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

	function closeOverlay(num){
		overlay[num].setMap(null);
	}
	
	// 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
	function addMarker(position, contentTypeId, markerImage, title, num, contentid, img, addr1, addr2) {

	    // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
	    var normalImage = markerImage;
	    var overImage;
	    if(contentTypeId==12){
	    	overImage = new daum.maps.MarkerImage(TourHoverMarkerImg, imageSize);
	    }
	    else if(contentTypeId==39){
	    	overImage = new daum.maps.MarkerImage(FoodHoverMarkerImg, imageSize);
	    }
	    
	    // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
	    var marker = new daum.maps.Marker({
	        map: map,
	        position: position,
	        image: normalImage,
	        title: title
	    });
	 
	    // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
	    marker.normalImage = normalImage;

	    // 마커에 mouseover 이벤트를 등록합니다
	    daum.maps.event.addListener(marker, 'mouseover', function() {
	    		marker.setImage(overImage);
	    });

	    // 마커에 mouseout 이벤트를 등록합니다
	    daum.maps.event.addListener(marker, 'mouseout', function() {
	    		marker.setImage(normalImage);
	    });

		var content = '<div class="wrap">' + 
	    '    <div class="info">' + 
	    '        <div class="title">' + 
	    '            '+title+
	    '            <div class="close" onclick="closeOverlay('+num+')" title="닫기"></div>' + 
	    '        </div>' + 
	    '        <div class="body">' + 
	    '            <div class="img">' +
	    '                <img src="'+img+'" width="73" height="70">' +
	    '           </div>' + 
	    '            <div class="desc">' + 
	    '                <div class="ellipsis">'+addr1+'</div>' + 
	    '                <div class="jibun ellipsis">'+addr2+'</div>' + 
	    '            </div>' + 
	    '        </div>' + 
	    '    </div>' +    
	    '</div>';
		
		
	    overlay.push(new daum.maps.CustomOverlay({
	    	content: content,
	    	map: map,
	    	position: position       
	    }));
	    
	    overlay[num].setMap(null);
	    
	 // 마커에 click 이벤트를 등록합니다
	    daum.maps.event.addListener(marker, 'click', function() {
	    	for(var i=0; i<positions.length; i++){
	    		if(num==i){
	    			overlay[i].setMap(map);
	    		}
	    		else{
	    			overlay[i].setMap(null);
	    		}
	    	}
	    });

	    return marker;
	}

