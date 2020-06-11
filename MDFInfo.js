
function MDFInfo(userName){
    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        //方法级渲染
        table.render({
            elem: '#table3'
            ,url: '../ItInfoData/selectDistribution.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}
                ,{field:'DISTRIBUTION_NAME',align: 'center', title:'配线架名称'}
                ,{field:'COMPANY', align: 'center',title:'所属公司'}
            ]]
            , height: 'full-140'
            ,id: 'reload3'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName
            }
        });




        var $ = layui.$, active = {
            pxjreload: function(){
                var param = $('#param3');

                //执行重载
                table.reload('reload3', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: param.val()
                        ,uname:userName
                    }
                });
            },
            pxjaddbut:function() {

                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加'
                    ,area: ['460px', '250px']
                    ,resize:false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/MDFInfo.html?userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('reload3', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                })
            },
            pxjedit:function () {
                var checkStatus = table.checkStatus('reload3')
                    ,datas = checkStatus.data;

                if (datas.length !=1) {
                    layer.alert('请选择一条数据！')
                    return;
                }
                var id=datas[0].ID

                var val1=datas[0].DISTRIBUTION_NAME




                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['460px', '250px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/ITInfoDatabase/MDFInfo.html?but=edit&id='+id+'&val1='+val1+'&userName='+userName
                    ,zIndex: layer.zIndex //重点1
                    ,end: function () {
                        //执行重载
                        table.reload('reload3', {
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        return false;
                    }


                });



            },
            pxjdelt:function () {
                var checkStatus = table.checkStatus('reload3')
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
                    var parData = [{"name":"配线架名称","valu1":datas.DISTRIBUTION_NAME}];
                    queryData('../ItInfoData/delDistribution.do', {id: jobid}, function (red) {
                        layer.close(indexlod);
                        if (red.code == 0) {

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'配线架信息','operTable':'IT_ASSET_DISTRIBUTION','descr':jobid,'createName':userName}
                            queryData('../log/addlog.do', param, function (redt) {});

                            table.reload('reload3', {
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
            pxjexportbut:function () {

                var bodysArr= [{'field1': "配线架名称"}]
                var pam = $('#param2').val();
                var redpar={param: pam,uname:userName}

                queryData('../ItInfoData/exlDistribution.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].DISTRIBUTION_NAME}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '配线架信息' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        $('#pxjbut1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#pxjbut2').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#pxjbut3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#pxjbut4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //
        $('#pxjreload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



        });






}




//
// function submit($,params){
//     $.post('${ctx}/golbal/add1', params, function (res) {
//         if (res.status==1) {
//             layer.msg(res.message,{icon:1},function(index){
//                 CloseWin();
//             })
//         }else if(res.status==2){
//             layer.msg(res.message,{icon:0},function(){
//                 parent.location.href='${ctx}/golbal/main';
//                 CloseWin();
//             })
//         }else{
//             layer.msg(res.message,{icon:0},function(){
//                 location.reload(); // 页面刷新
//                 return false
//             })
//         }
//     }, 'json');
// }
// debugger;
//
//关闭页面

function CloseWin(){

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}