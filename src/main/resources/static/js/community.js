function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
    if (!content){
        alert("回复内容不能为空")
        return;
    }
    $.ajax({
        type: "POST",
        url: '/comment',
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            //回复成功，刷新页面
            if (response.code==200){
                //$("#comment_section").hide();
                window.location.reload();
            }else{
                //未登录
                if (response.code==2003){
                    var isAccepted = confirm(response.message);
                    if (isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.826e125ed735f67a&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                       //本地存储
                        window.localStorage.setItem("closable",true);
                    }else{
                        alert(response.message);
                    }

                }
            }
        },
        dataType: 'json'
    });
}