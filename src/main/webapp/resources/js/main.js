function removeOrder(orderId) {
    let status = confirm('Are you sure?');
    if (status) {
        location.href='/orders/remove/' + orderId;
        document.getElementById('orderBox' + orderId).remove();
    }
}

function validateAndSend(form) {
    let elems = form.elements;
    let hasErrors = false;
    resetError(elems["username"].parentNode);
    if (!elems["username"].value || elems["username"].value.length <=3) {
        hasErrors = true;
        showError(elems["username"].parentNode, 'Username must be longer than 3 symbols');
    }
    if (!hasErrors) {
        let nameIsUsed = false;
        let url = '/username-check/' + elems["username"].value + '';
        $.ajax({
            url: String(url),
            type: "GET",
            dataType: "json",
            data: ({}),
            contentType: 'application/json',
            success: function (data) {
                nameIsUsed = data.nameStatus;
                console.log('username used: ' + nameIsUsed);
                if (nameIsUsed) {
                    hasErrors = true;
                    showError(elems["username"].parentNode, 'Username already used');
                }
            }
        });
    }

    resetError(elems["fullName"].parentNode);
    if (!elems["fullName"].value || elems["fullName"].value.length <=3) {
        hasErrors = true;
        showError(elems["fullName"].parentNode, 'Full name must be longer than 3 symbols');
    }
    resetError(elems["companyName"].parentNode);
    if (!elems["companyName"].value || elems["username"].value.length <=1) {
        hasErrors = true;
        showError(elems["companyName"].parentNode, 'Company name must be longer than 1 symbol');
    }
    resetError(elems["companyAddress"].parentNode);
    if (!elems["companyAddress"].value || elems["companyAddress"].value.length <=6) {
        hasErrors = true;
        showError(elems["companyAddress"].parentNode, 'Company address must be longer than 6 symbols');
    }
    let emailError = false;
    resetError(elems["email"].parentNode);
    if (!elems["email"].value || elems["email"].value.length <=3) {
        hasErrors = true;
        emailError = true;
        showError(elems["email"].parentNode, 'Email must be longer than 3 symbols');
    }
    if (!emailError) {
        let emailObj = {
            "str" : elems["email"].value
        };
        let emailIsUsed = false;
        $.ajax ({
            type : "POST",
            url : '/email-check',
            dataType: "json",
            contentType : 'application/json',
            data : JSON.stringify(emailObj),
            success : function (data) {
                emailIsUsed = data.emailStatus;
                if (emailIsUsed) {
                    hasErrors = true;
                    showError(elems["email"].parentNode, 'This email is already in use');
                }
            }
        });
    }
    let telNumError = false;
    resetError(elems["telNumber"].parentNode);
    if (!elems["telNumber"].value || elems["telNumber"].value.trim().length !== 10) {
        hasErrors = true;
        telNumError = true;
        showError(elems["telNumber"].parentNode, 'Phone number must consist of 10 numbers');
    }
    if (!telNumError) {
        let telNum = {
            "str" : elems["telNumber"].value.trim()
        };
        let telNumIsUsed = false;
        $.ajax ({
            type : "POST",
            url : '/tel-check',
            dataType: "json",
            contentType : 'application/json',
            data : JSON.stringify(telNum),
            success : function (data) {
                telNumIsUsed = data.telStatus;
                if (telNumIsUsed) {
                    hasErrors = true;
                    showError(elems["telNumber"].parentNode, 'This phone number is already in use');
                }
            }
        });
    }
    if (!hasErrors) {
        sendUserForm();
    }
}



function showError(container, errorMessage) {
    container.className = 'error';
    let msgElem = document.createElement('span');
    msgElem.className = "error-message";
    msgElem.innerHTML = errorMessage;
    container.appendChild(msgElem);
}

function resetError(container) {
    container.className = '';
    if (container.lastChild.className === "error-message") {
        container.removeChild(container.lastChild);
    }
}

function sendUserForm() {
    $.ajax ({
        type : "POST",
        url : '/user',
        dataType: "json",
        contentType : 'application/json',
        data : JSON.stringify(processRegForm()),
        complete : location.href='/'
    });
}

function processRegForm() {
    return {
        "id": 0,
        "username": document.getElementById('username').value,
        "enabled": 1,
        "fullName": document.getElementById('fullName').value,
        "companyName": document.getElementById('companyName').value,
        "companyAddress": document.getElementById('companyAddress').value,
        "email": document.getElementById('email').value,
        "telNumber": document.getElementById('telNumber').value,
        "password": document.getElementById('password').value,
        "groupId": 1
    };
}
