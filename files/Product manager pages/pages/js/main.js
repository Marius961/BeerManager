let animationTime = 150,
    dropdownButtons = $(".nav-dropdown-btn"),
    menuButton = $("#menuBtn"),
    categoryItems = $(".category-item"),
    menuItemWrappers = $(".menu-item-content-wrapper"),
    navMenuItemActiveClassname = "menu-item-active",
    menu = $("#menu");
    itemClassNae = "main-menu-item";

let mainMenu = $("#mainMenu");

let userMenu = {items:$("#mainMenuItems").children(),
        currentMenuItem: "",
        content: $("#userMenuContent").children(),
        paramName: "item"
    },
    myOrdersTabMenu = {items:$("#myOrdersTabs").children(),
        currentMenuItem: "",
        content: $("#myOrdersContent").children()
    },
    receivedOrdersTabMenu = {items:$("#receivedOrdersTabs").children(),
        currentMenuItem: "",
        content: $("#receivedOrdersContent").children()
    };


//  function for calculating price
$("#measurementInput").on("input", function () {
    let quantity = +$(this).val();
    let sumBox = $(".sum-box");
    if (quantity) {
        let productPrice = $("#price").text(),
            sum = (productPrice * quantity).toFixed(2),
            sumLabel = $(sumBox).find("#sum");

        $(sumLabel).text(sum + "грн");
        $(sumBox).slideDown(200);
    } else {
        $(sumBox).slideUp(200);
    }
});


// show dropdown on click on any dropdown button
$(dropdownButtons).on("click", function () {
    slideUpMenuWithContent();
    let menu = $(this.parentNode).find(".nav-dropdown-menu");
    if ($(menu).css("display") === "none") {
        clearDropdowSelection();
        $(menu).css("display", "inline-block")
    } else {
        $(menu).css("display", "none")
    }
});


// hide dropdown menu on mouseleave
$(".nav-dropdown-menu").mouseleave(function () {
    $(this).css("display", "none")
});


$("#closeAddressFormBtn").on("click", function () {
    popupFormFadeToggle();
});


$("#addAddressBtn").click(function () {
    popupFormFadeToggle();
});


$(".popup-bg").ready(function () {
    $(".popup-bg").css('height', $(document).height());
    // when resizing the page - popup change its size to such as page size
    $(window).resize(function () {
        $(".popup-bg").css('height', $(document).height())
    });
});

// slide up all categories and other menu items on close or open menu
$(menuButton).click(function () {
    clearDropdowSelection();
    slideUpAllCategoriesWrappers();
    slideUpAllMenuWrappers();
    menuSlideToggle();
});

$(".nav-menu, #menuBtn").mouseleave(function () {
    let menuTimerId = setTimeout(    slideUpMenuWithContent, 500);
    $(".nav-menu, #menuBtn").mouseover(function () {
        clearTimeout(menuTimerId);
    })
})

function slideUpMenuWithContent() {
    menuSlideUp();
    slideUpAllCategoriesWrappers();
    slideUpAllMenuWrappers();
}

$(".nav-menu-item").click(function () {
    slideUpAllCategoriesWrappers();
    let wrapper = $(this.parentNode).find(".menu-item-content-wrapper");
    if ($(wrapper).css("display") === "none") {
        slideUpAllMenuWrappers();
    }
    $(this).toggleClass(navMenuItemActiveClassname);
    $(wrapper).slideToggle(animationTime)
});

$(categoryItems).click(function () {
    let wrapper  = $(this).find(".category-item-content-wrapper");
    if ($(wrapper).css("display") === "none") {
        slideUpAllCategoriesWrappers();
    }
    $(this).toggleClass("category-item-active")
    $(wrapper).slideToggle(animationTime)
});


function slideUpAllMenuWrappers() {
    $(menuItemWrappers).slideUp(animationTime);
    $(".nav-menu-item").removeClass(navMenuItemActiveClassname);
}

function slideUpAllCategoriesWrappers() {
    $(categoryItems).removeClass("category-item-active");
    $(".category-item-content-wrapper").slideUp(animationTime);
}


function menuSlideToggle() {
    $(menu).slideToggle(animationTime);
}

function menuSlideUp() {
    $(menu).slideUp(animationTime);
}


$("#filtersBtn").on("click", function () {
    $("#filters").toggleClass("d-none")
});


function clearDropdowSelection() {
    $(".nav-dropdown-menu").css("display", "none");
}

function popupFormFadeToggle() {
    $(".popup-bg").fadeToggle(animationTime);
}


$(document).ready(function () {
    if ($("*").is(mainMenu)) {
        readSelectedItemFromUrlParams();
        selectItem($(myOrdersTabMenu.items)[0], myOrdersTabMenu);
        selectItem($(receivedOrdersTabMenu.items)[0], receivedOrdersTabMenu);

        $(userMenu.items).click( function (e) {
            selectItem($(this), userMenu);
            e.preventDefault();
        });

        $(myOrdersTabMenu.items).click( function (e) {
            selectItem($(this), myOrdersTabMenu);
            e.preventDefault();
        });

        $(receivedOrdersTabMenu.items).click( function (e) {
            selectItem($(this), receivedOrdersTabMenu);
            e.preventDefault();
        });
    }
});


function selectItem(item, menu) {
    if ($(item)[0] !== menu.currentMenuItem) {
        let href = $(item).attr('href');
        $(menu.items).removeClass("selected-item");
        $(menu.content).fadeOut(animationTime);
        $(item).addClass("selected-item");
        setTimeout(function () {
            $(href).fadeIn(animationTime);
        }, animationTime);
        menu.currentMenuItem = $(item)[0];
        if (menu.paramName !== undefined) {
            setGetParam("item", href.substr(1))
        }
    }
}


function setGetParam(key,value) {
    if (history.pushState) {
        let params = new URLSearchParams(window.location.search);
        params.set(key, value);
        let newUrl = window.location.protocol + "//" + window.location.host + window.location.pathname + '?' + params.toString();
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
        let item = $(".main-menu-item[href='"+ menuItemHref +"']")[0];
        if (item !== undefined) {
            selectItem($(item), userMenu);
        } else {
            selectItem($(userMenu.items)[0], userMenu);
        }
    } catch (e) {
        selectItem($(userMenu.items)[0], userMenu);
    }
}


// enable bootstrap popowers
$(function () {
    $('#newProductForm [data-toggle="popover"]').popover({
        template: '' +
            '<div class="popover p-2" role="tooltip">' +
            '   <div class="arrow"></div>' +
            '   <div class="row align-items-center">\n' +
            '       <div class="col-auto">\n' +
            '           <img src="img/tip.png">\n' +
            '       </div>\n' +
            '       <div class="col popover-body p-0 pr-2"></div>\n' +
            '   </div>\n' +
            '</div>'
    })
})
