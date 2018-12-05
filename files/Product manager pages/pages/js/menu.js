let items = $(".menu-item");
let currenMenuItem = "";

$(document).ready(function () {
    let url = location.href;
    let itemHref = url.substring(url.indexOf("#")+1);
    let item = $(".menu-item[href='#"+ itemHref +"']")[0];
    if (item !== undefined) {
        try {
            selectMenuItem($(item)[0]);
        } catch (e) {
            selectMenuItem($(items)[0]);
        }
    } else {
        let localSelectedItem = localStorage.getItem('lastItem') || '';
        if (localSelectedItem !== '') {
            try {
                item = $(".item[name='"+ localSelectedItem +"']")[0];
                selectMenuItem($(item)[0]);
            } catch (e) {
                selectMenuItem($(items)[0]);
            }
        } else {
            selectMenuItem($(items)[0]);
        }
    }
});


$(items).click( function () {
    selectMenuItem($(this));
});

function selectMenuItem(item) {
    if ($(item)[0] !== currenMenuItem) {
        let href = $(item).attr('href');
        $(items).removeClass("selected-item");
        $(".item-content").fadeOut(100);
        $(item).addClass("selected-item");
        setTimeout(function () {
            $(href).fadeIn(100);
        }, 100);
        currenMenuItem = $(item)[0];
        localStorage.setItem("lastItem", $(item).attr('name'));
    }
}