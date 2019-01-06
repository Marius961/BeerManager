let animationTime = 150;




/*
    * Method for calculating price
*/
$("#measurementInput").on("input", function () {
    let sumBox = $(".sum-box");
    if ($(this).val() !== "") {
        let sum = +$("#price").text() * +$(this).val();
        $(sumBox).find("#sum").text(sum.toFixed(2) + "грн");
        $(sumBox).fadeIn(510);
    } else {
        $(sumBox).fadeOut(300);
    }
});


/*
    * Dropdown methods
    * dropdonw classws: p-drop-btn, p-dropdown,
    * classes must the same parent node
*/

$(".p-drop-btn").on("click", function () {
    let menu = $(this.parentNode).find(".p-dropdown");
    if ($(menu).css("display") === "none") {
        clearDropdowSelection();
        closeCategoriesMenu();
        $(menu).css("display", "inline-block")
    } else {
        $(menu).css("display", "none")
    }
});


$("#closeAddressFormBtn").on("click", function () {
    popupFormFadeToggle();
});

$("#addAddressBtn").click(function () {
    popupFormFadeToggle();
});


/*
    * Resize address form window popup
*/
$(".popup-bg").ready(function () {
    $(".popup-bg").css('height', $(document).height());
    $(window).resize(function () {
        $(".popup-bg").css('height', $(document).height())
    });
});


/*
    * Toggle address form visibility
*/

$("#menu").on("click", function () {
    $("#menuContent").slideToggle(150);
});

$("#categoriesBtn").click(function () {
    clearDropdowSelection();
    categoriesMenuSlideToggle();
});

$(".c-menu-item-box").mouseover(function (e) {
    activateCategory(this);
});

$(".subcategories").mouseleave(function () {
    deactivateCategory(this);
});

$(".categories-segment").mouseleave(function () {
    deactivateCategory($(".subcategories"));
});

$("#filtersToggle").on("click", function () {
    let filtersContainer = $("#filters");
    if ($(filtersContainer).hasClass("d-none")) {
        $(filtersContainer).removeClass("d-none");
    } else {
        $(filtersContainer).addClass("d-none");
    }
});

function closeCategoriesMenu() {
    let menuBtn = $("#categoriesBtn");
    if($(menuBtn)[0] !== undefined) {
        let elem = $(".categories-segment");
        if ($(elem).css("display") === "flex") {
            categoriesMenuSlideToggle();
        }
    }
}

function showSubcategories(container) {
    $(container).removeClass("d-none");
}

function hideSubcategories(container) {
    $(container).addClass("d-none");
}

function hideAllSubcategories() {
    $(".subcategories").addClass("d-none");
}

function addCategoryActiveClass(category) {
    $(category).addClass("c-menu-item-box-active");
}

function cleanActiveCategories() {
    $(".c-menu-item-box-active").removeClass("c-menu-item-box-active");
}


function activateCategory(category) {
    let subcategoriesContainer = $(category.parentNode).find(".subcategories");
    cleanActiveCategories();
    hideAllSubcategories();
    addCategoryActiveClass(category);
    showSubcategories(subcategoriesContainer);
}

function deactivateCategory(category) {
    hideSubcategories(category);
    cleanActiveCategories();
}


function popupFormFadeToggle() {
    $(".popup-bg").fadeToggle(150);
}

function clearDropdowSelection() {
    $(".p-dropdown").css("display", "none");
}

function categoriesMenuSlideToggle() {
    $(".categories-segment").animate({width:'toggle'},animationTime);
}