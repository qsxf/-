
function networkCableInfo(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function() {
        var table = layui.table;
        var $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        //方法级渲染,,,,,
        table.render({
            elem: '#table4'
            ,url: '../ItInfoData/selectCable.do'
            , cols: [[
                {type: 'checkbox',align: 'center',width: 70, title: '选择'}
                , {type: 'numbers',align: 'center', title: '序号'}
                , {field: 'CABLE_NAME', align: 'center', title: '线缆名称'}
                , {field: 'EQUIPMENT_PORT', align: 'center', title: '首尾带电设备端口'}
                , {field: 'RACK_PORT', align: 'center', title: '上下联配线架端口'}
                , {field: 'PANEL_ID', align: 'center', title: '面板号'}
                , {field: 'INTERFACE_CLASS', align: 'center', title: '接口类型'}
                , {field: 'LENGTH', align: 'center', title: '长度（米）'}
                ,{field:'COMPANY', align: 'center',title:'所属公司'}

            ]]
            , height: 'full-140'
            , id: 'reload4'
            , page: true
            ,limit:20
            ,where:{
                uname:userName
            }
        });

        var $ = layui.$, active = {
            reload4: function(){
                var param = $('#param4');

                //执行重载
                table.reload('reload4', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: param.val()
                        ,uname:userName
                    }
                });
            },
            gqaddbut:function() {

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加'
                    ,area: ['460px', '400px']
                    ,resize:false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/networkCable.html?userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('reload4', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                })
            },
            gqedit:function () {
                var checkStatus = table.checkStatus('reload4')
                    ,datas = checkStatus.data;

                if (datas.length !=1) {
                    layer.alert('请选择一条数据！')
                    return;
                }
                var id=datas[0].ID

                var val1=datas[0].CABLE_NAME
                var val2=datas[0].EQUIPMENT_PORT
                var val3=datas[0].RACK_PORT
                var val4=datas[0].PANEL_ID
                var val5=datas[0].INTERFACE_CLASS
                var val6=datas[0].LENGTH


                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['460px', '400px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/networkCable.html?but=edit&id='+id+'&val1='+val1+'&val2='+val2+'&val3='+val3+'&val4='+val4+'&val5='+val5+'&val6='+val6+'&userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('reload4', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                });



            },
            gqdelt:function () {
                var checkStatus = table.checkStatus('reload4')
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
                    var parData = [{"name":"线缆名称","valu1":datas.CABLE_NAME},
                        {"name":"首尾带电设备端口","valu1":datas.EQUIPMENT_PORT},
                        {"name":"上下联配线架端口","valu1":datas.RACK_PORT},
                        {"name":"面板号","valu1":datas.PANEL_ID},
                        {"name":"接口类型","valu1":datas.INTERFACE_CLASS},
                        {"name":"长度","valu1":datas.LENGTH}
                    ];
                    queryData('../ItInfoData/delCable.do', {id: jobid}, function (red) {
                        layer.close(indexlod);
                        if (red.code == 0) {

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'光纤网线信息','operTable':'IT_ASSET_CABLE','descr':jobid,'createName':userName}
                            queryData('../log/addlog.do', param, function (redt) {});

                            table.reload('reload4', {
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
            gqexportbut:function () {

                var bodysArr= [{'field1': "线缆名称", 'field2': "首尾带电设备端口",'field3': "上下联配线架端口",'field4': "面板号",'field5': "接口类型",'field6': "长度"}]
                var pam = $('#param4').val();
                var redpar={param: pam,uname:userName}

                queryData('../ItInfoData/exlCable.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].CABLE_NAME,'field2':rec[i].EQUIPMENT_PORT,'field3':rec[i].RACK_PORT,'field4':rec[i].PANEL_ID,'field5':rec[i].INTERFACE_CLASS,'field6':rec[i].LENGTH}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '光纤网线信息' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        $('#gqbut1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#gqbut2').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#gqbut3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#gqbut4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#butreload4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


    })


}


