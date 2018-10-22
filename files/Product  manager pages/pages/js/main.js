let toggleAnimationTime = 200;



function showCategories() {
    let elem = $(".categories-wrapper");
    if ($(elem).css("display") === "none") {
        $(elem).slideDown(toggleAnimationTime);
    } else {
        $(elem).slideToggle(toggleAnimationTime);
    }
}


$(".category").hover(function () {
    let container = $(this).find(".subcategories-container");
    if (!$(container).hasClass("d-none")) {
        $(container).addClass("d-none");
    } else {
        $(".subcategories-container").each(function (index, element) {
            if (!$(element).hasClass("d-none")) {
                $(element).addClass("d-none");
            }
        });
        $(container).removeClass("d-none");
    }
});


