/**
 * checkbox
 */
var NCheck = React.createClass({
    propTypes: {
        // model
        model: React.PropTypes.object,
        // CellLayout
        layout: React.PropTypes.object
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
        // set model value to component
        this.getComponent().prop("checked", this.getValueFromModel());
        // add post change listener to handle model change
        this.getModel().addListener(this.getId(), "post", "change", this.onModelChange);
    },
    /**
     * did mount
     */
    componentDidMount: function () {
        // set model value to component
        this.getComponent().prop("checked", this.getValueFromModel());
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
     * render check box, using glyphicon instead
     * @returns {XML}
     */
    renderCheckbox: function () {
        return (<a href="javascript:void(0);" onClick={this.handleHrefClicked} className="n-checkbox">
            <Glyphicon glyph={this.isChecked() ? "check" : "unchecked"} bsSize="large"/>
        </a>);
    },
    /**
     * render
     * @returns {XML}
     */
    render: function () {
        return (<div className={this.getCSS()}>
            <label>
                {this.renderCheckbox()}
                <input type="checkbox" id={this.getId()} style={{display: "none"}}
                       onChange={this.onComponentChange}/>
            </label>
        </div>);
    },
    /**
     * handle href clicked event
     */
    handleHrefClicked: function () {
        this.setValueToModel(!this.isChecked());
    },
    /**
     * on component change
     * @param evt
     */
    onComponentChange: function (evt) {
        // synchronize value to model
        this.setValueToModel(evt.target.checked);
    },
    /**
     * on model change
     * @param evt
     */
    onModelChange: function (evt) {
        this.getComponent().prop("checked", evt.new === true);
        this.forceUpdate();
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
        return this.getCombineCSS("", "comp");
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
     * is checked or not
     * @returns {boolean}
     */
    isChecked: function () {
        return this.getValueFromModel() === true;
    }
});