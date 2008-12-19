
Ext.onReady(function() {

    Ext.BLANK_IMAGE_URL = '../ext/resources/images/default/s.gif';

    $('#userList').css('padding-top','5px');

    var myData = [
        ['Smith','Eve', 'esmith@alphaomega.com', true, '2008-12-08'],
        ['Smith','Adam', 'asmith@alphaomega.com', true, '2008-12-10'],
        ['Weibust','Erik', 'erik@weibust.net', true, '2008-11-14'],
        ['Rollins','Larry', 'larry@hotmail.com', true, '2008-12-07'],
        ['Richards','Alice', 'alice.richards@myorg.net', true, '2008-11-27'],
        ['Burns','Edward', 'eburns@bigmoney.com', true, '2008-11-29'],
        ['Campos','Mario', 'mario@smallgiver.net', true, '2008-12-08'],
        ['Burton','Louise', 'lburton@mac.com', true, '2008-12-10'],
        ['Swift','Jonathan', 'jswift@writersblock.com', true, '2008-12-05'],
        ['Parker','Jessica', 'jessica@topcoders.com', true, '2008-12-13'],
        ['Peters','Terry', 'motivator@conference.com', true, '2008-12-06'],
        ['MacKenzie','Sue', 'bigmac@mackenzie.net', true, '2008-12-03'],
        ['Snyder','Mark', 'msnyder@bigmoney.com', true, '2008-12-08'],
        ['Twist','Wendle', 'wtwist@alphaomega.com', true, '2008-12-10'],
        ['Feldman','Mark', 'mfeldman@bigmoney.com', true, '2008-12-08'],
        ['Feldman','Susan', 'mfeldman@bigmoney.com', true, '2008-12-09'],
        ['Hunter','Fisher', 'fisher_hunter@trapper.com', true, '2008-12-10'],
        ['Rainer','Brittney', 'brainer@me.com', true, '2008-11-28'],
        ['Wilson','Kevin', 'kwilson@hotmail.com', true, '2008-12-11'],
        ['Parsons','Alan', 'aparsons@rhaposdy.com', true, '2008-12-06'],
        ['King','Janet', 'jking@gmail.com', true, '2008-12-02'],
        ['Neilson','Carly', 'c.neilson@linkedup.com', true, '2008-12-09'],
        ['Reilly','Oscar', 'oreilly@books.com', true, '2008-12-06'],
        ['Press','Kim', 'kpress@gmail.com', true, '2008-11-02'],
        ['Clements','Roberto', 'rclemens@gmail.com', false, '2008-12-05']
    ];

    var store = new Ext.data.SimpleStore({
        fields: [
           {name: 'lastName'},
           {name: 'firstName'},
           {name: 'email'},
           {name: 'active', type: 'boolean'},
           {name: 'lastLogin', type: 'date', dateFormat: 'Y-m-d'}
        ],
        sortInfo:{field: 'lastName', direction: "ASC"},
        data: myData
    });


    // custom column plugin example
    var checkColumn = new Ext.grid.CheckColumn({
       header: "Active",
       dataIndex: 'active',
       width: 60,
       sortable: false
    });

// create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {header: "Last Name", width: 120, sortable: true, dataIndex: 'lastName'},
            {header: "Fist Name", width: 120, sortable: true, dataIndex: 'firstName'},
            {id: "emailColumn", header: "Email", width: 200, sortable: true, dataIndex: 'email'},
            checkColumn,
            {header: "Last Login", width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastLogin'}
        ],
        sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
        plugins: checkColumn,
        height:400,
        width:650,
        frame: false,
        listeners: {
            rowdblclick: function(grid, row, evt) {
                var rec = grid.getSelectionModel().getSelected();
                window.location.href = "edit-user.html?firstName=" + rec.data.firstName + "&lastName=" + rec.data.lastName;
            }


        },
        buttonAlign: 'center',
        buttons: [{text: 'Save Changes'},{text: 'Cancel'}],
        autoExpandColumn: "emailColumn"
    });

    grid.render('userList');

});


Ext.grid.CheckColumn = function(config){
    Ext.apply(this, config);
    if(!this.id){
        this.id = Ext.id();
    }
    this.renderer = this.renderer.createDelegate(this);
};

Ext.grid.CheckColumn.prototype ={
    init : function(grid){
        this.grid = grid;
        this.grid.on('render', function(){
            var view = this.grid.getView();
            view.mainBody.on('mousedown', this.onMouseDown, this);
        }, this);
    },

    onMouseDown : function(e, t){
        if(t.className && t.className.indexOf('x-grid3-cc-'+this.id) != -1){
            e.stopEvent();
            var index = this.grid.getView().findRowIndex(t);
            var record = this.grid.store.getAt(index);
            record.set(this.dataIndex, !record.data[this.dataIndex]);
        }
    },

    renderer : function(v, p, record){
        p.css += ' x-grid3-check-col-td';
        return '<div class="x-grid3-check-col'+(v?'-on':'')+' x-grid3-cc-'+this.id+'">&#160;</div>';
    }
};
