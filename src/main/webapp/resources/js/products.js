let mainDiv = $(".main-div");
let allTabs = $(".tab");
let currentTab = '';
let allProducts;
let activeProducts = [];
let notActiveProducts = [];
let lock = false;
$(document).ready(function () {
    loadProductsList('/product', sortOrders);
    moveToFirstTab();
});

function showProductsList(tab) {
    if (tab !== currentTab) {
        currentTab = tab;
        clearTabSelection();
        selectTab(tab);
        mainDiv.fadeOut(100);
        setTimeout(function () {
            processProducts(this.allProducts)
        }, 100);
    }
}

function showBlockedProductList(tab) {
    if (tab !== currentTab) {
        currentTab = tab;
        clearTabSelection();
        selectTab(tab);
        mainDiv.fadeOut(100);
        setTimeout(function () {
            processProducts(this.notActiveProducts);
        }, 100);
    }
}

function showUnblockedProducts(tab) {
    if (tab !== currentTab) {
        currentTab = tab;
        clearTabSelection();
        selectTab(tab);
        mainDiv.fadeOut(100);
        setTimeout(function () {
            processProducts(this.activeProducts);
        }, 100);
    }
}
function blockProduct(productId) {
    if (!lock) {
        lock = true;
        sendRequest("/product-block/", productId, 'POST', changeButtonLabel);
    }
}

function unblockProduct(productId) {
    sendRequest("/product-unblock/", productId, 'POST', changeButtonLabel);
}

function removeProduct(productId) {
    sendRequest("/product-remove/", productId, 'DELETE', removeListElement);
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

function sendRequest(url, id, method, func) {
    $.ajax ({
        url: url + id,
        type: String(method),
        dataType: "json",
        data: ({}),
        contentType: 'application/json',
        success: func(id)
    });
}

function processProducts(data) {
    mainDiv.html("");
    if (data) {
        $.each(data, function (index, element) {
            addListElement(element);
        })
    }
    $(".cssload-container").fadeOut(100).remove();
    $(".main-div").fadeIn(100);
}

function addListElement(element) {
    let onclick = '';
    let label = '';
    if (element.active === true) {
        onclick = 'blockProduct(' + element.id + ')';
        label = 'Block';
    }  else {
        onclick = 'unblockProduct(' + element.id + ')';
        label = 'Unblock';
    }
    $(".main-div").append("" +
        "<div class='list-elem' id='product"+ element.id +"'>\n" +
        "    <table style='width: 100%'>\n" +
        "        <tr>\n" +
        "            <th class='name-td'>"+ element.name +"</th>\n" +
        "            <td class='btn-td'><button id='' class='btn btn-secondary' onclick='"+ onclick +"'>"+ label +"</button></td>\n" +
        "            <td class='btn-td'><button class='btn btn-danger' onclick='removeProduct("+ element.id +")'>Remove</button></td>\n" +
        "            </tr>\n" +
        "    </table>\n" +
        "    <div>\n" +
        "        <H6>Опис</H6>\n" +
        "        <p>"+ element.description +"</p>\n" +
        "    </div>\n" +
        "</div>\n")
}



function removeListElement(productId) {
    let tab1 = $("#tab1");
    if (currentTab !== $(tab1)[0]) {
        let obj = $("#product" + productId);
        obj.slideToggle(300);
        setTimeout(function () {
            obj.remove()
        }, 500);
    }
}

function clearTabSelection() {
    allTabs.css('border-bottom', '7px solid #ededed');
    $(".tab-search").css('border', '0');
}

function selectTab(tab) {
    $(tab).css('border-bottom', '7px solid black');
}

function addForm() {
    $("body").append("" +
        "<div class='b-popup'>\n" +
        "        <div class='b-popup-content'>\n" +
        "            <form>\n" +
        "                <div class='form-group'>\n" +
        "                    <label for='name'>Product name</label>\n" +
        "                    <input type='text' class='form-control' id='name' placeholder=''/>\n" +
        "                </div>\n" +
        "                <div class='form-group'>\n" +
        "                    <label for=description'>Product description</label>\n" +
        "                    <input type='text' class='form-control' id='description' placeholder=''/>\n" +
        "                </div>\n" +
        "                <div class='custom-control custom-checkbox'>\n" +
        "                    <input type='checkbox' class='custom-control-input' id='active'>\n" +
        "                    <label class='custom-control-label' for='active'>Activate product</label>\n" +
        "                </div>" +
        "                <br>\n" +
        "                <button type='button' class='btn btn-primary' onclick='validateAndSendForm(this.form)'>Submit</button>\n" +
        "                <button type='button' class='btn btn-primary' onclick='removeForm()'>Cancel</button>\n" +
        "            </form>\n" +
        "        </div>\n" +
        "    </div>");
    $(".b-popup").fadeIn(100);
}

function removeForm() {
    $(".b-popup").fadeOut(100).remove();
}

function validateAndSendForm(form) {
    let  hasErrors = false;
    let elems = form.elements;
    $("#productName").click(function () {
        resetError(elems["name"].parentNode);
    });
    if (!elems["name"].value || elems["name"].value.length <=3) {
        hasErrors = true;
        showError(elems["name"].parentNode, 'Product name must be longer than 3 symbols');
    }
    if (!hasErrors) {
        sendForm('POST', processForm(form));
    }
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

function processForm(elems) {
    let active = $(elems["active"]).is(":checked");
    alert(active);
    return {
        "id" : 0,
        "name" : elems["name"].value,
        "description" : elems["description"].value,
        "active" : active
    }
}

function sendForm(method, product) {
    $.ajax ({
        type : String(method),
        url : '/product',
        dataType: "json",
        contentType : 'application/json',
        data : JSON.stringify(product),
        complete : function () {
            removeForm();
            moveToFirstTab();
        }
    });
}

function moveToFirstTab() {
    currentTab = '';
    showProductsList($("#tab1")[0]);
}

function sortOrders(data) {
    this.allProducts = data;
    let tempNotActiveProducts = [];
    let tempActiveProducts = [];
    $.each(this.allProducts, function (index, element) {
        if (!element.active) {
            tempNotActiveProducts.push(element);
        } else {
            tempActiveProducts.push(element);
        }
    });
    this.notActiveProducts = tempNotActiveProducts;
    this.activeProducts = tempActiveProducts;
}

function changeButtonLabel(id) {
    let element = $("#product" + id);
    let blockBtn = element.find(".btn").eq(0);
    if (blockBtn.html() === "Block") {
        blockBtn.html("Unblock");
        blockBtn.click(function () {
            unblockProduct(id);
        });
    } else {
        blockBtn.html("Block");
        blockBtn.click(function () {
            blockProduct(id);
        });
    }
    removeListElement(id);
    lock = false;
}
