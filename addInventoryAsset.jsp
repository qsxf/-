<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资产盘点</title>
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
    <i style="font-size: 24px;font-weight: bold;font-family: SimHei;font-style: normal">盘点单</i>
</div>
<div style="padding: 20px;">
    <div class="layui-row layui-col-space15">
        <div class="" >
            <div class="layui-card" style="border: solid 2px #8D8D8D;width: 100%">

                <div class="layui-card-body" style="margin-top: 20px">
                    <form class="layui-form" action="" lay-filter="upFrom"    id="upFrom">
                        <div style="margin-top: 20px">
                            <div style="margin-left: 30px">
                                <i style="font-size: 22px;font-weight: bold;font-family: SimHei;font-style: normal">盘点信息</i>
                            </div>
                            <div style="margin-top: 20px;">
                                <div class="layui-inline">
                                    <label class="layui-form-label"><span style="color:red">*</span>计划开始时间：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="jhkssj" id="date1" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label"><span style="color:red">*</span>计划结束时间：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="jhjssj" id="date2" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                    </div>
                                </div>

                            </div>
                            <div style="">
                                <%--<div class="layui-inline">
                                    <label class="layui-form-label"><span style="color:red">*</span>盘点编号：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="pdbh" lay-verify="pdbh" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                    </div>
                                </div>--%>
                                <div class="layui-inline">
                                    <label class="layui-form-label"><span style="color:red">*</span>盘点人：</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="pdr" lay-verify="required" autocomplete="off" class="layui-input"  placeholder="请输入">
                                    </div>
                                </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">备注：</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="bz"  autocomplete="off" class="layui-input"  placeholder="请输入">
                                        </div>
                                    </div>
                            </div>




                        </div>
                        <div style="margin-top: 20px">
                            <div style="margin-left: 30px">
                                <i style="font-size: 22px;font-weight: bold;font-family: SimHei;font-style: normal">盘点范围</i>
                            </div>
                            <div class="layui-inline" style="margin-top: 20px;margin-left: 50px">
                                <label class="layui-form-label"><span style="color:red">*</span>指定部门：</label>
                                <div class="layui-input-inline">
                                    <select multiple="multiple" lay-filter="addUserRole" id="gsgs" name="zdbm">

                                    </select>
                                    <%--<select name="zdbm" lay-verify="required" id="gsgs">
                                        <option value="">请选择</option>
                                    </select>--%>
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
<script src="../../js/inventoryAsset/infoInventoryAsset.js"></script>

<script>

    layui.use(['form','element'], function(){
        var element = layui.element;
        var form = layui.form;
        form.render();
    });
</script>
<script>
    layui.config({
        base : '../../layui_extends'
    }).extend({
        multiSelect:'/multiSelect',
    }).use(['form', 'layedit', 'laydate','multiSelect'], function() {

        let param = new URLSearchParams(location.search);
        //onst userName = param.get('userName');

        const val1 = param.get('userName');
        var laydate = layui.laydate;
        var form = layui.form;
        var multiSelect = layui.multiSelect;
        var $ = layui.$, active = {
            cancel: function () {
                window.location.href = "../inventoryAsset.jsp?user=" + val1;

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
            url:"../../stockAsset/selPDBM.do?uname="+val1,
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {
                for (var k in data) {
                    $("#gsgs").append("<option value='" + data[k].NAME+"' id='"+ data[k].NAME+"'>" + data[k].NAME + "</option>");
                }
                multiSelect.render("select");
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
                for (var i = 0; i <data.length; i++) {
                    if (compnms[i]==null){
                        return
                    }else {
                        //$("#COMPANY").append(new Option(compnms[i] ));
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

        laydate.render({
            elem: '#date1' //指定元素
            ,showBottom: false
        });
        laydate.render({
            elem: '#date2' //指定元素
            ,showBottom: false
        });
        /*form.verify({
            pdbh: function(value) {
                var message = '';
                $.ajax({
                    async: false, //改为同步请求
                    url:"../../inventoryAsset/selInventoryid.do?inventoryid="+value+"&uname="+val1,
                    contentType:"application/json",
                    type:"post",
                    dataType:"json",
                    beforeSend:"",
                    data:JSON.stringify(),
                    success: function(data) {
                        var pabhre = data[0].COUNTS;
                        if (pabhre != "0"){
                            message = "此盘点编号已存在";
                        }else {

                        }
                    }
                });
                if (message !== '')
                    return message;
            }
        });*/
        form.on('submit(reSubmit)', function (datas) {
            var ids = [];
            var roId = "";
            $('select[multiple] option:selected').each(function () {
                ids.push($(this).attr("id"));
            });
            for (var i = 0; i < ids.length; i++) {

                if ((i+1)==ids.length){
                    roId += ids[i]
                }else {
                    roId +=  ids[i]+","
                }
            }
            var parameter = datas.field
            parameter.zdbm = roId;
            var indexlod = layer.load(1);
            var parData = [{"name":"计划开始时间","valu1":parameter.jhkssj},
                {"name":"计划结束时间","valu1":parameter.jhjssj},
                //{"name":"盘点编号","valu1":parameter.pdbh},
                {"name":"盘点人","valu1":parameter.pdr},
                {"name":"备注","valu1":parameter.bz},
                {"name":"指定部门","valu1":parameter.zdbm}
            ];
            parameter["uname"]=val1;
            queryData('../../inventoryAsset/addInventoryAsset.do',parameter, function (red) {
                if (red.code == '0') {

                    var fieldName="";
                    var fieldAfter='';
                    $.each(parData, function () {

                        fieldName+=this.name+","
                        fieldAfter+=this.valu1+","
                    });
                    var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                    var param={'operType':'新增','operPage':'资产盘点信息','operTable':'IT_ASSET_INVENTORYASSET','descr':strDescr,'createName':val1}
                    queryData('../../log/addlog.do', param, function (redt) {});

                    layer.alert(red.msg, {icon: 1});
                    window.location.href = "../inventoryAsset.jsp?user=" + val1;

                    // $(window).attr('location',"../inventoryAsset.jsp");
                }


            })


            layer.close(indexlod);
            return false;
            /*var pabhre = "";
            $.ajax({
                url:"../../inventoryAsset/selInventoryid.do?inventoryid="+parameter.pdbh+"&uname="+val1,
                contentType:"application/json",
                type:"post",
                dataType:"json",
                beforeSend:"",
                data:JSON.stringify(),
                success: function(data) {
                    pabhre = data[0].COUNTS;
                    if (pabhre != null){
                        layer.alert('此盘存编号已存在', {icon: 1});
                        return;
                    }else {

                    }
                }
            });*/


        });
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
/*    $(function(){
        $("#cancel").click(function(){
            $(window).attr('location',"../inventoryAsset.jsp");
        });
    });*/
</script>

</html>