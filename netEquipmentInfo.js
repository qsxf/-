
function netEquipmentInfo(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        //方法级渲染
        table.render({
            elem: '#table6'
            ,url: '../ItInfoData/selectNetequipment.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}

                ,{field:'EUT_NAME',align: 'center',width:'100', title:'设备名称'}
                ,{field:'EUT_ID',align: 'center',width:'100', title:'设备序列号'}
                ,{field:'EUP_CLASS',align: 'center',width:'100',title:'设备类别'}
                ,{field:'STORE_NAME',align: 'center',width:'100',title:'厂商名称'}
                ,{field:'EUP_MODEL',align: 'center',width:'100',title:'设备型号'}
                ,{field:'DRAM_SIZE', align: 'center',width:'150',title:'设备DRAM大小'}
                ,{field:'FLASH_SIZE', align: 'center',width:'150',title:'设备FLASH大小'}
                ,{field:'BUY_TIME', align: 'center',width:'150',title:'购买日期'}
                ,{field:'EXITINSUR_TIME', align: 'center',width:'150',title:'出保日期'}
                ,{field:'CABINET', align: 'center',width:'150',title:'设备所在机柜'}
                ,{field:'U_BIT', align: 'center',width:'100',title:'所在U位'}
                ,{field:'STATE_DESCRIPTION', align: 'center',width:'150',title:'设备状态描述'}
                ,{field:'ADMIN', align: 'center',width:'100',title:'设备管理员'}
                ,{field:'IP', align: 'center',width:'120',title:'IP地址'}
                ,{field:'REMARK', align: 'center',width:'150',title:'备注'}
                ,{field:'COMPANY', align: 'center',width:'150',title:'所属公司'}
            ]]
            , height: 'full-140'
            ,id: 'tReload6'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName
            }
        });

        var $ = layui.$, active = {
            reload6: function(){
                var param = $('#param6');

                //执行重载
                table.reload('tReload6', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: param.val()
                        ,uname:userName
                    }
                });
            },
            wladdbut:function() {

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加'
                    ,area: ['660px', '500px']
                    ,resize:false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/netEquipment.html?userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('tReload6', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                })
            },
            jfedit:function () {
                var checkStatus = table.checkStatus('tReload6')
                    ,datas = checkStatus.data;

                if (datas.length !=1) {
                    layer.alert('请选择一条数据！')
                    return;
                }
                var id=datas[0].ID

                var val1=datas[0].EUT_NAME
                var val2=datas[0].EUT_ID
                var val3=datas[0].EUP_CLASS
                var val4=datas[0].STORE_NAME
                var val5=datas[0].EUP_MODEL
                var val6=datas[0].DRAM_SIZE
                var val7=datas[0].FLASH_SIZE
                var val8=datas[0].BUY_TIME
                var val9=datas[0].EXITINSUR_TIME
                var val10=datas[0].CABINET
                var val11=datas[0].U_BIT
                var val12=datas[0].STATE_DESCRIPTION
                var val13=datas[0].ADMIN
                var val14=datas[0].IP
                var val15=datas[0].REMARK


                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['660px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/netEquipment.html?but=edit&id='+id+'&val1='+val1+'&val2='+val2+'&val3='+val3+'&val4='+val4+'&val5='+val5+'&val6='+val6+'&val7='+val7+'&val8='+val8+'&val9='+val9+'&val10='+val10+'&val11='+val11+'&val12='+val12+'&val13='+val13+'&val14='+val14+'&val15='+val15+'&userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('tReload6', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                });



            },
            jfdelt:function () {
                var checkStatus = table.checkStatus('tReload6')
                    ,datas = checkStatus.data;
                var jobid = "";
                if (datas.length == 0) {
                    layer.alert('请选择要删除的数据！', {icon: 1});
                    return;
                }
                for (var i = 0; i < datas.length; i++) {

                    jobid += datas[i].ID + ",";
                }
                //var id=datas[0].ID
                layer.confirm('是否删除！', function (index) {
                    var indexlod = layer.load(1);
                    var parData = [{"name":"设备名称","valu1":datas.EUT_NAME},
                        {"name":"设备序列号","valu1":datas.EUT_ID},
                        {"name":"设备类别","valu1":datas.EUP_CLASS},
                        {"name":"厂商名称","valu1":datas.STORE_NAME},
                        {"name":"设备型号","valu1":datas.EUP_MODEL},
                        {"name":"设备DRAM大小","valu1":datas.DRAM_SIZE},
                        {"name":"设备FLASH大小","valu1":datas.FLASH_SIZE},
                        {"name":"购买日期","valu1":datas.BUY_TIME},
                        {"name":"出保日期","valu1":datas.EXITINSUR_TIME},
                        {"name":"设备所在机柜","valu1":datas.CABINET},
                        {"name":"所在U位","valu1":datas.U_BIT},
                        {"name":"设备状态描述","valu1":datas.STATE_DESCRIPTION},
                        {"name":"设备管理员","valu1":datas.ADMIN},
                        {"name":"IP地址","valu1":datas.IP},
                        {"name":"备注","valu1":datas.REMARK}
                    ];
                    queryData('../ItInfoData/delNetequipment.do', {id: jobid}, function (red) {
                        layer.close(indexlod);
                        if (red.code == 0) {

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'网络设备信息','operTable':'IT_ASSET_NETEQUIPMENT','descr':jobid,'createName':userName}
                            queryData('../log/addlog.do', param, function (redt) {});

                            table.reload('tReload6', {
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            layer.close(index);
                        }

                        layer.msg(red.msg, {icon: 1})
                    })


                });



            },
            jfexportbut:function () {

                var bodysArr= [{'field1': "设备名称", 'field2': "设备序列号",'field3': "设备类别",'field4': "厂商名称",'field5': "设备型号",'field6': "设备DRAM大小",'field7': "设备FLASH大小",'field8': "购买日期",'field9': "出保日期",'field10': "设备所在机柜",'field11': "所在U位",'field12': "设备状态描述",'field13': "设备管理员",'field14': "IP地址",'field15': "备注"}]
                var pam = $('#param6').val();
                var redpar={param: pam,uname:userName}

                queryData('../ItInfoData/exlNetequipment.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].EUT_NAME,'field2':rec[i].EUT_ID,'field3':rec[i].EUP_CLASS,'field4':rec[i].STORE_NAME,'field5':rec[i].EUP_MODEL,'field6':rec[i].DRAM_SIZE,'field7':rec[i].FLASH_SIZE,'field8': rec[i].BUY_TIME,'field9': rec[i].EXITINSUR_TIME,'field10': rec[i].CABINET,'field11': rec[i].U_BIT,'field12': rec[i].STATE_DESCRIPTION,'field13': rec[i].ADMIN,'field14': rec[i].IP,'field15': rec[i].REMARK}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '设备信息' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },
        };


        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });





        $('#wlbut1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#wlbut2').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#wlbut3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#wlbut4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#reload6').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });





        });






}


