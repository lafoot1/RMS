var DashboardView = Backbone.View.extend({
    el: '.mainView',
    pageNo: 1,
    pageSize: App.Context.getValue('PAGE_SIZE'),
    query: '',
    minSearchTextLen: App.Context.getValue('MIN_SEARCH_TEXT_LEN'),
    currentid: -1,
    wfComment: '',
    referrals:{},
    render: function() {
        var that = this;
        var q = $("input.search").val();
        if (q && q.length >= this.minSearchTextLen) {
          this.query = q;
        }
        this.referrals = new Referrals();
        var search={"pageNo": this.pageNo-1, "pageSize": this.pageSize, "query": this.query};
        this.referrals.fetch({
            type: "post",
            headers: {
                "Content-Type":"application/json",
                "Accept":"application/json"
            },
            data: JSON.stringify(search),
            success: function(referrals) {
                var html = render('dashboard-template', {referrals: referrals.models, pages: referrals.pages, currentPage: referrals.currentPage, query: that.query});
                that.$el.html(html); 
            }
        });      
    },
    events: {
        "click .paginate": "paginate",
        "click .referral-tr": "rowClicked",
        "click button.btnSearch": "search",
        "click .btn-deleted": "reject",
        "click .reject-save": "saveReject",
        "click .btn-close": "resetModal"
    },
    paginate: function(evt) {
        this.pageNo = parseInt($(evt.target).text());
        this.render();
    },
    rowClicked: function(evt) {
        var id = $(evt.currentTarget.cells[0]).find("span").text();
        if(!evt.target.classList.contains('btn')){
            window.document.location='#referralDetail/'+id;
        }
    },
    search: function(evt) {
      this.pageNo = 1;
      this.render();
    },
    reject: function(evt){
       this.currentid = evt.target.dataset.id;       
    },
    saveReject: function(evt) {
        if (this.currentid == -1) {
            return;
        }

        var form = $(evt.currentTarget).closest("form");
        this.wfComment = $(form).find(':input').val();
        var that = this;

        var referral = new Referral({id: this.currentid});
        referral.fetch({
            success: function(referral) {
                var wfStatus = referral.get("wfStatus");
                wfStatus.targetPaths.forEach(function(item, index) {
                    if ("rejected" == item.wfStatus) {
                        wfStatus.id = item.id;
                    }
                });
                referral.save({wfStatus: wfStatus, wfComment: that.wfComment});
                document.getElementsByName('comment')[0].value = "";
                //remove the reject option
                that.render();
            }
        });
    },
    resetModal: function(evt){
        document.getElementsByName('comment')[0].value = "";
    }

});

function render(tmpl_name, tmpl_data) {
    if ( !render.tmpl_cache ) { 
        render.tmpl_cache = {};
    }

    if ( ! render.tmpl_cache[tmpl_name] ) {
        var tmpl_dir = '/templates';
        var tmpl_url = tmpl_dir + '/' + tmpl_name + '.html';
        var tmpl_string;
        $.ajax({
            url: tmpl_url,
            method: 'GET',
            async: false,
            success: function(data) {
                tmpl_string = data;
            }
        });

        render.tmpl_cache[tmpl_name] = _.template(tmpl_string);
    }
    return render.tmpl_cache[tmpl_name](tmpl_data);
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}