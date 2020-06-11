
function  infoinventoryAsset(){
    layui.use(['table', 'form', 'layer','laydate'], function(){
        var table = layui.table;
        var  $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        let param = new URLSearchParams(location.search);
        const val1 = param.get('val1');
        const val2 = param.get('val2');
        const val3 = param.get('val3');
        const val4 = param.get('val4');
        const val5 = param.get('userName');
        const val6 = param.get('val5');
        const val7 = param.get('val6');
        const val8 = param.get('val7');
        const val9 = param.get('val8');
        var form = layui.form;
        form.val('upFrom', {
            "pddh":val1,
            "pdfw":val2,
            "zsl":val3,
            "sjpdsj":(val6=="null"?"":val6),
            "sjjssj":(val7=="null"?"":val7),
            "bz":(val8=="null"?"":val8),
            "pcs":(val9=="null"?"":val9),
        });
        laydate.render({
            elem: '#date1' //指定元素
            ,showBottom: false
        });
        laydate.render({
            elem: '#date2' //指定元素
            ,showBottom: false
        });
        //方法级渲染
        table.render({
            elem: '#user'
            ,url: '../../inventoryAsset/selectAsset.do'
            ,type:'post'
            ,cols: [[
                {type:'numbers',title:'序号'}
                ,{field:'ASSETS_ID',align: 'center', title:'资产编号'}
                ,{field:'ASSETS_NAME', align: 'center',title:'资产名称'}
                ,{field:'BRAND',align: 'center',title:'品牌'}
                ,{field:'ATTRIBUTION', align: 'center',title:'部门'}
                ,{field:'NUM',align: 'center',title:'数量'}
                ,{field:'RESPMAN',align: 'center',title:'负责人'}
                ,{field:'LOCATION',align: 'center',title:'存放位置'}
                //,{field:'', templet:'#cklob1',width:180,align: 'center',title:'是否盘存 <button type="button" name="xxx"class="layui-btn layui-btn-primary" data-type="switch1" id="switch1" >全选</button>'}
                ,{type:'checkbox',align: 'center',width: 100,title:'选择' ,templet: function(data) {// 替换数据
                        if (data.LOG == 0){
                            return this.LAY_CHECKED=true;
                        }else {
                            return "";
                        }

                    }}

            ]]
            // , height: 'full-140'
            ,id: 'testReload'
            ,where:{
                uname: val5,
                param: val2,
                pdbh:val1,
            },done: function(res){
                //可以自行添加判断的条件是否选中
                //这句才是真正选中，通过设置关键字LAY_CHECKED为true选中，这里只对第一行选中
                for (var i = 0;i<res.data.length;i++){
                    if (res.data[i].LOG == '0'){
                        res.data[i]["LAY_CHECKED"]='true';
                        //下面三句是通过更改css来实现选中的效果
                        var index= res.data[i]['LAY_TABLE_INDEX'];
                        $('tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                        $('tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
                    }
                }


            }

        });
        var $ = layui.$, active = {
            reload: function(){
                var pam = val2;
                //var date = $('#bzDate').val();

                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        param: pam,
                        uname:val5,
                      //  bzDate:date
                    }
                });
            } ,
        };
        form.on('submit(reSubmit)', function (data) {
            var checkStatus = table.checkStatus('testReload')
                ,datas = checkStatus.data;
            var param = "";
            for (var i = 0; i < datas.length; i++) {

                param += datas[i].ASSETS_ID + ","+val1+","+val5+";";
            }
            var parameter = data.field
            var indexlod = layer.load(1);
            var parData = [{"name":"盘点单号","valu1":parameter.pddh},
                {"name":"指定部门","valu1":parameter.pdfw},
                {"name":"实际盘点时间","valu1":parameter.sjpdsj},
                {"name":"实际结束时间","valu1":parameter.sjjssj},
                {"name":"总数量","valu1":parameter.zsl},
                {"name":"盘存数","valu1":parameter.pcs}
            ];
            parameter["ID"]=val4;
            parameter["uname"]=val5;
            parameter["param"]=param;
            queryData('../../inventoryAsset/upInventoryAsset.do',parameter, function (red) {
                if (red.code == '0') {

                    var fieldName="";
                    var fieldAfter='';
                    $.each(parData, function () {

                        fieldName+=this.name+","
                        fieldAfter+=this.valu1+","
                    });
                    var strDescr = "字段名称：["+fieldName+"]/字段值：["+fieldAfter+"]"
                    var param={'operType':'新增','operPage':'实际盘点信息','operTable':'IT_ASSET_INVENTORYASSET','descr':strDescr,'createName':val5}
                    queryData('../../log/addlog.do', param, function (redt) {});

                    layer.alert(red.msg, {icon: 1});
                    window.location.href = "../inventoryAsset.jsp?user=" + val5;

                    //$(window).attr('location',"../inventoryAsset.jsp");
                }


            })


            layer.close(indexlod);
            return false;
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        //监听锁定操作
        function add(param) {$.ajax({
            url:"../../inventoryAsset/addInventoryAssetLog.do?param="+param+"&INVENTORYASSET="+val1+"&uname="+val5,
            contentType:"application/json",
            type:"post",
            dataType:"json",
            async:false,
            beforeSend:"",
            data:JSON.stringify(),
            success: 200,
        });}
        form.on('checkbox(switch)', function(obj){
            layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
            if (obj.elem.checked == 1){
                var numva = $("#pcs").val();
                var result = Number( numva)+Number(obj.value);
                $("#pcs").val(result);
                var param = this.id+","+this.checked+";";
            }else {
                var numva = $("#pcs").val();
                var result = Number( numva)-Number(obj.value);
                $("#pcs").val(result);
                var param = this.id+","+this.checked+";";
            }
            //$("#checkbox1").append("readonly:readonly");
        });
        var $ = layui.$, active = {
            cancel: function () {
                window.location.href = "../inventoryAsset.jsp?user=" + val5;

            },
            tijiao: function () {
                var checkStatus = $("input[name='zzz']");
                var data = "";
                $.each(checkStatus, function () {

                    data+=this.id+","+this.checked+";";
                });
                queryData('../../inventoryAsset/addInventoryAssetLog.do',{param:data,INVENTORYASSET:val1,uname:val5}, function (red) {
                    /*if (red.code == '0') {
                        layer.alert(red.msg, {icon: 1});
                        window.location.href = "../inventoryAsset.jsp?user=" + val5;

                        //$(window).attr('location',"../inventoryAsset.jsp");
                    }*/


                })
            },
            jilu: function () {
                $.ajax({
                    url:"../../inventoryAsset/selectInventoryAssetLog.do?INVENTORYASSET="+val1+"&uname="+val5,
                    contentType:"application/json",
                    type:"post",
                    dataType:"json",
                    async:false,
                    beforeSend:"",
                    data:JSON.stringify(),
                    success: function(obj) {
                        var data = obj.data;
                        var ca = table.cache.testReload;
                        var numva = $("#pcs").val();
                        var result = Number( numva);
                        for (var i = 0; i < ca.length;i++){
                            var ass = ca[i].ASSETS_ID;
                            for (var j = 0; j < data.length;j++){
                                if (ass == data[j].ASSETS_ID && data[j].LOG == 0&& $("#"+ass).is(':checked')==false){
                                    $("#"+ass).click();

                                    result=result+Number(ca[i].NUM);
                                    console.info("result",result);
                                }
                            }
                            $("#pcs").val(result);
                        }
                        layui.form.render();
                    }
                });

                    /*if (red.code == '0') {
                        layer.alert(red.msg, {icon: 1});
                        window.location.href = "../inventoryAsset.jsp?user=" + val5;

                        //$(window).attr('location',"../inventoryAsset.jsp");
                    }*/



            },
            switch1: function () {
                $('.layui-unselect').click();
                /*var checkStatus = $("input[name='zzz']");
                var data = "";
                $.each(checkStatus, function () {

                    data+=this.id+","+this.checked+";";
                });
                $.ajax({
                    url:"../../inventoryAsset/addInventoryAssetLog.do?param="+data+"&INVENTORYASSET="+val1+"&uname="+val5,
                    contentType:"application/json",
                    type:"post",
                    dataType:"json",
                    beforeSend:"",
                    data:JSON.stringify(),
                    success: function(data) {
                    }
                });*/
            },
        }
        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#cancel').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#switch1').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#tijiao').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#jilu').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
}

function CloseWin(){

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}