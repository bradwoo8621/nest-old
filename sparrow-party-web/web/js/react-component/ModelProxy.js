/**
 * find element from array, return null if not found.
 */
if (Array.prototype.find === undefined) {
    Array.prototype.find = function (func) {
        for (var index = 0, count = this.length; index < count; index++) {
            if (func.call(this, this[index], index, this)) {
                return this[index];
            }
        }
        return null;
    };
}
/**
 * find element index from array, return -1 if not found.
 */
if (Array.prototype.findIndex === undefined) {
    Array.prototype.findIndex = function (func) {
        for (var index = 0, count = this.length; index < count; index++) {
            if (func.call(this, this[index], index, this)) {
                return index;
            }
        }
        return -1;
    };
}
if (String.prototype.upperFirst === undefined) {
    String.prototype.upperFirst = function () {
        if (this.length == 1) {
            return this.toUpperCase();
        } else {
            return this.substring(0, 1).toUpperCase() + this.substring(1)
        }
    };
}
/**
 * replace place holders %1, %2, etc with given string array
 *
 * @param strArray
 * @returns
 */
if (String.prototype.replaceMessage === undefined) {
    String.prototype.replaceMessage = function (strArray) {
        return this.replace(/%(\d+)/g, function (_, m) {
            return strArray[--m];
        });
    };
}

/**
 * Model Proxy class.
 */
var ModelProxyClass = function (methods) {
    var ModelProxy = function () {
        this.initialize.apply(this, arguments);
    };
    for (var property in methods) {
        ModelProxy.prototype[property] = methods[property];
    }
    if (!ModelProxy.prototype.initialize) {
        ModelProxy.prototype.initialize = function () {
        };
    }
    return ModelProxy;
};
/**
 * Codes
 */
var Codes = ModelProxyClass({
    /**
     * initialize code object
     * @param array {[{id, text}]}
     *  id is required.
     *  text is required when parameter render is undefined, otherwise text is unnecessary.
     *  any other property is free and optional.
     * @param render {func}
     *  optional. a function to process the code to text.
     */
    initialize: function (array, render) {
        this.codes = array;
        var map = {};
        this.codes.forEach(function (code, index, array) {
            map[code.id] = code;
            if (render) {
                code.text = render(code);
            }
        });
        this.map = map;
    },
    /**
     * get array of codes
     * @returns {[*]}
     */
    list: function () {
        return this.codes;
    },
    /**
     * get code by given id
     * @param id
     * @returns {*}
     */
    get: function (id) {
        return this.map[id];
    },
    /**
     * filter by given function, return array of codes. signature of filter function refers to array.filter.
     * @param func
     * @returns {[*]}
     */
    filter: function (func) {
        return this.codes.filter(func);
    },
    /**
     * check if there is at least one code match the given function.
     * signature of some function refers to array.some.
     * @param func
     * @returns {boolean}
     */
    some: function (func) {
        return this.codes.some(func);
    }
});
/**
 * Model Proxy.
 */
var ModelProxy = ModelProxyClass({
    /**
     * initialize, modelLayout should have the same keys with model.
     * @param model {*}
     * @param modelLayout {ModelLayoutProxy}
     */
    initialize: function (model, modelLayout) {
        // init listeners
        this.__propertyListeners = {};
        // init model
        this.__modelLayout = modelLayout;
        this.__initModel = model;
        this.__model = ModelUtil.clone(model);
        var _this = this;
        Object.keys(model).forEach(function (key) {
            var value = model[key];
            // values, create getter/setter
            var upper = key.upperFirst();
            _this["get" + upper] = function () {
                return this.__model[key];
            };
            _this["set" + upper] = function (newValue) {
                var oldValue = this.__model[key];
                console.debug(upper + "'s value changes from [" + oldValue + "] to [" + newValue + "].");
                if (newValue == oldValue) {
                    // equals, skip the invoke
                    console.debug("Set operation is skipped since new value equals old value.");
                    return;
                }

                // invoke pre change event
                this.__fireEvent({
                    key: key,
                    oldValue: oldValue,
                    newValue: newValue,
                    time: "pre",
                    type: "change"
                });
                this.__model[key] = newValue;
                // invoke post change event
                this.__fireEvent({
                    key: key,
                    oldValue: oldValue,
                    newValue: newValue,
                    time: "post",
                    type: "change"
                });
            }
        });
    },
    __fireEvent: function (evt) {
        var key = evt.key + "-" + evt.time + "-" + evt.type;
        if (this.__propertyListeners[key]) {
            var _this = this;
            this.__propertyListeners[key].forEach(function (element) {
                element.call(_this, evt);
            });
        }
    },
    __convertListenerType: function (type) {
        if (type == "pre") {
            type = "pre-change";
        } else if (type == "post") {
            type = "post-change";
        }
        return type;
    },
    /**
     * add listener. type should be "pre" or "post"
     */
    addListener: function (propName, types, listener) {
        if (!Array.isArray(types)) {
            types = [types];
        }
        var _this = this;
        types.forEach(function (type) {
            var key = propName + "-" + _this.__convertListenerType(type);
            if (_this.__propertyListeners[key] === undefined) {
                _this.__propertyListeners[key] = new Array();
                _this.__propertyListeners[key].push(listener);
            } else {
                var index = _this.__propertyListeners[key].findIndex(function (element) {
                    return element === listener;
                });
                if (index == -1) {
                    // not found, add into array
                    _this.__propertyListeners[key].push(listener);
                }
            }
        });
    },
    /**
     * remove listener. type should be "pre" or "post"
     */
    removeListener: function (propName, types, listener) {
        if (!Array.isArray(types)) {
            types = [types];
        }
        var _this = this;
        types.forEach(function (type) {
            var key = propName + "-" + _this.__convertListenerType(type);
            if (_this.__propertyListeners[key] === undefined) {
                return;
            } else {
                var index = _this.__propertyListeners[key].findIndex(function (element) {
                    return element === listener;
                });
                if (index != -1) {
                    _this.__propertyListeners[key].splice(index, 1);
                }
            }
        });
    },
    // get
    get: function (propName) {
        var methodName = "get" + propName.upperFirst();
        return this[methodName].call(this);
    },
    set: function (propName, value) {
        var methodName = "set" + propName.upperFirst();
        this[methodName].call(this, value);
    },
    // add item into given property array value
    // if index is not appointed, will add item at the end of array.
    add: function (propName, newItem, index) {
        var array = this.get(propName);
        if (array == null) {
            this.set(propName, []);
        } else {
            if (!Array.isArray(array)) {
                throw new TypeError("Property \"" + propName + "\" must be an instance of array.");
            }
        }
        if (index) {
            if (index < 0) {
                index = 0;
            } else if (index > array.length) {
                index = array.length;
            }
            this.__fireEvent({
                key: propName,
                array: array,
                item: newItem,
                index: index,
                type: "add",
                time: "pre"
            });
            array.splice(index, 0, newItem);
            this.__fireEvent({
                key: propName,
                array: array,
                item: newItem,
                index: index,
                type: "add",
                time: "post"
            });
        } else {
            this.__fireEvent({
                key: propName,
                array: array,
                oldValue: null,
                newValue: newItem,
                index: array.length,
                type: "add",
                time: "pre"
            });
            array.push(newItem);
            this.__fireEvent({
                key: propName,
                array: array,
                oldValue: null,
                newValue: newItem,
                index: array.length - 1,
                type: "add",
                time: "post"
            });
        }
    },
    // remove item from give property value array
    remove: function (propName, itemOrIndex) {
        var array = this.get(propName);
        if (array == null) {
            // do nothing
            return;
        } else {
            var index = -1;
            var item = null;
            if (typeof itemOrIndex === "number") {
                index = itemOrIndex;
                if (index < 0 || index > array.length - 1) {
                    throw {
                        name: "IndexOutOfBoundsError",
                        message: "Invalid index [" + index + "] of size [" + array.length
                        + "] for do removing from property [" + propName + "]."
                    };
                } else {
                    item = array[index];
                }
            } else {
                item = itemOrIndex;
                index = array.findIndex(function (element) {
                    return element == item;
                });
            }
            if (index != -1) {
                this.__fireEvent({
                    key: propName,
                    array: array,
                    oldValue: item,
                    newValue: null,
                    index: index,
                    type: "remove",
                    time: "pre"
                });
                array.splice(index, 1);
                this.__fireEvent({
                    key: propName,
                    array: array,
                    oldValue: item,
                    newValue: null,
                    index: index,
                    type: "remove",
                    time: "post"
                });
            }
        }
    },
    update: function (propName, newItem, index) {
        var array = this.get(propName);
        if (array == null) {
            throw {
                name: "IndexOutOfBoundsError",
                message: "Invalid index [" + index + "] of uninitialized array for property [" + propName + "]."
            };
        } else {
            if (index < 0 || index > array.length - 1) {
                throw {
                    name: "IndexOutOfBoundsError",
                    message: "Invalid index [" + index + "] of size [" + array.length + "] for do updating property ["
                    + propName + "]."
                };
            } else {
                var oldItem = array[index];
                this.__fireEvent({
                    key: propName,
                    array: array,
                    oldValue: oldItem,
                    newValue: newItem,
                    index: index,
                    type: "change",
                    time: "pre"
                });
                array.splice(index, 1, newItem);
                this.__fireEvent({
                    key: propName,
                    array: array,
                    oldValue: oldItem,
                    newValue: newItem,
                    index: index,
                    type: "change",
                    time: "post"
                });
            }
        }
    },
    // reset data model, clone from init model
    reset: function () {
        this.__model = ModelUtil.clone(this.__initModel);
    },
    // validate data model, use model layout
    validate: function () {
        var layout = this.getDataModelLayout();
        if (layout === undefined || layout == null) {
            // no constraints
            return true;
        }
        var model = this.getDataModel();
        var result = {};
        for (var key in model) {
            var options = layout.get(key);
            var validator = new ValidationProxy(options);
            var value = this.get(key);
            validator.validate(value, model);
            result[key] = validator.getMessages();

            // special check for list
            if (options.type === "list") {
                if (value != null && value.length != 0) {
                    var validateResult = value.map(function (element, index, array) {
                        var proxy = new ModelProxy(element, new ModelLayoutProxy(options.layout));
                        return proxy.validate();
                    });
                    result[key].push({
                        detail: validateResult
                    });
                }
            }
        }
        return result;
    },
    // get data model, json object
    getDataModel: function () {
        return this.__model;
    },
    getDataModelLayout: function () {
        return this.__modelLayout;
    }
});

/**
 * Model Utilities
 */
var ModelUtil = {
    clone: function (json) {
        return $.extend(true, {}, json);
    },
    // create a model, layout is an instance of Layout
    create: function (layout) {
        return new ModelProxy(layout.create(), layout);
    }
};

/**
 * Layout Proxy class.
 */
var LayoutProxyClass = function (methods) {
    var LayoutProxy = function () {
        this.initialize.apply(this, arguments);
    };
    for (var property in methods) {
        LayoutProxy.prototype[property] = methods[property];
    }
    if (!LayoutProxy.prototype.initialize) {
        LayoutProxy.prototype.initialize = function () {
        };
    }
    return LayoutProxy;
};
/**
 * Row Layout
 */
var FormRowLayout = LayoutProxyClass({
    initialize: function () {
        this.cellData = [];
    },
    put: function (cell) {
        this.row = cell.cell.row;
        this.cellData.push(cell);
        this.cellData.sort(function (a, b) {
            return a.cell.col - b.cell.col;
        });
    },
    cells: function () {
        return this.cellData;
    },
    rowIndex: function () {
        return this.row;
    }
});
/**
 * Form Layout
 */
var Layout = LayoutProxyClass({
    /**
     * initialize, arguments should be one or more JSON object.
     * each property of argument must have a property "cell".
     * eg. {id: {cell: {row: 1}}}
     * then properties with same row should be collected into a FormRowLayout,
     * and the property key should be added into its value object as key "id"
     */
    initialize: function () {
        this.allData = {};
        var rows = {};
        if (arguments) {
            var argus = [];
            for (var index = 0, count = arguments.length; index < count; index++) {
                argus.push(arguments[index]);
            }
            var _this = this;
            argus.forEach(function (element) {
                Object.keys(element).forEach(function (key) {
                    _this.allData[key] = element[key];
                    var rowIndex = element[key].cell.row;
                    if (rows[rowIndex]) {
                    } else {
                        rows[rowIndex] = new FormRowLayout({});
                    }
                    rows[rowIndex].put($.extend({
                        id: key
                    }, element[key]));
                });
            });
            this.rowData = [];
            Object.keys(rows).forEach(function (key) {
                _this.rowData.push(rows[key]);
            });
            this.rowData.sort(function (a, b) {
                return a.rowIndex() - b.rowIndex();
            });
        }
    },
    /**
     * return rows, order by "cell.row"
     */
    rows: function () {
        return this.rowData;
    },
    cell: function (key) {
        return this.allData[key];
    },
    all: function () {
        return this.allData;
    }
});
/**
 * Table Layout
 */
var TableLayout = LayoutProxyClass({
    initialize: function (layout) {
        var _this = this;
        this.label = layout.label;
        this.type = layout.type;
        this.cell = layout.cell;
        this.layout = layout.layout;
        this.columns = new TableColumnLayout(layout.columns);
        this.editWidth = layout.editWidth;

        var form = {};
        this.columns.columns().forEach(function (column) {
            form[column.data] = {
                label: column.title,
                cell: {
                    row: column.edit.row,
                    col: column.edit.col,
                    width: column.edit.width
                }
            };
            for (var key in column.edit) {
                if ("row,col,width,".indexOf(key) == -1) {
                    form[column.data][key] = column.edit[key];
                }
            }
        });
        this.form = new Layout(form);
    },
    editLayout: function () {
        return this.form;
    }
});
/**
 * table column layout
 */
var TableColumnLayout = LayoutProxyClass({
    initialize: function (columns) {
        this.all = {};
        this.array = [];
        for (var key in columns) {
            var column = columns[key];
            column.data = key;
            this.all[key] = column;
            this.array.push(column);
        }
        this.array.sort(function (a, b) {
            return a.index - b.index;
        });
    },
    columns: function () {
        return this.array;
    },
    map: function (func) {
        return this.columns().map(func);
    },
    forEach: function (func) {
        this.columns().forEach(func);
    },
    push: function (column) {
        this.array.push(column);
    },
    // parameters same as array.splice
    splice: function () {
        Array.prototype.splice.apply(this.array, arguments);
    },
    get: function (key) {
        return this.all[key];
    },
    length: function () {
        return this.array.length;
    },
    clone: function () {
        return new TableColumnLayout(this.all);
    }
});

/**
 * model layout proxy class
 */
var ModelLayoutProxyClass = function (methods) {
    var ModelLayoutProxy = function () {
        this.initialize.apply(this, arguments);
    };
    for (var property in methods) {
        ModelLayoutProxy.prototype[property] = methods[property];
    }
    if (!ModelLayoutProxy.prototype.initialize) {
        ModelLayoutProxy.prototype.initialize = function () {
        };
    }
    return ModelLayoutProxy;
};
/**
 * model layout proxy
 */
var ModelLayoutProxy = ModelLayoutProxyClass({
    initialize: function (modelLayout) {
        this.__modelLayout = modelLayout;
    },
    // create model
    create: function (modelLayout) {
        if (modelLayout === undefined) {
            modelLayout = this.__modelLayout;
        }
        var model = {};
        for (var key in modelLayout) {
            var propLayout = modelLayout[key];
            model[key] = this["__create" + propLayout.type.upperFirst()].call(this, propLayout);
        }
        return model;
    },
    __createPropValue: function (propLayout) {
        if (propLayout.dVal) {
            if (typeof (propLayout.dVal) === "function") {
                return propLayout.dVal();
            } else {
                return propLayout.dVal;
            }
        } else {
            return null;
        }
    },
    __createNumber: function (propLayout) {
        return this.__createPropValue(propLayout);
    },
    __createText: function (propLayout) {
        return this.__createPropValue(propLayout);
    },
    __createCode: function (propLayout) {
        return this.__createPropValue(propLayout);
    },
    __createBoolean: function (propLayout) {
        return this.__createPropValue(propLayout);
    },
    __createDate: function (propLayout) {
        return this.__createPropValue(propLayout);
    },
    __createList: function (propLayout) {
        var value = this.__createPropValue(propLayout);
        return value == null ? [] : value;
    },
    __createRefer: function (propLayout) {
        return this.__createPropValue(propLayout);
    },
    // get model layout of property
    get: function (propName) {
        return this.__modelLayout[propName];
    }
});

/**
 * validation proxy class
 */
var ValidationProxyClass = function (methods) {
    var ValidationProxy = function () {
        this.initialize.apply(this, arguments);
    };
    for (var property in methods) {
        ValidationProxy.prototype[property] = methods[property];
    }
    if (!ValidationProxy.prototype.initialize) {
        ValidationProxy.prototype.initialize = function () {
        };
    }
    return ValidationProxy;
};
/**
 * validation proxy
 */
var ValidationProxy = ValidationProxyClass({
    initialize: function (options) {
        this.__options = options;
    },
    getOptions: function () {
        return this.__options;
    },
    validate: function (value, model) {
        return this.__checkType(value, model) && this.__checkRules(value, model);
    },
    __initMessages: function () {
        if (this.__messages === undefined) {
            this.__messages = new Array();
        }
    },
    __pushMessage: function (message) {
        this.__initMessages();
        this.__messages.push(message);
    },
    __handleResult: function (result) {
        if (result.passed) {
            return true;
        } else {
            if (Array.isArray(result.msg)) {
                var _this = this;
                result.msg.forEach(function (msg) {
                    _this.__pushMessage(msg);
                });
            } else {
                this.__pushMessage(result.msg);
            }
            return false;
        }
    },
    __checkType: function (value, model) {
        var options = this.getOptions();
        var type = options.type;
        var typeValidator = Validators["_" + type];
        if (typeValidator === undefined) {
            return true;
        } else {
            return this.__handleResult(typeValidator(value, model));
        }
    },
    __checkRules: function (value, model) {
        var rules = this.getOptions().check;
        if (rules === undefined || rules == null) {
            return true;
        }
        var ret = true;
        for (var key in rules) {
            var validator = Validators[key];
            if (validator == undefined) {
                console.log("Validator of [" + key + "] is undefined.");
                continue;
            }
            ret = ret && this.__handleResult(validator(value, model, rules));
        }
        return ret;
    },
    getMessages: function () {
        return this.__messages === undefined ? [] : this.__messages;
    }
});
/**
 * validators
 */
var Validators = {
    _number: function (value) {
        return {
            passed: value == null || !isNaN(value),
            msg: "\"%1\" must be a number."
        };
    },
    _boolean: function (value) {
        return {
            passed: value == null || value == true || value == false,
            msg: "\"%1\" must be a boolean."
        };
    },
    _date: function (value) {
        return {
            passed: true
        };
    },
    required: function (value) {
        return {
            passed: value != null && (typeof (value) === "string" ? value.length != 0 : true),
            msg: "\"%1\" is required."
        };
    },
    // value must be a string
    length: function (value, model, rules) {
        return {
            passed: value == null || value.length <= rules.length,
            msg: "Maximum length of \"%1\" is " + rules.length + "."
        };
    },
    // value must be a string
    minLength: function (value, model, rules) {
        return {
            passed: value == null || value.length >= rules.minLength,
            msg: "Minimum length of \"%1\" is " + rules.minLength + "."
        };
    },
    size: function (value, model, rules) {
        return {
            passed: value == null || value.length <= rules.size,
            msg: "Maximum size of \"%1\" is " + rules.size + "."
        };
    },
    minSize: function (value, model, rules) {
        return {
            passed: value != null && value.length >= rules.minSize,
            msg: "Minimum size of \"%1\" is " + rules.minSize + "."
        };
    },
    // assert value
    assert: function (value, model, rules) {
        var assertValue = rules.assert;
        if (typeof (assertValue) == "function") {
            assertValue = assertValue(value, model, rules);
        }
        return {
            passed: value == null || value == assertValue,
            msg: "Value of \"%1\" must be \"" + assertValue + "\"."
        };
    },
    before: function (value, model, rules) {
        return Validators.__dateCompare(value, model, rules.before, rules.format, Validators.__beforeSpecial);
    },
    __beforeSpecial: function (momentValue, model, check, format) {
        var compareValue = Validators.__convertToMomentValue(check, model, format);
        if (compareValue != null) {
            if (momentValue.unix() > compareValue.unix()) {
                return {
                    passed: false,
                    msg: "Value of \"%1\" must be before than \"" + compareValue.format(format) + "\"."
                };
            }
        }
        return {
            passed: true
        };
    },
    after: function (value, model, rules) {
        return Validators.__dateCompare(value, model, rules.after, rules.format, Validators.__afterSpecial);
    },
    __afterSpecial: function (momentValue, model, check, format) {
        var compareValue = Validators.__convertToMomentValue(check, model, format);
        if (compareValue != null) {
            if (momentValue.unix() < compareValue.unix()) {
                return {
                    passed: false,
                    msg: "Value of \"%1\" must be after than \"" + compareValue.format(format) + "\"."
                };
            }
        }
        return {
            passed: true
        };
    },
    __convertToMomentValue: function (value, model, format) {
        var compareValue = null;
        if (value === "now") {
            compareValue = moment();
        } else if (value.unix !== undefined) {
            // value is a moment value
            compareValue = value;
        } else if (typeof (value) === "function") {
            compareValue = value();
        } else if (model[value] !== undefined) {
            // value from another property
            if (model[value] != null) {
                compareValue = moment(model[value], format);
            }
        } else {
            // plain date time string
            compareValue = moment(value, format);
        }
        return compareValue;
    },
    __dateCompare: function (value, model, checks, format, specialFunc) {
        if (value == null) {
            return {
                passed: true
            };
        }
        var dateValue = moment(value, format);
        var results = null;
        if (Array.isArray(checks)) {
            results = checks.map(function (check, index, checks) {
                return specialFunc(dateValue, model, check, format);
            });
        } else {
            results = [];
            results.push(specialFunc(dateValue, model, checks, format));
        }
        var passed = true;
        var messages = [];
        results.forEach(function (result, index, results) {
            passed = passed && result.passed;
            if (!result.passed) {
                messages.push(result.msg);
            }
        });
        return {
            passed: passed,
            msg: messages
        };
    }
};
