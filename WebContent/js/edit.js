    var file_count=0;
    var file_name=new Array(0);

$(document).ready(function(){
    $(".upload-hidden1").on('change', function(fileValue){
    
        if(window.FileReader){
            filename = $(this)[0].files[0].name; 
        } 
    
        else { 
           filename = $(this).val().split('/').pop().split('\\').pop(); 
        }
        $(this).siblings('.upload1').val(filename);
    });
    $(".upload-hidden2").on('change', function(fileValue){

        if(window.FileReader){
            filename = $(this)[0].files[0].name; 
        } 
    
        else { 
           filename = $(this).val().split('/').pop().split('\\').pop(); 
        }
        $(this).siblings('.upload2').val(filename);
    });
    $(".upload-hidden3").on('change', function(fileValue){
        if(window.FileReader){
            filename = $(this)[0].files[0].name; 
        } 
    
        else { 
           filename = $(this).val().split('/').pop().split('\\').pop(); 
        }
        $(this).siblings('.upload3').val(filename);
    });
    $(".upload-hidden4").on('change', function(fileValue){
        if(window.FileReader){
            filename = $(this)[0].files[0].name; 
        } 
    
        else { 
           filename = $(this).val().split('/').pop().split('\\').pop(); 
        }
        $(this).siblings('.upload4').val(filename);
    });
    $(".upload-hidden5").on('change', function(fileValue){
        if(window.FileReader){
            filename = $(this)[0].files[0].name; 
        } 
    
        else { 
           filename = $(this).val().split('/').pop().split('\\').pop(); 
        }
        $(this).siblings('.upload5').val(filename);
    });
   
}); 

function getFileType(filePath)
{
    var index = -1;
        index = filePath.lastIndexOf('.');

    var type = "";

    if(index != -1)
    {
        type = filePath.substring(index+1, filePath.len);
    }
    else
    {
        type = "";
    }

    return type;
}

function display(filename){
    document.getElementById("file_list").innerHTML=="";
    
        document.getElementById("file_list").innerHTML+=create(filename);
    
}
function create(name){ //동적 추가 
    return '<option value="'+name+'">'+name+'</option>';
}

function getFileType(filePath){
    var index=-1;
    index=filePath.lastIndexOf('.');
    var type="";
    if(index!=-1){
        type=filePath.substring(index+1, filePath.length);
    }
    else{
        type="";
    }
    return type;
}

function checkForm(){ //폼 유효성 체크 부분
    var title=document.edit_form.title;
    var content=document.edit_form.content;
    
    if(title.value==''){
        alert("제목을 입력하세요");
        title.focus();
        return false;
    }
    else if(content.value==''){
        alert("내용을 입력하세요");
        content.focus();
        return false;
    }
    
    $(".upload-hidden").val(file_name);
    return true;
}