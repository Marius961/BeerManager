let currentTab = '';

let monthList = ['Січня', 'Лютого','Березня','Квітня','Травня','Червня','Липня','Серпня','Вересня','Жовтня','Листопада','Грудня'];

let defaultBorderStyle = '0.8mm solid #ededed';
let selectedBorderStyle = '0.8mm solid black';
let allTabs = $(".tab");

$(document).ready(function () {
    let path = location.href;
    path = path.substr(path.lastIndexOf('/') + 1);
    if (path === 'orders') {
        loadUserCurrentDateOrders($(allTabs)[0]);

    } if (path === 'all-orders') {
        loadAllOrdersForCurrentDate($(allTabs)[0]);
    }
});


/*Functions for user in "My orders" page*/

function loadUserCurrentDateOrders(tab) {
    if (tab !== currentTab) {
        clearTabSelection();
        selectTab(tab);
        let dateObj = {
            "str" : 'CURRENT_DATE'
        };
        $("#orders-container").fadeOut(100);
        setTimeout(function () {
            $("#orders").html('');
            loadOrderList('/orders', processOrders, dateObj, false);
        }, 100);
    }
}

function loadUserOtherOrders(tab) {
    if (tab !== currentTab) {
        clearTabSelection();
        selectTab(tab);
        let dateObj = {
            "str" : '!CURRENT_DATE'
        };
        $("#orders-container").fadeOut(100);
        setTimeout(function () {
            $("#orders").html('');
            loadOrderList('/orders', processOrders, dateObj, false);
        }, 100);
    }
}

/**/


/*Functions for admin in "All orders" page*/

function loadAllOrdersForCurrentDate(tab) {
    if (tab !== currentTab) {
        clearTabSelection();
        selectTab(tab);
        let dateObj = {
            "str" : 'CURRENT_DATE'
        };
        setTimeout(function () {
            $("#orders").html('');
            loadOrderList('/user-orders', processOrders, dateObj, true);
        }, 100);
    }
}

function loadAllOtherOrders(tab) {
    if (tab !== currentTab) {
        clearTabSelection();
        selectTab(tab);
        let dateObj = {
            "str" : '!CURRENT_DATE'
        };
        $("#orders-container").fadeOut(100);
        setTimeout(function () {
            $("#orders").html('');
            loadOrderList('/user-orders', processOrders, dateObj, true);
        }, 150);
    }
}

/**/

function loadOrderList(url, func, dateObj, displayCustomerData) {
    addLoadAnimation();
    $.ajax ({
        url: String(url),
        type: "POST",
        dataType: "json",
        data: JSON.stringify(dateObj),
        contentType: 'application/json',
        success: function (data) {
            func(data, displayCustomerData);
        },
    });
}

function processOrders(data, displayCustomerData) {
    if (data) {
        Object.keys(data).forEach(function (key) {
            let label = '';
            let date = new Date(key);
            label += date.getDate() + ' ';
            label += getMonthName(date.getMonth()) + ' ';
            label += date.getFullYear();
            addDateHeader(key, label);
            $.each(data[key], function (index, element) {
                addListElement(element, key, displayCustomerData);
            })
        });
    }  else {
        $("#orders").append("<h4>Немає замовлень</h4>");
    }
    removeLoadAnimation();
    setTimeout(function () {
        $("#orders-container").fadeIn(300);
    }, 200);
}


function addListElement(element, target, displayCustomerData) {
    $("#list-" + target).append("" +
        "<div class='row mb-4 order-box' id='order-"+ element.id + "'>\n" +
        "   <div class='col-12'>\n" +
        "       <div class='row order-header p-1'>\n" +
        "           <div class='col-12 col-sm-6 text-center text-sm-left'>Дата виконання: <span class='font-weight-bold'>" + element.execDate + "</span> </div>\n" +
        "           <div class='col-12 col-sm-6 text-center text-sm-right'>Замовлення №<span class='font-weight-bold'>"+ element.id + "</span></div>\n" +
        "           <div class='col-12 col-sm-12 text-center text-sm-right'>Дата створення: <span class='font-weight-bold'>" + element.creationDate + "</span> </div>\n" +
        "           <div class='col-12 col-sm-12 text-center text-sm-right'>Час cтворення:<span class='font-weight-bold'>" + element.creationTime + "</span></div>\n" +
        "       </div>\n" +
        "   </div>\n" +
        "</div>");
    let orderElement = $("#order-" + element.id);
    if (displayCustomerData) {
        $(orderElement).find(".order-header").append("" +
            "<div class='col-12 col-sm-12 text-center text-sm-left'>П.І.Б. замовника : <span class='font-weight-bold'>" + element.customer.fullName + "</span> </div>\n" +
            "<div class='col-12 col-sm-12 text-center text-sm-left'>Назва компанії:<span class='font-weight-bold'>" + element.customer.companyName + "</span></div>\n" +
            "<div class='col-12 col-sm-12 text-center text-sm-left'>Адреса компанії: <span class='font-weight-bold'>" + element.customer.companyAddress + "</span> </div>\n" +
            "<div class='col-12 col-sm-12 text-center text-sm-left'>Номер телефону:<span class='font-weight-bold'>" +element.customer.telNumber + "</span></div>\n");
    }
    $(orderElement).find(">:first-child").append("" +
        "<div class='row order-header p-1 mt-1 text-center font-weight-bold'>\n" +
        "   <div class='col-9'>Назва товару</div>\n" +
        "   <div class='col-3'>Об'єм</div>\n" +
        "</div>\n" +
        "<div class='row'>\n" +
        "   <div class='col' id='items-" + element.id + "'></div>\n" +
        "</div>");
    if (element.comment) {
        $(orderElement).append("" +
            "<div class='col p-1 pl-2 comment-col'>\n" +
            "       <h5>Коментар:</h5>\n" +
            "       <p>"+ element.comment +"</p>\n" +
            "</div>\n");
    }
    $.each(element.orderItems, function (index, item) {
        $("#items-" + element.id ).append("" +
            "<div class='row p-1 mt-1 item-1'>\n " +
            "   <div class='col-9'>" + item.product.name + "</div>\n" +
            "   <div class='col-3 text-center'>" + item.volume + 'л.' +"</div>\n" +
            "</div>");
    });
}

function addDateHeader(date, label) {
    $("#orders").append("" +
        "<div class='row' id='" + date + "'>\n" +
        "   <div class='col-12 p-0 mb-2'>\n" +
        "       <h2 class='date-header p-1 p-sm-2 text-center'>" + label + "</h2>\n" +
        "   </div>\n" +
        "   <div class='col-12' id='list-"+ date +"'>\n" +
        "   </div>" +
        "</div>");
}

function getMonthName(number) {
    return monthList[number];
}

function clearTabSelection() {
    allTabs.css('border-bottom', defaultBorderStyle);
}

function selectTab(tab) {
    $(tab).css('border-bottom', selectedBorderStyle);
    currentTab = tab;
    $("#orders-container").fadeOut(100);
}

function addLoadAnimation() {
    $(".main-div").append("<div class='cssload-container'>\n" +
        "<div class='cssload-speeding-wheel'></div>\n" +
        "</div>");
    $(".cssload-loader").fadeIn(150);
}

function removeLoadAnimation() {
    let container = $(".cssload-container");
    container.fadeOut(150);
    setTimeout(function () {
        container.remove();
    }, 150);
}

