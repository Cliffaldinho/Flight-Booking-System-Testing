$(document).ready(function (e) {
    var startAngle = 180;
    var unit = 155  ;

    var animate = function () {
        var rad = startAngle * (Math.PI / 180);

        $('.circle').css({
            left: $(window).width()/2.3 + Math.cos(rad) * unit + 35 + 'px',
            //top:  $(window).height()/35 + unit  * (1 - Math.sin(rad))   + 35  + 'px',
            top:  0 + unit  * (1 - Math.sin(rad))   + 35  + 'px',
            transform : 'rotate( ' + -startAngle +'deg)'
        })

        startAngle--;

    };
    var rotation = 0;

    jQuery.fn.rotate = function(degrees) {
        $(this).css({'transform' : 'rotate('+ -degrees +'deg)'});
        return $(this);
    };

    var timer = setInterval(animate, 10);
});