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
                                商品id
                            </th>
                            <th>
                                名称
                            </th>
                            <th>
                                图片
                            </th>
                            <th>
                                单价
                            </th>
                            <th>
                                库存
                            </th>
                            <th>
                                描述
                            </th>
                            <th>
                                类目
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>
                                修改时间
                            </th>
                            <th colspan="2">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>
                                    ${productInfo.productId}
                                </td>
                                <td>
                                    ${productInfo.productName}
                                </td>
                                <td>
                                    <img width="50" height="50" src="${productInfo.productIcon}">
                                </td>
                                <td>
                                    ${productInfo.productPrice}
                                </td>
                                <td>
                                    ${productInfo.productStock}
                                </td>
                                <td>
                                    ${productInfo.productDescription}
                                </td>
                                <td>
                                    ${productInfo.categoryType}
                                </td>
                                <td>
                                    ${productInfo.createTime}
                                </td>
                                <td>
                                    ${productInfo.updateTime}
                                </td>
                                <td>
                                    <button  type="button" class="btn btn-default btn-link">修改</button>
                                </td>
                                <td>
                                    <#if productInfo.productStatusEnum.message=="在架">
                                        <button onclick="location.href='/sell/seller/product/off_sale?productId=${productInfo.productId}'" type="button" class="btn btn-default btn-link">下架</button>
                                    <#else>
                                        <button onclick="location.href='/sell/seller/product/on_sale?productId=${productInfo.productId}'" type="button" class="btn btn-default btn-link">上架</button>
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
                            <li><a href="/sell/seller/product/list?page=${currentPage-1}&size=${currentSize}">上一页</a></li>
                        </#if>
                        <#if productInfoPage.totalPages gt 3>
                            <#if currentPage==1>
                                <li class="disabled"> <a href="/sell/seller/product/list?page=1&size=${currentSize}">1</a> </li>
                            <#else>
                                <li> <a href="/sell/seller/product/list?page=1&size=${currentSize}">1</a> </li>
                            </#if>
                            <li class="disabled"> <a href="#">.</a> </li>
                            <#if currentPage!=1 && currentPage!=productInfoPage.totalPages>
                                <li class="disabled"> <a href="/sell/seller/product/list?page=${currentPage}&size=${currentSize}">${currentPage}</a> </li>
                            <#else>
                                <li class="disabled"> <a href="#">.</a> </li>
                            </#if>
                            <li class="disabled"> <a href="#">.</a> </li>
                            <#if currentPage==productInfoPage.totalPages>
                                <li class="disabled"> <a href="/sell/seller/product/list?page=${productInfoPage.totalPages}&size=${currentSize}">${productInfoPage.totalPages}</a> </li>
                            <#else>
                                <li> <a href="/sell/seller/order/product?page=${productInfoPage.totalPages}&size=${currentSize}">${productInfoPage.totalPages}</a> </li>
                            </#if>
                        <#else>
                            <#list 1..productInfoPage.totalPages as index>
                                <#if currentPage==index>
                                    <li class="disabled"> <a href="/sell/seller/product/list?page=${index}&size=${currentSize}">${index}</a> </li>
                                <#else>
                                    <li> <a href="/sell/seller/product/list?page=${index}&size=${currentSize}">${index}</a> </li>
                                </#if>
                            </#list>
                        </#if>
                        <#if currentPage gte productInfoPage.totalPages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/product/list?page=${currentPage+1}&size=${currentSize}">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
