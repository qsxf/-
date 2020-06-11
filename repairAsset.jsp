<%--
  Created by IntelliJ IDEA.
  User: duyuuuuy
  Date: 2019/12/17
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>维修清单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link id="layuicss-laydate" rel="stylesheet" href="../layui/css/modules/laydate/default/laydate.css" media="all">
    <link id="layuicss-layer" rel="stylesheet" href="../layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body{
            margin-top: 15px;
        }

        .layui-table-view .layui-form-checkbox[lay-skin=primary] i {
            width: 18px;
            margin-top: 4px;
            height: 18px;
        }


        .layui-btn {
            display: inline-block;
            height: 32px;
            line-height: 32px;
            border-radius: 6px;
        }
        .layui-table-view .layui-form-radio {
            line-height: 1;
            padding: 5px;
        }

        .layui-input-block {
            margin-left: 95px;
            min-height: 40px;
        }

        .layui-form-label {
            padding: 7px 5px;
            width: 85px;
        }
        .layui-input-inline {
            min-height: 40px;
        }

        .layui-form-item {
            margin-bottom: 5px;

        }
    </style>
</head>


<%--<div class="layui-form"   id="repForm" style="display: none;margin-top: 10px;">
</div>--%>

<body>

<div style="margin-top: 10px;" class="layui-form">
    <span id="tiaojian1" >
    <div class="layui-inline">
        <label class="layui-form-label">关键字：</label>
        <div class="layui-input-inline">
            <input type="tel" name="param" id="param" autocomplete="off" class="layui-input" style="width: 211px" placeholder="请输入资产编号或资产名称" >
        </div>
    </div>


    <div class="layui-inline" >
        <label class="layui-form-label">报修时间：</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="bzDate" name="bzDate" placeholder=" - ">
            <%--<input type="text" name="bzDate" id="bzDate"   placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" style="width: 211px">--%>
        </div>
    </div>
    </span>
    <span id="tiaojian2" style="display: none">
         <div class="layui-inline" >
        <label class="layui-form-label">报修时间：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date" placeholder=" - " >
        </div>

    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">资产编号：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam2"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">资产名称：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam3"   autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">维修单号：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam4"  autocomplete="off" class="layui-input" >
        </div>
    </div>

    <div class="layui-inline" >
        <label class="layui-form-label">报修人：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam5"  autocomplete="off" class="layui-input"  >
        </div>
    </div>


    <div class="layui-inline" >
        <label class="layui-form-label">归属公司：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam6"  autocomplete="off" class="layui-input" lay-verify="Qq">
        </div>
    </div>
    </span>
    <button class="layui-btn  layui-btn-normal"  data-type="reload"  style=" margin-left: 20px" id="reload">
        <i class="layui-icon layui-icon-search "></i>查询
    </button>
    <div class="layui-inline" style="margin-left: 10px;height: 20px">
        <div class="layui-input-inline" >
            <input type="checkbox" id="duoxuan" name="duoxuan" lay-skin="primary" title="高级查询" lay-filter="duoxuan" style="height: 20px" >
        </div>
    </div>
    <div>
        <!--<button type="button" class="layui-btn layui-btn-normal" data-type='addbut' id="but1">
            新增</button>-->
        <button type="button" class="layui-btn layui-btn-danger" data-type='delt' id="but1"> <i class="layui-icon"></i>删除</button>
        <button type="button" class="layui-btn layui-btn-warm" data-type='exportbut' id="but2"><i class="layui-icon layui-icon-download-circle"></i>导出</button>
    </div>
</div>


<div>
    <table class="layui-hide" id="user"  lay-filter="user"></table>
</div>
</body>

<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../layui/lay/modules/table.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../layui/lay/modules/layer.js"></script>
<script src="../js/common.js"></script>
<script src="../js/repairAsset/repairAsset.js"></script>
<script src="../layui/lay/modules/laydate.js"></script>
<script>

    layui.use(['form','element'], function(){
        var element = layui.element;
        var form = layui.form;
        form.render();
    });
</script>
<script type="text/html" id="ckjob">

    <a href="javascript:;" class="layui-btn layui-btn-sm" id="detai2"  lay-event="detai2">{{ '编辑' }}</a>
</script>
<script>
    var userName="<%= request.getParameter("user") %>"

    infoRepairAsset(userName)

    $(function(){
        $("#detai2").click(function(){
            $(window).attr('location',"../view/repairAsset/addRepairAsset.html");
        });
    });

</script>
</html>
