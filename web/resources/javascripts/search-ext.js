Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../ext/resources/images/default/s.gif';

    var qs = document.location.search;


    if(qs.length > 1) {
         var params = Ext.urlDecode(qs.substr(1));
    }

    $('#userList').css('padding-top','5px');

    var myData = [
        ['Smith','Eve', 'esmith', 'esmith@alphaomega.com', true, '2008-12-08'],
        ['Smith','Adam', 'asmith','asmith@alphaomega.com', true, '2008-12-10'],
        ['Weibust','Erik','eweibust', 'erik@weibust.net', true, '2008-11-14'],
        ['Rollins','Larry','lrollins', 'larry@hotmail.com', true, '2008-12-07'],
        ['Richards','Alice','arichards', 'alice.richards@myorg.net', true, '2008-11-27'],
        ['Burns','Edward', 'eburns','eburns@bigmoney.com', true, '2008-11-29'],
        ['Campos','Mario', 'mcampos', 'mario@smallgiver.net', true, '2008-12-08'],
        ['Burton','Louise', 'lburton', 'lburton@mac.com', true, '2008-12-10'],
        ['Swift','Jonathan','jswift', 'jswift@writersblock.com', true, '2008-12-05'],
        ['Parker','Jessica','jparker', 'jessica@topcoders.com', true, '2008-12-13'],
        ['Peters','Terry','tpeters', 'motivator@conference.com', true, '2008-12-06'],
        ['MacKenzie','Sue','smackenzie', 'bigmac@mackenzie.net', true, '2008-12-03'],
        ['Snyder','Mark', 'msnyder', 'msnyder@bigmoney.com', true, '2008-12-08'],
        ['Twist','Wendle', 'wtwist', 'wtwist@alphaomega.com', true, '2008-12-10'],
        ['Feldman','Mark','mfeldman', 'mfeldman@bigmoney.com', true, '2008-12-08'],
        ['Feldman','Susan','sfeldman', 'mfeldman@bigmoney.com', true, '2008-12-09'],
        ['Hunter','Fisher', 'fhunter','fisher_hunter@trapper.com', true, '2008-12-10'],
        ['Rainer','Brittney', 'brainer', 'brainer@me.com', true, '2008-11-28'],
        ['Wilson','Kevin', 'kwilson', 'kwilson@hotmail.com', true, '2008-12-11'],
        ['Parsons','Alan', 'aparsons','aparsons@rhaposdy.com', true, '2008-12-06'],
        ['King','Janet', 'jking','jking@gmail.com', true, '2008-12-02'],
        ['Markeson','Carly','cmarkeson', 'c.markeson@linkedup.com', true, '2008-12-09'],
        ['Reilly','Oscar', 'oreilly', 'oreilly@books.com', true, '2008-12-06'],
        ['Press','Kim', 'kpress','kpress@gmail.com', true, '2008-11-02'],
        ['Clements','Roberto','rclements', 'rclemens@gmail.com', false, '2008-12-05']
    ];

    var store = new Ext.data.SimpleStore({
        fields: [
           {name: 'lastName'},
           {name: 'firstName'},
           {name: 'login'},
           {name: 'email'},
           {name: 'active', type: 'boolean'},
           {name: 'lastLogin', type: 'date', dateFormat: 'Y-m-d'}
        ],
        sortInfo:{field: 'lastName', direction: "ASC"},
        data: myData
    });

    if(params !== undefined) {
        store.filterBy(function(record){
            var q = params.criteria.toString().toUpperCase();

            if(record.data.lastName.toUpperCase().indexOf(q) > -1 ) {
                return true;
            }

            if(record.data.firstName.toUpperCase().indexOf(q) > -1 ) {
                return true;
            }

            if(record.data.login.toUpperCase().indexOf(q) > -1 ) {
                return true;
            }
        });
    }


// create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {header: "Last Name", width: 120, sortable: true, dataIndex: 'lastName'},
            {header: "Fist Name", width: 120, sortable: true, dataIndex: 'firstName'},
            {header: "Login Name", width: 120, sortable: true, dataIndex: 'login'},
            {id: "emailColumn", header: "Email", width: 200, sortable: true, dataIndex: 'email'},
            {header: "Last Login", width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastLogin'}
        ],
        sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
        height:400,
        width:650,
        frame: false,
        listeners: {
            rowdblclick: function(grid, row, evt) {
                var rec = grid.getSelectionModel().getSelected();
                window.location.href = "edit-user.html?firstName=" + rec.data.firstName + "&lastName=" + rec.data.lastName;
            }


        },
        autoExpandColumn: "emailColumn"
    });

    grid.render('userList');


});