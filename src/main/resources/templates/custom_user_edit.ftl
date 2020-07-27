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
          <form name="update_custom_user_form" class="form-horizontal border-left-darkgreen border-right-darkgreen border-top-darkgreen border-bottom-darkgreen " <#if profile??>action="/profile-update"<#else>action="/custom-user-update"</#if> method="post" >
              <input type="hidden" name="userId" value="${customUser.getUserIdString()!0}" >
              <div class="form-group row">
                  <label for="username" class="col-md-2 control-label">username*:</label>
                  <div class="col-md-3">
                      <input readonly type="text" class="form-control" name="username" id="username" value="${customUser.username!""}">
                  </div>
                  <label for="roleId" class="col-md-2 control-label">Role:</label>
                  <div class="col-md-3">
                      <#if profile??>
                          <#list userRoles as userRole>
                              <#if userRole.roleId == customUser.roleId>
                                  <input type="hidden" class="form-control" name="roleId" id="roleId" value="${(customUser.roleId)!""}">
                                  <input readonly type="text" class="form-control" name="roleName" id="roleName" value="${(userRole.roleDescription)!""}">
                              </#if>
                          </#list>
                      <#else>
                          <select name="roleId" class="form-control" id="roleId">
                              <#list userRoles as userRole>
                                  <option <#if userRole.roleId == customUser.roleId>selected="selected"</#if>
                                          value="${userRole.roleId!0}" >${(userRole.roleDescription)!""}</option>
                              </#list>
                          </select>
                      </#if>
                  </div>
              </div>
              <div class="form-group row">
                <label for="fName" class="col-md-2 control-label">First Name*:</label>
                <div class="col-md-3">
                  <input type="text" class="form-control" name="fName" id="fName" value="${customUser.firstName!""}" required>
                </div>
                <label for="sName" class="col-md-2 control-label">Second Name:</label>
                <div class="col-md-3">
                  <input type="text" class="form-control" name="sName" id="sName"  value="${customUser.secondName!""}">
                </div>
              </div>
              <div class="form-group row">
                  <label for="password" class="col-md-2 control-label">Password:<br>(if you do not want to change the password, leave the field blank)</label>
                  <div class="col-md-3">
                      <input type="password" class="form-control" name="password" id="password1" placeholder="Password">
                  </div>
                  <label for="repeatPassword" class="col-md-2 control-label">Repeat Password:<br>(if you do not want to change the password, leave the field blank)</label>
                  <div class="col-md-3">
                      <input type="password" class="form-control" name="repeatPassword" id="password2" placeholder="Repeat Password">
                  </div>
              </div>
              <div class="form-group row">
                <div class="col-md-7"></div>
                <div class="col-md-3">
                  <button type="button" onclick="submitEditCustomUserForm()" id="checkForm" class="btn btn-success btn-icon-split" style="float: right;">
                      <span class="icon text-white-50">
                        <i class="fas fa-check"></i>
                      </span>
                    <span class="text">Edit User</span>
                  </button>
                </div>
              </div>
          </form>

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