/**
 * form component, a div
 */
var NForm = React.createClass({
    propTypes: {
        // model
        model: React.PropTypes.object,
        // layout, FormLayout
        layout: React.PropTypes.object
    },
    /**
     * render row
     * @param row {RowLayout}
     */
    renderRow: function (row) {
        var _this = this;
        var cells = row.getCells().map(function (cell) {
            return <NFormCell layout={cell} model={_this.getModel()}/>;
        });
        return (<div className="row">{cells}</div>);
    },
    /**
     * render
     * @returns {XML}
     */
    render: function () {
        return (<div>{this.props.layout.getRows().map(this.renderRow)}</div>);
    },
    /**
     * get model
     * @returns {*}
     */
    getModel: function () {
        return this.props.model;
    },
    /**
     * get layout
     * @returns {*}
     */
    getLayout: function () {
        return this.props.layout;
    }
});
/**
 * form cell component
 */
var NFormCell = React.createClass({
    propTypes: {
        // model, whole model, not only for this cell
        // use id to get the value of this cell from model
        model: React.PropTypes.object,
        // CellLayout
        layout: React.PropTypes.object
    },
    /**
     * render text input
     * @returns {XML}
     */
    renderText: function () {
        return <NText model={this.getModel()} layout={this.getLayout()}/>;
    },
    /**
     * render checkbox
     * @returns {XML}
     */
    renderCheck: function () {
        return <NCheck model={this.getModel()} layout={this.getLayout()}/>;
    },
    /**
     * render datetime picker
     * @returns {XML}
     */
    renderDateTime: function () {
        return <NDateTime model={this.getModel()} layout={this.getLayout()}/>;
    },
    /**
     * render select
     * @returns {XML}
     */
    renderSelect: function () {
        return <NSelect model={this.getModel()} layout={this.getLayout()}/>;
    },
    /**
     * render table
     * @returns {XML}
     */
    renderTable: function () {
        return <NTable model={this.getModel()} layout={this.getLayout()}/>;
    },
    /**
     * render input component
     */
    renderInputComponent: function () {
        switch (this.getLayout().getComponentType()) {
            case ComponentConstants.Text:
                return this.renderText();
            case ComponentConstants.Date:
                return this.renderDateTime();
            case ComponentConstants.Select:
                return this.renderSelect();
            case ComponentConstants.Check:
                return this.renderCheck();
            case ComponentConstants.Radio:
            default:
                throw new ComponentException(ComponentConstants.Err_Unsupported_Component,
                    "Component type[" + this.getLayout().getComponentType() + "] is not supported yet.");
        }
    },
    /**
     * get css class
     * @returns {string}
     */
    getCSSClassName: function () {
        var width = this.getLayout().getWidth();
        var css = "col-sm-" + width + " col-md-" + width + " col-lg-" + width;
        return this.getLayout().getCellCSS(css);
    },
    /**
     * render
     * @returns {XML}
     */
    render: function () {
        if (this.getLayout().getComponentType() == ComponentConstants.Table) {
            return (<div className={this.getCSSClassName()}>
                {this.renderTable()}
            </div>);
        } else {
            return (<div className={this.getCSSClassName()}>
                <label htmlFor={this.getLayout().getId()}
                       className={this.getLayout().getLabelCSS()}>{this.getLayout().getLabel()}:</label>
                {this.renderInputComponent()}
            </div>);
        }
    },
    /**
     * get model
     * @returns {*}
     */
    getModel: function () {
        return this.props.model;
    },
    /**
     * get layout
     * @returns {CellLayout}
     */
    getLayout: function () {
        return this.props.layout;
    }
});