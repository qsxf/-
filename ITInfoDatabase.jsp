<%--
  Created by IntelliJ IDEA.
  User: duyuuuuy
  Date: 2019/12/17
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>IT信息数据库</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css">
    <link id="layuicss-laydate" rel="stylesheet" href="../layui/css/modules/laydate/default/laydate.css" media="all">
    <link id="layuicss-layer" rel="stylesheet" href="../layui/css/modules/layer/default/layer.css?v=3.1.1" media="all">
    <style>
        body{
            /*margin-top: 15px;*/
            margin-left: 10px;
            margin-right: 10px;
            /*background-color: #f5f6fa !important;*/
            border-color: #e6e6e6;
        }
        .layui-table-view .layui-form-checkbox[lay-skin=primary] i {
            width: 18px;
            margin-top: 4px;
            height: 18px;
        }
        .layui-table-view .layui-form-radio {
            line-height: 1;
            padding: 5px;
        }

        .layui-btn {
            display: inline-block;
            height: 32px;
            line-height: 32px;
            border-radius: 6px;
        }

    </style>
</head>
<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title">
        <li class="layui-this">机房信息</li>
        <li>机柜信息</li>
        <li>配线架</li>
        <li>光纤、网线信息</li>
        <li>面板信息</li>
        <li>网络设备信息</li>
    </ul>
    <div class="layui-tab-content" style="height: 100px;">
        <div class="layui-tab-item layui-show">
            <div style="margin-top: 10px;" class="layui-form">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字：</label>
                    <div class="layui-input-inline">
                        <input type="text" id="param1"  autocomplete="off" class="layui-input" style="width: 211px" placeholder="请输入名称、所在楼宇">
                    </div>
                </div>

                <button class="layui-btn  layui-btn-normal"  data-type="jfreload"  style=" margin-left: 20px" id="jfreload">
                    <i class="layui-icon layui-icon-search "></i>查询
                </button>

                <div class="layui-inline" style="float: right">

                    <button class="layui-btn layui-btn-normal" data-type='jfaddbut' id="jfbut1">
                        <i class="layui-icon layui-icon-add-1"></i> 添加</button>
                    <button class="layui-btn  " data-type="jfedit" id="jfbut2">
                        <i class="layui-icon layui-icon-edit"></i> 修改</button>
                    <button class="layui-btn layui-btn-danger" data-type="jfdelt" id="jfbut3">
                        <i class="layui-icon"></i> 删除</button>
                    <button class="layui-btn  layui-btn-warm" data-type="jfexportbut" id="jfbut4">
                        <i class="layui-icon layui-icon-download-circle"></i>导出</button>

                </div>
            </div>

            <div>
                <table class="layui-hide" id="table1"  lay-filter="table1"></table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div style="margin-top: 10px;" class="layui-form">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字：</label>
                    <div class="layui-input-inline">
                        <input type="text" id="param2"  autocomplete="off" class="layui-input" style="width: 211px" placeholder="请输入名称">
                    </div>
                </div>

                <button class="layui-btn  layui-btn-normal"  data-type="jgreload"  style=" margin-left: 20px" id="jgreload">
                    <i class="layui-icon layui-icon-search "></i>查询
                </button>

                <div class="layui-inline" style="float: right">

                    <button class="layui-btn layui-btn-normal" data-type='jgaddbut' id="jgbut1">
                        <i class="layui-icon layui-icon-add-1"></i> 添加</button>
                    <button class="layui-btn  " data-type="jgedit" id="jgbut2">
                        <i class="layui-icon layui-icon-edit"></i> 修改</button>
                    <button class="layui-btn layui-btn-danger" data-type="jgdelt" id="jgbut3">
                        <i class="layui-icon"></i> 删除</button>
                    <button class="layui-btn  layui-btn-warm" data-type="jgexportbut" id="jgbut4">
                        <i class="layui-icon layui-icon-download-circle"></i>导出</button>
                </div>
            </div>
            <div>
                <table class="layui-hide" id="table2"  lay-filter="table2"></table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div style="margin-top: 10px;" class="layui-form">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字：</label>
                    <div class="layui-input-inline">
                        <input type="text" id="param3"  autocomplete="off" class="layui-input" style="width: 211px" placeholder="请输入名称">
                    </div>
                </div>

                <button class="layui-btn  layui-btn-normal"  data-type="pxjreload"  style=" margin-left: 20px" id="pxjreload">
                    <i class="layui-icon layui-icon-search "></i>查询
                </button>

                <div class="layui-inline" style="float: right">

                    <button class="layui-btn layui-btn-normal" data-type='pxjaddbut' id="pxjbut1">
                        <i class="layui-icon layui-icon-add-1"></i> 添加</button>
                    <button class="layui-btn  " data-type="pxjedit" id="pxjbut2">
                        <i class="layui-icon layui-icon-edit"></i> 修改</button>
                    <button class="layui-btn layui-btn-danger" data-type="pxjdelt" id="pxjbut3">
                        <i class="layui-icon"></i> 删除</button>
                    <button class="layui-btn  layui-btn-warm" data-type="pxjexportbut" id="pxjbut4">
                        <i class="layui-icon layui-icon-download-circle"></i>导出</button>

                </div>
            </div>
            <div>
                <table class="layui-hide" id="table3"  lay-filter="table3"></table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div style="margin-top: 10px;" class="layui-form">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字：</label>
                    <div class="layui-input-inline">
                        <input type="text" id="param4"  autocomplete="off" class="layui-input" style="width: 211px" placeholder="请输入名称">
                    </div>
                </div>

                <button class="layui-btn  layui-btn-normal"  data-type="reload4"  style=" margin-left: 20px" id="butreload4">
                    <i class="layui-icon layui-icon-search "></i>查询
                </button>

                <div class="layui-inline" style="float: right">

                    <button class="layui-btn layui-btn-normal" data-type='gqaddbut' id="gqbut1">
                        <i class="layui-icon layui-icon-add-1"></i> 添加</button>
                    <button class="layui-btn  " data-type="gqedit" id="gqbut2">
                        <i class="layui-icon layui-icon-edit"></i> 修改</button>
                    <button class="layui-btn layui-btn-danger" data-type="gqdelt" id="gqbut3">
                        <i class="layui-icon"></i> 删除</button>
                    <button class="layui-btn  layui-btn-warm" data-type="gqexportbut" id="gqbut4">
                        <i class="layui-icon layui-icon-download-circle"></i>导出</button>

                </div>
            </div>
            <div>
                <table class="layui-hide" id="table4"  lay-filter="table4"></table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div style="margin-top: 10px;" class="layui-form">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="param5"  id="param5" autocomplete="off" class="layui-input" style="width: 211px" placeholder="请输入编号">
                    </div>
                </div>

                <button class="layui-btn  layui-btn-normal"  data-type="reload5"  style=" margin-left: 20px" id="reload5">
                    <i class="layui-icon layui-icon-search "></i>查询
                </button>

                <div class="layui-inline" style="float: right">

                    <button class="layui-btn layui-btn-normal" data-type='mbaddbut' id="mbbut1">
                        <i class="layui-icon layui-icon-add-1"></i> 添加</button>
                    <button class="layui-btn  " data-type="mbedit" id="mbbut2">
                        <i class="layui-icon layui-icon-edit"></i> 修改</button>
                    <button class="layui-btn layui-btn-danger" data-type="mbdelt" id="mbbut3">
                        <i class="layui-icon"></i> 删除</button>
                    <button class="layui-btn  layui-btn-warm" data-type="mbexportbut" id="mbbut4">
                        <i class="layui-icon layui-icon-download-circle"></i>导出</button>

                </div>
            </div>
            <div>
                <table class="layui-hide" id="table5"  lay-filter="table5"></table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div style="margin-top: 10px;" class="layui-form">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字：</label>
                    <div class="layui-input-inline">
                        <input type="text" id="param6"  autocomplete="off" class="layui-input" style="width: 211px" placeholder="请输入名称、序列号">
                    </div>
                </div>

                <button class="layui-btn  layui-btn-normal"  data-type="reload6"  style=" margin-left: 20px" id="reload6">
                    <i class="layui-icon layui-icon-search "></i>查询
                </button>

                <div class="layui-inline" style="float: right">

                    <button class="layui-btn layui-btn-normal" data-type='wladdbut' id="wlbut1">
                        <i class="layui-icon layui-icon-add-1"></i> 添加</button>
                    <button class="layui-btn  " data-type="jfedit" id="wlbut2">
                        <i class="layui-icon layui-icon-edit"></i> 修改</button>
                    <button class="layui-btn layui-btn-danger" data-type="jfdelt" id="wlbut3">
                        <i class="layui-icon"></i> 删除</button>
                    <button class="layui-btn  layui-btn-warm" data-type="jfexportbut" id="wlbut4">
                        <i class="layui-icon layui-icon-download-circle"></i>导出</button>

                </div>
            </div>
            <div>
                <table class="layui-hide" id="table6"  lay-filter="table6"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../layui/lay/modules/table.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../layui/lay/modules/layer.js"></script>
<script src="../js/common.js"></script>
<script src="../js/ITInfoDatabase/engineRoomInfo.js"></script>
<script src="../js/ITInfoDatabase/cabinetInfo.js"></script>
<script src="../js/ITInfoDatabase/MDFInfo.js"></script>
<script src="../js/ITInfoDatabase/networkCableInfo.js"></script>
<script src="../js/ITInfoDatabase/panelInfo.js"></script>
<script src="../js/ITInfoDatabase/netEquipmentInfo.js"></script>
<script src="../layui/lay/modules/laydate.js"></script>
<script>

    layui.use(['element'], function(){
        var element = layui.element;

    });
</script>
<script>
    var userName="<%= request.getParameter("user") %>"
    engineRoomInfo(userName);
    cabinetInfo(userName);
    MDFInfo(userName);
    networkCableInfo(userName);
    panelInfo(userName);
    netEquipmentInfo(userName)
</script>
</html>
