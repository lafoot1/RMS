var UploadView = Backbone.View.extend({
    el: '.mainView',
    render: function() {
        var that = this;
        var html = render('upload-template');
        that.$el.html(html); 
    },
    events: {
        "click #upload": "upload",
        "click .close": "closeAlert"
    },
    closeAlert: function() {
    	$('.alert').removeClass('in');
    },
    upload: function() {
    	var that = this;
        $.ajax({
          type: "POST",
          url: App.Context.getValue('BASE_URL') + '/rest/secured/referral/upload',
	        data: new FormData($('form')[0]),
	        cache: false,
	        contentType: false,
	        processData: false,
	        xhr: function() {
	            var myXhr = $.ajaxSettings.xhr();
	            if (myXhr.upload) {
	                // For handling the progress of the upload
	                myXhr.upload.addEventListener('progress', function(e) {
	                    if (e.lengthComputable) {
	                        $('progress').attr({
	                            value: e.loaded,
	                            max: e.total,
	                        });
	                    }
	                } , false);
	            }
	            return myXhr;
	        },
	        success: function(result) {
	        	$('.alert').addClass('in');
	        	if (result.length > 0) {
	        		$(".uploadError").text("No. of Failed records: "+result.length);
	        		var ids = ' ';
	        		result.forEach(function(item) {
	        			ids += item.candidate.rmsId + ' ';
	        		});
	        		$(".uploadErrorIds").text("Failed Ids: "+ids);
	        	}
	        	
				//$(".alert").delay(200).addClass("in").fadeOut(20000);
	        },
	        error: function(msg) {
	        	console.log(msg);
	        }
	    });
    }
});