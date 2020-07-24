function viewExpense(expenseId) {
    window.open("/expense?expid="+expenseId, "_self");
}
function editExpense(expenseId) {
    window.open("/expense-edit?expid="+expenseId, "_self");
}


function editGlobalUser(level, enterpriseId, employeeId){
    var url = "/global-user-edit?l=" + level;
    if(enterpriseId != null){
        url += ("&b=" + enterpriseId);
    }
    if(employeeId != null){
        url += ("&e=" + employeeId);
    }
    window.open(url, "_self");
}

function checkRegistrationForm(){
    var submitOk = 1;
    if(document.getElementById('password1').value.trim() == ''){
        submitOk = 0;
        document.getElementById('password1').style.borderColor = "#e83e8c";
        document.getElementById('password1').focus();
    } else{
        if(document.getElementById('password1').value !=
            document.getElementById('password2').value){
            submitOk = 0;
            alert("Пароли не совпадают.");
            document.getElementById('password1').style.borderColor = "#e83e8c";
            document.getElementById('password1').focus();
        } else{
            document.getElementById('password1').style.borderColor = "#cccccc";
        }
    }
    if(document.getElementById('password2').value == ''){
        submitOk = 0;
        document.getElementById('password2').style.borderColor = "#e83e8c";
    } else{

        if(document.getElementById('password1').value !=
            document.getElementById('password2').value){
            submitOk = 0;
            document.getElementById('password2').style.borderColor = "#e83e8c";
        } else{
            document.getElementById('password2').style.borderColor = "#cccccc";
            // document.getElementById('password2').value = '';
        }
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.registration_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function submitGlobalUserChangePassForm(){
    var submitOk = 1;
    if(document.getElementById('password1').value.trim() == ''){
        submitOk = 0;
        document.getElementById('password1').style.borderColor = "#e83e8c";
        document.getElementById('password1').focus();
    }else if(document.getElementById('password2').value == ''){
        submitOk = 0;
        document.getElementById('password2').style.borderColor = "#e83e8c";
    }else{
        if(document.getElementById('password1').value !=
            document.getElementById('password2').value){
            submitOk = 0;
            alert("Пароли не совпадают.");
            document.getElementById('password1').style.borderColor = "#e83e8c";
            document.getElementById('password2').style.borderColor = "#e83e8c";
            document.getElementById('password1').focus();
        } else{
            document.getElementById('password1').style.borderColor = "#cccccc";
            document.getElementById('password2').style.borderColor = "#cccccc";
        }
    }
    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.global_user_change_pass_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function editEnterprise(level,enterpriseId, currentEmployeeId){
    var url = "/enterprise-edit?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(currentEmployeeId != null){
        url += ("&e="+currentEmployeeId);
    }
    window.open(url, "_self");
}

function submitEnterpriseAddNewForm(){

}

function submitEnterpriseEditForm(){

}

function getPay(level,enterpriseId){
    window.open("/get-pay?l="+level+"&b="+enterpriseId, "_blank");
}

function viewEmployee(level, enterpriseId, currentEmployeeId, employeeId){
    // window.open("/employee?b=" + enterpriseId + "&e=" + employeeId, "_self");
    if(currentEmployeeId != null) {
        window.open("/employee?l=" + level + "&b=" + enterpriseId + "&e=" + currentEmployeeId + "&e2=" + employeeId, "_self");
    }else{
        window.open("/employee?l=" + level + "&b=" + enterpriseId + "&e2=" + employeeId, "_self");
    }
}

function visibleDismissedEmployee(){
    var checkBoxVisibleDismissed = document.getElementById("visible_dismissed");
    var dismissedEmployeeLine = document.getElementsByClassName("dismissed_employee");
    for( var i=0; i < dismissedEmployeeLine.length; i++){
        var dismissedEmployee = dismissedEmployeeLine.item(i);
        if(checkBoxVisibleDismissed.checked){
            dismissedEmployee.style.display = "table-row";
        }else{
            dismissedEmployee.style.display = "none";
        }
    }
}

function viewClient(level,enterpriseId,currentEmployeeId,clientId){
    // alert('viewClient(level,enterpriseId,employeeId,clientId);\nlevel='+level
    //     +'; enterpriseId='+enterpriseId
    //     +'; employeeId='+employeeId
    //     +'; clientId='+clientId
    // );
    // if(currentEmployeeId != null) {
    //     window.open("/client?l="+level+"&b=" + enterpriseId + "&e=" + currentEmployeeId + "&c=" + clientId, "_self");
    // }else{
    //     window.open("/client?l="+level+"&b=" + enterpriseId + "&c=" + clientId, "_self");
    // }
    var url = "/client?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(currentEmployeeId != null){
        url += ("&e="+currentEmployeeId);
    }
    url += ("&c=" + clientId);
    window.open(url, "_self");
}

function editClient(level,enterpriseId,currentEmployeeId,clientId){
    // window.open("/client-edit?b="+enterpriseId+"&e="+employeeId+"&c="+clientId, "_self");
    // alert('editClient(level,enterpriseId,employeeId,clientId);<br>level='+level
    //     +'; <br>enterpriseId='+enterpriseId
    //     +'; <br>employeeId='+employeeId
    //     +'; <br>clientId='+clientId);
    // if(employeeId != null) {
    //     window.open("/client-edit?l="+level+"&b=" + enterpriseId + "&e=" + employeeId + "&c=" + clientId, "_self");
    // }else{
    //     window.open("/client-edit?l="+level+"&b=" + enterpriseId + "&c=" + clientId, "_self");
    // }
    var url = "/client-edit?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(currentEmployeeId != null){
        url += ("&e="+currentEmployeeId);
    }
    url += ("&c=" + clientId);
    window.open(url, "_self");
}

///////////

function viewClientsGlobal(level,enterpriseId,employeeId){
    var url = "/clients-gl?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsEnterprise(level,enterpriseId,employeeId){
    var url = "/clients-ent?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsEmployee(level,enterpriseId,employeeId){
    var url = "/clients-emp?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsYesterdayGlobal(level,enterpriseId,employeeId){
    var url = "/clients-yesterday-gl?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsYesterdayEnterprise(level,enterpriseId,employeeId){
    var url = "/clients-yesterday-ent?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsYesterdayEmployee(level,enterpriseId,employeeId){
    var url = "/clients-yesterday-emp?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsWithoutMailingGlobal(level,enterpriseId,employeeId){
    var url = "/clients-no-mailing-gl?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsWithoutMailingEnterprise(level,enterpriseId,employeeId){
    var url = "/clients-no-mailing-ent?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewClientsWithoutMailingEmployee(level,enterpriseId,employeeId){
    var url = "/clients-no-mailing-emp?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

///////////

function editEmployee(level,enterpriseId,currentEmployeeId, employeeId){
    // window.open("/employee-edit?b="+enterpriseId+"&e="+employeeId, "_self");
    if(enterpriseId != null && currentEmployeeId != null) {
        window.open("/employee-edit?l=" + level  + "&b=" + enterpriseId + "&e=" + currentEmployeeId + "&e2=" + employeeId, "_self");
    }else if(enterpriseId != null && currentEmployeeId == null){
        window.open("/employee-edit?l=" + level  + "&b=" + enterpriseId + "&e2=" + employeeId, "_self");
    }else if(enterpriseId == null && currentEmployeeId != null){
        window.open("/employee-edit?l=" + level + "&e=" + currentEmployeeId + "&e2=" + employeeId, "_self");
    }else{
        window.open("/employee-edit?l=" + level + "&e2=" + employeeI, "_self");
    }
}

function submitAddTaskForm(){
    var submitOk = 1;
    if(document.getElementById('taskDescriptionShort').value == ''){
        document.getElementById('taskDescriptionShort').style.borderColor = "#e83e8c";
        document.getElementById('taskDescriptionShort').focus();
        submitOk = 0;
    }else{
        document.getElementById('taskDescriptionShort').style.borderColor = "#cccccc";
    }
    if(document.getElementById('dateFinishPlane').value == ''){
        document.getElementById('dateFinishPlane').style.borderColor = "#e83e8c";
        document.getElementById('dateFinishPlane').focus();
        submitOk = 0;
    }else{
        document.getElementById('dateFinishPlane').style.borderColor = "#cccccc";
    }
    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.add_task_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function editTask(){
    document.getElementById("edit_task_block").style = "display: block; margin: 0 auto;";
    document.getElementById("edit_field").disabled = true;
    document.getElementById("edit_field").style = "background: grey;";
    var posY = document.getElementById("edit_task_block").getBoundingClientRect().y;
    setTimeout(function(){window.scrollTo(0, posY - 0);},300);
}

function viewTask(level,enterpriseId,employeeId,taskId){
    // window.open("/task?l=" + level + "&b=" + enterpriseId + "&e=" + employeeId + "&t=" + taskId, "_self");
    var url = "/task?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    url += ("&t="+taskId);
    window.open(url, "_self");
}


function viewTaskByManager(level,enterpriseId,employeeId,tasksType,taskManagerId){
    var url = "";
    if(tasksType == 1) {
        url = "/tasks-manager-open?l=" + level;
    }else if(tasksType == 2){
        url = "/tasks-manager-close?l=" + level;
    }else if(tasksType == 3){
        url = "/tasks-manager-today?l=" + level;
    }else if(tasksType == 4){
        url = "/tasks-manager-overdue?l=" + level;
    }else if(tasksType == 5){
        url = "/tasks-manager-all?l=" + level;
    }else{
        url = "/tasks-manager-all?l=" + level;
    }

    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    url += ("&manid="+taskManagerId);
    window.open(url, "_self");
}

function viewCloseTasksYesterdayGlobal(level,enterpriseId,employeeId){
    var url = "/tasks-close-yesterday-gl?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewCloseTasksYesterdayEnterprise(level,enterpriseId,employeeId){
    var url = "/tasks-close-yesterday-ent?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewCloseTasksYesterdayEmployee(level,enterpriseId,employeeId){
    var url = "/tasks-close-yesterday-emp?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewOpenTasksGlobal(level,enterpriseId,employeeId){
    var url = "/tasks-all-open-gl?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewOpenTasksEnterprise(level,enterpriseId,employeeId){
    var url = "/tasks-all-open?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewOpenTasksEmployee(level,enterpriseId,employeeId){
    var url = "/tasks-my-open?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewNewDateTasksYesterdayGlobal(level,enterpriseId,employeeId){
    var url = "/tasks-new-date-gl?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewNewDateTasksYesterdayEnterprise(level,enterpriseId,employeeId){
    var url = "/tasks-new-date-ent?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewNewDateTasksYesterdayEmployee(level,enterpriseId,employeeId){
    var url = "/tasks-new-date-emp?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewOverdueTasksGlobal(level,enterpriseId,employeeId){
    var url = "/tasks-overdue-gl?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewOverdueTasksEnterprise(level,enterpriseId,employeeId){
    var url = "/tasks-overdue-ent?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function viewOverdueTasksEmployee(level,enterpriseId,employeeId){
    var url = "/tasks-overdue-emp?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(employeeId != null){
        url += ("&e="+employeeId);
    }
    window.open(url, "_self");
}

function submitEditTaskForm(){
    var submitOk = 1;
    if(document.getElementById('taskDescriptionShort').value == ''){
        document.getElementById('taskDescriptionShort').style.borderColor = "#e83e8c";
        document.getElementById('taskDescriptionShort').focus();
        submitOk = 0;
    }else{
        document.getElementById('taskDescriptionShort').style.borderColor = "#cccccc";
    }
    if(document.getElementById('dateFinishPlane').value == ''){
        document.getElementById('dateFinishPlane').style.borderColor = "#e83e8c";
        document.getElementById('dateFinishPlane').focus();
        submitOk = 0;
    }else{
        document.getElementById('dateFinishPlane').style.borderColor = "#cccccc";
    }
    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.edit_task_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function checkStartLetterSenderNow(){
    if(document.getElementById('start').checked) {
        document.getElementById('dateStart').value = new Date().toISOString().substring(0, 10);
    }
}

function getPaySelect(tariffId, priceOneWorkplaceForMonth){
    var cntMonthsSelect = document.getElementById('countMonths_'+tariffId);
    var cntMonths = cntMonthsSelect.options[cntMonthsSelect.selectedIndex].value;
    var cntWorkplacesSelect = document.getElementById('countWorkplaces_'+tariffId);
    var cntWorkplaces = cntWorkplacesSelect.options[cntWorkplacesSelect.selectedIndex].value;
    var price = cntMonths * cntWorkplaces * priceOneWorkplaceForMonth;
    document.getElementById('tariffFullPrice_'+tariffId).innerHTML = price + ' грн';
    document.getElementById('sum_'+tariffId).value = price;
    document.getElementById('cntMonths_'+tariffId).value = cntMonths;
    document.getElementById('cntWorkplaces_'+tariffId).value = cntWorkplaces;
}

function loginFormPrivacyPolicyChecked(){
    var customCheck = document.getElementById('customCheck');
    var loginButton = document.getElementById('checkForm');
    if(customCheck.checked){
        loginButton.disabled = false;
    }else{
        loginButton.disabled = true;
    }
}

function noPayAlert(){
    alert("Для дальнейшего пользования UkrCRM необходимо внести оплату." +
          "\nЭто можно сделать в карточке предприятия");
        // "\n<a href=\"http://localhost:7755/get-pay?l=2&b=1\" target='_parent'>http://localhost:7755/get-pay?l=2&b=1</a>");
}





function filterClientForm(){
    var value = document.getElementById('status_list').value;
    var val = value.split('];[');
    for(i=0; i<val.length; i++){
        var element = document.getElementById('status-'+val[i]);
        if(element.checked){
            document.getElementById('filter-statuses').value += val[i] + ";";
        }
    }
    var result = document.getElementById('filter-statuses').value;
    if(result.length > 1){
        result = result.substring(0, result.length - 1)
    }
    document.getElementById('filter-statuses').value = result;
    document.getElementById('checkForm').disabled = true;
    document.filter_client_form.submit();
}

function submitAddEmployeeForm(){
    var submitOk = 1;

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var mail = document.getElementById('username').value;
    if( reg.test(mail) == false){
        alert('Введите корректный e-mail');
        document.getElementById('username').style.borderColor = "#e83e8c";
        document.getElementById('username').focus();
        return;
    }else{
        document.getElementById('username').style.borderColor = "#cccccc";
    }

    if(document.getElementById('password1').value.trim() == ''){
        submitOk = 0;
        document.getElementById('password1').style.borderColor = "#e83e8c";
        document.getElementById('password1').focus();
    }else if(document.getElementById('password2').value == ''){
        submitOk = 0;
        document.getElementById('password2').style.borderColor = "#e83e8c";
        document.getElementById('password2').focus();
    }else{
        if(document.getElementById('password1').value !=
            document.getElementById('password2').value){
            submitOk = 0;
            alert("Пароли не совпадают.");
            document.getElementById('password1').style.borderColor = "#e83e8c";
            document.getElementById('password2').style.borderColor = "#e83e8c";
        } else{
            document.getElementById('password1').style.borderColor = "#cccccc";
            document.getElementById('password2').style.borderColor = "#cccccc";
        }
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.add_employee_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function submitUpdateEmployeeForm(){
    var submitOk = 1;

    if(document.getElementById('username').value.trim() == ''){
        submitOk = 0;
        document.getElementById('username').style.borderColor = "#e83e8c";
        document.getElementById('username').focus();
    }

    if(document.getElementById('fname').value.trim() == ''){
        submitOk = 0;
        document.getElementById('fname').style.borderColor = "#e83e8c";
        document.getElementById('fname').focus();
    } else{
        document.getElementById('fname').style.borderColor = "#cccccc";
    }
    if(document.getElementById('sname').value.trim() == ''){
        submitOk = 0;
        document.getElementById('sname').style.borderColor = "#e83e8c";
        document.getElementById('sname').focus();
    } else{
        document.getElementById('sname').style.borderColor = "#cccccc";
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.update_employee_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function submitAddClientForm() {
    var submitOk = 1;
    if(document.getElementById('fname').value.trim() == ''){
        submitOk = 0;
        document.getElementById('fname').style.borderColor = "#e83e8c";
        document.getElementById('fname').focus();
    } else{
        document.getElementById('fname').style.borderColor = "#cccccc";
    }

    if(document.getElementById('sname').value.trim() == ''){
        submitOk = 0;
        document.getElementById('sname').style.borderColor = "#e83e8c";
        document.getElementById('sname').focus();
    } else{
        document.getElementById('sname').style.borderColor = "#cccccc";
    }


    // if(document.getElementById('phone').value.trim() == ''
    //     &&
    //    document.getElementById('mail').value.trim() == ''
    //   ){
    //     submitOk = 0;
    //     document.getElementById('phone').style.borderColor = "#e83e8c";
    //     document.getElementById('mail').style.borderColor = "#e83e8c";
    //     document.getElementById('mail').focus();
    // } else{
    //     document.getElementById('phone').style.borderColor = "#cccccc";
    //     document.getElementById('mail').style.borderColor = "#cccccc";
    // }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.add_client_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function submitUpdateClientForm() {
    var submitOk = 1;
    if(document.getElementById('fname').value.trim() == ''){
        submitOk = 0;
        document.getElementById('fname').style.borderColor = "#e83e8c";
        document.getElementById('fname').focus();
    } else{
        document.getElementById('fname').style.borderColor = "#cccccc";
    }

    // if(document.getElementById('phone').value.trim() == ''
    //     &&
    //    document.getElementById('mail').value.trim() == ''
    //   ){
    //     submitOk = 0;
    //     document.getElementById('mail').style.borderColor = "#e83e8c";
    //     document.getElementById('mail').focus();
    //     document.getElementById('phone').style.borderColor = "#e83e8c";
    // } else{
    //     document.getElementById('mail').style.borderColor = "#cccccc";
    //     document.getElementById('phone').style.borderColor = "#cccccc";
    // }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.update_client_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function viewMailingList(level, enterpriseId, employeeId, mailingListId){
    window.open("/mailing-list?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&mlid="+mailingListId, "_self");
}

function editMailingList(level, enterpriseId, employeeId, mailingListId) {
    window.open("/mailing-list-edit?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&mlid="+mailingListId, "_self");
}

function submitAddMailingToMailingListForm(mailingCount) {
    var submitOk = 1;
    for(i=1; i<=mailingCount; i++){
        var element = document.getElementById('mailing-'+i);
        if(element.checked){
            document.getElementById('mailingIds').value += document.getElementById('mailingId-'+i).value + ";";
        }
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.mailing_list_add_mailing_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}
function submitAddMailing2ToMailingListForm(mailingCount) {
    var submitOk = 1;
    for(i=1; i<=mailingCount; i++){
        var element = document.getElementById('mailing2-'+i);
        if(element.checked){
            document.getElementById('mailingId2s').value += document.getElementById('mailingId2-'+i).value + ";";
        }
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm2').disabled = true;
        document.mailing_list_add_mailing_2_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function submitAddClientToMailingListForm(clientCount) {
    var submitOk = 1;
    for(i=1; i<=clientCount; i++){
        var element = document.getElementById('client-'+i);
        if(element.checked){
            document.getElementById('clientIds').value += document.getElementById('clientId-'+i).value + ";";
        }
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.mailing_list_add_client_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function viewLetterSubject(level, enterpriseId, employeeId, letterSubjectId){
    window.open("/letter-subject?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&lsub="+letterSubjectId, "_self");
}

function editLetterSubject(level, enterpriseId, employeeId, letterSubjectId) {
    window.open("/letter-subject-edit?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&lsub="+letterSubjectId, "_self");
}

function viewLetterSignature(level, enterpriseId, employeeId, letterSignatureId){
    window.open("/letter-signature?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&lsig="+letterSignatureId, "_self");
}

function editLetterSignature(level, enterpriseId, employeeId, letterSignatureId) {
    window.open("/letter-signature-edit?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&lsig="+letterSignatureId, "_self");
}

function viewLetterData(level, enterpriseId, employeeId, letterDataId){
    window.open("/letter-data?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&ldat="+letterDataId, "_self");
}

function editLetterData(level, enterpriseId, employeeId, letterDataId) {
    window.open("/letter-data-edit?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&ldat="+letterDataId, "_self");
}

function viewLetter(level, enterpriseId, employeeId, letterId) {
    window.open("/letter?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&lid="+letterId, "_self");
}

function editLetter(level, enterpriseId, employeeId, letterId) {
    window.open("/letter-edit?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&lid="+letterId, "_self");
}

function sendLetterToMe(level, enterpriseId, employeeId, letterId, mailTo) {
    alert("Письмо будет отправлено на Ваш email " + mailTo);
    window.open("/send-letter-to-me?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&lid="+letterId, "_self");
}

function viewMailing(level, enterpriseId, employeeId, mailingId) {
    window.open("/mailing?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&mid="+mailingId, "_self");
}

function editMailing(level, enterpriseId, employeeId, mailingId) {
    window.open("/mailing-edit?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&mid="+mailingId, "_self");
}

function viewMailing2(level, enterpriseId, employeeId, mailingId) {
    window.open("/mailing-2?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&mid="+mailingId, "_self");
}

function editMailing2(level, enterpriseId, employeeId, mailingId) {
    window.open("/mailing-2-edit?l="+level+"&b="+enterpriseId+"&e="+employeeId+"&mid="+mailingId, "_self");
}

function submitUpdateMailing2Form() {
    var submitOk = 1;
    if(document.getElementById('mailingDescription').value.trim() == ''){
        submitOk = 0;
        document.getElementById('mailingDescription').style.borderColor = "red";
        document.getElementById('mailingDescription').focus();
    } else{
        document.getElementById('mailingDescription').style.borderColor = "#cccccc";
    }
    if(document.getElementById('letterId_1').value == '0'){
        submitOk = 0;
        document.getElementById('letterId_1').style.borderColor = "red";
        document.getElementById('letterId_1').focus();
    } else{
        document.getElementById('letterId_1').style.borderColor = "#cccccc";
    }
    if(document.getElementById('date_1').value == ''){
        submitOk = 0;
        document.getElementById('date_1').style.borderColor = "red";
        document.getElementById('date_1').focus();
    } else{
        document.getElementById('date_1').style.borderColor = "#cccccc";
    }
    for(i=10; i<=1; i--){
        var element = document.getElementById('letterId_'+i);
        if(element.value != '0'){
            var date = document.getElementById('date_'+i);
            if(date.value == ''){
                submitOk = 0;
                document.getElementById('date_'+i).style.borderColor = "red";
                date.focus();
            }else{
                date.style.borderColor = "#cccccc";
            }
        }
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.update_mailing_2_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}


function submitAddMailing2Form() {
    var submitOk = 1;
    if(document.getElementById('mailingDescription').value.trim() == ''){
        submitOk = 0;
        document.getElementById('mailingDescription').style.borderColor = "red";
        document.getElementById('mailingDescription').focus();
    } else{
        document.getElementById('mailingDescription').style.borderColor = "#cccccc";
    }
    if(document.getElementById('letterId_1').value == '0'){
        submitOk = 0;
        document.getElementById('letterId_1').style.borderColor = "red";
        document.getElementById('letterId_1').focus();
    } else{
        document.getElementById('letterId_1').style.borderColor = "#cccccc";
    }
    if(document.getElementById('date_1').value == ''){
        submitOk = 0;
        document.getElementById('date_1').style.borderColor = "red";
        document.getElementById('date_1').focus();
    } else{
        document.getElementById('date_1').style.borderColor = "#cccccc";
    }
    for(i=10; i<=1; i--){
        var element = document.getElementById('letterId_'+i);
        if(element.value != '0'){
            var date = document.getElementById('date_'+i);
            if(date.value == ''){
                submitOk = 0;
                document.getElementById('date_'+i).style.borderColor = "red";
                date.focus();
            }else{
                date.style.borderColor = "#cccccc";
            }
        }
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.add_mailing_2_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function viewClientMailing(level, enterpriseId, currentEmployeeId, clientId, clientAndMailingId, mailingTypeId){

    var url = "/client-mailing?l="+level;
    if(mailingTypeId == 2){
        url = "/client-mailing-2?l="+level;
    }
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(currentEmployeeId != null){
        url += ("&e="+currentEmployeeId);
    }
    url += ("&c=" + clientId);
    url += ("&cmid=" + clientAndMailingId);
    window.open(url, "_self");
}

function editClientMailing(level, enterpriseId, currentEmployeeId, clientId, clientAndMailingId, mailingTypeId){

    var url = "/client-mailing-edit?l="+level;
    if(mailingTypeId == 2){
        url = "/client-mailing-2-edit?l="+level;
    }
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(currentEmployeeId != null){
        url += ("&e="+currentEmployeeId);
    }
    url += ("&c=" + clientId);
    url += ("&cmid=" + clientAndMailingId);
    window.open(url, "_self");
}

function mailUsRedirect(level,enterpriseId,currentEmployeeId){
    var urlFrom = window.location.href;
    var url = "/feedback-new?l="+level;
    if(enterpriseId != null){
        url += ("&b="+enterpriseId);
    }
    if(currentEmployeeId != null){
        url += ("&e="+currentEmployeeId);
    }
    url += ("&m=" + urlFrom);
    window.open(url, "_self");
}

// function submitAddLetterSignatureForm() {
//     var submitOk = 1;
//     if(document.getElementById('add-letter-signature').value.trim() == ''){
//         submitOk = 0;
//         document.getElementById('add-letter-signature').style.borderColor = "red";
//         document.getElementById('add-letter-signature').focus();
//     } else{
//         document.getElementById('add-letter-signature').style.borderColor = "#cccccc";
//     }
//
//     if(submitOk){
//         // alert('Форма заполнена.');
//         document.getElementById('checkForm').disabled = true;
//         document.new_letter_signature_form.submit();
//     }else {
//         // alert("Не заполнены обязателные поля");
//     }
// }
//
// function submitUpdateLetterSignatureForm() {
//     submitAddLetterSignatureForm();
// }
//
// function submitAddLetterSubjectForm() {
//     var submitOk = 1;
//     if(document.getElementById('add-letter-subject').value.trim() == ''){
//         submitOk = 0;
//         document.getElementById('add-letter-subject').style.borderColor = "red";
//         document.getElementById('add-letter-subject').focus();
//     } else{
//         document.getElementById('add-letter-subject').style.borderColor = "#cccccc";
//     }
//
//     if(submitOk){
//         // alert('Форма заполнена.');
//         document.getElementById('checkForm').disabled = true;
//         document.new_letter_subject_form.submit();
//     }else {
//         // alert("Не заполнены обязателные поля");
//     }
// }
//
// function submitUpdateLetterSubjectForm() {
//     submitAddLetterSubjectForm();
// }
//
// function submitAddLetterDataForm() {
//     var submitOk = 1;
//     if(document.getElementById('add-letter-data').value.trim() == ''){
//         submitOk = 0;
//         document.getElementById('add-letter-data').style.borderColor = "red";
//         document.getElementById('add-letter-data').focus();
//     } else{
//         document.getElementById('add-letter-data').style.borderColor = "#cccccc";
//     }
//
//     if(submitOk){
//         // alert('Форма заполнена.');
//         document.getElementById('checkForm').disabled = true;
//         document.new_letter_data_form.submit();
//     }else {
//         // alert("Не заполнены обязателные поля");
//     }
// }
//
// function submitUpdateLetterDataForm() {
//     submitAddLetterDataForm();
// }
//
// function submitAddLetterForm() {
//     var submitOk = 1;
//     if(document.getElementById('description').value.trim() == ''){
//         submitOk = 0;
//         document.getElementById('description').style.borderColor = "red";
//         document.getElementById('description').focus();
//     } else{
//         document.getElementById('description').style.borderColor = "#cccccc";
//     }
//     var subjectId = document.getElementById('letterSubjectId').value;
//     var dataId = document.getElementById('letterDataId').value;
//     var signatureId = document.getElementById('letterSignatureId').value;
//
//     if(!isNaN(parseFloat(subjectId)) && !isNaN(subjectId - 0)){
//         document.getElementById('letterSubjectId').style.borderColor = "#cccccc";
//     } else{
//         submitOk = 0;
//         document.getElementById('letterSubjectId').style.borderColor = "red";
//         document.getElementById('letterSubjectId').focus();
//     }
//
//     if(!isNaN(parseFloat(dataId)) && !isNaN(dataId - 0)){
//         document.getElementById('letterDataId').style.borderColor = "#cccccc";
//     } else{
//         submitOk = 0;
//         document.getElementById('letterDataId').style.borderColor = "red";
//         document.getElementById('letterDataId').focus();
//     }
//
//     if(!isNaN(parseFloat(signatureId)) && !isNaN(signatureId - 0)){
//         document.getElementById('letterSignatureId').style.borderColor = "#cccccc";
//     } else{
//         submitOk = 0;
//         document.getElementById('letterSignatureId').style.borderColor = "red";
//         document.getElementById('letterSignatureId').focus();
//     }
//
//     if(submitOk){
//         // alert('Форма заполнена.');
//         document.getElementById('checkForm').disabled = true;
//         document.new_letter_form.submit();
//     }else {
//         // alert("Не заполнены обязателные поля");
//     }
// }
//
// function submitUpdateLetterForm() {
//     submitAddLetterForm();
// }
//
// function submitAddMailingForm() {
//     var submitOk = 1;
//     if(document.getElementById('add-mailing-form-comment').value.trim() == ''){
//         submitOk = 0;
//         document.getElementById('add-mailing-form-comment').style.borderColor = "red";
//         document.getElementById('add-mailing-form-comment').focus();
//     } else{
//         document.getElementById('add-mailing-form-comment').style.borderColor = "#cccccc";
//     }
//     if(document.getElementById('day_1').value.trim() == ''){
//         submitOk = 0;
//         document.getElementById('day_1').style.borderColor = "red";
//         document.getElementById('day_1').focus();
//     } else{
//         document.getElementById('day_1').style.borderColor = "#cccccc";
//     }
//     var letterId_1 = document.getElementById('select_letter_1').value;
//     var letterId_2 = document.getElementById('select_letter_2').value;
//     var letterId_3 = document.getElementById('select_letter_3').value;
//     var letterId_4 = document.getElementById('select_letter_4').value;
//     var letterId_5 = document.getElementById('select_letter_5').value;
//     var letterId_6 = document.getElementById('select_letter_6').value;
//     var letterId_7 = document.getElementById('select_letter_7').value;
//     var letterId_8 = document.getElementById('select_letter_8').value;
//     var letterId_9 = document.getElementById('select_letter_9').value;
//     var letterId_10 = document.getElementById('select_letter_10').value;
//
//     // alert('letterId_2 = ' + letterId_2 + '; !isNaN(parseFloat(letterId_2)) = ' + !isNaN(parseFloat(letterId_2))
//     // + '; !isNaN(letterId_2 - 0) = ' + !isNaN(letterId_2 - 0));
//
//     if(!isNaN(parseFloat(letterId_1)) && !isNaN(letterId_1 - 0)){
//         document.getElementById('select_letter_1').style.borderColor = "#cccccc";
//     } else{
//         submitOk = 0;
//         document.getElementById('select_letter_1').style.borderColor = "red";
//         document.getElementById('select_letter_1').focus();
//     }
//
//     if(!(!isNaN(parseFloat(letterId_2)) && !isNaN(letterId_2 - 0))){
//         var x = document.getElementById("select_letter_2");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//
//     if(!(!isNaN(parseFloat(letterId_3)) && !isNaN(letterId_3 - 0))){
//         var x = document.getElementById("select_letter_3");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//     if(!(!isNaN(parseFloat(letterId_4)) && !isNaN(letterId_4 - 0))){
//         var x = document.getElementById("select_letter_4");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//     if(!(!isNaN(parseFloat(letterId_5)) && !isNaN(letterId_5 - 0))){
//         var x = document.getElementById("select_letter_5");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//     if(!(!isNaN(parseFloat(letterId_6)) && !isNaN(letterId_6 - 0))){
//         var x = document.getElementById("select_letter_6");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//     if(!(!isNaN(parseFloat(letterId_7)) && !isNaN(letterId_7 - 0))){
//         var x = document.getElementById("select_letter_7");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//     if(!(!isNaN(parseFloat(letterId_8)) && !isNaN(letterId_8 - 0))){
//         var x = document.getElementById("select_letter_8");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//     if(!(!isNaN(parseFloat(letterId_9)) && !isNaN(letterId_9 - 0))){
//         var x = document.getElementById("select_letter_9");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//     if(!(!isNaN(parseFloat(letterId_10)) && !isNaN(letterId_10 - 0))){
//         var x = document.getElementById("select_letter_10");
//         var option = document.createElement("option");
//         option.text = "0";
//         option.selected = true;
//         x.add(option, x[0]);
//     }
//
//     if(document.getElementById('day_2').value.trim() == '') {
//         document.getElementById('day_2').value = "0";
//     }
//     if(document.getElementById('day_3').value.trim() == '') {
//         document.getElementById('day_3').value = "0";
//     }
//     if(document.getElementById('day_4').value.trim() == '') {
//         document.getElementById('day_4').value = "0";
//     }
//     if(document.getElementById('day_5').value.trim() == '') {
//         document.getElementById('day_5').value = "0";
//     }
//     if(document.getElementById('day_6').value.trim() == '') {
//         document.getElementById('day_6').value = "0";
//     }
//     if(document.getElementById('day_7').value.trim() == '') {
//         document.getElementById('day_7').value = "0";
//     }
//     if(document.getElementById('day_8').value.trim() == '') {
//         document.getElementById('day_8').value = "0";
//     }
//     if(document.getElementById('day_9').value.trim() == '') {
//         document.getElementById('day_9').value = "0";
//     }
//     if(document.getElementById('day_10').value.trim() == '') {
//         document.getElementById('day_10').value = "0";
//     }
//
//     if(submitOk){
//         // alert('Форма заполнена.');
//         document.getElementById('checkForm').disabled = true;
//         document.new_mailing_form.submit();
//     }else {
//         // alert("Не заполнены обязателные поля");
//     }
// }
//
// function submitUpdateMailingForm() {
//     submitAddMailingForm();
// }

function submitAddClientMailingForm() {
    var submitOk = 1;
    var mailingId = document.getElementById('mailingId').value;

    if(!isNaN(parseFloat(mailingId)) && !isNaN(mailingId - 0)){
        document.getElementById('mailingId').style.borderColor = "#cccccc";
    } else{
        submitOk = 0;
        document.getElementById('mailingId').style.borderColor = "red";
        document.getElementById('mailingId').focus();
    }

    // alert(document.getElementById('checkBoxStartNow').checked);
    if(document.getElementById('checkBoxStartNow').checked) {
        // alert('document.getElementById(\'dateBack_' + employeeId + '_' + i + ').value' + document.getElementById('dateBack_' + employeeId + '_' + i).value);
        document.getElementById('dateStart').value = new Date().toISOString().substring(0, 10);
    }

    if(document.getElementById('dateStart').value == ''){
        submitOk = 0;
        document.getElementById('dateStart').style.borderColor = "red";
        document.getElementById('dateStart').focus();
    } else{
        document.getElementById('dateStart').style.borderColor = "#cccccc";
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.new_client_mailing_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function submitUpdateClientMailingForm() {
    var submitOk = 1;

    // alert(document.getElementById('checkBoxStartNow').checked);
    if(document.getElementById('checkBoxStartNow').checked
        && !document.getElementById('dateStart').readOnly) {
        // alert('document.getElementById(\'dateBack_' + employeeId + '_' + i + ').value' + document.getElementById('dateBack_' + employeeId + '_' + i).value);
        document.getElementById('dateStart').value = new Date().toISOString().substring(0, 10);
    }

    if(document.getElementById('dateStart').value == ''){
        submitOk = 0;
        document.getElementById('dateStart').style.borderColor = "red";
        document.getElementById('dateStart').focus();
    } else{
        document.getElementById('dateStart').style.borderColor = "#cccccc";
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.getElementById('checkForm').disabled = true;
        document.new_client_mailing_form.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }
}

function submitSearchGlobalForm(minLength) {
    var submitOk = 1;
    var query = document.getElementById("searchQuery").value;
    if(query == null || query.trim().length < minLength){
        submitOk = 0;
        alert('Для поиска введите не менее 3 символов.');
    }

    if(submitOk){
        // alert('Форма заполнена.');
        document.searchGlobalForm.submit();
    }else {
        // alert("Не заполнены обязателные поля");
    }

}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function deleteCookie(cname) {
    setCookie(cname, "", {
        'max-age': -1
    })
}

function setToggleMenuLeftCookie(){
    var pageTopClass = document.getElementById('page-top').className;
    if(pageTopClass != null && pageTopClass.includes('sidebar-toggled')){
        deleteCookie('ExpenseTrackerToggleMenuLeft');
    }else{
        setCookie('ExpenseTrackerToggleMenuLeft','1',365)
    }
    // alert('pageTopClass : ' + pageTopClass);
    // var accordionSidebarClass = document.getElementById('accordionSidebar').className;
    // alert('accordionSidebarClass : ' + accordionSidebarClass);
    // setCookie('ToggleMenuLeft', 'toggle', 365);
}

window.onload = function () {
    // alert('|' + new Date() + '\n|' + new Date().toISOString() + '\n|' + new Date().toISOString().substring(0, 10) + '|');
    if(document.getElementById('dateCreate')) {
        document.getElementById('dateCreate').value = new Date().toISOString().substring(0, 10);
    }
    if(document.getElementsByClassName('dateToday').length > 0) {
        var options = {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        };
        var elements = document.getElementsByClassName('dateToday');
        for(i = 0; i < elements.length; i++)
        {
            elements[i].innerHTML = new Date().toLocaleString("ru", options);
            // elements[i].innerHTML = new Date().toISOString().substring(0, 10);
        }
    }
    if(document.getElementById('video_about_ukrcrm')) {
        var width = document.getElementById('video_about_ukrcrm').offsetWidth;
        document.getElementById('video_about_ukrcrm').style.height = width / 21 * 9;
    }


    // alert("Загрузился документ");
    // if(document.getElementById('get_pay_form_liqpay')) {
    //     submitGetPayForm();
    // }
    // if(document.getElementById('customCheck')) {
    //     var customCheck = document.getElementById('customCheck');
    //     var loginButton = document.getElementById('login-button');
    //     if(customCheck.checked){
    //         loginButton.disabled = false;
    //     }else{
    //         loginButton.disabled = true;
    //     }
    // }

}
