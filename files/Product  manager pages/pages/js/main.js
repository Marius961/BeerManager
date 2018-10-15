function showCategories() {
    let elem = $(".categories-wrapper");
    if ($(elem).css("display") === "none") {
        $(elem).slideDown(300);
    } else {
        $(elem).slideToggle(300);
    }
}

