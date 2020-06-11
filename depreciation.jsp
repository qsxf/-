<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资产折旧</title>
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
<body>

<div style="margin-top: 10px;" class="layui-form">
    <div class="layui-inline">
        <label class="layui-form-label" style="width: 112px">资产名称或编号：</label>
        <div class="layui-input-inline">
            <input type="text" name="param" id="param"  autocomplete="off" class="layui-input" style="width: 211px" >
        </div>
    </div>


    <button class="layui-btn  layui-btn-normal"  data-type="reload"  style=" margin-left: 20px" id="reload">
        <i class="layui-icon layui-icon-search "></i>查询
    </button>

    <div class="layui-inline" style="float: right;">
        <button type="button" class="layui-btn layui-btn-danger" data-type='delbut' id="but2"><i class="layui-icon"></i>删除</button>
        <button type="button" class="layui-btn layui-btn-warm" data-type='exportbut' id="but3"><i class="layui-icon layui-icon-download-circle"></i>导出</button>
    </div>
</div>


<div>
    <table class="layui-hide" id="tableId"  lay-filter="tableFil"></table>
</div>
</body>

<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../layui/lay/modules/table.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../layui/lay/modules/layer.js"></script>
<script src="../js/common.js"></script>
<script src="../js/depreciation/depreciation.js"></script>
<script src="../layui/lay/modules/laydate.js"></script>
<script>

    layui.use(['element'], function(){
        var element = layui.element;

    });
</script>
<script type="text/html" id="ckjob">

   <a href="javascript:;" class="layui-btn layui-btn-sm" id="editbut"  lay-event="detai2">{{ '编辑' }}</a>
</script>
<script>
    var userName="<%= request.getParameter("user") %>"
    infoDepreciation(userName)
</script>
<script>
    $(function(){
        $("#editbut").click(function(){
            //$(window).attr('location',"${pageContext.request.contextPath }/tp/addTp");
            $(window).attr('location',"../view/depreciation/addDepreciation.html");
        });
    });

</script>
</html>