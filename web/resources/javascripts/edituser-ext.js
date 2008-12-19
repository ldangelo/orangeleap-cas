Ext.onReady(function() {

    Ext.BLANK_IMAGE_URL = '../ext/resources/images/default/s.gif';

    var qs = document.location.search;
    var params = {lastName: 'Weibust', firstName: 'Erik'};

    if(qs.length > 1) {
         params = Ext.urlDecode(qs.substr(1));
    }

    Ext.get('lastName').dom.value = params.lastName;
    Ext.get('firstName').dom.value = params.firstName;

});