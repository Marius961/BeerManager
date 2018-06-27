$(document).ready(function () {
    loadOrderList();
})

function loadOrderList() {
    let url = '/orders';
    $(".main-div").append("<div class='cssload-container'>\n" +
        "\t<div class='cssload-zenith'></div>\n" +
        "</div>");
    $.ajax ({
        url: String(url),
        type: "GET",
        dataType: "json",
        data: ({}),
        contentType: 'application/json',
        success: function (data) {
            $(".cssload-container").fadeOut(300).remove();
            if (data) {
                let currentDate = new Date();
                currentDate.setHours(0 ,0 , 0 , 0);
                let orderDate = "";
                $.each(data, function (index, element) {
                    orderDate = new Date(element.execDate);
                    orderDate.setHours(0 ,0 , 0 , 0);
                    if (+orderDate === +currentDate) {
                        addListElement(element, '#currentDayOrders');
                    }
                    else {
                        addListElement(element, '#otherOrders');
                    }
                });
                $(".container-div").fadeIn(300);
            }  else {
                $(".main-div").append("<h1>No orders</h1>");
            }
        }
    });
}

function addListElement(element, targetList) {
    let elemId = 'elem' + element.id;
    let blockId = 'productBlock' + element.id;
    let tableContainer = 'tableContainer' + element.id;
    let table = 'table' + element.id;
    let blockSelector = '#' + blockId;
    $(targetList).append("<div class='content-box' id='"+ elemId + "'></div>");
    $("#" + elemId).append("<div class='product-block' id='" + blockId + "'></div>");
    $(blockSelector).append("<p class='details-1 order-info'>Order №: <span class='info'>" + element.id + "</span></p>");
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

function displayHideOrderGroup(blockId) {
    if ($(blockId).css('display') === 'none') {
        $(blockId).animate({height: 'show'}, 300);
    }
    else {
        $(blockId).animate({height: 'hide'}, 300);
    }
}
