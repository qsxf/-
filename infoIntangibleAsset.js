function infoLicense() {
    layui.use(['table', 'form', 'layer', 'laydate'], function () {
        var table = layui.table;
        var $ = layui.jquery, form = layui.form;
        var laydate = layui.laydate;
        // var assetsIdLicense = table.checkStatus('ASSETS_ID');
        //方法级渲染
        // var checkStatus = table.checkStatus('intangibleAssetValue'), datas = checkStatus.data;
        // var assetsIdLicense = datas[0].ASSETS_ID;
        table.set();
        table.render({
            elem: '#user',
            url: '../../intangibleAsset/getLicenseInit.do',
            where: {param: 1},
            cols: [[
                {type: 'numbers', title: '序号'},
                {field: 'LICENSECOMPANY', width: 400, align: 'center', title: '公司名称'},
                {field: 'LICENSENUM', width: 400, align: 'center', title: 'License个数'}
            ]], height: 'full-140',
            id: 'licenseValue',
            page: true

        });

        var $ = layui.$, active = {
            reload: function () {
                var seKey = $('#selectKey');
                //执行重载
                table.reload('licenseValue', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    },
                    where: {
                        param: seKey,
                    }
                });
            },
            addbut: function () {
                var checkStatus = table.checkStatus('testReload')
                        , data = checkStatus.data;

                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '添加无形资产'
                    , area: ['600px', '540px']
                    , resize: false
                    // ,offset: [10 ,200 ]
                    , maxmin: true
                    , btn: ['保存', '取消']
                    , content: '../view/collect/addAndEditCollection.html'
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero, index) {

                    }

                    , yes: function (index, layero) {

                    }
                })
            },

            edit: function () {
                var checkStatus = table.checkStatus('testReload')
                        , data = checkStatus.data;

                layer.open({
                    type: 2 //此处以iframe举例
                    , title: '修改无形资产'
                    , area: ['600px', '540px']
                    , resize: false
                    , maxmin: true
                    // ,offset: [10 ,200 ]
                    , btn: ['保存', '取消']
                    , content: '../view/collect/addAndEditCollection.html'
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero, index) {

                    }, yes: function (index, layero) {
                    }
                });
            },

            delt: function () {
                var checkStatus = table.checkStatus('testReload')
                        , data = checkStatus.data;
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

                    jonname += data[i].NAME;
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
        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#but1').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#but2').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#but3').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('#but4').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('#reload').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        table.on('tool(user)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detai2') {
                var that = this;
                window.location.href = "../view/intangibleAsset/editIntangibleAsset.html";
            }
            if (obj.event === 'detail') {
                // var othis = $(this), method = othis.data('method');
                // active[method] ? active[method].call(this, othis) : '';
                // layer.msg('ID：'+ data.id + ' 的查看操作');

                var that = this;
                //多窗口模式，层叠置顶
                window.location.href = "../view/intangibleAsset/infoIntangibleAsset.html";
                layer.open({
                    type: 1 //此处以iframe举例
                    , title: '详情'
                    , area: ['760px', '500px']
                    , maxmin: true
                    , offset: [2, 200]
                    , content: '<div>内容</div>'
                    , btn: ['关闭'] //只是为了演示

                    , btn2: function () {
                        layer.closeAll();
                    }

                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        layer.setTop(layero); //重点2
                    }
                });
            }
            if (obj.event === 'detai3') {
                var that = this;
                //多窗口模式，层叠置顶
                layer.open({
                    type: 1 //此处以iframe举例
                    , title: 'License分配'
                    , area: ['760px', '500px']
                    , maxmin: true
                    , offset: [2, 200]
                    , content: '<div style="margin-left: 40px;margin-top: 30px">' +
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

                            ' layui.use(\'form\', function(){\n' +
                            '        var form = layui.form; \n' +
                            '        form.render();\n' +
                            ' }); \n' +
                            '</script>'
                    , btn: ['关闭'] //只是为了演示

                    , btn2: function () {
                        layer.closeAll();
                    }

                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        layer.setTop(layero); //重点2
                    }
                });
            }
        });

    });

}

//关闭页面
function CloseWin() {

    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭

}