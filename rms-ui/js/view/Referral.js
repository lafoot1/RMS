var ReferralDetailView = Backbone.View.extend({
    el: '.mainView',
    referral: {},
    render: function(id) {
        var that = this;
        if (id) {
            this.referral = new Referral({id: id});
            this.referral.fetch({
                success: function(referral) {
                    that.baseRender(referral);
                }
            });
        } else {
            that.baseRender(this.referral);
        }
        return this;
    },
    events: {
        "click .panel-heading": "accordion",
        "click .save": "save",
        "click .saveReferral": "saveReferral",
        "click .edit-but": "showEdit",
        "click .edit-status": "showStatusEdit",
        "change .wfStatus": "showField",
        "click .cancel-status": "cancelStatusUpdate",
        "click .cancel-candidate-update": "cancelCandidateUpdate"
    },
    accordion: function(evt) {
        //console.log($(evt.target).parent().find('a[data-toggle=collapse]').html());
        //$(evt.target).parent().find('a[data-toggle=collapse]').click();
    },
    save: function(evt) {
        var form = $(evt.target).parent();
        var that = this;
        var candidateObj = that.referral.get("candidate");

        $(form).find("input, select").each(function(index, elem) {
            var property = $(elem).attr('name');
            candidateObj[property] = $(elem).val();
        });
        var candidate = new Candidate(candidateObj);
        candidate.save({},
            {
                success: function(result) {
                    that.referral.set("candidate",result.attributes);
                    that.render();
                    console.log(that.referral.get("candidate").genderDesc);
                    //that.showView();
                },
                error: function(result) {
                    console.log("error");
                    console.log(result);
                }
            })
        ;
    },
    saveReferral: function(evt) {
        var form = $(evt.target).parent().parent();
        var that = this;
        var referralObj = that.referral;

        $(form).find("input, select").each(function(index, elem) {
            var property = $(elem).attr('name');
            referralObj.get('wfStatus')[property] = $(elem).val();
        });
        this.referral.save({}, {
            success: function(result) {
                that.referral = result;
                that.render();
                //that.showView();
            },
            error: function(result) {
                console.log("error");
                console.log(result);
            }
        });
    },
    baseRender: function(referral) {
        templateName = 'referral-detail-template';
        var html = render(templateName, {referral: referral, formatDate: App.Context.formatDate});
        this.$el.html(html); 
    },
    showEdit: function() {
        $(".edit-details").show();
        $(".view-details").hide();
    },
    showView: function() {
        $(".edit-details").hide();
        $(".view-details").show();
    },
    showStatusEdit: function() {
        $(".edit-status-info").show();
        $(".show-hide").hide();
    },
    showField: function(evt){
        var currentStatus = $('.wfStatus').val();
        var x = $('.wfStatus').text()
        //var inProcess = "in_process";
        if(currentStatus >= 4){
          $(".in-progress").show();
        }else{
            $(".in-progress").hide();
        }
    },
    cancelStatusUpdate: function(){
        $(".edit-status-info").hide();
        $(".show-hide").show();
    },
    cancelCandidateUpdate: function(){
        $(".edit-details").hide();
        $(".view-details").show();
    }
});