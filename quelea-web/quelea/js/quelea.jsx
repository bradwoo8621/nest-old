(function () {
    var Controller = jsface.Class({
        // render home page
        initialize: function (menus) {
            this.renderHeader(menus);
            this.home();
            this.renderFooter();
            this.renderConfirmDialog();
        },
        /**
         * render header
         * @param menus
         */
        renderHeader: function (menus) {
            // render page header
            React.render(<NPageHeader brand="Quelea" menus={menus} brandFunc={this.home} controller={this}/>,
                document.getElementById("page-header"));
        },
        /**
         * to home
         */
        home: function () {
            // render main content
            React.render(<NJumbortron
                    highlightText={["Hello, World!", "Welcome to Quelea."]}/>,
                document.getElementById("main"));
            // release current model
            this.model = null;
        },
        /**
         * render footer
         */
        renderFooter: function () {
            // render page footer
            React.render(<NPageFooter name="Quelea-WEB"/>, document.getElementById("page-footer"));
        },
        /**
         * render confirm dialog
         */
        renderConfirmDialog: function () {
            this.confirmDialog = React.render(<NConfirm />, document.getElementById("confirm-dialog"));
        },
        /**
         * find party
         */
        findParty: function () {
            var render = function () {
                this.model = ModelUtil.createModel(ModelUtil.cloneJSON(ModelDefine.PartyQueryModel));
                var layout = new FormLayout(LayoutDefine.PartyQueryLayout);
                var panel = (<div>
                    <Panel header="Find Party...">
                        <NForm model={this.model} layout={layout}/>
                        <NPanelFooter reset={this.onResetClicked.bind(this)}
                                      right={[{icon:"search",
                                        text:"Search",
                                        style:"primary",
                                        onClick:this.onPartySearchClicked.bind(this)}]}/>
                    </Panel>
                </div>);
                this.mainPanel = React.render(panel, document.getElementById("main"));
            };
            this.swtichPage(render);
        },
        /**
         * find role
         */
        findRole: function () {
            var render = function () {
                this.model = ModelUtil.createModel(ModelUtil.cloneJSON(ModelDefine.RoleQueryModel));
                var layout = new FormLayout(LayoutDefine.RoleQueryLayout);
                var panel = (<div>
                    <Panel header="Find Role...">
                        <NForm model={this.model} layout={layout}/>
                        <NPanelFooter reset={this.onResetClicked.bind(this)}
                                      right={[{icon:"search",
                                        text:"Search",
                                        style:"primary",
                                        onClick:this.onPartySearchClicked.bind(this)}]}/>
                    </Panel>
                </div>);
                this.mainPanel = React.render(panel, document.getElementById("main"));
            };
            this.swtichPage(render);
        },
        /**
         * create party
         */
        createParty: function (model, layout, header) {
            var panel = (<Panel header={"Create " + header}>
                <NForm model={model} layout={layout}/>
                <NPanelFooter reset={this.onResetClicked.bind(this)}
                              validate={this.onValidateClicked.bind(this)}
                              save={this.onPartySaveClicked.bind(this)}
                              cancel={this.onCancelClicked.bind(this, "Cancel creating " + header)}/>
            </Panel>);
            this.mainPanel = React.render(panel, document.getElementById("main"));
        },
        /**
         * create individual party
         */
        createIndividualParty: function () {
            var render = function () {
                this.model = ModelUtil.createModel(ModelUtil.cloneJSON(ModelDefine.IndividualParty));
                this.createParty(this.model,
                    new FormLayout(LayoutDefine.PartyCommon, LayoutDefine.IndividualParty),
                    "Individual Party");
            };
            this.swtichPage(render);
        },
        /**
         * create organization party
         */
        createOrganizationParty: function () {
            var render = function () {
                this.model = ModelUtil.createModel(ModelUtil.cloneJSON(ModelDefine.OrganizationParty));
                this.createParty(this.model,
                    new FormLayout(LayoutDefine.PartyCommon, LayoutDefine.OrganizationParty),
                    "Organization Party");
            };
            this.swtichPage(render);
        },
        /**
         * on cancel clicked
         */
        onCancelClicked: function (title) {
            this.confirmDialog.show(title,
                ["Are you sure to cancel current operating?",
                    "All data will be lost and cannot be recovered."],
                this.home.bind(this));
        },
        /**
         * on reset clicked
         */
        onResetClicked: function () {
            var reset = function () {
                this.model.reset();
                this.mainPanel.forceUpdate();
            };
            this.confirmDialog.show("Reset Data",
                ["Are you sure to reset data?",
                    "All data will be lost and cannot be recovered."],
                reset.bind(this));
        },
        /**
         * on validate clicked
         */
        onValidateClicked: function () {
            var model = this.model;
            model.setPartyCode("I00001");
            model.setEnabled(false);
            model.setIdTypeCode("1");

            if (model.setDateOfBirth) {
                model.setDateOfBirth("2001/02/03");
            } else {
                model.setDateOfRegister("2000/01/02");
            }
        },
        /**
         * on party save clicked
         */
        onPartySaveClicked: function () {
            // TODO
            alert("Party Save Clicked");
        },
        /**
         * on party search clicked
         */
        onPartySearchClicked: function () {
            // TODO
            alert("Party Query Clicked");
        },
        /**
         * switch page
         * @param func
         */
        swtichPage: function (func) {
            if (this.model != null) {
                // something editing
                this.confirmDialog.show("Page Jumping",
                    ["Are you sure to jump to another page?",
                        "All data will be lost and cannot be recovered."],
                    func.bind(this));
            } else {
                func.call(this);
            }
        },
        demo: function () {
            this.confirmDialog.show("Demo Page",
                ["Function is under developing"], function () {
                }.bind(this));
        }
    });

    // create controller
    var controller = new Controller();
    // create menus
    var menus = [{
        text: "Party",
        children: [{text: "Find Party", func: controller.findParty},
            {text: "Find Role", func: controller.findRole},
            {divider: true},
            {
                text: "Create Party",
                children: [{text: "Individual", func: controller.createIndividualParty},
                    {text: "Organization", func: controller.createOrganizationParty}]
            },
            {divider: true},
            {
                text: "Create Customer",
                children: [{text: "Individual", func: controller.demo},
                    {text: "Organization", func: controller.demo}]
            }, {
                text: "Create Agent",
                children: [{text: "Individual", func: controller.demo},
                    {text: "Organization", func: controller.demo}]
            },
            {text: "Create Bank", func: controller.demo},
            {
                text: "Create Own Roles",
                children: [{text: "Branch", func: controller.demo},
                    {text: "Department", func: controller.demo},
                    {text: "Employee", func: controller.demo}]
            }]
    }];
    // initialize
    controller.initialize(menus);
}());