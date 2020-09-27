//jquery新版本不支持toggle()的解决方法
$.fn.toggle = function( fn, fn2 ) {
    var args = arguments,guid = fn.guid || $.guid++,i=0,
    toggle = function( event ) {
      var lastToggle = ( $._data( this, "lastToggle" + fn.guid ) || 0 ) % i;
      $._data( this, "lastToggle" + fn.guid, lastToggle + 1 );
      event.preventDefault();
      return args[ lastToggle ].apply( this, arguments ) || false;
    };
    toggle.guid = guid;
    while ( i < args.length ) {
      args[ i++ ].guid = guid;
    }
    return this.click( toggle );
};
//展开收缩
$(".filter-content-wrapper").css("display","none");
$(".advanced-filter").toggle(function(){
    $(".filter-content-wrapper").slideDown();
    $(this).find("i").removeClass("icon-chevron-down").addClass("icon-chevron-up");
    $(this).find("span").text("收起筛选");
},function(){
   $(".filter-content-wrapper").slideUp();
   $(this).find("i").removeClass("icon-chevron-up").addClass("icon-chevron-down");
   $(this).find("span").text("高级筛选");
})