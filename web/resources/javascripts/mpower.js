$(document).ready(function() {

    $('#dialog').jqm({overlay:10}).jqDrag($('.jqmWindow h4'));

    
    $("div.navGroup:not(:has('a.active'))").hoverIntent({
        sensitivity: 7,
        interval: 100,
        over: function() {
            $(this).find("span.secondary").filter(":hidden").slideDown();
        },
        timeout: 1000
    });

    // Drop-down menu logic, ensure the drop-down panel of
    // menu items is at least slightly larger than the button
    // that dropped down the panel
    $(".primaryNav li ul").each(function() {
        var btnWidth = $(this).prev("a").outerWidth() + 20;
        var selfWidth = $(this).outerWidth();
        if (btnWidth > selfWidth) {
            $(this).width(btnWidth);
        }
    });

    // Drop-down menu logic, switches selectors of the top-level
    // menu item and toggles classes to show/hide dropdown
    $(".primaryNav li:has(ul)").hover(function() {
        $(this).find("ul").show().prev("a").addClass("bactive");
    }, function() {
        $(this).find("ul").hide().prev("a").removeClass("bactive");
    });


});