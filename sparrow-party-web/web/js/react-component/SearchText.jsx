// search text
var SearchText = React.createClass({
	propTypes: {
		id: React.PropTypes.string.isRequired, // id used in UI
		name: React.PropTypes.string,  // property name in model, use id instead if not exists
		model: React.PropTypes.object.isRequired,
		placeholder: React.PropTypes.string,
		onChange: React.PropTypes.func,
		
		search: React.PropTypes.func,
		advancedSearch: React.PropTypes.func
	},
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
	renderSearchButton: function() {
		if (this.props.search) {
			return (<Button><Glyphicon glyph="screenshot" /></Button>);
		} else {
			return null;
		}
	},
	renderAdvancedSearchButton: function() {
		if (this.props.advancedSearch) {
			return (
				<OverlayTrigger placement="top" overlay={<Popover>Advanced Search...</Popover>}>
					<Button><Glyphicon glyph="search" /></Button>
				</OverlayTrigger>
			);
		} else {
			return null;
		}
	},
	render: function() {
		return (
			<div className="input-group">
				<input type="text" id={this.props.id} className="form-control search-code" 
					placeholder={this.props.placeholder} onChange={this.handleComponentChange} />
				<span className="input-group-btn" style={{width: "0px"}}></span>
				<input type="text" className="form-control search-label" readOnly={true} onFocus={this.transFocus}/>
				<div className="input-group-btn">
					{this.renderSearchButton()}
					{this.renderAdvancedSearchButton()}
				</div>
			</div>
		);
	},
	// handle component change
	handleComponentChange: function(e) {
		// synchronize value to model
		this.setValueToModel(e.target.value);
		if (this.props.onChange) {
			this.props.onChange(e, e.target.value);
		}
	},
	// handle model change
	handleModelChange: function(evt) {
		this.getComponent().val(evt.newValue);
	},
	transFocus: function() {
		this.getComponent().focus();
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
