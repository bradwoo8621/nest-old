/**
 * bootstrap-datetimepicker react
 */ 
var DateTimePicker = React.createClass({
	displayName: "DateTimePicker",
	propTypes: {
		id: React.PropTypes.string.isRequired, // id used in UI
		name: React.PropTypes.string, // property name in model, use id instead if not exists
		model: React.PropTypes.object.isRequired,

		format: React.PropTypes.string,
		dayViewHeaderFormat: React.PropTypes.string,
		stepping: React.PropTypes.number,
		minDate: React.PropTypes.oneOfType([React.PropTypes.string, React.PropTypes.bool]),
		maxDate: React.PropTypes.oneOfType([React.PropTypes.string, React.PropTypes.bool]),
		collapse: React.PropTypes.bool,
		locale: React.PropTypes.string,
		disabledDates: React.PropTypes.oneOfType([React.PropTypes.arrayOf(React.PropTypes.string), React.PropTypes.bool]),
		enabledDates: React.PropTypes.oneOfType([React.PropTypes.arrayOf(React.PropTypes.string), React.PropTypes.bool]),
		icons: React.PropTypes.shape({
	            time: React.PropTypes.string,
	            date: React.PropTypes.string,
	            up: React.PropTypes.string,
	            down: React.PropTypes.string,
	            previous: React.PropTypes.string,
	            next: React.PropTypes.string,
	            today: React.PropTypes.string,
	            clear: React.PropTypes.string
		}),
		useStrict: React.PropTypes.bool,
		sideBySide: React.PropTypes.bool,
		daysOfWeekDisabled: React.PropTypes.arrayOf(React.PropTypes.number),
		calendarWeeks: React.PropTypes.bool,
		viewMode: React.PropTypes.oneOf(['years', 'months', 'days']),
		toolbarPlacement: React.PropTypes.oneOf(['default', 'top', 'bottom']),
		showTodayButton: React.PropTypes.bool,
		showClear: React.PropTypes.bool,
		showClose: React.PropTypes.bool,
		
		// event
		onChange: React.PropTypes.func
	},
	getDefaultProps: function() {
		return {
			format: "YYYY/MM/DD",
			dayViewHeaderFormat: "MMMM YYYY",
			stepping: 1,
			minDate: false,
			maxDate: false,
			collapse: true,
			defaultDate: false,
			disabledDates: false,
			enabledDates: false,
			icons: {
	            time: 'glyphicon glyphicon-time',
                date: 'glyphicon glyphicon-calendar',
                up: 'glyphicon glyphicon-chevron-up',
                down: 'glyphicon glyphicon-chevron-down',
                previous: 'glyphicon glyphicon-chevron-left',
                next: 'glyphicon glyphicon-chevron-right',
                today: 'glyphicon glyphicon-screenshot',
                clear: 'glyphicon glyphicon-trash'
			},
			useStrict: false,
			sideBySide: true,
			daysOfWeekDisabled: [],
			calendarWeeks: false,
			viewMode: 'days',
			toolbarPlacement: 'default',
			showTodayButton: true,
			showClear: true,
			showClose: true
		}
	},
	/**
	 * life-cycle
	 */
	componentDidUpdate: function(prevProps, prevState) {
		this.getComponent().data("DateTimePicker").date(this.getValueFromModel());
	},
	componentDidMount: function () {
		this.createComponent();
		this.getComponent().data("DateTimePicker").date(this.getValueFromModel());
		this.getModel().addListener(this.getPropertyName(), "post", this.handleModelChange);
	},
	componentWillUnmount: function() {
		this.getModel().removeListener(this.getPropertyName(), "post", this.handleModelChange);
	},
	createComponent: function() {
		var options = {
				format: this.props.format,
				dayViewHeaderFormat: this.props.dayViewHeaderFormat,
				stepping: this.props.stepping,
				minDate: this.props.minDate,
				maxDate: this.props.maxDate,
				collapse: this.props.collapse,
				disabledDates: this.props.disableDates,
				enabledDates: this.props.enabledDates,
				icons: this.props.icons,
				useStrict: this.props.useStrict,
				sideBySide: this.props.sideBySide,
				daysOfWeekDisabled: this.props.daysOfWeekDisabled,
				calendarWeeks: this.props.calendarWeeks,
				viewMode: this.props.viewMode,
				toolbarPlacement: this.props.toolbarPlacement,
				showTodayButton: this.props.showTodayButton,
				showClear: this.props.showClear,
				showClose: this.props.showClose
		};
		this.getComponent().datetimepicker(options).on("dp.change", this.handleComponentChange);
	},
	render: function() {
		return (
			<div className='input-group date' id={this.getComponentId()}>
				<input id={this.props.id} type='text' className="form-control" value={this.getValueFromModel()} />
				<span className="input-group-addon"><span className="glyphicon glyphicon-calendar"></span></span>
			</div>
		);
	},
	// handle component change
	handleComponentChange: function(e) {
		var value = e.date;
		value = value == null ? null : value.format(this.props.format);
		// synchronize value to model
		this.setValueToModel(value);
		if (this.props.onChange) {
			this.props.onChange(e, value);
		}
	},
	// handle model change
	handleModelChange: function(evt) {
		this.getComponent().data("DateTimePicker").date(evt.newValue);
	},
	getComponentId: function() {
		return "div_" + this.props.id;
	},
	getComponent: function() {
		return $("#" + this.getComponentId());
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
