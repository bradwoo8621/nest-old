/**
 * Component Base
 * @type {*}
 */
var ComponentBase = {
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
     * @param originalCSS original CSS
     * @returns {string}
     */
    getComponentCSS: function (originalCSS) {
        return this.getCombineCSS(originalCSS, "comp");
    },
    /**
     * get combine css
     * @param originalCSS css class names
     * @param additionalKey key of additional css in layout
     * @returns {string}
     */
    getCombineCSS: function (originalCSS, additionalKey) {
        return this.getLayout().getAdditionalCSS(additionalKey, originalCSS);
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
     * get component rule value.
     * get component option by given key. return default value if not defined.
     * otherwise call when function and return.
     * @param key
     * @param defaultValue
     * @returns {*}
     */
    getComponentRuleValue: function (key, defaultValue) {
        var rule = this.getComponentOption(key);
        if (rule == null) {
            return defaultValue;
        } else {
            var when = rule.when;
            return when.call(this, this.getModel(), this.getValueFromModel());
        }
    },
    /**
     * is enabled
     * @returns {boolean}
     */
    isEnabled: function () {
        return this.getComponentRuleValue("enabled", true);
    },
    /**
     * is visible
     * @returns {boolean}
     */
    isVisible: function () {
        return this.getComponentRuleValue("visible", true)
    },
    /**
     * is read only
     * @returns {boolean}
     */
    isReadonly: function () {
        return this.getComponentRuleValue("readonly", false);
    },

    // event
    addPostChangeListener: function (listener) {
        this.getModel().addListener(this.getId(), "post", "change", listener);
    },
    removePostChangeListener: function (listener) {
        this.getModel().removeListener(this.getId(), "post", "change", listener);
    },
    addPostAddListener: function (listener) {
        this.getModel().addListener(this.getId(), "post", "add", listener);
    },
    removePostAddListener: function (listener) {
        this.getModel().removeListener(this.getId(), "post", "add", listener);
    },
    addPostRemoveListener: function (listener) {
        this.getModel().addListener(this.getId(), "post", "remove", listener);
    },
    removePostRemoveListener: function (listener) {
        this.getModel().removeListener(this.getId(), "post", "remove", listener);
    },
    addPostValidateListener: function (listener) {
        this.getModel().addListener(this.getId(), "post", "validate", listener);
    },
    removePostValidateListener: function (listener) {
        this.getModel().removeListener(this.getId(), "post", "validate", listener);
    }
};
/**
 * component define
 * @param define
 * @constructor
 */
var ComponentDefine = function (define) {
    return $.extend({}, ComponentBase, define);
};