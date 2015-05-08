/**
 * search text, with two buttons(max)
 */
var SearchText = React.createClass({
    __classNames: {
        control: "form-control search-code",
        div: "input-group search-text",
        label: "form-control search-label",
        separator: "input-group-btn",
        addon: "input-group-btn",
        calendarButton: "glyphicon glyphicon-calendar",
        searchButton: "screenshot",
        advancedSearchButton: "search",
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
    __displayLabels: {
        advancedSearchTooltip: "Advanced Search..."
    },
    /**
     * get display label
     * @param name
     * @returns {String}
     */
    getDisplayLabel: function (name) {
        return this.props["dl_" + name] ? this.props["dl_" + name] : this.__displayLabels[name];
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

        // place holder string
        placeholder: React.PropTypes.string,

        // search function
        search: React.PropTypes.func,
        // advanced search function
        advancedSearch: React.PropTypes.func,

        // onChange event handler
        onChange: React.PropTypes.func
    },
    /**
     * override react method
     * @param prevProps
     * @param prevState
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
        this.getComponent().val(this.getValueFromModel());
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
     * render search button
     * @returns {XML}
     */
    renderSearchButton: function () {
        return (
            <Button onclick={this.handleSearchClicked}><Glyphicon glyph={this.getCSSClass("searchButton")}/></Button>);
    },
    /**
     * render advanced search button
     * @returns {XML}
     */
    renderAdvancedSearchButton: function () {
        return (
            <OverlayTrigger placement="top"
                            overlay={<Popover>{this.getDisplayLabel("advancedSearchTooltip")}</Popover>}>
                <Button onClick={this.handleAdvancedSearchClicked}>
                    <Glyphicon glyph={this.getCSSClass("advancedSearchButton")}/>
                </Button>
            </OverlayTrigger>
        );
    },
    /**
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        return (<OverlayTrigger placement="top" overlay={this.renderErrorPopover()} trigger="manual" ref="overlay">
            <div className={this.getCSSClass("div")} onMouseMove={this.mouseMove} onMouseOut={this.mouseOut}>
                <input type="text" id={this.getId()} className={this.getCSSClass("control")}
                       placeholder={this.props.placeholder} onChange={this.handleComponentChange}/>
                <span className={this.getCSSClass("separator")} style={{width: "0px"}}></span>
                <input type="text" className={this.getCSSClass("label")} readOnly={true} onFocus={this.transFocus}/>

                <div className={this.getCSSClass("addon")}>
                    {this.renderSearchButton()}
                    {this.renderAdvancedSearchButton()}
                </div>
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
    /**
     * handle component change
     * @param e
     */
    handleComponentChange: function (e) {
        // synchronize value to model
        this.setValueToModel(e.target.value);
        if (this.props.onChange) {
            this.props.onChange(e, e.target.value);
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
     * transfer focus to first text input
     */
    transFocus: function () {
        this.getComponent().focus();
    },
    /**
     * handle search clicked
     */
    handleSearchClicked: function () {
        if (this.props.search) {
            this.props.search();
        }
    },
    /**
     * handle advanced search clicked
     */
    handleAdvancedSearchClicked: function () {
        if (this.props.advancedSearch) {
            this.props.advancedSearch();
        }
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
