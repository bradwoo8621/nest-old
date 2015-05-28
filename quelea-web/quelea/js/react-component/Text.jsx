/**
 * text input
 */
var NText = React.createClass({
    propTypes: {
        // model
        model: React.PropTypes.object,
        // CellLayout
        layout: React.PropTypes.object
    },
    getDefaultProps: function () {
        return {defaultOptions: {}};
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
     * did update
     * @param prevProps
     * @param prevState
     */
    componentDidUpdate: function (prevProps, prevState) {
        this.getComponent().val(this.getValueFromModel());
        // add post change listener to handle model change
        this.getModel().addListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * did mount
     */
    componentDidMount: function () {
        // set model value to component
        this.getComponent().val(this.getValueFromModel());
        // add post change listener to handle model change
        this.getModel().addListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * will unmount
     */
    componentWillUnmount: function () {
        // remove post change listener to handle model change
        this.getModel().removeListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * render
     * @returns {XML}
     */
    render: function () {
        return <input type="text" className={this.getCSS()} id={this.getId()}
                      onChange={this.onComponentChange} disabled={this.isComponentDisabled()}/>
    },
    /**
     * on component change
     * @param evt
     */
    onComponentChange: function (evt) {
        this.setValueToModel(evt.target.value);
    },
    /**
     * on model change
     * @param evt
     */
    onModelChange: function (evt) {
        this.getComponent().val(evt.new);
    },
    /**
     * get component
     * @returns {*|jQuery|HTMLElement}
     */
    getComponent: function () {
        return $("#" + this.getId());
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
        return this.getModel().get(this.getId());
    },
    /**
     * set value to model
     * @param value
     */
    setValueToModel: function (value) {
        this.getModel().set(this.getId(), value);
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
     * get component css
     * @returns {string}
     */
    getCSS: function () {
        return this.getCombineCSS("form-control", "comp");
    },
    /**
     * get combine css
     * @param originalCSS css class names
     * @param additionalKey key of additional css in layout
     * @returns {*}
     */
    getCombineCSS: function (originalCSS, additionalKey) {
        var additionalCSS = this.getLayout().getAdditionalCSS(additionalKey);
        return additionalCSS.length == 0 ? originalCSS : (originalCSS + " " + additionalCSS);
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
     * is component disabled
     * @returns {boolean}
     */
    isComponentDisabled: function () {
        var func = this.getComponentOption("enabled");
        if (func == null) {
            return false;
        } else {
            return !func.call(this, this.getModel(), this.getValueFromModel());
        }
    }
});