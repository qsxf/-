<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>无形资产台账</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <link id="layuicss-laydate" rel="stylesheet" href="../../layui/css/modules/laydate/default/laydate.css" media="all">
    <link id="layuicss-layer" rel="stylesheet" href="../../layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body{
            /*margin-top: 15px;*/
            /* margin-left: 10px;*/
            /*margin-right: 50px;*/
            /*background-color: #f5f6fa !important;*/
            border-color: #F2F2F2;
            background-color: #F2F2F2;
        }

        .divradio .layui-form-radio >i{
            margin-right: 5px;
            font-size: 16px;
        }
        .layui-input, .layui-textarea {
            display: block;
            width: 170px;
            padding-left: 10px;
        }

        .layui-btn {
            display: inline-block;
            height: 32px;
            line-height: 32px;
            border-radius: 6px;
        }
        /*.layui-btn {*/

        /*height: 35px;*/
        /*line-height: 35px;*/
        /*padding: 0 11px;*/
        /*font-size: 13px;*/
        /*}*/

        .layui-input-block {
            margin-left: 95px;
            min-height: 40px;
        }

        .layui-form-label {
            padding: 7px 5px;
            width: 112px;
        }
        .layui-input-inline {
            min-height: 40px;
        }
    </style>
</head>
<body>
<div style="margin-top: 40px;margin-left: 30px">
    <i style="font-size: 24px;font-weight: bold;font-family: SimHei;font-style: normal">新增无形资产</i>
</div>
<div style="padding: 20px;">
    <div class="layui-row layui-col-space15">
        <div class="">
            <div class="layui-card" style="border: solid 2px #8D8D8D;width: 100%">

                <div class="layui-card-body" style="margin-top: 20px;margin-left: 20px;">
                    <form class="layui-form" action="" lay-filter="upFrom"    id="upFrom">

                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>资产编号：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="ASSETS_ID" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>资产名称：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="ASSETS_NAME" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>品牌：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="BRAND" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>数量：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="NUM" lay-verify="required|number" autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>单位：</label>
                                <div class="layui-input-inline">
                                    <select name="UNIT" lay-verify="required" id="UNIT">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>

                            <div class="layui-inline" >
                                <label class="layui-form-label">license期限：</label>
                                <div class="layui-input-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="LICENSELIMIT" id="date1"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-inline" >
                                <label class="layui-form-label">license个数：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="LICENSEAMT"  autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">规格型号：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="TYPE_NAME"  autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>使用状态：</label>
                                <div class="layui-input-inline">
                                    <select name="STATUS" lay-verify="required" id="STATUS">
                                        <option value="">请选择</option>
                                    </select>

                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>归属公司：</label>
                                <div class="layui-input-inline">
                                    <select name="ATTRIBUTION" lay-verify="required" id="COMPANY"  lay-filter="COMPANY">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label">供应商：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="BUY_DEPARTMENT"  autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                        <div class="layui-inline" >
                            <label class="layui-form-label">购买价格（元）：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="PRICE"  autocomplete="off" class="layui-input" placeholder="请输入">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label"><span style="color:red">*</span>归属部门：</label>
                            <div class="layui-input-inline">
                                <select name="USE_DEPARTMENT"  id="gsgs">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>




                        <div class="layui-inline" >
                            <label class="layui-form-label"><span style="color:red">*</span>存放地点：</label>
                            <div class="layui-input-inline">

                                <select name="LOCATION" lay-verify="required" id="CFDD">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label"><span style="color:red">*</span>责任人：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="RESPMAN" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">
                            </div>
                        </div>

                            <div class="layui-inline" >
                                <label class="layui-form-label"><span style="color:red">*</span>入账日期：</label>
                                <div class="layui-input-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="POSTINGDATE" id="date2" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>

                            <div class="layui-inline" >
                            <label class="layui-form-label">购买日期：</label>
                            <div class="layui-input-inline">
                                <div class="layui-input-inline">
                                    <input type="text" name="PURCHASEDATE" id="date3" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item" style="text-align: center;margin-top: 30px">
                            <div class="layui-input-block">
                                <button type="submit" class="layui-btn" lay-submit lay-filter="reSubmit" data-type="">保存</button>
                                <button type="button" class="layui-btn layui-btn-primary" data-type="cancel" id="cancel">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="../../layui/layui.js" charset="utf-8"></script>
<script src="../../layui/lay/modules/table.js"></script>
<script src="../../js/jquery-3.3.1.min.js"></script>
<script src="../../layui/lay/modules/layer.js"></script>
<script src="../../js/common.js"></script>
<script src="../../layui/lay/modules/laydate.js"></script>
<script>
    layui.use(['form','element'], function(){
        var element = layui.element;
        var form = layui.form;
        form.render();
    });
</script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        let param = new URLSearchParams(location.search);
        //onst userName = param.get('userName');

        const val1 = param.get('userName');
        var laydate = layui.laydate;
        var form = layui.form;
        var $ = layui.$, active = {
            cancel: function () {
                window.location.href = "../intangibleAsset.jsp?user=" + val1;

            },
        }
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#cancel').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        /*$.ajax({
            url:"../../stockAsset/selBM.do",
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {
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
                    }else {
                        $("#COMPANY").append(new Option(compnms[i] ));
                        $("#gsgs").append("<optgroup label="+compnms[i]+">");
                        for (var j = 0;j<data.length;j++){
                            if (compnms[i] == data[j].COMPNM){
                                $("#gsgs").append(" <option value='"+data[j].NAME+"'>"+data[j].NAME+"</option>");
                            }
                        }
                        $("#gsgs").append("</optgroup>");
                        form.render();
                    }
                }

            }

        });*/
        form.on("select(COMPANY)",function () {
            var optionstring = "";
            var cate1 = $("#COMPANY").val();
            $.ajax({
                url:"../../stockAsset/selBM.do",
                contentType:"application/json",
                type:"post",
                dataType:"json",
                beforeSend:"",
                data:JSON.stringify(),
                success: function(data) {
                    $("select[name='USE_DEPARTMENT']").empty();
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
                    for (var i = 0; i <compnms.length; i++) {
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
            $.ajax({
                url:"../../stockAsset/selCFDD.do?company="+cate1,
                contentType:"application/json",
                type:"post",
                dataType:"json",
                beforeSend:"",
                data:JSON.stringify(),
                success: function(data) {
                    $("select[name='LOCATION']").empty();

                    for (var i = 0; i <data.length; i++) {
                        $("#CFDD").append((new Option(data[i].TYPENM )));
                        form.render();
                    }
                }
            });
        })
        $.ajax({
            url:"../../stockAsset/selGSGS.do?uname="+val1,
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {

                for (var i = 0; i <data.length; i++) {
                    $("#COMPANY").append((new Option(data[i].COMPNM )));
                    form.render();
                }
            }
        });
        $.ajax({
            url:"../../intangibleAsset/selWXDW.do",
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {

                for (var i = 0; i <data.length; i++) {
                    $("#UNIT").append((new Option(data[i].TYPENM )));
                    form.render();
                }
            }
        });
        $.ajax({
            url:"../../intangibleAsset/selWXZT.do",
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {

                for (var i = 0; i <data.length; i++) {
                    $("#STATUS").append(" <option value='"+data[i].TYPECD+"'>"+data[i].TYPENM+"</option>");
                    form.render();
                }
            }
        });
        /*$.ajax({
            url:"../../stockAsset/selBM.do",
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {
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
                for (var i = 0; i <compnms.length; i++) {
                    if (compnms[i]==null){
                        return
                    }else {
                        $("#COMPANY").append(new Option(compnms[i] ));
                        // $("#gsgs").append("<optgroup label="+compnms[i]+">");
                        // for (var j = 0;j<data.length;j++){
                        //    if (compnms[i] == data[j].COMPNM){
                        //        $("#gsgs").append(" <option value='"+data[j].NAME+"'>"+data[j].NAME+"</option>");
                        //    }
                        // }
                        //$("#gsgs").append("</optgroup>");
                        form.render();
                    }
                }
            }

        });*/

        laydate.render({
            elem: '#date1' //指定元素
            ,showBottom: false
        });
        laydate.render({
            elem: '#date2' //指定元素
            ,showBottom: false
        });
        laydate.render({
            elem: '#date3'
            ,showBottom: false
        });

        // 提交按钮
        form.on('submit(reSubmit)', function (data) {
            var parameter = data.field;
            var indexlod = layer.load(1);
            var parData = [{"name":"资产编号","valu1":parameter.ASSETS_ID},
                {"name":"资产名称","valu1":parameter.ASSETS_NAME},
                {"name":"品牌","valu1":parameter.BRAND},
                {"name":"数量","valu1":parameter.NUM},
                {"name":"license期限","valu1":parameter.LICENSELIMIT},
                {"name":"license个数","valu1":parameter.LICENSEAMT},
                {"name":"供应商","valu1":parameter.BUY_DEPARTMENT},
                {"name":"责任人","valu1":parameter.RESPMAN},
                {"name":"购买价格（元）","valu1":parameter.PRICE},
                {"name":"归属公司","valu1":parameter.ATTRIBUTION},
                {"name":"使用部门","valu1":parameter.USE_DEPARTMENT},
                {"name":"入账日期","valu1":parameter.POSTINGDATE},
                {"name":"购买日期","valu1":parameter.PURCHASEDATE}
            ];
            parameter["uname"]=val1;
            queryData('../../intangibleAsset/addIntangibleAsset.do', parameter, function (date) {
                if (date.code == '0') {

                    var fieldName="";
                    var fieldAfter='';
                    $.each(parData, function () {

                        fieldName+=this.name+","
                        fieldAfter+=this.valu1+","
                    });
                    var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                    var param={'operType':'新增','operPage':'无形资产信息','operTable':'IT_ASSET_INTANGIBLEASSET','descr':strDescr,'createName':val1}
                    queryData('../../log/addlog.do', param, function (redt) {});

                    layer.alert(date.msg, {icon: 1});
                    window.location.href = "../intangibleAsset.jsp?user=" + val1;
                   // $(window).attr('location', "../intangibleAsset.jsp");
                }
            });
            layer.close(indexlod);
            return false;
        });

    });


    // 取消按钮
    /*$(function () {
        $("#cancel").click(function () {
            $(window).attr('location', "../intangibleAsset.jsp");
        });
    });*/

    // 获取对接系统接口

</script>
</html>