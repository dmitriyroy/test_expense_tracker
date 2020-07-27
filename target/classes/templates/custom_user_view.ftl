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

          <div class="h3 mb-4 text-gray-800 text-center mx-auto">${header!""}</div>
          <div name="add_custom_user_form" class="form-horizontal border-left-darkgreen border-right-darkgreen border-top-darkgreen border-bottom-darkgreen " >
              <input type="hidden" name="userId" value="${customUser.getUserIdString()!0}" >
              <div class="form-group row">
                  <div class="col-md-8"></div>
                  <div class="col-md-2">
                      <#if profile??>
                          <button type="button" onclick="editProfile()"
                                  class="btn btn-secondary btn-icon-split" style="float: right;">
                            <span class="icon text-white-50">
                              <i class="fa fa-pen" aria-hidden="true"></i>
                            </span>
                              <span class="text">Edit</span>
                          </button>
                      <#else>
                          <button type="button" onclick="editCustomUser(${customUser.getUserIdString()!0})"
                                  class="btn btn-secondary btn-icon-split" style="float: right;">
                                <span class="icon text-white-50">
                                  <i class="fa fa-pen" aria-hidden="true"></i>
                                </span>
                              <span class="text">Edit</span>
                          </button>
                      </#if>
                  </div>
              </div>
              <div class="form-group row">
                  <label for="username" class="col-md-2 control-label">username*:</label>
                  <div class="col-md-3">
                      <input readonly type="text" class="form-control" name="username" id="username" value="${customUser.username!""}">
                  </div>
                  <label for="roleId" class="col-md-2 control-label">Role:</label>
                  <div class="col-md-3">
                      <#list userRoles as userRole>
                          <#if userRole.roleId == customUser.roleId>
                              <input readonly type="text" class="form-control" name="roleId" id="roleId" value="${(userRole.roleDescription)!""}">
                          </#if>
                      </#list>
                  </div>
              </div>
              <div class="form-group row">
                <label for="fName" class="col-md-2 control-label">First Name*:</label>
                <div class="col-md-3">
                  <input readonly type="text" class="form-control" name="fName" id="fName" value="${customUser.firstName!""}">
                </div>
                <label for="sName" class="col-md-2 control-label">Second Name:</label>
                <div class="col-md-3">
                  <input readonly type="text" class="form-control" name="sName" id="sName" value="${customUser.secondName!""}">
                </div>
              </div>
          </div>

        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->
      <!-- Footer -->
<#--      <#include "*/blocks/copyright.ftl">-->
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