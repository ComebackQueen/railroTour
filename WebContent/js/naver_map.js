
            
            var mapOptions = {  //지도 옵션 셋팅
                center: new naver.maps.LatLng(35.439355, 127.047028), 
                zoom: 4, //지도의 초기 줌 레벨
                minZoom: 1, //지도의 최소 줌 레벨
                zoomControl: true, //줌 컨트롤의 표시 여부
                zoomControlOptions: { //줌 컨트롤의 옵션
                position: naver.maps.Position.TOP_RIGHT
                }
            }; 

            var map = new naver.maps.Map('map', mapOptions);  //지도 생성       
            
            var buk_do = { //이미지 마커
                position: new naver.maps.LatLng(35.71750000, 127.15300000), //위도 경도 설정
                map: map,
                icon: {
                    url: './jpg/marker.png',
                    size: new naver.maps.Size(20, 32),
                    scaledSize: new naver.maps.Size(20, 32),
                    origin: new naver.maps.Point(0, 0),
                    anchor: new naver.maps.Point(10, 16)
                }
            };
            
            var nam_do = { //이미지 마커
                position: new naver.maps.LatLng(34.86790000, 126.99100000), //위도 경도 설정
                map: map,
                icon: {
                    url: './jpg/marker.png',
                    size: new naver.maps.Size(20, 32),
                    scaledSize: new naver.maps.Size(20, 32),
                    origin: new naver.maps.Point(0, 0),
                    anchor: new naver.maps.Point(10, 16)
                }
            };
            
            var gwangju = { //이미지 마커
                position: new naver.maps.LatLng(35.153946, 126.834872), //위도 경도 설정
                map: map,
                icon: {
                    url: './jpg/marker.png',
                    size: new naver.maps.Size(20, 32),
                    scaledSize: new naver.maps.Size(20, 32),
                    origin: new naver.maps.Point(0, 0),
                    anchor: new naver.maps.Point(10, 16)
                }
            };           
            var marker=new Array();

            var Big_markers=[buk_do, nam_do, gwangju]; //전라도 지역 마커 리스트 (큰 도 기준)
            
            var te=$("#ALL_VIEW").css("background-color");
            var result="rgb(26, 122, 217)";

            if(te==result){ //초기 마커 찍기
                for(var i=0; i<Big_markers.length; i++){
                    marker[i]=new naver.maps.Marker(Big_markers[i]);
                }
            }
            
            var all_view=new naver.maps.LatLng(35.439355, 127.047028); //모두보기 중심
            var buk_do_view=new naver.maps.LatLng(35.858529, 127.170817); //전라북도 보기 중심
            var nam_do_view=new naver.maps.LatLng(34.847102, 126.863816); //전라남도 보기 중심
            
            var test1=new naver.maps.LatLng(35.71750000, 127.15300000);
            var test2=new naver.maps.LatLng(34.86790000, 126.99100000);
            
            /*
            * 대분류 눌렀을때
            */
            var buk_do_array=new Array(); //전라북도안의 모든 지역의 마커 옵션 기록
            var nam_do_array=new Array(); //전라남도안의 모든 지역의 마커 옵션 기록
            
            var Small_markers1=new Array(); //전라북도 마커 추가
            var Small_markers2=new Array(); //전라남도 마커 추가
        
            
            for(var i=0; i<14; i++){ //전라북도의 모든 지역 리스트 마커 추가
                var mapX=$('.buk_do'+(i+1)).data("lat");
                var mapY=$('.buk_do'+(i+1)).data("lng");
                var station=$('.buk_do'+(i+1)).data("station");
                if(station=='버스'){
                    buk_do_array[i]={
                        position: new naver.maps.LatLng(mapX, mapY), //위도 경도 설정
                        map: map,
                        icon: {
                            url: './naver_Marker_image/bus_marker.png',
                            size: new naver.maps.Size(28.6, 43),
                            scaledSize: new naver.maps.Size(28.6, 43),
                            origin: new naver.maps.Point(0, 0),
                            anchor: new naver.maps.Point(14.3, 21.5)
                        }
                    }
                }
                else if(station=='기차'){
                    buk_do_array[i]={
                        position: new naver.maps.LatLng(mapX, mapY), //위도 경도 설정
                        map: map,
                        icon: {
                            url: './naver_Marker_image/train_marker.png',
                            size: new naver.maps.Size(28.6, 43),
                            scaledSize: new naver.maps.Size(28.6, 43),
                            origin: new naver.maps.Point(0, 0),
                            anchor: new naver.maps.Point(14.3, 21.5)
                        }
                    }
                }
            }
            
            for(var i=0; i<22; i++){ //전라남도 모든 지역 리스트 마커 추가
                var mapX=$('.nam_do'+(i+1)).data("lat");
                var mapY=$('.nam_do'+(i+1)).data("lng");
                var station=$('.nam_do'+(i+1)).data("station");
                if(station=='버스'){
                    nam_do_array[i]={
                        position: new naver.maps.LatLng(mapX, mapY), //위도 경도 설정
                        map: map,
                        icon: {
                            url: './naver_Marker_image/bus_marker.png',
                            size: new naver.maps.Size(28.6, 43),
                            scaledSize: new naver.maps.Size(28.6, 43),
                            origin: new naver.maps.Point(0, 0),
                            anchor: new naver.maps.Point(14.3, 21.5)
                        }
                    }
                }
                else if(station=='기차'){
                    nam_do_array[i]={
                        position: new naver.maps.LatLng(mapX, mapY), //위도 경도 설정
                        map: map,
                        icon: {
                            url: './naver_Marker_image/train_marker.png',
                            size: new naver.maps.Size(28.6, 43),
                            scaledSize: new naver.maps.Size(28.6, 43),
                            origin: new naver.maps.Point(0, 0),
                            anchor: new naver.maps.Point(14.3, 21.5)
                        }
                    }
                }
            }
            
            
            $(document).ready(function(){
                $("#ALL_VIEW").on("click", function(e){
                    $(".all").css({display:'block'});
                    $(".buk_do").css({display:'none'});
                    $(".nam_do").css({display:'none'});
                    $(".Gwangju_do").css({display:'none'});
                    $("#ALL_VIEW").css({backgroundColor:'#1a7ad9'});
                    $("#ALL_VIEW").siblings().css({backgroundColor:'transparent'});
                    e.preventDefault();
                    map.setZoom(4, true);
                    map.panTo(all_view);
                    for(var i=0; i<Big_markers.length; i++){ //큰 도 지역 마커 찍기
                        marker[i]=new naver.maps.Marker(Big_markers[i]);
                    }
                    for(var i=0; i<buk_do_array.length; i++){ //전라북도 모든 지역 마커 삭제
                        Small_markers1[i].setMap(null);
                    }
                    for(var i=0; i<nam_do_array.length; i++){ //전라남도 모든 지역 마커 삭제
                        Small_markers2[i].setMap(null);
                    }

                })
                
                $("#BUK_DO_VIEW").on("click", function(e){
                    $(".all").css({display:'none'});
                    $(".buk_do").css({display:'block'});
                    $(".nam_do").css({display:'none'});
                    $(".Gwangju_do").css({display:'none'});
                    $("#BUK_DO_VIEW").css({backgroundColor:'#1a7ad9'});
                    $("#BUK_DO_VIEW").siblings().css({backgroundColor:'transparent'});
                    e.preventDefault();
                    map.setCenter(buk_do_view);
                    map.setZoom(5, true);
                    for(var i=0; i<buk_do_array.length; i++){ //전라북도 모든 지역 마커 찍기
                        Small_markers1[i]=new naver.maps.Marker(buk_do_array[i]);
                    }
                    for(var i=0; i<marker.length; i++){ //큰 도 지역 마커 삭제
                        marker[i].setMap(null);
                    }
                    for(var i=0; i<nam_do_array.length; i++){ //전라남도 모든 지역 마커 삭제
                        Small_markers2[i].setMap(null);
                    }
                })
                
                $("#NAM_DO_VIEW").on("click", function(e){
                    $(".all").css({display:'none'});
                    $(".buk_do").css({display:'none'});
                    $(".nam_do").css({display:'block'});
                    $(".Gwangju_do").css({display:'none'});
                    $("#NAM_DO_VIEW").css({backgroundColor:'#1a7ad9'});
                    $("#NAM_DO_VIEW").siblings().css({backgroundColor:'transparent'});
                    e.preventDefault();
                    map.setCenter(nam_do_view);
                    map.setZoom(5, true);
                    for(var i=0; i<nam_do_array.length; i++){ //전라남도 모든 지역 마커 찍기
                        Small_markers2[i]=new naver.maps.Marker(nam_do_array[i]);
                    }
                    for(var i=0; i<marker.length; i++){ //큰 도 지역 마커 삭제
                        marker[i].setMap(null);
                    }
                    for(var i=0; i<buk_do_array.length; i++){ //전라북도 모든 지역 마커 삭제
                        Small_markers1[i].setMap(null);
                    }

                })
                
                $("#Gwangju_VIEW").on("click", function(e){
                    $(".all").css({display:'none'});
                    $(".buk_do").css({display:'none'});
                    $(".nam_do").css({display:'none'});
                    $(".Gwangju_do").css({display:'block'});
                    $("#Gwangju_VIEW").css({backgroundColor:'#1a7ad9'});
                    $("#Gwangju_VIEW").siblings().css({backgroundColor:'transparent'});
//                    e.preventDefault();
//                    map.setCenter(nam_do_view);
//                    map.setZoom(5, true);
//                    for(var i=0; i<nam_do_array.length; i++){ //전라남도 모든 지역 마커 찍기
//                        Small_markers2[i]=new naver.maps.Marker(nam_do_array[i]);
//                    }
//                    for(var i=0; i<marker.length; i++){ //큰 도 지역 마커 삭제
//                        marker[i].setMap(null);
//                    }
//                    for(var i=0; i<buk_do_array.length; i++){ //전라북도 모든 지역 마커 삭제
//                        Small_markers1[i].setMap(null);
//                    }
                })
                /*
                * 소분류 눌렀을때
                */

                $(".bukdo").click(function(e){
                    e.preventDefault();
                    map.setCenter(buk_do_view);
                    map.setZoom(5, true);
                    for(var i=0; i<buk_do_array.length; i++){ //전라북도 모든 지역 마커 찍기
                        Small_markers1[i]=new naver.maps.Marker(buk_do_array[i]);
                    }
                    for(var i=0; i<marker.length; i++){ //큰 도 지역 마커 삭제
                        marker[i].setMap(null);
                    }
                    for(var i=0; i<nam_do_array.length; i++){ //전라남도 모든 지역 마커 삭제
                        Small_markers2[i].setMap(null);
                    }
                })
                $(".namdo").click(function(e){
                    e.preventDefault();
                    map.setCenter(nam_do_view);
                    map.setZoom(5, true);
                    for(var i=0; i<nam_do_array.length; i++){ //전라남도 모든 지역 마커 찍기
                        Small_markers2[i]=new naver.maps.Marker(nam_do_array[i]);
                    }
                    for(var i=0; i<marker.length; i++){ //큰 도 지역 마커 삭제
                        marker[i].setMap(null);
                    }
                    for(var i=0; i<buk_do_array.length; i++){ //전라북도 모든 지역 마커 삭제
                        Small_markers1[i].setMap(null);
                    }
                })
                
                
                $(".buk_do .item_box").hover(function(){
                    var X=$(this).data("lat");
                    var Y=$(this).data("lng");
                    var destination=$(this).data("station");
                    var num="(lat:"+X+",lng:"+Y+")";
                    

                    for(var i=0; i<14; i++){
                        if(buk_do_array[i].position==num){

                                Small_markers1[i].setIcon({
                                    url: './naver_Marker_image/hover_marker.png',
                                    size: new naver.maps.Size(32, 50),
                                    scaledSize: new naver.maps.Size(32, 50),
                                    origin: new naver.maps.Point(0, 0),
                                    anchor: new naver.maps.Point(16, 25)
                                }); 
                            
                            break;
                        }
                    }
                }, function(e){
                    var X=$(this).data("lat");
                    var Y=$(this).data("lng");
                    var destination=$(this).data("station");
                    var num="(lat:"+X+",lng:"+Y+")";
                    

                    for(var i=0; i<14; i++){
                        if(buk_do_array[i].position==num){
                            if(destination=='기차'){
                                Small_markers1[i].setIcon({
                                    url: './naver_Marker_image/train_marker.png',
                                    size: new naver.maps.Size(28.6, 43),
                                    scaledSize: new naver.maps.Size(28.6, 43),
                                    origin: new naver.maps.Point(0, 0),
                                    anchor: new naver.maps.Point(14.3, 21.5)
                                }); 
                            }
                            else if(destination=='버스'){
                                Small_markers1[i].setIcon({
                                    url: './naver_Marker_image/bus_marker.png',
                                    size: new naver.maps.Size(28.6, 43),
                                    scaledSize: new naver.maps.Size(28.6, 43),
                                    origin: new naver.maps.Point(0, 0),
                                    anchor: new naver.maps.Point(14.3, 21.5)
                                }); 
                            }
                            break;
                        }
                    }
                })
                
                $(".nam_do .item_box").hover(function(){
                    var X=$(this).data("lat");
                    var Y=$(this).data("lng");
                    var destination=$(this).data("station");
                    var num="(lat:"+X+",lng:"+Y+")";
                    

                    for(var i=0; i<22; i++){
                        if(nam_do_array[i].position==num){
                                Small_markers2[i].setIcon({
                                    url: './naver_Marker_image/hover_marker.png',
                                    size: new naver.maps.Size(32, 50),
                                    scaledSize: new naver.maps.Size(32, 50),
                                    origin: new naver.maps.Point(0, 0),
                                    anchor: new naver.maps.Point(16, 25)
                                }); 
                            
                            break;
                        }
                    }
                }, function(e){
                    var X=$(this).data("lat");
                    var Y=$(this).data("lng");
                    var destination=$(this).data("station");
                    var num="(lat:"+X+",lng:"+Y+")";
                    

                    for(var i=0; i<22; i++){
                        if(nam_do_array[i].position==num){
                            if(destination=='기차'){
                                Small_markers2[i].setIcon({
                                    url: './naver_Marker_image/train_marker.png',
                                    size: new naver.maps.Size(28.6, 43),
                                    scaledSize: new naver.maps.Size(28.6, 43),
                                    origin: new naver.maps.Point(0, 0),
                                    anchor: new naver.maps.Point(14.3, 21.5)
                                }); 
                            }
                            else if(destination=='버스'){
                                Small_markers2[i].setIcon({
                                    url: './naver_Marker_image/bus_marker.png',
                                    size: new naver.maps.Size(28.6, 43),
                                    scaledSize: new naver.maps.Size(28.6, 43),
                                    origin: new naver.maps.Point(0, 0),
                                    anchor: new naver.maps.Point(14.3, 21.5)
                                }); 
                            }
                            break;
                        }
                    }
                })
                
            })
//            alert(document.getElementById('test1').innerHTML)
