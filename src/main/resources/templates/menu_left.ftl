<#-- Freemarker template -->
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-${color_style} sidebar sidebar-dark accordion <#if toggleMenu == '1'>toggled</#if>" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/workplace?l=1&w=1" title="Переход в основной рабочий стол.">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-globe"></i>
        </div>
        <div class="sidebar-brand-text mx-3 overflow-hidden"><#if currentUser??>${currentUser.getFI()!""}</#if></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">
    <li class="nav-item">
        <div class="nav-link text-center" style="padding: 0 !important; color: white;">
            <#if currentEnterprise??>
                <span>${(currentEnterprise.getEnterpriseNameShort())!""}</span>
            <#else>
                <span><#if currentUser??>${(currentUser.getFI())!""}</#if></span>
            </#if>
        </div>
    </li>
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseWorkplace'>active</#if>">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseWorkplace" aria-expanded="true" aria-controls="collapseWorkplace">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Рабочий стол</span>
        </a>
        <div id="collapseWorkplace" class="collapse <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseWorkplace' && toggleMenu != '1'>show</#if>" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Глобальный:</h6>
                <a class="collapse-item overflow-hidden <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'workplace-global'>active-${color_style}</#if>" href="/workplace?l=2&w=1&b=${currentEnterprise.getEnterpriseIdString()!"0"}"><i class="fas fa-globe fa-l-2"></i>Основной</a>
                <h6 class="collapse-header">Предприятие:</h6>
                <a class="collapse-item overflow-hidden <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'workplace-enterprise'>active-${color_style}</#if>" href="/workplace?l=2&w=2&b=${currentEnterprise.getEnterpriseIdString()!"0"}"><i class="fas fa-industry fa-l-2"></i>${currentEnterprise.getEnterpriseNameShort()!""}</a>
            </div>
        </div>
    </li>

    <#list g_u_employees as employee>
        <#if employee.getEnterpriseId() == currentEnterprise.getEnterpriseId() >
            <!-- Divider -->
            <hr class="sidebar-divider">
        <#--    <hr class="sidebar-divider d-none d-md-block"><!-- Heading &ndash;&gt;-->
<#--            <div class="sidebar-heading">-->
<#--                Активность-->
<#--            </div>-->
            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="/workplace?l=3&w=3&b=${currentEnterprise.getEnterpriseIdString()!"0"}&e=${employee.getEmployeeIdString()!"0"}">
                    <i class="fas fa-fw fa-briefcase"></i>
                    <span>Я сотрудник</span></a>
            </li>
        </#if>
    </#list>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
<#--    <div class="sidebar-heading">-->
<#--        Люди-->
<#--    </div>-->

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseClient'>active</#if>">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseClient" aria-expanded="true" aria-controls="collapseClient">
            <i class="fas fa-fw fa-id-card"></i>
            <span>Клиенты</span>
        </a>
        <div id="collapseClient" class="collapse <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseClient' && toggleMenu != '1'>show</#if>" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">клиенты:</h6>
                <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'clients-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" href="/clients?l=2&b=${currentEnterprise.getEnterpriseIdString()!0}">Все клиенты</a>
                <#if noPay??>
                    <div class="noPayMenuItem collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'client-new-from-file-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" onclick="noPayAlert()"><i class="fas fa-download fa-l-2"></i>Загрузка файлом</div>
                    <div class="noPayMenuItem collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'client-new-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" onclick="noPayAlert()"><i class="fas fa-plus-circle fa-l-2"></i>Добавить клиента</div>
                <#else>
                    <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'client-new-from-file-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" href="/client-new-from-file?l=2&b=${currentEnterprise.getEnterpriseIdString()!0}"><i class="fas fa-download fa-l-2"></i>Загрузка файлом</a>
                    <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'client-new-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" href="/client-new?l=2&b=${currentEnterprise.getEnterpriseIdString()!0}"><i class="fas fa-plus-circle fa-l-2"></i>Добавить клиента</a>
                </#if>
            </div>
        </div>
    </li>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseEmployee'>active</#if>">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseEmployee" aria-expanded="true" aria-controls="collapseEmployee">
            <i class="fas fa-fw fa-gavel"></i>
            <span>Сотрудники</span>
        </a>
        <div id="collapseEmployee" class="collapse <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseEmployee' && toggleMenu != '1'>show</#if>" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">сотрудники:</h6>
                <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'employees-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" href="/employees?l=2&b=${currentEnterprise.getEnterpriseIdString()!0}">Все сотрудники</a>
                <#if noPay??>
                    <div class="noPayMenuItem collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'employee-new-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" onclick="noPayAlert()"><i class="fas fa-plus-circle fa-l-2"></i>Добавить сотрудника</div>
                <#else>
                    <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'employee-new-${currentEnterprise.getEnterpriseIdString()!0}'>active-${color_style}</#if>" href="/employee-new?l=2&b=${currentEnterprise.getEnterpriseIdString()!0}"><i class="fas fa-plus-circle fa-l-2"></i>Добавить сотрудника</a>
                </#if>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

<#--    <div class="sidebar-heading">-->
<#--        Управление-->
<#--    </div>-->

    <!-- Nav Item - Utilities Collapse Menu -->
<#--    <li class="nav-item <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'settings'>active</#if>">-->
<#--        <a class="nav-link" href="/settings?l=2&b=${currentEnterprise.getEnterpriseIdString()!"0"}">-->
<#--            <i class="fas fa-fw fa-cog fa-fw"></i>-->
<#--            <span>Настройки</span></a>-->
<#--    </li>-->

    <!-- Nav Item - Pages Collapse Menu -->
    <#include "*/menu/menu_faq.ftl">

    <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseProfile'>active</#if>">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProfile" aria-expanded="true" aria-controls="collapseProfile">
            <i class="fas fa-fw fa-user"></i>
            <span>Профиль</span>
        </a>
        <div id="collapseProfile" class="collapse <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseProfile' && toggleMenu != '1'>show</#if>" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Глобальный:</h6>
                <a class="collapse-item overflow-hidden <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'global-user'>active-${color_style}</#if>" href="/global-user?l=2&b=${currentEnterprise.getEnterpriseIdString()!"0"}"><i class="fas fa-globe fa-l-2"></i>Основной</a>
                <h6 class="collapse-header">Предприятие:</h6>
                <a class="collapse-item overflow-hidden <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'profile-enterprise'>active-${color_style}</#if>" href="/enterprise?l=2&b=${currentEnterprise.getEnterpriseIdString()!"0"}"><i class="fas fa-industry fa-l-2"></i>${currentEnterprise.getEnterpriseNameShort()!""}</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button onclick="setToggleMenuLeftCookie()" class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->