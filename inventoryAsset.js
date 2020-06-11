
function infoinventoryAsset(userName){
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
            elem: '#date3'
            ,range: true
        });
        laydate.render({
            elem: '#date4'
            ,range: true
        });
        laydate.render({
            elem: '#date5'
            ,range: true
        });
        laydate.render({
            elem: '#date6'
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
            ,url: '../inventoryAsset/selectInventoryAsset.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{field:'INVENTORY_ID', width:100,align: 'center',title:'盘点单号'}
                ,{field:'ATTRIBUTION', width:100,align: 'center',title:'盘点范围'}
                ,{field:'PLANDATE',width: 150,align: 'center',title:'计划盘点时间'}
                ,{field:'PLANFINDATE',width: 150,align: 'center',title:'计划完成时间'}
                ,{field:'REALITYDATE', width:150,align: 'center',title:'实际盘点时间'}
                ,{field:'REALITYFINDATE',width: 150,align: 'center',title:'实际完成时间',
                    templet : function(data) {// 替换数据
                        if (data.REALITYFINDATE != null){
                            return data.REALITYFINDATE;
                        }else {
                            return "";
                        }

                    }}
                ,{field:'INVENTORYER', width:160,align: 'center',title:'盘点人'}
                ,{field:'STAGE',width: 100,align: 'center',title:'状态',
                    templet : function(data) {// 替换数据

                        if(data.STAGE==1){
                            return "已完成";
                        }else if(data.STAGE==0){
                            return "未盘";
                        }else if(data.STAGE==2){
                            return  "盘点中";
                        }
                    }}
                ,{field:'DISKCOUNT',width: 100,align: 'center',title:'应盘数'}
                ,{field:'INVENTORYNUM',width: 100,align: 'center',title:'盘存数'}
                ,{field:'COMPANY',width: 150,align: 'center',title:'所属公司'}
                ,{field:'REMARK', width:100,align: 'center',title:'备注',
                    templet : function(data) {// 替换数据
                        if (data.REMARK != null){
                            return data.REMARK;
                        }else {
                            return "";
                        }

                    }}
                //,{field:'', templet:'#ckjob',width:100,align: 'center',title:'操作'}

            ]]
            , height: 'full-80'
            ,id: 'testReload'
            ,page: true
            ,limit:20
            ,where:{
                uname: userName,
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
                , area: ['450px', '450px']
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
                    var pam1=$("#pam1").val()
                    var pam2=$("#pam2").val()
                    var pam3=$("#date3").val()
                    var pam4=$("#date4").val()
                    var pam5=$("#date5").val()
                    var pam6=$("#date6").val()
                    var pam7=$("#pam7").val()
                    var pam8=$("input[name='sex']:checked").val()
                    var pam9p=$("#pam9p").val()
                    var pam9l=$("#pam9l").val()
                    var pam10p=$("#pam10p").val()
                    var pam10l=$("#pam10l").val()
                    var pam11=$("#pam11").val()
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
                            param6: pam6,
                            param7: pam7,
                            param8: pam8,
                            param9p: pam9p,
                            param9l: pam9l,
                            param10p: pam10p,
                            param10l: pam10l,
                            param11: pam11
                        }
                    });
                    layer.close(index);
                    return false;
                }
            });



            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });*/
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
                var pam6="";
                var pam7="";
                var pam8="";
                var pam9p="";
                var pam9l="";
                var pam10p="";
                var pam10l="";
                var pam11="";
                if (isChecked){
                    pam="";
                    date="";
                    pam1=$("#pam1").val()
                    pam2=$("#pam2").val()
                    pam3=$("#date3").val()
                    pam4=$("#date4").val()
                    pam5=$("#date5").val()
                    pam6=$("#date6").val()
                    pam7=$("#pam7").val()
                    pam8=$("input[name='sex']:checked").val()
                    pam9p=$("#pam9p").val()
                    pam9l=$("#pam9l").val()
                    pam10p=$("#pam10p").val()
                    pam10l=$("#pam10l").val()
                    pam11=$("#pam11").val()
                }
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: pam,
                        uname: userName,
                        bzDate:date,
                        param1: pam1,
                        param2: pam2,
                        param3: pam3,
                        param4: pam4,
                        param5: pam5,
                        param6: pam6,
                        param7: pam7,
                        param8: pam8,
                        param9p: pam9p,
                        param9l: pam9l,
                        param10p: pam10p,
                        param10l: pam10l,
                        param11: pam11
                    }
                });
            } ,
            addbut:function () {
                window.location.href="../view/inventoryAsset/addInventoryAsset.jsp?userName="+userName;

            },
            pandian:function () {
                var checkStatus = table.checkStatus('testReload')
                    ,data = checkStatus.data;
                if (data.length !=1) {

                    layer.alert('请选择一条数据！', {icon: 1});
                    return;
                }

                    var val1=data[0].INVENTORY_ID
                    var val2=data[0].ATTRIBUTION
                    var val3=data[0].DISKCOUNT
                    var val4=data[0].ID
                    var val5=data[0].REALITYDATE
                    var val6=data[0].REALITYFINDATE
                    var val7=data[0].REMARK
                    var val8=data[0].INVENTORYNUM
                    window.location.href="../view/inventoryAsset/InfoInventoryAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&userName="+userName;


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
                    layer.close(index);
                    layer.close(indexlod);

                });
                 layer.confirm('是否删除选择的数据！',  function (index) {
                     var indexlod = layer.load(1);
                     var parData = [{"name":"盘点单号","valu1":data.INVENTORY_ID},
                         {"name":"盘点范围","valu1":data.ATTRIBUTION},
                         {"name":"计划盘点时间","valu1":data.PLANDATE},
                         {"name":"计划完成时间","valu1":data.PLANFINDATE},
                         {"name":"实际盘点时间","valu1":data.REALITYDATE},
                         {"name":"实际完成时间","valu1":data.REALITYFINDATE},
                         {"name":"盘点人","valu1":data.INVENTORYER},
                         {"name":"状态","valu1":data.STAGE}
                     ];
                     queryData('../inventoryAsset/delInventoryAsset.do', {bxcode: jobid}, function (rec) {
                         if (rec.code="0"){

                             var fieldName="";
                             var fieldAfter='';
                             $.each(parData, function () {

                                 fieldName+=this.name+","
                                 fieldAfter+=this.valu1+","
                             });
                             var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                             var param={'operType':'删除','operPage':'资产盘点信息','operTable':'IT_ASSET_INVENTORYASSET','descr':jobid,'createName':userName}
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

                var bodysArr= [{'field1': "盘点编号", 'field2': "盘点范围",'field3': "计划盘点时间",'field4': "计划完成时间",'field5': "实际盘点时间",'field6': "实际完成时间",'field7': "盘点人",'field8': "状态",'field9': "应盘数",'field10': "盘存数",'field11': "备注"}]
                var vparam = $('#param');
                var vtype = $('#type');
                var vcdate=$('#date');
                var redpar={ param: vparam.val(),
                    type:vtype.val(),
                    uname: userName,
                    cdate:vcdate.val()}

                queryData('../inventoryAsset/exlInventoryAsset.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].INVENTORY_ID,'field2':rec[i].ATTRIBUTION,'field3':rec[i].PLANDATE,'field4':rec[i].PLANFINDATE,'field5':rec[i].REALITYDATE,'field6':rec[i].REALITYFINDATE,'field7':rec[i].INVENTORYER,'field8':rec[i].STAGE,'field9':rec[i].DISKCOUNT,'field10':rec[i].INVENTORYNUM,'field11':rec[i].REMARK}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '盘点信息' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };

        $('.demoTable .layui-btn').on('click', function(){
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
        $('#delt').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        $('#reload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#but3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



        table.on('tool(user)', function(obj){


        });

    });
}

function CloseWin(){

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}