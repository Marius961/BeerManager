function removeOrder(orderId) {
    let status = confirm('Are you sure?');
    if (status) {
        location.href='/orders/remove/' + orderId;
        document.getElementById('orderBox' + orderId).remove();
    }
}

function validateAndSend(form) {
    let url = '';
    let data = "";
    let  hasErrors = false;
    let elems = form.elements;

    $("#username").click(function () {
        resetError(elems["username"].parentNode);
    });
    if (!elems["username"].value || elems["username"].value.length <=3) {
        hasErrors = true;
        showError(elems["username"].parentNode, 'Username must be longer than 3 symbols');
    }
    if (!hasErrors) {
        url = '/username-check/' + elems["username"].value + '';
        let nameIsUsed;
        sendCheckRequest(url);
        data = window.data1;
        nameIsUsed = data.nameStatus;
        if (nameIsUsed) {
            hasErrors = true;
            showError(elems["username"].parentNode, 'Username already used');
        }
    }
    $("#fullName").click(function () {
        resetError(elems["fullName"].parentNode);
    });
    if (!elems["fullName"].value || elems["fullName"].value.length <=3) {
        hasErrors = true;
        showError(elems["fullName"].parentNode, 'Full name must be longer than 3 symbols');
    }

    $("#companyName").click(function () {
        resetError(elems["companyName"].parentNode);
    });
    if (!elems["companyName"].value || elems["username"].value.length <=1) {
        hasErrors = true;
        showError(elems["companyName"].parentNode, 'Company name must be longer than 1 symbol');
    }

    $("#companyAddress").click(function () {
        resetError(elems["companyAddress"].parentNode);
    });
    if (!elems["companyAddress"].value || elems["companyAddress"].value.length <=6) {
        hasErrors = true;
        showError(elems["companyAddress"].parentNode, 'Company address must be longer than 6 symbols');
    }

    $("#email").click(function () {
        resetError(elems["email"].parentNode);
    });
    let emailError = false;
    if (!elems["email"].value || elems["email"].value.length <=3) {
        hasErrors = true;
        emailError = true;
        showError(elems["email"].parentNode, 'Email must be longer than 3 symbols');
    }
    if (!emailError) {
        let emailIsUsed;
        url = '/email-check';
        let emailObj = {
            "str" : elems["email"].value
        };
        sendCheckObject(url, emailObj);
        data = window.data1;
        // noinspection JSUnresolvedVariable
        emailIsUsed = data.emailStatus;
        if (emailIsUsed) {
            hasErrors = true;
            showError(elems["email"].parentNode, 'This email is already in use');
        }
    }

    $("#telNumber").click(function () {
        resetError(elems["telNumber"].parentNode);
    });
    let telNumError = false;
    if (!elems["telNumber"].value || elems["telNumber"].value.trim().length !== 10) {
        hasErrors = true;
        telNumError = true;
        showError(elems["telNumber"].parentNode, 'Phone number must consist of 10 numbers');
    }
    if (!telNumError) {
        let telNumIsUsed;
        let telNum = {
            "str" : elems["telNumber"].value.trim()
        };
        url = '/tel-check';
        sendCheckObject(url, telNum);
        data = window.data1;
        // noinspection JSUnresolvedVariable
        telNumIsUsed = data.telStatus;
        if (telNumIsUsed) {
            hasErrors = true;
            showError(elems["telNumber"].parentNode, 'This phone number is already in use');
        }
    }

    $("#password").click(function () {
        resetError(elems["password"].parentNode);
    });
    let passError = false;
    if (!elems["password"].value || elems["password"].value.length < 6 ) {
        hasErrors = true;
        passError = true;
        showError(elems["password"].parentNode, 'Password must be longer than 6 symbols');
    }

    $("#passwordConfirm").click(function () {
        resetError(elems["passwordConfirm"].parentNode);
    });
    if (!passError && elems["passwordConfirm"].value !== elems["password"].value) {
        hasErrors = true;
        showError(elems["passwordConfirm"].parentNode, 'Passwords do not match');
    }
    if (!hasErrors) {
        sendUserForm(processRegForm());
    }
}

function sendCheckRequest(url) {
    $.ajax ({
        url: String(url),
        type: "GET",
        dataType: "json",
        data: ({}),
        async : false,
        contentType: 'application/json',
        success: function (data) {
            setData(data);
        }
    });
}

function sendCheckObject(url, object) {
    $.ajax ({
        type : "POST",
        url : url,
        dataType: "json",
        async : false,
        contentType : 'application/json',
        data : JSON.stringify(object),
        success : function (data) {
            setData(data);
        }
    });
}

function setData(data) {
    window.data1 = data;
}

function showError(container, errorMessage) {
    if (container.className !== 'error') {
        container.className = 'error';
        let msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
    }
}

function resetError(container) {
    container.className = '';
    if (container.lastChild.className === "error-message") {
        container.removeChild(container.lastChild);
    }
}

function sendUserForm(formObject) {
    $.ajax ({
        type : "POST",
        url : '/user',
        dataType: "json",
        contentType : 'application/json',
        async : false,
        data : JSON.stringify(formObject),
        complete : (function () {
            location.href='/';
        })
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

function validateOrderForm() {
    let errorCounter = 0;
    $(".left").each(function (index, element) {
        resetSectionError();
        if ((element.value % 5) !== 0) {
            errorCounter++;
            showSectionError("You can add only for 5 liters");
            return false;
        }
    });
    if (errorCounter === 0) {
        $("#orderForm").submit();
    }
}

function showSectionError(error) {
    $(".content-div").append("<span class='error-message'>"+ error + "</span>")
}

function resetSectionError() {
    $(".error-message").remove();
}







