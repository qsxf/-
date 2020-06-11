
function infoRepairAsset(userName){
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
            elem: '#user'
            ,url: '../repairAsset/selectRepairAsset.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}
                ,{field:'REPAIR_ID',align: 'center', width:100,title:'维修单号'}
                ,{field:'ASSETS_ID',align: 'center', width:100,title:'资产编号'}
                ,{field:'ASSETS_NAME',align: 'center',width: 180,title:'资产名称'}
                ,{field:'REPORTDATE',align: 'center', width:150,title:'报修时间'}
                ,{field:'REPORTER',align: 'center',width: 100,title:'报修人'}
                /*,{field:'FAULTTYPE', width:160,align: 'center',title:'故障类型'}*/
                ,{field:'FAULTCLASS',align: 'center',width: 100,title:'故障等级'}
                ,{field:'REPAIR_PRICE',align: 'center',width: 100,title:'维修金额'}
                ,{field:'FAULTDESC',align: 'center',width: 180,title:'故障内容   '}
                ,{field:'FINISHTIME',align: 'center',width: 150,title:'维修完成时间'}
                ,{field:'ASSETS_COMPANY',align: 'center',width: 150,title:'归属公司'}
                ,{field:'', templet:'#ckjob',width:100,align: 'center',title:'操作'}

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
        table.on('checkbox(user)', function(obj){
            $("input[name='layTableCheckbox']").each(function(i,obj){
                if($(this).is(':checked')){
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#e0ebeb"});  //被选中的行变色
                }else{
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#fff"});    //其他的置成白色
                }
            });
        });
        //监听行单击事件（双击事件为：rowDouble）
        /*table.on('rowDouble(user)', function(obj){
            // var data = obj.data;
            //
            // layer.alert(JSON.stringify(data), {
            //     title: '当前行数据：'
            // });



            layer.open({
                type: 1
                , title: '查询'
                , area: ['400px', '450px']
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
                  var pam1=$("#date").val()
                    var pam2=$("#pam2").val()
                    var pam3=$("#pam3").val()
                    var pam4=$("#pam4").val()
                    var pam5=$("#pam5").val()
                    var pam6=$("#pam6").val()
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
                            param6: pam6

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
                if (isChecked){
                    pam="";
                    date="";

                    pam1=$("#date").val()
                    pam2=$("#pam2").val()
                    pam3=$("#pam3").val()
                    pam4=$("#pam4").val()
                    pam5=$("#pam5").val()
                    pam6=$("#pam6").val()
                }
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {

                        param: pam,
                        uname:userName,
                        bzDate:date,
                        param1: pam1,
                        param2: pam2,
                        param3: pam3,
                        param4: pam4,
                        param5: pam5,
                        param6: pam6

                    }
                });
            } ,

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
                    var parData = [{"name":"维修单号","valu1":data.REPAIR_ID},
                        {"name":"资产编号","valu1":data.ASSETS_ID},
                        {"name":"报修人","valu1":data.REPORTER},
                    ];
                    queryData('../repairAsset/delRepairAsset.do', {bxcode: jobid}, function (rec) {
                      if (rec.code="0"){
                          var fieldName="";
                          var fieldAfter='';
                          $.each(parData, function () {

                              fieldName+=this.name+","
                              fieldAfter+=this.valu1+","
                          });
                          var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                          var param={'operType':'删除','operPage':'资产维修信息','operTable':'IT_ASSET_REPAIRASSET','descr':jobid,'createName':userName}
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

                var bodysArr= [{'field1': "维修单号", 'field2': "资产编号",'field3': "资产名称",'field4': "报修时间",'field5': "报修人",'field7': "故障等级",'field8': "故障内容",'field9': "维修完成时间"}]

                var pam = $('#param').val();
                var date = $('#bzDate').val();

                var redpar={
                    param: pam,
                    uname:userName,
                    bzDate:date}

                queryData('../repairAsset/exlRepairAsset.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].REPAIR_ID,'field2':rec[i].ASSETS_ID,'field3':rec[i].ASSETS_NAME,'field4':rec[i].REPORTDATE,'field5':rec[i].REPORTER,'field7':rec[i].FAULTCLASS,'field8':rec[i].FAULTDESC,'field9':rec[i].FINISHTIME}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '维修清单' + new Date().toLocaleString() + '.xlsx', 'xlsx');
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



        $('#reload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        table.on('tool(user)', function(obj){
            var tdata = obj.data;
            if(obj.event === 'detai2'){
                var id=tdata.ID

                var val1=tdata.REPAIR_ID
                var val2=tdata.ASSETS_ID
                var val3=tdata.ASSETS_NAME
                var val4=tdata.REPORTDATE
                var val5=tdata.REPORTER
               /* var val6=tdata.FAULTTYPE*/
                var val7=tdata.FAULTCLASS

                var val8=tdata.FAULTDESC
                var val9=tdata.FINISHTIME
                var val10=tdata.REPAIR_PRICE
                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/repairAsset/editRepairAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&id="+id+'&userName='+userName
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
                //window.location.href="../view/repairAsset/editRepairAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&id="+id+'&userName='+userName;
            }

        });

        });

}





function CloseWin(){

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}