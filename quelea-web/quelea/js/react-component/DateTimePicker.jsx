/**
 * datetime picker, see datetimepicker from bootstrap
 */
var NDateTime = React.createClass({
    propTypes: {
        // model
        model: React.PropTypes.object,
        // CellLayout
        layout: React.PropTypes.object
    },
    getDefaultProps: function () {
        return {
            defaultOptions: {
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
                showClose: true,
                // value format can be different with display format
                valueFormat: "YYYY/MM/DD"
            }
        }
    },
    /**
     * will update
     * @param nextProps
     */
    componentWillUpdate: function (nextProps) {
        // remove post change listener to handle model change
        this.getModel().removeListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * overrride react method
     * @param prevProps
     * @param prevState
     * @override
     */
    componentDidUpdate: function (prevProps, prevState) {
        this.getComponent().data("DateTimePicker").date(this.getValueFromModel());
        // add post change listener
        this.getModel().addListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * override react method
     * @override
     */
    componentDidMount: function () {
        this.createComponent();
        this.getComponent().data("DateTimePicker").date(this.getValueFromModel());
        // add post change listener
        this.getModel().addListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * override react method
     * @override
     */
    componentWillUnmount: function () {
        // remove post change listener
        this.getModel().removeListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * create component
     */
    createComponent: function () {
        this.getComponent().datetimepicker(this.createDisplayOptions({
            format: null,
            dayViewHeaderFormat: null,
            stepping: null,
            minDate: null,
            maxDate: null,
            collapse: null,
            disabledDates: null,
            enabledDates: null,
            icons: null,
            useStrict: null,
            sideBySide: null,
            daysOfWeekDisabled: null,
            calendarWeeks: null,
            viewMode: null,
            toolbarPlacement: null,
            showTodayButton: null,
            showClear: null,
            showClose: null
        })).on("dp.change", this.onComponentChange);
    },
    /**
     * create display options
     * @param optionsDefine
     */
    createDisplayOptions: function (optionsDefine) {
        var _this = this;
        Object.keys(optionsDefine).forEach(function (key) {
            optionsDefine[key] = _this.getComponentOption(key);
        });
        return optionsDefine;
    },
    /**
     * get option
     * @param key
     */
    getComponentOption: function (key) {
        var option = this.getLayout().getComponentOption(key);
        if (option == null) {
            option = this.props.defaultOptions[key];
        }
        return option === undefined ? null : option;
    },
    /**
     * render
     * @returns {XML}
     */
    render: function () {
        return (<div className={this.getDivCSS()} id={this.getDivId()}>
            <input id={this.getId()} type='text' className={this.getCSS()}/>
            <span className="input-group-addon">
                <Glyphicon glyph="calendar"/>
            </span>
        </div>);
    },
    /**
     * on component change
     * @param evt
     */
    onComponentChange: function (evt) {
        // synchronize value to model
        this.setValueToModel(evt.date);
    },
    /**
     * on model change
     * @param evt
     */
    onModelChange: function (evt) {
        this.getComponent().data("DateTimePicker").date(this.convertValueFromModel(evt.new));
    },
    /**
     * get component
     * @returns {*|jQuery|HTMLElement}
     */
    getComponent: function () {
        return $("#" + this.getDivId());
    },
    /**
     * get model
     * @returns {*}
     */
    getModel: function () {
        return this.props.model;
    },
    /**
     * get value from model
     * @returns {*}
     */
    getValueFromModel: function () {
        return this.convertValueFromModel(this.getModel().get(this.getId()));
    },
    /**
     * set value to model
     * @param value
     */
    setValueToModel: function (value) {
        value = value == null ? null : value.format(this.getComponentOption("valueFormat"));
        this.getModel().set(this.getId(), value);
    },
    /**
     * convert value from model
     * @param value string date with value format
     * @returns {*} moment date
     */
    convertValueFromModel: function (value) {
        return value == null ? null : moment(value, this.getComponentOption("valueFormat"));
    },
    /**
     * get layout
     * @returns {CellLayout}
     */
    getLayout: function () {
        return this.props.layout;
    },
    /**
     * get id of component
     * @returns {string}
     */
    getId: function () {
        return this.getLayout().getId();
    },
    /**
     * get div id
     * @returns {string}
     */
    getDivId: function () {
        return "div_" + this.getId();
    },
    /**
     * get div css
     * @returns {string}
     */
    getDivCSS: function () {
        return this.getLayout().getAdditionalCSS("div", "input-group");
    },
    /**
     * get component css
     * @returns {string}
     */
    getCSS: function () {
        return this.getLayout().getAdditionalCSS("comp", "form-control");
    }
});