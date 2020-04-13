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
                                类目id
                            </th>
                            <th>
                                编号
                            </th>
                            <th>
                                名称
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>
                                修改时间
                            </th>
                            <th>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productCategoryList as productCategory>
                            <tr>
                                <td>
                                    ${productCategory.categoryId}
                                </td>
                                <td>
                                    ${productCategory.categoryType}
                                </td>
                                <td>
                                    ${productCategory.categoryName}
                                </td>
                                <td>
                                    ${productCategory.createTime}
                                </td>
                                <td>
                                    ${productCategory.updateTime}
                                </td>
                                <td>
                                    <button onclick="location.href='/sell/seller/category/index?categoryId=${productCategory.categoryId}'"  type="button" class="btn btn-default btn-link">修改</button>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>