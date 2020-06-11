
function infoAllcationAsset(userName){
    layui.config({
        base: '../layui_exts/'
    }).extend({
        multiSelect:'/multiSelect',
    }).use(['table', 'form', 'layer','laydate','excel','multiSelect'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        var excel = layui.excel;
        var multiSelect = layui.multiSelect;
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
            ,url: '../allocationAsset/selectAllocationAsset.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                //,{type:'numbers',title:'序号'}
                ,{field:'ASSETS_ID', align: 'center',width:100,title:'资产编号'}
                ,{field:'ASSETS_NAME', align: 'center',width:180,title:'资产名称'}
                ,{field:'ALLOCATIONDATE',align: 'center',width: 150,title:'调拨日期'}
                ,{field:'ALLOCATION_ID',align: 'center', width:100,title:'调拨编号'}
                ,{field:'BRAND',align: 'center',width: 200,title:'品牌'}
                ,{field:'TYPE_NAME', width:160,align: 'center',title:'型号'}
                ,{field:'NUM',align: 'center',width: 100,title:'调拨数量'}
                ,{field:'APPLYDEPART',align: 'center',width: 150,title:'申请部门'}
                ,{field:'APPLYCOMPNM',align: 'center',width: 150,title:'申请公司'}
                ,{field:'UNIT',align: 'center',width: 100,title:'单位'}
                ,{field:'APPLICANT',align: 'center',width: 150,title:'申请人'}
                ,{field:'RESPMAN',align: 'center',width: 150,title:'责任人'}
                ,{field:'ASSETS_COMPANY',align: 'center',width: 150,title:'所属公司'}
                ,{field:'', templet:'#ckjob',width:180,align: 'center',title:'操作'}
            ]]
            , height: 'full-80'
            ,id: 'testReload'
            ,page: true
            ,limit:20
            ,where:{
                uname:userName,
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
                    var pam7=$("#pam7").val()
                    var pam8=$("#pam8").val()
                    var pam9=$("#pam9").val()
                    var pam10=$("#pam10").val()
                    var pam11=$("#pam11").val()
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
                            param6: pam6,
                            param7: pam7,
                            param8: pam8,
                            param9: pam9,
                            param10: pam10,
                            param11: pam11

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
                var pam7="";
                var pam8="";
                var pam9="";
                var pam10="";
                var pam11="";
                if (isChecked){
                    pam="";
                    date="";
                    pam1=$("#date").val()
                    pam2=$("#pam2").val()
                    pam3=$("#pam3").val()
                    pam4=$("#pam4").val()
                    pam5=$("#pam5").val()
                    pam6=$("#pam6").val()
                    pam7=$("#pam7").val()+""
                    pam8=$("#pam8").val()
                    pam9=$("#pam9").val()
                    pam10=$("#pam10").val()
                    pam11=$("#pam11").val()
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
                        param6: pam6,
                        param7: pam7,
                        param8: pam8,
                        param9: pam9,
                        param10: pam10,
                        param11: pam11
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
                    var parData = [{"name":"调拨编号","valu1":data.ALLOCATION_ID},
                        {"name":"资产编号","valu1":data.ASSETS_ID},
                        {"name":"调拨日期","valu1":data.ALLOCATIONDATE},
                        {"name":"调拨数量","valu1":data.NUM},
                        {"name":"申请人","valu1":data.APPLICANT},
                    ];
                    queryData('../allocationAsset/delAllocationAsset.do', {ID: jobid}, function (rec) {
                        if (rec.code="0"){

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'资产调拨信息','operTable':'IT_ASSET_ALLOCATIONASSET','descr':jobid,'createName':userName}
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

                var bodysArr= [{'field1': "资产编号", 'field2': "资产名称",'field3': "调拨日期",'field4': "调拨编号",'field5': "品牌",'field6': "型号",'field7': "调拨数量",'field8': "单位",'field9': "申请人",'field10': "联系方式",'field11': "申请公司",'field12': "现存放地址",'field13': "调拨地址",'field14': "责任人",'field15': "调拨情况"}]
                var vparam = $('#param');
                var vtype = $('#type');
                var vcdate=$('#bzDate');
                var redpar={ param: vparam.val(),
                    type:vtype.val(),
                    bzDate:vcdate.val(),
                    uname:userName}

                queryData('../allocationAsset/exlAllocationAsset.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].ASSETS_ID,'field2':rec[i].ASSETS_NAME,'field3':rec[i].ALLOCATIONDATE,'field4':rec[i].ALLOCATION_ID,'field5':rec[i].BRAND,'field6':rec[i].TYPE_NAME,'field7':rec[i].NUM,'field8':rec[i].UNIT,'field9':rec[i].APPLICANT,'field10':rec[i].PHONE,'field11':rec[i].APPLYDEPART,'field12':rec[i].LOCATION,'field13':rec[i].TOADRESS,'field14':rec[i].RESPMAN,'field15':rec[i].ALLOCATIONDESC}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '调拨清单' + new Date().toLocaleString() + '.xlsx', 'xlsx');
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

        $('#delt').on('click', function(){
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
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.ALLOCATIONDATE
                var val4=tdata.ALLOCATION_ID
                var val5=tdata.BRAND
                var val6=tdata.TYPE_NAME
                var val7=tdata.NUM

                var val8=tdata.UNIT
                var val9=tdata.APPLICANT
                var val10=tdata.PHONE
                var val11=tdata.APPLYDEPART
                var val12=tdata.LOCATION
                var val13=tdata.TOADRESS
                var val14=tdata.RESPMAN
                var val15=tdata.ALLOCATIONDESC
                var val16=tdata.ID
                var val17=tdata.APPLYCOMPNM
                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/allocationAsset/editAllocationAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+'&userName='+userName+"&val17="+val17
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
               // window.location.href="../view/allocationAsset/editAllocationAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+'&userName='+userName+"&val17="+val17;
            }
            if(obj.event === 'detai1'){
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.ALLOCATIONDATE
                var val4=tdata.ALLOCATION_ID
                var val5=tdata.BRAND
                var val6=tdata.TYPE_NAME
                var val7=tdata.NUM
    
                var val8=tdata.UNIT
                var val9=tdata.APPLICANT
                var val10=tdata.PHONE
                var val11=tdata.APPLYDEPART
                var val12=tdata.LOCATION
                var val13=tdata.TOADRESS
                var val14=tdata.RESPMAN
                var val15=tdata.ALLOCATIONDESC
                var val16=tdata.APPLYCOMPNM
                layer.open({
                    type: 2
                    ,title: '详情'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/allocationAsset/infoAllocationAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+'&userName='+userName+"&val16="+val16
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
                //window.location.href="../view/allocationAsset/infoAllocationAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+'&userName='+userName+"&val16="+val16;
            }
        });
        form.on("select(COMPANY)",function () {
            var optionstring = "";
            var cate1 = $("#pam8").val();
            $.ajax({
                url:"../stockAsset/selBM.do",
                contentType:"application/json",
                type:"post",
                dataType:"json",
                beforeSend:"",
                data:JSON.stringify(),
                success: function(data) {
                    $("select[name='modules']").empty();
                    var compnms = [];
                    for (var i=0;i<data.length;i++){
                        compnms[i] = data[i].COMPNM;
                    }

                    for(var i=0;i<compnms.length;i++){
                        for(var j=i+1;j<compnms.length;j++){
                            if(compnms[i]==compnms[j]){
                                //如果第一个等于第二个，splice方法删除第二个
                                compnms.splice(j,1);
                                j--;
                            }
                        }
                    }
                    for (var i = 0; i <compnms.length; i++) {
                        if (compnms[i]==null){
                            return
                        }else if(compnms[i]==cate1){

                            for (var j = 0;j<data.length;j++){
                                if (compnms[i] == data[j].COMPNM){
                                    /*
                                                                        $("#gsgs").append(" <option value='"+data[j].NAME+"'>"+data[j].NAME+"</option>");
                                    */
                                    $("#pam7").append("<option value='" + data[j].NAME+"' id='"+ data[j].NAME+"'>" + data[j].NAME + "</option>");

                                }
                            }
                            multiSelect.render("select");
                            //form.render();
                        }
                    }
                    //form.render();
                }

            });

        })
        $.ajax({
            url:"../stockAsset/selGS.do?uname="+userName,
            contentType:"application/json",
            type:"post",
            dataType:"json",
            beforeSend:"",
            data:JSON.stringify(),
            success: function(data) {
                $.ajax({
                    url:"../stockAsset/selGSGS.do?uname="+userName,
                    contentType:"application/json",
                    type:"post",
                    dataType:"json",
                    beforeSend:"",
                    data:JSON.stringify(),
                    success: function(obj) {

                        for (var i = 0; i <obj.length; i++) {
                            $("#pam8").append((new Option(obj[i].COMPNM )));
                            //$("#pam19").append((new Option(obj[i].COMPNM )));
                            form.render();
                        }
                        $("#pam8").val(data[0].COMPNM);
                       // $("#pam19").val(data[0].COMPNM);
                        $.ajax({
                            url:"../stockAsset/selBM.do",
                            contentType:"application/json",
                            type:"post",
                            dataType:"json",
                            beforeSend:"",
                            data:JSON.stringify(),
                            success: function(data) {
                                var cate1 = $("#pam8").val();
                                $("select[name='modules']").empty();
                                var compnms = [];
                                for (var i=0;i<data.length;i++){
                                    compnms[i] = data[i].COMPNM;
                                }

                                for(var i=0;i<compnms.length;i++){
                                    for(var j=i+1;j<compnms.length;j++){
                                        if(compnms[i]==compnms[j]){
                                            //如果第一个等于第二个，splice方法删除第二个
                                            compnms.splice(j,1);
                                            j--;
                                        }
                                    }
                                }
                                for (var i = 0; i <data.length; i++) {
                                    if (compnms[i]==null){
                                        return
                                    }else if(compnms[i]==cate1){
                                        // $("#COMPANY").append(new Option(compnms[i] ));
                                        // $("#gsgs").append("<optgroup label="+compnms[i]+">");

                                        for (var j = 0;j<data.length;j++){
                                            if (compnms[i] == data[j].COMPNM){
                                                //$("#pam16").append(" <option value='"+data[j].NAME+"'>"+data[j].NAME+"</option>");
                                                $("#pam7").append("<option value='" + data[j].NAME+"' id='"+ data[j].NAME+"'>" + data[j].NAME + "</option>");

                                            }
                                        }
                                        multiSelect.render("select");
                                        // $("#gsgs").append("</optgroup>");
                                        //form.render();
                                    }
                                }

                            }

                        });
                        form.render();

                    }
                });
                //$("select[name='sqgs']").empty();
                /*$("#pam15").val("大众公共本部");
                $("#pam19").val(data[0].COMPNM);*/

                form.render();
            }
        });
    });








}





function CloseWin(){

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}