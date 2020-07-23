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
      <#include "*/menu/menu_top_level_0.ftl">

  <div class="container-fluid <#--bg-gradient-primary-->">
    <div class="form-group row">
      <div class="col-md-8" style="height: 2.5em;">
      </div>
    </div>
    <#include "*/blocks/crm_name.ftl">
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
                    <h1 class="h4 text-gray-800 mb-4">Вход</h1>
                  </div>
                  <form class="user" action="/login" method="post">
                    <div class="form-group">
                      <input name="username" type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" required placeholder="email...">
                    </div>
                    <div class="form-group">
                      <input name="password" type="password" class="form-control form-control-user" id="exampleInputPassword" required placeholder="password...">
                    </div>
                    <button class="btn btn-primary btn-user btn-block" id="checkForm">
                      Вход
                    </button>
                    <!--
                    <hr>
                    <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a>
                    -->
                    <hr>
                    <div class="text-center">
                      <a class="small" href="/forgot-password">Забыли пароль?</a>
                    </div>
                    <div class="text-center">
                      <a class="small" href="/new-registration">Зарегистрироваться</a>
                    </div>
                    <hr>
                    <div>
                      <div class="custom-control custom-checkbox small">
                        <input onclick="loginFormPrivacyPolicyChecked()" type="checkbox" class="custom-control-input" id="customCheck" checked>
                        <#--                        <label class="custom-control-label" for="customCheck">Запомнить меня</label>-->
                        <label class="custom-control-label privacy-policy" for="customCheck"><a href="http://t2studio.org/privacy-policy/" title="Публичная оферта" target="_blank">Даю согласие на <strong>Использование моих персональных данных</strong>. С <strong>Пользовательским соглашением</strong> и использованием <strong>файлов cookies</strong> ознакомлен(а).</a></label>
                      </div>
                    </div>
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
  <!-- Footer -->
  <#include "*/blocks/copyright.ftl">
  <!-- End of Footer -->
    </div>
    <!-- End of Content Wrapper -->
  </div>
</div>
  <!-- End of Page Wrapper -->
  <!-- Scroll to Top Button-->
  <#include "*/scroll_to_top_button.ftl">
  <!-- Logout Modal-->
  <#include "*/logout_modal.ftl">
  <#include "*/footer_include_js.ftl">
</body>
</html>

