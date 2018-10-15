let togleAnimationTime = 300,
    categoriesContainer = $(".categories-container"),
    scrollAnimationClock = 80,
    firefoxscrollAnimationClock = 40;




function showCategories() {
    let elem = $(".categories-wrapper");
    if ($(elem).css("display") === "none") {
        $(elem).slideDown(togleAnimationTime);
    } else {
        $(categoriesContainer).stop().animate({scrollLeft: 0}, 300);
        let togglingTimeOut = 0;
        if ($(categoriesContainer)[0].scrollLeft !== 0) {
            togglingTimeOut = togleAnimationTime;
        }
        setTimeout(function () {
            $(elem).slideToggle(togleAnimationTime);
        }, togglingTimeOut);
    }
}

$(document).ready(function() {
    $(categoriesContainer).bind('mousewheel', function(e) {
        e.preventDefault();
        let containerWidth = parseFloat($(categoriesContainer).css("width"));
        // noinspection JSCheckFunctionSignatures
        $(categoriesContainer).stop().animate({scrollLeft: String('-=' + (-containerWidth / e.originalEvent.wheelDelta)*scrollAnimationClock)}, 300);
    });
    $(categoriesContainer).bind('mousewheel', function(e) {
        e.preventDefault();
        let containerWidth = parseFloat($(categoriesContainer).css("width"));
        // noinspection JSCheckFunctionSignatures
        $(categoriesContainer).stop().animate({scrollLeft: String('+=' + (-containerWidth / e.originalEvent.wheelDelta)*firefoxscrollAnimationClock)}, 300);
    });
    // $('.categories-container').bind('DOMMouseScroll', function(e) {//Speacially for Mozilla
    //     this.scrollLeft += (e.originalEvent.detail*40);//Multiply by 40 because Mozilla DOMMouseScroll speed is 3px at time to match scrolling speed of chrome(speed of scrolling 120px at time)
    // });

});
