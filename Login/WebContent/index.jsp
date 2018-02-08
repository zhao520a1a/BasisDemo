<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>

<script type="text/javascript">
	function chkPassword() {
		var p = document.getElementsByName("password");
		for (var i = 0; i < p.length; i++) {
			alert(p[i].value);
		}
	}

	//不能密码全设为数字
	function chkNum(event) {
		/* 	   单个键
				//e.which适用于火狐，而e.keyCode使用于ie
				var key = window.event ? e.keyCode : e.which; //得到键码
				var keyChar = String.fromCharCode(key); //得到键值
			   // alert(keyChar);
		 */
		var pas = document.getElementById("password");
		var reg = /\d/; //正则表达式
		var value = pas.value;
		for (var i = 0; i < value.length; i++) {
			var result = reg.test(value.charAt(i));
			if (!result) {
				break;
			}
			if (i == value.length - 1) {
				alert('密码过于简单！');
			}
		}
	}
</script>

<link href="style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<form name="form1" method="post" id="form1" action="UserControl.do">

		<dl class="lineDl">
			<dt>
				用户名 <b>*</b>
			</dt>
			<dd>
				<div>
					<input id="username" type="text" name="username" value="" maxlength="20" />
				</div>
			</dd>
		</dl>

		<div>
			<dl class="lineDl">
				<dt>
					密码 <b>*</b>
				</dt>
				<dd>
					<div>
						<input id="password" type="password" name="password"
							maxlength="20" onblur="chkNum(event)" style="img-mode: Disabled;" />
					</div>
				</dd>
			</dl>

			<dl class="lineDl">
				<dt>
					再次输入密码 <b>*</b>
				</dt>
				<dd>
					<div>
						<input id="repassword" type="password" name="repassword"
							maxlength="20" />
					</div>
				</dd>

			</dl>
		</div>


		<dl class="lineDl">

			<dd>
				<div>
					<input type="submit" value="登录" />
					 <input type="button" 	value="得到密码" onclick="chkPassword()" />
				</div>
			</dd>
		</dl>

	</form>

</body>
</html>