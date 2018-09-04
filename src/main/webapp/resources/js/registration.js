let usernameLengthMessage = 'Логін повинен бути довшим ніж 3 символи';
let usernameInUseMessage = 'Цей логін вже у використанні';
let fullNameLengthMessage = 'П.І.Б. повинно складатись як мінімум з 6 символів';
let companyNameLengthMessage = 'Назва компанії повинна складатись як мінімум з 2 символів';
let companyAddressLengthMessage = 'Адреса компанії повинна складатись як мінімум з 6 символів';
let emailLengthMessage = 'Довжина ел. адреси повинна складатись більше ніж з 4 символів';
let emailUsedMessage = 'Ця електронна адреса вже використовується';
let phoneNumberLengthMessage = 'Номер мобільного телефону повинен складатись з 10 символів';
let phoneNumberInUseMessage = 'Цей номер телефону вже використовується';
let passwordLengthMessage = 'пароль повинен складатись мінімум з 8 символів';
let passwordsNotMatchMessage = 'Паролі не співпадають';

let litersLimitMessage = 'Ви можете додавати тільки з кроком у 5 літрів';
let productSelectionMessage = 'Ви повинні вибрати як мінімум 1 продукт';
let timeLimitMessage = 'Ви можете створити замовлення тільки до 9:00 дня виконання замовлення';
let orderExecutionDataMessage = 'Дата виконання замовлення повинна бути більшою від поточної дати';
let emptyDateError = "Будь ласка виберіть дату виконання замовлення";

$(":input").on('input', function () {
    resetError($(this)[0].parentNode);
});

$(document).ready(function () {
    let now = new Date();
    let day = ("0" + now.getDate()).slice(-2);
    let month = ("0" + (now.getMonth() + 1)).slice(-2);
    let today = now.getFullYear()+"-"+(month)+"-"+(day) ;
   $("#execDate").val(today);
});
function isRegistrationFormValid(form) {
    let url = '';
    let data = "";
    let  errors = 0;
    let elems = form.elements;
    if (!elems["username"].value || elems["username"].value.length <=3) {
        errors++;
        showError(elems["username"].parentNode, usernameLengthMessage);
    }
    if (errors === 0) {
        url = '/username-check/' + elems["username"].value + '';
        let nameIsUsed;
        sendCheckRequest(url);
        data = window.data1;
        nameIsUsed = data.nameStatus;
        if (nameIsUsed) {
            errors++;
            showError(elems["username"].parentNode, usernameInUseMessage);
        }
    }
    if (!elems["fullName"].value || elems["fullName"].value.length <6) {
        errors++;
        showError(elems["fullName"].parentNode, fullNameLengthMessage);
    }
    if (!elems["companyName"].value || elems["companyName"].value.length <2) {
        errors++;
        showError(elems["companyName"].parentNode, companyNameLengthMessage);
    }
    if (!elems["companyAddress"].value || elems["companyAddress"].value.length <=6) {
        errors++;
        showError(elems["companyAddress"].parentNode, companyAddressLengthMessage);
    }
    let emailError = false;
    if (!elems["email"].value || elems["email"].value.length <=3) {
        errors++;
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
            errors++;
            showError(elems["email"].parentNode, emailUsedMessage);
        }
    }
    let telNumError = false;
    if (!elems["telNumber"].value || elems["telNumber"].value.trim().length !== 10) {
        errors++;
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
            errors++;
            showError(elems["telNumber"].parentNode, phoneNumberInUseMessage);
        }
    }
    let passError = false;
    if (!elems["password"].value || elems["password"].value.length < 8 ) {
        errors++;
        passError = true;
        showError(elems["password"].parentNode, passwordLengthMessage);
    }
    if (!passError && elems["passwordConfirm"].value !== elems["password"].value) {
        errors++;
        showError(elems["passwordConfirm"].parentNode, passwordsNotMatchMessage);
    }
    if (errors === 0) {
        sendUserForm(processRegForm());
    }
    return false;
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
    let error = $(container).find(".error-message");
    if (!$(error)[0]) {
        let msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
        $(msgElem).slideDown(300);
    }
}

function resetError(container) {
    let errorMessage =  $(container).find(".error-message");
    errorMessage.slideToggle(300);
    setTimeout(function () {
        errorMessage.remove();
    }, 300);
}

function sendUserForm(formObject) {
    $.ajax ({
        type : "POST",
        url : '/user',
        contentType : 'application/json',
        async : false,
        data : JSON.stringify(formObject),
        error: function(){
            swal({
                title: "Сталась помилка",
                icon: "error",
                button: "OK",
            });
        },
        success : function () {
            location.href='/';
        }
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

function IsOrderFormValid() {
    let errorCounter = 0;
    let emptyFieldsCount = 0;
    let fieldsCount = 0;
    resetSectionError();
    $('input[type = "number"]').each(function (index, element) {
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
    let dateError;
    let dateField = $("#execDate")[0];
    resetError(dateField.parentNode);
    if (!dateField.value) {
        errorCounter++;
        dateError = true;
        showError(dateField.parentNode, emptyDateError);
    }
    if (!dateError) {
        let orderDate = new Date($("#execDate")[0].value);
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
        return true;
    }
    return false;
}

function showSectionError(error) {
    let errorContainer = $("#errorContainer")[0];
    if(errorContainer === undefined) {
        $(".row").eq(0).prepend("<div class='col-12 alert alert-danger' role='alert' id='errorContainer'></div>")
    }
    $("#errorContainer").append(error);
}

function resetSectionError() {
    $(".error-message").remove();
    $("#errorContainer").remove();
}







