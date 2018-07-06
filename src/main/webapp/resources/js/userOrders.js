$(document).ready(function () {
    showCurrentDateOrders();
});

function showCurrentDateOrders() {
    let date = getCurrentDate();
    let dateObj = {
        "str" : date
    };
    loadOrderList('/user-orders', processOrders, dateObj);
}

function showOtherOrders() {
    let date = getCurrentDate();
    let dateObj = {
        "str" : '!' + date
    };
    loadOrderList('/user-orders', processOrders, dateObj);
    $(".show-btn").remove();
}

function loadOrderList(url, func, dateObj) {
    $(".main-div").append("<div class='cssload-container'>\n" +
        "\t<div class='cssload-zenith'></div>\n" +
        "</div>");
    $.ajax ({
        url: String(url),
        type: "POST",
        dataType: "json",
        data: JSON.stringify(dateObj),
        contentType: 'application/json',
        success: function (data) {
            func(data);
        },
    });
}

function processOrders(data) {
    $(".cssload-container").fadeOut(300).remove();
    if (data) {
        Object.keys(data).forEach(function (key) {
            let label = '';
            if (key === getCurrentDate()) {
                label = 'Today';
            } else {
                label = key;
            }
            addDateHeader(key, label);
            $.each(data[key], function (index, element) {
                addListElement(element, key);
            })
        });
        $(".container-div").fadeIn(300);
    }  else {
        $(".main-div").append("<h1>No orders</h1>");
    }
}

function addListElement(element, target) {
    let elemId = 'elem' + element.id;
    let blockId = 'productBlock' + element.id;
    let tableContainer = 'tableContainer' + element.id;
    let table = 'table' + element.id;
    let blockSelector = '#' + blockId;
    $("#" + target).append("<div class='content-box' id='"+ elemId + "'></div>");
    $("#" + elemId).append("<div class='product-block' id='" + blockId + "'></div>");
    $(blockSelector).append("<p class='details-1 order-info'>Order #<span class='info'>" + element.id + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Full name full name: <span class='info'>" + element.customer.fullName + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Company name: <span class='info'>" + element.customer.companyName + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Company address: <span class='info'>" + element.customer.companyAddress + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Phone number: <span class='info'>" + element.customer.telNumber + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Creation date: <span class='info'>" + element.creationDate + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Creation time: <span class='info'>" + element.creationTime + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Execution date: <span class='info'>" + element.execDate + "</span></p>");
    $(blockSelector).append("<p class='details-1 inline order-info background'>Price: <span class='info'>" + element.price + "</span></p>");
    $(blockSelector).append("<div class='prod-order inline background order-info' id='" + tableContainer + "'></div>");
    $("#" + tableContainer).append("<table class='table-order' id='" + table + "'></table>");
    $.each(element.orderItems, function (index, element) {
        $("#" + table).append("<tr>\n" +
            "<td class='table-td'><h4 class='prod-name'>" + element.product.name + ":</h4></td>\n" +
            "<td class='table-td-vol'><span class='barrel-qt'>" + element.volume + "</span></td>\n" +
            "<td class='table-td-vol-name'><span class='barrels-vol'>liters</span></td>" +
            "</tr>");
    })
}

function addDateHeader(date, label) {
    $("#orders").append("<div id='" + date + "'></div>");
    $("#" + date).append("<h2 class='date-header'>" + label + "</h2>")
}

function displayHideOrderGroup(blockId) {
    if ($(blockId).css('display') === 'none') {
        $(blockId).animate({height: 'show'}, 300);
    }
    else {
        $(blockId).animate({height: 'hide'}, 300);
    }
}

function getCurrentDate() {
    let simpleDate;
    let now = new Date();
    simpleDate = now.toISOString();
    simpleDate = simpleDate.substr(0,10);
    return simpleDate;
}
