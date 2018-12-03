let animationTime = 200;




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
        clearCategorySelection();
        closeCategoriesMenu();
        $(menu).css("display", "inline-block")
    } else {
        $(menu).css("display", "none")
    }
});

function clearDropdowSelection() {
    $(".p-dropdown").css("display", "none");
}

$("#closeAddressFormBtn").on("click", function () {
    popupFormFadeToggle();
});

$("#addAddressBtn").click(function () {
    popupFormFadeToggle();
});


/*
    * Resize address form window popu[]
*/
$(".popup-bg").ready(function () {
    $(".popup-bg").css('height', $(document).height())
    $(window).resize(function () {
        $(".popup-bg").css('height', $(document).height())
    });
});


/*
    * Toggle address form visibility
*/
function popupFormFadeToggle() {
    $(".popup-bg").fadeToggle(150);
}


