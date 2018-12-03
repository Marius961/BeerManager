$("#menu").on("click", function () {
    $("#menuContent").slideToggle(150);
    });

$("#categoriesBtn").click(function () {
    clearDropdowSelection();
    $(".categories-segment").slideToggle(animationTime);

});

$(".subcategory-box").hover(function (e) {
    let container = $(this).find(".subcategories-container");
    toggleCategoryVisibility(container);
});


$("#filtersToggle").on("click", function () {
    let filtersContainer = $("#filters");
    if ($(filtersContainer).hasClass("d-none")) {
        $(filtersContainer).removeClass("d-none");
    } else {
        $(filtersContainer).addClass("d-none");
    }
});


function clearCategorySelection() {
    $(".subcategories-container").each(function (index, element) {
        if (!$(element).hasClass("d-none")) {
            $(element).addClass("d-none");
        }
    });
}

function closeCategoriesMenu() {
    let elem = $(".categories-segment");
    $(elem).slideUp(animationTime);
}

function toggleCategoryVisibility(container) {
    if (!$(container).hasClass("d-none")) {
        $(container).addClass("d-none");
    } else {
        clearCategorySelection();
        $(container).removeClass("d-none");
    }
}