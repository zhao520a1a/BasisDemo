<%--
  ajax自动补全实例：返回的xml数据
--%>
<!--与传统应用的视图层不同 ，这个jsp返回的是xml的数据，因此contentType的值是text/xml-->
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<!--返回xml数据的‘视图层暂时不做任何逻辑判断，先将所有单词都返回，待前后台应用可以完整的协作之后，再限制返回的内容’-->
<words>
    <%
        String word = (String) request.getAttribute("word");
    %>
    <% if ("absolute".startsWith(word)) {
    %>
    <word>absolute</word>
    <%
        }
    %>
    <%
        if ("anyone".startsWith(word)) {
    %>
    <word>anyone</word>
    <%
        }
    %>
    <%
        if ("anything".startsWith(word)) {
    %>
    <word>anything</word>
    <%
        }
    %>  <%
    if ("apple".startsWith(word)) {
%>
    <word>apple</word>
    <%
        }
    %>  <%
    if ("abandon".startsWith(word)) {
%>
    <word>abandon</word>
    <%
        }
    %>  <%
    if ("breach".startsWith(word)) {
%>
    <word>breach</word>
    <%
        }
    %>
    <%
        if ("break".startsWith(word)) {
    %>
    <word>break</word>
    <%
        }
    %>
    <%
        if ("boolean".startsWith(word)) {
    %>
    <word>boolean</word>
    <%
        }
    %>

</words>
