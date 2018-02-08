
/*
* 需要点击主菜单的按钮时，对应的子菜单可以显示，再次点击子菜单则隐藏
*  方法：在页面装载时，给所有的主菜单添加onclick的事件
*/
//注册页面装载时执行的方法
$(document).ready(function() {
    //这里需要首先找到所有的主菜单，然后给所有的主菜单注册点击事件（找到ul中的节点）
    var as = $("ul > a");
    as.click(function() {
        //这里需要找到当前ul中的li，然后让li显示出来
        var aNode = $(this);//获取当前被点击的a节点
        var lis = aNode.nextAll("li");  //找到当前a节点的所有li兄弟字节点
        lis.toggle("show");    //让子节点显示或隐藏
    });
});