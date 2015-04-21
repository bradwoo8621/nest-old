// form section
var Form = React.createClass({
	propTypes: {
		model: React.PropTypes.object.isRequired,
		layout: React.PropTypes.object.isRequired,
		
		className: React.PropTypes.string,
	},
	renderRows: function (row) {
		var _this = this;
		var cells = row.cells().map(function(cell) {
			return (<FormCell options={cell} model={_this.getModel()} ref={cell.id} />);
		});
		return (<div className="row">{cells}</div>);
	},
	render: function() {
		return (<div className={this.props.className}>
			{this.getLayout().rows().map(this.renderRows)}
		</div>);
	},
	getLayout: function() {
		return this.props.layout;
	},
	getModel: function() {
		return this.props.model;
	}
});
// form cell
var FormCell = React.createClass({
	propTypes: {
		// options
		options: React.PropTypes.shape({
			id: React.PropTypes.string, // only not required when type is empty
			label: React.PropTypes.string,
			type: React.PropTypes.oneOf(["text", "date", "select", "check", "search", "table", "empty"]).isRequired,
			
			// for type is "select"
			selectOptions: React.PropTypes.object,
			needSearchBox: React.PropTypes.bool,
			parent: React.PropTypes.object,
			
			// for type is "search"
			placeholder: React.PropTypes.string,
			
			// for type is "table"
			columns: React.PropTypes.array(React.PropTypes.shape({
				title: React.PropTypes.string,
				data: React.PropTypes.string, // path of value
				render: React.PropTypes.func, 
				width: React.PropTypes.number,
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
		model: React.PropTypes.object.isRequired
	},
	getDefaultProps: function() {
		return {
			// layout, 4 columns
			cellWidth: 3,
			// css
			labelClassName: "control-label form-vertical-label"
		};
	},
	// handle text change event, synchronize value to model
	_handleTextChanged: function(e, value) {
		this.props.model[e.target.id] = value;
	},
	// render input component
	renderInputComponent: function() {
		if (this.props.options.type == "text") {
			return this.createText();
		} else if (this.props.options.type == "date") {
			return this.createDateText();
		} else if (this.props.options.type == "select") {
			return this.createSelect();
		} else if (this.props.options.type == "check") {
			return this.createCheck();
		} else if (this.props.options.type == "search") {
			return this.createSearch();
		} else {
			return null;
		}
	},
	// create normal text
	createText: function() {
		return <Text id={this.props.options.id} model={this.props.model} />;
	},
	// create date text
	createDateText: function() {
		return (<DateTimePicker id={this.props.options.id} model={this.props.model} />);
	},
	// create checkbox
	createCheck: function() {
		return <CheckBox id={this.props.options.id} model={this.props.model} />;
	},
	// create select
	createSelect: function() {
		if (this.props.options.needSearchBox) {
			return (<Select2 id={this.props.options.id} options={this.props.options.selectOptions} minimumResultsForSearch={1} 
						model={this.props.model} parent={this.props.options.parent} />);
		} else {
			return (<Select2 id={this.props.options.id} options={this.props.options.selectOptions} 
						model={this.props.model} parent={this.props.options.parent} />)
		}
	},
	// create search text
	createSearch: function() {
		return (
			<SearchText id={this.props.options.id} model={this.props.model} 
				placeholder={this.props.options.placeholder} 
				search={this._handleTextChanged} advancedSearch={this._handleTextChanged} />
		);			
	},
	// create table
	createTable: function() {
		// test data
		return (
			<DataTable id={this.props.options.id} title={this.props.options.label}
					columns={this.props.options.columns} layout={this.props.options.layout} 
					model={this.props.model} editLayout={this.props.options.editLayout()}/>
		);
	},
	// get cell width class name
	getCellWidthClassName: function() {
		var cellWidth = this.props.options.cellWidth ? this.props.options.cellWidth : this.props.cellWidth;
		// using layout
		if (this.props.options.cell && this.props.options.cell.width) {
			cellWidth = this.props.options.cell.width;
		}
		
		return "col-md-" + cellWidth + " form-group";
	},
	render: function() {
		var className = this.getCellWidthClassName();
		if (this.props.options.type == "empty") {
			return (<div className={className} />);
		} else if (this.props.options.type == "table") {
			return (
				<div className={className}>
					{this.createTable()}
				</div>
			);
		} else {
			return (
				<div className={className}>
					<label htmlFor={this.props.options.id} className={this.props.labelClassName}>{this.props.options.label}:</label>
					{this.renderInputComponent()}
				</div>
			);
		}
	}
});
