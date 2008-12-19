
Ext.onReady(function() {

    Ext.BLANK_IMAGE_URL = '../ext/resources/images/default/s.gif';

    $('#userList').css('padding-top','5px');

    var myData = [
        ['Smith','Eve', 'esmith@alphaomega.com', false, '2008-12-08'],
        ['Clements','Roberto', 'rclemens@gmail.com', false, '2008-12-05']
    ];

    var store = new Ext.data.SimpleStore({
        fields: [
           {name: 'lastName'},
           {name: 'firstName'},
           {name: 'email'},
           {name: 'unlock', type: 'boolean'},
           {name: 'lastLogin', type: 'date', dateFormat: 'Y-m-d'}
        ],
        data: myData
    });


    // custom column plugin example
    var checkColumn = new Ext.grid.CheckColumn({
       header: "Unlock",
       dataIndex: 'unlock',
       width: 60
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
        listeners: {
            rowdblclick: function(grid, row, evt) {
                var rec = grid.getSelectionModel().getSelected();
                window.location.href = "edit-user.html?firstName=" + rec.data.firstName + "&lastName=" + rec.data.lastName;
        }},
        height:400,
        width:650,
        frame: false,
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
