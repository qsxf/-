
function infoDumpAsset(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        //时间范围
        laydate.render({
            elem: '#date1'
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
            elem: '#tableId'
            ,url: '../repairAsset/selectDumpAsset.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                // ,{type:'numbers',title:'序号'}
                ,{field:'ASSETS_ID', align: 'center',width:100,title:'资产编号'}
                ,{field:'ASSETS_NAME',align: 'center', width:180,title:'资产名称'}
                ,{field:'ASSETCLASS_1',align: 'center',minWidth: 130,title:'一级分类'}
                ,{field:'ASSETCLASS_2',align: 'center', width:150,title:'二级分类'}
                ,{field:'BRAND',align: 'center',width: 200,title:'品牌'}
                ,{field:'TYPE_NAME', width:160,align: 'center',title:'型号'}
                ,{field:'REASON',align: 'center',width: 180,title:'报废原因'}
                ,{field:'DUMPDATE',align: 'center',width: 150,title:'报废时间'}
                ,{field:'ASSETS_COMPANY',align: 'center',width: 150,title:'归属公司'}
                // ,{field:'fie8',width: 100,title:'处理人'}
               // ,{field:'CREATE_NAME',width: 150,title:'创建人'}
               // ,{field:'CREATE_TIME',width: 150,title:'创建时间'}
               // ,{field:'fie8',width: 150,title:'入库时间'}
                ,{field:'', templet:'#ckjob',width:180,align: 'center',title:'操作'}

            ]]
            , height: 'full-80'
            ,id: 'testReload'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName
            }
        });
        //监听表格复选框选择
        table.on('checkbox(tableId)', function(obj){
            $("input[name='layTableCheckbox']").each(function(i,obj){
                if($(this).is(':checked')){
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#e0ebeb"});  //被选中的行变色
                }else{
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#fff"});    //其他的置成白色
                }
            });
        });
        /*table.on('rowDouble(tableFil)', function(obj){
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
                    var pam2=$("#pam2").val()
                    var pam3=$("#pam3").val()
                    var pam4=$("#pam4").val()
                    var pam5=$("#pam5").val()
                    var pam6=$("#pam6").val()
                    var pam7=$("#pam7").val()
                    var pam8=$("#date").val()
                    var pam9=$("#pam9").val()
                    var pam10=$("#pam10").val()
                    table.reload('testReload', {

                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        // ,url: '../repairAsset/selectRepairAsset.do'
                        ,where: {
                            param: pam,
                            uname:userName,
                            datas:"",
                            param2: pam2,
                            param3: pam3,
                            param4: pam4,
                            param5: pam5,
                            param6: pam6,
                            param7: pam7,
                            param8: pam8,
                            param9: pam9,
                            param10: pam10


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
                var par = $('#param').val();
                var date1=$('#date1').val();
                var isChecked = $('#duoxuan').prop('checked');
                var pam2="";
                var pam3="";
                var pam4="";
                var pam5="";
                var pam6="";
                var pam7="";
                var pam8="";
                var pam9="";
                var pam10="";
                var pam11="";
                if (isChecked){
                    par="";
                    date1="";
                    pam2=$("#pam2").val()
                    pam3=$("#pam3").val()
                    pam4=$("#pam4").val()
                    pam5=$("#pam5").val()
                    pam6=$("#pam6").val()
                    pam7=$("#pam7").val()
                    pam8=$("#pam8").val()
                    pam9=$("#pam9").val()
                    pam10=$("#pam10").val()
                    pam11=$("#pam11").val()
                }
                // console.log(zhuangtai.val());
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {

                        param: par,
                        datas:date1,
                        uname:userName,
                        param2: pam2,
                        param3: pam3,
                        param4: pam4,
                        param5: pam5,
                        param6: pam6,
                        param7: pam7,
                        param8: pam8,
                        param9: pam9,
                        param10: pam10

                    }
                });
            } ,
            delbut:function () {
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
                    var parData = [{"name":"报废单号","valu1":data.DUMP_ID},
                        {"name":"资产编号","valu1":data.ASSETS_ID},
                        {"name":"报废时间","valu1":data.DUMPDATE}
                    ];
                    queryData('../repairAsset/delDumpAsset.do', {id: jobid}, function (rec) {
                        if (rec.code="0"){

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'资产报废信息','operTable':'IT_ASSET_DUMPASSET','descr':jobid,'createName':userName}
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

                var bodysArr= [{'field1': "资产编号", 'field2': "资产名称",'field3': "一级分类",'field4': "二级分类",'field5': "品牌",'field6': "型号",'field7': "报废原因",'field8': "报废时间",'field9': "创建人",'field10': "创建时间"}]

                var par = $('#param');
                var date1=$('#date1');
                var redpar={   param: par.val(),
                    datas:date1.val(),
                    uname:userName}

                queryData('../repairAsset/exlDumpAsset.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].ASSETS_ID,'field2':rec[i].ASSETS_NAME,'field3':rec[i].ASSETCLASS_1,'field4':rec[i].ASSETCLASS_2,'field5':rec[i].BRAND,'field6':rec[i].TYPE_NAME,'field7':rec[i].REASON,'field8':rec[i].DUMPDATE,'field9':rec[i].CREATE_NAME,'field10':rec[i].CREATE_TIME}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '报废清单' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            }


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
        $('#reload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        table.on('tool(tableFil)', function(obj){
            var data = obj.data;
            var id=data.ID

            var val1=data.ASSETS_ID
            var val2=data.ASSETS_NAME



            var val3=data.ASSETCLASS_1
            var val4=data.ASSETCLASS_2
            var val5=data.BRAND
            var val6=data.TYPE_NAME

            var val13=data.DUMPDATE
            var val14=data.REASON

            var val9=data.CREATE_NAME




            if(obj.event === 'edit'){
                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/dumpAsset/editDumpAsset.html?val1='+val1+"&val2="+val2+"&val3="+val13+"&val4="+val14+"&id="+id+'&userName='+userName
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
               // window.location.href="../view/dumpAsset/editDumpAsset.html?val1="+val1+"&val2="+val2+"&val3="+val13+"&val4="+val14+"&id="+id+'&userName='+userName;
            }
            if(obj.event === 'detail'){
                layer.open({
                    type: 2
                    ,title: '详情'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/dumpAsset/infoDumpAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val13="+val13+"&val14="+val14+"&val9="+val9+'&userName='+userName
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
               // window.location.href="../view/dumpAsset/infoDumpAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val13="+val13+"&val14="+val14+"&val9="+val9+'&userName='+userName;

            }
        });

        });






}

function CloseWin(){

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}