<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资产入库</title>
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
        width: 200px;
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
    <i style="font-size: 24px;font-weight: bold;font-family: SimHei;font-style: normal">新增资产入库</i>
</div>
<div style="padding: 20px;">
    <div class="layui-row layui-col-space15">
        <div class="" >
            <div class="layui-card" style="border: solid 2px #8D8D8D;width: 100%">

                <div class="layui-card-body" style="margin-top: 20px">
                    <form class="layui-form" action="" lay-filter="upFrom"    id="upFrom">

                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>资产编号：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="ASSETS_ID" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>资产名称：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="ASSETS_NAME" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>一级分类：</label>
                                <div class="layui-input-inline">
                                    <!-- <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" style="width: 211px">-->
                                    <select name="ASSETCLASS_1" lay-verify="required" id="functionCode">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>二级分类：</label>
                                <div class="layui-input-inline">
                                    <!-- <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" style="width: 211px">-->
                                    <select name="ASSETCLASS_2" lay-verify="required" id="functionCode2">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>品牌：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="BRAND" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>规格型号：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="TYPE_NAME" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>数量：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="NUM" lay-verify="required|number" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>单位：</label>
                                <div class="layui-input-inline">
                                    <select name="UNIT" lay-verify="required" id="UNIT">
                                        <option value="">请选择</option>
                                    </select>
<%--
                                    <input type="text" name="UNIT" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
--%>
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>入账日期：</label>
                                <div class="layui-input-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="POSTINGDATE" id="date1" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">购买日期：</label>
                                <div class="layui-input-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="PURCHASEDATE" id="date2" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>质保期(年)：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="WARRANTY" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>预计使用寿命(年)：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="SERVICELIFE" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>

                            <%--<div class="layui-inline">
                                <label class="layui-form-label">使用部门：</label>
                                <div class="layui-input-inline">
                                    <!-- <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" style="width: 211px">-->
                                    <select name="ATTRIBUTION" lay-verify="required" id="gsgs" lay-filter="gsgs">
                                        &lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;
                                    </select>
                                </div>
                            </div>--%>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>责任人：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="RESPMAN" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">购买价格（元）：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="PRICE" lay-verify="number" autocomplete="off" class="layui-input"  placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>归属公司：</label>
                                <div class="layui-input-inline">
<%--
                                    <input type="text" name="COMPANY" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
--%>
                                    <div class="layui-input-inline">
                                        <select name="COMPANY" lay-verify="required" id="COMPANY" lay-filter="COMPANY">

                                    </select>
                                    </div>
                                </div>
                            </div>
                        <div class="layui-inline">
                            <label class="layui-form-label"><span style="color:red">*</span>存放地点：</label>
                            <div class="layui-input-inline">
                                <select name="LOCATION" lay-verify="required" id="CFDD">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>

<!--                            <div class="layui-inline" style="margin-bottom: 20px">-->
<!--                                <label class="layui-form-label">入库时间：</label>-->
<!--                                <div class="layui-input-inline">-->
<!--                                    <div class="layui-input-inline">-->
<!--                                        <input type="text" name="INCOMING_TIME" id="date3" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </div>-->
                            <!--<div class="layui-inline" style="margin-bottom: 20px">
                                <label class="layui-form-label">入库人：</label>
                                <div class="layui-input-inline">
                                    <div class="layui-input-inline">
                                        <input type="text" name="USEDESC" lay-verify="required|phone" autocomplete="off" class="layui-input"  placeholder="请输入">
                                    </div>
                                </div>
                            </div>-->
                            <div class="layui-inline">
                                <label class="layui-form-label">备注：</label>
                                <div class="layui-input-inline" >
                                    <div class="layui-input-inline">
                                        <textarea name="USEDESC" placeholder="请输入"  class="layui-textarea" ></textarea>
                                    </div>
                                </div>
                            </div>


                        <!--<div class="layui-form-item layui-form-text">
                          <label class="layui-form-label">编辑器</label>
                          <div class="layui-input-block">
                            <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="LAY_demo_editor"></textarea>
                          </div>
                        </div>-->
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

</script>
<script>
    var userName="<%= request.getParameter("user") %>"
    layui.use(['table', 'form', 'layer','laydate', 'layedit'], function() {

        let param = new URLSearchParams(location.search);
        // const userName = param.get('userName');

        const val1 = param.get('userName');




        var laydate = layui.laydate;
        var form = layui.form;

        var $ = layui.$, active = {
            cancel: function () {
                window.location.href = "../stockAsset.jsp?user=" + val1;

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
            url:"../../stockAsset/selDW.do",
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
            url:"../../stockAsset/selYJFL.do",
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {

                for (var i = 0; i <data.length; i++) {
                    $("#functionCode").append((new Option(data[i].TYPENM )));
                    form.render();
                }
            }
        });

        $.ajax({
            url:"../../stockAsset/selEJFL.do",
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {

                for (var i = 0; i <data.length; i++) {
                    $("#functionCode2").append((new Option(data[i].TYPENM )));
                    form.render();
                }
            }
        });
        form.on("select(COMPANY)",function () {
            var optionstring = "";
            var cate1 = $("#COMPANY").val();
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
                    form.render();
                }
            });

        })


       /* $.ajax({
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

        form.val('upFrom', {

        });

        form.on('submit(reSubmit)', function (data) {
            var parameter = data.field;
            var indexlod = layer.load(1);
            var parData = [{"name":"资产编号","valu1":parameter.ASSETS_ID},
                {"name":"资产名称","valu1":parameter.ASSETS_NAME},
                {"name":"一级分类","valu1":parameter.ASSETCLASS_1},
                {"name":"二级分类","valu1":parameter.ASSETCLASS_2},
                {"name":"品牌","valu1":parameter.BRAND},
                {"name":"规格型号","valu1":parameter.TYPE_NAME},
                {"name":"数量","valu1":parameter.NUM},
                {"name":"单位","valu1":parameter.UNIT},
                {"name":"入账日期","valu1":parameter.POSTINGDATE},
                {"name":"购买日期","valu1":parameter.PURCHASEDATE},
                {"name":"质保期(年)","valu1":parameter.WARRANTY},
                {"name":"预计使用寿命(年)","valu1":parameter.SERVICELIFE},
                {"name":"使用部门","valu1":parameter.ATTRIBUTION},
                {"name":"责任人","valu1":parameter.RESPMAN},
                {"name":"购买价格","valu1":parameter.PRICE},
                {"name":"归属公司","valu1":parameter.COMPANY},
                {"name":"备注","valu1":parameter.USEDESC}
            ];
            parameter["uname"]=val1;
            queryData('../../stockAsset/addStockAsset.do', parameter, function (red) {
                if (red.code == '0') {

                    var fieldName="";
                    var fieldAfter='';
                    $.each(parData, function () {

                        fieldName+=this.name+","
                        fieldAfter+=this.valu1+","
                    });
                    var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                    var param={'operType':'新增','operPage':'资产入库信息','operTable':'IT_ASSET_FIXASSET','descr':strDescr,'createName':val1}
                    queryData('../../log/addlog.do', param, function (redt) {});

                    layer.alert(red.msg, {icon: 1});
                    window.location.href = "../stockAsset.jsp?user=" + val1;
                    //$(window).attr('location',"../stockAsset.jsp");
                }


            })


            layer.close(indexlod);
            return false;
        });

    });

    /*$(function(){

        $("#cancel").click(function(){

            $(window).attr('location',"../stockAsset.jsp");
        });
    });*/
</script>
</html>