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
          <form name="add_expense_form" class="form-horizontal border-left-darkgreen border-right-darkgreen border-top-darkgreen border-bottom-darkgreen " action="/expense-add" method="post" >
              <input type="hidden" name="userId" value="${currentUser.getUserIdString()!0}" >
              <div class="form-group row">
                <label for="description" class="col-md-2 control-label">Description*:</label>
                <div class="col-md-8">
                  <input type="text" class="form-control" name="description" id="description" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="amount" class="col-md-2 control-label">Amount*:</label>
                <div class="col-md-2">
                  <input type="number" step="0.01" min="0" value="0" class="form-control" name="amount" id="amount" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="comment" class="col-md-2 control-label">Comment:</label>
                <div class="col-md-8">
                  <textarea class="form-control" name="comment" id="comment" rows="3"></textarea>
                </div>
              </div>

              <div class="form-group row">
                <label for="datePay" class="col-md-2 control-label">Date*:</label>
                <div class="col-md-2">
                  <input type="date" class="form-control" name="datePay" id="datePay" required>
                </div>
                <label for="hours" class="col-md-1 control-label">Hours*:</label>
                <div class="col-md-1">
                    <input type="number" min="0" max="23" step="1" value="0" class="form-control" name="hours" id="hours" required>
                </div>
                <label for="minutes" class="col-md-1 control-label">Minutes*:</label>
                <div class="col-md-1">
                  <input type="number" min="0" max="59" step="1" value="0" class="form-control" name="minutes" id="minutes" required>
                </div>
                <label for="seconds" class="col-md-1 control-label">Seconds*:</label>
                <div class="col-md-1">
                  <input type="number" min="0" max="59" step="1" value="0" class="form-control" name="seconds" id="seconds" required>
                </div>
              </div>

              <div class="form-group row">
                <div class="col-md-7"></div>
                <div class="col-md-3">
                  <button type="submit" class="btn btn-success btn-icon-split" style="float: right;">
                      <span class="icon text-white-50">
                        <i class="fas fa-check"></i>
                      </span>
                    <span class="text">Add Expense</span>
                  </button>
                </div>
              </div>
          </form>


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