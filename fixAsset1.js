
function infoFixAsset(userName){
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
            elem: '#date1'
            ,range: true
        });
        laydate.render({
            elem: '#date2'
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
            ,url: '../fixAsset/selectFixAsset.do'
            ,cols: [[
                {type:'checkbox',align: 'center',width: 70,title:'选择' }
                ,{type:'numbers',align: 'center',title:'序号'}
                ,{field:'ASSETS_ID', align: 'center',width:100,title:'资产编号'}
                ,{field:'ASSETS_NAME', align: 'center',width:180,title:'资产名称'}
                ,{field:'ASSETCLASS_1',align: 'center',minWidth: 120,title:'一级分类'}
                ,{field:'ASSETCLASS_2', align: 'center',width:120,title:'二级分类'}
                ,{field:'BRAND',align: 'center',width: 200,title:'品牌'}
                ,{field:'TYPE_NAME', align: 'center',width:160,align: 'center',title:'规格型号'}
                ,{field:'NUM',align: 'center',width: 100,title:'数量'}
                ,{field:'UNIT',align: 'center',width: 100,title:'单位'}
                ,{field:'POSTINGDATE',width: 150,align: 'center',title:'入账日期'}
                ,{field:'PURCHASEDATE',width: 150,align: 'center',title:'购买日期'}
                ,{field:'PRICE',align: 'center',width: 150,title:'购买价格(元)',
                    templet : function(data) {// 替换数据
                        if (data.PRICE != null){
                            return data.PRICE;
                        }else {
                            return "";
                        }

                    }}
                ,{field:'WARRANTY',align: 'center',width: 100,title:'质保期(年)'}
                ,{field:'SERVICELIFE',align: 'center',width: 150,title:'预计使用寿命(年)'}
                ,{field:'RESPMAN',align: 'center',width: 100,title:'责任人'}
                ,{field:'COMPANY',align: 'center',width: 150,title:'使用公司'}
                ,{field:'ATTRIBUTION',align: 'center',width: 100,title:'使用部门'}
                ,{field:'LOCATION',align: 'center',width: 150,title:'存放地点'}
                ,{field:'TYPENM',align: 'center',width: 150,title:'使用状态'/*,
                    templet : function(data) {// 替换数据

                        if(data.STATUS==1){
                            return "空闲状态";
                        }else if(data.STATUS==2){
                            return "使用状态";
                        }else if(data.STATUS==3){
                            return "维修状态";
                        }else if(data.STATUS==4){
                            return "报废状态";
                        }
                    }*/
                }


                ,{field:'ASSETS_COMPANY',align: 'center',width: 150,title:'归属公司'}
                ,{field:'USEDESC',align: 'center',width: 150,title:'备注',
                    templet : function(data) {// 替换数据
                        if (data.USEDESC != null){
                            return data.USEDESC;
                        }else {
                            return "";
                        }

                    }}
               // ,{field:'fie8',width: 150,title:'入库时间'}
                ,{field:'', templet:'#ckjob',width:360,align: 'center',title:'操作'}

            ]]
            , height: 'full-80'
            ,id: 'testReload'
            ,page: true
            ,limit:20
            ,where:{
                uname: userName,
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
/*
            $("input[name='layTableCheckbox']").each(function(i,obj){
                if($(this).is(':checked')){
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#5FB878"});  //被选中的行变色
                }else{
                    $("tr[data-index='" + (i - 1) + "']").attr({"style":"background:#fff"});    //其他的置成白色
                }
            });*/

        /*table.on('rowDouble(user)', function(obj){
            // var data = obj.data;
            //
            // layer.alert(JSON.stringify(data), {
            //     title: '当前行数据：'
            // });



            layer.open({
                type: 1
                , title: '查询'
                , area: ['450px', '450px']
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
                    var pam2=$("#pam2").val()
                    var pam3=$("#pam3").val()
                    var pam4=$("#pam4").val()
                    var pam5=$("#pam5").val()
                    var pam6=$("#pam6").val()
                    var pam7=$("#pam7").val()
                    var pam8=$("#pam8").val()
                    var pam9=$("#date1").val()
                    var pam10=$("#date2").val()
                    var pam11p=$("#pam11p").val()
                    var pam11l=$("#pam11l").val()
                    var pam12p=$("#pam12p").val()
                    var pam12l=$("#pam12l").val()
                    var pam13p=$("#pam13p").val()
                    var pam13l=$("#pam13l").val()
                    var pam14=$("#pam14").val()
                    var pam15=$("#pam15").val()
                    var pam16=$("#pam16").val()
                    var pam17=$("#pam17").val()
                    var pam18=$("#pam18").val()
                    var pam19=$("#pam19").val()
                    table.reload('testReload', {

                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                        // ,url: '../repairAsset/selectRepairAsset.do'
                        ,where: {
                            param: pam,
                            uname:userName,
                            bzDate:"",
                            param2: pam2,
                            param3: pam3,
                            param4: pam4,
                            param5: pam5,
                            param6: pam6,
                            param7: pam7,
                            param8: pam8,
                            param9: pam9,
                            param10: pam10,
                            param11p: pam11p,
                            param11l: pam11l,
                            param12p: pam12p,
                            param12l: pam12l,
                            param13p: pam13p,
                            param13l: pam13l,
                            param14: pam14,
                            param15: pam15,
                            param16: pam16,
                            param17: pam17,
                            param18: pam18,
                            param19: pam19
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
                var pam2="";
                var pam3="";
                var pam4="";
                var pam5="";
                var pam6="";
                var pam7="";
                var pam8="";
                var pam9="";
                var pam10="";
                var pam11p="";
                var pam11l="";
                var pam12p="";
                var pam12l="";
                var pam13p="";
                var pam13l="";
                var pam14="";
                var pam15="";
                var pam16="";
                var pam17="";
                var pam18="";
                var pam19="";
                if (isChecked){
                    pam="";
                    date="";

                    pam2=$("#pam2").val()
                    pam3=$("#pam3").val()
                    pam4=$("#pam4").val()
                    pam5=$("#pam5").val()
                    pam6=$("#pam6").val()
                    pam7=$("#pam7").val()
                    pam8=$("#pam8").val()
                    pam9=$("#date1").val()
                    pam10=$("#date2").val()
                    pam11p=$("#pam11p").val()
                    pam11l=$("#pam11l").val()
                    pam12p=$("#pam12p").val()
                    pam12l=$("#pam12l").val()
                    pam13p=$("#pam13p").val()
                    pam13l=$("#pam13l").val()
                    pam14=$("#pam14").val()
                    pam15=$("#pam15").val()
                    pam16=$("#pam16").val()+""
                    pam17=$("#pam17").val()
                    pam18=$("#pam18").val()
                    pam19=$("#pam19").val()
                }
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: pam,
                        uname: userName,
                        bzDate:date,
                        param2: pam2,
                        param3: pam3,
                        param4: pam4,
                        param5: pam5,
                        param6: pam6,
                        param7: pam7,
                        param8: pam8,
                        param9: pam9,
                        param10: pam10,
                        param11p: pam11p,
                        param11l: pam11l,
                        param12p: pam12p,
                        param12l: pam12l,
                        param13p: pam13p,
                        param13l: pam13l,
                        param14: pam14,
                        param15: pam15,
                        param16: pam16,
                        param17: pam17,
                        param18: pam18,
                        param19: pam19
                    }
                });
            } ,


            edit:function () {
                var checkStatus = table.checkStatus('testReload')
                    ,data = checkStatus.data;
                if (data.length !=1) {

                    layer.alert('请选择一条数据！', {icon: 1});
                    return;
                }
                var val1=data[0].ASSETS_ID;
                var val2=data[0].ASSETS_NAME;
                var val3=data[0].ASSETCLASS_1;
                var val4=data[0].ASSETCLASS_2;
                var val5=data[0].BRAND;
                var val6=data[0].TYPE_NAME;
                var val7=data[0].NUM;
                var val8=data[0].UNIT;
                var val9=data[0].POSTINGDATE;
                var val10=data[0].PURCHASEDATE;
                var val11=data[0].WARRANTY;
                var val12=data[0].SERVICELIFE;
                var val13=data[0].ATTRIBUTION;
                var val14=data[0].RESPMAN;
                var val15=data[0].PRICE;
                var val16=data[0].LOCATION;
                var val17=data[0].USEDESC;
                var val18=data[0].ID;
                var val19=data[0].COMPANY;
                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/FixAsset/editFixAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+"&userName="+userName+"&val19="+val19
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
                //window.location.href="../view/FixAsset/editFixAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+"&userName="+userName+"&val19="+val19;

            },
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

               /* var id=data[0].ID;*/

                layer.confirm('是否删除选择的数据！', {icon: 3, title: '提示'}, function (index) {
                    var indexlod = layer.load(1);
                    var parData = [{"name":"资产编号","valu1":data.ASSETS_ID},
                        {"name":"资产名称","valu1":data.ASSETS_NAME},
                        {"name":"一级分类","valu1":data.ASSETCLASS_1},
                        {"name":"二级分类","valu1":data.ASSETCLASS_2},
                        {"name":"品牌","valu1":data.BRAND},
                        {"name":"规格型号","valu1":data.TYPE_NAME},
                        {"name":"数量","valu1":data.NUM},
                        {"name":"单位","valu1":data.UNIT},
                        {"name":"入账日期","valu1":data.POSTINGDATE},
                        {"name":"购买日期","valu1":data.PURCHASEDATE},
                        {"name":"质保期(年)","valu1":data.WARRANTY},
                        {"name":"预计使用寿命(年)","valu1":data.SERVICELIFE},
                        {"name":"使用部门","valu1":data.ATTRIBUTION},
                        {"name":"责任人","valu1":data.RESPMAN},
                        {"name":"购买价格","valu1":data.PRICE},
                        {"name":"存放地点","valu1":data.LOCATION},
                        {"name":"归属公司","valu1":data.COMPANY},
                        {"name":"入库时间","valu1":data.INCOMING_TIME},
                        {"name":"备注","valu1":data.USEDESC},
                    ];
                    queryData('../fixAsset/delFixAsset.do', {id: jobid}, function (rec) {
                        if (rec.code="0"){

                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'资产台账信息','operTable':'IT_ASSET_FIXASSET','descr':jobid,'createName':userName}
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

                var bodysArr= [{'field1': "资产编号", 'field2': "资产名称",'field3': "一级分类",'field4': "二级分类",'field5': "品牌",'field6': "规格型号",'field7': "数量",'field8': "单位",'field9': "入账日期",'field10': "购买日期",'field11': "购买价格",'field12': "保质期(年)",'field13': "预计使用寿命(年)",'field14': "责任人",'field15': "使用公司",'field16': "使用部门",'field17': "存放地点",'field18': "使用状态",'field19': "备注",'field20': "归属公司"}]
                var vparam = $('#param');
                var vtype = $('#type');
                var vcdate=$('#bzDate');
                var redpar={ param: vparam.val(),
                    type:vtype.val(),
                    bzDate:vcdate.val(),
                    uname:userName}

                queryData('../fixAsset/exlFixAsset.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].ASSETS_ID,'field2':rec[i].ASSETS_NAME,'field3':rec[i].ASSETCLASS_1,'field4':rec[i].ASSETCLASS_2,'field5':rec[i].BRAND,'field6':rec[i].TYPE_NAME,'field7':rec[i].NUM,'field8':rec[i].UNIT,'field9':rec[i].POSTINGDATE,'field10':rec[i].PURCHASEDATE,'field11':rec[i].PRICE,'field12':rec[i].WARRANTY,'field13':rec[i].SERVICELIFE,'field14':rec[i].RESPMAN,'field15':rec[i].COMPANY,'field16':rec[i].ATTRIBUTION,'field17':rec[i].LOCATION,'field18':rec[i].TYPENM,'field19':rec[i].USEDESC,'field20':rec[i].ASSETS_COMPANY}
                        bodysArr.push(deta)
                    }
                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '资产' + new Date().toLocaleString() + '.xlsx', 'xlsx');
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




        table.on('tool(user)', function(obj){
            var tdata = obj.data;
            if(obj.event === 'detai1'){
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.ASSETCLASS_1
                var val4=tdata.ASSETCLASS_2
                var val5=tdata.BRAND
                var val6=tdata.TYPE_NAME
                var val7=tdata.NUM

                var val8=tdata.UNIT
                var val9=tdata.POSTINGDATE
                var val10=tdata.PURCHASEDATE
                var val11=tdata.WARRANTY
                var val12=tdata.SERVICELIFE
                var val13=tdata.ATTRIBUTION
                var val14=tdata.RESPMAN
                var val15=tdata.PRICE
                var val16=tdata.LOCATION
                var val17=tdata.USEDESC
                var val18=tdata.INCOMING_TIME
                var val19=tdata.ID
                var val20=tdata.COMPANY;
                var val21=tdata.ASSETS_COMPANY;
                layer.open({
                    type: 2
                    ,title: '详情'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/FixAsset/infoFixAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+"&val19="+val19+"&val20="+val20+"&userName="+userName+"&val21="+val21
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
               // window.location.href="../view/FixAsset/infoFixAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+"&val19="+val19+"&val20="+val20+"&userName="+userName+"&val21="+val21;
            }
            if(obj.event === 'detai2'){
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.BRAND
                var val4=tdata.TYPE_NAME
                var val5=tdata.NUM
                var val6=tdata.UNIT
                var val7=tdata.LOCATION
                var val8=tdata.RESPMAN
                var val9=tdata.ID
                var val10=tdata.COMPANY;
                layer.open({
                    type: 2
                    ,title: '调拨'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/allocationAsset/addAllocationAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&userName="+userName+"&val10="+val10
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
                //window.location.href="../view/allocationAsset/addAllocationAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&userName="+userName+"&val10="+val10;
            }
            if(obj.event === 'detai3'){
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.ID
                layer.open({
                    type: 2
                    ,title: '维修'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/repairAsset/addRepairAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&userName="+userName
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
               // window.location.href="../view/repairAsset/addRepairAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&userName="+userName;
            }
            if(obj.event === 'detai4'){
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.ASSETCLASS_1
                var val4=tdata.ASSETCLASS_2
                var val5=tdata.BRAND
                var val6=tdata.TYPE_NAME
                var val7=tdata.ID
                layer.open({
                    type: 2
                    ,title: '报废'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/dumpAsset/addDumpAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&userName="+userName
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
                //window.location.href="../view/dumpAsset/addDumpAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&userName="+userName;
            }
            if(obj.event === 'detai5'){
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3=tdata.SERVICELIFE
                var val4=tdata.ID
                window.location.href="../view/depreciation/addDepreciation.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&userName="+userName;
            }
        });
        form.on("select(COMPANY)",function () {
            var optionstring = "";
            var cate1 = $("#pam15").val();
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
                                    $("#pam16").append("<option value='" + data[j].NAME+"' id='"+ data[j].NAME+"'>" + data[j].NAME + "</option>");

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
                            $("#pam15").append((new Option(obj[i].COMPNM )));
                            $("#pam19").append((new Option(obj[i].COMPNM )));
                            form.render();
                        }
                        $("#pam15").val(data[0].COMPNM);
                        $("#pam19").val(data[0].COMPNM);
                        $.ajax({
                            url:"../stockAsset/selBM.do",
                            contentType:"application/json",
                            type:"post",
                            dataType:"json",
                            beforeSend:"",
                            data:JSON.stringify(),
                            success: function(data) {
                                var cate1 = $("#pam15").val();
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
                                                $("#pam16").append("<option value='" + data[j].NAME+"' id='"+ data[j].NAME+"'>" + data[j].NAME + "</option>");

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