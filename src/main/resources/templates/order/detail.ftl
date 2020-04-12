<html>
<#include "../common/herder.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                ${orderDTO.orderId}
                            </td>
                            <td>
                                ${orderDTO.orderAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-condensed table-bordered">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                            <tr>
                                <td>
                                    ${orderDetail.productId}
                                </td>
                                <td>
                                    ${orderDetail.productName}
                                </td>
                                <td>
                                    ${orderDetail.productPrice}
                                </td>
                                <td>
                                    ${orderDetail.productQuantity}
                                </td>
                                <td>
                                    ${orderDetail.productPrice*orderDetail.productQuantity}
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <#if orderDTO.orderStatusEnum.msg=='新订单'>
                        <button type="button" onclick="location.href='/sell/seller/order/finish?orderId=${orderDTO.orderId}'" class="btn btn-default btn-success">完结订单</button>
                        <button type="button" onclick="location.href='/sell/seller/order/cancel?orderId=${orderDTO.orderId}'" class="btn btn-default btn-danger">取消订单</button>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>