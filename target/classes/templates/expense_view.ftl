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

          <div name="view_expense_form" class="form-horizontal border-left-darkgreen border-right-darkgreen border-top-darkgreen border-bottom-darkgreen " >
<#--              <input type="hidden" name="userId" value="${currentUser.getUserIdString()!0}" >-->
              <div class="form-group row">
                  <div class="col-md-8"></div>
                  <div class="col-md-2">
                      <button type="button" onclick="editExpense(${expense.getExpenseIdString()!0})"
                              class="btn btn-secondary btn-icon-split" style="float: right;">
                        <span class="icon text-white-50">
                          <i class="fa fa-pen" aria-hidden="true"></i>
                        </span>
                          <span class="text">Edit</span>
                      </button>
                  </div>
              </div>
              <div class="form-group row">
                <label for="description" class="col-md-2 control-label">Description*:</label>
                <div class="col-md-8">
                  <input readonly type="text" class="form-control" name="description" id="description" value="${expense.getExpenseDescription()!""}">
                </div>
              </div>
              <div class="form-group row">
                <label for="amount" class="col-md-2 control-label">Amount*:</label>
                <div class="col-md-3">
                  <input readonly type="text" class="form-control" name="amount" id="amount"  value="${expense.getExpenseAmount()!0}">
                </div>
              </div>
              <div class="form-group row">
                <label for="comment" class="col-md-2 control-label">Comment:</label>
                <div class="col-md-8">
                  <textarea readonly class="form-control" name="comment" id="comment" rows="3">${expense.getExpenseComment()!""}</textarea>
                </div>
              </div>

              <div class="form-group row">
                <label for="datePay" class="col-md-2 control-label">Date*:</label>
                <div class="col-md-8">
                  <input readonly type="text" class="form-control" name="datePay" value="${expense.getExpenseDttmStringYYYYMMDDHHMMSS()}" >
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