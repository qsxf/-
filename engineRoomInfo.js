

function engineRoomInfo(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;

        table.render({
            elem: '#table1'
            ,url: '../ItInfoData/selectItRoom.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}
                ,{field:'ITROOM_NAME',align: 'center',width:120, title:'机房名称'}
                ,{field:'LOCATION',align: 'center',width:140, title:'机房所在楼宇'}
                ,{field:'FLOOR',align: 'center',width:100,title:'楼层'}
                ,{field:'ROOMNUM',align: 'center',width:100,title:'房间号'}
                ,{field:'AREA',align: 'center',width:100,title:'机房面积'}
                ,{field:'CABINETNUM', align: 'center',width:160,title:'机房内现有机柜数'}
                ,{field:'COMPANY', align: 'center',minWidth:160,title:'所属公司'}
            ]]
            , height: 'full-140'
            ,id: 'reload1'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName
            }
            // ,toolbar: '#toolbarDemo1'

        });


        var $ = layui.$, active = {
            jfreload: function(){
                var param = $('#param1');

                //执行重载
                table.reload('reload1', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: param.val()
                        ,uname:userName
                    }
                });
            },
            jfaddbut:function() {

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加'
                    ,area: ['460px', '450px'] //['460px', '400px']
                    ,resize:false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/engineRoom.html?userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载

                        table.reload('reload1', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                })
            },
            jfedit:function () {
                var checkStatus = table.checkStatus('reload1')
                    ,datas = checkStatus.data;

                if (datas.length !=1) {
                    layer.alert('请选择一条数据！')
                    return;
                }
                var id=datas[0].ID

                var val1=datas[0].ITROOM_NAME
                var val2=datas[0].LOCATION
                var val3=datas[0].FLOOR
                var val4=datas[0].ROOMNUM
                var val5=datas[0].AREA
                var val6=datas[0].CABINETNUM


                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['460px', '400px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/engineRoom.html?but=edit&id='+id+'&val1='+val1+'&val2='+val2+'&val3='+val3+'&val4='+val4+'&val5='+val5+'&val6='+val6+'&userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('reload1', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                });



            },
            jfdelt:function () {
                var checkStatus = table.checkStatus('reload1')
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
                    var parData = [{"name":"机房名称","valu1":datas.ITROOM_NAME},
                        {"name":"机房所在楼宇","valu1":datas.LOCATION},
                        {"name":"楼层","valu1":datas.FLOOR},
                        {"name":"房间号","valu1":datas.ROOMNUM},
                        {"name":"机房面积","valu1":datas.AREA},
                        {"name":"机房内现有机柜数","valu1":datas.CABINETNUM}
                    ];
                    queryData('../ItInfoData/delItRoom.do', {id: jobid}, function (red) {
                        layer.close(indexlod);
                        if (red.code == 0) {

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'机房信息','operTable':'IT_ASSET_ITROOM','descr':jobid,'createName':userName}
                            queryData('../log/addlog.do', param, function (redt) {});

                            table.reload('reload1', {
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

                var bodysArr= [{'field1': "机房名称", 'field2': "机房所在楼宇",'field3': "楼层",'field4': "房间号",'field5': "机房面积",'field6': "机房内现有机柜数"}]
                var pam = $('#param1').val();
                var redpar={param: pam,uname:userName}

                queryData('../ItInfoData/exlItRoom.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].ITROOM_NAME,'field2':rec[i].LOCATION,'field3':rec[i].FLOOR,'field4':rec[i].ROOMNUM,'field5':rec[i].AREA,'field6':rec[i].CABINETNUM}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '机房信息' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        $('#jfbut1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#jfbut2').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#jfbut3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#jfbut4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#jfreload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



        });





}


