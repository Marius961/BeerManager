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
    if (!elems["username"].value) {
        hasErrors = true;
        showError(elems["username"].parentNode, 'Input username');
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
                alert(nameIsUsed)
                if (nameIsUsed) {
                    hasErrors = true;
                    showError(elems["username"].parentNode, 'Username already used');
                }
            }
        });
    }

    resetError(elems["fullName"].parentNode);
    if (!elems["fullName"].value) {
        hasErrors = true;
        showError(elems["fullName"].parentNode, 'Input full name');
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












