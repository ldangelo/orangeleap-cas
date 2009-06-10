Ext.BLANK_IMAGE_URL = "/cas/resources/images/default/s.gif";

Ext.onReady(function() {

    Ext.QuickTips.init();

    var viewport = new Ext.Viewport({

        layout: 'border',
        hideBorders: true,
        items: [
            {
                region: 'north',
                autoHeight: true,
                contentEl: 'header'
            },
            {
                region: 'center',
                contentEl: 'center'
            },
            {
                region: 'south',
                contentEl: 'footer'
            }
        ]
    });

    Ext.get('centerDiv').boxWrap();

    Ext.select('input[type=text]').on('focus', function() {
        Ext.fly(this).prev('label').addClass('focused');
    }).on('blur', function() {
        Ext.fly(this).prev('label').removeClass('focused');
    });

    Ext.select('input[type=password]').on('focus', function() {
        Ext.fly(this).prev('label').addClass('focused');
    }).on('blur', function() {
        Ext.fly(this).prev('label').removeClass('focused');
    });


    Ext.select('input:first').focus();

});
