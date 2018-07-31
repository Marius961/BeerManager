$(document).ready(function () {
    showProductsList();
});

function showProductsList() {
    loadProductsList('/product', processProducts);
}

function blockProduct(productId) {
    sendRequest("/product-block/", productId, 'POST', switchButton);
}

function unblockProduct(productId) {
    sendRequest("/product-unblock/", productId, 'POST', switchButton);
}

function removeProduct(productId) {
    sendRequest("/product-remove/", productId, 'DELETE', removeListElement);
}

function loadProductsList(url, func) {
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
    $(".cssload-container").fadeOut(100).remove();
    if (data) {
        $.each(data, function (index, element) {
            addListElement(element);
        })
    }
}

function addListElement(element) {
    $(".main-div").append("" +
        "<div class='list-elem' id='product"+ element.id +"'>\n" +
        "    <table style='width: 100%'>\n" +
        "        <tr>\n" +
        "            <th class='name-td'>"+ element.name +"</th>\n" +
        "            <td class='btn-td'><button class='btn btn-secondary' onclick='blockProduct("+ element.id +")'>Заблокувати</button></td>\n" +
        "            <td class='btn-td'><button class='btn btn-danger' onclick='removeProduct("+ element.id +")'>Видалити</button></td>\n" +
        "            </tr>\n" +
        "    </table>\n" +
        "    <div>\n" +
        "        <H6>Опис</H6>\n" +
        "        <p>"+ element.description +"</p>\n" +
        "    </div>\n" +
        "</div>\n")
}

function removeListElement(productId) {
    let obj = $("#product" + productId);
    obj.slideToggle(300);
    // obj.fadeOut(200);
    setTimeout(function () {
        obj.remove()
    }, 500);
}

function switchButton(productId) {

}