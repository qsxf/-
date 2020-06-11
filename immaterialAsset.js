
function infoImmaterialAsset(){
    layui.use(['table', 'form', 'layer','laydate'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        //方法级渲染
        table.render({
            elem: '#user'
            ,url: '../json/data2.json'
            ,cols: [[
                {type:'radio',title:'选择' }
                ,{type:'numbers',title:'序号'}

                ,{field:'fie1', width:100,align: 'center',title:'软件编号'}
                ,{field:'fie8', width:100,align: 'center',title:'软件名称'}
                ,{field:'fie8',width: 100,align: 'center',title:'软件分类'}
                ,{field:'fie8',width: 100,align: 'center',title:'品牌'}
                /*,{field:'fie5', width:160,align: 'center',title:'型号'}*/
                ,{field:'fie8',width: 100,align: 'center',title:'数量'}
                ,{field:'fie8',width: 150,align: 'center',title:'License期限'}
                ,{field:'fie8',width: 150,align: 'center',title:'License个数'}
                ,{field:'fie8',width: 100,align: 'center',title:'采购单位'}
                ,{field:'fie8',width: 150,align: 'center',title:'使用单位'}
                ,{field:'fie8',width: 150,align: 'center',title:'购买价格（元）'}
                ,{field:'fie8',width: 150,align: 'center',title:'入账日期'}
                ,{field:'fie8',width: 150,align: 'center',title:'购买日期'}
                ,{field:'fie8',width: 150,align: 'center',title:'备注'}
                ,{field:'fie8',width: 150,align: 'center',title:'创建人'}
                ,{field:'fie8',width: 150,align: 'center',title:'创建时间'}
                ,{field:'', templet:'#ckjob',width:260,align: 'center',title:'操作'}

            ]]
            , height: 'full-140'
            ,id: 'testReload'
            ,page: true
            ,limit:20
            // ,toolbar: '#toolbarDemo1'

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
        //监听性别操作
        // form.on('switch(sexDemo)', function(obj){
        //     // layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
        //     var   bol="";
        //     var    redData={}
        //
        //
        //     // console.log(this.value + ' ，，' + this.name + '：'+ obj.elem.checked, obj.othis);
        //     if (obj.elem.checked) {
        //         bol="是";
        //         redData={id:this.value,ENABLE:'1'}
        //     }else {
        //         bol="否";
        //         redData={id:this.value,ENABLE:'0'}
        //     }
        //     var x=obj.elem.checked;
        //
        //
        //     layer.open({
        //         title:'是否启用？'
        //         ,icon:3
        //         ,content: bol
        //         ,btn: ['确定', '取消']
        //         ,yes: function(index, layero){
        //
        //             queryData('../JobRecord/bolEnable.do', redData, function (rec) {
        //
        //             });
        //             layer.close(index);
        //         }
        //         ,btn2: function(index, layero){
        //             //按钮【按钮二】的回调
        //             obj.elem.checked=!x;
        //             form.render();
        //         }
        //         ,cancel: function(){
        //             //右上角关闭回调
        //             obj.elem.checked=!x;
        //             form.render();
        //         }
        //     });
        //     return false;
        //
        // });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#jobname');
                var jmane = $('#name');
                var qiyongfou=$('#qiyongfou');
                var createddate=$('#createddate');
                var zhuangtai=$('#zhuangtai');
                // console.log(zhuangtai.val());
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {

                        NAME: demoReload.val(),
                        ENABLE:qiyongfou.val(),
                        CREATED_DATE:createddate.val(),
                        jobstate:zhuangtai.val(),
                        jname:jmane.val(),
                    }
                });
            },
            addbut:function() {
                var checkStatus = table.checkStatus('testReload')
                    ,data = checkStatus.data;

                // if (data.length>1){
                //     layer.msg('只能选择一行数据！', {icon: 7});
                //     return;
                // }


                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '添加无形资产'
                    ,area: ['600px', '540px']
                    ,resize :false
                    // ,offset: [10 ,200 ]
                    , maxmin:true
                    ,btn: ['保存', '取消']
                    ,content:'../view/collect/addAndEditCollection.html'
                    ,zIndex: layer.zIndex //重点1
                    , success: function(layero, index){

                    }

                    , yes:function(index,layero){


                //         //作业名称，数据源id，job文件id，生效日期，调度周期，触发时间，是否启用，间隔时长，周，月
                //         var fromdata = {jobname:"" ,jdescribe:"",database:"",jobRoute:"",sxdate:"",ddzhouqi:"",time:"",qiyongfou:"",txtPersonNumber:"",zhou:"",sum1:"",module:""};
                //         var body = layer.getChildFrame('body',index);
                //         // console.log(body.html());
                //         var form = body.find("#addScheduling").serializeArray();//获取指定id的表单
                //
                //
                //
                //         $.each(form, function () {
                //             fromdata[this.name] = this.value;
                //             // console.log(this.name)
                //         });
                //         <!--0:只执行一次，1:按频率执行，2：按每天执行，3：按每周执行，4：按每月执行-->
                //
                //         if (fromdata.database=='') {
                //             alert("请选择数据源！");
                //             return
                //         }
                //         if (fromdata.jobRoute=='') {
                //             alert("请选择调度文件！");
                //             return
                //         }
                //         if (fromdata.sxdate=='') {
                //             alert("请选择生效日期！");
                //             return
                //         }
                //
                //         if (fromdata.time=='') {
                //             alert("请选择时间！");
                //             return
                //         }
                //
                //
                //
                //         if (fromdata.ddzhouqi=='1'){
                //             if (fromdata.txtPersonNumber==''){
                //
                //                 alert("请填写间隔时长！");
                //                 return
                //             }
                //
                //         }
                //         if (fromdata.ddzhouqi=='3'){
                //             if (fromdata.zhou==''){
                //
                //                 alert("请选择星期！");
                //                 return
                //             }
                //
                //         }
                //         if (fromdata.ddzhouqi=='4'){
                //             if (fromdata.sum1==''){
                //
                //                 alert("请选择几号！");
                //                 return
                //             }
                //         }
                //         var indexlod = layer.load(1);
                //         queryData('../JobRecord/addScheduling.do', fromdata, function (rec) {
                //
                //             if(rec.code==0){
                //
                //                layer.close(index); //再执行关闭
                //
                //                 table.reload('testReload', {
                //                     page: {
                //                         curr: 1 //重新从第 1 页开始
                //                     }});
                //
                //                 layer.msg(rec.msg, {icon: 1});
                //             }else {
                //                 layer.msg(rec.msg, {icon: 2});
                //             }
                //
                //             layer.close(indexlod);
                //         });
                //
                    }
                })
            },


            edit:function () {
                var checkStatus = table.checkStatus('testReload')
                    ,data = checkStatus.data;
                // if (data[0].jobstate=='1'){
                //     layer.msg('执行中的作业不可修改！', {icon: 7});
                //     return;
                // }
                // if (data.length>1){
                //     layer.msg('只能选择一行数据！', {icon: 7});
                //     return;
                // }
                //
                // if (data[0].jobstate=='1'){
                //     layer.msg('执行中的作业不可修改！', {icon: 7});
                //     return;
                // }


                    // console.log(data[i].j_id );
                    layer.open({
                        type: 2 //此处以iframe举例
                        ,title: '修改无形资产'
                        ,area: ['600px', '540px']
                        ,resize :false
                        , maxmin:true
                        // ,offset: [10 ,200 ]
                        ,btn: ['保存', '取消']
                        ,content:'../view/collect/addAndEditCollection.html'
                        ,zIndex: layer.zIndex //重点1
                        , success: function(layero, index){

                        }

                        , yes:function(index,layero){
                            // // console.log(data[0].id );
                            // var  jobid=data[0].s_id;
                            // var fromdata = {jobname:"" ,jdescribe:"",databaseid:"",sid:jobid,start_date:"",type:"",time:"",qiyongfou:"",frequency:"",zhou:"",sum1:"",module:""};
                            // var body = layer.getChildFrame('body',index);
                            // // console.log(body.html());
                            // var form = body.find("#editScheduling").serializeArray();//获取指定id的表单
                            //
                            // $.each(form, function () {
                            //     fromdata[this.name] = this.value;
                            //     // console.log(this.name)
                            // });
                            // <!--0:只执行一次，1:按频率执行，2：按每天执行，3：按每周执行，4：按每月执行-->
                            //
                            // if (fromdata.databaseid=='') {
                            //     alert("请选择数据源！");
                            //     return
                            // }
                            // if (fromdata.jobRoute=='') {
                            //     alert("请选择调度文件！");
                            //     return
                            // }
                            // if (fromdata.start_date=='') {
                            //     alert("请选择生效日期！");
                            //     return
                            // }
                            //
                            // if (fromdata.time=='') {
                            //     alert("请选择时间！");
                            //     return
                            // }
                            //
                            //
                            //
                            // if (fromdata.type=='1'){
                            //     if (fromdata.frequency==''){
                            //
                            //         alert("请填写间隔时长！");
                            //         return
                            //     }
                            //
                            // }
                            // if (fromdata.type=='3'){
                            //     if (fromdata.zhou==''){
                            //
                            //         alert("请选择星期！");
                            //         return
                            //     }
                            //
                            // }
                            // if (fromdata.type=='4'){
                            //     if (fromdata.sum1==''){
                            //
                            //         alert("请选择几号！");
                            //         return
                            //     }
                            // }
                            // var indexlod = layer.load(1);
                            // queryData('../JobRecord/editScheduling.do', fromdata, function (rec) {
                            //
                            //     if(rec.code==0){
                            //
                            //         layer.closeAll();
                            //
                            //         // layer.msg('更新完成！', {icon: 1});
                            //         layer.close(index); //再执行关闭
                            //
                            //         table.reload('testReload', {
                            //             page: {
                            //                 curr: 1 //重新从第 1 页开始
                            //             }});
                            //
                            //
                            //         layer.msg(rec.msg, {icon: 1});
                            //     }else {
                            //         layer.msg(rec.msg, {icon: 2});
                            //     }
                            //
                            // });
                            // layer.close(indexlod);
                        }
                    });



            },



            // chakan:function () {
            //     var checkStatus = table.checkStatus('testReload')
            //         ,data = checkStatus.data;
            //     if (data.length > 1) {
            //         layer.msg('只能选择一行数据！', {icon: 7});
            //         return;
            //     }
            //     // layer.msg('选中了：'+ data.length + ' 个');
            //     for (var i = 0; i < data.length; i++) {
            //         layer.open({
            //             type: 2 //此处以iframe举例
            //             , title: '查看'
            //             , area: ['560px', '450px']
            //             , offset: [2, 200]
            //             , resize: false
            //             , content: 'panel/seepanel.html?id=' + data[i].s_id
            //             , btn: ['关闭'] //只是为了演示
            //
            //             , btn2: function () {
            //                 layer.closeAll();
            //             }
            //
            //             , zIndex: layer.zIndex //重点1
            //             , success: function (layero) {
            //                 layer.setTop(layero); //重点2
            //             }
            //         });
            //
            //
            //     }
            // },

            delt:function () {
                var checkStatus = table.checkStatus('testReload')
                    ,data = checkStatus.data;
                var jonname = "";
                var jobid = "";
                if (data.length == 0) {

                    return;
                }


                for (var i = 0; i < data.length; i++) {

                    if (data[i].jobstate == '1') {
                        layer.msg('执行中的作业不可删除！', {icon: 7});
                        return;
                    }

                    jonname += data[i].NAME ;
                    jobid += data[i].s_id + ",";
                }

                layer.confirm(jonname, {icon: 3, title: '是否删除采集服务信息！'}, function (index) {
                    // var indexlod = layer.load(1);
                    // queryData('../JobRecord/ByIddelet.do', {id: jobid}, function (rec) {
                    //     // layer.msg('删除完成！', {icon: 1});
                    //
                    //     table.reload('testReload', {
                    //         page: {
                    //             curr: 1 //重新从第 1 页开始
                    //         }
                    //     });
                    //
                    // });
                    // layer.close(index);
                    // layer.close(indexlod);

                });
            },
            runBut:function () {
                var checkStatus = table.checkStatus('testReload')
                    ,data = checkStatus.data;
                var jobnames = "";
                var jobid = "";
                var a;
                if (data.length == 0) {

                    return;
                }

                for (var i = 0; i < data.length; i++) {
                    if (data[i].jobstate == '1') {
                        layer.msg('执行中的作业不可运行！', {icon: 7});
                        return;
                    }

                    jobid += data[i].id + ","
                    jobnames += data[i].NAME

                }
                layer.confirm(jobnames, {icon: 3, title: '是否运行！'}, function (index) {
                    // var indexlod = layer.load(1);
                    // queryData('../ETRController/runjob.do', {jobids: jobid}, function (rec) {
                    //     layer.msg('正在运行！', {icon: 1});
                    //
                    // });
                    //
                    //
                    // setTimeout(function () {
                    //     table.reload('testReload', {
                    //         page: {
                    //             curr: 1 //重新从第 1 页开始
                    //         }
                    //     });
                    // }, 2000);
                    // layer.close(indexlod);
                });


            },



        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



        //工具栏事件
        // table.on('toolbar(user)', function(obj){
        //     var checkStatus = table.checkStatus(obj.config.id);
        //     switch(obj.event){
        //         case

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
        $('#but4').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#reload').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });




        table.on('tool(user)', function(obj){
            var data = obj.data;
            if(obj.event === 'detai2'){
                var that = this;
                window.location.href="../view/immaterialAsset/editImmaterialAsset.html";
            }
            if(obj.event === 'detail'){
                // var othis = $(this), method = othis.data('method');
                // active[method] ? active[method].call(this, othis) : '';
                // layer.msg('ID：'+ data.id + ' 的查看操作');

                var that = this;
                //多窗口模式，层叠置顶
                window.location.href="../view/immaterialAsset/infoImmaterialAsset.html";
                layer.open({
                    type: 1 //此处以iframe举例
                    ,title: '详情'
                    ,area: ['760px', '500px']
                    , maxmin:true
                    ,offset: [2 ,200 ]
                    ,content: '<div>内容</div>'
                    ,btn: ['关闭'] //只是为了演示

                    ,btn2: function(){
                        layer.closeAll();
                    }

                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                });
            }
            if(obj.event === 'detai3'){
                // var othis = $(this), method = othis.data('method');
                // active[method] ? active[method].call(this, othis) : '';
                // layer.msg('ID：'+ data.id + ' 的查看操作');

                var that = this;
                //多窗口模式，层叠置顶
                //window.location.href="../view/immaterialAsset/infoImmaterialAsset.html";
                layer.open({
                    type: 1 //此处以iframe举例
                    ,title: 'License分配'
                    ,area: ['760px', '500px']
                    , maxmin:true
                    ,offset: [2 ,200 ]
                    ,content: '<div style="margin-left: 40px;margin-top: 30px">' +
                        '<form class="layui-form" action="">' +
                        '<div id="main">' +
                        '<div>' +
                        '<div class="layui-inline">' +
                        '     <label class="layui-form-label">公司名称：</label>' +
                        '            <div class="layui-input-inline">' +
                        '                  <select name="modules" lay-verify="required">' +
                        '                            <option value="">请选择</option>' +
                        '                            <option value="1">layer</option>' +
                        '                            <option value="2">form</option>' +
                        '                   </select>' +
                        '            </div>' +
                        ' </div>' +
                        '<div class="layui-inline" style="margin-top: 15px">' +
                        '     <label class="layui-form-label">License个数：</label>' +
                        '            <div class="layui-input-inline">' +
                        '                                    <input type="text" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input"  placeholder="请输入">' +
                        '            </div>' +
                        ' </div>' +
                        /*'<div class="layui-inline" style="margin-left: 20px">' +
                        '   <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" id="addbtn" name="addbtn"><i class="layui-icon"></i></button>' +
                        '</div>' +*/
                        '</div>' +
                        '</div>' +
                        '<div class="layui-form-item" style="margin-top: 20px">' +
                        '    <div class="layui-input-block">' +
                        '      <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>' +
                        '      <button type="reset" class="layui-btn layui-btn-primary">重置</button>' +
                        '    </div>' +
                        '  </div>' +
                        '</form>' +
                        '</div>' +
                        '<script>' +

                       /* '$(document).ready(function(){\n' +
                        '    $("#addbtn").click(function(){\n' +
                        '        var html = \'<div>' +
                        '<div class="layui-inline">' +
                        '<label class="layui-form-label">公司名称：</label>' +
                        '<div class="layui-input-inline">' +
                        '<select name="modules" lay-verify="required">' +
                        '<option value="">请选择</option>' +
                        '<option value="1">layer</option><option value="2">form</option></select></div></div><div class="layui-inline" style="margin-top: 15px">' +
                        '<label class="layui-form-label">License个数：</label><div class="layui-input-inline"><input type="text" name="phone" lay-verify="required|phone" ' +
                        'autocomplete="off" class="layui-input"  placeholder="请输入"></div></div>' +
                        '</div>\';' +
                        '        $("#main").append(html);' +
                        '    });\n' +
                        '});' +*/
                        ' layui.use(\'form\', function(){\n' +
                        '        var form = layui.form; \n' +
                        '        form.render();\n' +
                        ' }); \n' +
                        '</script>'
                    ,btn: ['关闭'] //只是为了演示

                    ,btn2: function(){
                        layer.closeAll();
                    }

                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                });
            }
        });

        });
        // //提交监听事件
        // form.on('submit(save)', function (data) {
        //     var index = layer.open();
        //     layer.close(index);
        //     // params = data.field;
        //     // alert(JSON.stringify(params));
        //     // // submit($,params);
        //     // CloseWin();
        //     // return false;
        // });
        //
        // form.on('reset(closeBtn)', function () {
        //     var index = layer.open();
        //     layer.close(index);
        //     // params = data.field;
        //     // alert(JSON.stringify(params));
        //     // // submit($,params);
        //     // CloseWin();
        //     // return false;
        // });
        // debugger;




        // //常规用法
        //
        // laydate.render({
        //     elem: '#createddate'
        // });

        // //监听工具条





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