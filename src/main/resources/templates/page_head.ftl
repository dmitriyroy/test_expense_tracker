<#-- Freemarker template -->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Custom fonts for this template-->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="../css/sb-admin-2.min.css" rel="stylesheet">
  <!-- Custom styles for this page -->
  <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!--Custom styles-->
    <link rel="stylesheet" href="../css/style.css" type="text/css" />
<#--    ---------------------------------------        -->

    <title>Expense Tracker</title>
</head>

<#--  Set cookie for menu_left.ftl  -->
<#assign toggleMenu = '0'>
<#if request??>
  <#assign cookies = request.getCookies()![]>
  <#list cookies as cookie>
    <#if cookie.name = "ExpenseTrackerToggleMenuLeft">
      <#assign toggleMenu = cookie.value>
    </#if>
  </#list>
</#if>