/**
 * bootstrap-datetimepicker react, base on momentjs
 */
var DateTimePicker = React.createClass({
    __classNames: {
        control: "form-control",
        div: "input-group date",
        addon: "input-group-addon",
        calendarButton: "glyphicon glyphicon-calendar",
        errorPopover: "error-popover"
    },
    /**
     * get CSS class
     * @param name {String}
     * @returns {String}
     */
    getCSSClass: function (name) {
        return this.props["css_" + name] ? this.props["css_" + name] : this.__classNames[name];
    },
    /**
     * @override
     */
    propTypes: {
        // id of component
        id: React.PropTypes.string.isRequired,
        // label of component
        label: React.PropTypes.string,
        // model
        model: React.PropTypes.object.isRequired,
        // error model
        error: React.PropTypes.object,

        // the following properties are copy from datetimepicker
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

        // onChange event handler
        onChange: React.PropTypes.func
    },
    /**
     * override react method
     * @returns {*}
     * @override
     */
    getDefaultProps: function () {
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
     * overrride react method
     * @param prevProps
     * @param prevState
     * @override
     */
    componentDidUpdate: function (prevProps, prevState) {
        this.getComponent().data("DateTimePicker").date(this.getValueFromModel());
    },
    /**
     * override react method
     * @override
     */
    componentDidMount: function () {
        this.createComponent();
        this.getComponent().data("DateTimePicker").date(this.getValueFromModel());
        // add post change listener
        this.getModel().addListener(this.getId(), "post", this.handleModelChange);
    },
    /**
     * override react method
     * @override
     */
    componentWillUnmount: function () {
        // remove post change listener
        this.getModel().removeListener(this.getId(), "post", this.handleModelChange);
    },
    /**
     * create component
     */
    createComponent: function () {
        var options = {
            format: this.props.format,
            dayViewHeaderFormat: this.props.dayViewHeaderFormat,
            stepping: this.props.stepping,
            minDate: this.props.minDate,
            maxDate: this.props.maxDate,
            collapse: this.props.collapse,
            disabledDates: this.props.disabledDates,
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
    /**
     * render error popover.
     * error messages will be rendered as divs.
     * @returns {XML}
     */
    renderErrorPopover: function () {
        if (this.hasError()) {
            var _this = this;
            return (<Popover className={this.getCSSClass("errorPopover")}>
                {this.getError().map(function (element) {
                    return (<div>{element.replaceMessage([_this.getLabel()])}</div>);
                })}
            </Popover>);
        } else {
            return (<Popover />);
        }
    },
    /**
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        return (<OverlayTrigger placement="top" overlay={this.renderErrorPopover()} trigger="manual" ref="overlay">
            <div className={this.getCSSClass("div")} id={this.getComponentId()}
                 onMouseMove={this.mouseMove} onMouseOut={this.mouseOut}>
                <input id={this.getId()} type='text' className={this.getCSSClass("control")}
                       value={this.getValueFromModel()}/>
                <span className={this.getCSSClass("addon")}>
                    <span className={this.getCSSClass("calendarButton")}></span>
                </span>
            </div>
        </OverlayTrigger>);
    },
    /**
     * mouse move
     */
    mouseMove: function () {
        if (this.hasError()) {
            // show overlay
            this.refs.overlay.show();
        }
    },
    /**
     * mouse out
     */
    mouseOut: function () {
        // hide overlay
        this.refs.overlay.hide();
    },
    // handle component change
    handleComponentChange: function (e) {
        var value = e.date;
        value = value == null ? null : value.format(this.props.format);
        // synchronize value to model
        this.setValueToModel(value);
        if (this.props.onChange) {
            this.props.onChange(e, value);
        }
    },
    /**
     * handle model change
     * @param evt
     */
    handleModelChange: function (evt) {
        this.getComponent().data("DateTimePicker").date(evt.newValue);
    },
    /**
     * get component id
     * @returns {string}
     */
    getComponentId: function () {
        return "div_" + this.getId();
    },
    /**
     * get component
     * @returns {*|jQuery|HTMLElement}
     */
    getComponent: function () {
        return $("#" + this.getComponentId());
    },
    /**
     * get data model
     * @returns {ModelProxy}
     */
    getModel: function () {
        return this.props.model;
    },
    /**
     * get id
     * @returns {String}
     */
    getId: function () {
        return this.props.id;
    },
    /**
     * get label
     * @returns {String}
     */
    getLabel: function () {
        return this.props.label;
    },
    /**
     * get value from model by id of props
     * @returns {String}
     */
    getValueFromModel: function () {
        return this.getModel().get(this.getId());
    },
    /**
     * set value to model by id of props
     * @param value {String}
     */
    setValueToModel: function (value) {
        this.getModel().set(this.getId(), value);
    },
    /**
     * get error
     * @returns {[String]}
     */
    getError: function () {
        if (this.props.error === undefined || this.props.error == null) {
            return null;
        }
        return this.props.error[this.getId()];
    },
    /**
     * check if there is error existed
     * @returns {boolean}
     */
    hasError: function () {
        var error = this.getError();
        return error != null && error.length > 0;
    }
});
