let usernameLengthMessage = 'Логін повинен бути довшим ніж 3 символи';
let usernameInUseMessage = 'Цей логін вже у використанні';
let fullNameLengthMessage = 'П.І.Б. повинно складатись як мінімум з 6 символів';
let companyNameLengthMessage = 'Назва компанії повинна складатись як мінімум з 2 символів';
let companyAddressLengthMessage = 'Адреса компанії повинна складатись як мінімум з 6 символів';
let emailLengthMessage = 'Довжина ел. адреси повинна складатись більше ніж з 4 символів';
let emailUsedMessage = 'Ця електронна адреса вже використовується';
let phoneNumberLengthMessage = 'Номер мобільного телефону повинен складатись з 10 символів';
let phoneNumberInUseMessage = 'Цей номер телефону вже використовується';
let passwordLengthMessage = 'пароль повинен складатись як мінімум з 8 символів';
let passwordsNotMatchMessage = 'Паролі не співпадають';

let litersLimitMessage = 'Ви можете додавати тільки з кроком у 5 літрів';
let productSelectionMessage = 'Ви повинні вибрати як мінімум 1 продукт';
let timeLimitMessage = 'Ви можете створити замовлення тільки до 9:00 дня виконання замовлення';
let orderExecutionDataMessage = 'Дата виконання замовлення повинна бути більшою від поточної дати';


function validateAndSend(form) {
    let url = '';
    let data = "";
    let  hasErrors = false;
    let elems = form.elements;

    $("#username").on('input', function () {
        resetError(elems["username"].parentNode);
    });
    if (!elems["username"].value || elems["username"].value.length <=3) {
        hasErrors = true;
        showError(elems["username"].parentNode, usernameLengthMessage);
    }
    if (!hasErrors) {
        url = '/username-check/' + elems["username"].value + '';
        let nameIsUsed;
        sendCheckRequest(url);
        data = window.data1;
        nameIsUsed = data.nameStatus;
        if (nameIsUsed) {
            hasErrors = true;
            showError(elems["username"].parentNode, usernameInUseMessage);
        }
    }
    $("#fullName").on('input', function () {
        resetError(elems["fullName"].parentNode);
    });
    if (!elems["fullName"].value || elems["fullName"].value.length <6) {
        hasErrors = true;
        showError(elems["fullName"].parentNode, fullNameLengthMessage);
    }

    $("#companyName").on('input', function () {
        resetError(elems["companyName"].parentNode);
    });
    if (!elems["companyName"].value || elems["companyName"].value.length <2) {
        hasErrors = true;
        showError(elems["companyName"].parentNode, companyNameLengthMessage);
    }

    $("#companyAddress").on('input', function () {
        resetError(elems["companyAddress"].parentNode);
    });
    if (!elems["companyAddress"].value || elems["companyAddress"].value.length <=6) {
        hasErrors = true;
        showError(elems["companyAddress"].parentNode, companyAddressLengthMessage);
    }

    $("#email").on('input', function () {
        resetError(elems["email"].parentNode);
    });
    let emailError = false;
    if (!elems["email"].value || elems["email"].value.length <=3) {
        hasErrors = true;
        emailError = true;
        showError(elems["email"].parentNode, emailLengthMessage);
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
            showError(elems["email"].parentNode, emailUsedMessage);
        }
    }

    $("#telNumber").on('input', function () {
        resetError(elems["telNumber"].parentNode);
    });
    let telNumError = false;
    if (!elems["telNumber"].value || elems["telNumber"].value.trim().length !== 10) {
        hasErrors = true;
        telNumError = true;
        showError(elems["telNumber"].parentNode, phoneNumberLengthMessage);
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
            showError(elems["telNumber"].parentNode, phoneNumberInUseMessage);
        }
    }

    $("#password").on('input', function () {
        resetError(elems["password"].parentNode);
    });
    let passError = false;
    if (!elems["password"].value || elems["password"].value.length < 8 ) {
        hasErrors = true;
        passError = true;
        showError(elems["password"].parentNode, passwordLengthMessage);
    }

    $("#passwordConfirm").on('input', function () {
        resetError(elems["passwordConfirm"].parentNode);
    });
    if (!passError && elems["passwordConfirm"].value !== elems["password"].value) {
        hasErrors = true;
        showError(elems["passwordConfirm"].parentNode, passwordsNotMatchMessage);
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
    if (container.lastChild.className !== "error-message") {
        let msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
        $(msgElem).slideDown(300);
    }
}

function resetError(container) {
    if (container.lastChild.className === "error-message") {
        $(container.lastChild).slideToggle(300);
        setTimeout(function () {
            $(container.lastChild).remove();
        }, 300);
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
    let emptyFieldsCount = 0;
    let fieldsCount = 0;
    resetSectionError();
    $(".left").each(function (index, element) {
        if ((+element.value % 5) !== 0) {
            if (errorCounter < 1) {
                showSectionError(litersLimitMessage);
            }
            errorCounter++;
        }
        if (+element.value === 0) {
            emptyFieldsCount++;
        }
        fieldsCount++;
    });
    if (fieldsCount === emptyFieldsCount) {
        errorCounter++;
        showSectionError(productSelectionMessage);
    }
    let dateError = false;
    let dateField = document.getElementById('execDate');
    resetError(dateField.parentNode);
    if (!dateField.value) {
        errorCounter++;
        dateError = true;
        showError(dateField.parentNode, 'Select date');
    }
    if (!dateError) {
        let orderDate = new Date(document.getElementById('execDate').value);
        let currentDate = new Date();
        let currentHours = currentDate.getHours();
        currentDate.setHours(0 ,0 , 0 , 0);
        orderDate.setHours(0 ,0 , 0 , 0);
        if (currentHours > 8 && +currentDate === +orderDate) {
            errorCounter++;
            dateError = true;
            showError(dateField.parentNode, timeLimitMessage);
        }
        if (!dateError && +currentDate > +orderDate) {
            errorCounter++;
            showError(dateField.parentNode, orderExecutionDataMessage);
        }
    }
    if (errorCounter === 0) {
        $("#orderForm").submit();
    }
}

function showSectionError(error) {
    $(".content-div").append("<p class='error-message'>"+ error + "</p>");
}

function resetSectionError() {
    $(".error-message").remove();
}







