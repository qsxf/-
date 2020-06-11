<%--
  Created by IntelliJ IDEA.
  User: qsxf
  Date: 2019/12/19
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>资产盘点</title>
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
        .layui-table-view .layui-form-checkbox[lay-skin=primary] i {
            width: 18px;
            margin-top: 4px;
            height: 18px;
        }
        .layui-input-block {
            margin-left: 95px;
            min-height: 40px;
        }
        .layui-form-label {
            padding: 7px 5px;
            width: 99px;
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
            <input type="text" name="param" id="param"  autocomplete="off" class="layui-input" placeholder="请输入盘点单号或盘点人">
        </div>
    </div>


    <div class="layui-inline" >
        <label class="layui-form-label">计划盘点时间：</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="bzDate" name="bzDate" placeholder=" - ">
            <%--<input type="text" name="bzDate" id="bzDate"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">--%>
        </div>
    </div>
    </span>
    <span id="tiaojian2" style="display: none">
 <div class="layui-inline" >
        <label class="layui-form-label">盘点单号：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam1"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">盘点范围：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam2"   autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">计划盘点时间：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date3" placeholder=" - " >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">计划完成时间：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date4" placeholder=" - " >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">实际盘点时间：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date5" placeholder=" - " >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">实际完成时间：</label>
        <div class="layui-input-inline">
            <input type="text"  class="layui-input" autocomplete="off" id="date6" placeholder=" - " >
        </div>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">盘点人：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam7"  autocomplete="off" class="layui-input"  >
        </div>
    </div>
        <div class="layui-inline" >
        <label class="layui-form-label">归属公司：</label>
        <div class="layui-input-inline">
            <input type="text" id="pam11"  autocomplete="off" class="layui-input"  >
        </div>
    </div>

    <div class="layui-inline" >
        <label class="layui-form-label">应盘数：</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam9p" name="price_min"  autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">-</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam9l" name="price_max"  autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-input-inline">
            <input type="text" id="pam12"  autocomplete="off" class="layui-input"  >
        </div>--%>
    </div>
    <div class="layui-inline" >
        <label class="layui-form-label">盘存数：</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam10p" name="price_min"  autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">-</div>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" id="pam10l" name="price_max"  autocomplete="off" class="layui-input">
        </div>
        <%--<div class="layui-input-inline">
            <input type="text" id="pam12"  autocomplete="off" class="layui-input"  >
        </div>--%>
    </div>
<div class="layui-inline" >
        <label class="layui-form-label">状态：</label>
        <div class="layui-input-inline">
            <input type="radio" id="pam8" name="sex" value="1" title="已盘" >
            <input type="radio"  id="pam8" name="sex" value="0" title="未盘">
            <%--<input type="text" id="pam8"  autocomplete="off" class="layui-input"  >--%>
        </div>
    </div>

    </span>
    <button class="layui-btn  layui-btn-normal"  data-type="reload"  style="" id="reload">
        <i class="layui-icon layui-icon-search "></i>查询
    </button>
    <div class="layui-inline" style="margin-left: 10px;height: 20px">
        <div class="layui-input-inline" >
            <input type="checkbox" id="duoxuan" name="duoxuan" lay-skin="primary" title="高级查询" lay-filter="duoxuan" style="height: 20px" >
        </div>
    </div>
    <div>
        <button type="button" class="layui-btn layui-btn-normal" data-type='addbut' id="but1"><i class="layui-icon"></i>新增</button>
        <button type="button" class="layui-btn layui-btn-danger" data-type='delt' id="delt"> <i class="layui-icon"></i>删除</button>
        <button type="button" class="layui-btn" id="but3" data-type='pandian'><i class="layui-icon"></i>盘点</button>
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
<script src="../js/inventoryAsset/inventoryAsset.js"></script>
<script src="../layui/lay/modules/laydate.js"></script>
<script>

    layui.use(['form','element'], function(){
        var element = layui.element;
        var form = layui.form;
        form.render();
    });
</script>
<%--<script type="text/html" id="ckjob">
    <a href="javascript:;" class="layui-btn layui-btn-sm" id="detai2"  lay-event="detai2">{{ '盘点' }}</a>
</script>--%>
<script>
    var userName="<%= request.getParameter("user") %>"
    infoinventoryAsset(userName)
    $.ajax({
        url:"../stockAsset/selGS.do?uname="+userName,
        contentType:"application/json",
        type:"post",
        dataType:"json",
        beforeSend:"",
        data:JSON.stringify(),
        success: function(data) {
            $("select[name='sqgs']").empty();
            $("#COMPANY").val(data[0].COMPNM);

            $.ajax({
                url:"../../stockAsset/selCFDD.do?company="+data[0].COMPNM,
                contentType:"application/json",
                type:"post",
                dataType:"json",
                beforeSend:"",
                data:JSON.stringify(),
                success: function(data) {
                    $("select[name='dbdz']").empty();

                    for (var i = 0; i <data.length; i++) {
                        $("#CFDD").append((new Option(data[i].TYPENM )));
                        form.render();
                    }
                    $("#CFDD").val(val7);
                    form.render();
                }

            });
            form.render();
        }
    });



    $.ajax({
        url:"../stockAsset/selBM.do",
        contentType:"application/json",
        type:"post",
        dataType:"json",
        beforeSend:"",
        data:JSON.stringify(),
        success: function(data) {
            var cate1 = $("#COMPANY").val();
            $("select[name='sqbm']").empty();
            var compnms = [];
            for (var i=0;i<data.length;i++){
                compnms[i] = data[i].COMPNM;
            }

            for(var i=0;i<compnms.length;i++){
                for(var j=i+1;j<compnms.length;j++){
                    if(compnms[i]==compnms[j]){
                        //如果第一个等于第二个，splice方法删除第二个
                        compnms.splice(j,1);
                        j--;
                    }
                }
            }
            for (var i = 0; i <data.length; i++) {
                if (compnms[i]==null){
                    return
                }else if(compnms[i]==cate1){
                    // $("#COMPANY").append(new Option(compnms[i] ));
                    // $("#gsgs").append("<optgroup label="+compnms[i]+">");
                    for (var j = 0;j<data.length;j++){
                        if (compnms[i] == data[j].COMPNM){
                            $("#gsgs").append(" <option value='"+data[j].NAME+"'>"+data[j].NAME+"</option>");
                        }
                    }
                    // $("#gsgs").append("</optgroup>");
                    form.render();
                }
            }

        }

    });
</script>
<script>
    /*$(function(){
        $("#but1").click(function(){
            $(window).attr('location',"../view/inventoryAsset/addInventoryAsset.jsp");
        });
    });*/

</script>
</html>