<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
<table>

    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
       <#-- <td>生日</td>-->
    </tr>

    <#--<#if stu??>-->
    <#list stus as stu>
        <tr>
            <td>${stu_index+1}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.mondy}</td>
            <#--<td>${stu.birthday?string("yyyy年MM月dd日")}</td>-->
        </tr>
    </#list>
    <#--</#if>-->
</table>

<br>
遍历map中的数据
<br>1:第一种方式<br/>
姓名:${stuMap.stu1.name}
<br/>2:第二种方式<br/>
姓名:${stuMap['stu1'].name}
<br/>3:第三种方式<br/>
<#list stuMap?keys as stu>

姓名:${stuMap[stu].name}

</#list>

</body>
</html>