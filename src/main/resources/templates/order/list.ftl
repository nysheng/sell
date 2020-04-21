<html>
    <#include "../common/herder.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                手机号
                            </th>
                            <th>
                                地址
                            </th>
                            <th>
                                金额
                            </th>
                            <th>
                                订单状态
                            </th>
                            <th>
                                支付方式
                            </th>
                            <th>
                                支付状态
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th colspan="2">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>
                                    ${orderDTO.orderId}
                                </td>
                                <td>
                                    ${orderDTO.buyerName}
                                </td>
                                <td>
                                    ${orderDTO.buyerPhone}
                                </td>
                                <td>
                                    ${orderDTO.buyerAddress}
                                </td>
                                <td>
                                    ${orderDTO.orderAmount}
                                </td>
                                <td>
                                    ${orderDTO.orderStatusEnum.msg}
                                </td>
                                <td>
                                    微信
                                </td>
                                <td>
                                    ${orderDTO.payStatusEnum.msg}
                                </td>
                                <td>
                                    ${orderDTO.createTime}
                                </td>
                                <td>
                                    <button onclick="location.href='/sell/seller/order/detail?orderId=${orderDTO.orderId}'" type="button" class="btn btn-default btn-link">详情</button>
                                </td>
                                <td>
                                    <#if orderDTO.orderStatusEnum.msg=="新订单">
                                        <button type="button" onclick="location.href='/sell/seller/order/cancel?orderId=${orderDTO.orderId}'" class="btn btn-default btn-link">取消</button>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <#--            分页条实现-->
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${currentSize}">上一页</a></li>
                        </#if>
                        <#if orderDTOPage.totalPages gt 3>
                            <#if currentPage==1>
                                <li class="disabled"> <a href="/sell/seller/order/list?page=1&size=${currentSize}">1</a> </li>
                            <#else>
                                <li> <a href="/sell/seller/order/list?page=1&size=${currentSize}">1</a> </li>
                            </#if>
                            <li class="disabled"> <a href="#">.</a> </li>
                            <#if currentPage!=1 && currentPage!=orderDTOPage.totalPages>
                                <li class="disabled"> <a href="/sell/seller/order/list?page=${currentPage}&size=${currentSize}">${currentPage}</a> </li>
                            <#else>
                                <li class="disabled"> <a href="#">.</a> </li>
                            </#if>
                            <li class="disabled"> <a href="#">.</a> </li>
                            <#if currentPage==orderDTOPage.totalPages>
                                <li class="disabled"> <a href="/sell/seller/order/list?page=${orderDTOPage.totalPages}&size=${currentSize}">${orderDTOPage.totalPages}</a> </li>
                            <#else>
                                <li> <a href="/sell/seller/order/list?page=${orderDTOPage.totalPages}&size=${currentSize}">${orderDTOPage.totalPages}</a> </li>
                            </#if>
                        <#else>
                            <#list 1..orderDTOPage.totalPages as index>
                                <#if currentPage==index>
                                    <li class="disabled"> <a href="/sell/seller/order/list?page=${index}&size=${currentSize}">${index}</a> </li>
                                <#else>
                                    <li> <a href="/sell/seller/order/list?page=${index}&size=${currentSize}">${index}</a> </li>
                                </#if>
                            </#list>
                        </#if>
                        <#if currentPage gte orderDTOPage.totalPages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${currentSize}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提示消息
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单...
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause();"  type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>
            </div>
        </div>
    </div>
</div>
<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>
</body>
<script>
    var websocket =null;
    if('WebSocket' in window){
        websocket=new WebSocket("ws://redbtree.natapp4.cc/sell/websocket");
    }else{
        alert("该浏览器不支持websocket");
    }
    websocket.onopen=function (event) {
        console.log("建立连接");
    }
    websocket.onclose=function (event) {
        console.log("断开连接");
    }
    websocket.onmessage=function (event) {
        console.log("消息来了："+event.data);
        //弹窗提醒，播放音乐
        $('#myModal').modal('show');
        document.getElementById('notice').play();
    }
    websocket.onerror=function (event) {
        alert("websocket发生异常");
    }
    window.onbeforeunload=function () {
        websocket.close();
    }
</script>
</html>
