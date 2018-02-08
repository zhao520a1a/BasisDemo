
var highlightindex = -1;  //表示当前高亮的节点
var timeoutId;  //为避免快速打字造成的频繁请求,而设置的一个标识

$(document).ready(function() {
    var wordInput = $("#word");
    var wordInputOffset = wordInput.offset();
    //自动补全框最开始应该隐藏起来
    $("#auto").hide().css("border","1px black solid").css("position","absolute")
            .css("top",wordInputOffset.top + wordInput.height() + 5 + "px")
            .css("left",wordInputOffset.left + "px").width(wordInput.width() + 2);

    //给文本框添加键盘按下并弹起的事件
    wordInput.keyup(function(event) {
        //处理文本框中的键盘事件
        var myEvent = event || window.event;
        var keyCode = myEvent.keyCode;
        //如果输入的是字母、退格键或删除键，应该将文本框中最新的信息发送给服务器
        if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46) {
            //1.首先获取文本框中的内容
            var wordText = $("#word").val();
            var autoNode = $("#auto");
            if (wordText != "") {
                /*对上次未完成的延时操作进行取消*/
                clearTimeout(timeoutId);
                /*为避免快速打字造成的频繁请求，对于服务器端经进行交互延迟500ms */
                timeoutId = setTimeout(function() {
                        //2.将文本框中的内容发送给服务器端
                        $.post("AutoComplete",
                            {word: wordText},
                            function (data) {
                                //将dom对象data转换成JQuery的对象
                                var jqueryObj = $(data);
                                //找到所有的word节点
                                var wordNodes = jqueryObj.find("word");
                                //遍历所有的word节点，取出单词内容，然后将单词内容添加到弹出框中
                                //需要清空原来的内容
                                autoNode.html("");
                                wordNodes.each(function (i) {
                                    //获取单词内容
                                    var wordNode = $(this);
                                    //新建div节点，将单词内容加入到新建的节点中
                                    //将新建的节点加入到弹出框的节点中
                                    var newDivNode = $("<div>").attr("id", i);  //创建时就添加一个索引值
                                    newDivNode.html(wordNode.text()).appendTo(autoNode);
                                    //增加鼠标进入事件，高亮当前节点
                                    newDivNode.mouseover(function () {
                                        if (highlightindex != -1) {  //将原来的高亮节点取消
                                            $("#auto").children("div").eq(highlightindex).css("background-color", "white");
                                        }
                                        highlightindex = $(this).attr("id");  //记录新的高亮索引
                                        $(this).css("background-color", "red") //鼠标进入的节点高亮
                                    });
                                    //增加鼠标移出事件，取消当前节点的高亮
                                    newDivNode.mouseout(function () {
                                        $(this).css("background-color", "white");  //取消鼠标移出节点的高亮
                                    });
                                    //增加鼠标点击事件，可以进行补全
                                    newDivNode.click(function () {
                                        var comText = $(this).text();  //取出高亮节点的内容
                                        $("#auto").hide();
                                        highlightindex = -1;
                                        $("#word").val(comText);   //文本框内容变成高亮节点的内容
                                    });
                                });
                                //如果服务器段有数据返回，则显示弹出框
                                if (wordNodes.length > 0) {
                                    autoNode.show();
                                } else {
                                    autoNode.hide();
                                    //弹出框隐藏的同时，高亮节点索引值也制成-1
                                    highlightindex = -1;
                                }
                            }, "xml");
                    }
                    ,500);

            } else {
                autoNode.hide();
                highlightindex = -1;
            }
        } else if (keyCode == 38 || keyCode == 40) {     //如果输入的是向上38向下40按键
            if (keyCode == 38) {   //向上
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {  //如果原来存在高亮节点，则将背景色改称白色
                    autoNodes.eq(highlightindex).css("background-color","white");
                    highlightindex--;
                } else {
                    highlightindex = autoNodes.length - 1;    
                }
                if (highlightindex == -1) {   //如果修改索引值以后index变成-1，则将索引值指向最后一个元素
                    highlightindex = autoNodes.length - 1;
                }
                autoNodes.eq(highlightindex).css("background-color","red");      //让现在高亮的内容变成红色
            }
            if (keyCode == 40) {    //向下
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {
                    autoNodes.eq(highlightindex).css("background-color","white");
                }
                highlightindex++;
                if (highlightindex == autoNodes.length) {
                    highlightindex = 0;
                }
                autoNodes.eq(highlightindex).css("background-color","red");
            }
        } else if (keyCode == 13) {  //如果输入的是回车
            if (highlightindex != -1) {  //下拉框有高亮内容
                //取出高亮节点的文本内容
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();
                highlightindex = -1;
                $("#word").val(comText);      //文本框中的内容变成高亮节点的内容
            } else {     //下拉框没有高亮内容
                alert("文本框中的[" + $("#word").val() + "]被提交了");
                $("#auto").hide();  //隐藏下拉框内容
                $("#word").get(0).blur();  //让文本框失去焦点
            }
        }
    });

    //给按钮添加事件，表示文本框中的数据被提交
    $("input[type='button']").click(function() {
        alert("文本框中的[" + $("#word").val() + "]被提交了");
    });
})