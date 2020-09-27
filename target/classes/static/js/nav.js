$(function(){
    // nav收缩展开
    $(document).on('click', '.nav-item>a', function(){
        if (!$('.nav').hasClass('nav-mini')) {
            if ($(this).next().css('display') == "none") {
                //展开未展开
                $('.nav-item').children('ul').slideUp(300);
                $(this).next('ul').slideDown(300);
                $(this).parent('li').addClass('nav-show').siblings('li').removeClass('nav-show');
            }else{
                //收缩已展开
                $(this).next('ul').slideUp(300);
                $('.nav-item.nav-show').removeClass('nav-show');
            }
        }
    });
    //切换当前选中高亮
    $(document).on('click', '.nav-item>ul>li', function(){
    	$(this).addClass('active').siblings().removeClass('active');
    	$(this).parent('ul').parent('li').siblings().children('ul').children('li').removeClass('active');
    });
    //nav-mini切换
    $('#mini').on('click',function(){
        if (!$('.nav').hasClass('nav-mini')) {
            $('.nav-item.nav-show').removeClass('nav-show');
            $('.nav-item').children('ul').removeAttr('style');
            $('.nav').addClass('nav-mini');
            //jqGrid宽度自适应
            if($('#myjqgridwrapper').length) $('#jqGrid').setGridWidth($('#myjqgridwrapper').width()+130);
        }else{
            $('.nav').removeClass('nav-mini');
            //jqGrid宽度自适应
            if($('#myjqgridwrapper').length) $('#jqGrid').setGridWidth($('#myjqgridwrapper').width()-130);
        }
    });
});