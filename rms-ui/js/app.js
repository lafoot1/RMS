App = {Context: ''};
App.Context = {
  getValue: function(key) {
      return this[key];
  },
  setValue: function(key, value) {
      this[key] = value;
  },
  formatDate: function (date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var ampm = hours >= 12 ? 'pm' : 'am';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    minutes = minutes < 10 ? '0'+minutes : minutes;
    var strTime = hours + ':' + minutes + ' ' + ampm;
    return date.getMonth()+1 + "/" + date.getDate() + "/" + date.getFullYear() + "  " + strTime;
  }
};
App.Context.setValue('BASE_URL', 'http://localhost:8090/rms');
App.Context.setValue('REFERRAL_EP', App.Context.getValue('BASE_URL') + '/rest/secured/referral');
App.Context.setValue('CANDIDATE_EP', App.Context.getValue('BASE_URL') + '/rest/secured/candidate');
App.Context.setValue('REQUISITION_EP', App.Context.getValue('BASE_URL') + '/rest/secured/requisition');
App.Context.setValue('PAGE_SIZE', 10);
App.Context.setValue('MIN_SEARCH_TEXT_LEN', 3);

$(document).ready(function(){
  Backbone.history.start();

  $(".submenu > a").click(function(e) {
    e.preventDefault();
    var $li = $(this).parent("li");
    var $ul = $(this).next("ul");

    if($li.hasClass("open")) {
      $ul.slideUp(350);
      $li.removeClass("open");
    } else {
      $(".nav > li > ul").slideUp(350);
      $(".nav > li").removeClass("open");
      $ul.slideDown(350);
      $li.addClass("open");
    }
  });
  
});

