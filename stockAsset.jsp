<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资产入库</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link id="layuicss-laydate" rel="stylesheet" href="../layui/css/modules/laydate/default/laydate.css" media="all">
    <link id="layuicss-layer" rel="stylesheet" href="../layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body{
            /*margin-top: 15px;*/
            margin-left: 10px;
            margin-right: 10px;
            /*background-color: #f5f6fa !important;*/
            border-color: #e6e6e6;
        }
        .layui-table-view .layui-form-checkbox[lay-skin=primary] i {
            width: 18px;
            margin-top: 4px;
            height: 18px;
        }
        .divradio .layui-form-radio >i{
            margin-right: 5px;
            font-size: 16px;
        }
        .layui-table-view .layui-form-radio {
            line-height: 1;
            padding: 5px;
        }

        .layui-btn {
            display: inline-block;
            height: 32px;
            line-height: 32px;
            border-radius: 6px;
            margin-left: 0px;
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
            <input type="text" id="param" name="keyword" lay-verify="required" autocomplete="off" class="layui-input" style="width: 322px" placeholder="请输入资产编号、名称、二级分类、品牌或责任人">
        </div>
    </div>


    <div class="layui-inline">
        <label class="layui-form-label">入库时间：</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="bzDate" name="bzDate" placeholder=" - ">
        </div>
        <%--<div class="layui-input-inline">
            <input type="text" name="bzDate1" id="bzDate1" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" style="width: 211px">
        </div>
        <div class="layui-input-inline">
            -
        </div>
        <div class="layui-input-inline">
            <input type="text" name="bzDate2" id="bzDate2" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" style="width: 211px">
        </div>--%>
    </div>
    </span>
    <span id="tiaojian2" style="display: none">
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
        <label class="layui-form-label">一级分类：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam4"  autocomplete="off" class="layui-input" >
        </div>
    </div>

    <div class="layui-inline">
        <label class="layui-form-label">二级分类：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam5"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">品牌：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam6"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">型号：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam7"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">单位：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam8"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">入账日期：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date1" placeholder=" - " >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">购买日期：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date2" placeholder=" - " >
        </div>
    </div>

    <div class="layui-inline">
        <label class="layui-form-label">责任人：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam14"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">填写时间：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date3" placeholder=" - " >
        </div>
    </div>
    <%--<div class="layui-form-item">
        <label class="layui-form-label" style="width: 140px;">使用部门：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam16"  autocomplete="off" class="layui-input"  >
        </div>
    </div>--%>
    <div class="layui-inline">
        <label class="layui-form-label">存放地点：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam17"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <%--<div class="layui-form-item">
        <label class="layui-form-label" style="width: 140px;">备注：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam18"  autocomplete="off" class="layui-input"  >
        </div>
    </div>--%>

    <div class="layui-inline">
        <label class="layui-form-label">归属公司：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam19"  autocomplete="off" class="layui-input" lay-verify="Qq">
        </div>
    </div>
        <div class="layui-inline">
        <label class="layui-form-label">购买价格（元）：</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam11p" name="price_min" placeholder="￥" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">-</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam11l" name="price_max" placeholder="￥" autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-input-inline">
            <input type="text" id="pam11"  autocomplete="off" class="layui-input"  >
        </div>--%>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">质保期（年）：</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam12p" name="price_min"  autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">-</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam12l" name="price_max"  autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-input-inline">
            <input type="text" id="pam12"  autocomplete="off" class="layui-input"  >
        </div>--%>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">预计使用寿命（年）：</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam13p" name="price_min"  autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">-</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam13l" name="price_max"  autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-input-inline">
            <input type="text" id="pam13"  autocomplete="off" class="layui-input"  >
        </div>--%>
    </div>
    </span>
    <button class="layui-btn  layui-btn-normal"  data-type="reload"  id="reload">
        <i class="layui-icon layui-icon-search "></i>查询
    </button>
    <div class="layui-inline" style="margin-left: 10px;height: 20px">
        <div class="layui-input-inline" >
            <input type="checkbox" id="duoxuan" name="duoxuan" lay-skin="primary" title="高级查询" lay-filter="duoxuan" style="height: 20px" >
        </div>
    </div>
    <div>

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
<script src="../js/stockAsset/stockAsset.js"></script>
<script src="../layui/lay/modules/laydate.js"></script>
<script>

    layui.use(['form','element'], function(){
        var element = layui.element;
        var form = layui.form;
        form.render();
    });
</script>
<script type="text/html" id="ckjob">
    <a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal " id="detai1"  lay-event="detai1">{{ '详情' }}</a>
    &nbsp;&nbsp;
    <a href="javascript:;" class="layui-btn layui-btn-sm" id="detai2"  lay-event="detai2">{{ '编辑' }}</a>
</script>
<script>


</script>
<script>
    var userName="<%= request.getParameter("user") %>"
    infoStockAsset(userName);
</script>
</html>