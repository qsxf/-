

function license(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        let param = new URLSearchParams(location.search);
        const val1 = param.get('val1');
        const val2 = param.get('val2');
        const val3 = param.get('val3');
        const val555 = param.get('val555');
        $("#titleid").val(val2+"--License分配");
        table.render({
            elem: '#table1'
            ,url: '../inventoryAsset/selectLicense.do'
            ,where: {
                param: val1
            }
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}
                ,{field:'LICENSECOMPANY',align: 'center', title:'公司名称'}
                ,{field:'LICENSENUM',align: 'center', title:'分配License数'}


            ]]
            , height: 'full-130'
            ,id: 'reload1'
            ,page: true


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
                        param: val1

                    }
                });
            },
            jfaddbut:function() {
                var checkStatus = table.checkStatus('reload1')
                    ,datas = checkStatus.data;
                var val4 = val3;
                for (var i = 0; i < datas.length; i++) {

                    val4 = val4 - datas[i].LICENSENUM;
                }
                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加'
                    ,area: ['500px', '400px']
                    ,resize:false
                    , maxmin:false
                    ,content:'../view/intangibleAsset/license.html?userName='+val555+'&val3='+val4+"&assets_id="+val1
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
                var val4 = val3;
                /*for (var i = 0; i < datas.length; i++) {

                    val4 = val4 - datas[i].LICENSENUM;
                }*/
                if (datas.length !=1) {
                    layer.alert('请选择一条数据！')
                    return;
                }
                var id=datas[0].ID

                var val1=datas[0].LICENSENUM
                var val2=datas[0].LICENSECOMPANY

                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['500px', '400px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/intangibleAsset/license.html?but=edit&id='+id+'&val1='+val1+'&val2='+val2+'&val3='+val4+'&userName='+val555
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
                    layer.alert('请选择一条数据！')
                    return;
                }
                for (var i = 0; i < datas.length; i++) {

                    jobid += datas[i].ID + ",";
                }
                //var id=datas[0].ID
                layer.confirm('是否删除！', function (index) {
                    var indexlod = layer.load(1);
                    queryData('../inventoryAsset/delLicense.do', {id: jobid}, function (red) {
                        layer.close(indexlod);
                        if (red.code == 0) {
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

            /*cancel: function () {
                window.location.href = "intangibleAsset.jsp?user=" + val555;

            },*/


        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        $('#cancel').on('click', function(){
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


    $("#cancel").click(function () {
        windowclose();
    });
    function windowclose() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    }


}


