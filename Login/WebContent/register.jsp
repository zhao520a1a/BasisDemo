<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> register </title>

<link href="style.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	function mysubmit() {
		//document.all.form1.submit();
	}

	function chkUserName() {
		var userName = document.getElementById("name");
		var nameVal = userName.value;
		var errMsg = '';

		if (nameVal == '') {
			errMsg = '请填写姓名';
			alert(errMsg);
		} else {
			alert("用户名：" + nameVal);
			//alert('用户名：' + document.all.register_form.name.value);

		}
	}

	function chkGender() {
		var g = document.getElementsByName("gender");

		for (var i = 0; i < g.length; i++) {
			if (g[i].checked) {
				alert(g[i].value);
			}
		}
	}

	function chkHabbit() {
		var h = document.getElementsByName("habbit");

		for (var i = 0; i < h.length; i++) {
			if (h[i].checked) {
				alert(h[i].value);
			}
		}
	}

	function chkWorkyears() {
		var y = document.getElementById("workyears");

		for (var i = 0; i < y.options.length; i++) {
			if (y.options[i].selected) {
				alert(y.options[i].value);
			}
		}
	}

	//鼠标划过显示图片
	function over() {
		document.all.tip.style.display = "block";
	}
	function out() {
		document.all.tip.style.display = "none";
	}
</script>

</head>
<body class="backgroundimg">

	<form name="register_form" method="post" id="register_form" action="show_message.jsp">
		<dl class="lineDl">
			<dt>
				姓名 <b>*</b>
			</dt>
			<dd>
				<div>
					<input id="name" type="text" name="name" value="" maxlength="20" />
				</div>
			</dd>
			<dd>
				<div>
					<label> <input type="radio" name="gender" value="0"
						checked="checked" /> <img src="boypic.gif" width="21" height="25" />
						<input type="radio" name="gender" value="1" /> <img
						src="girlpic.gif" width="21" height="27" />
					</label>
				</div>
			</dd>

		</dl>

		<dl class="lineDl">
			<dt>
				出生日期 <b>*</b>
			</dt>
			<dd>
				<div class="inptext_fl inptyear" id="birthday_year_div">
					<input type="text" id="YearOfBirthday" class="input" value="" /> <span
						class="textIcon">年</span>

				</div>
				<div class="inptext_fl inptmonth" id="birthday_month_div">
					<input type="text" id="MonthOfBirthday" class="input" value="" />
					<span class="textIcon">月</span>

				</div>
				<div class="inptext_fl inptday" id="birthday_day_div">
					<input type="text" id="DayOfBirthday" class="input" value="" /> <span
						class="textIcon">日</span>
				</div>
			</dd>
		</dl>

		<!--  
		<dl class="lineDl" id="phone_box">
			<dt>
				手机号码 <b>*</b>
			</dt>
			<dd>
				<input type="hidden" id="isPhoneBinded" name="isPhoneBinded"
					value="0">
				<div class="inptext">
					<input type="text" id="Mobile" name="Mobile"
						class="input inpWidth226" onblur="chkMobile()" maxlength="20"
						value="">
					<div class="mobile_verify" id="er_phone_msg" style="display: none">
						<i class="inpPromptIcon"></i> <span class="inpTextLink ref_f"></span>
					</div>
					<div class="mobile_verify" id="phone_msg" style="display: none">
						<i class="inpPromptIcon"></i> <span class="inpTextLink ref_f">该手机号码已被他人使用，请</span>
						<a href="javascript:void(0)"
							onclick="Modify_Info('My_ModifyPhone');"
							class="inpTextLink verity_hit">点击认证</a>
					</div>
				</div>
			</dd>
		</dl>
		-->

		<dl class="lineDl">
			<dt>
				Email <b>*</b>
			</dt>
			<dd>
				<div>
					<input type="text" id="email" name="email" />
				</div>
			</dd>
		</dl>

		<dl class="lineDl">
			<dt>
				工作年限 <b>*</b>
			</dt>
			<dd>
				<div>
					<select id="workyears" name="workyears">
						<option value="wy0">在读学生</option>
						<option value="wy1">应届毕业生</option>
						<option value="wy2">1年</option>
						<option value="wy3">2年</option>
						<option value="wy4">3-4年</option>
						<option value="wy5">5-7年</option>
						<option value="wy6">8-9年</option>
					</select>
				</div>
			</dd>
		</dl>

		<dl class="lineDl">
			<dt>
				爱好 <b>*</b>
			</dt>
			<dd>
				<div>
					<input type="checkbox" id="habbit1" name="habbit" value="book" />
					爱读书 <input type="checkbox" id="habbit2" name="habbit" value="sport" />
					爱运动 <input type="checkbox" id="habbit3" name="habbit"
						value="travel" /> 爱旅游 <input type="checkbox" id="habbit4"
						name="habbit" value="music" /> 爱音乐 <input type="checkbox"
						id="habbit5" name="habbit" value="others" /> 爱啥啥……
				</div>
			</dd>
		</dl>

		<dl class="lineDl">
			<dt>
				照片 <b>*</b>
			</dt>
			<dd>
				<div>
					<input type="file" id="picture" name="picture" />
				</div>
			</dd>
		</dl>

		<dl class="lineDl">
			<dt>
				自我介绍 <b>*</b>
			</dt>

			<dd>
				<div>
					<textarea rows=“30” cols="20">
			</textarea>
				</div>
			</dd>
		</dl>

		<dl class="lineDl">
			<dt>
				提交 <b>*</b>
			</dt>
			<dd>
				<div>
					<!-- <input type="submit" value="提交" /> <br/>  -->
					<input type="button" value="自定义提交" onclick="mysubmit()" /> <br /> 
					<input type="submit" value="纯提交" /> <br />
					<input type="reset"  value="重置"/> <br />
					<input
						type="button" value="获得用户名" onclick="chkUserName()" /> <br /> <input
						type="button" value="获得性别" onclick="chkGender()" /> <br /> <input
						type="button" value="获得爱好" onclick="chkHabbit()" /> <br /> <input
						type="button" value="获得工作经验" onclick="chkHabbit()" /> <br />
	
				</div>
			</dd>
		</dl>

		<dl class="lineDl">
			<dt>
				居住地 <b>*</b>
			</dt>
			<dd>
				<div class="inptext_fl inptChoose">
					<div id="Location_div">
						<input id="Location" type="text" class="input" value=""
							placeholder="请选择城市" maxlength="100" /> <input id="Location_code"
							name="Location" type="hidden" value="" />
					</div>
					<a id="location_layer_btn" href="javascript:void(0);"
						class="butChoose">选择<i></i></a>
					<div class="select_layer" id="location_layer"
						style="display: none;"></div>
				</div>
				<div id="Location_err" class="inpPrompt f12" style="display: none;"></div>
			</dd>
		</dl>
	</form>

	<iframe class="player"
		src="http://music.163.com/outchain/player?type=2&id=420400984&auto=0&height=66"></iframe>

	<dl class="picture">
		<a href="#" onmouseover="over()" onmouseout="out()">
			<dt>查看照片</dt>
		</a>
		<dd>
			<div id="tip" style="display: none">
				<table>

					<tr>
						<img src="2.jpg" height=200 width=180
							style="position: absolute; top: 30px; left: 0px;">
					</tr>
				</table>
			</div>
		</dd>
	</dl>


</html>