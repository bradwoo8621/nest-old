/**
 * Select2 React
 */
var Select2 = React.createClass({
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

        // allow clear button enabled
        allowClear: React.PropTypes.bool,
        // options of select, Codes
        options: React.PropTypes.object,
        disabled: React.PropTypes.bool,
        // never used now
        maximumInputLength: React.PropTypes.number,
        // never used now
        minimumInputLength: React.PropTypes.number,
        // minimum results for search, integer value. all set as "Infinity" to
        // close the search box
        minimumResultsForSearch: React.PropTypes.oneOfType([
            React.PropTypes.number,
            React.PropTypes.string
        ]),
        // multiple selection enabled or not
        multiple: React.PropTypes.bool,
        // placeholder string
        placeholder: React.PropTypes.string,
        /**
         * @see Select2 document
         */
        tags: React.PropTypes.bool,
        width: React.PropTypes.string,

        parent: React.PropTypes.shape({
            // if not set or null, use current model
            model: React.PropTypes.object,
            // parent property which will be monitor
            prop: React.PropTypes.string,
            // filter prop of myself or a function
            filter: React.PropTypes.oneOfType([React.PropTypes.string, React.PropTypes.func]),
            // available when no parent value selected
            availableWhenNoParentValue: React.PropTypes.bool
        }),

        // onChange event handler
        onChange: React.PropTypes.func
    },
    getDefaultProps: function () {
        return {
            allowClear: true,
            disabled: false,
            maximumInputLength: 0,
            minimumInputLength: 1,
            minimumResultsForSearch: -1,
            multiple: false,
            placeholder: "Please Select...",
            tags: false,
            width: "100%"
        }
    },
    getInitialState: function () {
        return {};
    },
    /**
     * override react method
     * @param prevProps {*}
     * @param prevState {*}
     * @override
     */
    componentDidUpdate: function (prevProps, prevState) {
        var comp = this.getComponent();
        var oldValue = comp.val();
        var newValue = this.getValueFromModel();
        // forbid the change if value is same
        if (oldValue == newValue) {
            return;
        }
        // reset the value when component update
        comp.val(newValue).trigger("change");
        this.removeTooltip();
    },
    /**
     * override react method
     * @override
     */
    componentDidMount: function () {
        // Set up Select2
        this.createComponent();
        // add post change listener
        this.getModel().addListener(this.getId(), "post", this.handleModelChange);
        if (this.hasParent()) {
            // add post change listener into parent model
            this.getParentModel().addListener(this.props.parent.prop, "post", this.handleParentChange);
        }
        this.removeTooltip();
    },
    /**
     * override react method
     * @override
     */
    componentWillUnmount: function () {
        // remove post change listener
        this.getModel().removeListener(this.getId(), "post", this.handleModelChange);
        if (this.hasParent()) {
            // remove post change listener from parent model
            this.getParentModel().removeListener(this.props.parent.prop, "post", this.handleParentChange);
        }
    },
    /**
     * remove tooltip, which is default set by select2 component.
     * it's unnecessary.
     */
    removeTooltip: function () {
        $("#select2-" + this.getId() + "-container").removeAttr("title");
    },
    /**
     * create component
     */
    createComponent: function () {
        var options = {
            multiple: this.props.multiple,
            allowClear: this.props.allowClear,
            placeholder: this.props.placeholder,
            minimumResultsForSearch: this.props.minimumResultsForSearch,
            data: this.availableWhenNoParentValue() ? this.props.options.list() : this.getAvailableOptions(this.getParentValue())
        };
        this.getComponent().fireOnDisable().select2(options)
            .prop("disabled", this.props.disabled)
            .val(this.getValueFromModel()).trigger("change")
            .change(this.handleComponentChange);
    },
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
     * override react method
     * @returns {XML}
     * @override
     */
    render: function () {
        var style = {width: this.props.width};
        // since the select2 only can be render one time. so must be keep the dom stable.
        // set the trigger as manually, and add mouse listener for show/hide the overlay.
        return (<OverlayTrigger placement="top" overlay={this.renderErrorPopover()} trigger="manual" ref="overlay">
            <div id={this.getId() + "_select2"} onMouseMove={this.mouseMove} onMouseOut={this.mouseOut}>
                <select style={style} id={this.getId()}/>
            </div>
        </OverlayTrigger>);
    },
    /**
     * mouse move
     */
    mouseMove: function () {
        if (this.hasError()) {
            // show overlay when has error
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
        var value = this.getComponent().val();
        // synchronize value to model
        this.setValueToModel(value);
        if (this.props.onChange) {
            this.props.onChange(e, value);
        }
        this.removeTooltip();
    },
    /**
     * get available options according to give parent value
     * @param parentValue
     * @returns {[*]}
     */
    getAvailableOptions: function (parentValue) {
        if (parentValue == null) {
            return this.availableWhenNoParentValue() ? this.props.options.list() : [];
        } else {
            if (typeof(this.props.parent.filter) === "function") {
                return this.props.parent.filter(parentValue, this.props.options);
            } else {
                var filterProp = this.props.parent.filter;
                return this.props.options.filter(function (element) {
                    return element[filterProp] == parentValue;
                });
            }
        }
    },
    /**
     * handle parent change
     * @param evt
     */
    handleParentChange: function (evt) {
        var newValue = evt.newValue;
        this.setSelectOptions(this.getAvailableOptions(newValue));
    },
    /**
     * handle model change
     * @param evt
     */
    handleModelChange: function (evt) {
        this.getComponent().val(evt.newValue).trigger("change");
    },
    /**
     * available when no parent value.
     * default is false, which means no parent value selected, component is not available(no options to select).
     * if no parent assigned, return true
     * @returns {boolean}
     */
    availableWhenNoParentValue: function () {
        return (this.props.parent && this.props.parent.availableWhenNoParentValue === true) || this.props.parent === undefined;
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
     * has parent or not
     * @returns {boolean}
     */
    hasParent: function () {
        return this.props.parent !== undefined;
    },
    /**
     * get parent model
     * @returns {ModelProxy}
     */
    getParentModel: function () {
        if (this.props.parent && this.props.parent.model) {
            return this.props.parent.model;
        } else {
            return this.getModel();
        }
    },
    /**
     * get parent value
     * @returns {ModelProxy}
     */
    getParentValue: function () {
        return this.props.parent ? this.getParentModel().get(this.props.parent.prop) : null;
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
    },
    /**
     * set select options, use to reset the options. options is a json array
     * which has id and text of each element.
     * @param selectOptions
     */
    setSelectOptions: function (selectOptions) {
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
        options.data.forEach(function (element) {
            if (element.id == orgValue) {
                innerHTML += "<option value=\"" + element.id + "\"" + (element.id == orgValue ? " selected" : "") + ">" + element.text + "</option>";
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
    }
});

// to fix the select2 disabled property not work in IE8-10
// provided by https://gist.github.com/cmcnulty/7036509
(function ($) {
    "use strict";

    $.fn.fireOnDisable = function (settings) {
        // Only perform this DOM change if we have to watch changes with
        // propertychange
        // Also only perform if getOwnPropertyDescriptor exists - IE>=8
        // I suppose I could test for "propertychange fires, but not when form
        // element is disabled" - but it would be overkill
        if (!( 'onpropertychange' in document.createElement('input') ) || Object.getOwnPropertyDescriptor === undefined) {
            return this;
        }

        // IE9-10 use HTMLElement proto, IE8 uses Element proto
        var someProto = window.HTMLElement === undefined ? window.Element.prototype : window.HTMLElement.prototype,
            someTrigger = function () {
            },
            origDisabled = Object.getOwnPropertyDescriptor(someProto, 'disabled');

        if (document.createEvent) {
            someTrigger = function (newVal) {
                var event = document.createEvent('MutationEvent');
                /*
                 * Instantiate the event as close to native as possible:
                 * event.initMutationEvent(eventType, canBubble, cancelable,
                 * relatedNodeArg, prevValueArg, newValueArg, attrNameArg,
                 * attrChangeArg);
                 */
                event.initMutationEvent('DOMAttrModified', true, false, this.getAttributeNode('disabled'), '', '', 'disabled', 1);
                this.dispatchEvent(event);
            };
        } else if (document.fireEvent) {
            someTrigger = function () {
                this.fireEvent('onpropertychange');
            };
        }

        return this.each(function () {
            // call prototype's set, and then trigger the change.
            Object.defineProperty(this, 'disabled', {
                set: function (isDisabled) {
                    // We store preDisabled here, so that when we inquire as to
                    // the result after throwing the event, it will be accurate
                    // We can't throw the event after the native send, because
                    // it won't be be sent.
                    // We must do a native fire/dispatch, because native
                    // listeners don't catch jquery trigger 'propertychange'
                    // events
                    $.data(this, 'preDisabled', isDisabled);
                    if (isDisabled) {
                        // Trigger with dispatchEvent
                        someTrigger.call(this, isDisabled);
                    }

                    return origDisabled.set.call(this, isDisabled);
                },
                get: function () {
                    var isDisabled = $.data(this, 'preDisabled');
                    if (isDisabled === undefined) {
                        isDisabled = origDisabled.get.call(this);
                    }
                    return isDisabled;
                }
            });
        });
    };
})(jQuery);
