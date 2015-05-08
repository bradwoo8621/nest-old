/**
 * check box, use icon instead
 */
var CheckBox = React.createClass({
    __classNames: {
        control: "form-vertical-checkbox",
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

        // onChange event handler
        onChange: React.PropTypes.func
    },
    /**
     * override react method
     * @param prevProps {*}
     * @param prevState {*}
     * @override
     */
    componentDidUpdate: function (prevProps, prevState) {
        // set model value to component
        this.getComponent().prop("checked", this.getValueFromModel());
    },
    /**
     * override react method
     * @override
     */
    componentDidMount: function () {
        // set model value to component
        this.getComponent().prop("checked", this.getValueFromModel());
        // add post change listener to handle model change
        this.getModel().addListener(this.getId(), "post", this.handleModelChange);
    },
    /**
     * override react method
     * @override
     */
    componentWillUnmount: function () {
        // remove post change listener to handle model change
        this.getModel().removeListener(this.getId(), "post", this.handleModelChange);
    },
    /**
     * render error popover.
     * error messages will be rendered as divs.
     * @returns {XML}
     */
    renderErrorPopover: function () {
        var _this = this;
        return (<Popover className={this.getCSSClass("errorPopover")}>
            {this.getError().map(function (element) {
                return (<div>{element.replaceMessage([_this.getLabel()])}</div>);
            })}
        </Popover>);
    },
    /**
     * render checkbox, use glyphicon instead.
     * @returns {XML}
     */
    renderCheckbox: function () {
        return (<a href="javascript:void(0)" onClick={this.handleHrefClicked}>
            <Glyphicon glyph={this.isChecked() ? "check" : "unchecked"} bsSize="large"/>
        </a>);
    },
    /**
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        if (this.hasError()) {
            return (<div><OverlayTrigger placement="top" overlay={this.renderErrorPopover()}>
                <label className={this.getCSSClass("control")}>
                    {this.renderCheckbox()}
                    <input type="checkbox" id={this.getId()}
                           onChange={this.handleComponentChange} style={{display: "none"}}/>
                </label>
            </OverlayTrigger></div>);
        } else {
            return (
                <div>
                    <label className={this.getCSSClass("control")}>
                        {this.renderCheckbox()}
                        <input type="checkbox" id={this.getId()}
                               onChange={this.handleComponentChange} style={{display: "none"}}/>
                    </label>
                </div>
            );
        }
    },
    /**
     * handle href clicked
     */
    handleHrefClicked: function () {
        this.setValueToModel(!this.isChecked());
        // force update component
        this.forceUpdate();
    },
    /**
     * handle component change
     * @param e
     */
    handleComponentChange: function (e) {
        // synchronize value to model
        this.setValueToModel(e.target.checked);
        if (this.props.onChange) {
            this.props.onChange(e, e.target.checked);
        }
    },
    /**
     * handle model change
     * @param evt
     */
    handleModelChange: function (evt) {
        this.getComponent().prop("checked", evt.newValue === true);
    },
    /**
     * get component
     * @returns {*|jQuery|HTMLElement}
     */
    getComponent: function () {
        return $("#" + this.getId());
    },
    /**
     * get data model, json object
     * @returns {ModelProxy}
     */
    getModel: function () {
        return this.props.model;
    },
    /**
     * get id, string
     * @returns {String}
     */
    getId: function () {
        return this.props.id;
    },
    getLabel: function () {
        return this.props.label;
    },
    /**
     * get value from model by id of props, returns null or boolean value
     * @returns {boolean}
     */
    getValueFromModel: function () {
        return this.getModel().get(this.getId());
    },
    /**
     * set value to model by id of props, value should be boolean value or null
     * @param value {boolean}
     */
    setValueToModel: function (value) {
        this.getModel().set(this.getId(), value);
    },
    /**
     * get error of checkbox
     * @returns {[String]}
     */
    getError: function () {
        if (this.props.error === undefined || this.props.error == null) {
            return null;
        }
        return this.props.error[this.getId()];
    },
    /**
     * check if there is error of the checkbox
     * @returns {boolean}
     */
    hasError: function () {
        var error = this.getError();
        return error != null && error.length > 0;
    },
    /**
     * return the value of model
     * @returns {boolean}
     */
    isChecked: function () {
        return this.getValueFromModel() === true;
    }
});
