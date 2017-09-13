var RequisitionView = Backbone.View.extend({
    el: '.mainView',
    pageNo: 1,
    pageSize: App.Context.getValue('PAGE_SIZE'),
    query: '',
    minSearchTextLen: App.Context.getValue('MIN_SEARCH_TEXT_LEN'),
    requisitions:{},
    render: function() {
        var that = this;
        var q = $("input.search").val();
        if (q && q.length >= this.minSearchTextLen) {
          this.query = q;
        }
        this.requisitions = new Requisitions();
        var search={"pageNo": this.pageNo-1, "pageSize": this.pageSize, "query": this.query};
        this.requisitions.fetch({
            type: "post",
            headers: {
                "Content-Type":"application/json",
                "Accept":"application/json"
            },
            data: JSON.stringify(search),
            success: function(requisitions) {
                var html = render('requisition-template', {requisitions: requisitions.attributes, pages: requisitions.pages, currentPage: requisitions.currentPage, query: that.query});
                that.$el.html(html); 
            }
        });      
    },
    events: {
        "click .paginate": "paginate",
        "click .requisition-tr": "rowClicked",
        "click button.btnSearch": "search"
    },
    paginate: function(evt) {
        this.pageNo = parseInt($(evt.target).text());
        this.render();
    },
    rowClicked: function(evt) {
        var id = $(evt.currentTarget.cells[0]).find("span").text();
        window.document.location='#requisitionDetail/'+id;
    },
    search: function(evt) {
      this.pageNo = 1;
      this.render();
    }

});
