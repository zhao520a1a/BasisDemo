//期望进入页面后就可以开始从服务器段获取数据，然后显示股票价格
//保存服务器段返回的股票对象
/*
 data = ({
 "000001":{
 "id":"000001",
 "name":"浦发银行",
 "now":22.44,
 "today":23.5,
 "yesterday":23.22
 },
 "300001":{
 "id":"300001",
 "name":"上证指数",
 "now":3032.21,
 "today":2990.1,
 "yesterday":3000
 }
 } )
*/
var obj;  //后台传到前端的JSON对象，封装了股票的信息数据；
var sid;  //当前股票的编号
$(document).ready(function() {
    //设置弹出框的css格式，设置页面载入时隐藏弹出框
    var stockNode = $("#stock").css("border","1px solid black").width("150px")
            .css("position","absolute").css("z-index","99").css("background-color","blue")
            .css("color","yellow");
    stockNode.hide();
    var as = $("a");
    //定义鼠标进入股票名称时的操作
    as.mouseover(function(event){
        var aNode = $(this);    //找到鼠标进入的链接节点：a节点
        var divNode = aNode.parent();   //找到a节点的父节点：div节点
        sid = divNode.attr("id");  //获取到当前股票的编号
        updatediv();  //更新弹出框中得内容
        //使弹出框出现在鼠标的右下方
        var myEvent = event || window.event;
        stockNode.css("left",myEvent.clientX + 5 + "px").css("top",myEvent.clientY + 5 + "px");
        stockNode.show();     //弹出框显示
    });
    //定义鼠标离开股票名称时的操作
    as.mouseout(function() {
        stockNode.hide();  //弹出框隐藏
    })
    getInfo();

    //3。每隔一秒钟和服务器交互一次，用户不用刷新页面就可以不断地看到最新的股票信息
    setInterval(getInfo,1000);
});

//从服务器段获取股票信息数据并显示在页面上的的方法
function getInfo() {
    //1。向服务器发起请求，获取数据
    $.get("/StocksServlet", null, function(data) {
        //2.接收并解析数据
        obj = data;
        //2.1获取两只股票的当前指数信息
        var szzs = obj["300001"]; /*理论上也可以采用obj.atrrName的方式访问，但是这里300001不是一个合法的属性名，因此不行！*/
        var pfyh = obj["000001"];

        //2.2找到页面中对应的节点，然后填充最新的股票价格
        var span3 = $("#300001").children("span");
        span3.html(szzs.now);
        if (szzs.now > szzs.yesterday) {
            span3.css("color","red");  //当前价格大于昨天收盘，则显示红色
        } else {
            span3.css("color","green");
        }
        var span1 = $("#000001").children("span");
        span1.html(pfyh.now);
        if (pfyh.now > pfyh.yesterday) {
            span1.css("color","red");     //当前价格大于昨天收盘，则显示红色
        } else {
            span1.css("color","green");
        }
        updatediv();  //同步更新当前股票的价格
    }, "json")
}

//更新弹出框中得内容
function updatediv() {
    var stockobj = obj[sid];
    //  遍历一个js的对象的方法:
    for (var proname in stockobj) {
        if (proname != "name" && proname != "id") {
            $("#" + proname).children("span").html(stockobj[proname]);   //找到对应的弹出框中的div节点，然后找到span字节点，将数据填充
        }
    }
}