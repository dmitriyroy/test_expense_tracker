<#-- Freemarker template -->
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-darkgreen sidebar sidebar-dark accordion <#if toggleMenu == '1'>toggled</#if>" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/index" title="Index - page">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-globe"></i>
        </div>
        <div class="sidebar-brand-text mx-3 overflow-hidden"><#if currentUser??>${currentUser.getFI()!""}</#if></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <#if currentUser.roleId == 1 >
        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseUser'>active</#if>">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUser" aria-expanded="true" aria-controls="collapseUser">
                <i class="fas fa-fw fa-id-card"></i>
                <span>Users</span>
            </a>
            <div id="collapseUser" class="collapse <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseUser' && toggleMenu != '1'>show</#if>" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header">users:</h6>
                    <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'custom-users'>active-darkgreen</#if>" href="/custom-users">All users</a>
                    <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'custom-user-new'>active-darkgreen</#if>" href="/custom-user-new"><i class="fas fa-plus-circle fa-l-2"></i>Add user</a>
                </div>
            </div>
        </li>
    </#if>
    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseExpense'>active</#if>">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseExpense" aria-expanded="true" aria-controls="collapseExpense">
            <i class="fas fa-fw fa-money-bill"></i>
            <span>Expenses</span>
        </a>
        <div id="collapseExpense" class="collapse <#if activeMenuLevel_1?? && activeMenuLevel_1 == 'collapseExpense' && toggleMenu != '1'>show</#if>" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Expenses:</h6>
                <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'expense'>active-darkgreen</#if>" href="/expenses">My expenses</a>
                <a class="collapse-item <#if activeMenuLevel_2?? && activeMenuLevel_2 == 'expense-new'>active-darkgreen</#if>" href="/expense-new"><i class="fas fa-plus-circle fa-l-2"></i>Add expense</a>
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