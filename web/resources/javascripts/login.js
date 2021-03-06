// ensure this points to the correct location for the spacer gif
Ext.BLANK_IMAGE_URL = "/cas/resources/images/default/s.gif";

Ext.onReady(function() {
    Ext.QuickTips.init();

    var resetPasswordForm = new Ext.form.FormPanel({
        labelWidth: 100,
        frame:true,
        bodyStyle:'padding:10px',
        width: 350,
        itemCls: 'spaced',
        defaults: {width: 180},
        defaultType: 'textfield',
        monitorValid: true,
         keys: [{key : [10,13],fn: function(){
            if(resetPasswordForm.getForm().isValid()) submitResetPassword();
        }}],
        items: [
            {fieldLabel: 'Username@Site', name: 'login', allowBlank:false},
            {fieldLabel: 'Email Address', name: 'email', allowBlank:false, vtype: 'email'}
        ],
        buttons: [{text: 'Request Reset', type: 'submit', formBind: true, handler: function() {submitResetPassword();} },
        {text: 'Cancel', handler: function() {resetPasswordWindow.hide(); resetPasswordForm.getForm().reset();}}]
    });

    var resetPasswordWindow = new Ext.Window({
        layout:'fit',
        height: 170,
        title: 'Reset Password',
        closeAction:'hide',
        modal: true,
        resizable: false,
        items: resetPasswordForm
    });

    resetPasswordWindow.on('show', function(win){
        var form = resetPasswordForm.getForm();
        form.findField('login').focus(false,600);
    });

    var submitResetPassword = function() {
        resetPasswordWindow.hide();
        var form = resetPasswordForm.getForm();
        var login = form.findField('login').getValue();
        var email = form.findField('email').getValue();
        // clear out the form in case they open it again
        form.reset();

        Ext.Ajax.request({
            url: 'requestReset.json',
            params: {login: login, email: email},
            callback: function(options, success, response) {
                var obj = Ext.decode(response.responseText);

                if (obj.success === true) {
                    Ext.MessageBox.alert('Reset Request Submitted',
                            'Your reset request has been submitted.<br/> Once confirmed, you will receive an email with a new temporary password.');
                } else {
                    Ext.MessageBox.alert('Password Reset Failed', obj.error);
                }
            }
        });
    };

    Ext.fly('username').focus();
    Ext.fly('forgotPasswordLink').on('click', function() { resetPasswordWindow.show(); return false; });
});

