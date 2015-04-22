/**
 * find element from array, return null if not found.
 */
Array.prototype.find = function(func) {
	for (var index = 0, count = this.length; index < count; index++) {
		if (func.call(this, this[index], index, this)) {
			return this[index];
		}
	}
	return null;
};
/**
 * find element index from array, return -1 if not found.
 */
Array.prototype.findIndex = function(func) {
	for (var index = 0, count = this.length; index < count; index++) {
		if (func.call(this, this[index], index, this)) {
			return index;
		}
	}
	return -1;
};
String.prototype.upperFirst = function() {
	if (this.length == 1) {
		return this.toUpperCase();
	} else {
		return this.substring(0, 1).toUpperCase() + this.substring(1)
	}
};
/**
 * Model Proxy class.
 */
var ModelProxyClass = function(methods) {
	var ModelProxy = function() {
		this.initialize.apply(this, arguments);
	};
	for ( var property in methods) {
		ModelProxy.prototype[property] = methods[property];
	}
	if (!ModelProxy.prototype.initialize) {
		ModelProxy.prototype.initialize = function() {
		};
	}
	return ModelProxy;
};
// Codes
var Codes = ModelProxyClass({
	initialize : function(array, render) {
		this.codes = array;
		var map = {};
		this.codes.forEach(function(code, index, array) {
			map[code.id] = code;
			if (render) {
				code.text = render(code);
			}
		});
		this.map = map;
	},
	list : function() {
		return this.codes;
	},
	get : function(id) {
		return this.map[id];
	},
	// same as array.filter
	filter : function(func) {
		return this.codes.filter(func);
	},
	// same as array.some
	some : function(func) {
		return this.codes.some(func);
	}
});
/**
 * Model Proxy. eg. var model = new ModelProxy({}); Parameter must be a JSON
 * object.
 */
var ModelProxy = ModelProxyClass({
	initialize : function(model, modelConfig) {
		// init listeners
		this.__propertyListeners = {};
		// init model
		this.__modelConfig = modelConfig;
		this.__initModel = model;
		this.__model = ModelUtil.clone(model);
		var _this = this;
		Object.keys(model).forEach(function(key) {
			var value = model[key];
			// values, create getter/setter
			var upper = key.upperFirst();
			_this["get" + upper] = function() {
				return this.__model[key];
			};
			_this["set" + upper] = function(newValue) {
				var oldValue = this.__model[key];
				console.debug(upper + "'s value changes from [" + oldValue + "] to [" + newValue + "].");
				if (newValue == oldValue) {
					// equals, skip the invoke
					console.debug("Set operation is skipped since new value equals old value.");
					return;
				}

				// invoke pre change event
				this.__fireEvent({
					key : key,
					oldValue : oldValue,
					newValue : newValue,
					time : "pre",
					type : "change"
				});
				this.__model[key] = newValue;
				// invoke post change event
				this.__fireEvent({
					key : key,
					oldValue : oldValue,
					newValue : newValue,
					time : "post",
					type : "change"
				});
			}
		});
	},
	__fireEvent : function(evt) {
		var key = evt.key + "-" + evt.time + "-" + evt.type;
		if (this.__propertyListeners[key]) {
			var _this = this;
			this.__propertyListeners[key].forEach(function(element) {
				element.call(_this, evt);
			});
		}
	},
	__convertListenerType : function(type) {
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
	addListener : function(propName, types, listener) {
		if (!Array.isArray(types)) {
			types = [ types ];
		}
		var _this = this;
		types.forEach(function(type) {
			var key = propName + "-" + _this.__convertListenerType(type);
			if (_this.__propertyListeners[key] === undefined) {
				_this.__propertyListeners[key] = new Array();
				_this.__propertyListeners[key].push(listener);
			} else {
				var index = _this.__propertyListeners[key].findIndex(function(element) {
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
	removeListener : function(propName, types, listener) {
		if (!Array.isArray(types)) {
			types = [ types ];
		}
		var _this = this;
		types.forEach(function(type) {
			var key = propName + "-" + _this.__convertListenerType(type);
			if (_this.__propertyListeners[key] === undefined) {
				return;
			} else {
				var index = _this.__propertyListeners[key].findIndex(function(element) {
					return element === listener;
				});
				if (index != -1) {
					_this.__propertyListeners[key].splice(index, 1);
				}
			}
		});
	},
	// get
	get : function(propName) {
		var methodName = "get" + propName.upperFirst();
		return this[methodName].call(this);
	},
	set : function(propName, value) {
		var methodName = "set" + propName.upperFirst();
		this[methodName].call(this, value);
	},
	// add item into given property array value
	// if index is not appointed, will add item at the end of array.
	add : function(propName, newItem, index) {
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
				key : propName,
				array : array,
				item : newItem,
				index : index,
				type : "add",
				time : "pre",
			});
			array.splice(index, 0, newItem);
			this.__fireEvent({
				key : propName,
				array : array,
				item : newItem,
				index : index,
				type : "add",
				time : "post"
			});
		} else {
			this.__fireEvent({
				key : propName,
				array : array,
				oldValue : null,
				newValue : newItem,
				index : array.length,
				type : "add",
				time : "pre",
			});
			array.push(newItem);
			this.__fireEvent({
				key : propName,
				array : array,
				oldValue : null,
				newValue : newItem,
				index : array.length - 1,
				type : "add",
				time : "post"
			});
		}
	},
	// remove item from give property value array
	remove : function(propName, itemOrIndex) {
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
						name : "IndexOutOfBoundsError",
						message : "Invalid index [" + index + "] of size [" + array.length
								+ "] for do removing from property [" + propName + "]."
					};
				} else {
					item = array[index];
				}
			} else {
				item = itemOrIndex;
				index = array.findIndex(function(element) {
					return element == item;
				});
			}
			if (index != -1) {
				this.__fireEvent({
					key : propName,
					array : array,
					oldValue : item,
					newValue : null,
					index : index,
					type : "remove",
					time : "pre",
				});
				array.splice(index, 1);
				this.__fireEvent({
					key : propName,
					array : array,
					oldValue : item,
					newValue : null,
					index : index,
					type : "remove",
					time : "post"
				});
			}
		}
	},
	update : function(propName, newItem, index) {
		var array = this.get(propName);
		if (array == null) {
			throw {
				name : "IndexOutOfBoundsError",
				message : "Invalid index [" + index + "] of uninitialized array for property [" + propName + "]."
			};
		} else {
			if (index < 0 || index > array.length - 1) {
				throw {
					name : "IndexOutOfBoundsError",
					message : "Invalid index [" + index + "] of size [" + array.length + "] for do updating property ["
							+ propName + "]."
				};
			} else {
				var oldItem = array[index];
				this.__fireEvent({
					key : propName,
					array : array,
					oldValue : oldItem,
					newValue : newItem,
					index : index,
					type : "change",
					time : "pre",
				});
				array.splice(index, 1, newItem);
				this.__fireEvent({
					key : propName,
					array : array,
					oldValue : oldItem,
					newValue : newItem,
					index : index,
					type : "change",
					time : "post",
				});
			}
		}
	},
	// reset data model, clone from init model
	reset : function() {
		this.__model = ModelUtil.clone(this.__initModel);
	},
	// get data model, json object
	getDataModel : function() {
		return this.__model;
	},
	getDataModelConfig : function() {
		return this.__modelConfig;
	},
});

/**
 * Model Utilities
 */
var ModelUtil = {
	clone : function(json) {
		return $.extend(true, {}, json);
	},
	// create a model, layout is an instance of Layout
	create : function(layout) {
		var model = {};
		for ( var key in layout.all()) {
			var cell = layout.cell(key);
			if (cell.type == "table") {
				// array, initialize to empty array
				model[key] = [];
			} else {
				// plain field, initialize to null
				model[key] = null;
			}
		}
		return new ModelProxy(model);
	}
};

/**
 * Layout Proxy class.
 */
var LayoutProxyClass = function(methods) {
	var LayoutProxy = function() {
		this.initialize.apply(this, arguments);
	};
	for ( var property in methods) {
		LayoutProxy.prototype[property] = methods[property];
	}
	if (!LayoutProxy.prototype.initialize) {
		LayoutProxy.prototype.initialize = function() {
		};
	}
	return LayoutProxy;
}
/**
 * Row Layout
 */
var FormRowLayout = LayoutProxyClass({
	initialize : function() {
		this.cellData = [];
	},
	put : function(cell) {
		this.row = cell.cell.row;
		this.cellData.push(cell);
		this.cellData.sort(function(a, b) {
			return a.cell.col - b.cell.col;
		});
	},
	cells : function() {
		return this.cellData;
	},
	rowIndex : function() {
		return this.row;
	}
});
/**
 * Form Layout
 */
var Layout = LayoutProxyClass({
	initialize : function() {
		this.allData = {};
		var rows = {};
		if (arguments) {
			var argus = [];
			for (var index = 0, count = arguments.length; index < count; index++) {
				argus.push(arguments[index]);
			}
			var _this = this;
			argus.forEach(function(element) {
				Object.keys(element).forEach(function(key) {
					_this.allData[key] = element[key];
					var rowIndex = element[key].cell.row;
					if (rows[rowIndex]) {
					} else {
						rows[rowIndex] = new FormRowLayout({});
					}
					rows[rowIndex].put($.extend({
						id : key
					}, element[key]));
				});
			});
			this.rowData = [];
			Object.keys(rows).forEach(function(key) {
				_this.rowData.push(rows[key]);
			});
			this.rowData.sort(function(a, b) {
				return a.rowIndex() - b.rowIndex();
			});
		}
	},
	/**
	 * return rows, order by "cell.row"
	 */
	rows : function() {
		return this.rowData;
	},
	cell : function(key) {
		return this.allData[key];
	},
	all : function() {
		return this.allData;
	}
});
/**
 * Table Layout
 */
var TableLayout = LayoutProxyClass({
	initialize : function(layout) {
		var _this = this;
		this.label = layout.label;
		this.type = layout.type;
		this.cell = layout.cell;
		this.layout = layout.layout;
		this.columns = new TableColumnLayout(layout.columns);
		this.editWidth = layout.editWidth;

		var form = {};
		this.columns.columns().forEach(function(column) {
			form[column.data] = {
				label : column.title,
				cell : {
					row : column.edit.row,
					col : column.edit.col,
					width : column.edit.width
				}
			};
			for ( var key in column.edit) {
				if ("row,col,width,".indexOf(key) == -1) {
					form[column.data][key] = column.edit[key];
				}
			}
		});
		this.form = new Layout(form);
	},
	editLayout : function() {
		return this.form;
	}
});
/**
 * table column layout
 */
var TableColumnLayout = LayoutProxyClass({
	initialize : function(columns) {
		this.all = {};
		this.array = [];
		for ( var key in columns) {
			var column = columns[key];
			column.data = key;
			this.all[key] = column;
			this.array.push(column);
		}
		this.array.sort(function(a, b) {
			return a.index - b.index;
		});
	},
	columns : function() {
		return this.array;
	},
	map: function(func) {
		return this.columns().map(func);
	},
	forEach: function(func) {
		this.columns().forEach(func);
	},
	push : function(column) {
		this.array.push(column);
	},
	// parameters same as array.splice
	splice : function() {
		Array.prototype.splice.apply(this.array, arguments);
	},
	get : function(key) {
		return this.all[key];
	},
	length: function() {
		return this.array.length;
	},
	clone: function() {
		return new TableColumnLayout(this.all);
	},
});