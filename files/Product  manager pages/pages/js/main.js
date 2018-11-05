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


$("#filtersToggle").on("click", function () {
    let filtersContainer = $("#filters");
    if ($(filtersContainer).hasClass("d-none")) {
        $(filtersContainer).removeClass("d-none");
    } else {
        $(filtersContainer).addClass("d-none");
    }
});

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



