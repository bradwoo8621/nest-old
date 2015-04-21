// page model
var app = {
	// type selection model
	typeSelectionModel: new ModelProxy({
		partyTypeCode: null,
		roleTypeCode: null
	}),
	// edit model
	editModel: new ModelProxy({}),
	// reset model
	// TODO call common initializer
	initModel: function() {
		// all clear
		this.editModel = ModelUtil.create(this.getUIConfig());
	},
	getUIConfig: function() {
		// find layout configuration by given role type code
		var config = PartyRoleConfig[this.typeSelectionModel.getRoleTypeCode()];
		if (config == undefined) {
			// the role type is suit for all party types, add party type suffix to find the layout configuration
			config = PartyRoleConfig[this.typeSelectionModel.getRoleTypeCode() + "_" + this.typeSelectionModel.getPartyTypeCode()];
		}
		return config;
	}
};

// creation panel
var CreationPanel = React.createClass({
	propTypes: {
		app: React.PropTypes.object, // page model
	},
	getDefaultProps: function() {
		return {
			app: app,
		};
	},
	getInitialState: function() {
		return {
			creating: false,
		};
	},
	render: function() {
		return (
			<PanelGroup ref="group">
				<TypeSelectionPanel model={this.getApp().typeSelectionModel} parent={this} ref="selection" />
				<EditPanel app={this.getApp()} parent={this} visible={this.state.creating} />
			</PanelGroup>
		);
	},
	getSelectionPanel: function() {
		// panel group has two levels ref hierarchy
		return this.refs.group.refs.selection;
	},
	// is creating or not
	isCreating: function() {
		return this.state.creating;
	},
	// get app
	getApp: function() {
		return this.props.app;
	},
	// start creating
	startCreating: function() {
		this.getApp().initModel();
		this.setState({creating: true});
	},
	stopCreating: function() {
		this.getSelectionPanel().hideWarning();
		this.setState({creating: false});
	}
});
// type selection panel
var TypeSelectionPanel = React.createClass({
	_panelClassName: "panel-bold",
	_labelClassName: "form-horizontal-label",
	_alertClassName: "alert-last-row",
	propTypes: {
		model: React.PropTypes.object.isRequired, // app.typeSelectionModel
		parent: React.PropTypes.object.isRequired, // parent 
	},
	getInitialState: function() {
		return {
			warningVisible: false,
			warningMessage: null,
		};
	},
	shouldComponentUpdate: function(nextProps, nextState) {
		this.getPartyTypeComponent().prop("disabled", this.getParent().isCreating());
		this.getRoleTypeComponent().prop("disabled", this.getParent().isCreating());
		
		// forbidden the unnecessary render operation
		return this.state.warningVisible != nextState.warningVisible;
	},
	componentDidMount: function () {
		this.getModel().addListener("partyTypeCode", "post", this.partyTypeChanged);
		this.getModel().addListener("roleTypeCode", "post", this.roleTypeChanged);
	},
	componentWillUnmount: function() {
		this.getModel().removeListener("partyTypeCode", "post", this.partyTypeChanged);
		this.getModel().removeListener("roleTypeCode", "post", this.roleTypeChanged);
	},
	render: function() {
		var warnClassName = "row";
		// check the warning message is visible or not
		if (!this.state.warningVisible) {
			warnClassName += " hide";
		};
		// add listener
		return (
			<Panel header="Please choose the role of party..." className={this._panelClassName}>
				<div className="row">
					<div className="col-md-2">
	        			<label htmlhtmlFor="partyType" className={this._labelClassName}>Party Type:</label>
	        		</div>
        			<div className="col-md-3">
        				<Select2 id="partyType" name="partyTypeCode" options={Codes.partyTypeList} 
        					model={this.getModel()} ref="partyType" />
        			</div>
					<div className="col-md-2">
	        			<label htmlhtmlFor="roleType" className={this._labelClassName}>Role Type:</label>
	        		</div>
        			<div className="col-md-3">
						<Select2 id="roleType" name="roleTypeCode" options={Codes.roleTypeList} model={this.getModel()}
							minimumResultsForSearch="1" ref="roleType"
							parent={{prop: "partyTypeCode", filter: this.filterRoleType, availableWhenNoParentValue: true}}/>
					</div>
        			<div className="col-md-2">
        				<Button bsStyle="primary" block onClick={this.createClicked}><Glyphicon glyph="plus-sign" /> Create</Button>
        			</div>
	        	</div>
        		<div className={warnClassName} id="warn">
					<div className="col-md-12" >
						<Alert bsStyle="warning" className={this._alertClassName}>{this.state.warningMessage}</Alert>
					</div>
				</div>
			</Panel>
		);
	},
	// get party type select component
	getPartyTypeComponent: function() {
		return $("#partyType");
	},
	// get role type select component
	getRoleTypeComponent: function() {
		return $("#roleType");
	},
	// show warning message
	showWarning: function(message) {
		this.setState({warningVisible: true, warningMessage: message});
	},
	// hide warning message
	hideWarning: function() {
		this.setState({warningVisible: false, warningMessage: null});
	},
	// party type changed
	partyTypeChanged: function(evt) {
		this.hideWarning();
	},
	// role type changed
	roleTypeChanged: function(evt) {
		this.hideWarning();
	},
	createClicked: function() {
		var partyTypeCode = this.getModel().getPartyTypeCode();
		var roleTypeCode = this.getModel().getRoleTypeCode();
		if (roleTypeCode != null) {
			// role type selected
			if (this.getParent().isCreating()) {
				// a party is creating now
				this.showWarning("Sorry, a party is creating now, discard it if really want to create a new one.");
			} else if (partyTypeCode == null && this.checkRoleTypeIsSuitAllPartyType(roleTypeCode)) {
				// the selected role type is suit for all party types, ask for select party type
				this.showWarning("Sorry, the selected role can be either organization or individual, please select \"Party Type\" first.");
			} else {
				// start creating
				this.hideWarning();
				this.getParent().startCreating();
			}
		} else {
			// role type not selected
			this.showWarning("Sorry, please select \"Role Type\" first.");
		}
	},
	// get parent component
	getParent: function() {
		return this.props.parent;
	},
	// get model
	getModel: function() {
		return this.props.model;
	},
	// party type can be null/I/O. if it is null, return all role types; 
	// role type with type 'B' always be shown
	filterRoleType: function(partyTypeCode, roleTypeList) {
		return roleTypeList.filter(function(element) {
			return element.type == partyTypeCode || element.type == 'B';
		}); 
	},
	// check the given role code is suit all party type or not
	checkRoleTypeIsSuitAllPartyType: function(roleTypeCode) {
		return Codes.roleTypeList.some(function(element) {
					return element.id == roleTypeCode && element.type == 'B';
				});
	},
});
// edit panel
var EditPanel = React.createClass({
	_panelClassName: "panel-bold",
	propTypes: {
		app: React.PropTypes.object.isRequired, // app
		parent: React.PropTypes.object.isRequired, // parent
		
		visible: React.PropTypes.bool.isRequired, // visibility
	},
	render: function() {
		if (!this.isVisible()) {
			return null;
		}
		var roleTypeCode = this.getTypeSelectionModel().getRoleTypeCode();
		var roleType = Codes.roleTypeList.get(roleTypeCode);
		var header = "Creating " + roleType.text;

		return (
			<Panel header={header} className={this._panelClassName}>
				<Form layout={this.getApp().getUIConfig()} model={this.getEditModel()} ref="form" />
				<div className="row">
					<div className="col-md-6">
						<ButtonToolbar>
							<Button bsStyle="danger" onClick={this.discardClicked}><Glyphicon glyph="trash" /> Discard</Button>
							<Button onClick={this.resetClicked}><Glyphicon glyph="erase" /> Reset</Button>
						</ButtonToolbar>
					</div>
					<div className="col-md-6">
						<ButtonToolbar className="btn-toolbar-right">
							<Button onClick={this.validateClicked}><Glyphicon glyph="check" /> Validate</Button>
							<Button bsStyle="primary"><Glyphicon glyph="floppy-save" /> Save</Button>
						</ButtonToolbar>
					</div>
				</div>
				<ModalConfirmDialog ref="confirmDialog" />
			</Panel>
		);
	},
	// get parent component
	getParent: function() {
		return this.props.parent;
	},
	// get type selection model
	getTypeSelectionModel: function() {
		return this.props.app.typeSelectionModel;
	},
	// get model
	getEditModel: function() {
		return this.props.app.editModel;
	},
	getApp: function() {
		return this.props.app;
	},
	// is visible or not
	isVisible: function() {
		return this.props.visible;
	},
	// discard clicked
	discardClicked: function() {
		this.refs.confirmDialog.show("Discard data?",
		 	["Are you sure you want to discard the editing data?", "All data will be lost and cannot be recovered."],
		 	this.discard);
	},
	// discard
	discard: function() {
		this.getParent().stopCreating();
	},
	// reset clicked
	resetClicked: function() {
		this.refs.confirmDialog.show("Reset data?",
		 	["Are you sure you want to reset the editing data?", "All updated data will be lost and cannot be recovered."],
		 	this.reset);
	},
	reset: function() {
		// reset model
		this.getEditModel().reset();
		// invoke update force
		this.refs.form.forceUpdate();
		// hide the confirm dialog
		this.refs.confirmDialog.hide();
	},
	// validate
	validateClicked: function() {
		this.getEditModel().setRegisterNumber("XYZ12345");
		this.getEditModel().setPartyEnabled(true);
		this.getEditModel().setRegisterDate("2005/02/03");
		this.getEditModel().setParentBranch("P001");
		this.getEditModel().setRegisterIn("AND");
		console.log("Here is the model:");
		console.log(this.getEditModel());
	},
});

// create into dom
React.render(<CreationPanel />, document.getElementById("main"));
