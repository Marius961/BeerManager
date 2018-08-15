function validateForm(form) {
    let formErrors = 0;
    let elements = form.elements;
    let usernameField = elements["username"];
    if (!usernameField.value || usernameField.value.length <= 3) {
        formErrors++;
        addLoginError();
    }
    let passwordField = elements["password"];
    if (!passwordField.value || passwordField.value.length < 8) {
        formErrors++;
        addLoginError();
    }

    if (formErrors === 0) {
        return true;
    }
    return false;
}

function addLoginError() {
    let errorMessage = $(".alert");
    if (!$(errorMessage)[0]) {
        $(".p-3").prepend("<div class='row no-gutters'>\n" +
            "   <div class='col'>\n" +
            "       <div class='alert alert-danger' role='alert'>\n" +
            "           Неправильний логін або пароль\n" +
            "       </div>\n" +
            "   </div>\n" +
            "</div>");
    }
}