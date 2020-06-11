
function cabinetInfo(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        //方法级渲染
        table.render({
            elem: '#table2'
            ,url: '../ItInfoData/selectCabinet.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}
                ,{field:'CABINET_NAME',align: 'center', title:'机柜名称'}
                ,{field:'CABINET_UHIHG',align: 'center', title:'机柜U高'}
                ,{field:'COMPANY', align: 'center',title:'所属公司'}
            ]]
            , height: 'full-140'
            ,id: 'reload2'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName
            }

        });
        var $ = layui.$, active = {
            jgreload: function(){
                var param = $('#param2');

                //执行重载
                table.reload('reload2', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: param.val()
                        ,uname:userName
                    }
                });
            },
            jgaddbut:function() {

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加'
                    ,area: ['460px', '250px']
                    ,resize:false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/cabinet.html?userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('reload2', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                })
            },
            jgedit:function () {
                var checkStatus = table.checkStatus('reload2')
                    ,datas = checkStatus.data;

                if (datas.length !=1) {
                    layer.alert('请选择一条数据！')
                    return;
                }
                var id=datas[0].ID

                var val1=datas[0].CABINET_NAME
                var val2=datas[0].CABINET_UHIHG



                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['460px', '250px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/cabinet.html?but=edit&id='+id+'&val1='+val1+'&val2='+val2+'&userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('reload2', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                });



            },
            jgdelt:function () {
                var checkStatus = table.checkStatus('reload2')
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
                    var parData = [{"name":"机柜名称","valu1":datas.CABINET_NAME},
                        {"name":"机柜U高","valu1":datas.CABINET_UHIHG}
                    ];
                    queryData('../ItInfoData/delCabinet.do', {id: jobid}, function (red) {
                        layer.close(indexlod);
                        if (red.code == 0) {

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'机柜信息','operTable':'IT_ASSET_CABINET','descr':jobid,'createName':userName}
                            queryData('../log/addlog.do', param, function (redt) {});

                            table.reload('reload2', {
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
            jgexportbut:function () {

                var bodysArr= [{'field1': "机柜名称", 'field2': "机柜U高"}]
                var pam = $('#param2').val();
                var redpar={param: pam,uname:userName}

                queryData('../ItInfoData/exlCabinet.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].CABINET_NAME,'field2':rec[i].CABINET_UHIHG}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '机柜信息' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        $('#jgbut1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#jgbut2').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#jgbut3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#jgbut4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //
        $('#jgreload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



    });







}
