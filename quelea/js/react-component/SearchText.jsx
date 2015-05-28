/**
 * search text
 */
var NSearchText = React.createClass({
    propTypes: {
        // model
        model: React.PropTypes.object,
        // CellLayout
        layout: React.PropTypes.object
    },
    getDefaultProps: function () {
        return {
            defaultOptions: {
                searchButtonIcon: "screenshot",
                advancedSearchButtonIcon: "search"
            }
        };
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
        return (<div className="input-group search-text">
            <input type="text" className={this.getCombineCSS("form-control search-code", "text")}
                   id={this.getId()} onChange={this.onComponentChange}/>
            <span className="input-group-btn" style={{width: "0px"}}></span>
            <input type="text" className={this.getCombineCSS("form-control search-label", "label")}
                   readOnly={true} onFocus={this.onLabelFocused}/>
            <span className="input-group-addon search-btn" onClick={this.onSearchClicked}>
                <Glyphicon glyph={this.getComponentOption("searchButtonIcon")}/>
            </span>
            <span className="input-group-addon advanced-search-btn" onClick={this.onAdvancedSearchClicked}>
                <Glyphicon glyph={this.getComponentOption("advancedSearchButtonIcon")}/>
            </span>
        </div>);
    },
    /**
     * on advanced search clicked
     */
    onAdvancedSearchClicked: function () {
        alert("onAdvancedSearchClicked");
    },
    /**
     * on search clicked
     */
    onSearchClicked: function () {
        alert("onSearchClicked");
    },
    /**
     * transfer focus to first text input
     */
    onLabelFocused: function () {
        this.getComponent().focus();
    },
    /**
     * on component changed
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
    }
});