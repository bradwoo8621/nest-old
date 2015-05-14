/**
 * Codes proxy class
 */
var CodesProxyClass = function(methods) {
	var CodesProxy = function() {
		this.initialize.apply(this, arguments);
	};
	for ( var property in methods) {
		CodesProxy.prototype[property] = methods[property];
	}
	if (!CodesProxy.prototype.initialize) {
		CodesProxy.prototype.initialize = function() {
		};
	}
	return CodesProxy;
};
/**
 * Codes class
 */
var Codes = CodesProxyClass({
	/**
	 * initialize code object
	 * 
	 * @param array
	 *            {[{id, text}]} id is required. text is required when parameter
	 *            render is undefined, otherwise text is unnecessary. any other
	 *            property is free and optional.
	 * @param render
	 *            {func} optional. a function to process the code to text.
	 */
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
	/**
	 * get array of codes
	 * 
	 * @returns {[*]}
	 */
	list : function() {
		return this.codes;
	},
	/**
	 * get code by given id
	 * 
	 * @param id
	 * @returns {*}
	 */
	get : function(id) {
		return this.map[id];
	},
	/**
	 * filter by given function, return array of codes. signature of filter
	 * function refers to array.filter.
	 * 
	 * @param func
	 * @returns {[*]}
	 */
	filter : function(func) {
		return this.codes.filter(func);
	},
	/**
	 * check if there is at least one code match the given function. signature
	 * of some function refers to array.some.
	 * 
	 * @param func
	 * @returns {boolean}
	 */
	some : function(func) {
		return this.codes.some(func);
	}
});
