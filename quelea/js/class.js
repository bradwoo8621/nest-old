// add upperFirst function to String
if (String.prototype.upperFirst === undefined) {
    String.prototype.upperFirst = function () {
        if (this.length == 1) {
            return this.toUpperCase();
        } else {
            return this.substring(0, 1).toUpperCase() + this.substring(1)
        }
    };
}
if (String.prototype.endsWith === undefined) {
    String.prototype.endsWith = function (suffix) {
        return this.indexOf(suffix, this.length - suffix.length) !== -1;
    };
}
if (String.prototype.trim === undefined) {
    String.prototype.trim = function () {
        return this.replace(/^\s+|\s+$/gm, '');
    };
}
if (String.prototype.isEmpty === undefined) {
    String.prototype.isEmpty = function () {
        return this == "";
    };
}
if (String.prototype.isBlank === undefined) {
    String.prototype.isBlank = function () {
        return this.trim() == "";
    };
}
/**
 * replace place holders %1, %2, etc with given string array
 *
 * @param strArray
 * @returns
 */
if (String.prototype.format === undefined) {
    String.prototype.format = function (strArray) {
        return this.replace(/%(\d+)/g, function (_, m) {
            return strArray[--m];
        });
    };
}
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

/**
 * component constants
 * @type {class}
 */
var ComponentConstants = {
    // component types
    Text: "text",
    Select: "select",
    Check: "check",
    Radio: "radio",
    Table: "table",
    Date: "date",
    // exception codes
    Err_Unsupported_Component: "NEST-00001",
    Err_Unuspported_Column_Sort: "NEXT_00002"
};

// jsface conflict
jsface.noConflict();
// react-bootstrap tag name redefine
var Button = ReactBootstrap.Button;
var ButtonGroup = ReactBootstrap.ButtonGroup;
var ButtonToolbar = ReactBootstrap.ButtonToolbar;
var Glyphicon = ReactBootstrap.Glyphicon;
var Modal = ReactBootstrap.Modal;
var Panel = ReactBootstrap.Panel;

/**
 * model utilities
 * @type {class}
 */
var ModelUtil = jsface.Class({
    $statics: {
        /**
         * create model
         * @param jsonObject
         * @param objectValidator {ModelValidator}
         */
        createModel: function (jsonObject, objectValidator) {
            // create model
            var model = {
                constructor: function (model, objectValidator) {
                    this.__base = model;
                    this.__model = ModelUtil.cloneJSON(model);
                    this.__validator = objectValidator;
                    this.__validateResults = {};
                },
                /**
                 * get original model
                 * @returns {*}
                 */
                getOriginalModel: function () {
                    return this.__base;
                },
                /**
                 * get current model
                 * @returns {*}
                 */
                getCurrentModel: function () {
                    return this.__model;
                },
                get: function (id) {
                    return this.__model[id];
                },
                set: function (id, value) {
                    var oldValue = this.__model[id];
                    if (oldValue == value) {
                        // value is same as old value
                        return;
                    }
                    this.__model[id] = value;
                    this.fireEvent({
                        id: id,
                        old: oldValue,
                        "new": value,
                        time: "post",
                        type: "change"
                    });
                },
                /**
                 * add listener
                 * @param id id of property
                 * @param time post|pre
                 * @param type change|add|remove
                 * @param listener
                 */
                addListener: function (id, time, type, listener) {
                    if (this.listeners === undefined) {
                        this.listeners = {};
                    }
                    var key = id + "-" + time + "-" + type;
                    var listeners = this.listeners[key];
                    if (listeners === undefined) {
                        listeners = [];
                        this.listeners[key] = listeners;
                    }
                    var index = listeners.findIndex(function (item) {
                        return item == listener;
                    });
                    if (index == -1) {
                        listeners.push(listener);
                    }
                },
                /**
                 * remove listener
                 * @param id id of property
                 * @param time post|pre
                 * @param type change|add|remove
                 * @param listener
                 */
                removeListener: function (id, time, type, listener) {
                    if (this.listeners !== undefined) {
                        var listeners = this.listeners[id + "-" + time + "-" + type];
                        if (listeners !== undefined) {
                            // listeners is an array
                            var index = listeners.findIndex(function (item) {
                                return item = listener;
                            });
                            if (index != -1) {
                                listeners.splice(index, 1);
                            }
                        }
                    }
                },
                /**
                 * fire event
                 * @param evt
                 * @private
                 */
                fireEvent: function (evt) {
                    if (this.listeners === undefined) {
                        // no listner defined
                        return;
                    }
                    var key = evt.id + "-" + evt.time + "-" + evt.type;
                    var listeners = this.listeners[key];
                    if (listeners === undefined || listeners.length == 0) {
                        // no listener defined
                        return;
                    }
                    var _this = this;
                    listeners.forEach(function (listener) {
                        listener.call(_this, evt);
                    });
                },
                /**
                 * reset model
                 */
                reset: function () {
                    this.__model = ModelUtil.cloneJSON(this.__base);
                    this.__validateResults = {};
                },
                /**
                 * validate
                 */
                validate: function (id) {
                    if (id) {
                        var ret = this.__validator.validate(this, id);
                        if (ret !== true) {
                            this.__validateResults[id] = ret;
                        } else {
                            delete this.__validateResults[id];
                        }
                    } else {
                        this.__validateResults = this.__validator.validate(this);
                    }
                },
                /**
                 * check the property is required or not
                 * @param id
                 */
                isRequired: function (id) {
                    return this.__validator && this.__validator.getConfig()
                        && this.__validator.getConfig()[id]
                        && this.__validator.getConfig()[id].required != undefined;
                },
                getError: function (id) {
                    if (id) {
                        // property level
                        return this.__validateResults[id];
                    } else {
                        return this.__validateResults;
                    }
                },
                /**
                 * check the model is has error or not
                 * @returns {boolean}
                 */
                hasError: function (id) {
                    if (id) {
                        // property level
                        return this.__validateResults[id] !== undefined && this.__validateResults[id] != null;
                    } else {
                        return Object.keys(this.__validateResults).length != 0;
                    }
                },
                /**
                 * remove row from given id
                 * @param id
                 * @param row
                 */
                remove: function (id, row) {
                    var data = this.get(id);
                    if (data == null || data.length == 0) {
                        return;
                    }
                    var index = data.findIndex(function (item) {
                        return item == row;
                    });
                    if (index != -1) {
                        data.splice(index, 1);
                        this.fireEvent({
                            id: id,
                            array: data, // after remove
                            index: index,
                            old: row,
                            "new": null,
                            type: "remove",
                            time: "post"
                        });
                    }
                },
                /**
                 * add row into array by given id
                 * @param id
                 * @param row
                 */
                add: function (id, row) {
                    var data = this.get(id);
                    if (data == null) {
                        data = [];
                        this.set(id, data);
                    }
                    var index = data.findIndex(function (item) {
                        return item == row;
                    });
                    if (index == -1) {
                        data.push(row);
                        this.fireEvent({
                            id: id,
                            array: data,    // after add
                            index: data.length - 1,
                            old: null,
                            "new": row,
                            type: "add",
                            time: "post"
                        });
                    }
                },
                /**
                 * update new item instead of old item
                 * @param id
                 * @param _old
                 * @param _new
                 */
                update: function (id, _old, _new) {
                    var data = this.get(id);
                    if (data == null) {
                        return;
                    }
                    var index = data.findIndex(function (item) {
                        return item == _old;
                    })
                    if (index != -1) {
                        data.splice(index, 1, _new);
                        this.fireEvent({
                            id: id,
                            array: data, // after update
                            index: index,
                            old: _old,
                            "new": _new,
                            type: "change",
                            time: "post"
                        });
                    }
                }
            };
            // create getter and setter
            Object.keys(jsonObject).forEach(function (key) {
                var name = key.upperFirst();
                model["get" + name] = function () {
                    return this.get(name);
                };
                model["set" + name] = function (value) {
                    this.set(key, value);
                };
            });
            var ModelClass = jsface.Class(model);
            return new ModelClass(jsonObject, objectValidator);
        },
        /**
         * clone json object
         * @param jsonObject
         */
        cloneJSON: function (jsonObject) {
            return $.extend(true, {}, jsonObject);
        }
    }
});
/**
 * component exception
 * @param value
 * @param message
 * @constructor
 */
var ComponentException = function (value, message) {
    this.value = value;
    this.message = message;
    this.toString = function () {
        return this.value + ": " + this.message;
    };
};
/**
 * code table sorter
 * @type {class}
 */
var CodeTableSorter = jsface.Class({
    constructor: function (otherId) {
        this.otherId = otherId;
    },
    sort: function (codes) {
        var _this = this;
        codes.sort(function (a, b) {
            if (_this.otherId) {
                if (a.id == _this.otherId) {
                    return 1;
                } else if (b.id == _this.otherId) {
                    return -1;
                }
            }
            return a.text < b.text ? -1 : (a.text > b.text ? 1 : 0);
        });
    }
});
/**
 * code table
 * @type {class}
 */
var CodeTable = jsface.Class({
    /**
     * construct code table
     * @param codeTableArray array of json object, eg: {id:"1", text:"text"}
     * @param renderer optional
     * @param sorter {CodeTableSorter} optional
     */
    constructor: function (codeTableArray, renderer, sorter) {
        this.codes = codeTableArray;
        var map = {};
        this.codes.forEach(function (code) {
            map[code.id] = code;
            if (renderer) {
                code.text = renderer(code);
            }
        });
        this.map = map;
        if (sorter) {
            sorter.sort(this.codes);
        }
    },
    filter: function (func) {
        return this.codes.filter(func);
    },
    get: function (code) {
        return this.map[code];
    },
    getText: function (code) {
        var item = this.get(code);
        return (item === undefined || item == null) ? null : item.text;
    },
    list: function () {
        return this.codes;
    },
    some: function (func) {
        return this.codes.some(func);
    }
});

/**
 * form layout
 * @type {class}
 */
var FormLayout = jsface.Class({
    /**
     * constructor of FormLayout, accepts one or more json object
     */
    constructor: function () {
        if (arguments) {
            var argus = [];
            for (var index = 0, count = arguments.length; index < count; index++) {
                argus.push(arguments[index]);
            }
        }
        // all cells map
        this.__all = {};
        var all = this.__all;
        // an json object, contains key of rowIndex
        var rows = {};
        argus.forEach(function (argu) {
            // read cells of arguments
            Object.keys(argu).forEach(function (key) {
                var cell = new CellLayout(key, argu[key]);
                all[cell.getId()] = cell;
                var rowIndex = cell.getRowIndex();
                var rowLayout = rows[rowIndex];
                if (rowLayout === undefined) {
                    // initialize row layout
                    rowLayout = new RowLayout(rowIndex);
                    rows[rowIndex] = rowLayout;
                }
                rowLayout.addCell(cell);
            });
        });
        // put rows json to array
        this.__rows = [];
        var rowsArray = this.__rows;
        Object.keys(rows).forEach(function (key) {
            rowsArray.push(rows[key]);
        });
        // sort
        rowsArray.sort(function (r1, r2) {
            return r1.getRowIndex() - r2.getRowIndex();
        });
    },
    /**
     * get all rows
     * @returns {[RowLayout]}
     */
    getRows: function () {
        return this.__rows;
    },
    /**
     * get cell layout
     */
    getCell: function (id) {
        return this.__all[id];
    }
});
/**
 * row layout
 * @type {class}
 */
var RowLayout = jsface.Class({
    constructor: function (rowIndex) {
        this.__rowIndex = rowIndex;
    },
    /**
     * get row index
     * @returns {number}
     */
    getRowIndex: function () {
        return this.__rowIndex;
    },
    /**
     * add cell
     */
    addCell: function (cell) {
        if (this.__cells === undefined) {
            this.__cells = [];
        }
        this.__cells.push(cell);
        this.__cells.sort(function (c1, c2) {
            return c1.getColumnIndex() - c2.getColumnIndex();
        });
    },
    /**
     * get cells
     * @returns {[CellLayout]}
     */
    getCells: function () {
        return this.__cells;
    }
});
/**
 * cell layout
 * @type {class}
 */
var CellLayout = jsface.Class({
    constructor: function (id, cell) {
        this.__id = id;
        this.__cell = cell;
    },
    /**
     * get id
     * @returns {*}
     */
    getId: function () {
        return this.__id;
    },
    /**
     * get row index
     * @returns {string}
     */
    getRowIndex: function () {
        return this.__cell.pos.row;
    },
    /**
     * get column index
     * @returns {Array|string|boolean|*}
     */
    getColumnIndex: function () {
        return this.__cell.pos.col;
    },
    /**
     * get width of cell, default is 3
     * @returns {number}
     */
    getWidth: function () {
        return this.__cell.pos.width ? this.__cell.pos.width : 3;
    },
    /**
     * get cell CSS, if not defined, return empty string
     * @param originalCSS optional
     * @returns {string}
     */
    getCellCSS: function (originalCSS) {
        return this.getAdditionalCSS("cell", originalCSS);
    },
    /**
     * get component type
     * @returns {string}
     */
    getComponentType: function () {
        var type = this.getComponentOption("type");
        return type == null ? ComponentConstants.Text : type;
    },
    /**
     * get component option by given key, return null when not defined
     * @param key optional, return all options if parameter not passed
     * @param defaultValue optional, only effective when key passed
     * @returns {*}
     */
    getComponentOption: function (key, defaultValue) {
        if (key) {
            if (this.__cell.comp) {
                var option = this.__cell.comp[key];
                return option === undefined ? defaultValue : option;
            } else {
                return defaultValue;
            }
        }
        return this.__cell.comp === undefined ? {} : this.__cell.comp;
    },
    /**
     * set component option by given key and value
     * @param key
     * @param value
     */
    setComponentOption: function (key, value) {
        if (this.__cell.comp === undefined) {
            this.__cell.comp = {};
        }
        this.__cell.comp[key] = value;
    },
    /**
     * get label
     * @returns {string}
     */
    getLabel: function () {
        return this.__cell.label;
    },
    /**
     * get label CSS, return empty string if not defined
     * @param originalCSS optional
     * @returns {string}
     */
    getLabelCSS: function (originalCSS) {
        return this.getAdditionalCSS("label", originalCSS);
    },
    /**
     * is additional css defined
     * @param key optional
     * @returns {boolean}
     */
    isAdditionalCSSDefined: function (key) {
        if (key) {
            return this.isAdditionalCSSDefined() && this.__cell.css[key] !== undefined;
        }
        return this.__cell.css !== undefined;
    },
    /**
     * get additional css object, return {} when not defined
     * @param key optional, return string or empty string(not defined) when passed this parameter
     * @param originalCSS optional, combine with additional CSS if exists
     * @returns {*|string}
     */
    getAdditionalCSS: function (key, originalCSS) {
        if (key) {
            var additionalCSS = this.isAdditionalCSSDefined(key) ? this.__cell.css[key] : "";
            if (originalCSS !== undefined && originalCSS.length != 0) {
                return originalCSS + " " + additionalCSS;
            } else {
                return additionalCSS;
            }
        }
        return this.isAdditionalCSSDefined() ? this.__cell.css : {};
    }
});
/**
 * table column layout, an array like object
 * @type
 */
var TableColumnLayout = jsface.Class({
    constructor: function (columns) {
        this.__columns = columns;
    },
    /**
     * clone
     * @returns {TableColumnLayout}
     */
    clone: function () {
        return new TableColumnLayout(this.__columns.slice(0));
    },
    columns: function () {
        return this.__columns;
    },
    get: function (index) {
        return this.__columns[index];
    },
    // implements methods of array
    push: function (column) {
        this.__columns.push(column);
    },
    splice: function (index, removeCount, newItem) {
        this.__columns.splice(index, removeCount, newItem);
    },
    map: function (func) {
        return this.__columns.map(func);
    },
    forEach: function (func) {
        this.__columns.forEach(func);
    },
    length: function () {
        return this.__columns.length;
    },
    some: function (func) {
        return this.__columns.some(func);
    }
});
/**
 * model validator
 * @type {class}
 */
var ModelValidator = jsface.Class({
    constructor: function (validatorConfig, rules) {
        this.__validator = validatorConfig;
        this.__rules = rules;
        if (this.__rules === undefined) {
            this.__rules = ValidateRules;
        }
    },
    /**
     * get configuration
     * @returns {*}
     */
    getConfig: function () {
        return this.__validator;
    },
    /**
     * get rule
     * @param ruleName
     * @returns {*}
     */
    getRule: function (ruleName) {
        return this.__rules[ruleName];
    },
    /**
     * validate
     * @param model
     * @param id
     * @returns {*}
     */
    validate: function (model, id) {
        if (id) {
            var config = this.getConfig()[id];
            if (config === undefined) {
                return true;
            } else {
                var _this = this;
                var result = Object.keys(config).map(function (rule) {
                    var ret = null;
                    var ruleBody = config[rule];
                    var value = model.get(id);
                    if (typeof ruleBody === "function") {
                        ret = ruleBody.call(_this, model, value);
                    } else {
                        ret = _this.getRule(rule).call(_this, model, value, ruleBody);
                    }
                    return ret != true ? ret : null;
                });
                var finalResult = [];
                result.forEach(function (item) {
                    if (item == null) {
                        return;
                    }
                    if (Array.isArray(item)) {
                        // array of plain string
                        item.forEach(function (i) {
                            finalResult.push(i);
                        });
                    } else {
                        // plain string
                        finalResult.push(item);
                    }
                });
                return finalResult.length == 0 ? true : finalResult;
            }
        } else {
            var result = {};
            var _this = this;
            Object.keys(this.getConfig()).forEach(function (id) {
                var ret = _this.validate(model, id);
                if (ret != true) {
                    result[id] = ret;
                }
            });
            return result;
        }
    }
});
/**
 * validate rules
 * @type {class}
 */
var ValidateRules = {
    required: function (model, value) {
        if (value == null || value.toString().isEmpty()) {
            return "\"%1\" is Required.";
        } else {
            return true;
        }
    },
    length: function (model, value, length) {
        if (value == null) {
            return true;
        } else if (value.length != length) {
            return "Length of \"%1\" should be " + length + "."
        }
    },
    maxlength: function (model, value, length) {
        if (value == null) {
            return true;
        } else if (value.length > length) {
            return "Length of \"%1\" cannot be more than " + length + ".";
        }
    },
    minlength: function (model, value, length) {
        if (value == null) {
            return true;
        } else if (value.length < length) {
            return "Length of \"%1\" cannot be less than " + length + ".";
        }
    },
    before: function (model, value, settings) {
        return ValidateRules.__dateCompare(value, model, settings, ValidateRules.__beforeSpecial);
    },
    /**
     * before special time
     * @param momentValue
     * @param model
     * @param check
     * @param format
     * @param label
     * @returns {*}
     * @private
     */
    __beforeSpecial: function (momentValue, model, check, format, label) {
        var compareValue = ValidateRules.__convertToMomentValue(check, model, format);
        if (compareValue != null) {
            if (momentValue.unix() > compareValue.unix()) {
                if (typeof check === "string") {
                    return "Value of \"%1\" must be before than "
                        + (label ? label : check) + ".";
                }
                return "Value of \"%1\" must be before than \"" + compareValue.format(format) + "\".";
            }
        }
        return true;
    },
    after: function (model, value, settings) {
        return ValidateRules.__dateCompare(value, model, settings, ValidateRules.__afterSpecial);
    },
    /**
     * after special time
     * @param momentValue
     * @param model
     * @param check
     * @param format
     * @param label
     * @returns {*}
     * @private
     */
    __afterSpecial: function (momentValue, model, check, format, label) {
        var compareValue = ValidateRules.__convertToMomentValue(check, model, format);
        if (compareValue != null) {
            if (momentValue.unix() < compareValue.unix()) {
                if (typeof check === "string") {
                    return "Value of \"%1\" must be after than "
                        + (label ? label : check) + ".";
                }
                return "Value of \"%1\" must be after than \"" + compareValue.format(format) + "\".";
            }
        }
        return true;
    },
    /**
     * convert value to momentjs object
     * @param value
     * @param model
     * @param format
     * @returns {*}
     * @private
     */
    __convertToMomentValue: function (value, model, format) {
        var compareValue = null;
        if (value === "now") {
            compareValue = moment();
        } else if (value.unix !== undefined) {
            // value is a moment value
            compareValue = value;
        } else if (typeof (value) === "function") {
            compareValue = value();
        } else if (model.get(value) !== undefined) {
            // value from another property
            if (model.get(value) != null) {
                compareValue = moment(model.get(value), format);
            }
        } else {
            // plain date time string
            compareValue = moment(value, format);
        }
        return compareValue;
    },
    /**
     * compare data
     * @param value
     * @param model
     * @param settings
     * @param checkFunc
     * @returns {[string]|string}
     * @private
     */
    __dateCompare: function (value, model, settings, checkFunc) {
        if (value == null) {
            return true;
        }
        var format = typeof settings.format === "string" ? settings.format : "YYYY/MM/DD";
        var checks = settings.rule ? settings.rule : settings;
        var labels = settings.rule ? settings.label : null;
        var dateValue = moment(value, format);
        var results = [];
        if (Array.isArray(checks)) {
            for (var index = 0, count = checks.length; index < count; index++) {
                var check = checks[index];
                var label = labels ? labels[index] : null;
                results.push(checkFunc(dateValue, model, check, format, label));
            }
        } else {
            results.push(checkFunc(dateValue, model, checks, format, labels));
        }
        var messages = results.filter(function (result) {
            return result != true;
        })
        return messages.length == 0 ? true : (messages.length == 1 ? messages[0] : messages);
    }
};