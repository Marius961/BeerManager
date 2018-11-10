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
* classes must the same parent node*/

$(".p-drop-btn").on("click", function () {
    let menu = $(this.parentNode).find(".p-dropdown");
    if ($(menu).css("display") === "none") {
        clearDropdowSelection();
        $(menu).css("display", "inline-block")
    } else {
        $(menu).css("display", "none")
    }
});


function clearDropdowSelection() {
    $(".p-dropdown").css("display", "none");
}


