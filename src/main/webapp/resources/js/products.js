let mainDiv = $(".main-div");
let allTabs = $(".tab");
let currentTab = '';
$(document).ready(function () {
    currentTab = '';
    showProductsList($("#tab1")[0]);
});

function showProductsList(tab) {
    if (tab !== currentTab) {
        currentTab = tab;
        clearTabSelection();
        selectTab(tab);
        mainDiv.fadeOut(100);
        setTimeout(function () {
            loadProductsList('/product', processProducts);
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
            loadProductsList('/product/0', processProducts);
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
            loadProductsList('/product/1', processProducts);
        }, 100);
    }
}
function blockProduct(productId) {
    sendRequest("/product-block/", productId, 'POST', removeListElement);
}

function unblockProduct(productId) {
    sendRequest("/product-unblock/", productId, 'POST', removeListElement);
}

function removeProduct(productId) {
    sendRequest("/product-remove/", productId, 'DELETE', removeListElement);
}

function loadProductsList(url, func) {
    $("main").append("<div class='cssload-container'>\n" +
        "\t<div class='cssload-zenith'></div>\n" +
    "</div>");
    mainDiv.html("");
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
        complete: function () {
            func(id);
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
    if (currentTab === $(tab1)[0]) {
        currentTab = '';
        showProductsList($(tab1)[0]);
    } else {
        let obj = $("#product" + productId);
        obj.slideToggle(300);
        setTimeout(function () {
            obj.remove()
        }, 500);
    }
}

function clearTabSelection() {
    allTabs.css('border-bottom', '7px solid #ededed');
}

function selectTab(tab) {
    $(tab).css('border-bottom', '7px solid black');
}
