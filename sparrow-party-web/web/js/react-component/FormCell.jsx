/**
 * form
 */
var Form = React.createClass({
    /**
     * @override
     */
    propTypes: {
        // ModelProxy
        model: React.PropTypes.object.isRequired,
        // Layout
        layout: React.PropTypes.object.isRequired,

        // class name which will be applied to div
        className: React.PropTypes.string
    },
    /**
     * override react method
     * @returns {*}
     * @override
     */
    getInitialState: function () {
        return {
            // validate result
            validateResult: null
        };
    },
    /**
     * render form rows
     * @param row
     * @returns {XML}
     */
    renderRows: function (row) {
        var _this = this;
        var cells = row.cells().map(function (cell) {
            return (<FormCell options={cell} model={_this.getModel()}
                              modelLayout={_this.getModelLayout().get(cell.id)} error={_this.getValidateResult()}
                              ref={cell.id}/>);
        });
        return (<div className="row">{cells}</div>);
    },
    /**
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        return (<div className={this.props.className}>
            {this.getLayout().rows().map(this.renderRows)}
        </div>);
    },
    /**
     * get ui layout
     * @returns {Layout}
     */
    getLayout: function () {
        return this.props.layout;
    },
    /**
     * get data model
     * @returns {ModelProxy}
     */
    getModel: function () {
        return this.props.model;
    },
    /**
     * get data model layout
     * @returns {ModelLayoutProxy}
     */
    getModelLayout: function () {
        return this.getModel().getDataModelLayout();
    },
    /**
     * validate data model
     */
    validate: function () {
        var result = this.getModel().validate();
        this.setState({validateResult: result});
        return result;
    },
    /**
     * get validate result
     * @returns {*}
     */
    getValidateResult: function () {
        return this.state.validateResult;
    },
    /**
     * check there is error existed in form or not
     * will call validate automatically
     */
    hasError: function (error) {
        if (error == undefined || error == null) {
            return false;
        } else {
            for (var key in error) {
                var detailError = error[key];
                if (detailError != null && detailError.length > 0) {
                    return true;
                }
            }
        }
        return false;
    }
});
/**
 * form cell
 */
var FormCell = React.createClass({
    __classNames: {
        cell: "form-group",
        error: "has-error",
        label: "control-label form-vertical-label"
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
        // options, cell ui layout information
        options: React.PropTypes.shape({
            id: React.PropTypes.string,
            label: React.PropTypes.string,
            type: React.PropTypes.oneOf(["text", "date", "select", "check", "search", "table", "empty"]).isRequired,

            // for type is "select"
            selectOptions: React.PropTypes.object,
            needSearchBox: React.PropTypes.bool,
            parent: React.PropTypes.object,

            // for type is "search"
            placeholder: React.PropTypes.string,
            // search function
            search: React.PropTypes.func,
            // advanced search function
            advancedSearch: React.PropTypes.func,

            // for type is "table"
            columns: React.PropTypes.array(React.PropTypes.shape({
                title: React.PropTypes.string,
                data: React.PropTypes.string, // path of value
                render: React.PropTypes.func,
                width: React.PropTypes.number
            })),
            layout: React.PropTypes.shape({
                scrollY: React.PropTypes.number, // eg. 200
                scrollX: React.PropTypes.bool,
                fixedLeftColumns: React.PropTypes.number,
                fixedRightColumns: React.PropTypes.number,
                addable: React.PropTypes.bool,
                removable: React.PropTypes.bool,
                editable: React.PropTypes.bool
            }),
            editLayout: React.PropTypes.func,

            // layout
            cellWidth: React.PropTypes.oneOf([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12])
        }).isRequired,

        // data model
        model: React.PropTypes.object.isRequired,
        // data model layout
        modelLayout: React.PropTypes.object.isRequired,
        // error
        error: React.PropTypes.object
    },
    /**
     * override react method
     * @returns {*}
     * @override
     */
    getDefaultProps: function () {
        return {};
    },
    /**
     * render cell component
     * @returns {*}
     */
    renderInputComponent: function () {
        switch (this.getComponentType()) {
            case "text":
                return this.createText();
            case "date":
                return this.createDateText();
            case "select":
                return this.createSelect();
            case "check":
                return this.createCheck();
            case "search":
                return this.createSearch();
            default:
                return null;
        }
    },
    /**
     * create normal text
     * @returns {XML}
     */
    createText: function () {
        return <Text id={this.getId()} model={this.getModel()} error={this.getError()}
                     label={this.getLabel()}/>;
    },
    /**
     * create date text
     * @returns {XML}
     */
    createDateText: function () {
        return (<DateTimePicker id={this.getId()} model={this.getModel()} error={this.getError()}
                                label={this.getLabel()}/>);
    },
    /**
     * create checkbox
     * @returns {XML}
     */
    createCheck: function () {
        return <CheckBox id={this.getId()} model={this.getModel()} error={this.getError()}
                         label={this.getLabel()}/>;
    },
    /**
     * create select
     * @returns {XML}
     */
    createSelect: function () {
        if (this.props.options.needSearchBox) {
            return (<Select2 id={this.getId()} options={this.props.options.selectOptions}
                             minimumResultsForSearch={1}
                             model={this.getModel()} parent={this.props.options.parent}
                             error={this.getError()} label={this.getLabel()}/>);
        } else {
            return (<Select2 id={this.getId()} options={this.props.options.selectOptions}
                             model={this.getModel()} parent={this.props.options.parent}
                             error={this.getError()} label={this.getLabel()}/>)
        }
    },
    /**
     * create search text
     * @returns {XML}
     */
    createSearch: function () {
        return (
            <SearchText id={this.getId()} model={this.getModel()}
                        placeholder={this.props.options.placeholder}
                        search={this.props.options.search} advancedSearch={this.props.options.advancedSearch}
                        error={this.getError()} label={this.getLabel()}/>
        );
    },
    /**
     * create table
     * @returns {XML}
     */
    createTable: function () {
        // this.props.options is TableLayout object
        return (
            <DataTable id={this.getId()} label={this.getLabel()}
                       columns={this.props.options.columns}
                       model={this.getModel()} modelLayout={this.props.modelLayout}
                       layout={this.props.options.layout} editLayout={this.props.options.editLayout()}
                       error={this.getError()}/>
        );
    },
    /**
     * get default cell width
     * @returns {number}
     */
    getDefaultCellWidth: function () {
        return 3;
    },
    /**
     * get cell width class name
     * @returns {string}
     */
    getCellWidthClassName: function () {
        var cellWidth = this.props.options.cellWidth ? this.props.options.cellWidth : this.getDefaultCellWidth();
        // using layout
        if (this.props.options.cell && this.props.options.cell.width) {
            cellWidth = this.props.options.cell.width;
        }

        return "col-md-" + cellWidth + " " + this.getCSSClass("cell");
    },
    /**
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        var className = this.getCellWidthClassName();
        if (this.props.options.type == "table") {
            return (
                <div className={className}>
                    {this.createTable()}
                </div>
            );
        } else {
            if (this.hasError()) {
                className = this.getCSSClass("error") + " " + className;
            }
            return (
                <div className={className}>
                    <label htmlFor={this.getId()}
                           className={this.getCSSClass("label")}>{this.getLabel()}:</label>
                    {this.renderInputComponent()}
                </div>
            );
        }
    },
    /**
     * get error model
     * @return {*}
     */
    getError: function () {
        return this.props.error;
    },
    /**
     * get error of component
     * @returns {[String]}
     */
    getComponentError: function () {
        var error = this.getError();
        if (error === undefined || error == null) {
            return null;
        }
        return error[this.getId()];
    },
    /**
     * check if there is error of the checkbox
     * @returns {boolean}
     */
    hasError: function () {
        var error = this.getComponentError();
        return error != null && error.length > 0;
    },
    /**
     * get id
     * @returns {String}
     */
    getId: function () {
        return this.props.options.id;
    },
    /**
     * get label
     * @returns {String}
     */
    getLabel: function () {
        return this.props.options.label;
    },
    /**
     * get model
     * @returns {ModelProxy}
     */
    getModel: function () {
        return this.props.model;
    },
    /**
     * get component type
     * @returns {String}
     */
    getComponentType: function () {
        return this.props.options.type;
    }
});

/**
 * discard button
 */
var DiscardButton = React.createClass({
    propTypes: {
        text: React.PropTypes.string,
        icon: React.PropTypes.string,
        onClick: React.PropTypes.func
    },
    getDefaultProps: function () {
        return {
            text: "Discard",
            icon: "trash"
        };
    },
    render: function () {
        return (<Button bsStyle="danger" onClick={this.clicked}>
            <Glyphicon glyph={this.props.icon}/> {this.props.text}
        </Button>);
    },
    clicked: function () {
        if (this.props.onClick) {
            this.props.onClick();
        }
    }
});
/**
 * reset button
 */
var ResetButton = React.createClass({
    propTypes: {
        text: React.PropTypes.string,
        icon: React.PropTypes.string,
        onClick: React.PropTypes.func
    },
    getDefaultProps: function () {
        return {
            text: "Reset",
            icon: "erase"
        };
    },
    render: function () {
        return (<Button onClick={this.clicked}>
            <Glyphicon glyph={this.props.icon}/> {this.props.text}
        </Button>);
    },
    clicked: function () {
        if (this.props.onClick) {
            this.props.onClick();
        }
    }
});
/**
 * check button
 */
var CheckButton = React.createClass({
    propTypes: {
        text: React.PropTypes.string,
        icon: React.PropTypes.string,
        onClick: React.PropTypes.func
    },
    getDefaultProps: function () {
        return {
            text: "Validate",
            icon: "check"
        };
    },
    render: function () {
        return (<Button onClick={this.clicked}>
            <Glyphicon glyph={this.props.icon}/> {this.props.text}
        </Button>);
    },
    clicked: function () {
        if (this.props.onClick) {
            this.props.onClick();
        }
    }
});
/**
 * save button
 */
var SaveButton = React.createClass({
    propTypes: {
        text: React.PropTypes.string,
        icon: React.PropTypes.string,
        onClick: React.PropTypes.func
    },
    getDefaultProps: function () {
        return {
            text: "Save",
            icon: "floppy-save"
        };
    },
    render: function () {
        return (<Button bsStyle="primary" onClick={this.clicked}>
            <Glyphicon glyph={this.props.icon}/> {this.props.text}
        </Button>);
    },
    clicked: function () {
        if (this.props.onClick) {
            this.props.onClick();
        }
    }
});