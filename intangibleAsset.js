function intangibleAsset(userName) {
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
        laydate.render({
            elem: '#date16'
            ,range: true
        });
        laydate.render({
            elem: '#date17'
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
            ,url: '../intangibleAsset/selectIntangibleAsset.do'
            ,cols: [[
                {type: 'checkbox',align: 'center', width: 70, title: '选择'},
                {type:'numbers',align: 'center',title:'序号'},
                {field: 'ASSETS_ID', width: 100, align: 'center', title: '资产编号'},
                {field: 'ASSETS_NAME', width: 180, align: 'center', title: '资产名称'},
                {field: 'BRAND', width: 200, align: 'center', title: '品牌'},
                {field: 'TYPE_NAME', width: 180, align: 'center', title: '规格型号',
                    templet : function(data) {// 替换数据
                        if (data.TYPE_NAME != null){
                            return data.TYPE_NAME;
                        }else {
                            return "";
                        }

                    }},
                {field: 'NUM', width: 100, align: 'center', title: '数量'},
                {field: 'UNIT', width: 100, align: 'center', title: '单位'},
                {field: 'LICENSELIMIT', width: 150, align: 'center', title: 'License期限',
                    templet : function(data) {// 替换数据
                        if (data.LICENSELIMIT != null){
                            return data.LICENSELIMIT;
                        }else {
                            return "";
                        }

                    }},
                {field: 'LICENSEAMT', width: 150, align: 'center', title: 'License个数',
                    templet : function(data) {// 替换数据
                        if (data.LICENSEAMT != null){
                            return data.LICENSEAMT;
                        }else {
                            return "";
                        }

                    }},
                {field: 'LICENSENUM', width: 150, align: 'center', title: '未分配License个数',
                    templet : function(data) {// 替换数据
                        if (data.LICENSENUM != null && data.LICENSEAMT != null){
                            return data.LICENSEAMT-data.LICENSENUM;
                        }else if (data.LICENSEAMT == null){
                            return "";
                        }else if (data.LICENSEAMT != null && data.LICENSENUM == null){
                            return data.LICENSEAMT;
                        }

                    }
                },
                {field: 'BUY_DEPARTMENT', width: 100, align: 'center', title: '供应商',
                    templet : function(data) {// 替换数据
                        if (data.BUY_DEPARTMENT != null){
                            return data.BUY_DEPARTMENT;
                        }else {
                            return "";
                        }

                    }},
                {field: 'LOCATION', width: 150, align: 'center', title: '存放地点'},
                {field: 'TYPENM', width: 120, align: 'center', title: '状态'},
                {field: 'USE_DEPARTMENT', width: 150, align: 'center', title: '归属部门'},
                {field: 'ATTRIBUTION', width: 150, align: 'center', title: '归属公司'},
                {field: 'RESPMAN', width: 150, align: 'center', title: '责任人'},
                {field: 'PRICE', width: 150, align: 'center', title: '购买价格（元）',
                    templet : function(data) {// 替换数据
                        if (data.PRICE != null){
                            return data.PRICE;
                        }else {
                            return "";
                        }

                    }},
                {field: 'POSTINGDATE', width: 150, align: 'center', title: '入账日期'},
                {field: 'PURCHASEDATE', width: 150, align: 'center', title: '购买日期'},
                // {field: '', width: 150, align: 'center', title: '备注'},
                {field: '', templet: '#ckjob', width: 300, align: 'center', title: '操作'}
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
                    var pam1=$("#pam1").val()
                    var pam2=$("#pam2").val()
                    var pam3=$("#pam3").val()
                    var pam4=$("#pam4").val()
                    var pam5=$("#pam5").val()
                    var pam6=$("#date").val()
                    var pam7p=$("#pam7p").val()
                    var pam7l=$("#pam7l").val()
                    var pam8p=$("#pam8p").val()
                    var pam8l=$("#pam8l").val()
                    var pam9=$("#pam9").val()
                    var pam10=$("#pam10").val()
                    var pam11=$("#pam11").val()
                    var pam12=$("#pam12").val()
                    var pam13=$("#pam13").val()
                    var pam14=$("#pam14").val()
                    var pam15p=$("#pam15p").val()
                    var pam15l=$("#pam15l").val()
                    var pam16=$("#date16").val()
                    var pam17=$("#date17").val()
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
                            param7p: pam7p,
                            param7l: pam7l,
                            param8p: pam8p,
                            param8l: pam8l,
                            param9: pam9,
                            param10: pam10,
                            param11: pam11,
                            param12: pam12,
                            param13: pam13,
                            param14: pam14,
                            param15p: pam15p,
                            param15l: pam15l,
                            param16: pam16,
                            param17: pam17
                        }
                    });
                    layer.close(index);
                    return false;
                }
            });



            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });*/
        // 查询按钮
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
                var pam7p="";
                var pam7l="";
                var pam8p="";
                var pam8l="";
                var pam9="";
                var pam10="";
                var pam11="";
                var pam12="";
                var pam13="";
                var pam14="";
                var pam15p="";
                var pam15l="";
                var pam16="";
                var pam17="";
                if (isChecked){
                    pam="";
                    date="";
                    pam1=$("#pam1").val()
                    pam2=$("#pam2").val()
                    pam3=$("#pam3").val()
                    pam4=$("#pam4").val()
                    pam5=$("#pam5").val()
                    pam6=$("#date").val()
                    pam7p=$("#pam7p").val()
                    pam7l=$("#pam7l").val()
                    pam8p=$("#pam8p").val()
                    pam8l=$("#pam8l").val()
                    pam9=$("#pam9").val()
                    pam10=$("#pam10").val()
                    pam11=$("#pam11").val()
                    pam12=$("#pam12").val()
                    pam13=$("#pam13").val()+""
                    pam14=$("#pam14").val()
                    pam15p=$("#pam15p").val()
                    pam15l=$("#pam15l").val()
                    pam16=$("#date16").val()
                    pam17=$("#date17").val()
                }
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: pam,
                        uname: userName,
                        bzDate:date,
                        param1: pam1,
                        param2: pam2,
                        param3: pam3,
                        param4: pam4,
                        param5: pam5,
                        param6: pam6,
                        param7p: pam7p,
                        param7l: pam7l,
                        param8p: pam8p,
                        param8l: pam8l,
                        param9: pam9,
                        param10: pam10,
                        param11: pam11,
                        param12: pam12,
                        param13: pam13,
                        param14: pam14,
                        param15p: pam15p,
                        param15l: pam15l,
                        param16: pam16,
                        param17: pam17
                    }
                });
            } ,
            addbut:function () {
                window.location.href="../view/intangibleAsset/addIntangibleAsset.jsp?userName="+userName;

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

               // var id=data[0].ID

                layer.confirm('是否删除选择的数据！', {icon: 3, title: '提示'}, function (index) {
                    var indexlod = layer.load(1);

                    queryData('../intangibleAsset/delIntangibleAsset.do', {ID: jobid}, function (rec) {
                        if (rec.code="0"){
                            var fieldName="";
                            var fieldAfter='';
                            $.each(parData, function () {

                                fieldName+=this.name+","
                                fieldAfter+=this.valu1+","
                            });
                            var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                            var param={'operType':'删除','operPage':'资产报废信息','operTable':'IT_ASSET_DUMPASSET','descr':jobid,'createName':userName}
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

                var bodysArr= [{'field1': "资产编号", 'field2': "资产名称",'field3': "品牌",'field4': "数量",'field5': "License期限",'field6': "License数量",'field7': "供应商",'field8': "使用部门",'field9': "购买价格",'field10': "归属公司",'field11': "责任人",'field12': "入账日期",'field13': "购买日期",'field14': "创建时间",'field15': "创建人",'field16': "修改时间",'field17': "修改人"}]
                var vparam = $('#param');
                var vtype = $('#type');
                var bzDate=$('#bzDate');
                var redpar={ param: vparam.val(),
                    type:vtype.val(),
                    uname: userName,
                    bzDate:bzDate.val()}

                queryData('../intangibleAsset/exlIntangibleAsset.do', redpar, function (rec) {
                    for (var i=0;i<rec.length;i++){
                        var  deta={'field1':rec[i].ASSETS_ID,'field2':rec[i].ASSETS_NAME,'field3':rec[i].BRAND,'field4':rec[i].NUM,'field5':rec[i].LICENSELIMIT,'field6':rec[i].LICENSEAMT,'field7':rec[i].BUY_DEPARTMENT,'field8':rec[i].USE_DEPARTMENT,'field9':rec[i].PRICE,'field10':rec[i].ATTRIBUTION,'field11':rec[i].RESPMAN,'field12':rec[i].POSTINGDATE,'field13':rec[i].PURCHASEDATE,'field14':rec[i].CREATE_DATE,'field15':rec[i].CREATE_NAME,'field16':rec[i].UPDATE_DATE,'field17':rec[i].UPDATE_NAME}
                        bodysArr.push(deta)

                    }


                    //导出excel
                    excel.exportExcel({
                        sheet1: bodysArr
                    }, '无形资产' + new Date().toLocaleString() + '.xlsx', 'xlsx');
                })



            },



        };
        // 导出
        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#bzDate').on('click', function(){
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
        // 编辑页面
        table.on('tool(user)', function (obj) {
            var tdata = obj.data;
            if (obj.event === 'detai2') {
                var val1=tdata.ID
                var val2=tdata.ASSETS_ID
                var val3=tdata.BRAND
                var val4=tdata.NUM
                var val5=tdata.LICENSELIMIT
                var val6=tdata.LICENSEAMT
                var val7=tdata.BUY_DEPARTMENT
                var val8=tdata.USE_DEPARTMENT
                var val9=tdata.ATTRIBUTION
                var val10=tdata.RESPMAN
                var val11=tdata.PRICE
                var val12=tdata.POSTINGDATE
                var val13=tdata.PURCHASEDATE
                var val14=tdata.ASSETS_NAME
                var val15=tdata.TYPE_NAME
                var val16=tdata.UNIT
                var val17=tdata.LOCATION
                var val18=tdata.STATUS
                layer.open({
                    type: 2
                    ,title: '修改'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/intangibleAsset/editIntangibleAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+'&userName='+userName
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
               // window.location.href = "../view/intangibleAsset/editIntangibleAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+'&userName='+userName;

            }
            // 详情
            if (obj.event === 'detai1') {
                var val1=tdata.ID
                var val2=tdata.ASSETS_ID
                var val3=tdata.BRAND
                var val4=tdata.NUM
                var val5=tdata.LICENSELIMIT
                var val6=tdata.LICENSEAMT
                var val7=tdata.BUY_DEPARTMENT
                var val8=tdata.USE_DEPARTMENT
                var val9=tdata.ATTRIBUTION
                var val10=tdata.RESPMAN
                var val11=tdata.PRICE
                var val12=tdata.POSTINGDATE
                var val13=tdata.PURCHASEDATE
                var val14=tdata.ASSETS_NAME
                var val15=tdata.TYPE_NAME
                var val16=tdata.UNIT
                var val17=tdata.LOCATION
                var val18=tdata.TYPENM
                layer.open({
                    type: 2
                    ,title: '详情'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/intangibleAsset/infoIntangibleAsset.html?val1='+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+'&userName='+userName
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
               // window.location.href = "../view/intangibleAsset/infoIntangibleAsset.html?val1="+val1+"&val2="+val2+"&val3="+val3+"&val4="+val4+"&val5="+val5+"&val6="+val6+"&val7="+val7+"&val8="+val8+"&val9="+val9+"&val10="+val10+"&val11="+val11+"&val12="+val12+"&val13="+val13+"&val14="+val14+"&val15="+val15+"&val16="+val16+"&val17="+val17+"&val18="+val18+'&userName='+userName;

            }

            // License 分配
            if (obj.event === 'detai3') {
                var val1=tdata.ASSETS_ID
                var val2=tdata.ASSETS_NAME
                var val3 = 0;
                if (tdata.LICENSENUM != null){
                    val3 = tdata.LICENSEAMT-tdata.LICENSENUM;
                }else {
                    val3 = tdata.LICENSEAMT;
                }
                layer.open({
                    type: 2
                    ,title: 'License分配'
                    ,area: ['860px', '500px']
                    ,resize :false
                    , maxmin:false
                    ,content:'../view/License.jsp?val1='+val1+"&val2="+val2+"&val3="+val3+"&val555="+userName
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
               // window.location.href = "../view/License.jsp?val1="+val1+"&val2="+val2+"&val3="+val3+"&val555="+userName;


            }
        });
        form.on("select(COMPANY)",function () {
            var optionstring = "";
            var cate1 = $("#pam12").val();
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
                                    $("#pam13").append("<option value='" + data[j].NAME+"' id='"+ data[j].NAME+"'>" + data[j].NAME + "</option>");

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
                            $("#pam12").append((new Option(obj[i].COMPNM )));
                            //$("#pam19").append((new Option(obj[i].COMPNM )));
                            form.render();
                        }
                        $("#pam12").val(data[0].COMPNM);
                        // $("#pam19").val(data[0].COMPNM);
                        $.ajax({
                            url:"../stockAsset/selBM.do",
                            contentType:"application/json",
                            type:"post",
                            dataType:"json",
                            beforeSend:"",
                            data:JSON.stringify(),
                            success: function(data) {
                                var cate1 = $("#pam12").val();
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
                                                $("#pam13").append("<option value='" + data[j].NAME+"' id='"+ data[j].NAME+"'>" + data[j].NAME + "</option>");

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

//关闭页面
function CloseWin() {

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}