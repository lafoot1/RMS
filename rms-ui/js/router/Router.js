var sidebarView = new SidebarView();
var dashboardView = new DashboardView();
var uploadView = new UploadView();
var referralDetailView = new ReferralDetailView();
var requisitionView = new RequisitionView();

 var Router = Backbone.Router.extend({
    routes: {
        '': 'home',
        'upload': 'upload',
        'referralDetail/:id': 'referral',
        'requisition': 'requisition'
    }
});

var router = new Router();

router.on('route:home', function() {
	sidebarView.render(1);
    dashboardView.render();
});
router.on('route:requisition', function() {
    sidebarView.render(2);
    requisitionView.render();
});
router.on('route:upload', function() {
    sidebarView.render(3);
    uploadView.render();
});
router.on('route:referral', function(id) {
    sidebarView.render();
    referralDetailView.render(id);
});