

function infoDepreciation(userName){

    layui.config({
        base: '../layui_exts/'
    }).use(['table', 'form', 'layer','laydate','excel'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;


        //方法级渲染
        table.render({
            elem: '#tableId'
            ,url: '../repairAsset/selectDepreciation.do'
            ,cols: [[
                {type:'checkbox',title:'选择' }
                // ,{type:'numbers',title:'序号'}

                ,{field:'ASSETS_ID', width:100,title:'资产编号'}
                ,{field:'ASSETS_NAME', width:100,title:'资产名称'}
                ,{field:'DEPREDATE',minWidth: 100,title:'折旧年月'}
                ,{field:'DEPREMETHOD', width:100,title:'折旧方法'}
                ,{field:'PRICE',width: 100,title:'购置金额'}
                ,{field:'SERVICE_LIFE', width:160,align: 'center',title:'使用寿命（月）'}

                ,{field:'NETRATE',width: 120,title:'净残率（%）'}
                ,{field:'DEPREMONTHS',width: 100,title:'折旧月数'}
                ,{field:'INITIALNETWORTH',width: 100,title:'初期净值'}
                ,{field:'DEPRENOWMONTH',width: 100,title:'本月折旧'}
                ,{field:'DEPREALL',width: 150,title:'累计折旧'}
                ,{field:'NETWORTH',width: 150,title:'净值'}
                ,{field:'', templet:'#ckjob',width:180,align: 'center',title:'操作'}

            ]]
            , height: 'full-80'
            ,id: 'testReload'
            ,page: true

        });


        var $ = layui.$, active = {
            reload: function(){
                var pam = $('#param').val();


                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: pam


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
                    var parData = [{"name":"折旧编号","valu1":data.DEPRECIATION_ID},
                        {"name":"资产编号","valu1":data.ASSETS_ID},
                        {"name":"折旧年月","valu1":data.DEPREDATE},
                        {"name":"折旧方法","valu1":data.DEPREMETHOD},
                        {"name":"购置金额","valu1":data.PRICE},
                        {"name":"使用寿命（月）","valu1":data.SERVICE_LIFE},
                        {"name":"净残率（%）","valu1":data.NETRATE},
                        {"name":"折旧月数","valu1":data.DEPREMONTHS},
                        {"name":"初期净值","valu1":data.INITIALNETWORTH},
                        {"name":"本月折旧","valu1":data.DEPRENOWMONTH},
                        {"name":"累计折旧","valu1":data.DEPREALL},
                        {"name":"净值","valu1":data.NETWORTH}
                    ];
                    queryData('../repairAsset/delDepreciation.do', {id: jobid}, function (rec) {
                        if (rec.code="0"){

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'资产折旧信息','operTable':'IT_ASSET_DEPRECIATION','descr':strDescr,'createName':userName}
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

                var bodysArr= [{'field1': "资产编号", 'field2': "资产名称",'field3': "折旧年月",'field4': "折旧方法",'field5': "购置金额",'field6': "使用寿命（月）",'field7': "净残率（%）",'field8': "折旧月数",'field9': "初期净值",'field10': "本月折旧",'field11': "累计折旧",'field12': "净值"}]
                var pam = $('#param').val();
                var redpar={param: pam}

                queryData('../repairAsset/exlDepreciation.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].ASSETS_ID,'field2':rec[i].ASSETS_NAME,'field3':rec[i].DEPREDATE,'field4':rec[i].DEPREMETHOD,'field5':rec[i].PRICE,'field6':rec[i].SERVICE_LIFE,'field7':rec[i].NETRATE,'field8':rec[i].DEPREMONTHS,'field9':rec[i].INITIALNETWORTH,'field10':rec[i].DEPRENOWMONTH,'field11':rec[i].DEPREALL,'field12':rec[i].NETWORTH}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '资产折旧' + new Date().toLocaleString() + '.xlsx', 'xlsx');
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
        $('#but3').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        $('#reload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



        table.on('tool(tableFil)', function(obj){
            var tdata = obj.data;
            if(obj.event === 'detai2'){

                var id=tdata.ID
                var val1=tdata.DEPRECIATION_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.DEPREDATE
                var val4=tdata.DEPREMETHOD
                var val5=tdata.SERVICE_LIFE
                var val6=tdata.NETRATE
                var val7=tdata.DEPREMONTHS

                var val8=tdata.INITIALNETWORTH
                var val9=tdata.DEPRENOWMONTH
                var val10=tdata.DEPREALL
                var val11=tdata.NETWORTH

                window.location.href="../view/depreciation/editDepreciation.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&id="+id+'&userName='+userName;
            }

        });

    });



}



//关闭页面
function CloseWin(){

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}