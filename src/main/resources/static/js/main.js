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
            alert("Password mismatch.");
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
        document.getElementById('checkForm').disabled = true;
        document.registration_form.submit();
    }else {
    }
}
function viewCustomUser(userId) {
    window.open("/custom-user?cuid="+userId, "_self");
}
function editCustomUser(userId) {
    window.open("/custom-user-edit?cuid="+userId, "_self");
}
function editProfile() {
    window.open("/profile-edit", "_self");
}
function viewExpense(expenseId) {
    window.open("/expense?expid="+expenseId, "_self");
}
function editExpense(expenseId) {
    window.open("/expense-edit?expid="+expenseId, "_self");
}
function submitAddCustomUserForm(){
    var submitOk = 1;

    if(document.getElementById('username').value.trim() == ''){
        submitOk = 0;
        document.getElementById('username').style.borderColor = "#e83e8c";
        document.getElementById('username').focus();
    }else{
        document.getElementById('username').style.borderColor = "#cccccc";
    }

    if(document.getElementById('fName').value.trim() == ''){
        submitOk = 0;
        document.getElementById('fName').style.borderColor = "#e83e8c";
        document.getElementById('fName').focus();
    }else{
        document.getElementById('fName').style.borderColor = "#cccccc";
    }

    // AJAX - check if user exist
    // var userExist = false;
    var username = document.getElementById('username').value;
    if(username == 'admin'){
        submitOk = 0;
        alert('This user already exists. Try again.');
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
            alert("Password mismatch");
            document.getElementById('password1').style.borderColor = "#e83e8c";
            document.getElementById('password2').style.borderColor = "#e83e8c";
        } else{
            document.getElementById('password1').style.borderColor = "#cccccc";
            document.getElementById('password2').style.borderColor = "#cccccc";
        }
    }

    if(submitOk){
        document.getElementById('checkForm').disabled = true;
        document.add_custom_user_form.submit();
    }else {
    }
}
function submitEditCustomUserForm(){
    var submitOk = 1;

    if(document.getElementById('fName').value.trim() == ''){
        submitOk = 0;
        document.getElementById('fName').style.borderColor = "#e83e8c";
        document.getElementById('fName').focus();
    }else{
        document.getElementById('fName').style.borderColor = "#cccccc";
    }

    if(document.getElementById('password1').value.trim() == ''
        && document.getElementById('password2').value.trim() == ''){
        submitOk = 1;
    }else if(document.getElementById('password1').value.trim() == ''){
        submitOk = 0;
        document.getElementById('password1').style.borderColor = "#e83e8c";
        document.getElementById('password1').focus();
    }else if(document.getElementById('password2').value.trim() == ''){
        submitOk = 0;
        document.getElementById('password2').style.borderColor = "#e83e8c";
        document.getElementById('password2').focus();
    }else{
        if(document.getElementById('password1').value !=
            document.getElementById('password2').value){
            submitOk = 0;
            alert("Password mismatch");
            document.getElementById('password1').style.borderColor = "#e83e8c";
            document.getElementById('password2').style.borderColor = "#e83e8c";
        } else{
            document.getElementById('password1').style.borderColor = "#cccccc";
            document.getElementById('password2').style.borderColor = "#cccccc";
        }
    }

    if(submitOk){
        document.getElementById('checkForm').disabled = true;
        document.update_custom_user_form.submit();
    }else {
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
}

window.onload = function () {

}
