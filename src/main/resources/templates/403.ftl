<#-- Freemarker template -->
<html>
<#include "*/page_head.ftl">
<body id="page-top" <#if toggleMenu == '1'>class="sidebar-toggled"</#if>>
  <!-- Page Wrapper -->
  <div id="wrapper">
<#--    <#include "*/menu_left.ftl">-->
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
      <!-- Main Content -->
      <div id="content">
        <!-- Topbar -->
<#--        <#include "*/menu_top.ftl">-->
        <!-- End of Topbar -->
        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">Access denied</h1>
        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->
    </div>
    <!-- End of Content Wrapper -->
  </div>
  <!-- End of Page Wrapper -->
  <!-- Scroll to Top Button-->
  <#include "*/scroll_to_top_button.ftl">
  <!-- Logout Modal-->
  <#include "*/logout_modal.ftl">
  <#include "*/footer_include_js.ftl">
</body>
</html>