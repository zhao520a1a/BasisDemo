/**
 * Created by 小鑫哦 on 2016/11/26 0026.
 */

/*运用jQuery框架来进行AJAX的异步数据交互:获取服务器端返回的纯文本数据*/
function verify1() {
    //1.从页面获取数据
    var username = $('#username').val();
    //2.将数据传给Servlet
    // var url = "ajaxServlet?username=" + username;
    //为防止中文乱码问题，页面端做两次encodeURI,而服务器端做一次 URLDecoder.decode(username,"UTF-8");；
    var url = "ajaxServlet?username=" + encodeURI(encodeURI(username));
    url = convertURL(url);
    $.get(url, null, callback1)
}
//3.接收返回的纯文本数据-->data
function callback1(data) {
    //4.数据显示到页面上
    $("#result").html(data);
}

function convertURL(url) {
    /*采用代理，解决跨域（html代码和服务器端的代码不在一块）访问的问题：
    * 例如:
    *         http://www.sohu.com/index.html?name=123&id=000
    *   变为: Proxy?url=http://www.sohu.com/index.html&name=123&id=000
    */
    if(url.substring(0,7) == "http://"){
        url = url.replace("?", "&");
        url = "Proxy?url=" + url;
    }

    /*为了不让浏览器使用缓存，将原始的url拼接上一个时间戳信息，使其每次请求都访问服务器*/
    var timestamp = (new Date()).valueOf();//得到时间戳
    if(url.indexOf("?") >= 0){     //拼接到url中
        url += "&time=" + timestamp;
    } else {
        url += "?time=" + timestamp;
    }
    return url;
}



/*运用jQuery框架来进行AJAX的异步数据交互:获取服务器端返回的xml数据*/
function verify2() {
    //1.从页面获取数据
    var username = $('#username').val();
    //2.将数据传给Servlet
    $.ajax({
        type:"POST",
        url:"ajaxXmlServlet",
        data:"username=" + username,
        dataType:"xml", //返回的数据格式
        success:callback2  //定义回调函数
    })
}
//3.接收返回的xml数据-->data
function callback2(data) {
    //首先将dom的对象转化为jQuery对象
    var jQueryObj = $(data);
    //获取message结点；
    var message = jQueryObj.children();
    //获取文本内容
    var text = message.text();

    //4.数据显示到页面上
    $("#result").html(text);
}

/*用最底层XMLHttpRequest对象进行AJAX的异步数据交互:获取服务器端返回的纯文本数据*/
var xmlhttp;
function verify3() {
    //1.从页面获取数据
    var username = document.getElementById("username").value;

    //2.创建XMLHttpRequest对象 --- 核心点
    /*要针对不同的浏览器建立采用不同的方式来建立这个对象，写不同的代码*/
    if (window.XMLHttpRequest) {  //针对FireFox,Mozillar,Opera, Saffari,IE7, IE8等版本的浏览器;
        xmlhttp = new XMLHttpRequest();
        //下面这段代码，是为了针对某些特定版本的mozillar浏览器的Bug进行修正；
        if (xmlhttp.overrideMimeType) {
            xmlhttp.overrideMimeType("text/xml");
        }
    } else if (window.ActiveXObject) {  //针对IE5,IE5.5,IE6
        //有两个创建XMLHttpRequest对象的控件
        var activeName = ["MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
        for (var i = 0; i < activeName.length; i++) {
            try {
                xmlhttp = new ActiveXObject(activeName[i]);
                break;
            } catch (e) {
            }
        }
    }
    /* // 验证XMLHttpRequest对象创建成功；
     if(!xmlhttp) {
     alert("XMLHttpRequest对象创建失败！！")
     return;
     } else{
     alert(xmlhttp);
     }*/

    //3.注册回调函数
    xmlhttp.onreadystatechange = callback3;
    /*注意：只需要函数名，不要括号，否则会将函数返回值注册上，这是错误的；*/

    /*用Get方式来请求*/
    //4.设置连接信息
    xmlhttp.open("GET", "ajaxServlet?username=" + username, true);
    /*参数1：请求方式; 参数2：请求的url; 参数3：是否异步请求*/

    //5.发送数据，开始和服务器进行交互；若设为同步，程序会阻塞在这，等服务器的返回，异步不会；
    xmlhttp.send(null);
    /*因为在上面的url中已包含了所需要的数据，这一就置空了；*/
    /*
     /!*用Post方式来请求；注意：经运行发现若用户输入为"   ",Get方式会username=""，而Post方式传username="   "*!/
     xmlhttp.open("POST","ajaxServlet",true);
     xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");//post方式要自己设置请求头
     xmlhttp.send("username=" + username);
     */

}
/*每次XMLHttpRequest对象状态（有0-4五个状态；）改变都会调用这个方法，因此需要在开始时判断交互是否完成*/
function callback3() {
    //6.准备接收响应数据
    if (xmlhttp.readyState == 4) {  //判断XMLHttpRequest对象的状态是否是交互完成的状态
        if (xmlhttp.status == 200) {     //判断http的交互是否成功
            var responseText = xmlhttp.responseText; // 获取服务器端返回的纯文本数据
            //    7.将数据显示在页面上
            document.getElementById("result").innerHTML = responseText;
        }
    }
}


/*用最底层XMLHttpRequest对象进行AJAX的异步数据交互:获取服务器端返回的xml数据*/
function verify4() {
    //1.从页面获取数据
    var username = document.getElementById("username").value;

    //2.创建XMLHttpRequest对象 --- 核心点
    /*要针对不同的浏览器建立采用不同的方式来建立这个对象，写不同的代码*/
    if (window.XMLHttpRequest) {  //针对FireFox,Mozillar,Opera, Saffari,IE7, IE8等版本的浏览器;
        xmlhttp = new XMLHttpRequest();
        //下面这段代码，是为了针对某些特定版本的mozillar浏览器的Bug进行修正；
        if (xmlhttp.overrideMimeType) {
            xmlhttp.overrideMimeType("text/xml");
        }
    } else if (window.ActiveXObject) {  //针对IE5,IE5.5,IE6
        //有两个创建XMLHttpRequest对象的控件
        var activeName = ["MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
        for (var i = 0; i < activeName.length; i++) {
            try {
                xmlhttp = new ActiveXObject(activeName[i]);
                break;
            } catch (e) {
            }
        }
    }

    //3.注册回调函数
    xmlhttp.onreadystatechange = callback4;
    /*注意：只需要函数名，不要括号，否则会将函数返回值注册上，这是错误的；*/

    /*用Get方式来请求*/
    //4.设置连接信息
    xmlhttp.open("GET", "ajaxXmlServlet?username=" + username, true);
    /*参数1：请求方式; 参数2：请求的url; 参数3：是否异步请求*/

    //5.发送数据，开始和服务器进行交互；若设为同步，程序会阻塞在这，等服务器的返回，异步不会；
    xmlhttp.send(null);
    /*因为在上面的url中已包含了所需要的数据，这一就置空了；*/

    /*
     /!*用Post方式来请求；注意：经运行发现若用户输入为"   ",Get方式会username=""，而Post方式传username="   "*!/
     xmlhttp.open("POST","ajaxServlet",true);
     xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");//post方式要自己设置请求头
     xmlhttp.send("username=" + username);
     */

}
/*每次XMLHttpRequest对象状态（有0-4五个状态；）改变都会调用这个方法，因此需要在开始时判断交互是否完成*/
function callback4() {
    //6.准备接收响应数据
    if (xmlhttp.readyState == 4) {  //判断XMLHttpRequest对象的状态是否是交互完成的状态
        if (xmlhttp.status == 200) {     //判断http的交互是否成功
            var domObj = xmlhttp.responseXML; // 获取服务器端返回的xml数据对象
            if (domObj) {
                var messageNodes = domObj.getElementsByTagName("message");
                if (messageNodes.length > 0) {
                    //<message> **** </message>
                    var textNode = messageNodes[0].firstChild;  //取到文本节点
                    //    7.将数据显示在页面上
                    document.getElementById("result").innerHTML = textNode.nodeValue;
                } else {
                    alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
                }
            } else {
                alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
            }
        } else {
            alert("出错了！！！")
        }
    }
}

