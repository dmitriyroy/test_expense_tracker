<#-- Freemarker template -->
<html>
<#include "*/page_head.ftl">
<#--<#import "*/pager.ftl" as p>-->
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
              <h6 class="m-0 font-weight-bold text-primary">Your Expenses</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                  <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Comment</th>
                  </tr>
                  </thead>
                  <tfoot>
                  <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Amount</th>
                    <th>Description</th>
                    <th>Comment</th>
                  </tr>
                  </tfoot>
                  <tbody>
                  <#list expenses as expense>
                    <tr onclick="viewExpense(${expense.getExpenseIdString()!0})">
                      <th>${expense.getExpenseIdString()!0}</th>
                      <th>${expense.getExpenseDttmStringYYYYMMDDHHMMSS()!""}</th>
                      <th>${expense.getExpenseAmount()!0}</th>
                      <th>${expense.getExpenseDescription()!""}</th>
                      <th>${expense.getExpenseComment()!""}</th>
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