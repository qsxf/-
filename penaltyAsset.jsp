<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资产罚则记录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link id="layuicss-laydate" rel="stylesheet" href="../layui/css/modules/laydate/default/laydate.css" media="all">
    <link id="layuicss-layer" rel="stylesheet" href="../layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body {
            /*margin-top: 15px;*/
            margin-left: 10px;
            margin-right: 10px;
            /*background-color: #f5f6fa !important;*/
            border-color: #e6e6e6;
        }

        .divradio .layui-form-radio > i {
            margin-right: 5px;
            font-size: 16px;
        }


        .layui-btn {
            display: inline-block;
            height: 32px;
            line-height: 32px;
            border-radius: 6px;
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
            <input type="text" name="param" id="param" lay-verify="required" autocomplete="off" class="layui-input" style="width: 230px" placeholder="请输入被罚则人、资产编号或名称">
        </div>
    </div>
    <div class="layui-inline" style="margin-left: 30px">
        <label class="layui-form-label">罚则日期：</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="bzDate" name="bzDate" placeholder=" - ">
            <%--<input type="text" name="bzDate" id="bzDate"   placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" style="width: 211px">--%>
        </div>
    </div>
    </span>
    <span id="tiaojian2" style="display: none">
     <div class="layui-inline">
        <label class="layui-form-label">罚则日期：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date" placeholder=" - " >
        </div>

    </div>
    <div class="layui-inline">
        <label class="layui-form-label">资产编号：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam2"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">资产名称：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam3"   autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">罚则编号：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam4"  autocomplete="off" class="layui-input" >
        </div>
    </div>

    <div class="layui-inline">
        <label class="layui-form-label">罚则原因：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam5"  autocomplete="off" class="layui-input"  >
        </div>
    </div>


    <div class="layui-inline">
        <label class="layui-form-label">罚则金额：</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam6p" name="price_min" placeholder="￥" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">-</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam6l" name="price_max" placeholder="￥" autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-input-inline">
            <input type="text" id="pam6"  autocomplete="off" class="layui-input" lay-verify="Qq">
        </div>--%>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">被罚则人：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam7"  autocomplete="off" class="layui-input" lay-verify="Qq">
        </div>
    </div>

    <div class="layui-inline">
        <label class="layui-form-label">所属公司：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam8"  autocomplete="off" class="layui-input" lay-verify="Qq">
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
    <div class="layui-inline">
        <button type="button" class="layui-btn layui-btn-normal" data-type='addbut' id="but2">
            <i class="layui-icon"></i> 新增</button>
        <button type="button" class="layui-btn layui-btn-danger" data-type='delt' id="but1"> <i class="layui-icon"></i>删除</button>
        <button type="button" class="layui-btn layui-btn-warm" data-type='exportbut' id="but3"><i class="layui-icon layui-icon-download-circle"></i>导出</button>
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
<script src="../js/penaltyAsset/penaltyAsset.js"></script>
<script src="../layui/lay/modules/laydate.js"></script>
<script>

    layui.use(['element'], function () {
        var element = layui.element;

    });
</script>
<script type="text/html" id="ckjob">
    <!-- <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal " id="detail"  lay-event="detail">{{ '详情' }}</a>
     &nbsp;&nbsp;-->
    <a href="javascript:;" class="layui-btn layui-btn-sm " id="detai2" lay-event="detai2">{{ '编辑' }}</a>
</script>
<script>
    var userName="<%= request.getParameter("user") %>"
    infoPenaltyAsset(userName)
</script>
<script>
    /*$(function () {
        $("#but2").click(function () {
            $(window).attr('location', "../view/penaltyAsset/addPenaltyAsset.jsp");
        });
    });*/

</script>
</html>