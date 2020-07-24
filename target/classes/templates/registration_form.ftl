<#-- Freemarker template -->
<html>
<#include "*/page_head.ftl">
<body id="page-top" <#if toggleMenu == '1'>class="sidebar-toggled"</#if>>
<!-- Page Wrapper -->
<div id="wrapper">
  <!-- Content Wrapper -->
  <div id="content-wrapper" class="d-flex flex-column">
    <!-- Main Content -->
    <div id="content">
      <div class="container-fluid">
        <div class="form-group row">
          <div class="col-md-8" style="height: 2.5em;">
          </div>
        </div>
        <!-- Outer Row -->
        <div class="col-xl-7 col-lg-7 col-md-7 container_center">
          <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
              <!-- Nested Row within Card Body -->
              <div class="row">
                <div class="col-lg-12">
                  <div class="p-5">
                    <div class="text-center">
                      <#if message??><div class="exist_user_alert">${message!""}</div></#if>
                      <h1 class="h4 text-gray-900 mb-4">Create an Account</h1>
                    </div>
                    <form name="registration_form" class="user" action="/registration" method="post">
                      <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                          <input name="fName" type="text" class="form-control form-control-user" id="exampleFirstName" required placeholder="First Name..." <#if fName??>value="${fName!""}"</#if>>
                        </div>
                        <div class="col-sm-6">
                          <input name="sName" type="text" class="form-control form-control-user" id="exampleLastName" required placeholder="Second Name..." <#if sName??>value="${sName!""}"</#if>>
                        </div>
                      </div>
                      <div class="form-group">
                        <input name="username" type="text" class="form-control form-control-user" id="exampleInputUsername" required placeholder="Username..." <#if username??>value="${username!""}"</#if>>
                      </div>
                      <div class="form-group row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                          <input id="password1" name="password" type="password" class="form-control form-control-user" required placeholder="Password">
                        </div>
                        <div class="col-sm-6">
                          <input id="password2" name="repeatPassword"  type="password" class="form-control form-control-user" required placeholder="Repeat Password">
                        </div>
                      </div>
                      <button onclick="checkRegistrationForm()" id="checkForm" class="btn btn-primary btn-user btn-block">
                        Register Account
                      </button>
                      <hr>
                      <div class="text-center">
                        <a class="small" href="/login">Already have an account? Login!</a>
                      </div>
                      <hr>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
      <!-- End of Main Content -->
    </div>
    <!-- End of Content Wrapper -->
  </div>
</div>
<!-- End of Page Wrapper -->
<#include "*/footer_include_js.ftl">
</body>
</html>

