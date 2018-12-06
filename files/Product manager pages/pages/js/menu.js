

let userMenu = {items:$("#userMenuItems").children(),
        currentMenuItem: "",
        content: $("#userMenuContent").children(),
        paramName: "item"
    },
    tabMenu = {items:$("#ordersTabs").children(),
        currentMenuItem: "",
        content: $("#ordersTabsContent").children()
    };



$(document).ready(function () {
    readSelectedItemFromUrlParams();
    selectItem($(tabMenu.items)[0], tabMenu);
    $(userMenu.items).click( function (e) {
        selectItem($(this), userMenu);
        e.preventDefault();
    });

    $(tabMenu.items).click( function (e) {
        selectItem($(this), tabMenu);
        e.preventDefault();
    });
});


function selectItem(item, menu) {
    if ($(item)[0] !== menu.currentMenuItem) {
        let href = $(item).attr('href');
        $(menu.items).removeClass("selected-item");
        $(menu.content).fadeOut(100);
        $(item).addClass("selected-item");
        setTimeout(function () {
            $(href).fadeIn(100);
        }, 100);
        menu.currentMenuItem = $(item)[0];
        if (menu.paramName !== undefined) {
            setGetParam("item", href.substr(1))
        }
    }
}
function setGetParam(key,value) {
    if (history.pushState) {
        var params = new URLSearchParams(window.location.search);
        params.set(key, value);
        var newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?' + params.toString();
        window.history.pushState({path:newUrl},'',newUrl);
    }
}

function getParam(name){
    let results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    return results[1] || 0;
}


function readSelectedItemFromUrlParams() {
    try {
        let menuItemHref = "#" + getParam("item");
        let item = $(".item[href='"+ menuItemHref +"']")[0];
        if (item !== undefined) {
            selectItem($(item), userMenu);
        } else {
            selectItem($(userMenu.items)[0], userMenu);
        }
    } catch (e) {
        selectItem($(userMenu.items)[0], userMenu);
    }
}
