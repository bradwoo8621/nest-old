// check box
var CheckBox = React.createClass({
	_className: "form-vertical-checkbox",
	propTypes: {
		id: React.PropTypes.string.isRequired,
		name: React.PropTypes.string, // property name in model, use id instead if not exists
		model: React.PropTypes.object.isRequired,
		
		onChange: React.PropTypes.func,
	},
	componentDidUpdate: function(prevProps, prevState) {
		this.getComponent().prop("checked", this.getValueFromModel());
	},
	componentDidMount: function() {
		this.getComponent().prop("checked", this.getValueFromModel());
		this.getModel().addListener(this.getPropertyName(), "post", this.handleModelChange);
	},
	componentWillUnmount: function() {
		this.getModel().removeListener(this.getPropertyName(), "post", this.handleModelChange);
	},
	renderCheckbox: function() {
		if (this.getValueFromModel() == true) {
			return (<a href="javascript:void(0)" onClick={this.handleHrefClick}>
				<Glyphicon glyph="check" bsSize="large"/>
			</a>);
		} else {
			return (<a href="javascript:void(0)" onClick={this.handleHrefClick}>
				<Glyphicon glyph="unchecked" bsSize="large" />
			</a>);
		}
	},
	render: function() {
		return (
			<div>
				<label className="form-vertical-checkbox">
					{this.renderCheckbox()}
					<input type="checkbox" id={this.props.id}
								onChange={this.handleComponentChange} style={{display: "none"}} />
				</label>
			</div>
		);
	},
	handleHrefClick: function() {
		var value = this.getValueFromModel();
		if (value == true) {
			value = false;
		} else {
			value = true;
		}
		this.setValueToModel(value);
		this.forceUpdate();
	},
	// handle component change
	handleComponentChange: function(e) {
		// synchronize value to model
		this.setValueToModel(e.target.checked);
		if (this.props.onChange) {
			this.props.onChange(e, e.target.checked);
		}
	},
	// handle model change
	handleModelChange: function(evt) {
		this.getComponent().prop("checked", evt.newValue === true ? true : false);
	},
	getComponent: function() {
		return $("#" + this.props.id);
	},
	// get model
	getModel: function() {
		return this.props.model;
	},
	// get property name
	getPropertyName: function() {
		return this.props.name ? this.props.name : this.props.id;
	},
	// get value from model
	getValueFromModel: function() {
		return this.getModel().get(this.getPropertyName());
	},
	// set value to model
	setValueToModel: function(value) {
		this.getModel().set(this.getPropertyName(), value);
	},
});
