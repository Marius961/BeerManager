let animationTime = 150;



/*
    * Method for calculating price
*/
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


/*
    * Dropdown methods
    * dropdonw classws: p-drop-btn, p-dropdown,
    * classes must the same parent node
*/

$(".p-drop-btn").on("click", function () {
    let menu = $(this.parentNode).find(".p-dropdown");
    if ($(menu).css("display") === "none") {
        clearDropdowSelection();
        $(menu).css("display", "inline-block")
    } else {
        $(menu).css("display", "none")
    }
});

$(".p-dropdown").mouseleave(function () {
    $(this).css("display", "none")
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



$("#menuBtn").click(function () {
    clearDropdowSelection();
    slideUpAllCategoriesWrappers();
    slideUpAllMenuWrappers();
    menuSlideToggle();
});






//
// $(".category-item-box").mouseover(function () {
//
//     let subcategoriesContainer = $(this.parentNode).find(".subcategories");
//     removeAllCategoryActiveClasses();
//     hideAllSubcategories();
//     addCategoryActiveClass(this);
//     showSubcategories(subcategoriesContainer);
//     $(subcategoriesContainer).mouseleave(function () {
//         hideSubcategories(subcategoriesContainer);
//         removeAllCategoryActiveClasses();
//     })
// });


$(".menu-item").click(function () {
    slideUpAllCategoriesWrappers();
    let wrapper = $(this.parentNode).find(".menu-item-content-wrapper");
    if ($(wrapper).css("display") === "none") {
        slideUpAllMenuWrappers();
    }
    $(this).toggleClass("menu-item-active");
    $(wrapper).slideToggle(animationTime)
});

$(".category-item").click(function () {
    let wrapper  = $(this).find(".category-item-content-wrapper");
    if ($(wrapper).css("display") === "none") {
        slideUpAllCategoriesWrappers();
    }
    $(wrapper).slideToggle(animationTime)
})


function slideUpAllMenuWrappers() {
    $(".menu-item-content-wrapper").slideUp(animationTime);
    $(".menu-item").removeClass("menu-item-active");
}

function slideUpAllCategoriesWrappers() {
    $(".category-item-content-wrapper").slideUp(animationTime);
}

//
// $(".categories-segment").mouseleave(function () {
//     hideAllSubcategories();
//     let menuTimerId = setTimeout(function () {
//         menuSlideToggle()
//     }, 500 );
//     $(this).mouseenter(function () {
//         clearTimeout(menuTimerId);
//     })
// });
//
//
//
// function showSubcategories(container) {
//     $(container).removeClass("d-none");
// }
//
// function hideSubcategories(container) {
//     $(container).addClass("d-none");
// }
//
// function hideAllSubcategories() {
//     $(".subcategories").addClass("d-none");
// }
//
// function addCategoryActiveClass(category) {
//     $(category).addClass("c-menu-item-box-active");
// }
//
// function removeAllCategoryActiveClasses() {
//     $(".c-menu-item-box-active").removeClass("c-menu-item-box-active");
// }
//
function menuSlideToggle() {
    $("#menu").slideToggle(animationTime);
}





$("#filtersToggle").on("click", function () {
    let filtersContainer = $("#filters");
    if ($(filtersContainer).hasClass("d-none")) {
        $(filtersContainer).removeClass("d-none");
    } else {
        $(filtersContainer).addClass("d-none");
    }
});

function clearDropdowSelection() {
    $(".p-dropdown").css("display", "none");
}

function popupFormFadeToggle() {
    $(".popup-bg").fadeToggle(animationTime);
}