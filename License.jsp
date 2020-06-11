<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>license分配</title>
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

        .layui-table-cell .layui-form-checkbox[lay-skin=primary] {
            top: 1px;
            padding: 0;
        }

        .layui-btn {
            display: inline-block;
            height: 32px;
            line-height: 32px;
            border-radius: 6px;
        }

    </style>
</head>
<body>
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">License分配</li>

        </ul>
        <div class="layui-tab-content">
            <input type="text" id="titleid" disabled="disabled"   style="border-style:none"  autocomplete="off" class="layui-input" >
        </div>
        <div class="layui-tab-content" style="height: 100px;">
            <div class="layui-tab-item layui-show">
                <div style="margin-top: 10px;" class="layui-form">

                    <div class="layui-inline" >

                        <button class="layui-btn layui-btn-normal" data-type='jfaddbut' id="jfbut1">
                            <i class="layui-icon layui-icon-add-1"></i> 添加</button>
                        <button class="layui-btn  " data-type="jfedit" id="jfbut2">
                            <i class="layui-icon layui-icon-edit"></i> 修改</button>
                        <button class="layui-btn layui-btn-danger" data-type="jfdelt" id="jfbut3">
                            <i class="layui-icon"></i> 删除</button>

                        <button class="layui-btn  layui-btn-warm" id="cancel"  data-type="cancel">
                            <i class="layui-icon"></i>返回</button>
                    </div>
                </div>

                <div>
                    <table class="layui-hide" id="table1"  lay-filter="table1"></table>
                </div>
            </div>

        </div>
    </div>
</body>
<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../layui/lay/modules/table.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../layui/lay/modules/layer.js"></script>
<script src="../js/common.js"></script>
<script src="../js/intangibleAsset/license.js"></script>

<script src="../layui/lay/modules/laydate.js"></script>
<script>

    layui.use(['form','element'], function(){
        var element = layui.element;
        var form = layui.form;
        form.render();
    });
</script>
<script>
    var userName="<%= request.getParameter("user") %>"
    license(userName);


</script>
</html>