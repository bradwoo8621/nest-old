/**
 * Select2 React
 */
var Select2 = React.createClass({
	displayName: "Select2",
	propTypes: {
		id: React.PropTypes.string.isRequired, // id used in UI
		name: React.PropTypes.string, // property name in model, use id instead if not exists
		model: React.PropTypes.object.isRequired, 
		
		// property
		allowClear: React.PropTypes.bool,
		options: React.PropTypes.object, // options of select, Codes
		disabled: React.PropTypes.bool,
		errorClass: React.PropTypes.string,
		hasError: React.PropTypes.bool,
	    maximumInputLength: React.PropTypes.number,
	    minimumInputLength: React.PropTypes.number,
		// minimum results for search, integer value. all set as "Infinity" to
		// close the search box
	    minimumResultsForSearch:  React.PropTypes.oneOfType([
	        React.PropTypes.number,
	        React.PropTypes.string
	    ]),
		multiple: React.PropTypes.bool,
		placeholder: React.PropTypes.string,
		tags: React.PropTypes.bool,
		width: React.PropTypes.string,
		
		parent: React.PropTypes.shape({
			model: React.PropTypes.object, // if not set or null, use current model
			prop: React.PropTypes.string, // parent property which will be monitor
			filter: React.PropTypes.oneOfType([React.PropTypes.string, React.PropTypes.func]), // filter prop of myself or a function
			availableWhenNoParentValue: React.PropTypes.bool, // disabled when no parent value selected
		}),
		
		// event
		onChange: React.PropTypes.func
	},
	getDefaultProps: function() {
		return {
			allowClear: true,
			disabled: false,
			errorClass: "has-error", // default to bootstrap error class
			hasError: true,
			maximumInputLength: 0,
			minimumInputLength: 1,
			minimumResultsForSearch: -1,
			multiple: false,
			placeholder: "Please Select...",
			tags: false,
			width: "100%",
		}
	},
	getInitialState: function() {
		return {};
	},	
	/**
	 * life-cycle
	 */
	componentDidUpdate: function(prevProps, prevState) {
		var comp = this.getComponent();
		var oldValue = comp.val();
		var newValue = this.getValueFromModel();
		// forbid the change if value is same
		if (oldValue == newValue) {
			return;
		}
		// reset the value when component update
		comp.val(newValue).trigger("change");
	},
	componentDidMount: function () {
		// Set up Select2
		this.createComponent();
		this.getModel().addListener(this.getPropertyName(), "post", this.handleModelChange);
		var parentModel = this.props.parent ? (this.props.parent.model ? this.props.parent.model : this.getModel()) : null;
		if (parentModel != null) {
			parentModel.addListener(this.props.parent.prop, "post", this.handleParentChange);
		}
	},
	componentWillUnmount: function() {
		this.getModel().removeListener(this.getPropertyName(), "post", this.handleModelChange);
		var parentModel = this.props.parent ? (this.props.parent.model ? this.props.parent.model : this.getModel()) : null;
		if (parentModel != null) {
			parentModel.removeListener(this.props.parent.prop, "post", this.handleParentChange);
		}
	},
	// jquery component creating
	createComponent: function() {
		var options = {
			multiple: this.props.multiple,
			allowClear: this.props.allowClear,
			placeholder: this.props.placeholder,
			minimumResultsForSearch: this.props.minimumResultsForSearch,
			data: this.availableWhenNoParentValue() ? this.props.options.list() : this.getAvailableOptions(this.getParentValue())
		}
		this.getComponent().fireOnDisable().select2(options)
			.prop("disabled", this.props.disabled)
			.val(this.getValueFromModel()).trigger("change")
			.change(this.handleComponentChange);
	},
	render : function() {
		var style = {width: this.props.width};
		return (<div><select style={style} id={this.props.id} /></div>);
	},
	// handle component change
	handleComponentChange: function(e) {
		var value = this.getComponent().val();
		// synchronize value to model
		this.setValueToModel(value);
		if (this.props.onChange) {
			this.props.onChange(e, value);
		}
	},
	// get available options according to give parent value
	getAvailableOptions: function(parentValue) {
		if (parentValue == null) {
			return this.availableWhenNoParentValue() ? this.props.options.list() : [];
		} else {
			if (typeof(this.props.parent.filter) === "function") {
				return this.props.parent.filter(parentValue, this.props.options);
			} else {
				var filterProp = this.props.parent.filter;
				return this.props.options.filter(function(element) {
					return element[filterProp] == parentValue;
				});
			}
		}
	},
	// handle parent value change
	handleParentChange: function(evt) {
		var newValue = evt.newValue;
		this.setSelectOptions(this.getAvailableOptions(newValue));
	},
	// handle model change
	handleModelChange: function(evt) {
		this.getComponent().val(evt.newValue).trigger("change");
	},
	// available when no parent value. 
	// default is false, which means no parent value selected, this is not available(no options to select).
	// if no parent assigned, return true
	availableWhenNoParentValue: function () {
		if (this.props.parent) {
			if (this.props.parent.availableWhenNoParentValue) {
				return true;
			} else {
				return false;
			}
		} else {
			// no parent assigned
			return true;
		}
	},
	// get component
	getComponent: function() {
		return $("#" + this.props.id);
	},
	// get model
	getModel: function() {
		return this.props.model;
	},
	// get property name
	getPropertyName: function() {
		return this.props.name ? this.props.name : this.props.id;
	},
	getParentModel: function() {
		if (this.props.parent && this.props.parent.model) {
			return this.props.parent.model;
		} else {
			return this.getModel();
		}
	},
	getParentValue: function() {
		if (this.props.parent) {
			return this.getParentModel().get(this.props.parent.prop);
		} else {
			// no parent assigned
			return null;
		}
	},
	// get value from model
	getValueFromModel: function() {
		return this.getModel().get(this.getPropertyName());
	},
	// set value to model
	setValueToModel: function(value) {
		this.getModel().set(this.getPropertyName(), value);
	},
	// set select options, use to reset the options. options is a json array
	// which has id and text of each element.
	setSelectOptions: function(selectOptions) {
		// really sucks because the select2 doesn't support change the options
		// dynamically
		// https://github.com/silviomoreto/bootstrap-select maybe a replacement
		var innerHTML = "";
		var component = this.getComponent();
		var orgValue = component.val();
		var orgSelected = false;
		// first is Options object, second is really options
		var options = component.data("select2").options.options;
		component.html("");
		// data
		options.data = selectOptions;
		options.data.forEach(function(element) {
			if (element.id == orgValue) {
				innerHTML += "<option value=\"" + element.id + "\"" + (element.id == orgValue ? " selected":"") + ">" + element.text + "</option>";
				orgSelected = true;
			} else {
				innerHTML += "<option value=\"" + element.id + "\">" + element.text + "</option>";
			}
		});
		component.append(innerHTML);
		component.select2(options);
		
		if (!orgSelected) {
			component.val("").trigger("change");
		}
	},
});

// to fix the select2 disabled property not work in IE8-10
// provided by https://gist.github.com/cmcnulty/7036509
( function( $ ) {
	"use strict";

	$.fn.fireOnDisable = function( settings ) {
		// Only perform this DOM change if we have to watch changes with
		// propertychange
		// Also only perform if getOwnPropertyDescriptor exists - IE>=8
		// I suppose I could test for "propertychange fires, but not when form
		// element is disabled" - but it would be overkill
		if( !( 'onpropertychange' in document.createElement( 'input' ) ) || Object.getOwnPropertyDescriptor === undefined ) {
			return this;
		}

		// IE9-10 use HTMLElement proto, IE8 uses Element proto
		var someProto = window.HTMLElement === undefined ? window.Element.prototype : window.HTMLElement.prototype,
			someTrigger = function(){},
			origDisabled = Object.getOwnPropertyDescriptor( someProto, 'disabled' );

		if( document.createEvent ) {
			someTrigger = function( newVal ){
				var event = document.createEvent('MutationEvent');
				/*
				 * Instantiate the event as close to native as possible:
				 * event.initMutationEvent(eventType, canBubble, cancelable,
				 * relatedNodeArg, prevValueArg, newValueArg, attrNameArg,
				 * attrChangeArg);
				 */
				event.initMutationEvent( 'DOMAttrModified', true, false, this.getAttributeNode('disabled'), '', '', 'disabled', 1 );
				this.dispatchEvent( event );
			};
		} else if( document.fireEvent ) {
			someTrigger = function(){
				this.fireEvent( 'onpropertychange' );
			};
		}

		return this.each( function() {
			// call prototype's set, and then trigger the change.
			Object.defineProperty( this, 'disabled', {
				set: function( isDisabled ) {
					// We store preDisabled here, so that when we inquire as to
					// the result after throwing the event, it will be accurate
					// We can't throw the event after the native send, because
					// it won't be be sent.
					// We must do a native fire/dispatch, because native
					// listeners don't catch jquery trigger 'propertychange'
					// events
					$.data( this, 'preDisabled', isDisabled );
					if ( isDisabled ) {
						// Trigger with dispatchEvent
						someTrigger.call( this, isDisabled );
					}

					return origDisabled.set.call( this, isDisabled );
				},
				get: function() {
					var isDisabled = $.data( this, 'preDisabled' );
					if( isDisabled === undefined ) {
						isDisabled = origDisabled.get.call( this );
					}
					return isDisabled;
				}
			});
		});
	};
})( jQuery );
