var Referrals = Backbone.Collection.extend({
    url: App.Context.getValue('REFERRAL_EP'),
    //url: './js/data/dashboard.json',
  	parse : function(response) {
      this.pages = response.pages;
      this.currentPage = response.currentPage;
      return response.collection;  
	},
    pages: {},
    currentPage: {}
});