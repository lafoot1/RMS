var Candidate = Backbone.Model.extend({
	//urlRoot: './js/data/1.json'
	urlRoot: App.Context.getValue('CANDIDATE_EP')
});