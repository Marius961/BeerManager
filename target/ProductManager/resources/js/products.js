let mainDiv = $("#productsContainer");
let allTabs = $(".tab");
let currentTab = '';
const searchTab = $("#search")[0];
let allProducts;
let lock = false;

const defaultBorderStyle = '0.8mm solid #ededed';
const selectedBorderStyle = '0.8mm solid black';

const blockBtnLabel = 'Заблокувати';
const unblockBtnLabel = 'Розблокувати';
const deleteBtnLabel = 'Видалити';

const productNameLengthError = 'Назва продукту повинна перевищувати 3 символи';

const creationServerErrorMessage = "Нажаль не вдалося додати новий продукт, перезавантажте сторінку та спробуйте щераз";
const removingServerErrorMessage= 'Нажаль не вдалося додати новий продукт, перезавантажте сторінку та спробуйте щераз';
const blockingServerErrorMessage= 'Не вдалось заблокувати продукт, будь ласка оновіть сторінку та спробуйте щераз';
const unlockingServerErrorMessage = 'Не вдалось розблокувати продукт, будь ласка оновіть сторінку та спробуйте щераз';

$(document).ready(function () {
    refreshData();
});

function showProductsList(tab) {
    if (tab !== currentTab) {
        clearTabSelection();
        selectTab(tab);
        setTimeout(function () {
            mainDiv.html("");
            processProducts(this.allProducts)
        }, 100);
    }
}

function showBlockedProductList(tab) {
    if (tab !== currentTab) {
        clearTabSelection();
        selectTab(tab);
        setTimeout(function () {
            let tempNotActiveProducts = [];
            $.each(this.allProducts ,function (index, element) {
                if (!element.active) {
                    tempNotActiveProducts.push(element);
                }
            });
            mainDiv.html("");
            processProducts(tempNotActiveProducts);
        }, 100);
    }
}

function showUnblockedProducts(tab) {
    if (tab !== currentTab) {
        clearTabSelection();
        selectTab(tab);
        setTimeout(function () {
            let tempActiveProducts = [];
            $.each(this.allProducts ,function (index, element) {
                if (element.active) {
                    tempActiveProducts.push(element);
                }
            });
            mainDiv.html("");
            processProducts(tempActiveProducts);
        }, 100);
    }
}
function blockProduct(productId) {
    if (!lock) {
        swal({
            title: "Ви впевнені?",
            text: "Ви впевнені що хочете заблокувати продукт?",
            icon: "warning",
            buttons: ["Скасувати", true],
            dangerMode: true,
        })
            .then((willBlock) => {
                if (willBlock) {
                    lock = true;
                    sendRequest("/product-block/", productId, 'POST', changeButtonLabel, blockingServerErrorMessage);
                }
            });
    }
}

function unblockProduct(productId) {
    if (!lock) {
        swal({
            title: "Ви впевнені?",
            text: "Після розблокування продукт стане доступним для замовлення",
            icon: "warning",
            buttons: ["Скасувати", true],
            dangerMode: true,
        })
            .then((willUnblock) => {
                if (willUnblock) {
                    lock = true;
                    sendRequest("/product-unblock/", productId, 'POST', changeButtonLabel, unlockingServerErrorMessage);
                }
            });
    }
}

function removeProduct(productId) {
    if (!lock) {
        swal({
            title: "Ви впевнені?",
            text: "Видалений продукт неможливо буде відновити",
            icon: "warning",
            buttons: ["Скасувати", true],
            dangerMode: true,
        })
            .then((willUnblock) => {
                if (willUnblock) {
                    lock = true;
                    sendRequest("/product-remove/", productId, 'DELETE', removeListElement, removingServerErrorMessage);
                }
            });
    }
}

function loadProductsList(url, func) {
    $("main").append("<div class='cssload-container'>\n" +
        "\t<div class='cssload-zenith'></div>\n" +
    "</div>");
    $.ajax ({
        url: String(url),
        type: "GET",
        dataType: "json",
        data: ({}),
        contentType: 'application/json',
        success: function (data) {
            func(data);
        }
    });
}

function sendRequest(url, id, method, func, errorMessage) {
    $.ajax ({
        url: url + id,
        type: String(method),
        data: ({}),
        contentType: 'application/json',
        success: function () {
            swal({
                title: "Успішно!",
                icon: "success",
                button: "OK",
            });
            func(id);
        },
        error: function () {
            swal({
                title: "Сталась помилка",
                text: errorMessage,
                icon: "error",
                button: "OK",
            });
            lock = false;
        }
    });
}

function processProducts(data) {
    if (data) {
        $.each(data, function (index, element) {
            addListElement(element);
        })
    }
    $(".cssload-container").fadeOut(100).remove();
    $(mainDiv).fadeIn(100);
}

function addListElement(element) {
    let onclick = '';
    let label = '';
    if (element.active === true) {
        onclick = 'blockProduct(' + element.id + ')';
        label = blockBtnLabel;
    }  else {
        onclick = 'unblockProduct(' + element.id + ')';
        label = unblockBtnLabel;
    }
    $("#productsContainer").append("        " +
        "       <div class='col-12 col-xl-6 p-3 item-2' id='product"+ element.id +"'>\n" +
        "            <div class='row align-items-center'>\n" +
        "                <div class='col-12 col-sm-8 col-md-9 col-xl-8'>\n" +
        "                    <div class='row'>\n" +
        "                        <div class='col-12'>\n" +
        "                            <h5>" + element.name + "</h5>\n" +
        "                        </div>\n" +
        "                        <div class='col-12'>\n" +
        "                            <p>" + element.description + "</p>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class='col-12 col-sm-4 col-md-3 col-xl-4'>\n" +
        "                    <div class='btn-group'>\n" +
        "                        <button type='button' class='btn btn-warning' onclick='"+ onclick +"'>"+ label +"</button>\n" +
        "                        <button type='button' class='btn btn-warning dropdown-toggle dropdown-toggle-split' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>\n" +
        "                            <span class='sr-only'>Розгорнути меню</span>\n" +
        "                        </button>\n" +
        "                        <div class='dropdown-menu'>\n" +
        "                            <button class='dropdown-item' type='button'>Редагувати</button>\n" +
        "                            <button class='dropdown-item' type='button' onclick='removeProduct("+ element.id +")'>"+ deleteBtnLabel +"</button>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>")
}



function removeListElement(productId) {
    let obj = $("#product" + productId);
    obj.slideToggle(300);
    setTimeout(function () {
        obj.remove();
        lock = false;
    }, 500);
}

function clearTabSelection() {
    allTabs.css('border-bottom', defaultBorderStyle);
}

function selectTab(tab) {
    $(tab).css('border-bottom', selectedBorderStyle);
    currentTab = tab;
    mainDiv.fadeOut(100);
}

function addForm() {
    $("body").append("" +
        "<div class='b-popup'>\n" +
        "   <div class='row pt-5'>" +
        "        <div class='col-5 mx-auto b-popup-content mt-5 p-3'>\n" +
        "            <h4>Додати товар</h4>" +
        "            <hr>" +
        "            <form>\n" +
        "                <div class='form-group'>\n" +
        "                    <label for='name'>Назва продукту</label>\n" +
        "                    <input type='text' class='form-control' id='name' placeholder=''/>\n" +
        "                </div>\n" +
        "                <div class='form-group'>\n" +
        "                    <label for='description'>Опис продукту</label>\n" +
        "                    <textarea class='form-control' id='description' rows='3'></textarea>\n" +
        "                </div>" +
        "                <div class='custom-control custom-checkbox'>\n" +
        "                    <input type='checkbox' class='custom-control-input' id='active'>\n" +
        "                    <label class='custom-control-label' for='active'>Активувати продукт</label>\n" +
        "                </div>" +
        "                <br>\n" +
        "                <button type='button' class='btn btn-warning' onclick='validateAndSendForm(this.form)'>Додати</button>\n" +
        "                <button type='button' class='btn btn-secondary' onclick='removeForm()'>Скасувати</button>\n" +
        "            </form>\n" +
        "        </div>\n" +
        "   </div>" +
        "</div>");
    $(".b-popup").fadeIn(100);
}

function removeForm() {
    $(".b-popup").fadeOut(100).remove();
}

function validateAndSendForm(form) {
    let  hasErrors = false;
    let elems = form.elements;

    if (!elems["name"].value || elems["name"].value.length <=3) {
        hasErrors = true;
        showError(elems["name"].parentNode, productNameLengthError);
    }
    if (!hasErrors) {
        sendForm('POST', processForm(form), creationServerErrorMessage);
    }
}

$(":input").on('input', function () {
    resetError($(this)[0].parentNode);
});

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

function processForm(elems) {
    let active = $(elems["active"]).is(":checked");
    return {
        "id" : 0,
        "name" : elems["name"].value,
        "description" : elems["description"].value,
        "active" : active
    }
}

function sendForm(method, product, errorMessage) {
    $.ajax ({
        type : String(method),
        url : '/product',
        contentType : 'application/json',
        data : JSON.stringify(product),
        error: function () {
            swal({
                title: "Сталась помилка",
                text: errorMessage,
                icon: "error",
                button: "OK",
            });
        },
        success : function () {
            swal({
                title: "Успішно!",
                icon: "success",
                button: "OK",
            });
            removeForm();
            refreshData();
        }
    });
}

function moveToFirstTab() {
    currentTab = '';
    showProductsList($("#tab1")[0]);
}

function setAllProducts(data) {
    this.allProducts = data;
}

function changeButtonLabel(id) {
    let element = $("#product" + id);
    let btn = element.find(".btn").eq(0);
    let tab1 = $("#tab1");
    if (btn.html() === blockBtnLabel) {
        if (currentTab === $(tab1)[0] || currentTab === $(searchTab)[0]) {
            btn.html(unblockBtnLabel);
            btn.attr("onclick", "unblockProduct(" + id + ")");
            setActiveStatus(id, false);
        } else {
            setActiveStatus(id, false);
            removeListElement(id);
        }
    } else {
        if (currentTab === $(tab1)[0] || currentTab === $(searchTab)[0]) {

            btn.html(blockBtnLabel);
            btn.attr("onclick", "blockProduct(" + id + ")");
            setActiveStatus(id, true);
        } else {
            setActiveStatus(id, true);
            removeListElement(id);
        }
    }
    setTimeout(function () {
        lock = false;
    }, 1000);
}

function setActiveStatus(id, status) {
    for (let i = 0; i <this.allProducts.length; i++) {
        if (this.allProducts[i].id === id) {
            this.allProducts[i].active = status;
        }
    }
}

function search(request) {
    if (request) {
        clearTabSelection();
        mainDiv.html("");
        currentTab = searchTab;
        let results = [];
        for (let i = 0; i <this.allProducts.length; i++) {
            if (this.allProducts[i].name.toUpperCase().match(".*" + request.toUpperCase() +".*")) {
                results.push(this.allProducts[i]);
            }
        }
        if (results.length !== 0) {
            processProducts(results);
        } else {
            $(mainDiv).append("<h5>No results</h5>")
        }
    } else {
        moveToFirstTab();
    }
}

function refreshData() {
    loadProductsList('/product', setAllProducts);
    moveToFirstTab();
}

