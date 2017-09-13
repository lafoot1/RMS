var SidebarView = Backbone.View.extend({
    el: '.sidebar',
    current: '',
    render: function(index) {
    	this.current = index?index:1;
    	var that = this;    	
        var html = render('sidebar-template');
        that.$el.html(html);

        $.each($("ul.nav > li"), function(index, item) {
    		if (that.current == index+1) {
    			$(item).addClass('current');	
    		} else {
    			$(item).removeClass('current');	
    		}
    	});
    }
});