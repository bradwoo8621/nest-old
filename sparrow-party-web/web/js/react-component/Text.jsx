/**
 * text input
 */
var Text = React.createClass({
    __classNames: {
        control: "form-control",
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
        this.getComponent().val(this.getValueFromModel());
    },
    /**
     * override react method
     * @override
     */
    componentDidMount: function () {
        // set model value to component
        this.getComponent().val(this.getValueFromModel());
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
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        if (this.hasError()) {
            return (<OverlayTrigger placement="top" overlay={this.renderErrorPopover()}>
                <input type="text" className={this.getCSSClass("control")} id={this.getId()}
                       onChange={this.handleComponentChange}/>
            </OverlayTrigger>);
        } else {
            return <input type="text" className={this.getCSSClass("control")} id={this.getId()}
                          onChange={this.handleComponentChange}/>
        }
    },
    /**
     * handle component change
     * @param e
     */
    handleComponentChange: function (e) {
        // synchronize value to model
        this.setValueToModel(e.target.value);
        // invoke change event
        if (this.props.onChange) {
            this.props.onChange(e, this.getComponent().val());
        }
    },
    /**
     * handle model change
     * @param evt
     */
    handleModelChange: function (evt) {
        this.getComponent().val(evt.newValue);
    },
    /**
     * get component
     * @returns {*|jQuery|HTMLElement}
     */
    getComponent: function () {
        return $("#" + this.getId());
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
