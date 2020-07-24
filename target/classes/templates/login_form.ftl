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
      <div class="container-fluid ">
        <div class="form-group row">
          <div class="col-md-8" style="height: 2.5em;">
          </div>
        </div>
        <!-- Outer Row -->
        <div class="row justify-content-center">
          <div class="col-xl-5 col-lg-5 col-md-5">
            <div class="card o-hidden border-0 shadow-lg my-5">
              <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                  <div class="col-lg-12">
                    <div class="p-5">
                      <div class="text-center">
                        <#if message??><div class="exist_user_alert">${message!""}</div></#if>
                        <h1 class="h4 text-gray-800 mb-4">Welcome!</h1>
                      </div>
                      <form class="user" action="/login" method="post">
                        <div class="form-group">
                          <input name="username" type="text" class="form-control form-control-user" id="exampleInputusername" aria-describedby="usernameHelp" required placeholder="username...">
                        </div>
                        <div class="form-group">
                          <input name="password" type="password" class="form-control form-control-user" id="exampleInputPassword" required placeholder="password...">
                        </div>
                        <button class="btn btn-primary btn-user btn-block" id="checkForm">
                          Login
                        </button>
                        <hr>
                        <div class="text-center">
                          <a class="small" href="/new-registration">Create an Account!</a>
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

