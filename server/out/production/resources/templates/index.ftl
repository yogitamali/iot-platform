<#include "common/header.ftl">
<body>
<#include "common/navbar.ftl"/>
<div class="container-fluid" id="container-main">
<#if user??>
    <#include "common/sidenavbar.ftl"/>
</#if>
<main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">
<#if user??>

<#else>

</#if>
</main>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="/static/js/app.js"></script>
</body>
</html>
