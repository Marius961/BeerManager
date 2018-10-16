let toggleAnimationTime = 300,
    categoriesContainer = $(".categories-container"),
    scrollAnimationClock = 80,
    firefoxscrollAnimationClock = 40;




function showCategories() {
    let elem = $(".categories-wrapper");
    if ($(elem).css("display") === "none") {
        $(elem).slideDown(toggleAnimationTime);
    } else {
        $(categoriesContainer).stop().animate({scrollLeft: 0}, toggleAnimationTime);
        let togglingTimeOut = 0;
        if ($(categoriesContainer)[0].scrollLeft !== 0) {
            togglingTimeOut = toggleAnimationTime;
        }
        setTimeout(function () {
            $(elem).slideToggle(toggleAnimationTime);
        }, togglingTimeOut);
    }
}

$("#left").click(function () {
    let containerWidth = parseFloat($(categoriesContainer).css("width"));
    $(categoriesContainer).stop().animate({scrollLeft: "-=" + (containerWidth/100)*85}, 300);
});

$("#right").click(function () {
    let containerWidth = parseFloat($(categoriesContainer).css("width"));
    $(categoriesContainer).stop().animate({scrollLeft: "+=" + (containerWidth/100)*85}, 300);
});


$("#wrapper").click(function () {
    if ($(categoriesContainer).css("flex-wrap") === "nowrap") {
        let currentHeight = parseInt($(categoriesContainer).css("height"));
        $(categoriesContainer).css("flex-wrap", "wrap");
        let autoheight = $(categoriesContainer).css("height", "auto").height();
        $(categoriesContainer).css("height", currentHeight);
        $(".category").css("border", "0");
        $(categoriesContainer).animate({"height": "+" + (autoheight + 10)}, 150);
    } else {
        let currentHeight = parseInt($(categoriesContainer).css("height"));
        $(categoriesContainer).css("flex-wrap", "nowrap");
        let autoheight = $(categoriesContainer).css("height", "auto").height();
        $(categoriesContainer).css("height", currentHeight);

        $(categoriesContainer).animate({"height": autoheight + 10}, 150);
        setTimeout(function () {
            $(".category").css("border-left", "0.3ch solid black");
        }, 250);
    }
});

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
