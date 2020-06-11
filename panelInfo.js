
function panelInfo(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        //方法级渲染
        table.render({
            elem: '#table5'
            ,url: '../ItInfoData/selectPanel.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}

                ,{field:'PANEL_ID',align: 'center', title:'面板编号'}
                ,{field:'UNTI_ID', align: 'center',title:'单位编号'}
                ,{field:'LOCATION',align: 'center',title:'所在楼宇'}
                ,{field:'FLOOR',align: 'center',title:'楼层'}
                ,{field:'ROOM',align: 'center',title:'所在房间'}
                ,{field:'TYPE', align: 'center',title:'类型'}
                ,{field:'COMPANY', align: 'center',title:'所属公司'}

            ]]
            , height: 'full-140'
            ,id: 'tReload5'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName
            }
            // ,toolbar: '#toolbarDemo1'
        });

        var $ = layui.$, active = {
            reload5: function(){
                var param = $('#param5');

                //执行重载
                table.reload('tReload5', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: param.val()
                        ,uname:userName
                    }
                });
            },
            mbaddbut:function() {

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加'
                    ,area: ['460px', '400px']
                    ,resize:false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/panelInfo.html?userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('tReload5', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                })
            },
            mbedit:function () {
                var checkStatus = table.checkStatus('tReload5')
                    ,datas = checkStatus.data;

                if (datas.length !=1) {
                    layer.alert('请选择一条数据！')
                    return;
                }
                var id=datas[0].ID

                var val1=datas[0].PANEL_ID
                var val2=datas[0].UNTI_ID
                var val3=datas[0].LOCATION
                var val4=datas[0].FLOOR
                var val5=datas[0].ROOM
                var val6=datas[0].TYPE


                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['460px', '400px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/panelInfo.html?but=edit&id='+id+'&val1='+val1+'&val2='+val2+'&val3='+val3+'&val4='+val4+'&val5='+val5+'&val6='+val6+'&userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('tReload5', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                });



            },
            mbdelt:function () {
                var checkStatus = table.checkStatus('tReload5')
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

                    var parData = [{"name":"面板编号","valu1":datas.PANEL_ID},
                        {"name":"单位编号","valu1":datas.UNTI_ID},
                        {"name":"所在楼宇","valu1":datas.LOCATION},
                        {"name":"楼层","valu1":datas.FLOOR},
                        {"name":"所在房间","valu1":datas.ROOM},
                        {"name":"类型","valu1":datas.TYPE}
                    ];

                    queryData('../ItInfoData/delPanel.do', {id: jobid}, function (red) {
                        layer.close(indexlod);
                        if (red.code == 0) {

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'面板信息','operTable':'IT_ASSET_PANEL','descr':jobid,'createName':userName}
                            queryData('../log/addlog.do', param, function (redt) {});

                            table.reload('tReload5', {
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
            mbexportbut:function () {

                var bodysArr= [{'field1': "面板编号", 'field2': "单位编号",'field3': "所在楼宇",'field4': "楼层",'field5': "所在房间",'field6': "类型"}]
                var pam = $('#param5').val();
                var redpar={param: pam,uname:userName}

                queryData('../ItInfoData/exlPanel.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].PANEL_ID,'field2':rec[i].UNTI_ID,'field3':rec[i].LOCATION,'field4':rec[i].FLOOR,'field5':rec[i].ROOM,'field6':rec[i].TYPE}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '面板信息' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        $('#mbbut1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#mbbut2').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#mbbut3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#mbbut4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#reload5').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        });






}

