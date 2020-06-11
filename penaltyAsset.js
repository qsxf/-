function infoPenaltyAsset(userName) {
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;

        laydate.render({
            elem: '#bzDate'
            ,range: true
        });
        laydate.render({
            elem: '#date'
            ,range: true
        });
        form.on('checkbox(duoxuan)', function(data){

            var bol=data.elem.checked
            if (bol){
                $("#tiaojian1").hide()
                $("#tiaojian2").show()
            }else {
                $("#tiaojian1").show()
                $("#tiaojian2").hide()
            }


        });
        //方法级渲染
        table.render({
            elem: '#user'
            ,url: '../penaltyAsset/selectPenaltyAsset.do'
            ,cols: [[
                {type: 'checkbox',align: 'center',width: 70, title: '选择'},
                {type:'numbers',align: 'center',title:'序号'},
                {field: 'PENAL_ID', align: 'center',width: 100, title: '罚则编号',
                    templet : function(data) {// 替换数据
                        if (data.PENAL_ID != null){
                            return data.PENAL_ID;
                        }else {
                            return "";
                        }

                    }},
                {field: 'ASSETS_ID',align: 'center', width: 100, title: '资产编号'},
                {field: 'ASSETS_NAME',align: 'center', width: 180, title: '资产名称'},
                {field: 'PENALDATE',align: 'center', width: 150, title: '罚则日期'},
                {field: 'REASON',align: 'center', width: 180, title: '罚则原因'},
                {field: 'MONEY',align: 'center', width: 100, title: '罚则金额'},
                {field: 'PENAL', width: 160, align: 'center', title: '被罚则人'},
                {field: 'REMARK',align: 'center', width: 180, title: '备注',
                    templet : function(data) {// 替换数据
                        if (data.REMARK != null){
                            return data.REMARK;
                        }else {
                            return "";
                        }

                    }},
                {field: 'ASSETS_COMPANY',align: 'center', width: 180, title: '所属公司'},
                {field: '', templet: '#ckjob', width: 100, align: 'center', title: '操作'}
            ]]
            , height: 'full-90'
            ,id: 'testReload'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName
            }
        });
        //监听表格复选框选择
        table.on('checkbox(user)', function(obj){
            $("input[name='layTableCheckbox']").each(function(i,obj){
                if($(this).is(':checked')){
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#e0ebeb"});  //被选中的行变色
                }else{
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#fff"});    //其他的置成白色
                }
            });
        });
        /*table.on('rowDouble(user)', function(obj){
            // var data = obj.data;
            //
            // layer.alert(JSON.stringify(data), {
            //     title: '当前行数据：'
            // });



            layer.open({
                type: 1
                , title: '查询'
                , area: ['430px', '450px']
                , maxmin: false
                , resize: false
                ,content: $('#repForm')
                , zIndex: layer.zIndex
                , btn: ['确定']
                , btnAlign: 'c'
                , success: function (layero, index) {

                }
                , yes: function (index, layero) {
                    var pam = $('#param').val();
                    var pam1=$("#date").val()
                    var pam2=$("#pam2").val()
                    var pam3=$("#pam3").val()
                    var pam4=$("#pam4").val()
                    var pam5=$("#pam5").val()
                    var pam6p=$("#pam6p").val()
                    var pam6l=$("#pam6l").val()
                    var pam7=$("#pam7").val()
                    var pam8=$("#pam8").val()
                    table.reload('testReload', {

                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        // ,url: '../repairAsset/selectRepairAsset.do'
                        ,where: {
                            param: pam,
                            uname:userName,
                            bzDate:"",
                            param1: pam1,
                            param2: pam2,
                            param3: pam3,
                            param4: pam4,
                            param5: pam5,
                            param6p: pam6p,
                            param6l: pam6l,
                            param7: pam7,
                            param8: pam8

                        }
                    });
                    layer.close(index);
                    return false;
                }
            });



            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });*/
        // 查询按钮
        var $ = layui.$, active = {
            reload: function(){
                var pam = $('#param').val();
                var date = $('#bzDate').val();
                var isChecked = $('#duoxuan').prop('checked');
                var pam1="";
                var pam2="";
                var pam3="";
                var pam4="";
                var pam5="";
                var pam6p="";
                var pam6l="";
                var pam7="";
                var pam8="";
                if (isChecked){
                    pam="";
                    date="";

                    pam1=$("#date").val()
                    pam2=$("#pam2").val()
                    pam3=$("#pam3").val()
                    pam4=$("#pam4").val()
                    pam5=$("#pam5").val()
                    pam6p=$("#pam6p").val()
                    pam6l=$("#pam6l").val()
                    pam7=$("#pam7").val()
                    pam8=$("#pam8").val()
                }
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: pam,
                        uname:userName,
                        bzDate:date,
                        param1: pam1,
                        param2: pam2,
                        param3: pam3,
                        param4: pam4,
                        param5: pam5,
                        param6p: pam6p,
                        param6l: pam6l,
                        param7: pam7,
                        param8: pam8
                    }
                });
            } ,
            addbut:function () {
                window.location.href="../view/penaltyAsset/addPenaltyAsset.jsp?userName="+userName;

            },
            delt:function () {
                var checkStatus = table.checkStatus('testReload')

                    ,data = checkStatus.data;
                var jobid = "";
                if (data.length == 0) {
                    layer.alert('请选择要删除的数据！', {icon: 1});
                    return;
                }
                for (var i = 0; i < data.length; i++) {

                    jobid += data[i].ID + ",";
                }

                //var id=data[0].ID

                layer.confirm('是否删除选择的数据！', {icon: 3, title: '提示'}, function (index) {
                    var indexlod = layer.load(1);
                    var parameter = data;
                    var parData = [{"name":"罚则编号","valu1":parameter.PENAL_ID},
                        {"name":"资产编号","valu1":parameter.ASSETS_ID},
                        {"name":"罚则金额","valu1":parameter.MONEY},
                        {"name":"被罚则人","valu1":parameter.PENAL}
                    ];
                    queryData('../penaltyAsset/delPenaltyAsset.do', {ID: jobid}, function (rec) {
                        if (rec.code="0"){
                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'资产罚则信息','operTable':'IT_ASSET_PENALASSET','descr':jobid,'createName':userName}
                            queryData('../log/addlog.do', param, function (redt) {});

                            table.reload('testReload', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                        }
                        layer.alert(rec.msg, {icon: 1});
                    });

                    layer.close(index);
                    layer.close(indexlod);

                });
            },
            exportbut:function () {

                var bodysArr= [{'field1': "罚则编号", 'field2': "资产编号",'field3': "资产名称",'field4': "罚则日期",'field5': "罚则原因",'field6': "罚则金额",'field7': "被罚则人",'field8': "备注",'field9': "创建时间",'field10': "创建人",'field11': "修改时间",'field12': "修改人"}]
                var vparam = $('#param');
                var vtype = $('#type');
                var bzDate=$('#bzDate');
                var redpar={ param: vparam.val(),
                    type:vtype.val(),
                    uname:userName,
                    bzDate:bzDate.val()}

                queryData('../penaltyAsset/exlPenaltyAsset.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].PENAL_ID,'field2':rec[i].ASSETS_ID,'field3':rec[i].ASSETS_NAME,'field4':rec[i].PENALDATE,'field5':rec[i].REASON,'field6':rec[i].MONEY,'field7':rec[i].PENAL,'field8':rec[i].REMARK,'field9':rec[i].CREATE_DATE,'field10':rec[i].CREATE_NAME,'field11':rec[i].UPDATE_DATE,'field12':rec[i].UPDATE_NAME}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '资产罚则' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#bzDate').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#but1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#but2').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#but3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#reload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        // 编辑
        table.on('tool(user)', function(obj){
            var tdata = obj.data;
            if(obj.event === 'detai2'){
                var val1=tdata.ID
                var val2=tdata.PENAL_ID
                var val3=tdata.ASSETS_ID
                var val4=tdata.ASSETS_NAME
                var val5=tdata.PENALDATE
                var val6=tdata.REASON
                var val7=tdata.MONEY
                var val8=tdata.PENAL
                var val9=tdata.REMARK
                layer.open({
                    type: 2
                    ,title: '编辑'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/penaltyAsset/editPenaltyAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+'&userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('testReload', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                });
                //window.location.href = "../view/penaltyAsset/editPenaltyAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+'&userName='+userName;
            }
        });

    });

}

//关闭页面
function CloseWin() {
    //先得到当前iframe层的索引
    var index = parent.layer.getFrameIndex(window.name);
    //再执行关闭
    parent.layer.close(index);
}