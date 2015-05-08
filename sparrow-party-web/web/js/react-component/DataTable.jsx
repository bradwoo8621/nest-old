/**
 * data tables react. if scrollY is set, width of columns must be set. width
 * automatically calculation is not support yet.
 */
var DataTable = React.createClass({
    __classNames: {
        div: "panel panel-default panel-bold",
        heading: "panel-heading datatable-heading",
        headingLabel: "datatable-heading-label",
        headingButtons: "pagination pull-right",
        addButton: "plus-sign",
        body: "datatable-body",
        panelBody: "panel-body table-only",
        tableBody: "table-body",
        tableHeading: "table-heading",
        tableHeadingInner: "table-heading-inner",
        rowButtonGroup: "button-in-table-cell",
        rowButton: "datatable-operation-button",
        editButton: "pencil",
        removeButton: "remove",
        sort: "pull-right datatable-sort",
        sorted: " datatable-column-sorted",
        sortIcon: "sort",
        sortAscIcon: "sort-by-attributes",
        sortDescIcon: "sort-by-attributes-alt",
        confirmDialog: "datatable-confirm",
        table: "datatable cell-border",
        fixLeftTable: "table-fix-left",
        fixRightTable: "table-fix-right",
        fixRightTableWrapper: "table-fix-right-head-wrapper",
        fixRightTableCorner: "table-fix-right-top-corner",
        pagination: "datatable-pagination",
        editDialog: "datatable-edit-dialog",
        editDialogBody: "modal-body",
        editDialogFooter: "modal-footer",
        error: "table-has-error",
        cellError: "has-error",
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
        addButton: "Add",
        detailError: "Something invalid, see detail data please.",
        noData: "No Data Found",
        create: "Create ",
        edit: "Edit "
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

        modelLayout: React.PropTypes.object.isRequired, // model layout

        columns: React.PropTypes.object,
        layout: React.PropTypes.shape({
            scrollY: React.PropTypes.number, // eg. 200 is 200px
            scrollX: React.PropTypes.bool,
            fixedLeftColumns: React.PropTypes.number,
            fixedRightColumns: React.PropTypes.number,
            addable: React.PropTypes.bool,
            removable: React.PropTypes.bool,
            editable: React.PropTypes.bool,
            indexable: React.PropTypes.bool,
            pagable: React.PropTypes.bool,
            countPerPage: React.PropTypes.number,
            searchable: React.PropTypes.bool,
            sortable: React.PropTypes.oneOfType([React.PropTypes.bool, React.PropTypes.object])
        }),
        editLayout: React.PropTypes.object,

        newClassName: React.PropTypes.string
    },
    /**
     * @returns {*}
     * @override
     */
    getDefaultProps: function () {
        return {};
    },
    /**
     * @returns {*}
     * @override
     */
    getInitialState: function () {
        return {
            currentPageIndex: 1, // page number count from 1
            pageCount: 1, // page count default 1
            countPerPage: 10, // count per page default 10
            searchText: null, // search text,
            sortColumn: null,
            sortWay: null,

            // model layout of element in list
            editModelLayout: new ModelLayoutProxy(this.props.modelLayout.layout)
        };
    },
    /**
     * override react method
     * @param prevProps
     * @param prevState
     * @override
     */
    componentDidUpdate: function (prevProps, prevState) {
        this.renderIfIE8();
    },
    /**
     * override react method
     * @override
     */
    componentDidMount: function () {
        this.createComponent();
        this.getModel().addListener(this.getId(), ["post-add", "post-remove", "post"], this.handleModelChange);
    },
    /**
     * override react method
     * @override
     */
    componentWillUnmount: function () {
        this.getModel().removeListener(this.getId(), ["post-add", "post-remove", "post"], this.handleModelChange);
    },
    /**
     * render when IE8, fixed the height of table since IE8 doesn't support max-height
     */
    renderIfIE8: function () {
        if (isIE() != 8 || this.props.layout.scrollY === undefined) {
            return;
        }
        var mainTable = this.getComponent();
        var leftFixedDiv = $("#scrolled-left-columns-body-" + this.getId());
        var rightFixedDiv = $("#scrolled-right-columns-body-" + this.getId());
        var trs = mainTable.find("tr");
        var rowCount = trs.length;
        var height = rowCount * 32; // 32 is defined in css, if value in css is changed, it must be changed together
        if (height > this.props.layout.scrollY) {
            height = this.props.layout.scrollY;
        }
        // calculate height of body if ie8 and scrollY
        mainTable.closest("div").css({height: height + 17});
        leftFixedDiv.css({height: height});
        rightFixedDiv.css({height: height});
    },
    /**
     * create component
     */
    createComponent: function () {
        var _this = this;
        this.renderIfIE8();
        $("#scrolled-body-" + this.getId()).scroll(function (e) {
            var $this = $(this);
            $("#scrolled-head-" + _this.getId()).scrollLeft($this.scrollLeft());
            $("#scrolled-left-columns-body-" + _this.getId()).scrollTop($this.scrollTop());
            $("#scrolled-right-columns-body-" + _this.getId()).scrollTop($this.scrollTop());
        });
    },
    /**
     * render search  box
     * @returns {XML}
     */
    renderSearchBox: function () {
        if (this.props.layout.searchable) {
            return (
                <input id={"datatable-search-" + this.getId()} className="form-control table-search-box pull-right"
                       placeholder="Search..." onChange={this.handleSearchBoxChanged}/>
            );
        } else {
            return null;
        }
    },
    /**
     * render heading buttons
     * @returns {XML}
     */
    renderHeadingButtons: function () {
        if (this.isAddable()) {
            return (
                <ul className={this.getCSSClass("headingButtons")}>
                    <li>
                        <a href="javascript:void(0);" onClick={this.addClicked}>
                            <Glyphicon glyph={this.getCSSClass("addButton")}/> {this.getDisplayLabel("addButton")}
                        </a>
                    </li>
                </ul>
            );
        } else {
            return null;
        }
    },
    /**
     * check has error or not
     * @returns {boolean}
     */
    hasError: function () {
        var error = this.getError();
        return error != null && error.length > 0;
    },
    /**
     * check has table level error or not
     * @returns {boolean}
     */
    hasTableLevelError: function () {
        return this.hasError() && this.getError().some(function (element) {
                return typeof(element) === "string";
            });
    },
    /**
     * check has detail error or not
     * @returns {boolean}
     */
    hasDetailError: function () {
        return this.hasError() && this.getError().some(function (element) {
                return typeof(element) != "string";
            });
    },
    /**
     * render title error popover
     * @returns {XML}
     */
    renderTitleErrorPopover: function () {
        var _this = this;
        return (<Popover className={this.getCSSClass("errorPopover")}>
            {this.getError().map(function (element) {
                if (typeof(element) === "string") {
                    return (<div>{element.replaceMessage([_this.getLabel()])}</div>);
                } else {
                    return null;
                }
            })}
        </Popover>);
    },
    /**
     * render panel heading label
     * @returns {XML}
     */
    renderPanelHeadingLabel: function () {
        if (this.hasTableLevelError()) {
            return (<div className="col-md-3">
                <OverlayTrigger placement="top" overlay={this.renderTitleErrorPopover()}>
                    <label className={this.getCSSClass("headingLabel")}>{this.getLabel()}</label>
                </OverlayTrigger>
            </div>);
        } else if (this.hasError()) {
            return (<div className="col-md-3">
                <OverlayTrigger placement="top"
                                overlay={<Popover className={this.getCSSClass("errorPopover")}>{this.getDisplayLabel("detailError")}</Popover>}>
                    <label className={this.getCSSClass("headingLabel")}>{this.getLabel()}</label>
                </OverlayTrigger>
            </div>);
        } else {
            return <div className="col-md-3"><label
                className={this.getCSSClass("headingLabel")}>{this.getLabel()}</label>
            </div>;
        }
    },
    /**
     * render panel heading
     * @returns {XML}
     */
    renderPanelHeading: function () {
        return (<div className={this.getCSSClass("heading")}>
            <div className="row">
                {this.renderPanelHeadingLabel()}
                <div className="col-md-9" style={{paddingRight: "5px"}}>
                    {this.renderHeadingButtons()}
                    {this.renderSearchBox()}
                </div>
            </div>
        </div>);
    },
    /**
     * render operation cell
     * @param column
     * @param data
     * @returns {XML}
     */
    renderOperationCell: function (column, data) {
        var editButton = column.editable ?
            (<Button bsSize="xsmall" bsStyle="link" onClick={this.editClicked}
                     className={this.getCSSClass("rowButton")}>
                <Glyphicon glyph={this.getCSSClass("editButton")}/>
            </Button>) : null;
        var removeButton = column.removable ?
            (<Button bsSize="xsmall" bsStyle="link" onClick={this.removeClicked}
                     className={this.getCSSClass("rowButton")}>
                <Glyphicon glyph={this.getCSSClass("removeButton")}/>
            </Button>) : null;
        return (<ButtonGroup className={this.getCSSClass("rowButtonGroup")}>
            {editButton}
            {removeButton}
        </ButtonGroup>);
    },
    /**
     * render cell error popover
     * @param error
     * @param title
     * @returns {XML}
     */
    renderCellErrorPopover: function (error, title) {
        return (<Popover className={this.getCSSClass("errorPopover")}>
            {error.map(function (element) {
                if (typeof(element) === "string") {
                    return (<div>{element.replaceMessage([title])}</div>);
                } else {
                    return null;
                }
            })}
        </Popover>);
    },
    /**
     * render body rows
     * @param row
     * @param rowIndex
     * @param all
     * @param leftFixed
     * @param rightFixed
     * @returns {XML}
     */
    renderBodyRow: function (row, rowIndex, all, leftFixed, rightFixed) {
        var indexToRender = this.getRenderMaxColumnIndex(all, leftFixed, rightFixed);
        var columnIndex = 0;
        var _this = this;
        var className = rowIndex % 2 == 0 ? "even" : "odd";
        var detailError = this.hasDetailError() ? this.getDetailError(rowIndex) : null;
        return (<tr className={className}>{
            this.columns.map(function (column) {
                if (columnIndex >= indexToRender.min && columnIndex <= indexToRender.max) {
                    // column is fixed.
                    columnIndex++;
                    var style = {width: column.width};
                    if (!(column.visible === undefined || column.visible === true)) {
                        style.display = "none";
                    }
                    var data;
                    if (column.editable || column.removable) {
                        data = _this.renderOperationCell(column, row);
                    } else if (column.indexable) {
                        data = rowIndex;
                    } else {
                        data = row[column.data];
                    }
                    if (data != null && column.render) {
                        data = column.render(data, row);
                    }
                    if (detailError != null && detailError[column.data] !== undefined && detailError[column.data].length > 0) {
                        return (<td style={style} className={_this.getCSSClass("cellError")}>
                            <OverlayTrigger placement="top"
                                            overlay={_this.renderCellErrorPopover(detailError[column.data], column.title)}>
                                <span>{data}</span>
                            </OverlayTrigger>
                        </td>);
                    } else {
                        return (<td style={style}>{data}</td>);
                    }
                } else {
                    columnIndex++;
                }
            })
        }</tr>)
    },
    /**
     * compute page count
     * @returns {number}
     */
    computePageCount: function () {
        var data = this.getValueFromModel();
        var pageCount = data.length == 0 ? 1 : data.length / this.state.countPerPage;
        return (Math.floor(pageCount) == pageCount) ? pageCount : (Math.floor(pageCount) + 1);
    },
    /**
     * compute pagination
     * @returns {{min: number, max: number}}
     */
    computePagination: function () {
        var minRowIndex = 0;
        var maxRowIndex = 999999;
        if (this.isPagable()) {
            this.state.countPerPage = this.props.layout.countPerPage ?
                this.props.layout.countPerPage : this.state.countPerPage;
            this.state.pageCount = this.computePageCount();
            this.state.currentPageIndex = this.state.currentPageIndex > this.state.pageCount ? this.state.pageCount : this.state.currentPageIndex;
            minRowIndex = (this.state.currentPageIndex - 1) * this.state.countPerPage + 1;
            maxRowIndex = minRowIndex + this.state.countPerPage - 1;
        }
        return {min: minRowIndex, max: maxRowIndex};
    },
    /**
     * render body
     * @param all
     * @param leftFixed
     * @param rightFixed
     * @returns {XML}
     */
    renderBody: function (all, leftFixed, rightFixed) {
        var data = this.getDataToDisplay();
        var rowIndex = 1;
        var _this = this;
        var range = this.computePagination();
        return (<tbody onMouseMove={this.rowMouseMove} onMouseOut={this.rowMouseOut}>
        {data.map(function (element) {
            if (rowIndex >= range.min && rowIndex <= range.max) {
                return _this.renderBodyRow(element, rowIndex++, all, leftFixed, rightFixed);
            } else {
                rowIndex++;
                return null;
            }
        })}
        </tbody>);
    },
    /**
     * render sort button
     * @param column
     * @returns {XML}
     */
    renderSortButton: function (column) {
        if (this.isSortable(column)) {
            var icon = this.getCSSClass("sortIcon");
            var sortClass = this.getCSSClass("sort");
            if (this.state.sortColumn == column.data) {
                sortClass += this.getCSSClass("sorted");
                if (this.state.sortWay == "asc") {
                    icon = this.getCSSClass("sortAscIcon");
                } else {
                    icon = this.getCSSClass("sortDescIcon");
                }
            }
            return (<a href="javascript:void(0);" className={sortClass}
                       onClick={this.sortColumn} data-prop={column.data}>
                <Glyphicon glyph={icon}/>
            </a>);
        }
    },
    /**
     * render heading content
     * @param all
     * @param leftFixed
     * @param rightFixed
     * @returns {XML}
     */
    renderHeading: function (all, leftFixed, rightFixed) {
        var indexToRender = this.getRenderMaxColumnIndex(all, leftFixed, rightFixed);
        var columnIndex = 0;
        var _this = this;
        return (<thead>
        {this.columns.map(function (column) {
            if (columnIndex >= indexToRender.min && columnIndex <= indexToRender.max) {
                // column is fixed.
                columnIndex++;
                var style = {};
                style.width = column.width;
                if (!(column.visible === undefined || column.visible === true)) {
                    style.display = "none";
                }
                return (<td style={style}>
                    {column.title}
                    {_this.renderSortButton(column)}
                </td>);
            } else {
                columnIndex++;
            }
        })}
        </thead>);
    },
    /**
     * get max render column index. all is true, return 10000; all is false,
     * return max fixed left column index
     * @param all
     * @param leftFixed
     * @param rightFixed
     * @returns {{}}
     */
    getRenderMaxColumnIndex: function (all, leftFixed, rightFixed) {
        var index = {};
        if (all) {
            index.min = 0;
            index.max = 10000;
        } else if (leftFixed) {
            index.min = 0;
            index.max = this.getMaxFixedLeftColumnIndex();
        } else if (rightFixed) {
            index.min = this.getMinFixedRightColumnIndex();
            index.max = 10000;
        }
        return index;
    },
    /**
     * get max fixed left column index. if no column is fixed in left, return -1
     * @returns {number}
     */
    getMaxFixedLeftColumnIndex: function () {
        return this.fixedLeftColumns - 1;
    },
    /**
     * get min fixed right column index. if no column is fixed in right, return
     * max column index.
     * eg. there are 3 columns in table, if no fixed right column, return 3. if
     * 1 fixed right column, return 2.
     * @returns {number}
     */
    getMinFixedRightColumnIndex: function () {
        return this.columns.length() - this.fixedRightColumns;
    },
    /**
     * compute table style
     * @returns {{width: number, maxWidth: number}}
     */
    computeTableStyle: function () {
        var width = 0;
        if (this.props.layout.scrollX) {
            width = 0;
            // calculate width
            this.columns.forEach(function (element) {
                if (element.visible === undefined || element.visible === true) {
                    width += element.width;
                }
            });
        } else {
            width = "100%";
        }
        return {width: width, maxWidth: width};
    },
    /**
     * compute fixed left columns width
     * @returns {number}
     */
    computeFixedLeftColumnsWidth: function () {
        var width = 0;
        var fixedLeftColumns = this.getMaxFixedLeftColumnIndex();
        var columnIndex = 0;
        this.columns.forEach(function (element) {
            if (columnIndex <= fixedLeftColumns && (element.visible === undefined || element.visible === true)) {
                // column is fixed.
                width += element.width;
            }
            columnIndex++;
        });
        return width;
    },
    /**
     * compute fixed right columns width
     * @returns {number}
     */
    computeFixedRightColumnsWidth: function () {
        var width = 0;
        var fixedRightColumns = this.getMinFixedRightColumnIndex();
        var columnIndex = 0;
        this.columns.forEach(function (element) {
            if (columnIndex >= fixedRightColumns && (element.visible === undefined || element.visible === true)) {
                // column is fixed
                width += element.width;
            }
            columnIndex++;
        });
        return width;
    },
    /**
     * render fixed right columns with no scroll Y
     * @returns {XML}
     */
    renderFixedRightColumnsNoScrollY: function () {
        var divStyle = {width: this.computeFixedRightColumnsWidth()};
        return (<div className={this.getCSSClass("fixRightTable")} style={divStyle}>
            <table cellSpacing="0" className={this.getCSSClass("table")} style={{width: "100%"}}>
                {this.renderHeading(false, false, true)}
                {this.renderBody(false, false, true)}
            </table>
        </div>);
    },
    /**
     * render fixed right columns with scroll Y
     * @returns {XML}
     */
    renderFixedRightColumnsScrollY: function () {
        var divStyle = {width: this.computeFixedRightColumnsWidth() + 1, right: "16px"};
        var bodyDivStyle = {width: "100%", overflow: "hidden"};
        if (this.props.layout.scrollX) {
            // ie8 box mode, scrollbar is not in height.
            // ie>8 or chrome, scrollbar is in height.
            bodyDivStyle.maxHeight = this.props.layout.scrollY - ((isIE() == 8) ? 0 : 18);
        }
        var bodyId = "scrolled-right-columns-body-" + this.getId();
        return (
            <div className={this.getCSSClass("fixRightTable")} style={divStyle}>
                <div className={this.getCSSClass("fixRightTableWrapper")}>
                    <div className={this.getCSSClass("fixRightTableCorner")}/>
                    <table cellSpacing="0" style={{width: "100%"}} className={this.getCSSClass("table")}>
                        {this.renderHeading(false, false, true)}
                    </table>
                </div>
                <div id={bodyId} style={bodyDivStyle}>
                    <table cellSpacing="0" className={this.getCSSClass("table")}>
                        {this.renderBody(false, false, true)}
                    </table>
                </div>
            </div>
        );
    },
    /**
     * render fixed right columns
     * @returns {XML}
     */
    renderFixedRightColumns: function () {
        if (!this.hasFixedRightColumns()) {
            return null;
        }
        if (this.props.layout.scrollY) {
            return this.renderFixedRightColumnsScrollY();
        } else {
            return this.renderFixedRightColumnsNoScrollY();
        }
    },
    /**
     * has fixed right columns or not
     * @returns {boolean}
     */
    hasFixedRightColumns: function () {
        return this.fixedRightColumns > 0;
    },
    /**
     * render fixed left columns with no scroll Y
     * @returns {XML}
     */
    renderFixedLeftColumnsNoScrollY: function () {
        var divStyle = {width: this.computeFixedLeftColumnsWidth()};
        return (<div className={this.getCSSClass("fixLeftTable")} style={divStyle}>
            <table cellSpacing="0" className={this.getCSSClass("table")} style={{width: "100%"}}>
                {this.renderHeading(false, true)}
                {this.renderBody(false, true)}
            </table>
        </div>);
    },
    /**
     * render fixed left columns with scroll Y
     * @returns {XML}
     */
    renderFixedLeftColumnsScrollY: function () {
        var divStyle = {width: this.computeFixedLeftColumnsWidth() + 1};
        var bodyDivStyle = {width: "100%", overflow: "hidden"};
        if (this.props.layout.scrollX) {
            bodyDivStyle.maxHeight = this.props.layout.scrollY - ((isIE() == 8) ? 0 : 18);
        }
        var bodyId = "scrolled-left-columns-body-" + this.getId();
        return (
            <div className={this.getCSSClass("fixLeftTable")} style={divStyle}>
                <table cellSpacing="0" style={{width: "100%"}} className={this.getCSSClass("table")}>
                    {this.renderHeading(false, true)}
                </table>
                <div id={bodyId} style={bodyDivStyle}>
                    <table cellSpacing="0" className={this.getCSSClass("table")}>
                        {this.renderBody(false, true)}
                    </table>
                </div>
            </div>
        );
    },
    /**
     * render fixed left columns
     * @returns {XML}
     */
    renderFixedLeftColumns: function () {
        if (!this.hasFixedLeftColumns()) {
            return null;
        }
        if (this.props.layout.scrollY) {
            return this.renderFixedLeftColumnsScrollY();
        } else {
            return this.renderFixedLeftColumnsNoScrollY();
        }
    },
    /**
     * has fixed left columns
     * @returns {boolean}
     */
    hasFixedLeftColumns: function () {
        return this.fixedLeftColumns > 0;
    },
    /**
     * render table with no scroll Y
     * @returns {XML}
     */
    renderTableNoScrollY: function () {
        return (<div className={this.getCSSClass("panelBody")}>
            <table cellSpacing="0" className={this.getCSSClass("table")} style={this.computeTableStyle()}
                   id={this.getId()}>
                {this.renderHeading(true)}
                {this.renderBody(true)}
            </table>
        </div>);
    },
    /**
     * render table with scroll Y
     * @returns {XML}
     */
    renderTableScrollY: function () {
        var style = this.computeTableStyle();
        var headId = "scrolled-head-" + this.getId();
        var bodyId = "scrolled-body-" + this.getId();
        return (<div className={this.getCSSClass("panelBody")}>
            <div className={this.getCSSClass("tableHeading")} id={headId}>
                <div className={this.getCSSClass("tableHeadingInner")} style={style}>
                    <table cellSpacing="0" className={this.getCSSClass("table")} style={style}>
                        {this.renderHeading(true)}
                    </table>
                </div>
            </div>
            <div className={this.getCSSClass("tableBody")}
                 style={{maxHeight: this.props.layout.scrollY, overflowY: "scroll"}} id={bodyId}>
                <table cellSpacing="0" className={this.getCSSClass("table")} style={style} id={this.getId()}>
                    {this.renderBody(true)}
                </table>
            </div>
        </div>);
    },
    /**
     * render table
     * @returns {XML}
     */
    renderTable: function () {
        if (this.props.layout.scrollY) {
            return this.renderTableScrollY();
        } else {
            return this.renderTableNoScrollY();
        }
    },
    /**
     * render not data reminder label
     * @returns {*}
     */
    renderNoDataReminder: function () {
        if (this.hasDataToDisplay()) {
            return null;
        } else {
            return (<div className="no-data"><span>{this.getDisplayLabel("noData")}</span></div>);
        }
    },
    /**
     * render pagination
     * @returns {*}
     */
    renderPagination: function () {
        if (this.isPagable()) {
            return (<Pagination className={this.getCSSClass("pagination")} pageCount={this.state.pageCount}
                                currentPageIndex={this.state.currentPageIndex} toPage={this.toPage}/>);
        } else {
            return null;
        }
    },
    /**
     * render edit dialog content
     * @returns {XML}
     */
    renderEditDialogContent: function () {
        return (
            <Form model={this.state.editRow} layout={this.props.editLayout}
                  className={this.getCSSClass("editDialogBody")} ref="editForm"/>
        );
    },
    /**
     * render edit dialog
     * @returns {XML}
     */
    renderEditDialog: function () {
        if (this.state.editRow && this.state.editRow != null) {
            var title = this.getLabel();
            if (this.state.editType == "add") {
                title = this.getDisplayLabel("create") + title;
            } else if (this.state.editType == "edit") {
                title = this.getDisplayLabel("edit") + title;
            }
            return (
                <Modal bsStyle="primary" title={title}
                       className={this.getCSSClass("editDialog")}
                       backdrop="static" onRequestHide={this.discardEditClicked}>
                    {this.renderEditDialogContent()}
                    <div className={this.getCSSClass("editDialogFooter")}>
                        <div className="col-md-6 btn-toolbar-left">
                            <DiscardButton onClick={this.discardEditClicked}/>
                            <ResetButton onClick={this.resetClicked}/>
                        </div>
                        <div className="col-md-6">
                            <CheckButton onClick={this.validateClicked}/>
                            <SaveButton onClick={this.confirmClicked} text="Confirm"/>
                        </div>
                    </div>
                </Modal>);
        } else {
            return null;
        }
    },
    /**
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        // if scrollY is set, force set scrollX to true, since the table will be
        // splitted to head table and body table.
        // for make sure the cell is aligned, width of columns must be set.
        if (this.props.layout.scrollY) {
            this.props.layout.scrollX = true;
        }
        // copy from this.props.columns
        this.columns = this.props.columns.clone();
        this.fixedRightColumns = this.props.layout.fixedRightColumns ? this.props.layout.fixedRightColumns : 0;
        this.fixedLeftColumns = this.props.layout.fixedLeftColumns ? this.props.layout.fixedLeftColumns : 0;

        // if editable or removable, auto add last column to render the buttons
        if (this.props.layout.editable || this.props.layout.removable) {
            var config = {
                editable: this.isRowEditable(),
                removable: this.isRowRemovable(),
                title: ""
            };
            config.width = (config.editable ? 30 : 0) + (config.removable ? 30 : 0);
            this.columns.push(config);
            this.fixedRightColumns++;
        }
        // if indexable, auto add first column to render the row index
        if (this.isIndexable()) {
            var config = {
                indexable: true,
                width: 40,
                title: ""
            };
            this.columns.splice(0, 0, config);
            this.fixedLeftColumns++;
        }
        var className = this.getCSSClass("div");
        if (this.hasError()) {
            className += " " + this.getCSSClass("error");
        }
        return (
            <div className={className} id={"datatable-" + this.getId()}>
                {this.renderPanelHeading()}
                <div className={this.getCSSClass("body")}>
                    {this.renderTable()}
                    {this.renderFixedLeftColumns()}
                    {this.renderFixedRightColumns()}
                    {this.renderNoDataReminder()}
                </div>
                {this.renderPagination()}
                {this.renderEditDialog()}
                <ModalConfirmDialog ref="confirmDialog" className={this.getCSSClass("confirmDialog")} zIndex={9000}/>
            </div>
        );
    },
    /**
     * handle search box changed
     * @param evt
     */
    handleSearchBoxChanged: function (evt) {
        var value = evt.target.value;
        if (value == "") {
            this.setState({searchText: null});
        } else {
            this.setState({searchText: value});
        }
    },
    /**
     * handle model change
     * @param evt
     */
    handleModelChange: function (evt) {
        if (evt.type == "add") {
            this.state.currentPageIndex = this.computePageCount();
            this.computePagination();
            this.setState({editRow: null, editType: null});
        } else if (evt.type == "remove") {
            this.computePagination();
            this.forceUpdate();
        } else if (evt.type == "change") {
            this.setState({editRow: null, editType: null, editIndex: null});
        }
    },
    /**
     * jump to page by given page index
     * @param pageIndex
     */
    toPage: function (pageIndex) {
        this.setState({currentPageIndex: pageIndex});
    },
    /**
     * get operating row index, in current page
     * @param evt
     * @returns {int}
     */
    getOperatingRowIndex: function (evt) {
        var tr = $(evt.target).closest("tr");
        var rows = tr.parent().children("tr");
        return rows.index(tr);
    },
    /**
     * get operating row index, in whole page
     * @param evt
     * @returns {int}
     */
    getAbsoluteOperatingRowIndex: function (evt) {
        var index = this.getOperatingRowIndex(evt);
        if (this.isPagable()) {
            return this.state.countPerPage * (this.state.currentPageIndex - 1) + index;
        } else {
            // no pagination
            return index;
        }
    },
    /**
     * sort column
     * @param evt
     */
    sortColumn: function (evt) {
        var prop = $(evt.target).closest("a").attr("data-prop");
        var sortWay = "asc";
        if (this.state.sortColumn != null && this.state.sortColumn == prop) {
            sortWay = this.state.sortWay == "asc" ? "desc" : "asc";
        }
        var render = null;
        var isNumberValue = false;
        var sorter = function (a, b) {
            var v1 = a[prop], v2 = b[prop];
            if (render !== undefined && render != null) {
                v1 = render(v1, a);
                v2 = render(v2, a);
            }
            if (v1 == null) {
                return v2 == null ? 0 : -1;
            } else if (v1 == null) {
                return 1;
            } else {
                if (isNumberValue) {
                    v1 *= 1;
                    v2 *= 1;
                }
                if (v1 > v2) {
                    return 1;
                } else if (v1 < v2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        var column = this.props.columns.get(prop);
        if (column.sort !== undefined && column.sort != null) {
            if (typeof(column.sort) === "function") {
                sorter = column.sort;
            } else if (column.sort == "number") {
                isNumberValue = true;
            } else {
                throw {name: "TableSorterTypeError", message: "Sorter type [" + column.sort + "] is not supported."};
            }
        }
        render = column.render;

        if (sortWay == "asc") {
            this.getValueFromModel().sort(sorter);
        } else {
            this.getValueFromModel().sort(function (a, b) {
                return 0 - sorter(a, b);
            });
        }
        this.setState({sortColumn: prop, sortWay: sortWay});
    },
    /**
     * row mouse move
     * @param evt
     */
    rowMouseMove: function (evt) {
        var index = this.getOperatingRowIndex(evt);
        var selector = "tbody>tr:nth-child(" + (index + 1) + ")";
        $("#scrolled-body-" + this.getId()).find(selector).addClass("hover");
        $("#scrolled-left-columns-body-" + this.getId()).find(selector).addClass("hover");
        $("#scrolled-right-columns-body-" + this.getId()).find(selector).addClass("hover");
    },
    /**
     * row mouse out
     * @param evt
     */
    rowMouseOut: function (evt) {
        var index = this.getOperatingRowIndex(evt);
        var selector = "tbody>tr:nth-child(" + (index + 1) + ")";
        $("#scrolled-body-" + this.getId()).find(selector).removeClass("hover");
        $("#scrolled-left-columns-body-" + this.getId()).find(selector).removeClass("hover");
        $("#scrolled-right-columns-body-" + this.getId()).find(selector).removeClass("hover");
    },
    /**
     * handle add button click event
     */
    addClicked: function () {
        this.setState({editRow: ModelUtil.create(this.state.editModelLayout), editType: "add"});
    },
    /**
     * handle edit button(each row) clicked event.
     * @param evt
     */
    editClicked: function (evt) {
        var editIndex = this.getAbsoluteOperatingRowIndex(evt);
        var editRow = this.getValueFromModel()[editIndex];
        this.setState({
            editRow: new ModelProxy(editRow, this.state.editModelLayout),
            editType: "edit",
            editIndex: editIndex
        });
    },
    /**
     * handle remove button(each row) clicked event
     * @param evt
     */
    removeClicked: function (evt) {
        this.refs.confirmDialog.show("Delete data?",
            ["Are you sure you want to delete data?", "It cannot be recovered."],
            this.removeRow, this.getAbsoluteOperatingRowIndex(evt));
    },
    /**
     * remove row
     * @param selectedRowIndex
     */
    removeRow: function (selectedRowIndex) {
        this.refs.confirmDialog.hide();
        this.getModel().remove(this.getId(), selectedRowIndex);
    },
    /**
     * handle discard editing/creating clicked event
     */
    discardEditClicked: function () {
        this.refs.confirmDialog.show("Discard data?",
            ["Are you sure you want to discard the editing data?", "All data will be lost and cannot be recovered."],
            this.discard);
    },
    /**
     * discard editing/creating dialog
     */
    discard: function () {
        // hide the confirm dialog
        this.refs.confirmDialog.hide();
        this.setState({editRow: null});
    },
    /**
     * handle reset editing/creating clicked event
     */
    resetClicked: function () {
        this.refs.confirmDialog.show("Reset data?",
            ["Are you sure you want to reset the editing data?", "All updated data will be lost and cannot be recovered."],
            this.reset);
    },
    /**
     * reset editing/creating dialog
     */
    reset: function () {
        // reset model
        this.state.editRow.reset();
        // invoke update force
        this.refs.editForm.forceUpdate();
        // hide the confirm dialog
        this.refs.confirmDialog.hide();
    },
    /**
     * handle validate button clicked
     */
    validateClicked: function () {
        this.refs.editForm.validate();
    },
    /**
     * handle confirm button clicked
     */
    confirmClicked: function () {
        if (this.refs.editForm.hasError(this.refs.editForm.validate())) {
            return;
        }
        if (this.state.editType == "add") {
            this.addItemIntoModel(this.state.editRow.getDataModel());
        } else if (this.state.editType == "edit") {
            this.updateItemIntoModel(this.state.editRow.getDataModel(), this.state.editIndex);
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
     * check the table is pagable or not
     * @returns {boolean}
     */
    isPagable: function () {
        return this.props.layout.pagable === true ? true : false;
    },
    /**
     * check the table is searchable or not
     * @returns {boolean}
     */
    isSearchable: function () {
        return this.props.layout.searchable === true ? true : false;
    },
    /**
     * check currently is any text existed in search box
     * @returns {*|boolean}
     */
    isSearching: function () {
        return this.isSearchable() && this.state.searchText != null && this.state.searchText != "";
    },
    /**
     * check the table is sortable or not
     * @param column
     * @returns {boolean}
     */
    isSortable: function (column) {
        if (this.props.layout.sortable === true) {
            if (column.sort === false) {
                return false;
            } else if (column.editable || column.removable || column.indexable) {
                // operation and index column no sort
                return false;
            } else {
                return true;
            }
        } else {
            return column.sort !== undefined && column.sort != null;
        }
    },
    /**
     * is row removable
     * @returns {boolean}
     */
    isRowRemovable: function () {
        return this.props.layout.removable === true ? true : false;
    },
    /**
     * is row editable
     * @returns {boolean}
     */
    isRowEditable: function () {
        return this.props.layout.editable === true ? true : false;
    },
    /**
     * is addable
     * @returns {boolean}
     */
    isAddable: function () {
        return this.props.layout.addable;
    },
    /**
     * is indexable
     * @returns {boolean}
     */
    isIndexable: function () {
        return this.props.layout.indexable === true ? true : false;
    },
    /**
     * check has data to display or not
     * @returns {boolean}
     */
    hasDataToDisplay: function () {
        var data = this.getDataToDisplay();
        return data != null && data.length > 0;
    },
    /**
     * filter data to display
     * @param row
     * @param rowIndex
     * @param all
     * @returns {boolean} true means data of row can match the search text
     */
    filterData: function (row, rowIndex, all) {
        var text = this.state.searchText.toUpperCase();
        var columns = this.props.columns.columns();
        for (var index = 0, count = columns.length; index < count; index++) {
            var data = row[columns[index].data];
            if (data == null || data == "") {
                continue;
            }
            if (columns[index].render) {
                data = columns[index].render(data, row);
            }
            if (data == null || data == "") {
                continue;
            }
            if (data.toString().toUpperCase().indexOf(text) != -1) {
                return true;
            }
        }
        return false;
    },
    /**
     *
     * @returns {*}
     */
    getDataToDisplay: function () {
        var data = this.getValueFromModel();
        return this.isSearching() ? data.filter(this.filterData) : data;
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
     * get value of model
     * @returns {[*]}
     */
    getValueFromModel: function () {
        return this.getModel().get(this.getId());
    },
    /**
     * get error
     * @returns {[*|String]}
     */
    getError: function () {
        if (this.props.error === undefined || this.props.error == null) {
            return null;
        }
        return this.props.error[this.getId()];
    },
    /**
     * detail error is last record of array, it's an array. rowindex is start from 1
     * @param rowIndex
     * @returns {*}
     */
    getDetailError: function (rowIndex) {
        var error = this.getError();
        return error[error.length - 1].detail[rowIndex - 1];
    },
    /**
     * add item into model
     * @param item
     */
    addItemIntoModel: function (item) {
        this.getModel().add(this.getId(), item);
    },
    /**
     * update item into model
     * @param item
     * @param index
     */
    updateItemIntoModel: function (item, index) {
        this.getModel().update(this.getId(), item, index);
    }
});
