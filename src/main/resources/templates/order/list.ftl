<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
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
</body>
</html>
