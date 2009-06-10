Ext.BLANK_IMAGE_URL = "/cas/resources/images/default/s.gif";

Ext.onReady(function(){

    Ext.QuickTips.init();

    var viewport = new Ext.Viewport({

        layout: 'border',
        hideBorders: true,
        items: [{
            region: 'north',
            autoHeight: true,
            contentEl: 'header'
        }, {
            region: 'center',
            contentEl: 'center'
        }, {
            region: 'south',
            contentEl: 'footer'}]
    });

    Ext.get('centerDiv').boxWrap();

});
