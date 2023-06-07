$(document).ready(function(){
    mainSlide();
    subSlide();
});

function mainSlide(){
    $('.mainSlide').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 5000,
        infinite: true,
        dots: true,
        useTransform: false 
      });
}

function subSlide(){
    $('.subSlider').slick({
        centerMode: true,
        centerPadding: '20px',
        slidesToShow: 5,
        autoplay: true,
        autoplaySpeed: 4000,
        infinite: true,
        variableWidth: true
    });
}