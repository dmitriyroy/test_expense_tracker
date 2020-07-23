<#-- Freemarker template -->
<html>
<#include "*/page_head.ftl">
<body id="page-top" <#if toggleMenu == '1'>class="sidebar-toggled"</#if>>
<!-- Page Wrapper -->
<div id="wrapper">
  <#include "*/menu/menu_left_check.ftl">
  <!-- Content Wrapper -->
  <div id="content-wrapper" class="d-flex flex-column">
    <!-- Main Content -->
    <div id="content">
      <!-- Topbar -->
      <#if level?? && level gt 0>
        <#include "*/menu/menu_top.ftl">
      <#else>
        <#include "*/menu/menu_top_level_0.ftl">
      </#if>
      <!-- End of Topbar -->
          <!-- Begin Page Content -->
          <div class="container-fluid verification_mail_block">
            <!-- Page Heading -->
            <!-- 404 Error Text -->
            <div class="text-center">
              <div class="error mx-auto" data-text="404">404</div>
              <p class="lead text-gray-800 mb-5">Page Not Found</p>
<#--              <p class="text-gray-500 mb-0">It looks like you found a glitch in the matrix...</p>-->
              <a href="/index">&larr; Back to Workplace</a>
            </div>
          </div>
      <!-- /.container-fluid -->
    </div>
    <!-- End of Main Content -->
    <!-- Footer -->
    <#include "*/blocks/copyright.ftl">
    <!-- End of Footer -->
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