<#-- Freemarker template -->
<html>
<#include "*/page_head.ftl">
<body id="page-top" <#if toggleMenu == '1'>class="sidebar-toggled"</#if>>
  <!-- Page Wrapper -->
  <div id="wrapper">
    <#include "*/menu_left.ftl">
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
      <!-- Main Content -->
      <div id="content">
        <!-- Topbar -->
        <#include "*/menu_top.ftl">
        <!-- End of Topbar -->
        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
<#--          <div class="h3 mb-4 text-gray-800 text-center mx-auto">${header!""}</div>-->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">All Users</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                  <tr class="text-darkgreen">
                    <th>ID</th>
                    <th>username</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Date Registration</th>
                    <th>Role</th>
                  </tr>
                  </thead>
                  <tfoot>
                  <tr class="text-darkgreen">
                    <th>ID</th>
                    <th>username</th>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Date Registration</th>
                    <th>Role</th>
                  </tr>
                  </tfoot>
                  <tbody>
                  <#list customUsers as customUser>
                    <tr class="cursor_pointer" onclick="viewCustomUser(${customUser.getUserIdString()!0})">
                      <th>${customUser.getUserIdString()!0}</th>
                      <th>${customUser.username!""}</th>
                      <th>${customUser.firstName!""}</th>
                      <th>${customUser.secondName!""}</th>
                      <th>${customUser.getRegistrationDttmStringYYYYMMDDHHMMSS()!""}</th>
                      <#list userRoles as userRole>
                        <#if userRole.roleId == customUser.roleId>
                          <th>${userRole.roleDescription!""}</th>
                        </#if>
                      </#list>
                    </tr>
                  </#list>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

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