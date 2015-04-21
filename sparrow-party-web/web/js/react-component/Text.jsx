// text input
var Text = React.createClass({
	propTypes: {
		id: React.PropTypes.string.isRequired, // id used in UI
		name: React.PropTypes.string, // property name in model, use id instead if not exists
		model: React.PropTypes.object.isRequired,
		
		onChange: React.PropTypes.func,
	},
	// handle component change
	componentDidUpdate: function(prevProps, prevState) {
		this.getComponent().val(this.getValueFromModel());
	},
	componentDidMount: function() {
		this.getComponent().val(this.getValueFromModel());
		this.getModel().addListener(this.getPropertyName(), "post", this.handleModelChange);
	},
	componentWillUnmount: function() {
		this.getModel().removeListener(this.getPropertyName(), "post", this.handleModelChange);
	},
	render: function() {
		return <input type="text" className="form-control" id={this.props.id}
					onChange={this.handleComponentChange} />
	},
	// handle component change
	handleComponentChange: function(e) {
		// synchronize value to model
		this.setValueToModel(e.target.value);
		// invoke change event
		if (this.props.onChange) {
			this.props.onChange(e, this.getComponent().val());
		}
	},
	// handle model change
	handleModelChange: function(evt) {
		this.getComponent().val(evt.newValue);
	},
	// get component
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
