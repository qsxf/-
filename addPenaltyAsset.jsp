<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资产罚则记录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <link id="layuicss-laydate" rel="stylesheet" href="../../layui/css/modules/laydate/default/laydate.css" media="all">
    <link id="layuicss-layer" rel="stylesheet" href="../../layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body {
            /*margin-top: 15px;*/
            /* margin-left: 10px;*/
            /*margin-right: 50px;*/
            /*background-color: #f5f6fa !important;*/
            border-color: #F2F2F2;
            background-color: #F2F2F2;
        }

        .divradio .layui-form-radio > i {
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
    <i style="font-size: 24px;font-weight: bold;font-family: SimHei;font-style: normal">新增资产罚则记录</i>
</div>
<div style="padding: 20px;">
    <div class="layui-row layui-col-space15">
        <div class="">
            <div class="layui-card" style="border: solid 2px #8D8D8D;width: 100%">

                <div class="layui-card-body" style="margin-top: 20px">
                    <form class="layui-form" action="" lay-filter="addFrom" id="addFrom">

                            <div class="layui-inline">
                                <label class="layui-form-label">罚则编号：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="PENAL_ID"  autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>资产编号：</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="ASSETS_ID" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                        <div class="layui-inline">
                            <label class="layui-form-label"><span style="color:red">*</span>被罚则人：</label>
                            <div class="layui-input-inline">
                                <input type="text" name="PENAL" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">
                            </div>
                        </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>罚则日期：</label>
                                <div class="layui-input-inline">
                                    <!-- <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" style="width: 211px">-->
                                    <input type="text" name="PENALDATE" id="PENALDATE" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>罚则理由：</label>
                                <div class="layui-input-inline">
                                    <!-- <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" style="width: 211px">-->
                                    <input type="text" name="REASON" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><span style="color:red">*</span>罚则金额：</label>
                                <div class="layui-input-inline">
                                    <!-- <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" style="width: 211px">-->
                                    <input type="text" name="MONEY" lay-verify="required|number" autocomplete="off" class="layui-input" placeholder="请输入">
                                </div>
                            </div>

                            <div class="layui-inline">
                                <label class="layui-form-label">备注：</label>
                                <div class="layui-input-inline">
                                    <textarea placeholder="请输入"  class="layui-textarea" style="width: 200px" name="REMARK"></textarea>
                                    <%--<input type="text" name="REMARK" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入">--%>
                                </div>
                            </div>

                        <div class="layui-form-item" style="text-align: center;margin-top: 30px">
                            <div class="layui-input-block">
                                <button type="submit" class="layui-btn" lay-submit lay-filter="addSubmit" data-type="">保存</button>
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

    layui.use(['element'], function () {
        var element = layui.element;
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
                window.location.href = "../penaltyAsset.jsp?user=" + val1;

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

        laydate.render({
            elem: '#PENALDATE',
            showBottom: false
        });

        // 提交按钮
        form.on('submit(addSubmit)', function (data) {
            var parameter = data.field;
            var indexlod = layer.load(1);
            var parData = [{"name":"罚则编号","valu1":parameter.PENAL_ID},
                {"name":"资产编号","valu1":parameter.ASSETS_ID},
                {"name":"罚则日期","valu1":parameter.PENALDATE},
                {"name":"罚则理由","valu1":parameter.REASON},
                {"name":"罚则金额","valu1":parameter.MONEY},
                {"name":"被罚则人","valu1":parameter.PENAL},
                {"name":"备注","valu1":parameter.REMARK}
                ];
            parameter["uname"]=val1;
            queryData('../../penaltyAsset/addPenaltyAsset.do', parameter, function (date) {
                if (date.code == '0') {

                    var fieldName="";
                    var fieldAfter='';
                    $.each(parData, function () {

                        fieldName+=this.name+","
                        fieldAfter+=this.valu1+","
                    });
                    var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                    var param={'operType':'新增','operPage':'资产罚则信息','operTable':'IT_ASSET_PENALASSET','descr':strDescr,'createName':val1}
                    queryData('../../log/addlog.do', param, function (redt) {});

                    layer.alert(date.msg, {icon: 1});
                    window.location.href = "../penaltyAsset.jsp?user=" + val1;

                    //$(window).attr('location', "../penaltyAsset.jsp");
                }
            });
            layer.close(indexlod);
            return false;
        });

    });

    /*$(function () {
        $("#cancel").click(function () {
            $(window).attr('location', "../penaltyAsset.jsp");
        });
    });*/
</script>
</html>