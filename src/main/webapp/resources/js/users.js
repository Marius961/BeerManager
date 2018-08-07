$(document).ready(function () {
    loadUsersList();
})


function loadUsersList() {
    let url = '/users';
    $(".content-box").append("<div class='cssload-container'>\n" +
        "\t<div class='cssload-zenith' style='margin: 0 auto'></div>\n" +
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
                $.each(data, function (index, element) {
                    addUserToList(element);
                });
            }  else {
                $(".content-box").append("<h1>No users</h1>");
            }
        }
    });
}


function addUserToList(user) {
    if (user) {
        $(".table-users").append("<tr class='tr-users' onclick=\"location.href='/user/get/"+ user.id +"'\">\n" +
            "<td class='td-content th-content-id'>"+ user.id + "</td>\n" +
            "<td class='td-content th-content-2'>"+ user.companyName + "</td>\n" +
            "<td class='td-content th-content-2'>"+ user.fullName + "</td>\n" +
            "<td class='td-content th-orders-count'>54</td>\n" +
            "</tr>")
    }
}