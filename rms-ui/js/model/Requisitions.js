var Requisitions = Backbone.Model.extend({
	url: App.Context.getValue('REQUISITION_EP'),
  	parse : function(response) {
      this.pages = response.pages;
      this.currentPage = response.currentPage;
      return response.collection;  
	},
	pages: {},
  currentPage: {}
});